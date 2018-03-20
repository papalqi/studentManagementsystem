/**
 * <p>Title: StudentDetailInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月14日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.math.BigDecimal;

/**
 * <p>Title: StudentDetailInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月14日
 */
public class StudentDetailInfo {
	
	private int status;
	private BigDecimal stuid;
	private String stuname;
	private String stuclass;
	private String stucollege;
	private String stumajor;
	private String stuphoto;
	private Long stuphoneno;
	private String stumail;
	private String sgender;
	private BigDecimal sage;
	private String saddress;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getStuid() {
		return stuid;
	}
	public void setStuid(BigDecimal stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	public String getStucollege() {
		return stucollege;
	}
	public void setStucollege(String stucollege) {
		this.stucollege = stucollege;
	}
	public String getStumajor() {
		return stumajor;
	}
	public void setStumajor(String stumajor) {
		this.stumajor = stumajor;
	}
	public String getStuphoto() {
		return stuphoto;
	}
	public void setStuphoto(String stuphoto) {
		this.stuphoto = stuphoto;
	}
	public Long getStuphoneno() {
		return stuphoneno;
	}
	public void setStuphoneno(Long stuphoneno) {
		this.stuphoneno = stuphoneno;
	}
	public String getStumail() {
		return stumail;
	}
	public void setStumail(String stumail) {
		this.stumail = stumail;
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		this.sgender = sgender;
	}
	public BigDecimal getSage() {
		return sage;
	}
	public void setSage(BigDecimal sage) {
		this.sage = sage;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	
	
}
