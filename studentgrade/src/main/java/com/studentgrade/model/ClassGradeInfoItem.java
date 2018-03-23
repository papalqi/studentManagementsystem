/**
 * <p>Title: ClassGradeInfoItem.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月17日
 * @version 1.0
 */
package com.studentgrade.model;

import java.math.BigDecimal;

/**
 * <p>Title: ClassGradeInfoItem<／p>
 * <p>Description:教学班(学生)成绩item <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月17日
 */
public class ClassGradeInfoItem {
	
	private BigDecimal istudentid;
	private String sname;
	private BigDecimal iteachclassid;
	private String scoursename;
	private String scourseid;
	private BigDecimal iscore1;
	private BigDecimal iscore2;
	private BigDecimal iallscore;
	private String smajorname;
	private String scollegename;
	private BigDecimal icredit;
	private BigDecimal iscore1h;
	private BigDecimal iscore2h;

	
    private String scouesestate;

    private BigDecimal bscorestate;

    


	public BigDecimal getIcredit() {
		return icredit;
	}

	public void setIcredit(BigDecimal icredit) {
		this.icredit = icredit;
	}

	public BigDecimal getIscore1h() {
		return iscore1h;
	}

	public void setIscore1h(BigDecimal iscore1h) {
		this.iscore1h = iscore1h;
	}

	public BigDecimal getIscore2h() {
		return iscore2h;
	}

	public void setIscore2h(BigDecimal iscore2h) {
		this.iscore2h = iscore2h;
	}

	public BigDecimal getIstudentid() {
		return istudentid;
	}

	public void setIstudentid(BigDecimal istudentid) {
		this.istudentid = istudentid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public BigDecimal getIteachclassid() {
		return iteachclassid;
	}

	public void setIteachclassid(BigDecimal iteachclassid) {
		this.iteachclassid = iteachclassid;
	}

	public String getScoursename() {
		return scoursename;
	}

	public void setScoursename(String scoursename) {
		this.scoursename = scoursename;
	}

	public String getScourseid() {
		return scourseid;
	}

	public void setScourseid(String scourseid) {
		this.scourseid = scourseid;
	}

	public BigDecimal getIscore1() {
		return iscore1;
	}

	public void setIscore1(BigDecimal iscore1) {
		this.iscore1 = iscore1;
	}

	public BigDecimal getIscore2() {
		return iscore2;
	}

	public void setIscore2(BigDecimal iscore2) {
		this.iscore2 = iscore2;
	}

	public BigDecimal getIallscore() {
		return iallscore;
	}

	public void setIallscore(BigDecimal iallscore) {
		this.iallscore = iallscore;
	}

	public String getScouesestate() {
		return scouesestate;
	}

	public void setScouesestate(String scouesestate) {
		this.scouesestate = scouesestate;
	}

	public BigDecimal getBscorestate() {
		return bscorestate;
	}

	public void setBscorestate(BigDecimal bscorestate) {
		this.bscorestate = bscorestate;
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

	
}
