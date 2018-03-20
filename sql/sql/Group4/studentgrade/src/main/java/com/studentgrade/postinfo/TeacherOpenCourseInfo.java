/**
 * <p>Title: TeacherOpenCourseInfo.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��16��
 * @version 1.0
 */
package com.studentgrade.postinfo;

import java.util.List;

import com.studentgrade.model.OpenCourseInfo;
import com.studentgrade.model.OpenCourseTimeItem;

/**
 * <p>Title: TeacherOpenCourseInfo<��p>
 * <p>Description: <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��16��
 */
public class TeacherOpenCourseInfo {
	
	private OpenCourseInfo courseInfo;//������Ϣ
	private List<OpenCourseTimeItem> Timelist;//����ʱ��
	
	public OpenCourseInfo getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(OpenCourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}
	public List<OpenCourseTimeItem> getTimelist() {
		return Timelist;
	}
	public void setTimelist(List<OpenCourseTimeItem> timelist) {
		Timelist = timelist;
	}

}
