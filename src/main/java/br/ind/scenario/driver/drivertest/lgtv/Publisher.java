package br.ind.scenario.driver.drivertest.lgtv;

import jep.Interpreter;
import jep.JepException;

public class Publisher{
    private Interpreter interp;
    public Publisher(Interpreter interp) throws JepException {
        this.interp = interp;
        String pythonScriptFullPath = "./src/main/resources/scenario_lgtv.py";
        this.interp.runScript(pythonScriptFullPath);
        this.interp.exec("lg_tv = LG_TV()");
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.create_lgtv())");
    }

    public void connect() throws JepException {
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.connect())");
    }

    public void setListener(Listener listener) throws JepException {
        this.interp.set("java_listener", listener);
        this.interp.exec("lg_tv._listener = java_listener");
    }

    public void button(String name){
        this.interp.set("buttonName", name);
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.client.button(buttonName))");
    }

    public void request(String cmd){
        this.interp.set("cmdName", cmd);
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.client.request(cmdName))");
    }

    public void request(String cmd, String app){
        this.interp.set("cmdName", cmd);
        this.interp.set("app", app);
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.client.request(cmdName, app))");
    }


    public void sleep(){
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.sleep())");
    }
}
