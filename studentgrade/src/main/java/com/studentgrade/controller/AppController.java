/**
 * <p>Title: AppController.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月9日
 * @version 1.0
 */
package com.studentgrade.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.studentgrade.backinfo.ADClassListInfo;
import com.studentgrade.backinfo.ADClassListInfo.ADClassName;
import com.studentgrade.backinfo.AcademicbuildingListInfo;
import com.studentgrade.backinfo.CampusListinfo;
import com.studentgrade.backinfo.ClassroomListInfo;
import com.studentgrade.backinfo.CollegeListInfo;
import com.studentgrade.backinfo.MajorListInfo;
import com.studentgrade.backinfo.StatusObjectT;
import com.studentgrade.backinfo.CollegeListInfo.CollegeName;
import com.studentgrade.backinfo.MajorListInfo.MajorName;
import com.studentgrade.backinfo.StatusListT;
import com.studentgrade.backinfo.StudentDetailInfo;
import com.studentgrade.backinfo.StudentListInfo;
import com.studentgrade.backinfo.StudentListInfo.StudentListItem;
import com.studentgrade.backinfo.TeacherDetailInfo;
import com.studentgrade.backinfo.TeacherListInfo;
import com.studentgrade.bean.Adclass;
import com.studentgrade.bean.Administrators;
import com.studentgrade.bean.Classtimetale;
import com.studentgrade.bean.College;
import com.studentgrade.bean.Major;
import com.studentgrade.bean.OnlineNoInfo;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.bean.Teacher;
import com.studentgrade.bean.VLeaveMessage;
import com.studentgrade.bean.Vyhfk;
import com.studentgrade.dao.AcademicbuildingMapper;
import com.studentgrade.dao.AdclassMapper;
import com.studentgrade.dao.AdministratorsMapper;
import com.studentgrade.dao.CampusMapper;
import com.studentgrade.dao.ClassroomMapper;
import com.studentgrade.dao.ClasstimetaleMapper;
import com.studentgrade.dao.CollegeMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.MajorMapper;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.ininfo.InsertLeaveMessage;
import com.studentgrade.ininfo.InsertYHFKInfo;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.method.StudentRank;
import com.studentgrade.model.ClassScoreH;
import com.studentgrade.model.PicPath;
import com.studentgrade.model.StudentListInfoItem;
import com.studentgrade.model.TeacherListInfoItem;
//import com.studentgrade.postinfo.SetPhotoInfo;

/**
 * <p>Title: AppController<／p>
 * <p>Description: 控制器，处理前端访问请求,通用请求<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月9日
 */
@Controller
public class AppController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	/**
	 * <p>Title: Login<／p>
	 * <p>Description: 登录接口<／p>
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	@ResponseBody
	public Status Login(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "username") BigDecimal username,
			@RequestParam(value = "password") String password, @RequestParam(value = "identity") int identity){
		Status status = new Status();
		
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		Date d = new Date();
		System.out.println(d);
		try {
			PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
			AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
			
			Administrators administrators = administratorsMapper.SelectByUsername(username);
			Student student = studentMapper.selectByPrimaryKey(username);
			Teacher teacher = teacherMapper.selectByPrimaryKey(username);
			
			switch (identity) {
			case 1:
					if(administrators.getIid()!=null){
						System.out.println(administrators.getIid()+"身份"+identity);
						String ppassword = personMapper.getPasswordByIid(administrators.getIid());
						//Person person = personMapper.selectByPrimaryKey(administrators.getIid());
						System.out.println(ppassword);
						if(ppassword.equals(password)){
							status.setStatus(200);
							status.setInfo("success");
							//使用request对象的getSession()获取session，如果session不存在则创建一个
							HttpSession session = request.getSession();
							//将数据存储到session中  
					        session.setAttribute(username.toString(),password);  
					        //获取session的Id  
					        String sessionId = session.getId();  
					        //判断session是不是新创建的 
					        status.setCookie(sessionId);
					        if (session.isNew()) {  
					            System.out.println("session创建成功，session的id是："+sessionId);  
					        }else {  
					        	System.out.println("服务器已经存在该session了，session的id是："+sessionId);  
					        } 
					        onlineCheck.insertNo(sessionId, identity);
						}else{
							status.setStatus(400);
							status.setInfo("password wrong!");
						}
					}else{
						status.setStatus(400);
						status.setInfo("username not exist!");
					}	
				break;
			case 2:
				if(teacher.getIid()!=null){
					System.out.println(teacher.getIid()+"身份"+identity);
					//Person person = personMapper.selectByPrimaryKey(teacher.getIid());
					String ppasseord = personMapper.getPasswordByIid(teacher.getIid());
					System.out.println(ppasseord);
					if(ppasseord.equals(password)){
						status.setStatus(200);
						status.setInfo("success");
						//使用request对象的getSession()获取session，如果session不存在则创建一个
						HttpSession session = request.getSession();
						//将数据存储到session中  
				        session.setAttribute(username.toString(),password);  
				        //获取session的Id  
				        String sessionId = session.getId();  
				        //判断session是不是新创建的 
				        status.setCookie(sessionId);
				        if (session.isNew()) {  
				            System.out.println("session创建成功，session的id是："+sessionId);  
				        }else {  
				        	System.out.println("服务器已经存在该session了，session的id是："+sessionId);  
				        }  
				        onlineCheck.insertNo(sessionId, identity);
					}else{
						status.setStatus(400);
						status.setInfo("password wrong!");
					}
				}else{
					status.setStatus(400);
					status.setInfo("username not exist!");
				}
				break;
			case 3:
				if(student.getIid()!=null){
					System.out.println(student.getIid()+"身份"+identity);
					//Person person = personMapper.selectByPrimaryKey(student.getIid());
					String ppassword = personMapper.getPasswordByIid(student.getIid());
					System.out.println(ppassword+"in");
					System.out.println(password+"sql");
					if(ppassword.equals(password)){
						status.setStatus(200);
						status.setInfo("success");
						//使用request对象的getSession()获取session，如果session不存在则创建一个
						HttpSession session = request.getSession();
						//将数据存储到session中  
				        session.setAttribute(username.toString(),password);  
				        //获取session的Id  
				        String sessionId = session.getId();  
				        //判断session是不是新创建的 
				        status.setCookie(sessionId);
				        if (session.isNew()) {  
				            System.out.println("session创建成功，session的id是："+sessionId);  
				        }else {  
				        	System.out.println("服务器已经存在该session了，session的id是："+sessionId);  
				        }  
				        onlineCheck.insertNo(sessionId, identity);
					}else{
						status.setStatus(400);
						status.setInfo("password wrong!");
					}
				}else{
					status.setStatus(400);
					status.setInfo("username not exist!");
				}
				break;
				
			default:
				status.setStatus(400);
				status.setInfo("identity not exist!");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			status.setStatus(400);
			status.setInfo("username not exist!");
		}
		
//		response.setHeader("Access-Control-Allow-Credentials", "true");  
//		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
//		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"); 
		return status;
	}
	
	/**
	 * <p>Title: Sessionor<／p>
	 * <p>Description: 测试接口<／p>
	 */
	@RequestMapping(value = "/sessionor", method = RequestMethod.GET)
	@ResponseBody
	public Status Sessionor(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		Status status = new Status();
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			status.setStatus(200);
		}else {
			status.setStatus(400);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: Logout<／p>
	 * <p>Description: 退出<／p>
	 */
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	@ResponseBody
	public Status Logout(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie)
	{
		Status status = new Status();
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			//使当前session失效
			session.invalidate();
			onlineCheck.deleteNo(cookie);
			status.setStatus(200);
			System.out.println("退出成功！");
		}else {
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
			System.out.println("退出失败！");
		}
		
		return status;
	}
	
	/**
	 * <p>Title: OnlineNo<／p>
	 * <p>Description: 查询在线人数<／p>
	 */
	@RequestMapping(value = "/OnlineNo", method = RequestMethod.GET)
	@ResponseBody
	public OnlineNoInfo OnlineNo(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		OnlineNoInfo onlineNoInfo = new OnlineNoInfo();
		
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		//System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			
			onlineNoInfo.setStatus(200);
			onlineNoInfo.getInfo().setStudentonlineno(onlineCheck.getStudentNo());
			onlineNoInfo.getInfo().setTeacheronlineno(onlineCheck.getTeacherNo());
			System.out.println("获取在线人数成功"+onlineCheck.getStudentNo()+ " " +onlineCheck.getTeacherNo());
		}else {
			System.out.println("获取在线人数失败"+onlineCheck.getStudentNo()+ " " +onlineCheck.getTeacherNo());
			System.out.println("请求sessionid："+session.getId() + "cookie:" +cookie);
			onlineNoInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return onlineNoInfo;
	}
	
	/**
	 * <p>Title: setp<／p>
	 * <p>Description: 查看所有学院名字列表<／p>
	 */
	@RequestMapping(value = "/CollegeNameList", method = RequestMethod.GET)
	@ResponseBody
	public CollegeListInfo getCollegeName(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		CollegeListInfo stringListInfo = new CollegeListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CollegeMapper collegeMapper = sqlSession.getMapper(CollegeMapper.class); 
				System.out.println("学院数量："+collegeMapper.selectAll().size());
				List<College> colleges = collegeMapper.selectAll();
				for (int i = 0; i < colleges.size(); i++){
					CollegeName collegeName = new CollegeName();
					collegeName.setCollegeid(colleges.get(i).getIcollegeid());
					collegeName.setCollegename(colleges.get(i).getScollegename());
					stringListInfo.getInfo().add(collegeName);
				}
				stringListInfo.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				stringListInfo.setStatus(400);
			}
		} else {
			stringListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return stringListInfo;
	}
	
	
	/**
	 * <p>Title: getMajorName<／p>
	 * <p>Description:查看指定学院的专业列表 <／p>
	 */
	@RequestMapping(value = "/MajorNameList", method = RequestMethod.GET)
	@ResponseBody
	public MajorListInfo getMajorName(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "collegeid") BigDecimal collegeid){
		MajorListInfo majorListInfo = new MajorListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				MajorMapper majorMapper = sqlSession.getMapper(MajorMapper.class);
				System.out.println("专业数量："+majorMapper.selectByCollegeId(collegeid).size());
				List<Major> majors = majorMapper.selectByCollegeId(collegeid);
				for (int i = 0; i < majors.size(); i++) {
					MajorName majorName = new MajorName();
					majorName.setMajorname(majors.get(i).getSmajorname());
					majorName.setMajorid(majors.get(i).getImajorid());
					majorListInfo.getInfo().add(majorName);
				}
				majorListInfo.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				majorListInfo.setStatus(400);
				System.out.println("youwenti");
			}
		} else {
			majorListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return majorListInfo;
	}
	
	/**
	 * <p>Title: getAdclassList<／p>
	 * <p>Description:查看指定专业的行政班列表 <／p>
	 */
	@RequestMapping(value = "/ADClassNameList", method = RequestMethod.GET)
	@ResponseBody
	public ADClassListInfo getAdclassList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "majorid") BigDecimal majorid){
		ADClassListInfo adClassListInfo = new ADClassListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if(session.getId().equals(cookie)){
			try {
				System.out.println("asd1");
				AdclassMapper adclassMapper = sqlSession.getMapper(AdclassMapper.class);
				System.out.println("asd");
				List<Adclass> adclasses = adclassMapper.selectAll();
				System.out.println(adclassMapper.selectAll().size());
				for (int i = 0; i < adclasses.size(); i++) {
					ADClassName aClassName = new ADClassName();
					if(adclasses.get(i).getImajorid().equals(majorid)){
						aClassName.setADClassname(adclasses.get(i).getSclassname());
						aClassName.setADClassid(adclasses.get(i).getIclassid());
						adClassListInfo.getInfo().add(aClassName);
					}
				}
				adClassListInfo.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				adClassListInfo.setStatus(400);
				System.out.println("youwenti");
			}
		}else{
			adClassListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return adClassListInfo;
	}
	/**
	 * <p>Title: SelectOneStudent<／p>
	 * <p>Description: 学生详情<／p>
	 */
	@RequestMapping(value = "/SelectOneStudent")
	@ResponseBody
	public StudentDetailInfo SelectOneStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "stuid") BigDecimal stuid){
		StudentDetailInfo studentDetailInfo = new StudentDetailInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				CollegeMapper collegeMapper = sqlSession.getMapper(CollegeMapper.class);
				MajorMapper majorMapper = sqlSession.getMapper(MajorMapper.class);
				AdclassMapper adclassMapper = sqlSession.getMapper(AdclassMapper.class);

				Student student = studentMapper.selectByPrimaryKey(stuid);
				Adclass adclass = adclassMapper.selectByPrimaryKey(student.getIclassid());
				Person person = personMapper.selectByPrimaryKey(student.getIid());
				Major major = majorMapper.selectByPrimaryKey(adclass.getImajorid());
				College college = collegeMapper.selectByPrimaryKey(major.getIcollegeid());
				
				studentDetailInfo.setStuid(student.getIstudentid());
				studentDetailInfo.setStuname(person.getSname());
				studentDetailInfo.setStuclass(adclass.getSclassname());
				studentDetailInfo.setStucollege(college.getScollegename());
				studentDetailInfo.setStumajor(major.getSmajorname());
				studentDetailInfo.setStuphoto(person.getSpageaddress());
				studentDetailInfo.setStuphoneno(person.getNcellphone());
				studentDetailInfo.setStumail(person.getSemail());
				studentDetailInfo.setSgender(person.getCgender());
				studentDetailInfo.setSage(person.getIage());
				studentDetailInfo.setSaddress(person.getSplaceaddress());
				
				studentDetailInfo.setStatus(200);
				System.out.println("学生详情成功");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("学生详情error");				
				studentDetailInfo.setStatus(400);
			}
		} else {
			System.out.println("学生详情time out");		
			studentDetailInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}

		return studentDetailInfo;
	}
	/**
	 * <p>Title: SelectOneTeacher<／p>
	 * <p>Description:教师详情 <／p>
	 */
	@RequestMapping(value = "/SelectOneTeacher")
	@ResponseBody
	public TeacherDetailInfo SelectOneTeacher(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid){
		TeacherDetailInfo teacherDetailInfo = new TeacherDetailInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession(true);
		
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				CollegeMapper collegeMapper = sqlSession.getMapper(CollegeMapper.class);
				
				Teacher teacher = teacherMapper.selectByPrimaryKey(teaid);
				Person person = personMapper.selectByPrimaryKey(teacher.getIid());
				College college = collegeMapper.selectByPrimaryKey(teacher.getIcollegeid());
				
				teacherDetailInfo.setTaddress(person.getSplaceaddress());
				teacherDetailInfo.setTage(person.getIage());
				teacherDetailInfo.setTeacollege(college.getScollegename());
				teacherDetailInfo.setTeaid(teacher.getIteacheid());
				teacherDetailInfo.setTeamail(person.getSemail());
				teacherDetailInfo.setTeaname(person.getSname());
				teacherDetailInfo.setTeaphoneno(person.getNcellphone());
				teacherDetailInfo.setTeaphoto(person.getSpageaddress());
				teacherDetailInfo.setTeatitle(teacher.getStitle());
				teacherDetailInfo.setTgender(person.getCgender());
				
				teacherDetailInfo.setStatus(200);
				teacherDetailInfo.setInfo("success");
				
				System.out.println("教师详情success");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("教师详情error");	
				teacherDetailInfo.setStatus(400);
			}
		} else {
			System.out.println("教师详情time out");	
			teacherDetailInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		
		return teacherDetailInfo;
	}
	
	/**
	 * <p>Title: SelectStudent<／p>
	 * <p>Description: 模糊查询学生<／p>
	 */
	@RequestMapping(value = "/SelectStudent")
	@ResponseBody
	public StudentListInfo SelectStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		StudentListInfo studentListInfo = new StudentListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			try {
				List<StudentListInfoItem> studentListInfoItems = studentMapper.getStudentList(context);
				System.out.println(studentListInfoItems.size());
				
				for (int i = 0; i < studentListInfoItems.size(); i++) {
					StudentListItem studentListItem = new StudentListItem();
					studentListItem.setStuclass(studentListInfoItems.get(i).getSclassname());
					studentListItem.setStucollege(studentListInfoItems.get(i).getScollegename());
					studentListItem.setStuid(studentListInfoItems.get(i).getIstudentid());
					studentListItem.setStumajor(studentListInfoItems.get(i).getSmajorname());
					studentListItem.setStuname(studentListInfoItems.get(i).getSname());
					studentListInfo.getInfo().add(studentListItem);
				}
				studentListInfo.setStatus(200);
				System.out.println("模糊查询学生成功");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("模糊查询学生error");				
				studentListInfo.setStatus(400);
			}
		} else {
			System.out.println("学生模糊time out");	
			studentListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return studentListInfo;
	}
	/**
	 * <p>Title: SelectTeacher<／p>
	 * <p>Description: 模糊查询教师<／p>
	 */
	@RequestMapping(value = "/SelectTeacher")
	@ResponseBody
	public TeacherListInfo SelectTeacher(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "context") String context){
		TeacherListInfo teacherListInfo = new TeacherListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
			try {
				List<TeacherListInfoItem> teacherListInfoItems = teacherMapper.getTeacherList(context);
				System.out.println(teacherListInfoItems.size());
				teacherListInfo.setInfo(teacherListInfoItems);
				
				System.out.println("模糊查询教师success");				
				teacherListInfo.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("模糊查询教师error");				
				teacherListInfo.setStatus(400);
			}
		} else {
			System.out.println("教师模糊time out");	
			teacherListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return teacherListInfo;
	}
	
	/**
	 * <p>Title: SelectCampus<／p>
	 * <p>Description:查看校区 <／p>
	 */
	@RequestMapping(value = "/SelectCampus")
	@ResponseBody
	private CampusListinfo SelectCampus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie){
		CampusListinfo info = new CampusListinfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CampusMapper campusMapper = sqlSession.getMapper(CampusMapper.class);
				info.setInfo(campusMapper.selectAll());
				
				System.out.println("查看校区success");	
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查看校区error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("查看校区time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return info;
	}
	/**
	 * <p>Title: SelectAcademicbuilding<／p>
	 * <p>Description:根据校区查教学楼 <／p>
	 */
	@RequestMapping(value = "/SelectAcademicbuilding")
	@ResponseBody
	public AcademicbuildingListInfo SelectAcademicbuilding(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "icampusid") BigDecimal icampusid){
		AcademicbuildingListInfo info = new AcademicbuildingListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				AcademicbuildingMapper academicbuildingMapper = sqlSession.getMapper(AcademicbuildingMapper.class);
				
				info.setInfo(academicbuildingMapper.selectByCampusId(icampusid));
				System.out.println("查教学楼success");	
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查教学楼error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("查教学楼time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return info;
	}
	
	/**
	 * <p>Title: SelectClassroom<／p>
	 * <p>Description: 根据教学楼查教室<／p>
	 */
	@RequestMapping(value = "/SelectClassroom")
	@ResponseBody
	public ClassroomListInfo SelectClassroom(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "iacademicbuildingid") BigDecimal iacademicbuildingid){
		ClassroomListInfo info = new ClassroomListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				ClassroomMapper classroomMapper = sqlSession.getMapper(ClassroomMapper.class);
				info.setInfo(classroomMapper.selectByIacademicbuildingId(iacademicbuildingid));
				
				System.out.println("查教室成功");	
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查教室error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("查教室time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return info;
	}
	
	/**
	 * <p>Title: SelectScoreH<／p>
	 * <p>Description:查看课程分数占比 <／p>
	 */
	@RequestMapping(value = "/SelectScoreH")
	@ResponseBody
	public StatusObjectT<ClassScoreH> SelectScoreH(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couclassid") BigDecimal couclassid){
		StatusObjectT<ClassScoreH> status = new StatusObjectT<ClassScoreH>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				status.setInfo(courseTMapper.selectScoreHByPrimaryKey(couclassid));
				System.out.println("查看课程分数占比 success");	
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查看课程分数占比 error");	
				status.setStatus(400);
			}
		}else{
			System.out.println("查看课程分数占比 time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	/**
	 * <p>Title: SendMessage<／p>
	 * <p>Description: 写留言<／p>
	 */
	@RequestMapping(value = "/SendMessage")
	@ResponseBody
	public Status SendMessage(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "username") BigDecimal username,
			@RequestParam(value = "identity") int identity,@RequestParam(value = "context") String  context,
			@RequestParam(value = "couid") BigDecimal couid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
			
			Administrators administrators = administratorsMapper.SelectByUsername(username);
			Student student = studentMapper.selectByPrimaryKey(username);
			Teacher teacher = teacherMapper.selectByPrimaryKey(username);
			
			InsertLeaveMessage message = new InsertLeaveMessage();
			message.setMessage(context);
			message.setTeachclassID(couid);
			
			switch (identity) {
			case 1:
				if(administrators!=null){
					message.setIisd(administrators.getIid());
					Date d = new Date();
					System.out.println(d);
					message.setUdate(d);
					administratorsMapper.PleaveMessage(message);
					status.setStatus(200);
				}
				break;
			case 2:
				if(teacher!=null){
					message.setIisd(teacher.getIid());
					Date d = new Date();
					System.out.println(d);
					message.setUdate(d);
					administratorsMapper.PleaveMessage(message);
					status.setStatus(200);
				}
				break;
			case 3:
				if(student!=null){
					message.setIisd(student.getIid());
					Date d = new Date();
					System.out.println(d);
					message.setUdate(d);
					administratorsMapper.PleaveMessage(message);
					status.setStatus(200);
				}
				break;
			default:
				System.out.println("写留言error");	
				status.setStatus(400);
				break;
				
			}
		} else {
			System.out.println("写留言time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: SelectMessage<／p>
	 * <p>Description: 查看留言<／p>
	 */
	@RequestMapping(value = "/SelectMessage")
	@ResponseBody
	public StatusListT<VLeaveMessage> SelectMessage(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") BigDecimal couid){
		StatusListT<VLeaveMessage> status = new StatusListT<VLeaveMessage>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				List<VLeaveMessage> list = administratorsMapper.getleaveMessage(couid);
				StudentRank.lqsort(list, 0, list.size()-1);
				status.setInfo(list);
				System.out.println("看留言success");	
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("看留言error");	
				status.setStatus(400);
			}
		} else {
			System.out.println("看留言time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	/**
	 * <p>Title: DeleteMessage<／p>
	 * <p>Description: 删除留言<／p>
	 */
	@RequestMapping(value = "/DeleteMessage")
	@ResponseBody
	public Status DeleteMessage(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "mid") BigDecimal mid){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				administratorsMapper.PdeleteMessage(mid);
				
				System.out.println("删除留言success");	
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("删除留言error");	
				status.setStatus(400);
			}
		} else {
			System.out.println("删除留言time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: UpdatePersonInfo<／p>
	 * <p>Description: 修改个人信息<／p>
	 */
	@RequestMapping(value = "/UpdatePersonInfo")
	@ResponseBody
	public Status UpdatePersonInfo(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie,@RequestParam(value = "username") BigDecimal username, @RequestParam(value = "gender") String gender,
			@RequestParam(value = "age") BigDecimal age, @RequestParam(value = "cellphone") Long cellphone, @RequestParam(value = "identity") int identity,
			@RequestParam(value = "address") String address,@RequestParam(value = "email") String email,@RequestParam(value = "photo") String photo){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				Administrators administrators = administratorsMapper.SelectByUsername(username);
				Student student = studentMapper.selectByPrimaryKey(username);
				Teacher teacher = teacherMapper.selectByPrimaryKey(username);
				
				Person person = new Person();
				
				switch (identity) {
				case 1:
					if (administrators!=null) {
						person = personMapper.selectByPrimaryKey(administrators.getIid());
						person.setCgender(gender);
						person.setIage(age);
						person.setNcellphone(cellphone);
						person.setSplaceaddress(address);
						person.setSemail(email);
						person.setSpageaddress(photo);
						personMapper.updateByPrimaryKey(person);
						sqlSession.commit();
						System.out.println("删除留言sucess");	
						status.setStatus(200);
					}
					break;
				case 2:
					if (teacher!=null) {
						person = personMapper.selectByPrimaryKey(teacher.getIid());
						person.setCgender(gender);
						person.setIage(age);
						person.setNcellphone(cellphone);
						person.setSplaceaddress(address);
						person.setSemail(email);
						person.setSpageaddress(photo);
						personMapper.updateByPrimaryKey(person);
						sqlSession.commit();
						System.out.println("修改个人信息sucess");	
						status.setStatus(200);
					}
					break;
				case 3:
					if (student!=null) {
						person = personMapper.selectByPrimaryKey(student.getIid());
						person.setCgender(gender);
						person.setIage(age);
						person.setNcellphone(cellphone);
						person.setSplaceaddress(address);
						person.setSemail(email);
						person.setSpageaddress(photo);
						personMapper.updateByPrimaryKey(person);
						sqlSession.commit();
						System.out.println("修改个人信息sucess");	
						status.setStatus(200);
					}
					break;
				default:
					System.out.println("修改个人信息error");	
					status.setStatus(400);
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("修改个人信息error");	
				status.setStatus(400);
			}
		} else {
			System.out.println("修改个人信息time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: SendYHFK<／p>
	 * <p>Description: 用户反馈<／p>
	 */
	@RequestMapping(value = "/SendYHFK")
	@ResponseBody
	public Status SendYHFK(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie,@RequestParam(value = "username") BigDecimal username,
			@RequestParam(value = "context") String context,@RequestParam(value = "identity") int identity,
			@RequestParam(value = "title") String title){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				Administrators administrators = administratorsMapper.SelectByUsername(username);
				Student student = studentMapper.selectByPrimaryKey(username);
				Teacher teacher = teacherMapper.selectByPrimaryKey(username);
				
				InsertYHFKInfo info = new InsertYHFKInfo();
				switch (identity) {
				case 1:
					if (administrators!=null) {
						Date date = new Date();
						info.setIids(administrators.getIid());
						info.setMessages(context);
						info.setTimes(date);
						info.setStitle(title);
						administratorsMapper.PaddYHFK(info);
						sqlSession.commit();
						System.out.println("用户反馈success");	
						status.setStatus(200);
					}
					break;
				case 2:
					if (teacher!=null) {
						Date date = new Date();
						info.setIids(teacher.getIid());
						info.setMessages(context);
						info.setTimes(date);
						info.setStitle(title);
						administratorsMapper.PaddYHFK(info);
						sqlSession.commit();
						System.out.println("用户反馈success");	
						status.setStatus(200);
					}
					break;
				case 3:
					if (student!=null) {
						Date date = new Date();
						info.setIids(student.getIid());
						info.setMessages(context);
						info.setTimes(date);
						info.setStitle(title);
						administratorsMapper.PaddYHFK(info);
						sqlSession.commit();
						System.out.println("用户反馈success");	
						status.setStatus(200);
					}
					break;
				default:
					System.out.println("用户反馈error");	
					status.setStatus(400);
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("用户反馈error");	
				status.setStatus(400);
			}
			
		} else {
			System.out.println("用户反馈time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	@RequestMapping(value = "/GetYHFK")
	@ResponseBody
	public StatusListT<Vyhfk> GetYHFK(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		StatusListT<Vyhfk> status = new StatusListT<Vyhfk>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				AdministratorsMapper administratorsMapper = sqlSession.getMapper(AdministratorsMapper.class);
				
				List<Vyhfk> list = administratorsMapper.getyhfkMessage();
				System.out.println(list.size());
				StudentRank.yqsort(list, 0, list.size()-1);
				status.setInfo(list);
				System.out.println("查看用户反馈success");	
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("查看用户反馈error");	
				status.setStatus(400);
			}
		} else {
			System.out.println("查看用户反馈time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	
	/**
	 * <p>Title: CheckClassUsing<／p>
	 * <p>Description: 检查教室使用情况<／p>
	 */
	@RequestMapping(value = "/CheckClassUsing")
	@ResponseBody
	public StatusListT<Classtimetale> CheckClassUsing(HttpServletRequest request, @RequestParam(value = "cookie") String cookie,
			@RequestParam(value = "week") BigDecimal week, @RequestParam(value = "section") BigDecimal section){
		StatusListT<Classtimetale> status = new StatusListT<Classtimetale>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		Map<String, BigDecimal> map = new HashMap<>();
		map.put("iweek", week);
		map.put("isection", section);
		if (session.getId().equals(cookie)) {
			try {
				ClasstimetaleMapper classtimetaleMapper = sqlSession.getMapper(ClasstimetaleMapper.class);
				List<Classtimetale> list = classtimetaleMapper.CheckClassroomtime(map);
	            for (int i = 0; i <list.size()-1; i++) {
	                for (int j = list.size()-1; j >i; j--) {
	                    if (list.get(j).getIclassroomid().equals(list.get(i).getIclassroomid())) {
	                        list.remove(j);
	                    }
	                }
	            }
	            status.setInfo(list);
	            System.out.println("检查教室使用情况success");	
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("检查教室使用情况time out");	
				status.setStatus(400);
			}
		} else {
			System.out.println("检查教室使用情况time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: UploadPhote<／p>
	 * <p>Description: 上传照片<／p>
	 */
	@RequestMapping(value = "/UploadPhoto")
	@ResponseBody
	public StatusObjectT<PicPath> UploadPhote(HttpServletRequest request, @RequestParam(value = "cookie") String cookie,
			@RequestParam(value = "username") BigDecimal username, @RequestParam("imgFile") MultipartFile file){
		StatusObjectT<PicPath> status = new StatusObjectT<PicPath>();
		HttpSession session = request.getSession();
		//SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		//String cookie = setPhotoInfo.getCookie();
		
		if (session.getId().equals(cookie)) {
			try {
				String oldFileName = file.getOriginalFilename(); // 获取上传文件的原名
				// 获取图片后缀
	            String suffix = oldFileName.substring(oldFileName.lastIndexOf(".")); 
				System.out.println(oldFileName);
				String saveFilePath = "/Users/xuchongtian/Documents/workspace/studentgrade/src/main/resources/image";
				if (file!=null&&oldFileName!=null&oldFileName.length()>0) {
					String newFileName = username.toString()+suffix;
					File newFile = new File(saveFilePath + "\\" + newFileName);
					file.transferTo(newFile);
					String url = "http://192.168.1.187:8080/pic/"+newFileName;
					PicPath path = new PicPath();
					path.setPhotoaddress(url);
					status.setInfo(path);
					System.out.println("上传照片success");	
					status.setStatus(200);
				} else {
					System.out.println("上传照片fail");	
					status.setStatus(400);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("上传照片error");	
				status.setStatus(400);
			}
		} else {
			System.out.println("上传照片time out");	
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
}
