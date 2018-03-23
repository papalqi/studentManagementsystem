package com.studentgrade.dao;

import com.studentgrade.bean.CourseT;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.studentgrade.model.OpenCourseInfo;
import com.studentgrade.model.OpenCourseTimeItem;
import com.studentgrade.model.TeacherCourseTable;;

public interface CourseTMapper {
	
    int deleteByPrimaryKey(BigDecimal iteachclassid);

    int insert(CourseT record);

    int insertSelective(CourseT record);

    CourseT selectByPrimaryKey(BigDecimal iteachclassid);

    int updateByPrimaryKeySelective(CourseT record);

    int updateByPrimaryKeyWithBLOBs(CourseT record);

    int updateByPrimaryKey(CourseT record);
    
    void Paddcourse(OpenCourseInfo record);
    
    void PsetCourseTimeAndRoom(OpenCourseTimeItem openCourseTimeItem);
    
    void PdeleteCourse(@Param(value="tclassid")BigDecimal tclassid);
    
    List<TeacherCourseTable> getTeacherCourseTable(Map<String, BigDecimal> map);
}