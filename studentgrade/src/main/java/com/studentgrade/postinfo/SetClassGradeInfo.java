/**
 * <p>Title: SetClassGradeInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月17日
 * @version 1.0
 */
package com.studentgrade.postinfo;

import java.util.List;

/**
 * <p>Title: SetClassGradeInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月17日
 */
public class SetClassGradeInfo {
	private String cookie;
	private List<SetStudentGrade> info;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public List<SetStudentGrade> getInfo() {
		return info;
	}

	public void setInfo(List<SetStudentGrade> info) {
		this.info = info;
	}
	
	
}
