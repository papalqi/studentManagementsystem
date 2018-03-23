/**
 * <p>Title: DemoController.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月7日
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
 * <p>Title: DemoController<／p>
 * <p>Description:测试 MVC Controller <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月7日
 */
@Controller
@RequestMapping("/hello")//提供初步的请求映射信息。相对于WEB应用的根目录
public class DemoController {
	/***
	 * 首页 返回至/page/home.jsp页面
	 * @return
	 * 1.使用@RequestMapping 注解来映射请求的URL
	 * 2.返回值会通过视图解析器为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下解析:
	 * 通过：prefix+returnVal+suffix，这样的方式得到实际的物理视图，然后做转发操作。
	 */
	@RequestMapping("/helloworld")//为控制器指定可以处理的请求URL
	public String index(){
		//创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面
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
	 *  返回Json的示例：/hello/json
	 * 
	 *  这里加了@ResponseBody注解，说明将List<User>作为响应体，
	 *  将其响应为Json数据，因为已经在spring-servlet.xml进行配置
	 *  
	 */
//	@RequestMapping(value = "/json",method=RequestMethod.GET)
//	@ResponseBody
//	public List<Userbean> getUserInJson1(){
//		System.out.println("ds");
//		//填充所需返回的数据，本来应该是查询数据库，这里就写假数据了，因为重点不是在这
//		List<Userbean> list=new ArrayList<Userbean>();
//		for(int i=1;i<=3;i++){
//			Userbean user=new Userbean();
//		
//			user.setId(i);
//			user.setName("张三"+i);
//			user.setSex("男");
//		
//			List<String> hobbies=new ArrayList<String>();
//			hobbies.add("打篮球"+i);
//			hobbies.add("唱歌"+i);
//			hobbies.add("听音乐"+i);
//			user.setHobby(hobbies);
//			
//			list.add(user);
//		}
//		return list;
//	}
//	
	/**
	 * 获得MyBatis SqlSessionFactory  
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法。
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
