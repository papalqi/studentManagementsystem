/**
 * <p>Title: Status.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月9日
 * @version 1.0
 */
package com.studentgrade.bean;

/**
 * <p>Title: Status<／p>
 * <p>Description: 状态码<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月9日
 */
public class Status {
	
	private int status; //状态码
	private String info;//提示信息
	private String cookie;//

	/**
	 * <p>Title: getStatus<／p>
	 * <p>Description:  获取status<／p>
	 * 
	 */
	public int getStatus() {
		return status;
		
	}
	
	/**
	 * <p>Title: setStatus<／p>
	 * <p>Description: 设置status <／p>
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	
}
