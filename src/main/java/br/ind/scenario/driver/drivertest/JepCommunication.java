package br.ind.scenario.driver.drivertest;

import org.jetbrains.annotations.NotNull;

public class JepCommunication extends Thread{

    @Override
    public void run() {
        while (true){

        }
    }

    public void sendMessage(@NotNull JepMessage message) {
        System.out.printf("SEND MESSAGE TO JEP %s%n", message);
    }

    public static class JepMessage {
        public final int id;

        public final Object status;

        public JepMessage(int id, boolean status) {
            this.id = id;
            this.status = status;
        }

        public JepMessage(int id, int value) {
            this.id = id;
            this.status = value;
        }

        @Override
        public String toString() {
            return "JepMessage{" +
                    "id=" + id +
                    ", status=" + status +
                    '}';
        }
    }
}
