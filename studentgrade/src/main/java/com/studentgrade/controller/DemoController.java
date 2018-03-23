/**
 * <p>Title: DemoController.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��7��
 * @version 1.0
 */
package com.studentgrade.controller;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentgrade.bean.UserTest;
//import com.studentgrade.bean.Userbean;
import com.studentgrade.mapper.TestMapper;
import com.studentgrade.model.TestModel;

/**
 * <p>Title: DemoController<��p>
 * <p>Description:���� MVC Controller <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��7��
 */
@Controller
@RequestMapping("/hello")//�ṩ����������ӳ����Ϣ�������WEBӦ�õĸ�Ŀ¼
public class DemoController {
	/***
	 * ��ҳ ������/page/home.jspҳ��
	 * @return
	 * 1.ʹ��@RequestMapping ע����ӳ�������URL
	 * 2.����ֵ��ͨ����ͼ������Ϊʵ�ʵ�������ͼ������InternalResourceViewResolver��ͼ���������������½���:
	 * ͨ����prefix+returnVal+suffix�������ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ����ת��������
	 */
	@RequestMapping("/helloworld")//Ϊ������ָ�����Դ��������URL
	public String index(){
		//����ģ�͸���ͼ��������Ⱦҳ�档����ָ��Ҫ���ص�ҳ��Ϊhomeҳ��
		System.out.println("hhhh");
		//ModelAndView mor = new ModelAndView("home");
		return "home";
	}
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String testpost()
	{
		System.out.println("get");
		return "testget";
	}
	
	/**
	 *  ����Json��ʾ����/hello/json
	 * 
	 *  �������@ResponseBodyע�⣬˵����List<User>��Ϊ��Ӧ�壬
	 *  ������ӦΪJson���ݣ���Ϊ�Ѿ���spring-servlet.xml��������
	 *  
	 */
//	@RequestMapping(value = "/json",method=RequestMethod.GET)
//	@ResponseBody
//	public List<Userbean> getUserInJson1(){
//		System.out.println("ds");
//		//������践�ص����ݣ�����Ӧ���ǲ�ѯ���ݿ⣬�����д�������ˣ���Ϊ�ص㲻������
//		List<Userbean> list=new ArrayList<Userbean>();
//		for(int i=1;i<=3;i++){
//			Userbean user=new Userbean();
//		
//			user.setId(i);
//			user.setName("����"+i);
//			user.setSex("��");
//		
//			List<String> hobbies=new ArrayList<String>();
//			hobbies.add("������"+i);
//			hobbies.add("����"+i);
//			hobbies.add("������"+i);
//			user.setHobby(hobbies);
//			
//			list.add(user);
//		}
//		return list;
//	}
//	
	/**
	 * ���MyBatis SqlSessionFactory  
	 * SqlSessionFactory���𴴽�SqlSession��һ�������ɹ����Ϳ�����SqlSessionʵ����ִ��ӳ����䣬commit��rollback��close�ȷ�����
	 * @return
	 */
	private static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "configure.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
			System.out.println("p1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("p2");
		return sessionFactory;
	}
	
	@RequestMapping(value = "/data",method=RequestMethod.GET)
	@ResponseBody
	public TestModel getUserInJson(HttpServletResponse response){
		System.out.println("ddss");
		SqlSession sqlSession = getSessionFactory().openSession();  
        TestMapper userMapper = sqlSession.getMapper(TestMapper.class);  
        Short a = 10; 
        TestModel user = userMapper.selectByPrimaryKey(a);  
        response.setHeader("Access-Control-Allow-Origin", "*");
		return user;
	}
	
	@RequestMapping(value = "/ddp",method=RequestMethod.GET)
	@ResponseBody
	public UserTest getUserInJson4(HttpServletResponse response, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password){
		System.out.println("ddss1");
		UserTest user = new UserTest();
		user.setUsername(username);
		user.setPassword(password);
        response.setHeader("Access-Control-Allow-Origin", "*");
		return user;
	}
}
