/**
 * <p>Title: TeacherDetailInfo.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月15日
 * @version 1.0
 */
package com.studentgrade.backinfo;

import java.math.BigDecimal;

/**
 * <p>Title: TeacherDetailInfo<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月15日
 */
public class TeacherDetailInfo {
	
	private int status;
	private String info;
	
	private BigDecimal teaid;
	private String teaname;
	private String teatitle;
	private String teacollege;
	private String teaphoto;
	private Long teaphoneno;
	private String teamail;
	
	private String tgender;
	private BigDecimal tage;
	private String taddress;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public BigDecimal getTeaid() {
		return teaid;
	}
	public void setTeaid(BigDecimal teaid) {
		this.teaid = teaid;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	public String getTeatitle() {
		return teatitle;
	}
	public void setTeatitle(String teatitle) {
		this.teatitle = teatitle;
	}
	public String getTeacollege() {
		return teacollege;
	}
	public void setTeacollege(String teacollege) {
		this.teacollege = teacollege;
	}
	public String getTeaphoto() {
		return teaphoto;
	}
	public void setTeaphoto(String teaphoto) {
		this.teaphoto = teaphoto;
	}
	public Long getTeaphoneno() {
		return teaphoneno;
	}
	public void setTeaphoneno(Long teaphoneno) {
		this.teaphoneno = teaphoneno;
	}
	public String getTeamail() {
		return teamail;
	}
	public void setTeamail(String teamail) {
		this.teamail = teamail;
	}
	public String getTgender() {
		return tgender;
	}
	public void setTgender(String tgender) {
		this.tgender = tgender;
	}
	public BigDecimal getTage() {
		return tage;
	}
	public void setTage(BigDecimal tage) {
		this.tage = tage;
	}
	public String getTaddress() {
		return taddress;
	}
	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}
	
	
}
