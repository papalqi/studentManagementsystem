package com.studentgrade.bean;

import java.math.BigDecimal;

public class CourseA {
    private String scourseid;

    private String scoursename;

    private BigDecimal icredit;

    private BigDecimal ihours;

    private BigDecimal imajorid;

    private BigDecimal iscournumber;

    private BigDecimal inowscournumber;

    public String getScourseid() {
        return scourseid;
    }

    public void setScourseid(String scourseid) {
        this.scourseid = scourseid == null ? null : scourseid.trim();
    }

    public String getScoursename() {
        return scoursename;
    }

    public void setScoursename(String scoursename) {
        this.scoursename = scoursename == null ? null : scoursename.trim();
    }

    public BigDecimal getIcredit() {
        return icredit;
    }

    public void setIcredit(BigDecimal icredit) {
        this.icredit = icredit;
    }

    public BigDecimal getIhours() {
        return ihours;
    }

    public void setIhours(BigDecimal ihours) {
        this.ihours = ihours;
    }

    public BigDecimal getImajorid() {
        return imajorid;
    }

    public void setImajorid(BigDecimal imajorid) {
        this.imajorid = imajorid;
    }

    public BigDecimal getIscournumber() {
        return iscournumber;
    }

    public void setIscournumber(BigDecimal iscournumber) {
        this.iscournumber = iscournumber;
    }

    public BigDecimal getInowscournumber() {
        return inowscournumber;
    }

    public void setInowscournumber(BigDecimal inowscournumber) {
        this.inowscournumber = inowscournumber;
    }
}