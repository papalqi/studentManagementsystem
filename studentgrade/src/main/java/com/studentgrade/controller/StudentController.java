/**
 * <p>Title: StudentController.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��13��
 * @version 1.0
 */
package com.studentgrade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.backinfo.GetStudentGradeListInfo;
import com.studentgrade.backinfo.OpenCourseListInfo;
import com.studentgrade.backinfo.StatusListT;
import com.studentgrade.backinfo.StudentCourseTableListInfo;
import com.studentgrade.bean.CourseT;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.dao.CourseAMapper;
import com.studentgrade.dao.CourseSMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.method.SynchronizeCheck;
import com.studentgrade.model.ClassGradeInfoItem;
import com.studentgrade.model.CourseAListInfoItem;
import com.studentgrade.model.OneCoursePlaceTime;
import com.studentgrade.model.OpenCourseListInfoItem;
import com.studentgrade.model.StudentCourseListItem;
import com.studentgrade.model.StudentCourseTableItem;

/**
 * <p>Title: StudentController<��p>
 * <p>Description: <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��13��
 */
@Controller
public class StudentController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	/**
	 * <p>Title: ChangeAdminPassword<��p>
	 * <p>Description: �޸�ѧ������<��p>
	 */
	@RequestMapping(value = "/ChangeStudentPassword")
	@ResponseBody
	public Status ChangeAdminPassword(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "username") BigDecimal username, 
			@RequestParam(value = "newpassword") String newpassword, @RequestParam(value = "oldpassword") String oldpassword){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				
				Student student = studentMapper.selectByPrimaryKey(username);
				if(student.getIid()!=null){
					Person person = personMapper.selectByPrimaryKey(student.getIid());
					if (person.getSpassword().equals(oldpassword)) {
						person.setSpassword(newpassword);
						try {
							personMapper.updateByPrimaryKeySelective(person);
							sqlSession.commit();
							System.out.println("�޸����루ѧ������Ϣ�ɹ�");
							
							status.setStatus(200);
							status.setInfo("success");
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("�޸����루ѧ������Ϣû���ҵ��˺�");
							
							status.setStatus(400);
							status.setInfo("error1");
						}
					} else {
						System.out.println("�޸����루ѧ�������������");
						
						status.setStatus(400);
						status.setInfo("oldpassword not equal");
					}
				}else {
					System.out.println("�޸����루ѧ������Ϣû���ҵ��˺�");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸����루ѧ����error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸����루ѧ������Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: SelectCanChoiseCourse<��p>
	 * <p>Description: �鿴��ѡ�γ�<��p>
	 */
	@RequestMapping(value = "/SelectCanChoiseCourse")
	@ResponseBody
	public OpenCourseListInfo SelectCanChoiseCourse(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		OpenCourseListInfo info = new OpenCourseListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				info.setInfo(courseAMapper.getCanChoiceCourseList(context));
				
				System.out.println("�鿴��ѡ�γ���Ϣsuccess");
				
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴��ѡ�γ���Ϣerror");
				
				info.setStatus(400);
			}
		} else {
			System.out.println("�鿴��ѡ�γ���Ϣ��ʱ");
			
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		
		return info;
	}
	/**
	 * <p>Title: ChoiseCourse<��p>
	 * <p>Description:ѧ��ѡ�� <��p>
	 */
	@RequestMapping(value = "/ChoiseCourse")
	@ResponseBody
	public Status ChoiseCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") BigDecimal couid, 
			@RequestParam(value = "stuid") BigDecimal stuid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				if (SynchronizeCheck.StudentChoiceCourse(couid, stuid)) {
					CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
					List<OneCoursePlaceTime> list2 = courseTMapper.getCourseTimePlace(couid);
					CourseT l3 = courseTMapper.selectByPrimaryKey(couid);
					Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
					map.put("istudentid", stuid);
					map.put("iweeknumber", new BigDecimal(1));
					CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
					List<StudentCourseTableItem> list = courseSMapper.getStudentCourseTable(map);
					for (int i = 0; i < list.size(); i++) {
						if (list2.get(0).getIweek().equals(list.get(i).getIweek())) {
							if (list2.get(0).getIsection().equals(list.get(i).getIsection())) {
								status.setStatus(402);
								status.setInfo("ѡ�Σ�ѧ������Ϣfail402");
								return status;
							}
						}
					}
					for (int i = 0; i < list.size(); i++) {
						System.out.println((list.get(i).getScourseid()+"ii "+l3.getScourseid()));
						if (list.get(i).getScourseid().equals(l3.getScourseid())) {
								System.out.println((list.get(i).getScourseid()+" "+l3.getScourseid()));
								status.setStatus(403);
								status.setInfo("ѡ�Σ�ѧ������Ϣfail403");
								return status;
							
						}
					}
					
					//CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
					Map<String, BigDecimal> map1 = new HashMap<String, BigDecimal>();
					map1.put("studentID", stuid);
					map1.put("courseID", couid);
					System.out.println(stuid);
					System.out.println(couid);
					courseSMapper.PselectCourse(map1);
					System.out.println("ѡ�Σ�ѧ������Ϣsuccess");
					
					status.setStatus(200);
					status.setInfo("success");
				} else {
					System.out.println("ѡ�Σ�ѧ������Ϣfail");
					
					status.setStatus(401);
					status.setInfo("fail");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ѡ�Σ�ѧ������Ϣerror");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("ѡ�Σ�ѧ������Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: ChoiseCourse<��p>
	 * <p>Description:ѧ���˿� <��p>
	 */
	@RequestMapping(value = "/DeleteChoiseCourse")
	@ResponseBody
	public Status DeleteChoiseCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") BigDecimal couid, 
			@RequestParam(value = "stuid") BigDecimal stuid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				
				Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
				map.put("studentID", stuid);
				map.put("courseID", couid);
				courseSMapper.PstudentTCourse(map);
				
				System.out.println("tui�Σ�ѧ������Ϣsuccess");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�˿Σ�ѧ������Ϣerror");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�˿Σ�ѧ������Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	 
	/**
	 * <p>Title: SelectChoiseCourse<��p>
	 * <p>Description: �鿴ѧ���γ�<��p>
	 */
	@RequestMapping(value = "/SelectStudentCourse")
	@ResponseBody
	public StudentCourseTableListInfo SelectStudentCourse(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "week") BigDecimal week, 
			@RequestParam(value = "stuid") BigDecimal stuid){
		StudentCourseTableListInfo info = new StudentCourseTableListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				
				Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
				map.put("istudentid", stuid);
				map.put("iweeknumber", week);
				info.setInfo(courseSMapper.getStudentCourseTable(map));
				
				System.out.println("�鿴ѧ���γ�success");
				
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ���γ�error");
				
				info.setStatus(400);
			}
		} else {
			System.out.println("�鿴ѧ���γ̳�ʱ");
			
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		
		return info;
	}
	
	/**
	 * <p>Title: SelectStudentGrade<��p>
	 * <p>Description:�鿴ѧ���ɼ� <��p>
	 */
	@RequestMapping(value = "/SelectStudentGrade")
	@ResponseBody
	public GetStudentGradeListInfo SelectStudentGrade(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		GetStudentGradeListInfo info = new GetStudentGradeListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				List<ClassGradeInfoItem> list = courseSMapper.getStudentScore(stuid);
				List<ClassGradeInfoItem> list2 = new ArrayList<ClassGradeInfoItem>();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).getBscorestate());
					BigDecimal bscorestate = new BigDecimal(2);
					System.out.println(bscorestate);
					if(list.get(i).getBscorestate()!=null&&list.get(i).getBscorestate().equals(bscorestate)){
						list2.add(list.get(i));
					}else{
						
					}
				}
				info.setInfo(list2);
				System.out.println("�鿴ѧ���ɼ�success");
				
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ���ɼ�error");
				
				info.setStatus(400);
			}
		} else {
			System.out.println("�鿴ѧ���ɼ���ʱ");
			
			info.setStatus(408);
		}

		return info;
	}
	
	/**
	 * <p>Title: SelectStudentChoiseCourse<��p>
	 * <p>Description:�鿴ѧ����ѡ�γ��б� <��p>
	 */
	@RequestMapping(value = "/SelectStudentChoiseCourse")
	@ResponseBody
	public StatusListT<StudentCourseListItem> SelectStudentChoiseCourse(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		StatusListT<StudentCourseListItem> status = new StatusListT<StudentCourseListItem>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				status.setInfo(courseSMapper.getStudentCourseL(stuid));
				
				System.out.println("�鿴ѧ����ѡ�γ��б�success");
				
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ����ѡ�γ��б�error");
				
				status.setStatus(400);
			}
		}else {
			System.out.println("�鿴ѧ����ѡ�γ��б���ʱ");
			
			status.setStatus(408);
		}
		return status;
	}
	
	/**
	 * <p>Title: GetStudentGPA<��p>
	 * <p>Description:�鿴ѧ��gpa <��p>
	 */
	@RequestMapping(value = "/GetStudentGPA")
	@ResponseBody
	public Status GetStudentGPA(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				
				Double aDouble = courseSMapper.getStudentPGA(stuid);
				if (aDouble!=null) {
					status.setInfo(aDouble.toString());
				}		
				System.out.println("�鿴ѧ��gpa success");
				
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ��gpa error");
				
				status.setStatus(400);
			}
		}else {
			System.out.println("�鿴ѧ��gpa��ʱ");
			
			status.setStatus(408);
		}
		return status;
	}
	
	/**
	 * <p>Title: GetStudentRank<��p>
	 * <p>Description:�鿴ѧ������ <��p>
	 */
	@RequestMapping(value = "/GetStudentRank")
	@ResponseBody
	public Status GetStudentRank(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				BigDecimal a = courseSMapper.getStudentPM(stuid);
				if (a!=null) {
					status.setInfo(a.toString());
				}
				System.out.println("GetStudentRank success");
				
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("GetStudentRank error");
				
				status.setStatus(400);
			}
		}else {
			System.out.println("GetStudentRank��ʱ");
			
			status.setStatus(408);
		}
		return status;
	}
	
	/**
	 * <p>Title: SelectStudentCanChoiceCourselist<��p>
	 * <p>Description: �鿴ѧ����ѡ�γ�<��p>
	 */
	@RequestMapping(value = "/SelectStudentCanChoiceCourselist")
	@ResponseBody
	public StatusListT<CourseAListInfoItem> SelectStudentCanChoiceCourselist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StatusListT<CourseAListInfoItem> status = new StatusListT<CourseAListInfoItem>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				status.setInfo(courseSMapper.getCourseCanChoiceList(context));
				System.out.println("�鿴ѧ����ѡ�γ� success");
				
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ����ѡ�γ� error");
				
				status.setStatus(400);
			}
		}else {
			System.out.println("�鿴ѧ����ѡ�γ�  ��ʱ");
			
			status.setStatus(408);
		}
		return status;
	}
	
	/**
	 * <p>Title: SelectCourseByCourseID<��p>
	 * <p>Description: ͨ���γ����ѡ��<��p>
	 */
	@RequestMapping(value = "/SelectCourseByCourseID")
	@ResponseBody
	public StatusListT<OpenCourseListInfoItem> SelectCourseByCourseID(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "courseid") String courseid){
		StatusListT<OpenCourseListInfoItem> status = new StatusListT<OpenCourseListInfoItem>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				status.setInfo(courseSMapper.getCanChoiceCourseList2(courseid));
				System.out.println("ͨ���γ����ѡ�� success");
				
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ͨ���γ����ѡ�� error");
				
				status.setStatus(400);
			}
		}else {
			System.out.println("ͨ���γ����ѡ��  ��ʱ");
			
			status.setStatus(408);
		}
		return status;
	}
}