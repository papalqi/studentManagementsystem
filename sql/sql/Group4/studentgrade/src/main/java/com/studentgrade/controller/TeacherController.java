/**
 * <p>Title: TeacherController.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月14日
 * @version 1.0
 */
package com.studentgrade.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.backinfo.TeacherCourseTableInfo;
import com.studentgrade.bean.CourseT;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Teacher;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.postinfo.TeacherOpenCourseInfo;

/**
 * <p>Title: TeacherController<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月14日
 */
@Controller
public class TeacherController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	
	/**
	 * <p>Title: ChangeTeacherPassword<／p>
	 * <p>Description: 修改密码（教师）<／p>
	 */
	@RequestMapping(value = "/ChangeTeacherPassword")
	@ResponseBody
	public Status ChangeTeacherPassword(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "username") BigDecimal username, 
			@RequestParam(value = "newpassword") String newpassword, @RequestParam(value = "oldpassword") String oldpassword){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				Teacher teacher = teacherMapper.selectByPrimaryKey(username);
				if (teacher.getIid()!=null) {
					Person person = personMapper.selectByPrimaryKey(teacher.getIid());
					if (person.getSpassword().equals(oldpassword)) {
						person.setSpassword(newpassword); //插入新密码
						try {
							personMapper.updateByPrimaryKeySelective(person);
							sqlSession.commit();
							System.out.println("修改密码（教师）信息成功");
						
							status.setStatus(200);
							status.setInfo("success");
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("修改密码（教师）信息没有找到账号");
							
							status.setStatus(400);
							status.setInfo("error1");
						}
					} else {
						System.out.println("修改密码（教师）旧密码错误");
						
						status.setStatus(400);
						status.setInfo("oldpassword not equal");
					}
				} else {
					System.out.println("修改密码（教师）信息没有找到账号");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("修改密码（教师）error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("修改密码（教师）信息超时");
			
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
	 * <p>Title: InsertTeacherCourse<／p>
	 * <p>Description:教师开课 <／p>
	 */
	@RequestMapping(value = "/InsertTeacherCourse", method = RequestMethod.POST)
	@ResponseBody
	public Status InsertTeacherCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestBody TeacherOpenCourseInfo info){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
			
			courseTMapper.Paddcourse(info.getCourseInfo());
			BigDecimal AdclassID = info.getCourseInfo().getAdclassID();
			System.out.println(info.getCourseInfo().getAdclassID());
			for (int i = 0; i < info.getTimelist().size(); i++) {
				info.getTimelist().get(i).setTeachclassid(AdclassID);
				courseTMapper.PsetCourseTimeAndRoom(info.getTimelist().get(i));
			}
			status.setStatus(200);
			System.out.println("开课成功");			
		} else {
			System.out.println("开课超时");
			
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
	 * <p>Title: DeleteTeacherCourse<／p>
	 * <p>Description:删除开设课程 <／p>
	 */
	@RequestMapping(value = "/DeleteTeacherCourse")
	@ResponseBody
	public Status DeleteTeacherCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couclassid") BigDecimal couclassid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				courseTMapper.PdeleteCourse(couclassid);
				status.setStatus(200);
				System.out.println("删开课成功");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("删开课error");
				status.setStatus(408);
				status.setInfo("error");
			}
		} else {
			System.out.println("删开课超时");
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
	 * <p>Title: SetScoreH<／p>
	 * <p>Description:设置课程分数占比 <／p>
	 */
	@RequestMapping(value = "/SetScoreH")
	@ResponseBody
	public Status SetScoreH(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couclassid") BigDecimal couclassid,
			@RequestParam(value = "score1h") BigDecimal score1h,@RequestParam(value = "score2h") BigDecimal score2h){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				CourseT c = courseTMapper.selectByPrimaryKey(couclassid);
				
				c.setIscore1h(score1h);
				c.setIscore2h(score2h);
				
				courseTMapper.updateByPrimaryKey(c);
				status.setStatus(200);
				System.out.println("设置课程分数占比成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				status.setStatus(400);
				System.out.println("设置课程分数占比成功");
			}
		} else {
			System.out.println("设置课程分数占比超时");
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
	 * <p>Title: SelectTeacherTableCourse<／p>
	 * <p>Description: 查看学生课表<／p>
	 */
	@RequestMapping(value = "/SelectTeacherTableCourse")
	@ResponseBody
	public TeacherCourseTableInfo SelectTeacherTableCourse(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid,
			@RequestParam(value = "week") BigDecimal week){
		TeacherCourseTableInfo info = new TeacherCourseTableInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
			Map<String,BigDecimal> map= new HashMap<String,BigDecimal>();
			map.put("iteachid", teaid);
			map.put("iweeknumber", week);
			info.setInfo(courseTMapper.getTeacherCourseTable(map));
			System.out.println("设置课程分数占比超时");
			info.setStatus(200);
		} else {
			System.out.println("设置课程分数占比超时");
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return info;
	}
}
