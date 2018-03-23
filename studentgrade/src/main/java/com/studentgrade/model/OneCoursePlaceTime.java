/**
 * <p>Title: OneCoursePlaceTime.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月21日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: OneCoursePlaceTime<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月21日
 */
public class OneCoursePlaceTime {
	 private String sclassroomname;
	 private String nacademicbuildingname;
	 private String scampusname;
	 private BigDecimal iweek;
	 private BigDecimal isection;
	 
	public String getSclassroomname() {
		return sclassroomname;
	}
	public void setSclassroomname(String sclassroomname) {
		this.sclassroomname = sclassroomname;
	}
	public String getNacademicbuildingname() {
		return nacademicbuildingname;
	}
	public void setNacademicbuildingname(String nacademicbuildingname) {
		this.nacademicbuildingname = nacademicbuildingname;
	}
	public String getScampusname() {
		return scampusname;
	}
	public void setScampusname(String scampusname) {
		this.scampusname = scampusname;
	}

	public BigDecimal getIweek() {
		return iweek;
	}
	public void setIweek(BigDecimal iweek) {
		this.iweek = iweek;
	}
	public BigDecimal getIsection() {
		return isection;
	}
	public void setIsection(BigDecimal isection) {
		this.isection = isection;
	}
	 
	 
}
