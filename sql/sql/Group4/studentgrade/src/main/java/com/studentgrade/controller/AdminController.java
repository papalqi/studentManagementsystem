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
import com.studentgrade.bean.Administrators;
import com.studentgrade.bean.CourseA;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.bean.Teacher;
import com.studentgrade.dao.AdministratorsMapper;
import com.studentgrade.dao.CourseAMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.ininfo.InsertStudentInfo;
import com.studentgrade.ininfo.InsertTeacherInfo;
import com.studentgrade.method.OnlineCheck;

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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
			@RequestParam(value = "teaname") String teaname, @RequestParam(value = "collegeid") BigDecimal collegeid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				InsertTeacherInfo insertTeacherInfo = new InsertTeacherInfo();
				insertTeacherInfo.setsName(teaname);
				insertTeacherInfo.setTeacherID(teaid);
				insertTeacherInfo.setCollegeid(collegeid);
				
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
					
					status.setStatus(400);
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
			
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
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
}