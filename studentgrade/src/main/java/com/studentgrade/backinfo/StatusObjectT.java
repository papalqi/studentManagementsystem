/**
 * <p>Title: StatusObjectT.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月18日
 * @version 1.0
 */
package com.studentgrade.backinfo;

/**
 * <p>Title: StatusObjectT<／p>
 * <p>Description: 返回<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月18日
 */
public class StatusObjectT<T> {
	private int status;
	private T info;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
	
	
}
