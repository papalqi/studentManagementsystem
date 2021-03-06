/**
 * <p>Title: TeacherMapper.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.studentgrade.bean.Teacher;
import com.studentgrade.ininfo.InsertTeacherInfo;
import com.studentgrade.model.TeacherListInfoItem;

/**
 * <p>Title: TeacherMapper<／p>
 * <p>Description: Teacher表的接口，dao <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public interface TeacherMapper {

    int deleteByPrimaryKey(BigDecimal iteacheid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(BigDecimal iteacheid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    void insertTeacher(InsertTeacherInfo info);
    
    void deleteByTeacherId(BigDecimal teacherID);
    
    List<TeacherListInfoItem> getTeacherList(@Param(value="context") String context);
    

}
