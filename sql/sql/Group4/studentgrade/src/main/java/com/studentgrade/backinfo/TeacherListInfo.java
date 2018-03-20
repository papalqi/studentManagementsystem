/**
 * <p>Title: TeacherListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月15日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

import com.studentgrade.model.TeacherListInfoItem;

/**
 * <p>Title: TeacherListInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月15日
 */
public class TeacherListInfo {
	private int status;
	private List<TeacherListInfoItem> info = new ArrayList<TeacherListInfoItem>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<TeacherListInfoItem> getInfo() {
		return info;
	}
	public void setInfo(List<TeacherListInfoItem> info) {
		this.info = info;
	}
	
	
}
