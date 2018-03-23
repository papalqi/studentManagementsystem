/**
 * <p>Title: TeacherOpenCourseInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.postinfo;

import java.math.BigDecimal;
import java.util.List;

import com.studentgrade.model.OpenCourseTimeItem;

/**
 * <p>Title: TeacherOpenCourseInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class TeacherOpenCourseInfo {
	
	private String cookie;
	private String curriculumID;
	private BigDecimal teacherID;//教师id
	private BigDecimal maxNumber;//最大学生数
	//private OpenCourseInfo courseInfo;//开课信息
	private List<OpenCourseTimeItem> timelist;//开课时间t
	
	
	public BigDecimal getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(BigDecimal teacherID) {
		this.teacherID = teacherID;
	}
	public BigDecimal getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(BigDecimal maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getCurriculumID() {
		return curriculumID;
	}
	public void setCurriculumID(String curriculumID) {
		this.curriculumID = curriculumID;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
//	public OpenCourseInfo getCourseInfo() {
//		return courseInfo;
//	}
//	public void setCourseInfo(OpenCourseInfo courseInfo) {
//		this.courseInfo = courseInfo;
//	}
	public List<OpenCourseTimeItem> getTimelist() {
		return timelist;
	}
	public void setTimelist(List<OpenCourseTimeItem> timelist) {
		this.timelist = timelist;
	}

}
