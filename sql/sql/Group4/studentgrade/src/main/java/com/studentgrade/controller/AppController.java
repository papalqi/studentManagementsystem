/**
 * <p>Title: AppController.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��9��
 * @version 1.0
 */
package com.studentgrade.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.backinfo.ADClassListInfo;
import com.studentgrade.backinfo.ADClassListInfo.ADClassName;
import com.studentgrade.backinfo.AcademicbuildingListInfo;
import com.studentgrade.backinfo.CampusListinfo;
import com.studentgrade.backinfo.ClassroomListInfo;
import com.studentgrade.backinfo.CollegeListInfo;
import com.studentgrade.backinfo.MajorListInfo;
import com.studentgrade.backinfo.CollegeListInfo.CollegeName;
import com.studentgrade.backinfo.MajorListInfo.MajorName;
import com.studentgrade.backinfo.StudentDetailInfo;
import com.studentgrade.backinfo.StudentListInfo;
import com.studentgrade.backinfo.StudentListInfo.StudentListItem;
import com.studentgrade.backinfo.TeacherDetailInfo;
import com.studentgrade.backinfo.TeacherListInfo;
import com.studentgrade.bean.Adclass;
import com.studentgrade.bean.Administrators;
import com.studentgrade.bean.College;
import com.studentgrade.bean.Major;
import com.studentgrade.bean.OnlineNoInfo;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Student;
import com.studentgrade.bean.Teacher;
import com.studentgrade.dao.AcademicbuildingMapper;
import com.studentgrade.dao.AdclassMapper;
import com.studentgrade.dao.AdministratorsMapper;
import com.studentgrade.dao.CampusMapper;
import com.studentgrade.dao.ClassroomMapper;
import com.studentgrade.dao.CollegeMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.MajorMapper;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.StudentMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.model.StudentListInfoItem;
import com.studentgrade.model.TeacherListInfoItem;

/**
 * <p>Title: AppController<��p>
 * <p>Description: ������������ǰ�˷�������,ͨ������<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��9��
 */
@Controller
public class AppController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	/**
	 * <p>Title: Login<��p>
	 * <p>Description: ��¼�ӿ�<��p>
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	@ResponseBody
	public Status Login(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "username") BigDecimal username,
			@RequestParam(value = "password") String password, @RequestParam(value = "identity") int identity){
		Status status = new Status();
		
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
//		try {
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
						System.out.println(administrators.getIid()+"���"+identity);
						String ppassword = personMapper.getPasswordByIid(administrators.getIid());
						//Person person = personMapper.selectByPrimaryKey(administrators.getIid());
						System.out.println(ppassword);
						if(ppassword.equals(password)){
							status.setStatus(200);
							status.setInfo("success");
							//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
							HttpSession session = request.getSession();
							//�����ݴ洢��session��  
					        session.setAttribute(username.toString(),password);  
					        //��ȡsession��Id  
					        String sessionId = session.getId();  
					        //�ж�session�ǲ����´����� 
					        status.setCookie(sessionId);
					        if (session.isNew()) {  
					            System.out.println("session�����ɹ���session��id�ǣ�"+sessionId);  
					        }else {  
					        	System.out.println("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);  
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
					System.out.println(teacher.getIid()+"���"+identity);
					//Person person = personMapper.selectByPrimaryKey(teacher.getIid());
					String ppasseord = personMapper.getPasswordByIid(teacher.getIid());
					System.out.println(ppasseord);
					if(ppasseord.equals(password)){
						status.setStatus(200);
						status.setInfo("success");
						//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
						HttpSession session = request.getSession();
						//�����ݴ洢��session��  
				        session.setAttribute(username.toString(),password);  
				        //��ȡsession��Id  
				        String sessionId = session.getId();  
				        //�ж�session�ǲ����´����� 
				        status.setCookie(sessionId);
				        if (session.isNew()) {  
				            System.out.println("session�����ɹ���session��id�ǣ�"+sessionId);  
				        }else {  
				        	System.out.println("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);  
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
					System.out.println(student.getIid()+"���"+identity);
					//Person person = personMapper.selectByPrimaryKey(student.getIid());
					String ppassword = personMapper.getPasswordByIid(student.getIid());
					System.out.println(ppassword);
					if(ppassword.equals(password)){
						status.setStatus(200);
						status.setInfo("success");
						//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
						HttpSession session = request.getSession();
						//�����ݴ洢��session��  
				        session.setAttribute(username.toString(),password);  
				        //��ȡsession��Id  
				        String sessionId = session.getId();  
				        //�ж�session�ǲ����´����� 
				        status.setCookie(sessionId);
				        if (session.isNew()) {  
				            System.out.println("session�����ɹ���session��id�ǣ�"+sessionId);  
				        }else {  
				        	System.out.println("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);  
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

//		} catch (Exception e) {
//			// TODO: handle exception
//			status.setStatus(400);
//			status.setInfo("username not exist!");
//		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"); 
		return status;
	}
	
	/**
	 * <p>Title: Sessionor<��p>
	 * <p>Description: ���Խӿ�<��p>
	 */
	@RequestMapping(value = "/sessionor", method = RequestMethod.GET)
	@ResponseBody
	public Status Sessionor(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		Status status = new Status();
		//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			status.setStatus(200);
		}else {
			status.setStatus(400);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return status;
	}
	
	/**
	 * <p>Title: Logout<��p>
	 * <p>Description: �˳�<��p>
	 */
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	@ResponseBody
	public Status Logout(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie)
	{
		Status status = new Status();
		//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			//ʹ��ǰsessionʧЧ
			session.invalidate();
			onlineCheck.deleteNo(cookie);
			status.setStatus(200);
			System.out.println("�˳��ɹ���");
		}else {
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
			System.out.println("�˳�ʧ�ܣ�");
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return status;
	}
	
	/**
	 * <p>Title: OnlineNo<��p>
	 * <p>Description: ��ѯ��������<��p>
	 */
	@RequestMapping(value = "/OnlineNo", method = RequestMethod.GET)
	@ResponseBody
	public OnlineNoInfo OnlineNo(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie){
		OnlineNoInfo onlineNoInfo = new OnlineNoInfo();
		
		//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
		HttpSession session = request.getSession();
		//System.out.println(session.getId());
		if(session.getId().equals(cookie)){
			
			onlineNoInfo.setStatus(200);
			onlineNoInfo.getInfo().setStudentonlineno(onlineCheck.getStudentNo());
			onlineNoInfo.getInfo().setTeacheronlineno(onlineCheck.getTeacherNo());
			System.out.println("��ȡ���������ɹ�"+onlineCheck.getStudentNo()+ " " +onlineCheck.getTeacherNo());
		}else {
			System.out.println("��ȡ��������ʧ��"+onlineCheck.getStudentNo()+ " " +onlineCheck.getTeacherNo());
			System.out.println("����sessionid��"+session.getId() + "cookie:" +cookie);
			onlineNoInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return onlineNoInfo;
	}
	
	/**
	 * <p>Title: setp<��p>
	 * <p>Description: �鿴����ѧԺ�����б�<��p>
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
				System.out.println("ѧԺ������"+collegeMapper.selectAll().size());
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return stringListInfo;
	}
	
	
	/**
	 * <p>Title: getMajorName<��p>
	 * <p>Description:�鿴ָ��ѧԺ��רҵ�б� <��p>
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
				System.out.println("רҵ������"+majorMapper.selectByCollegeId(collegeid).size());
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return majorListInfo;
	}
	
	/**
	 * <p>Title: getAdclassList<��p>
	 * <p>Description:�鿴ָ��רҵ���������б� <��p>
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
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return adClassListInfo;
	}
	/**
	 * <p>Title: SelectOneStudent<��p>
	 * <p>Description: ѧ������<��p>
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
				System.out.println("ѧ������ɹ�");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ѧ������error");				
				studentDetailInfo.setStatus(400);
			}
		} else {
			System.out.println("ѧ������time out");		
			studentDetailInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return studentDetailInfo;
	}
	/**
	 * <p>Title: SelectOneTeacher<��p>
	 * <p>Description:��ʦ���� <��p>
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
				
				System.out.println("��ʦ����success");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ʦ����error");	
				teacherDetailInfo.setStatus(400);
			}
		} else {
			System.out.println("��ʦ����time out");	
			teacherDetailInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return teacherDetailInfo;
	}
	
	/**
	 * <p>Title: SelectStudent<��p>
	 * <p>Description: ģ����ѯѧ��<��p>
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
				System.out.println("ģ����ѯѧ���ɹ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ģ����ѯѧ��error");				
				studentListInfo.setStatus(400);
			}
		} else {
			System.out.println("ѧ��ģ��time out");	
			studentListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return studentListInfo;
	}
	/**
	 * <p>Title: SelectTeacher<��p>
	 * <p>Description: ģ����ѯ��ʦ<��p>
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
				
				System.out.println("ģ����ѯ��ʦsuccess");				
				teacherListInfo.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ģ����ѯ��ʦerror");				
				teacherListInfo.setStatus(400);
			}
		} else {
			System.out.println("��ʦģ��time out");	
			teacherListInfo.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return teacherListInfo;
	}
	
	/**
	 * <p>Title: SelectCampus<��p>
	 * <p>Description:�鿴У�� <��p>
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
				
				System.out.println("�鿴У��success");	
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴У��error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("�鿴У��time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return info;
	}
	/**
	 * <p>Title: SelectAcademicbuilding<��p>
	 * <p>Description:����У�����ѧ¥ <��p>
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
				System.out.println("���ѧ¥success");	
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���ѧ¥error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("���ѧ¥time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return info;
	}
	
	/**
	 * <p>Title: SelectClassroom<��p>
	 * <p>Description: ���ݽ�ѧ¥�����<��p>
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
				
				System.out.println("���ѧ¥error");	
				info.setStatus(1200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���ѧ¥error");	
				info.setStatus(400);
			}
		} else {
			System.out.println("�����time out");	
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");  
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		return info;
	}
	
	
}
