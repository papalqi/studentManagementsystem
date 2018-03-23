/**
 * <p>Title: CollegeMapper.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;

import com.studentgrade.bean.College;

/**
 * <p>Title: CollegeMapper<／p>
 * <p>Description: college表的接口，dao <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public interface CollegeMapper {

    int deleteByPrimaryKey(BigDecimal icollegeid);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(BigDecimal icollegeid);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
    
	List<College> selectAll();
}
