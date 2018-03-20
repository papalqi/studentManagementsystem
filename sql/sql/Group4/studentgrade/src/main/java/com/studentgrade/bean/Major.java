/**
 * <p>Title: Major.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��12��
 * @version 1.0
 */
package com.studentgrade.bean;

import java.math.BigDecimal;

/**
 * <p>Title: Major<��p>
 * <p>Description:רҵbean <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��12��
 */
public class Major {
	
	private BigDecimal imajorid; //רҵid

    private String smajorname; //רҵ����

    private BigDecimal icollegeid; //ѧԺid

    private BigDecimal iclassnumber; //�༶����

    private BigDecimal iteachernumber; //��ʦ����

    private BigDecimal istudentnumber; // ѧ������

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
