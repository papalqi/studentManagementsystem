/**
 * <p>Title: StudentRank.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月19日
 * @version 1.0
 */
package com.studentgrade.method;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.studentgrade.bean.Adclass;
import com.studentgrade.bean.Student;
import com.studentgrade.bean.VLeaveMessage;
import com.studentgrade.bean.Vjidianpaiming;
import com.studentgrade.bean.Vxueshengkpaiming;
import com.studentgrade.bean.Vyhfk;
import com.studentgrade.dao.AdclassMapper;
import com.studentgrade.dao.CourseSMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.model.ClassGradeInfoItem;

/**
 * <p>Title: StudentRank<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月19日
 */
public class StudentRank {
	
	public static void yqsort(List<Vyhfk> list, int l, int r){
		int i = l,j = r;
		System.out.println(list.get(0).getMessage());
		int  k = list.get((l+r)/2).getYid().intValue();
		while (i < j) {
			while(list.get(i).getYid().intValue() > k){
				i++;
			}
			while (list.get(j).getYid().intValue() < k){
				j--;
			}
			if (i<=j) {
				Vyhfk v = list.get(i);
				list.set(i, list.get(j));
				list.set(j, v);
				i++;
				j--;
			}
		}
		if (l<j) {
			yqsort(list, l, j);
		}
		if (i<r) {
			yqsort(list, i, r);
		}
	}
	
	
	public static void lqsort(List<VLeaveMessage> list, int l, int r){
		int i = l,j = r;
		int  k = list.get((l+r)>>1).getMid().intValue();
		while (i < j) {
			while(list.get(i).getMid().intValue() > k){
				i++;
			}
			while (list.get(j).getMid().intValue() < k){
				j--;
			}
			if (i<=j) {
				VLeaveMessage v = list.get(i);
				list.set(i, list.get(j));
				list.set(j, v);
				i++;
				j--;
			}
		}
		if (l<j) {
			lqsort(list, l, j);
		}
		if (i<r) {
			lqsort(list, i, r);
		}
	}
	
	public static void qsort(List<Vjidianpaiming> list, int l, int r){
		int i = l,j = r;
		Double  k = list.get((l+r)>>1).getDjidian();
		while (i < j) {
			while(list.get(i).getDjidian() < k){
				i++;
			}
			while (list.get(j).getDjidian() > k){
				j--;
			}
			if (i<=j) {
				Vjidianpaiming v = list.get(i);
				list.set(i, list.get(j));
				list.set(j, v);
				i++;
				j--;
			}
		}
		if (l<j) {
			qsort(list, l, j);
		}
		if (i<r) {
			qsort(list, i, r);
		}
	}
	
	public static void qsortt(List<Vjidianpaiming> list, int l, int r){
		int i = l,j = r;
		int  k = list.get((l+r)>>1).getIdorm().intValue();
		while (i < j) {
			while(list.get(i).getIdorm().intValue() < k){
				i++;
			}
			while (list.get(j).getIdorm().intValue() > k){
				j--;
			}
			if (i<=j) {
				Vjidianpaiming v = list.get(i);
				list.set(i, list.get(j));
				list.set(j, v);
				i++;
				j--;
			}
		}
		if (l<j) {
			qsortt(list, l, j);
		}
		if (i<r) {
			qsortt(list, i, r);
		}
	}
	
	public static int CheckOneStudentJidian(BigDecimal stuid){
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		try {
			CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
			List<ClassGradeInfoItem> list = courseSMapper.getStudentScore(stuid);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBscorestate()==null||list.get(i).getBscorestate().equals(new BigDecimal(0))) {
					System.out.println(stuid.doubleValue()+"jidain 检查 fail");
					return 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(stuid.doubleValue()+"jidain 检查 error");
			return 0;
		}
		System.out.println(stuid.doubleValue()+"jidain 检查 success");
		return 1;
	}
	
	public static int CalOneStudentJidian(BigDecimal stuid){
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		try {
			CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			List<Vxueshengkpaiming> list = courseTMapper.getStudentGradeBystuid(stuid);
			int sumxuefen = 0;
			double sumjidian = 0;
			double jidian = 0;
			for (int i = 0; i < list.size(); i++) {
				sumxuefen = sumxuefen + list.get(i).getIcredit().intValue();
				if (list.get(i).getIallscore().intValue()<=50) {
					sumjidian = sumjidian + 0;
				}else if (list.get(i).getIallscore().intValue()>50&&list.get(i).getIallscore().intValue()<=90) {
					sumjidian = sumjidian + (((list.get(i).getIallscore().doubleValue()-50)/10.0)*list.get(i).getIcredit().intValue());
				}else if (list.get(i).getIallscore().intValue()>90) {
					sumjidian = sumjidian + (4*list.get(i).getIcredit().intValue());
				}
			}
			jidian = sumjidian/sumxuefen;
			Student student = studentMapper.selectByPrimaryKey(stuid);
			student.setDjidian(jidian);
			studentMapper.updateByPrimaryKey(student);
			sqlSession.commit();
			System.out.println(stuid.doubleValue()+"jidain success");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(stuid.doubleValue()+"jidain error");
			return 0;
		}
	}
	
	public static int Checkadclasspaiming(BigDecimal iclassid){
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		try {
			CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
			List<Vjidianpaiming> list = courseTMapper.getStudentPMByclassid(iclassid);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDjidian()==null) {
					System.out.println(iclassid.intValue()+"jidainpaming 检查 fail");
					return 0;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(iclassid.intValue()+"jidainpaming 检查 error");
			return 0;
		}
		
		try {
			AdclassMapper adclassMapper = sqlSession.getMapper(AdclassMapper.class);
			Adclass adclass = adclassMapper.selectByPrimaryKey(iclassid);
			adclass.setIchengjib(new BigDecimal(1));
			adclassMapper.updateByPrimaryKey(adclass);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(iclassid.intValue()+"jidainpaming 检查 error2");
			return 0;
		}
		System.out.println(iclassid.intValue()+"jidainpaming 检查 success");
		return 1;
	}
	
	public static int Caladclasspaiming(BigDecimal iclassid){
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		try {
			CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			List<Vjidianpaiming> list = courseTMapper.getStudentPMByclassid(iclassid);
			qsort(list, 0, list.size()-1);
			int p = 1;
			for (int i = list.size()-1; i >= 0; i--) {
				Student student = studentMapper.selectByPrimaryKey(list.get(i).getIstudentid());
				student.setIdorm(new BigDecimal(p));
				studentMapper.updateByPrimaryKey(student);
				sqlSession.commit();
				p++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(iclassid.intValue()+"jidainpaming p errore");
			return 0;
		}
		System.out.println(iclassid.intValue()+"jidainpaming 检查 success");
		return 1;
	}
	

}
