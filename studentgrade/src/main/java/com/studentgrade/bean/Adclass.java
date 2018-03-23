package com.studentgrade.bean;

import java.math.BigDecimal;

public class Adclass {
    private BigDecimal iclassid;

    private BigDecimal inumber;

    private String sclassname;

    private BigDecimal imajorid;

    private BigDecimal ichengjib;

    public BigDecimal getIclassid() {
        return iclassid;
    }

    public void setIclassid(BigDecimal iclassid) {
        this.iclassid = iclassid;
    }

    public BigDecimal getInumber() {
        return inumber;
    }

    public void setInumber(BigDecimal inumber) {
        this.inumber = inumber;
    }

    public String getSclassname() {
        return sclassname;
    }

    public void setSclassname(String sclassname) {
        this.sclassname = sclassname == null ? null : sclassname.trim();
    }

    public BigDecimal getImajorid() {
        return imajorid;
    }

    public void setImajorid(BigDecimal imajorid) {
        this.imajorid = imajorid;
    }

    public BigDecimal getIchengjib() {
        return ichengjib;
    }

    public void setIchengjib(BigDecimal ichengjib) {
        this.ichengjib = ichengjib;
    }
}