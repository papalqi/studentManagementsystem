/**
 * <p>Title: TeacherListInfoItem.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��15��
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: TeacherListInfoItem<��p>
 * <p>Description: ��ʦ����б�item��Ϣ<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��15��
 */
public class TeacherListInfoItem {
	
    private String sname;
	private BigDecimal iteacheid;
    private String stitle; //רҵ����
	private String scollegename;
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public BigDecimal getIteacherid() {
		return iteacheid;
	}
	public void setIteacherid(BigDecimal iteacherid) {
		this.iteacheid = iteacherid;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getScollegename() {
		return scollegename;
	}
	public void setScollegename(String scollegename) {
		this.scollegename = scollegename;
	}
	
	
}