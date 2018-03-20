/**
 * <p>Title: CourseListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月15日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

import com.studentgrade.model.CourseAListInfoItem;

/**
 * <p>Title: CourseListInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月15日
 */
public class CourseListInfo {
	private int status;
	private List<CourseAListInfoItem> info = new ArrayList<CourseAListInfoItem>();
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<CourseAListInfoItem> getInfo() {
		return info;
	}
	public void setInfo(List<CourseAListInfoItem> info) {
		this.info = info;
	}
	
}
