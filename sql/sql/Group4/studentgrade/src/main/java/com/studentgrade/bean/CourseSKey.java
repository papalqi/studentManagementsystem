package com.studentgrade.bean;

import java.math.BigDecimal;

public class CourseSKey {
    private BigDecimal iteachclassid;

    private BigDecimal istudentid;

    public BigDecimal getIteachclassid() {
        return iteachclassid;
    }

    public void setIteachclassid(BigDecimal iteachclassid) {
        this.iteachclassid = iteachclassid;
    }

    public BigDecimal getIstudentid() {
        return istudentid;
    }

    public void setIstudentid(BigDecimal istudentid) {
        this.istudentid = istudentid;
    }
}