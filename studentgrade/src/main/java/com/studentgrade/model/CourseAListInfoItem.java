/**
 * <p>Title: CourseAListInfoItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月15日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: CourseAListInfoItem<／p>
 * <p>Description: 管理员查看设置课程信息列表item<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月15日
 */
public class CourseAListInfoItem {
	
	private String scourseid;
	private String scoursename;
	private String smajorname;
	private String scollegename;
	private BigDecimal icredit;
	private BigDecimal iscournumber;
	private BigDecimal ihours;
    private BigDecimal inowscournumber;
	public String getScourseid() {
		return scourseid;
	}
	public void setScourseid(String scourseid) {
		this.scourseid = scourseid;
	}
	public String getScoursename() {
		return scoursename;
	}
	public void setScoursename(String scoursename) {
		this.scoursename = scoursename;
	}
	public String getSmajorname() {
		return smajorname;
	}
	public void setSmajorname(String smajorname) {
		this.smajorname = smajorname;
	}
	public String getScollegename() {
		return scollegename;
	}
	public void setScollegename(String scollegename) {
		this.scollegename = scollegename;
	}
	public BigDecimal getIcredit() {
		return icredit;
	}
	public void setIcredit(BigDecimal icredit) {
		this.icredit = icredit;
	}

	public BigDecimal getIscournumber() {
		return iscournumber;
	}
	public void setIscournumber(BigDecimal iscournumber) {
		this.iscournumber = iscournumber;
	}
	public BigDecimal getInowscournumber() {
		return inowscournumber;
	}
	public void setInowscournumber(BigDecimal inowscournumber) {
		this.inowscournumber = inowscournumber;
	}
	public BigDecimal getIhours() {
		return ihours;
	}
	public void setIhours(BigDecimal ihours) {
		this.ihours = ihours;
	}

    
    

}
