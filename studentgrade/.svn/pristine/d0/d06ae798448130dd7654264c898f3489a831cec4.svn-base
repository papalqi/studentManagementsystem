/**
 * <p>Title: OnlineCheck.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.method;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: OnlineCheck<／p>
 * <p>Description: 检查在线人数的一些方法<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public class OnlineCheck {

	private List<String> studentlist = new ArrayList<>(); //学生在线列表
	private List<String> teacherlist = new ArrayList<>(); //教师在线列表
	
	private static class SingletonHolder {
		public static OnlineCheck onlineCheck = new OnlineCheck();
	}
	
	private OnlineCheck(){}
	
	public static OnlineCheck newInstance(){
		return SingletonHolder.onlineCheck;
	}
	
	public List<String> getStudentlist() {
		return studentlist;
	}

	public List<String> getTeacherlist() {
		return teacherlist;
	}
	
	/**
	 * <p>Title: getStudentNo<／p>
	 * <p>Description: 获取学生在线人数<／p>
	 */
	public int getStudentNo(){
		return studentlist.size();
	}
	
	/**
	 * <p>Title: getTeacherNo<／p>
	 * <p>Description: 获取教师在线人数<／p>
	 */
	public int getTeacherNo(){
		return teacherlist.size();
	}
	
	/**
	 * <p>Title: allNo<／p>
	 * <p>Description:获取在线总人数 <／p>
	 */
	public int allNo(){
		return studentlist.size() + teacherlist.size();
	}
	
	/**
	 * <p>Title: insertNo<／p>
	 * <p>Description:增加在线人数 <／p>
	 */
	public void insertNo(String cookie, int identity){
		switch (identity) {
		case 3:
			if(!studentlist.contains(cookie)){
				studentlist.add(cookie);
				System.out.println("在线学生数"+studentlist.size());
			}
				
			break;
		case 2:
			if(!teacherlist.contains(cookie)){
				teacherlist.add(cookie);
				System.out.println("在线老师数"+teacherlist.size());
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * <p>Title: deleteNo<／p>
	 * <p>Description:减少在线人数 <／p>
	 */
	public void deleteNo(String cookie){
		if(studentlist.contains(cookie))
			studentlist.remove(cookie);
		if(teacherlist.contains(cookie))
			teacherlist.remove(cookie);
	}
	
	
}
