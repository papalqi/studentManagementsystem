package com.studentgrade.bean;

import java.math.BigDecimal;

public class Academicbuilding {
    private BigDecimal iacademicbuildingid;

    private BigDecimal icampusid;

    private String nacademicbuildingname;

    private BigDecimal roomnumber;

    public BigDecimal getIacademicbuildingid() {
        return iacademicbuildingid;
    }

    public void setIacademicbuildingid(BigDecimal iacademicbuildingid) {
        this.iacademicbuildingid = iacademicbuildingid;
    }

    public BigDecimal getIcampusid() {
        return icampusid;
    }

    public void setIcampusid(BigDecimal icampusid) {
        this.icampusid = icampusid;
    }

    public String getNacademicbuildingname() {
        return nacademicbuildingname;
    }

    public void setNacademicbuildingname(String nacademicbuildingname) {
        this.nacademicbuildingname = nacademicbuildingname == null ? null : nacademicbuildingname.trim();
    }

    public BigDecimal getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(BigDecimal roomnumber) {
        this.roomnumber = roomnumber;
    }
}