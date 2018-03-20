/**
 * <p>Title: Administrators.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月10日
 * @version 1.0
 */
package com.studentgrade.bean;

import java.math.BigDecimal;

/**
 * <p>Title: Administrators<／p>
 * <p>Description: 管理员Bean <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月10日
 */
public class Administrators {

    private BigDecimal iadminid; //管理员账号

    private BigDecimal iid;//系统身份证号

    private BigDecimal iworkyears; //工作时长（年）

    public BigDecimal getIadminid() {
        return iadminid;
    }

    public void setIadminid(BigDecimal iadminid) {
        this.iadminid = iadminid;
    }

    public BigDecimal getIid() {
		return iid;
	}

	public void setIid(BigDecimal iid) {
		this.iid = iid;
	}

	public BigDecimal getIworkyears() {
        return iworkyears;
    }

    public void setIworkyears(BigDecimal iworkyears) {
        this.iworkyears = iworkyears;
    }
}
