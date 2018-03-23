/**
 * <p>Title: OpenCourseTimeItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: OpenCourseTimeItem<／p>
 * <p>Description:开课时间 <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class OpenCourseTimeItem {
	private BigDecimal classroomid;//教室id
	private BigDecimal weeknumber;//第几周
	private BigDecimal week;//星期几
	private BigDecimal ssection;//第几节
	
	private BigDecimal teachclassid;//教学班id
	public BigDecimal getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(BigDecimal classroomid) {
		this.classroomid = classroomid;
	}
	public BigDecimal getTeachclassid() {
		return teachclassid;
	}
	public void setTeachclassid(BigDecimal teachclassid) {
		this.teachclassid = teachclassid;
	}
	public BigDecimal getWeeknumber() {
		return weeknumber;
	}
	public void setWeeknumber(BigDecimal weeknumber) {
		this.weeknumber = weeknumber;
	}
	public BigDecimal getWeek() {
		return week;
	}
	public void setWeek(BigDecimal week) {
		this.week = week;
	}
	public BigDecimal getSsection() {
		return ssection;
	}
	public void setSsection(BigDecimal ssection) {
		this.ssection = ssection;
	}
}
