/**
 * <p>Title: TeacherController.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��14��
 * @version 1.0
 */
package com.studentgrade.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.backinfo.GetStudentGradeListInfo;
import com.studentgrade.backinfo.StatusListT;
import com.studentgrade.backinfo.TeacherCourseTableInfo;
import com.studentgrade.bean.Classtimetale;
import com.studentgrade.bean.CourseA;
import com.studentgrade.bean.CourseT;
import com.studentgrade.bean.Person;
import com.studentgrade.bean.Status;
import com.studentgrade.bean.Teacher;
import com.studentgrade.bean.Vxueshengkpaiming;
import com.studentgrade.dao.ClasstimetaleMapper;
import com.studentgrade.dao.CourseAMapper;
import com.studentgrade.dao.CourseSMapper;
import com.studentgrade.dao.CourseTMapper;
import com.studentgrade.dao.DatabaseSource;
import com.studentgrade.dao.PersonMapper;
import com.studentgrade.dao.TeacherMapper;
import com.studentgrade.method.OnlineCheck;
import com.studentgrade.method.OutputExcel;
import com.studentgrade.model.OneCoursePlaceTime;
import com.studentgrade.model.OpenCourseInfo;
import com.studentgrade.model.OpenCourseListInfoItem;
import com.studentgrade.postinfo.SetClassGradeInfo;
import com.studentgrade.postinfo.TeacherOpenCourseInfo;

/**
 * <p>Title: TeacherController<��p>
 * <p>Description: <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��14��
 */
@Controller
public class TeacherController {
	
	OnlineCheck onlineCheck = OnlineCheck.newInstance();
	
	/**
	 * <p>Title: ChangeTeacherPassword<��p>
	 * <p>Description: �޸����루��ʦ��<��p>
	 */
	@RequestMapping(value = "/ChangeTeacherPassword")
	@ResponseBody
	public Status ChangeTeacherPassword(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "username") BigDecimal username, 
			@RequestParam(value = "newpassword") String newpassword, @RequestParam(value = "oldpassword") String oldpassword){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		System.out.println(username+"  "+newpassword+"  "+oldpassword);
		if (session.getId().equals(cookie)) {
			try {
				PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
				TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
				
				Teacher teacher = teacherMapper.selectByPrimaryKey(username);
				if (teacher.getIid()!=null) {
					Person person = personMapper.selectByPrimaryKey(teacher.getIid());
					if (person.getSpassword().equals(oldpassword)) {
						person.setSpassword(newpassword); //����������
						try {
							personMapper.updateByPrimaryKeySelective(person);
							sqlSession.commit();
							System.out.println("�޸����루��ʦ����Ϣ�ɹ�");
						
							status.setStatus(200);
							status.setInfo("success");
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("�޸����루��ʦ����Ϣû���ҵ��˺�");
							
							status.setStatus(400);
							status.setInfo("error1");
						}
					} else {
						System.out.println("�޸����루��ʦ�����������");
						
						status.setStatus(400);
						status.setInfo("oldpassword not equal");
					}
				} else {
					System.out.println("�޸����루��ʦ����Ϣû���ҵ��˺�");
					
					status.setStatus(400);
					status.setInfo("fail");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸����루��ʦ��error");
				
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸����루��ʦ����Ϣ��ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		
		return status;
	}
	
	/**
	 * <p>Title: InsertTeacherCourse<��p>
	 * <p>Description:��ʦ���� <��p>
	 */
	@RequestMapping(value = "/InsertTeacherCourse", method = RequestMethod.POST)
	@ResponseBody
	public Status InsertTeacherCourse(HttpServletResponse response, HttpServletRequest request,@RequestBody TeacherOpenCourseInfo info){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		System.out.println(info.toString());
		String cookie = info.getCookie();
		if (session.getId().equals(cookie)) {
			try {
				
				synchronized (this) {
					CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
					CourseAMapper courseAMapper = sqlSession.getMapper(CourseAMapper.class);
					
					CourseA courseA = courseAMapper.selectByPrimaryKey(info.getCurriculumID());
					BigDecimal aclass = courseA.getInowscournumber();
					BigDecimal bclass = courseA.getIscournumber();
					if (aclass.intValue() >= bclass.intValue()) {
						status.setStatus(401);
						System.out.println("����fail");	
					} else {
						
						Map<String, BigDecimal> map = new HashMap<>();
						map.put("iweek", info.getTimelist().get(0).getWeek());
						map.put("isection", info.getTimelist().get(0).getSsection());
						ClasstimetaleMapper classtimetaleMapper = sqlSession.getMapper(ClasstimetaleMapper.class);
						List<Classtimetale> list = classtimetaleMapper.CheckClassroomtime(map);
						
						List<OpenCourseListInfoItem> list77 = courseTMapper.selectTeacherCourseByIteachId(info.getTeacherID());
			            for (int i = 0; i <list.size()-1; i++) {
			                for (int j = list.size()-1; j >i; j--) {
			                    if (list.get(j).getIclassroomid().equals(list.get(i).getIclassroomid())) {
			                        list.remove(j);
			                    }
			                }
			            }
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getIclassroomid().equals(info.getTimelist().get(0).getClassroomid())) {
								status.setStatus(402);
								System.out.println("���ν���fail");	
								return status;
							}
						}
						
						if (list77!=null && list77.size()>0) {
							
							for (int j = 0; j <list.size(); j++) {
								for (int i = 0; i < list77.size(); i++) {
									if (!list.get(j).getIteachclassid().equals(list77.get(i).getIteachclassid())) {
										System.out.println(list77.get(i).getIteachclassid()+"   ppe");
										System.out.println(list.get(j).getIteachclassid()+"   qwe");
										list.remove(j);
									}
								}
							}
							
							for (int i = 0; i < list.size(); i++) {
								if (list.get(i).getIweek().equals(info.getTimelist().get(0).getWeek())) {
									if (list.get(i).getIsection().equals(info.getTimelist().get(0).getSsection())) {
										System.out.println(list.get(i).getIteachclassid());
										System.out.println(list.get(i).getIweek()+"  "+info.getTimelist().get(0).getWeek());
										System.out.println(list.get(i).getIsection()+"  "+info.getTimelist().get(0).getSsection());
										status.setStatus(403);
										System.out.println("���ν���fail");	
										return status;
									}
								}
								
								if (list.get(i).getIweek().equals(info.getTimelist().get(1).getWeek())) {
									if (list.get(i).getIsection().equals(info.getTimelist().get(1).getSsection())) {
										System.out.println(list.get(i).getIweek()+"  "+info.getTimelist().get(1).getWeek());
										System.out.println(list.get(i).getIsection()+"  "+info.getTimelist().get(1).getSsection());
										status.setStatus(403);
										System.out.println("���ν���fail");	
										return status;
									}
								}
							}							
						}
						

						//System.out.println(info.getCourseInfo().getAdclassID());
						System.out.println(info.getCurriculumID());
						//info.getCourseInfo().setCurriculumID(info.getCurriculumID());
						//courseTMapper.Paddcourse(info.getCourseInfo());
						OpenCourseInfo courseInfo = new OpenCourseInfo();
						courseInfo.setCurriculumID(info.getCurriculumID());
						courseInfo.setMaxNumber(info.getMaxNumber());
						courseInfo.setTeacherID(info.getTeacherID());
						courseTMapper.Paddcourse(courseInfo);
						System.out.println(info.getTeacherID());
						BigDecimal AdclassID = courseInfo.getAdclassID();
						//System.out.println(info.getCourseInfo().getAdclassID());
						//System.out.println(info.getCourseInfo().getCurriculumID());
						System.out.println(info.getTimelist());
						for (int i = 0; i < info.getTimelist().size(); i++) {
							info.getTimelist().get(i).setTeachclassid(AdclassID);
							System.out.println(info.getTimelist().get(i).getWeeknumber());
							System.out.println(info.getTimelist().get(i).getSsection());
							System.out.println(info.getTimelist().get(i).getClassroomid());
							System.out.println(info.getTimelist().get(i).getTeachclassid());
							System.out.println(info.getTimelist().get(i).getWeek());
							courseTMapper.PsetCourseTimeAndRoom(info.getTimelist().get(i));
						}
						status.setStatus(200);
						System.out.println("���γɹ�");	
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				status.setStatus(400);
				System.out.println("����error");
			}
					
		} else {
			System.out.println("���γ�ʱ");
			
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: DeleteTeacherCourse<��p>
	 * <p>Description:ɾ������γ� <��p>
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
				System.out.println("ɾ���γɹ�");	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ɾ����error");
				status.setStatus(408);
				status.setInfo("error");
			}
		} else {
			System.out.println("ɾ���γ�ʱ");
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		

		return status;
	}
	
	/**
	 * <p>Title: SetScoreH<��p>
	 * <p>Description:���ÿγ̷���ռ�� <��p>
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
				Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
				map.put("courseID", couclassid);
				map.put("score1h", score1h);
				map.put("score2h", score2h);
				
				courseTMapper.PsetScoreH(map);
				status.setStatus(200);
				System.out.println("���ÿγ̷���ռ�ȳɹ�");
				
			} catch (Exception e) {
				// TODO: handle exception
				status.setStatus(400);
				System.out.println("���ÿγ̷���ռ��error");
			}
		} else {
			System.out.println("���ÿγ̷���ռ�ȳ�ʱ");
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}

		return status;
	}
	/**
	 * <p>Title: SelectTeacherCourse<��p>
	 * <p>Description:�鿴��ʦ�����γ� <��p>
	 */
	@RequestMapping(value = "/SelectTeacherCourse")
	@ResponseBody
	private StatusListT<OpenCourseListInfoItem> SelectTeacherCourse(HttpServletRequest request,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "teaid") BigDecimal teaid){
		StatusListT<OpenCourseListInfoItem> status = new StatusListT<>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				status.setInfo(courseTMapper.selectTeacherCourseByIteachId(teaid));
				
				System.out.println("�鿴��ʦ�����γ�success");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴��ʦ�����γ�error");
				status.setStatus(400);
			}
		} else {
			System.out.println("�鿴��ʦ�����γ̳�ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return status;
	}
	
	/**
	 * <p>Title: SelectTeacherTableCourse<��p>
	 * <p>Description: �鿴��ʦ�α�<��p>
	 */
	@RequestMapping(value = "/SelectTeacherTableCourse", method = RequestMethod.GET)
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
			System.out.println("�鿴��ʦ�α��ɹ�");
			info.setStatus(200);
		} else {
			System.out.println("�鿴��ʦ�α���ʱ");
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}

		return info;
	}
	/**
	 * <p>Title: UpdataClassGrade<��p>
	 * <p>Description: �޸�ѧ���ɼ�<��p>
	 */
	@RequestMapping(value = "/UpdataClassGrade")
	@ResponseBody
	public Status UpdataClassGrade(HttpServletRequest request,@RequestBody SetClassGradeInfo setClassGradeInfo){
		Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		String cookie = setClassGradeInfo.getCookie();
		System.out.println("tjcj");
		System.out.println(setClassGradeInfo.getInfo().size());
		if (session.getId().equals(cookie)) {
			try {
				CourseSMapper courseSMapper = sqlSession.getMapper(CourseSMapper.class);
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				System.out.println(setClassGradeInfo.getInfo().get(0).getCourseID());
				for (int i = 0; i < setClassGradeInfo.getInfo().size(); i++) {
					System.out.println(setClassGradeInfo.getInfo().get(i).toString());
					System.out.println(setClassGradeInfo.getInfo().get(i).getScore1());
					courseSMapper.PsetScore(setClassGradeInfo.getInfo().get(i));
				}
				CourseT courseT = courseTMapper.selectByPrimaryKey(setClassGradeInfo.getInfo().get(0).getCourseID());
				courseT.setBscorestate(new BigDecimal(1));
				courseTMapper.updateByPrimaryKey(courseT);
				sqlSession.commit();
				System.out.println("�޸�ѧ���ɼ�success");
				status.setStatus(200);
				status.setInfo("success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸�ѧ���ɼ�error");
				status.setStatus(400);
				status.setInfo("error");
			}
		} else {
			System.out.println("�޸�ѧ���ɼ���ʱ");
			status.setStatus(408);
			status.setInfo("time out");
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
	/**
	 * <p>Title: SelectClassGrade<��p>
	 * <p>Description: �鿴������ѧ��ĳɼ�<��p>
	 */
	@RequestMapping(value = "/SelectClassGrade")
	@ResponseBody
	public GetStudentGradeListInfo SelectClassGrade(HttpServletRequest request, @RequestParam(value = "cookie") String cookie,
			@RequestParam(value = "couclassid") BigDecimal couclassid){
		GetStudentGradeListInfo info = new GetStudentGradeListInfo();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				info.setInfo(courseTMapper.getClassScore(couclassid));
				info.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�鿴������ѧ��ĳɼ�error");
				info.setStatus(400);
			}
		}else {
			System.out.println("�鿴������ѧ��ĳɼ���ʱ");
			info.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		return info;
	}
	
	/**
	 * <p>Title: getGradeExcel<��p>
	 * <p>Description: ��ȡ��ѧ��ɼ�excel<��p>
	 */
	@RequestMapping(value = "/getGradeExcel", method=RequestMethod.GET)
	//@ResponseBody
	public void getGradeExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") BigDecimal couid){
		
		//Status status = new Status();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		OutputStream fos = null;  
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				List<Vxueshengkpaiming> list = courseTMapper.getClassGradeExcel(couid);
				fos = response.getOutputStream();
				String fileName;
				if (list.size()>0 && list.get(0)!=null) {
					fileName = list.get(0).getScoursename()+list.get(0).getIteachclassid().toString()+"��";
					
				} else {
					fileName = "O";
				}
				
				HSSFWorkbook workbook =OutputExcel.getGradeExcel(list);
				response.setCharacterEncoding("UTF-8");  
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");// ����contentTypeΪexcel��ʽ  
	            response.setHeader("Content-Disposition", "Attachment;Filename="+ fileName+".xls");  
	            workbook.write(fos);  
	            fos.close(); 
	            System.out.println("��ȡ��ѧ��ɼ�excel success");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ȡ��ѧ��ɼ�excel error");
				//status.setStatus(400);
				e.printStackTrace();
			}
		} else {
			System.out.println("��ȡ��ѧ��ɼ�excel��ʱ");
			//status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		//return status;
	}
	
	/**
	 * <p>Title: SelectOnCoursePlaceTime<��p>
	 * <p>Description: ��ʾĳ��ʱ��ص�<��p>
	 */
	@RequestMapping(value = "/SelectOnCoursePlaceTime")
	@ResponseBody
	public StatusListT<OneCoursePlaceTime> SelectOnCoursePlaceTime(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cookie") String cookie, @RequestParam(value = "couid") BigDecimal couid){
		StatusListT<OneCoursePlaceTime> status = new StatusListT<OneCoursePlaceTime>();
		HttpSession session = request.getSession();
		SqlSession sqlSession = DatabaseSource.getSessionFactory().openSession();
		if (session.getId().equals(cookie)) {
			try {
				CourseTMapper courseTMapper = sqlSession.getMapper(CourseTMapper.class);
				status.setInfo(courseTMapper.getCourseTimePlace(couid));
				System.out.println("��ȡ��ѧ��ɼ�excelss");
				status.setStatus(200);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ȡ��ѧ��ɼ�excelee");
				status.setStatus(400);
			}
		} else {
			System.out.println("��ȡ��ѧ��ɼ�excel��ʱ");
			status.setStatus(408);
			onlineCheck.deleteNo(cookie);
		}
		
		return status;
	}
}