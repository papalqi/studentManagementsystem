/**
 * <p>Title: CollegeListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CollegeListInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public class CollegeListInfo {
	private int status;
	private List<CollegeName> info = new ArrayList<CollegeName>();
	
	
	
	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public List<CollegeName> getInfo() {
		return info;
	}



	public void setInfo(List<CollegeName> info) {
		this.info = info;
	}



	public static class CollegeName{
		private String collegename;
		private BigDecimal collegeid;
		public CollegeName(){}
		public CollegeName(String name, BigDecimal id){
			collegename = name;
			collegeid = id;
		}
		public String getCollegename() {
			return collegename;
		}
		public void setCollegename(String collegename) {
			this.collegename = collegename;
		}
		public BigDecimal getCollegeid() {
			return collegeid;
		}
		public void setCollegeid(BigDecimal collegeid) {
			this.collegeid = collegeid;
		}
		
		
	}
}
