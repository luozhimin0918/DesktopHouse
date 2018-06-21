package com.example.yinlian.desktophousekeeper.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-21.10:22
 */
public class TariffInfoListJson implements Serializable {
   private String tariffDesc;
    private String tariffTag;
    private String probation;
    private String isDefaulted;
    private String originalPrice;
    private String presentPrice;
    private String discount;
    private String  serviceTerm;

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

    public String getProbation() {
        return probation;
    }

    public void setProbation(String probation) {
        this.probation = probation;
    }

    public String getIsDefaulted() {
        return isDefaulted;
    }

    public void setIsDefaulted(String isDefaulted) {
        this.isDefaulted = isDefaulted;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(String presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getServiceTerm() {
        return serviceTerm;
    }

    public void setServiceTerm(String serviceTerm) {
        this.serviceTerm = serviceTerm;
    }
}
