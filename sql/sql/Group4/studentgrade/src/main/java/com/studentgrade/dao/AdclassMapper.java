/**
 * <p>Title: AdclassMapper.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月13日
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;
import java.util.List;

import com.studentgrade.bean.Adclass;

/**
 * <p>Title: AdclassMapper<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月13日
 */
public interface AdclassMapper {

    int deleteByPrimaryKey(BigDecimal iclassid);

    int insert(Adclass record);

    int insertSelective(Adclass record);

    Adclass selectByPrimaryKey(BigDecimal iclassid);

    int updateByPrimaryKeySelective(Adclass record);

    int updateByPrimaryKey(Adclass record);
    
    List<Adclass> selectAll();
    
    List<Adclass> selectByMajorid(BigDecimal imajorid);
}
