/**
 * <p>Title: InsertLeaveMessage.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月18日
 * @version 1.0
 */
package com.studentgrade.ininfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: InsertLeaveMessage<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月18日
 */
public class InsertLeaveMessage {
	
    private BigDecimal iisd;

    private BigDecimal teachclassID;

    private String message;

    private Date udate;

	public BigDecimal getIisd() {
		return iisd;
	}

	public void setIisd(BigDecimal iisd) {
		this.iisd = iisd;
	}

	public BigDecimal getTeachclassID() {
		return teachclassID;
	}

	public void setTeachclassID(BigDecimal teachclassID) {
		this.teachclassID = teachclassID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}
    
    
}
