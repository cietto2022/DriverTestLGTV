package br.ind.scenario.driver.drivertest;

import br.ind.scenario.driver.core.ParameterizedDriver;
import br.ind.scenario.driver.core.command.Command;
import br.ind.scenario.driver.core.command.CommandBuilder;
import br.ind.scenario.driver.core.command.CommandDeliverer;
import br.ind.scenario.driver.core.params.DriverParams;
import br.ind.scenario.driver.drivertest.lgtv.LGTV;
import br.ind.scenario.driver.fakecontroller.FakeDriverController;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class DriverTest extends ParameterizedDriver<JepCommunication.JepMessage> {

    @NotNull
    private final JepCommunication jepCommunication;
    private LGTV lgtv;

    public DriverTest(int id, @NotNull LinkedBlockingQueue<Command> receivedCommandQueue,
                      @NotNull CommandDeliverer commandDeliverer,
                      @NotNull DriverParams driverParams,
                      @NotNull Map<String, List<Integer>> driversIds) {
        super(id, receivedCommandQueue, commandDeliverer, driverParams, driversIds);
        this.jepCommunication = new JepCommunication();
    }

    @Override
    public void initialize() {
        jepCommunication.start();
        this.lgtv = new LGTV();
        this.lgtv.setupLGTV();
    }

    @Override
    protected void receive(@NotNull JepCommunication.JepMessage commandParsed) {
        jepCommunication.sendMessage(commandParsed);
    }

    @Override
    protected @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    protected @NotNull Optional<JepCommunication.JepMessage> parse(@NotNull Command command) {
        var optionalExternalId = command.getFunctionCode();
        if (optionalExternalId.isEmpty()) return Optional.empty();

        var optionalInternalId = driverParams.getInternalByExternalCode(optionalExternalId.get());
        if (optionalInternalId.isEmpty()) return Optional.empty();

        switch (command.getType()) {
            case DIGITAL:
                System.out.println(command.getPayload().toString());
                switch (optionalInternalId.get()) {
                    case 100:
                        this.lgtv.volumeUpReq();
                        break;
                    case 200:
                        this.lgtv.volumeDownReq();
                        break;
                    case 300:
                        this.lgtv.mute();
                        break;
                    case 400:
                        this.lgtv.left();
                        break;
                    case 500:
                        this.lgtv.right();
                        break;
                    case 600:
                        this.lgtv.up();
                        break;
                    case 700:
                        this.lgtv.down();
                        break;
                    case 800:
                        this.lgtv.play();
                        break;
                    case 900:
                        this.lgtv.pause();
                        break;
                    case 1000:
                        this.lgtv.enter();
                        break;
                    case 1100:
                        this.lgtv.home();
                        break;
                    case 1200:
                        this.lgtv.launchReq("youtube.leanback.v4");
                        this.lgtv.launchAppReq("youtube.leanback.v4");
                        break;
                }
                return Optional.of(new JepCommunication.JepMessage(optionalInternalId.get(), command.getDigital().orElse(false)));
            case ANALOG:
                var optionalAnalog = command.getAnalog();
                if (optionalAnalog.isEmpty()) break;
                var analog = optionalAnalog.get().getFirst();

                if (analog == 65535) {
                    var optionalExternalCode = driverParams.getExternalByInternalCode(1000);
                    optionalExternalCode.ifPresent(integers -> {
                        var commandToSend = new CommandBuilder(getId(), -3, true)
                                .withFunctionCode(integers.get(0))
                                .build();
                        sendCommand(commandToSend);
                    });
                }

                return Optional.of(new JepCommunication.JepMessage(optionalInternalId.get(), analog));
        }

        return Optional.empty();
    }

    public static void main(String[] args) throws InterruptedException {
        FakeDriverController.start();
    }
}

