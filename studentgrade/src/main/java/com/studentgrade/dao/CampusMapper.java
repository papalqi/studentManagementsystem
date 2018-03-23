package com.studentgrade.dao;

import com.studentgrade.bean.Campus;
import java.math.BigDecimal;
import java.util.List;

public interface CampusMapper {
    int deleteByPrimaryKey(BigDecimal icampusid);

    int insert(Campus record);

    int insertSelective(Campus record);

    Campus selectByPrimaryKey(BigDecimal icampusid);

    int updateByPrimaryKeySelective(Campus record);

    int updateByPrimaryKey(Campus record);
    
    List<Campus> selectAll();
}