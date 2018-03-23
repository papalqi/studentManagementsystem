/**
 * <p>Title: InsertTeacherInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月13日
 * @version 1.0
 */
package com.studentgrade.ininfo;

import java.math.BigDecimal;

/**
 * <p>Title: InsertTeacherInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月13日
 */
public class InsertTeacherInfo {
	
	private String sName;
	private BigDecimal teacherID;
	private BigDecimal collegeid;
	private String tilte;
	
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public BigDecimal getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(BigDecimal teacherID) {
		this.teacherID = teacherID;
	}
	public BigDecimal getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(BigDecimal collegeid) {
		this.collegeid = collegeid;
	}
	
	
}
