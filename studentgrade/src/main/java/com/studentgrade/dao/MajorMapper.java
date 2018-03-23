/**
 * <p>Title: MajorMapper.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月12日
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;

import com.studentgrade.bean.Major;

/**
 * <p>Title: MajorMapper<／p>
 * <p>Description: Major表的接口，dao<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月12日
 */
public interface MajorMapper {
	
    int deleteByPrimaryKey(BigDecimal imajorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(BigDecimal imajorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
    
    List<Major> selectAll();
    
    List<Major> selectByCollegeId(BigDecimal icollegeid);
}
