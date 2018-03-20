package com.studentgrade.model;

import java.math.BigDecimal;

public class UserInfo {
    private BigDecimal userid;

    private BigDecimal password;

    private BigDecimal role;

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public BigDecimal getPassword() {
        return password;
    }

    public void setPassword(BigDecimal password) {
        this.password = password;
    }

    public BigDecimal getRole() {
        return role;
    }

    public void setRole(BigDecimal role) {
        this.role = role;
    }
}