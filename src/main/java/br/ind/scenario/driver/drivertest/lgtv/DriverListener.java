package br.ind.scenario.driver.drivertest.lgtv;

import java.util.Map;

public interface DriverListener {

    void notify(Map<String, Object> value);
    void notifyUser(String s);
    void notifyError(String s);

}
