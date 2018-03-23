package com.studentgrade.bean;

import java.math.BigDecimal;

public class Vadclasspaiming {
    private BigDecimal iclassid;

    private String sclassname;

    private BigDecimal ichengjib;

    private String smajorname;

    private String scollegename;

    public BigDecimal getIclassid() {
        return iclassid;
    }

    public void setIclassid(BigDecimal iclassid) {
        this.iclassid = iclassid;
    }

    public String getSclassname() {
        return sclassname;
    }

    public void setSclassname(String sclassname) {
        this.sclassname = sclassname == null ? null : sclassname.trim();
    }

    public BigDecimal getIchengjib() {
        return ichengjib;
    }

    public void setIchengjib(BigDecimal ichengjib) {
        this.ichengjib = ichengjib;
    }

    public String getSmajorname() {
        return smajorname;
    }

    public void setSmajorname(String smajorname) {
        this.smajorname = smajorname == null ? null : smajorname.trim();
    }

    public String getScollegename() {
        return scollegename;
    }

    public void setScollegename(String scollegename) {
        this.scollegename = scollegename == null ? null : scollegename.trim();
    }
}