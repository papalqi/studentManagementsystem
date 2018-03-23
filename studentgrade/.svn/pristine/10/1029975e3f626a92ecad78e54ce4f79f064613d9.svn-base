package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.studentgrade.bean.CourseS;
import com.studentgrade.bean.CourseSKey;
import com.studentgrade.model.ClassGradeInfoItem;
import com.studentgrade.model.CourseAListInfoItem;
import com.studentgrade.model.OpenCourseListInfoItem;
import com.studentgrade.model.StudentCourseListItem;
import com.studentgrade.model.StudentCourseTableItem;
import com.studentgrade.postinfo.SetStudentGrade;

public interface CourseSMapper {
    int deleteByPrimaryKey(CourseSKey key);

    int insert(CourseS record);

    int insertSelective(CourseS record);

    CourseS selectByPrimaryKey(CourseSKey key);

    int updateByPrimaryKeySelective(CourseS record);

    int updateByPrimaryKey(CourseS record);
    
    void PselectCourse(Map<String, BigDecimal> map);
    
    void PstudentTCourse(Map<String, BigDecimal> map);
    
    List<StudentCourseTableItem> getStudentCourseTable(Map<String, BigDecimal> map);
    
    
    void PsetScore(SetStudentGrade record);
    
    List<ClassGradeInfoItem> getStudentScore(BigDecimal istudentid);
    
    List<StudentCourseListItem>getStudentCourseL(BigDecimal istudentid);
    
    Double getStudentPGA(BigDecimal istudentid);
    
    BigDecimal getStudentPM(BigDecimal istudentid);
    
    List<CourseAListInfoItem> getCourseCanChoiceList(@Param(value="context") String context);
    
    List<OpenCourseListInfoItem> getCanChoiceCourseList2(@Param(value="scourseid") String scourseid);
}