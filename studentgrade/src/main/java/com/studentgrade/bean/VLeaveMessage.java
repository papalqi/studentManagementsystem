package com.studentgrade.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VLeaveMessage {
    private BigDecimal mid;

    private BigDecimal iid;

    private BigDecimal iteachclassid;

    private String scomment;

    private Date tcommenttime;

    private String sname;

    private String spageaddress;

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public BigDecimal getIid() {
        return iid;
    }

    public void setIid(BigDecimal iid) {
        this.iid = iid;
    }

    public BigDecimal getIteachclassid() {
        return iteachclassid;
    }

    public void setIteachclassid(BigDecimal iteachclassid) {
        this.iteachclassid = iteachclassid;
    }

    public String getScomment() {
        return scomment;
    }

    public void setScomment(String scomment) {
        this.scomment = scomment == null ? null : scomment.trim();
    }

    public Date getTcommenttime() {
        return tcommenttime;
    }

    public void setTcommenttime(Date tcommenttime) {
        this.tcommenttime = tcommenttime;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSpageaddress() {
        return spageaddress;
    }

    public void setSpageaddress(String spageaddress) {
        this.spageaddress = spageaddress == null ? null : spageaddress.trim();
    }
}