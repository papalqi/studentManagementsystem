/**
 * <p>Title: TeacherCourseTable.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: TeacherCourseTable<／p>
 * <p>Description: 教师课表详情<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class TeacherCourseTable {
	
	private String scourseid;
	private String scoursename;
	private String smajorname;
	private String scollegename;
	private BigDecimal iteacheid;
	private String sname;
	private BigDecimal iteachclassid;
	private BigDecimal istudentnumber;
	private BigDecimal istudentmaxnumber;
	private BigDecimal icredit;
	private BigDecimal ihours;
	
	private String sclassroomname;
	private String nacademicbuildingname;
	private String scampusname;
	private BigDecimal iweeknumber;
	private BigDecimal iweek;
	private BigDecimal isection;
	
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
	public BigDecimal getIcredit() {
		return icredit;
	}
	public void setIcredit(BigDecimal icredit) {
		this.icredit = icredit;
	}
	public BigDecimal getIhours() {
		return ihours;
	}
	public void setIhours(BigDecimal ihours) {
		this.ihours = ihours;
	}
	public String getSclassroomname() {
		return sclassroomname;
	}
	public void setSclassroomname(String sclassroomname) {
		this.sclassroomname = sclassroomname;
	}
	public String getNacademicbuildingname() {
		return nacademicbuildingname;
	}
	public void setNacademicbuildingname(String nacademicbuildingname) {
		this.nacademicbuildingname = nacademicbuildingname;
	}
	public String getScampusname() {
		return scampusname;
	}
	public void setScampusname(String scampusname) {
		this.scampusname = scampusname;
	}
	public BigDecimal getIweeknumber() {
		return iweeknumber;
	}
	public void setIweeknumber(BigDecimal iweeknumber) {
		this.iweeknumber = iweeknumber;
	}
	public BigDecimal getIweek() {
		return iweek;
	}
	public void setIweek(BigDecimal iweek) {
		this.iweek = iweek;
	}
	public BigDecimal getIsection() {
		return isection;
	}
	public void setIsection(BigDecimal isection) {
		this.isection = isection;
	}
}
