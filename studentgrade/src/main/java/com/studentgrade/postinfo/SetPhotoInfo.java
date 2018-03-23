/**
 * <p>Title: SetPhotoInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月20日
 * @version 1.0
 */
package com.studentgrade.postinfo;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title: SetPhotoInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月20日
 */
public class SetPhotoInfo {
	private String cookie;
	private String username;
	private MultipartFile file;
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
