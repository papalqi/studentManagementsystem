package com.studentgrade.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Yhfk {
    private BigDecimal yid;

    private BigDecimal iid;

    private Date times;

    private String message;

    private String stitle;

    public BigDecimal getYid() {
        return yid;
    }

    public void setYid(BigDecimal yid) {
        this.yid = yid;
    }

    public BigDecimal getIid() {
        return iid;
    }

    public void setIid(BigDecimal iid) {
        this.iid = iid;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle == null ? null : stitle.trim();
    }
}