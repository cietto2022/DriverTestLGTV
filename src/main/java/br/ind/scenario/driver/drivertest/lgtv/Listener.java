package br.ind.scenario.driver.drivertest.lgtv;

import java.util.Map;

public class Listener implements DriverListener {

    LGTV lgtv;

    public Listener(LGTV lg) {
        this.lgtv = lg;
    }

    @Override
    public void notify(Map<String, Object> value) {
        value.forEach((s, o) -> {
            System.out.println("JAVA:: " + s + ": " + o);
            this.lgtv.metadata.put(s,o);
            this.lgtv.processMetadata(s, o);
        });
    }
    @Override
    public void notifyUser(String s) {
        System.out.println(s);
    }

    @Override
    public void notifyError(String s){
        // send to Log?
        notifyUser("Erro!");
    }

}
