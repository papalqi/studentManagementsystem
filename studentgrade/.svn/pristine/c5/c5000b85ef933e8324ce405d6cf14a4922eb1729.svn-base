package com.studentgrade.dao;

import com.studentgrade.bean.Academicbuilding;
import java.math.BigDecimal;
import java.util.List;

public interface AcademicbuildingMapper {
    int deleteByPrimaryKey(BigDecimal iacademicbuildingid);

    int insert(Academicbuilding record);

    int insertSelective(Academicbuilding record);

    Academicbuilding selectByPrimaryKey(BigDecimal iacademicbuildingid);

    int updateByPrimaryKeySelective(Academicbuilding record);

    int updateByPrimaryKey(Academicbuilding record);
    
    List<Academicbuilding> selectByCampusId(BigDecimal icampusid);
}