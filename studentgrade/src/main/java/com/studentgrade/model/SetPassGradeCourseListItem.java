/**
 * <p>Title: SetPassGradeCourseListItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月18日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: SetPassGradeCourseListItem<／p>
 * <p>Description:管理员成绩确认课程列表 <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月18日
 */
public class SetPassGradeCourseListItem {
	private String scourseid;
	private String scoursename;
	private String smajorname;
	private String scollegename;
	private BigDecimal iteacheid;
	private String sname;
	private BigDecimal iteachclassid;
	private BigDecimal istudentnumber;
	private BigDecimal istudentmaxnumber;
	private BigDecimal bscorestate;
	private String scouesestate;
	

	public BigDecimal getBscorestate() {
		return bscorestate;
	}
	public void setBscorestate(BigDecimal bscorestate) {
		this.bscorestate = bscorestate;
	}
	public String getScouesestate() {
		return scouesestate;
	}
	public void setScouesestate(String scouesestate) {
		this.scouesestate = scouesestate;
	}
	public String getScourseid() {
		return scourseid;
	}
	public void setScourseid(String scourseid) {
		this.scourseid = scourseid;
	}
	public String getScoursename() {
		return scoursename;
	}
	public void setScoursename(String scoursename) {
		this.scoursename = scoursename;
	}
	public String getSmajorname() {
		return smajorname;
	}
	public void setSmajorname(String smajorname) {
		this.smajorname = smajorname;
	}
	public String getScollegename() {
		return scollegename;
	}
	public void setScollegename(String scollegename) {
		this.scollegename = scollegename;
	}
	public BigDecimal getIteacheid() {
		return iteacheid;
	}
	public void setIteacheid(BigDecimal iteacheid) {
		this.iteacheid = iteacheid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public BigDecimal getIteachclassid() {
		return iteachclassid;
	}
	public void setIteachclassid(BigDecimal iteachclassid) {
		this.iteachclassid = iteachclassid;
	}
	public BigDecimal getIstudentnumber() {
		return istudentnumber;
	}
	public void setIstudentnumber(BigDecimal istudentnumber) {
		this.istudentnumber = istudentnumber;
	}
	public BigDecimal getIstudentmaxnumber() {
		return istudentmaxnumber;
	}
	public void setIstudentmaxnumber(BigDecimal istudentmaxnumber) {
		this.istudentmaxnumber = istudentmaxnumber;
	}

}
