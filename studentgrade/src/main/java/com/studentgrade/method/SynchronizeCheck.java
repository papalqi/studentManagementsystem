/**
 * <p>Title: SynchronizeCheck.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��21��
 * @version 1.0
 */
package com.studentgrade.method;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.studentgrade.bean.CourseT;
import com.studentgrade.dao.CourseSMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;

/**
 * <p>Title: SynchronizeCheck<��p>
 * <p>Description: <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��21��
 */
public class SynchronizeCheck {

	/**
	 * <p>Title: StudentChoiceCourse<��p>
	 * <p>Description: ���γ��Ƿ��ѡ<��p>
	 */
	public static synchronized boolean StudentChoiceCourse(BigDecimal couresid, BigDecimal studentid){
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
		CourseT courseT = courseTMapper.selectByPrimaryKey(couresid);
		BigDecimal a = courseT.getIstudentnumber();
		BigDecimal b = courseT.getIstudentmaxnumber();
		if ( a.intValue() >= b.intValue()) {
			return false;
		} else {
			
			return true;
		}
	}
}