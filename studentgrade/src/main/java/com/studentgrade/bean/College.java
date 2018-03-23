/**
 * <p>Title: College.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.bean;

import java.math.BigDecimal;

/**
 * <p>Title: College<／p>
 * <p>Description: 学院bean<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public class College {

	private BigDecimal icollegeid;

    private String scollegename;

    private BigDecimal icampusid;

    private String scollegeadssress;

    private BigDecimal istudentnumber;

    private BigDecimal iteachernumber;

    public BigDecimal getIcollegeid() {
        return icollegeid;
    }

    public void setIcollegeid(BigDecimal icollegeid) {
        this.icollegeid = icollegeid;
    }

    public String getScollegename() {
        return scollegename;
    }

    public void setScollegename(String scollegename) {
        this.scollegename = scollegename == null ? null : scollegename.trim();
    }

    public BigDecimal getIcampusid() {
        return icampusid;
    }

    public void setIcampusid(BigDecimal icampusid) {
        this.icampusid = icampusid;
    }

    public String getScollegeadssress() {
        return scollegeadssress;
    }

    public void setScollegeadssress(String scollegeadssress) {
        this.scollegeadssress = scollegeadssress == null ? null : scollegeadssress.trim();
    }

    public BigDecimal getIstudentnumber() {
        return istudentnumber;
    }

    public void setIstudentnumber(BigDecimal istudentnumber) {
        this.istudentnumber = istudentnumber;
    }

    public BigDecimal getIteachernumber() {
        return iteachernumber;
    }

    public void setIteachernumber(BigDecimal iteachernumber) {
        this.iteachernumber = iteachernumber;
    }
}
