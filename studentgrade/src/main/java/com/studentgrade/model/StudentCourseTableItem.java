/**
 * <p>Title: StudentCourseTableItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月17日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: StudentCourseTableItem<／p>
 * <p>Description: 学生课表item<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月17日
 */
public class StudentCourseTableItem {
	private BigDecimal istudentid;//学生id
	private BigDecimal iteachclassid;//教学班id
	private String scoursename;
	private String scourseid;
	private BigDecimal iweeknumber;
	private BigDecimal iweek;
	private BigDecimal isection;
	private String sname;//教师名字
	private BigDecimal iclassroomid;
	private String sclassroomname;
	private String nacademicbuildingname;
	private String scampusname;
	
	
	public String getScampusname() {
		return scampusname;
	}
	public void setScampusname(String scampusname) {
		this.scampusname = scampusname;
	}
	public BigDecimal getIstudentid() {
		return istudentid;
	}
	public void setIstudentid(BigDecimal istudentid) {
		this.istudentid = istudentid;
	}
	public BigDecimal getIteachclassid() {
		return iteachclassid;
	}
	public void setIteachclassid(BigDecimal iteachclassid) {
		this.iteachclassid = iteachclassid;
	}
	public String getScoursename() {
		return scoursename;
	}
	public void setScoursename(String scoursename) {
		this.scoursename = scoursename;
	}
	public String getScourseid() {
		return scourseid;
	}
	public void setScourseid(String scourseid) {
		this.scourseid = scourseid;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public BigDecimal getIclassroomid() {
		return iclassroomid;
	}
	public void setIclassroomid(BigDecimal iclassroomid) {
		this.iclassroomid = iclassroomid;
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
	
}
