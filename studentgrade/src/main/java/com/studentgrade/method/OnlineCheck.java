/**
 * <p>Title: OnlineCheck.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��12��
 * @version 1.0
 */
package com.studentgrade.method;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: OnlineCheck<��p>
 * <p>Description: �������������һЩ����<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��12��
 */
public class OnlineCheck {

	private List<String> studentlist = new ArrayList<>(); //ѧ�������б�
	private List<String> teacherlist = new ArrayList<>(); //��ʦ�����б�
	
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
	 * <p>Title: getStudentNo<��p>
	 * <p>Description: ��ȡѧ����������<��p>
	 */
	public int getStudentNo(){
		return studentlist.size();
	}
	
	/**
	 * <p>Title: getTeacherNo<��p>
	 * <p>Description: ��ȡ��ʦ��������<��p>
	 */
	public int getTeacherNo(){
		return teacherlist.size();
	}
	
	/**
	 * <p>Title: allNo<��p>
	 * <p>Description:��ȡ���������� <��p>
	 */
	public int allNo(){
		return studentlist.size() + teacherlist.size();
	}
	
	/**
	 * <p>Title: insertNo<��p>
	 * <p>Description:������������ <��p>
	 */
	public void insertNo(String cookie, int identity){
		switch (identity) {
		case 3:
			if(!studentlist.contains(cookie)){
				studentlist.add(cookie);
				System.out.println("����ѧ����"+studentlist.size());
			}
				
			break;
		case 2:
			if(!teacherlist.contains(cookie)){
				teacherlist.add(cookie);
				System.out.println("������ʦ��"+teacherlist.size());
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * <p>Title: deleteNo<��p>
	 * <p>Description:������������ <��p>
	 */
	public void deleteNo(String cookie){
		if(studentlist.contains(cookie))
			studentlist.remove(cookie);
		if(teacherlist.contains(cookie))
			teacherlist.remove(cookie);
	}
	
	
}
