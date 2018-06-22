package com.example.yinlian.desktophousekeeper.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-22.10:34
 */
public class OrderInfoRespJsonDatail implements Serializable {


    /**
     * endTime : 2057-07-07 10:23:23
     * lastPaymentPrice : 0
     * lastPaymentTerm : 45
     * orderStatus : 3
     * remainingDays : 14260
     * startTime : 2018-06-06 20:13:28
     * tariffDesc : 默认套餐内容
     * totalPrice : 0
     */

    private String endTime;
    private int lastPaymentPrice;
    private int lastPaymentTerm;
    private String orderStatus;
    private int remainingDays;
    private String startTime;
    private String tariffDesc;
    private int totalPrice;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}