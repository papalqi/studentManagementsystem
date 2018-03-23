/**
 * <p>Title: AdministratorsMapper.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��10��
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;

import com.studentgrade.bean.Administrators;
import com.studentgrade.bean.VLeaveMessage;
import com.studentgrade.bean.Vadclasspaiming;
import com.studentgrade.bean.Vyhfk;
import com.studentgrade.ininfo.InsertLeaveMessage;
import com.studentgrade.ininfo.InsertNoPClass;
import com.studentgrade.ininfo.InsertYHFKInfo;

/**
 * <p>Title: AdministratorsMapper<��p>
 * <p>Description: Administrators��Ľӿڣ�dao<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��10��
 */
public interface AdministratorsMapper {
	
	/**
	 * <p>Title: SelectByUsername<��p>
	 * <p>Description:ͨ���û��������˺���Ϣ <��p>
	 */
	Administrators SelectByUsername(BigDecimal iadminid);
	
	void PleaveMessage(InsertLeaveMessage insertLeaveMessage);
	
	List<VLeaveMessage> getleaveMessage(BigDecimal iteachclassid);
	
	void PdeleteMessage(BigDecimal umid);
	
	void PaddYHFK(InsertYHFKInfo info);
	
	List<Vyhfk> getyhfkMessage();
	
	List<Vadclasspaiming> getNoPClass(InsertNoPClass info);
}
