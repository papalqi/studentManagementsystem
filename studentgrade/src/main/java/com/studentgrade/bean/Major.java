/**
 * <p>Title: Major.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.bean;

import java.math.BigDecimal;

/**
 * <p>Title: Major<／p>
 * <p>Description:专业bean <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public class Major {
	
	private BigDecimal imajorid; //专业id

    private String smajorname; //专业名称

    private BigDecimal icollegeid; //学院id

    private BigDecimal iclassnumber; //班级数量

    private BigDecimal iteachernumber; //老师数量

    private BigDecimal istudentnumber; // 学生数量

    public BigDecimal getImajorid() {
        return imajorid;
    }

    public void setImajorid(BigDecimal imajorid) {
        this.imajorid = imajorid;
    }

    public String getSmajorname() {
        return smajorname;
    }

    public void setSmajorname(String smajorname) {
        this.smajorname = smajorname == null ? null : smajorname.trim();
    }

    public BigDecimal getIcollegeid() {
        return icollegeid;
    }

    public void setIcollegeid(BigDecimal icollegeid) {
        this.icollegeid = icollegeid;
    }

    public BigDecimal getIclassnumber() {
        return iclassnumber;
    }

    public void setIclassnumber(BigDecimal iclassnumber) {
        this.iclassnumber = iclassnumber;
    }

    public BigDecimal getIteachernumber() {
        return iteachernumber;
    }

    public void setIteachernumber(BigDecimal iteachernumber) {
        this.iteachernumber = iteachernumber;
    }

    public BigDecimal getIstudentnumber() {
        return istudentnumber;
    }

    public void setIstudentnumber(BigDecimal istudentnumber) {
        this.istudentnumber = istudentnumber;
    }
}
