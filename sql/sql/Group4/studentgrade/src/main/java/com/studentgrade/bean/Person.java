/**
 * <p>Title: Person.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月10日
 * @version 1.0
 */
package com.studentgrade.bean;

import java.math.BigDecimal;

/**
 * <p>Title: Person<／p>
 * <p>Description: pseron的bean<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月10日
 */
public class Person {
	
    private BigDecimal iid;

    private String sname;

    private String cgender;

    private BigDecimal iage;

    private Long ncellphone;

    private String splaceoforigin1;

    private String splaceaddress;

    private String semail;

    private String spassword;

    private String spageaddress;

    private String splaceoforigin2;

    private String splaceoforigin3;

    public BigDecimal getIid() {
        return iid;
    }

    public void setIid(BigDecimal iid) {
        this.iid = iid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getCgender() {
        return cgender;
    }

    public void setCgender(String cgender) {
        this.cgender = cgender == null ? null : cgender.trim();
    }

    public BigDecimal getIage() {
        return iage;
    }

    public void setIage(BigDecimal iage) {
        this.iage = iage;
    }

    public Long getNcellphone() {
        return ncellphone;
    }

    public void setNcellphone(Long ncellphone) {
        this.ncellphone = ncellphone;
    }

    public String getSplaceoforigin1() {
        return splaceoforigin1;
    }

    public void setSplaceoforigin1(String splaceoforigin1) {
        this.splaceoforigin1 = splaceoforigin1 == null ? null : splaceoforigin1.trim();
    }

    public String getSplaceaddress() {
        return splaceaddress;
    }

    public void setSplaceaddress(String splaceaddress) {
        this.splaceaddress = splaceaddress == null ? null : splaceaddress.trim();
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail == null ? null : semail.trim();
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword == null ? null : spassword.trim();
    }

    public String getSpageaddress() {
        return spageaddress;
    }

    public void setSpageaddress(String spageaddress) {
        this.spageaddress = spageaddress == null ? null : spageaddress.trim();
    }

    public String getSplaceoforigin2() {
        return splaceoforigin2;
    }

    public void setSplaceoforigin2(String splaceoforigin2) {
        this.splaceoforigin2 = splaceoforigin2 == null ? null : splaceoforigin2.trim();
    }

    public String getSplaceoforigin3() {
        return splaceoforigin3;
    }

    public void setSplaceoforigin3(String splaceoforigin3) {
        this.splaceoforigin3 = splaceoforigin3 == null ? null : splaceoforigin3.trim();
    }
}
