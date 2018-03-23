/**
 * <p>Title: StatusListT.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月18日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: StatusListT<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月18日
 */
public class StatusListT<T> {
	
	private int status;
	private List<T> info = new ArrayList<T>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<T> getInfo() {
		return info;
	}
	public void setInfo(List<T> info) {
		this.info = info;
	}
	
}
