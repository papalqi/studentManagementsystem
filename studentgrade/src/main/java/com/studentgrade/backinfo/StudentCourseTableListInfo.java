/**
 * <p>Title: StudentCourseTableListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月17日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.List;

import com.studentgrade.model.StudentCourseTableItem;

/**
 * <p>Title: StudentCourseTableListInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月17日
 */
public class StudentCourseTableListInfo {
	
	private int status;
	private List<StudentCourseTableItem> info;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<StudentCourseTableItem> getInfo() {
		return info;
	}
	public void setInfo(List<StudentCourseTableItem> info) {
		this.info = info;
	}
	
	
}
