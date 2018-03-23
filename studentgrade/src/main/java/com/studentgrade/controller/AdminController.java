/**
 * <p>Title: AdminController.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��13��
 * @version 1.0
 */
package com.studentgrade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.backinfo.CourseListInfo;
import com.studentgrade.backinfo.OpenCourseListInfo;
import com.studentgrade.backinfo.StatusListT;
import com.studentgrade.bean.Adclass;
import com.studentgrade.bean.Administrators;
import com.studentgrade.bean.CourseA;
import com.studentgrade.bean.CourseT;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.bean.Teacher;
import com.studentgrade.bean.Vadclasspaiming;
import com.studentgrade.bean.Vjidianpaiming;
import com.studentgrade.bean.Yhfk;
import com.studentgrade.dao.AdclassMapper;
import com.studentgrade.dao.AdministratorsMapper;
import com.studentgrade.dao.CourseAMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.dao.YhfkMapper;
import com.studentgrade.ininfo.InsertNoPClass;
import com.studentgrade.ininfo.InsertStudentInfo;
import com.studentgrade.ininfo.InsertTeacherInfo;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.method.StudentRank;
import com.studentgrade.model.ClassGradeInfoItem;
import com.studentgrade.model.SetPassGradeCourseListItem;

/**
 * <p>Title: AdminController<��p>
 * <p>Description: ����Ա������������<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��13��
 */
@Controller
public class AdminController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	/**
	 * <p>Title: insertStudent<��p>
	 * <p>Description:����ѧ����Ϣ <��p>
	 */
	@RequestMapping(value = "/InsertStudent")
	@ResponseBody
	public Status insertStudent(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid, 
			@RequestParam(value = "stuname") String stuname, @RequestParam(value = "classid") BigDecimal classid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				InsertStudentInfo insertStudentInfo = new InsertStudentInfo();
				insertStudentInfo.setsName(stuname);
				insertStudentInfo.setClassID(classid);
				insertStudentInfo.setStudentID(stuid);
				BigDecimal age = new BigDecimal(0);
				insertStudentInfo.setAge(age);
				insertStudentInfo.setGender("δ¼��");
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				studentMapper.insertStudent(insertStudentInfo);
				System.out.println("����ѧ����Ϣ�ɹ�");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����ѧ����Ϣʧ��");
				
				status.setStatus(400);
				status.setInfo("fail");
			}
		} else {
			System.out.println("����ѧ����Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
	
		return status;
	}
	
	/**
	 * <p>Title: insertTeacher<��p>
	 * <p>Description: �����ʦ<��p>
	 */
	@RequestMapping(value = "/InsertTeacher")
	@ResponseBody
	public Status insertTeacher(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid, 
			@RequestParam(value = "teaname") String teaname, @RequestParam(value = "collegeid") BigDecimal collegeid,
			@RequestParam(value = "title") String title){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				InsertTeacherInfo insertTeacherInfo = new InsertTeacherInfo();
				insertTeacherInfo.setsName(teaname);
				insertTeacherInfo.setTeacherID(teaid);
				insertTeacherInfo.setCollegeid(collegeid);
				insertTeacherInfo.setTilte(title);
				
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				if (teacherMapper.selectByPrimaryKey(teaid)!=null) {
					System.out.println("������ʦ��Ϣʧ��(����)");
					
					status.setStatus(400);
					status.setInfo("teacher exist");
				} else {
					teacherMapper.insertTeacher(insertTeacherInfo);
					System.out.println("�����ʦ��Ϣ�ɹ�");
				
					status.setStatus(200);
					status.setInfo("success");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("������ʦ��Ϣʧ��");
				
				status.setStatus(400);
				status.setInfo("fail");
			}
		} else {
			System.out.println("�����ʦ��Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	/**
	 * <p>Title: DeleteTeacher<��p>
	 * <p>Description: ɾ����ʦ<��p>
	 */
	@RequestMapping(value = "/DeleteTeacher")
	@ResponseBody
	public Status DeleteTeacher(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		System.out.println(teaid.intValue());
		if (session.getId().equals(cookie)) {
			try {
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				if(teacherMapper.selectByPrimaryKey(teaid)==null){
					System.out.println("ɾ����ʦ��Ϣʧ��(������)");
					
					status.setStatus(400);
					status.setInfo("teacher not exist");
				}else {
					teacherMapper.deleteByTeacherId(teaid);	
					System.out.println("ɾ����ʦ��Ϣ�ɹ�");
					
					status.setStatus(200);
					status.setInfo("success");
					
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ɾ����ʦ��Ϣʧ��(error)");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("ɾ����ʦ��Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: ChangeAdminPassword<��p>
	 * <p>Description: �޸Ĺ���Ա����<��p>
	 */
	@RequestMapping(value = "/ChangeAdminPassword")
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
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				
				Administrators administrators = administratorsMapper.SelectByUsername(username);
				if(administrators.getIid()!=null){
					Person person = personMapper.selectByPrimaryKey(administrators.getIid());
					if (person.getSpassword().equals(oldpassword)) {
						person.setSpassword(newpassword);
						try {
							personMapper.updateByPrimaryKeySelective(person);
							sqlSession.commit();
							System.out.println("�޸����루����Ա����Ϣ�ɹ�");
							
							status.setStatus(200);
							status.setInfo("success");
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("�޸����루����Ա����Ϣû���ҵ��˺�");
							
							status.setStatus(400);
							status.setInfo("error1");
						}
					} else {
						System.out.println("�޸����루����Ա����Ϣ���������");
						
						status.setStatus(400);
						status.setInfo("oldpassword not equal");
					}
				}else {
					System.out.println("�޸����루����Ա����Ϣû���ҵ��˺�");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸����루����Ա��error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸����루����Ա����Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		
		return status;
	}
	/**
	 * <p>Title: DeleteStudent<��p>
	 * <p>Description: ɾ��ѧ��<��p>
	 */
	@RequestMapping(value = "/DeleteStudent")
	@ResponseBody
	public Status DeleteStudent(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				Student student = studentMapper.selectByPrimaryKey(stuid);
				if(student!=null){
					studentMapper.deleteByStudentId(stuid);
					sqlSession.commit();
					System.out.println("ɾ��ѧ����Ϣ�ɹ�");
					
					status.setStatus(200);
					status.setInfo("success");
				}else {
					System.out.println("ɾ��ѧ����Ϣ������");
					
					status.setStatus(400);
					status.setInfo("error1");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ɾ��ѧ��error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("ɾ��ѧ����Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		
		return status;
	}
	
	/**
	 * <p>Title: UpdataStudent<��p>
	 * <p>Description: �޸�ѧ���˺���Ϣ<��p>
	 */
	@RequestMapping(value = "/UpdateStudent")
	@ResponseBody
	public Status UpdateStudent(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid,
			@RequestParam(value = "stuname") String stuname, @RequestParam(value = "classid") BigDecimal classid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				
				Student student = studentMapper.selectByPrimaryKey(stuid);
				if(student !=null){
					
					Person person = personMapper.selectByPrimaryKey(student.getIid());
					
					System.out.println(person.getSname()+","+person.getIid());
					student.setIclassid(classid);
					person.setSname(stuname);
					System.out.println(person.getSname()+","+person.getIid());
					
					int a = studentMapper.updateByPrimaryKey(student);
					int b = personMapper.updateByPrimaryKey(person);
					//personMapper.updateSname(person);
					sqlSession.commit();
					System.out.println("�޸�ѧ����Ϣ�ɹ�,"+a+","+b);
					
					status.setStatus(200);
					status.setInfo("success");
				}else {
					System.out.println("�޸�ѧ��fail");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸�ѧ��error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸�ѧ����Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: UpdataTeacher<��p>
	 * <p>Description:�޸Ľ�ʦ�˺���Ϣ <��p>
	 */
	@RequestMapping(value = "/UpdataTeacher")
	@ResponseBody
	public Status UpdataTeacher(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid,
			@RequestParam(value = "teaname") String teaname, @RequestParam(value = "collegeid") BigDecimal collegid,
			@RequestParam(value = "teatitle") String teatitle){
		Status status = new Status();
		HttpSession session = request.getSession();
		
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				Teacher teacher = teacherMapper.selectByPrimaryKey(teaid);
				if (teacher!=null) {
					Person person = personMapper.selectByPrimaryKey(teacher.getIid());
					person.setSname(teaname);
					teacher.setIcollegeid(collegid);
					teacher.setStitle(teatitle);
					
					personMapper.updateByPrimaryKey(person);
					teacherMapper.updateByPrimaryKey(teacher);
					sqlSession.commit();
					
					System.out.println("�޸Ľ�ʦsuccess");
					
					status.setStatus(200);
					status.setInfo("success");
				} else {
					System.out.println("�޸Ľ�ʦfail");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸Ľ�ʦerror");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸Ľ�ʦ��Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
			
		
		return status;
	}
	
	/**
	 * <p>Title: UpdateStudentPasswordA<��p>
	 * <p>Description: ����Ա�޸�ѧ������<��p>
	 */
	@RequestMapping(value = "/UpdateStudentPasswordA")
	@ResponseBody
	public Status UpdateStudentPasswordA(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid,
			@RequestParam(value = "password") String password){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				Student student = studentMapper.selectByPrimaryKey(stuid);
				Person person = personMapper.selectByPrimaryKey(student.getIid());
				
				person.setSpassword(password);
				personMapper.updateByPrimaryKey(person);
				sqlSession.commit();
				System.out.println("�޸�ѧ������ɹ�");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸�ѧ������error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸�ѧ�����볬ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	/**
	 * <p>Title: UpdateStudentPasswordA<��p>
	 * <p>Description: ����Ա�޸Ľ�ʦ����<��p>
	 */
	@RequestMapping(value = "/UpdateTeacherPasswordA")
	@ResponseBody
	public Status UpdateTeacherPasswordA(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid,
			@RequestParam(value = "password") String password){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				Teacher teacher = teacherMapper.selectByPrimaryKey(teaid);
				Person person = personMapper.selectByPrimaryKey(teacher.getIid());
				
				person.setSpassword(password);
				personMapper.updateByPrimaryKey(person);
				sqlSession.commit();
				System.out.println("�޸Ľ�ʦ����ɹ�");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸Ľ�ʦ����error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸Ľ�ʦ���볬ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	/**
	 * <p>Title: InsertCourse<��p>
	 * <p>Description:���ӿγ� <��p>
	 */
	@RequestMapping(value = "/InsertCourse")
	@ResponseBody
	public Status InsertCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") String couid,
			@RequestParam(value = "couname") String couname, @RequestParam(value = "majorid") BigDecimal majoreid,
			@RequestParam(value = "couno") BigDecimal couno, @RequestParam(value = "icredit") BigDecimal icredit,
			@RequestParam(value = "ihours") BigDecimal ihours){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			CourseA courseA = new CourseA();
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				
				courseA.setIscournumber(couno);
				courseA.setIcredit(icredit);
				courseA.setIhours(ihours);
				courseA.setImajorid(majoreid);
				courseA.setScourseid(couid);
				courseA.setScoursename(couname);
				BigDecimal a = new BigDecimal(0);
				courseA.setInowscournumber(a);
				
				courseAMapper.insert(courseA);
				
				System.out.println("���ӿγ̳ɹ�");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���ӿγ�error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
			
		} else {
			System.out.println("���ӿγ̳�ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: DeleteCourse<��p>
	 * <p>Description:ɾ���γ� <��p>
	 */
	@RequestMapping(value = "/DeleteCourse")
	@ResponseBody
	public Status DeleteCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") String couid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				courseAMapper.deleteByPrimaryKey(couid);
				System.out.println("ɾ���γ�success");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ɾ���γ�error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
			
		} else {
			System.out.println("ɾ���γ̳�ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		
		return status;
	}
	/**
	 * <p>Title: UpdateCourse<��p>
	 * <p>Description: ���Ŀγ�<��p>
	 */
	@RequestMapping(value = "/UpdateCourse")
	@ResponseBody
	public Status UpdateCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") String couid,
			@RequestParam(value = "couname") String couname, @RequestParam(value = "majorid") BigDecimal majoreid,
			@RequestParam(value = "couno") BigDecimal couno, @RequestParam(value = "icredit") BigDecimal icredit,
			@RequestParam(value = "ihours") BigDecimal ihours){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {	
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				CourseA courseA = courseAMapper.selectByPrimaryKey(couid);
				courseA.setIscournumber(couno);
				courseA.setIcredit(icredit);
				courseA.setIhours(ihours);
				courseA.setImajorid(majoreid);
				courseA.setScourseid(couid);
				courseA.setScoursename(couname);
				
				courseAMapper.updateByPrimaryKey(courseA);
				sqlSession.commit();
				System.out.println("�޸Ŀγ̳ɹ�");
				
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸Ŀγ�error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
			
		} else {
			System.out.println("�޸Ŀγ̳�ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		
		return status;
	}
	
	/**
	 * <p>Title: SelectCourse<��p>
	 * <p>Description:ģ����ѯ�γ� <��p>
	 */
	@RequestMapping(value = "/SelectCourse")
	@ResponseBody
	public CourseListInfo SelectCourse(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		CourseListInfo info = new CourseListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				info.setInfo(courseAMapper.getCourseList(context));
				info.setStatus(200);
				System.out.println("��ѯ�γ̳ɹ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ѯ�γ�ʧ��");
				
				info.setStatus(400);
			}
			
		} else {
			System.out.println("��ѯ�γ̳�ʱ");
			
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		
		return info;
	}
	
	/**
	 * <p>Title: SelectOpenCourse<��p>
	 * <p>Description: �鿴ĳ����γ���Ϣ�б�<��p>
	 */
	@RequestMapping(value = "/SelectOpenCourse")
	@ResponseBody
	public OpenCourseListInfo SelectOpenCourse(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") String couid){
		OpenCourseListInfo info = new OpenCourseListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				info.setInfo(courseAMapper.getOpenCourseList(couid));
				info.setStatus(200);
				System.out.println("��ѯ�γ̳ɹ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ѯ�γ�ʧ��");
				
				info.setStatus(400);
			}
			
		} else {
			System.out.println("�鿴ĳ����γ���Ϣ�б���ʱ");
			
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return info;
	}
	/**
	 * <p>Title: SelectNoPassGrade<��p>
	 * <p>Description: �鿴δ��˳ɼ��б�<��p>
	 */
	@RequestMapping(value = "/SelectNoPassGrade")
	@ResponseBody
	public StatusListT<SetPassGradeCourseListItem> SelectNoPassGrade(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StatusListT<SetPassGradeCourseListItem> status = new StatusListT<SetPassGradeCourseListItem>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				
				List<SetPassGradeCourseListItem> list1 = courseAMapper.selectNoPassGrade(context);
				List<SetPassGradeCourseListItem> list2 = new ArrayList<SetPassGradeCourseListItem>();
				BigDecimal a = new BigDecimal(1);
				for (int i = 0; i < list1.size(); i++) {
					System.out.println(list1.size());
					System.out.println(list1.get(i).getBscorestate());
					if (list1.get(i).getBscorestate()!=null&&list1.get(i).getBscorestate().equals(a)) {
						list2.add(list1.get(i));
					} else {
	
					}
				}
				status.setInfo(list2);
				status.setStatus(200);
				System.out.println("�鿴δ��˳ɼ��б�ss");
			} catch (Exception e) {
				// TODO: handle exception
				status.setStatus(400);
				System.out.println("�鿴δ��˳ɼ��б�ee");
			}
		} else {
			System.out.println("�鿴δ��˳ɼ��б���ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: SelectNoPassGrade<��p>
	 * <p>Description: �鿴�Ѿ���˳ɼ��б�<��p>
	 */
	@RequestMapping(value = "/SelectPassGrade")
	@ResponseBody
	public StatusListT<SetPassGradeCourseListItem> SelectPassGrade(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StatusListT<SetPassGradeCourseListItem> status = new StatusListT<SetPassGradeCourseListItem>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				
				List<SetPassGradeCourseListItem> list1 = courseAMapper.selectNoPassGrade(context);
				List<SetPassGradeCourseListItem> list2 = new ArrayList<SetPassGradeCourseListItem>();
				BigDecimal a = new BigDecimal(2);
				for (int i = 0; i < list1.size(); i++) {
					System.out.println(list1.size());
					System.out.println(list1.get(i).getBscorestate());
					if (list1.get(i).getBscorestate()!=null&&list1.get(i).getBscorestate().equals(a)) {
						list2.add(list1.get(i));
					} else {
	
					}
				}
				status.setInfo(list2);
				status.setStatus(200);
				System.out.println("�鿴�Ѿ���˳ɼ��б�ss");
			} catch (Exception e) {
				// TODO: handle exception
				status.setStatus(400);
				System.out.println("�鿴�Ѿ���˳ɼ��б�ee");
			}
		} else {
			System.out.println("�鿴�Ѿ���˳ɼ��б���ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	/**
	 * <p>Title: CheckClassGrade<��p>
	 * <p>Description:��˳ɼ� <��p>
	 */
	@RequestMapping(value = "/CheckClassGrade")
	@ResponseBody
	public Status CheckClassGrade(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teacouid") BigDecimal teacouid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				CourseT courseT = courseTMapper.selectByPrimaryKey(teacouid);
				
				BigDecimal aBigDecimal = new BigDecimal(2);
				courseT.setBscorestate(aBigDecimal);
				courseTMapper.updateByPrimaryKey(courseT);
				sqlSession.commit();
				
				
				List<ClassGradeInfoItem> list = courseTMapper.getClassScore(teacouid);
				for (int i = 0; i < list.size(); i++) {
					if (StudentRank.CheckOneStudentJidian(list.get(i).getIstudentid())==1) {
						StudentRank.CalOneStudentJidian(list.get(i).getIstudentid());
					}
				}
				for (int i = 0; i < list.size(); i++) {
					Student student = studentMapper.selectByPrimaryKey(list.get(i).getIstudentid());
					StudentRank.Checkadclasspaiming(student.getIclassid());
				}
				
				System.out.println("��˳ɼ��б�success");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��˳ɼ��б�error");
				status.setStatus(400);
			}
		} else {
			System.out.println("��˳ɼ��б���ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: SelectNoPClass<��p>
	 * <p>Description: �鿴δ�����༶<��p>
	 */
	@RequestMapping(value = "/SelectNoPClass")
	@ResponseBody
	public StatusListT<Vadclasspaiming> SelectNoPClass(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StatusListT<Vadclasspaiming> status = new StatusListT<Vadclasspaiming>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		InsertNoPClass info = new InsertNoPClass();
		info.setIchengjib(new BigDecimal(1));
		info.setContext(context);
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				status.setInfo(administratorsMapper.getNoPClass(info));
				System.out.println("�鿴δ�����༶success");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴δ�����༶error");
				status.setStatus(400);
			}
		} else {
			System.out.println("�鿴δ�����༶��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		//StudentRank.Caladclasspaiming(iclassid);
		return status;
	}
	
	/**
	 * <p>Title: SelectYPClass<��p>
	 * <p>Description: �鿴�Ѿ������༶<��p>
	 */
	@RequestMapping(value = "/SelectYPClass")
	@ResponseBody
	public StatusListT<Vadclasspaiming> SelectYPClass(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StatusListT<Vadclasspaiming> status = new StatusListT<Vadclasspaiming>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		InsertNoPClass info = new InsertNoPClass();
		info.setIchengjib(new BigDecimal(2));
		info.setContext(context);
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				status.setInfo(administratorsMapper.getNoPClass(info));
				System.out.println("�鿴yi�����༶success");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴yi�����༶error");
				status.setStatus(400);
			}
		} else {
			System.out.println("�鿴yi�����༶��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		//StudentRank.Caladclasspaiming(iclassid);
		return status;
	}
	
	/**
	 * <p>Title: RankClassGrade<��p>
	 * <p>Description:����Ա������������ <��p>
	 */
	@RequestMapping(value = "/RankClassGrade")
	@ResponseBody
	public Status RankClassGrade(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "classid") BigDecimal classid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {		
			try {
				AdclassMapper adclassMapper = sqlSession.getMapper(AdclassMapper.class);
				Adclass adclass = adclassMapper.selectByPrimaryKey(classid);
				StudentRank.Caladclasspaiming(classid);
				adclass.setIchengjib(new BigDecimal(2));
				adclassMapper.updateByPrimaryKey(adclass);
				sqlSession.commit();
				System.out.println("����Ա������������ �ɹ�");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����Ա������������ error");
				status.setStatus(400);
			}
		} else {
			System.out.println("����Ա������������ ��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: SelectClassGradeRanking<��p>
	 * <p>Description:�鿴ѧ���༶�������� <��p>
	 */
	@RequestMapping(value = "/SelectClassGradeRanking")
	@ResponseBody
	public StatusListT<Vjidianpaiming> SelectClassGradeRanking(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "classid") BigDecimal classid){
		StatusListT<Vjidianpaiming> status = new StatusListT<Vjidianpaiming>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				List<Vjidianpaiming> list = courseTMapper.getStudentPMByclassid(classid);
				if(list.size() > 0){
					StudentRank.qsortt(list, 0, list.size()-1);
				}
				status.setInfo(list);
				System.out.println("�鿴ѧ���༶�������� success");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴ѧ���༶�������� error");
				status.setStatus(400);
			}
			
		} else {
			System.out.println("�鿴ѧ���༶�������� ��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: GetCourseNo<��p>
	 * <p>Description: �鿴�γ�����<��p>
	 */
	@RequestMapping(value = "/GetCourseNo")
	@ResponseBody
	private Status GetCourseNo(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
				List<CourseA> list = courseAMapper.selectAllCourseNo();
				String a = list.size()+"";
				status.setInfo(a);
				System.out.println("�鿴�γ����� �ɹ�");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴�γ����� error");
				status.setStatus(400);
			}
		} else {
			System.out.println("�鿴�γ����� ��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	
	/**
	 * <p>Title: GetYHFKno<��p>
	 * <p>Description:�鿴yhfkno <��p>
	 */
	@RequestMapping(value = "/GetYHFKno")
	@ResponseBody
	public Status GetYHFKno(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		if (session.getId().equals(cookie)) {
			try {
				YhfkMapper yhfkMapper = sqlSession.getMapper(YhfkMapper.class);
				List<Yhfk> list = yhfkMapper.selectAll();
				
				
				String a = list.size()+"";
				status.setInfo(a);
				System.out.println("�鿴yhfk �ɹ�");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴yhfk error");
				status.setStatus(400);
			}
		} else {
			System.out.println("�鿴yhfk ��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
}