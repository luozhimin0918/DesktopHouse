package com.example.yinlian.desktophousekeeper.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-22.10:06
 */
public class DataRespRecordPay implements Serializable {

    /**
     * ageId : 00010253
     * appId : da8e15f8a114433fb46c85c460389672
     * apvId : d9fde26bb0284f5d83da9a14a435c3a0
     * artId : null
     * creator : null
     * endTime : 2023-02-21 10:02:44
     * gmtCreate : null
     * gmtModified : null
     * isDeleted : null
     * lastPaymentPrice : 0
     * lastPaymentTerm : 45
     * lastPaymentTime : 2018-06-22 10:02:44
     * orderId : null
     * orderStatus : 3
     * posId : 00c8fbcc09e44bf28fc0d5cedca35b6a
     * probationEndTime : null
     * probationStartTime : null
     * remainingDays : 1705
     * startTime : 2018-06-06 20:13:28
     * tariffDesc : 默认套餐内容
     * tariffTag : 套餐一
     * totalPrice : 21100
     * totalTerm : 110
     * updater : null
     */

    private String ageId;
    private String appId;
    private String apvId;
    private Object artId;
    private Object creator;
    private String endTime;
    private Object gmtCreate;
    private Object gmtModified;
    private Object isDeleted;
    private int lastPaymentPrice;
    private int lastPaymentTerm;
    private String lastPaymentTime;
    private Object orderId;
    private String orderStatus;
    private String posId;
    private Object probationEndTime;
    private Object probationStartTime;
    private int remainingDays;
    private String startTime;
    private String tariffDesc;
    private String tariffTag;
    private int totalPrice;
    private int totalTerm;
    private Object updater;

    public String getAgeId() {
        return ageId;
    }

    public void setAgeId(String ageId) {
        this.ageId = ageId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApvId() {
        return apvId;
    }

    public void setApvId(String apvId) {
        this.apvId = apvId;
    }

    public Object getArtId() {
        return artId;
    }

    public void setArtId(Object artId) {
        this.artId = artId;
    }

    public Object getCreator() {
        return creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Object getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Object gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Object getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getLastPaymentPrice() {
        return lastPaymentPrice;
    }

    public void setLastPaymentPrice(int lastPaymentPrice) {
        this.lastPaymentPrice = lastPaymentPrice;
    }

    public int getLastPaymentTerm() {
        return lastPaymentTerm;
    }

    public void setLastPaymentTerm(int lastPaymentTerm) {
        this.lastPaymentTerm = lastPaymentTerm;
    }

    public String getLastPaymentTime() {
        return lastPaymentTime;
    }

    public void setLastPaymentTime(String lastPaymentTime) {
        this.lastPaymentTime = lastPaymentTime;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public Object getProbationEndTime() {
        return probationEndTime;
    }

    public void setProbationEndTime(Object probationEndTime) {
        this.probationEndTime = probationEndTime;
    }

    public Object getProbationStartTime() {
        return probationStartTime;
    }

    public void setProbationStartTime(Object probationStartTime) {
        this.probationStartTime = probationStartTime;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTariffDesc() {
        return tariffDesc;
    }

    public void setTariffDesc(String tariffDesc) {
        this.tariffDesc = tariffDesc;
    }

    public String getTariffTag() {
        return tariffTag;
    }

    public void setTariffTag(String tariffTag) {
        this.tariffTag = tariffTag;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalTerm() {
        return totalTerm;
    }

    public void setTotalTerm(int totalTerm) {
        this.totalTerm = totalTerm;
    }

    public Object getUpdater() {
        return updater;
    }

    public void setUpdater(Object updater) {
        this.updater = updater;
    }
}
