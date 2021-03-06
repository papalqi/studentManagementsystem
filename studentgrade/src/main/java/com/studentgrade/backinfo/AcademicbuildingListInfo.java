/**
 * <p>Title: AcademicbuildingListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月16日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.util.ArrayList;
import java.util.List;

import com.studentgrade.bean.Academicbuilding;

/**
 * <p>Title: AcademicbuildingListInfo<／p>
 * <p>Description: 教学楼ResponseBody <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月16日
 */
public class AcademicbuildingListInfo {
	
	private int status; //状态码
	private List<Academicbuilding> info = new ArrayList<Academicbuilding>();//教学楼信息list

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Academicbuilding> getInfo() {
		return info;
	}
	public void setInfo(List<Academicbuilding> info) {
		this.info = info;
	}
	
	
}
