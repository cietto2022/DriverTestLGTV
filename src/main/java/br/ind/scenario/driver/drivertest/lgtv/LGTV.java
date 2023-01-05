package br.ind.scenario.driver.drivertest.lgtv;

import jep.Interpreter;
import jep.SharedInterpreter;

import java.util.HashMap;
import java.util.Map;

public class LGTV {

    Map<String, Object> metadata = new HashMap<>();
    Publisher publisher;
    Interpreter interp;
    public LGTV() {
        this.interp = new SharedInterpreter();
    }

    public void setupLGTV(){
        this.publisher = new Publisher(this.interp);
        //LGTV atv = new LGTV(publisher);
        Listener listener = new Listener(this);
        publisher.setListener(listener);
        publisher.connect();
    }

    public void processMetadata(String s, Object o){
        switch (s){
            case "Is on":
                if (o.toString().matches("true")){
                    System.out.println("Tv is on");
                } else {
                    System.out.println("Tv is off");
                    publisher.connect();
                }
                break;
            case "Is connected":
                if (o.toString().matches("true")){
                    System.out.println("Tv is connected");
                } else {
                    System.out.println("Tv is not connected");
                    publisher.connect();
                }
                break;
            case "Volume":
                System.out.println("Volume = " + o);
                break;
        }
    }

    // All button commands from remote control
    public void left(){
        this.publisher.button("LEFT");
    }

    public void right(){
        this.publisher.button("RIGHT");
    }

    public void up(){
        this.publisher.button("UP");
    }

    public void down(){
        this.publisher.button("DOWN");
    }

    public void home(){
        this.publisher.button("HOME");
    }
    public void back(){
        this.publisher.button("BACK");
    }

    public void enter(){
        this.publisher.button("ENTER");
    }

    public void dash(){
        this.publisher.button("DASH");
    }

    public void info(){
        this.publisher.button("INFO");
    }

    public void asterisk(){
        this.publisher.button("ASTERISK");
    }

    public void cc(){
        this.publisher.button("CC");
    }

    public void exit(){
        this.publisher.button("EXIT");
    }

    public void mute(){
        this.publisher.button("MUTE");
    }

    public void red(){
        this.publisher.button("RED");
    }

    public void green(){
        this.publisher.button("GREEN");
    }

    public void blue(){
        this.publisher.button("BLUE");
    }

    public void volumeUp(){
        this.publisher.button("VOLUMEUP");
    }

    public void volumeDown(){
        this.publisher.button("VOLUMEDOWN");
    }

    public void channelUp(){
        this.publisher.button("CHANNELUP");
    }

    public void channelDown(){
        this.publisher.button("CHANNELDOWN");
    }

    public void play(){
        this.publisher.button("PLAY");
    }

    public void pause(){
        this.publisher.button("PAUSE");
    }

    public void zero(){
        this.publisher.button("0");
    }

    public void one(){
        this.publisher.button("1");
    }

    public void two(){
        this.publisher.button("2");
    }

    public void three(){
        this.publisher.button("3");
    }

    public void four(){
        this.publisher.button("4");
    }

    public void five(){
        this.publisher.button("5");
    }

    public void six(){
        this.publisher.button("6");
    }

    public void seven(){
        this.publisher.button("7");
    }

    public void eight(){
        this.publisher.button("8");
    }

    public void nine(){
        this.publisher.button("9");
    }


    //All tv commands (endpoints)
    public void powerOffCmd(){
        this.publisher.request("system/turnOff");
    }

    public void getServicesReq(){
        this.publisher.request("api/getServiceList");
    }

    public void setMuteReq(){ this.publisher.request("audio/setMute"); }

    public void getStatusReq(){
        this.publisher.request("audio/getStatus");
    }

    public void getVolumeReq(){
        this.publisher.request("audio/getVolume");
    }

    public void setVolumeReq(){
        this.publisher.request("audio/setVolume");
    }

    public void volumeUpReq(){
        this.publisher.request("audio/volumeUp");
    }

    public void volumeDownReq(){
        this.publisher.request("audio/volumeDown");
    }

    public void getCurrentAppInfoReq(){
        this.publisher.request("com.webos.applicationManager/getForegroundAppInfo");
    }

    public void launchAppReq(String app){
        this.publisher.request("com.webos.applicationManager/launch", app);
    }

    public void getAppsReq(){
        this.publisher.request("com.webos.applicationManager/listLaunchPoints");
    }

    public void getAppsAllReq(){
        this.publisher.request("com.webos.applicationManager/listApps");
    }

    public void sendEnterReq(){
        this.publisher.request("com.webos.service.ime/sendEnterKey");
    }

    public void sendDeleteReq(){
        this.publisher.request("com.webos.service.ime/deleteCharacters");
    }

    public void insertTextReq(){
        this.publisher.request("com.webos.service.ime/insertText");
    }

    public void set3DOnReq(){
        this.publisher.request("com.webos.service.tv.display/set3DOn");
    }

    public void set3DOffReq(){
        this.publisher.request("com.webos.service.tv.display/set3DOff");
    }

    public void getSoftwareInfoReq(){
        this.publisher.request("com.webos.service.update/getCurrentSWInformation");
    }

    public void playReq(){
        this.publisher.request("media.controls/play");
    }

    public void stopReq(){
        this.publisher.request("media.controls/stop");
    }

    public void pauseReq(){
        this.publisher.request("media.controls/pause");
    }

    public void rewindReq(){
        this.publisher.request("media.controls/rewind");
    }

    public void fastForwardReq(){
        this.publisher.request("media.controls/fastForward");
    }

    public void closeReq(){
        this.publisher.request("media.viewer/close");
    }

    public void powerOffReq(){
        this.publisher.request("system/turnOff");
    }

    public void showMessageReq(){
        this.publisher.request("system.notifications/createToast");
    }

    public void closeMessageReq(){
        this.publisher.request("system.notifications/closeToast");
    }

    public void createAlertReq(){
        this.publisher.request("system.notifications/createAlert");
    }

    public void closeAlertReq(){
        this.publisher.request("system.notifications/closeAlert");
    }

    public void closeLauncherReq(){
        this.publisher.request("system.launcher/close");
    }

    public void getAppStateReq(){
        this.publisher.request("system.launcher/getAppState");
    }

    public void getSystemInfoReq(){
        this.publisher.request("system/getSystemInfo");
    }

    public void launchReq(String app){
        this.publisher.request("system.launcher/launch", app);
    }

    public void openReq(){
        this.publisher.request("system.launcher/open");
    }

    public void getSystemSettingsReq(){
        this.publisher.request("settings/getSystemSettings");
    }

    public void channelDownReq(){
        this.publisher.request("tv/channelDown");
    }

    public void channelUpReq(){
        this.publisher.request("tv/channelUp");
    }

    public void getTVChannelsReq(){
        this.publisher.request("tv/getChannelList");
    }

    public void getChannelInfoReq(){
        this.publisher.request("tv/getChannelProgramInfo");
    }

    public void getCurrentChannelReq(){
        this.publisher.request("tv/getCurrentChannel");
    }

    public void getInputsReq(){
        this.publisher.request("tv/getExternalInputList");
    }

    public void setChannelReq(){
        this.publisher.request("tv/openChannel");
    }

    public void setInputReq(){
        this.publisher.request("tv/switchInput");
    }

    public void closeWebAppReq(){
        this.publisher.request("webapp/closeWebApp");
    }

    public void inputSocketReq(){
        this.publisher.request("com.webos.service.networkinput/getPointerInputSocket");
    }

    public void calibrationReq(){
        this.publisher.request("externalpq/setExternalPqData");
    }

    public void getCalibrationReq(){
        this.publisher.request("externalpq/getExternalPqData");
    }

    public void getSoundOutputReq(){
        this.publisher.request("com.webos.service.apiadapter/audio/getSoundOutput");
    }

    public void setSoundOutputReq(){
        this.publisher.request("com.webos.service.apiadapter/audio/changeSoundOutput");
    }

    public void getPowerStateReq(){
        this.publisher.request("com.webos.service.tvpower/power/getPowerState");
    }

    public void turnOffScreenReq(){
        this.publisher.request("com.webos.service.tvpower/power/turnOffScreen");
    }

    public void turnOnScreenReq(){
        this.publisher.request("com.webos.service.tvpower/power/ccc");
    }

    public void getConfigsReq(){
        this.publisher.request("config/getConfigs");
    }

    public void lunaSetConfigsReq(){
        this.publisher.request("com.webos.service.config/setConfigs");
    }

    public void lunaSetSystemSettingsReq(){
        this.publisher.request("com.webos.settingsservice/setSystemSettings");
    }

    public void lunaTurnOnScreenSaverReq(){ this.publisher.request("com.webos.service.tvpower/power/turnOnScreenSaver"); }


    public void lunaShowInputPickerReq(){
        this.publisher.request("com.webos.surfacemanager/showInputPicker");
    }



}

