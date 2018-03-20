package com.studentgrade.dao;

import com.studentgrade.bean.Classroom;
import java.math.BigDecimal;
import java.util.List;

public interface ClassroomMapper {
    int deleteByPrimaryKey(BigDecimal iclassroomid);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(BigDecimal iclassroomid);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);
    
    List<Classroom> selectByIacademicbuildingId(BigDecimal iacademicbuildingid);
}