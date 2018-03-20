/**
 * <p>Title: StudentController.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月13日
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

import com.studentgrade.backinfo.OpenCourseListInfo;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.dao.CourseAMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.method.OnlineCheck;

/**
 * <p>Title: StudentController<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月13日
 */
@Controller
public class StudentController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	/**
	 * <p>Title: ChangeAdminPassword<／p>
	 * <p>Description: 修改学生密码<／p>
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
							System.out.println("修改密码（学生）信息成功");
							
							status.setStatus(200);
							status.setInfo("success");
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("修改密码（学生）信息没有找到账号");
							
							status.setStatus(400);
							status.setInfo("error1");
						}
					} else {
						System.out.println("修改密码（学生）旧密码错误");
						
						status.setStatus(400);
						status.setInfo("oldpassword not equal");
					}
				}else {
					System.out.println("修改密码（学生）信息没有找到账号");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("修改密码（学生）error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("修改密码（学生）信息超时");
			
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
	 * <p>Title: SelectCanChoiseCourse<／p>
	 * <p>Description: 查看可选课程<／p>
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
				
				System.out.println("查看可选课程信息success");
				
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查看可选课程信息error");
				
				info.setStatus(400);
			}
		} else {
			System.out.println("查看可选课程信息超时");
			
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return info;
	}
}
