/**
 * <p>Title: ClassroomListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

import com.studentgrade.bean.Classroom;

/**
 * <p>Title: ClassroomListInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class ClassroomListInfo {

	private int status;
	private List<Classroom> info = new ArrayList<Classroom>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Classroom> getInfo() {
		return info;
	}
	public void setInfo(List<Classroom> info) {
		this.info = info;
	}
	
	
}
