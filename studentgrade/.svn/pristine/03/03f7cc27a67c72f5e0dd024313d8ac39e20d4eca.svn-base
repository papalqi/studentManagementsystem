/**
 * <p>Title: ADClassListInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月13日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ADClassListInfo<／p>
 * <p>Description: 教学班信息responsebody <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月13日
 */
public class ADClassListInfo {
	private int status; //状态码
	private List<ADClassName> info = new ArrayList<ADClassName>(); //教学班名字list
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ADClassName> getInfo() {
		return info;
	}

	public void setInfo(List<ADClassName> info) {
		this.info = info;
	}

	public static class ADClassName{
		private String ADClassname;
		private BigDecimal ADClassid;
		public String getADClassname() {
			return ADClassname;
		}
		public void setADClassname(String aDClassname) {
			ADClassname = aDClassname;
		}
		public BigDecimal getADClassid() {
			return ADClassid;
		}
		public void setADClassid(BigDecimal aDClassid) {
			ADClassid = aDClassid;
		}
		
		
	}
}
