package com.studentgrade.dao;

import com.studentgrade.bean.CourseT;
import com.studentgrade.bean.Vjidianpaiming;
import com.studentgrade.bean.Vxueshengkpaiming;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.studentgrade.model.ClassGradeInfoItem;
import com.studentgrade.model.ClassScoreH;
import com.studentgrade.model.OneCoursePlaceTime;
import com.studentgrade.model.OpenCourseInfo;
import com.studentgrade.model.OpenCourseListInfoItem;
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
    
    List<ClassGradeInfoItem> getClassScore(BigDecimal iteachclassid);
    
    ClassScoreH selectScoreHByPrimaryKey(BigDecimal iteachclassid);
    
    List<OpenCourseListInfoItem> selectTeacherCourseByIteachId(BigDecimal iteachid);
    
    void PsetScoreH(Map<String, BigDecimal> map);
    
    List<Vxueshengkpaiming> getClassGradeExcel(BigDecimal iteachclassid);
    
    List<Vxueshengkpaiming> getStudentGradeBystuid(BigDecimal istudentid);
    
    List<Vjidianpaiming> getStudentPMByclassid(BigDecimal iclassid);
    
    List<OneCoursePlaceTime> getCourseTimePlace(BigDecimal iteachclassid);
}