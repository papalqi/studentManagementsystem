package com.studentgrade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.studentgrade.bean.CourseA;
import com.studentgrade.model.CourseAListInfoItem;
import com.studentgrade.model.OpenCourseListInfoItem;

public interface CourseAMapper {
	int deleteByPrimaryKey(String scourseid);

    int insert(CourseA record);

    int insertSelective(CourseA record);

    CourseA selectByPrimaryKey(String scourseid);

    int updateByPrimaryKeySelective(CourseA record);

    int updateByPrimaryKey(CourseA record);
    
    List<CourseAListInfoItem> getCourseList(@Param(value="context") String context);
    
    List<OpenCourseListInfoItem> getOpenCourseList(@Param(value="scourseid") String scourseid);
    
    List<OpenCourseListInfoItem> getCanChoiceCourseList(@Param(value="context") String context);
}