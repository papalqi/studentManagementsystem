package com.studentgrade.bean;

import java.math.BigDecimal;

public class Campus {
    private BigDecimal icampusid;

    private String scampusname;

    public BigDecimal getIcampusid() {
        return icampusid;
    }

    public void setIcampusid(BigDecimal icampusid) {
        this.icampusid = icampusid;
    }

    public String getScampusname() {
        return scampusname;
    }

    public void setScampusname(String scampusname) {
        this.scampusname = scampusname == null ? null : scampusname.trim();
    }
}