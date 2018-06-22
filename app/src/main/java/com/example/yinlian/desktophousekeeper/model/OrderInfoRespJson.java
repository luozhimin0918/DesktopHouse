package com.example.yinlian.desktophousekeeper.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-22.10:34
 */
public class OrderInfoRespJson implements Serializable {


    /**
     * appInfo : {"appId":"afd2baf088034179b4c98826b4d9fcca","appName":"靓丽前台-银商版","appPackName":"com.shboka.beautyorderums","appVersionCode":"3.0.6.1"}
     * deviceInfo : {"deviceSn":"0820043480","firmCode":"109","prodCode":"19"}
     * interType : BMP-QUERY
     * mac : ums2018
     * respDetail : [{"endTime":"2057-07-07 10:23:23","lastPaymentPrice":0,"lastPaymentTerm":45,"orderStatus":"3","remainingDays":14260,"startTime":"2018-06-06 20:13:28","tariffDesc":"默认套餐内容","totalPrice":0}]
     * version : 001
     */

    private String appInfo;
    private String deviceInfo;
    private String interType;
    private String mac;
    private String respDetail;
    private String version;

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getInterType() {
        return interType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRespDetail() {
        return respDetail;
    }

    public void setRespDetail(String respDetail) {
        this.respDetail = respDetail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
