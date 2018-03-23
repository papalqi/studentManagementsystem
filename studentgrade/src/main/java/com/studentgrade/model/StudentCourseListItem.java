/**
 * <p>Title: StudentCourseListItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月18日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: StudentCourseListItem<／p>
 * <p>Description: 学生所选课程列表item <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月18日
 */
public class StudentCourseListItem {
	
	private BigDecimal istudentid;
	private BigDecimal iteachclassid;
	private String scoursename;
	private String scourseid;
	private String sname; //教师名字
	private String smajorname;
	private String scollegename;
	
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	
}
