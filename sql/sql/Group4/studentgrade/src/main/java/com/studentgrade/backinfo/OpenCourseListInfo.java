/**
 * <p>Title: OpenCourseListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.List;

import com.studentgrade.model.OpenCourseListInfoItem;

/**
 * <p>Title: OpenCourseListInfo<／p>
 * <p>Description: 查看某开设课程信息列表<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class OpenCourseListInfo {
	
	private int status;
	private List<OpenCourseListInfoItem> info;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<OpenCourseListInfoItem> getInfo() {
		return info;
	}
	public void setInfo(List<OpenCourseListInfoItem> info) {
		this.info = info;
	}
	
	
}
