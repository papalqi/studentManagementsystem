/**
 * <p>Title: CampusListinfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

import com.studentgrade.bean.Campus;

/**
 * <p>Title: CampusListinfo<／p>
 * <p>Description:  校区responsebody<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class CampusListinfo {
	private int status; //状态码
	private List<Campus> info = new ArrayList<Campus>(); //校区信息list
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Campus> getInfo() {
		return info;
	}
	public void setInfo(List<Campus> info) {
		this.info = info;
	}
}
