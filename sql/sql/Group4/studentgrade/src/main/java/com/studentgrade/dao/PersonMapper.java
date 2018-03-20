/**
 * <p>Title: PersonMapper.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��10��
 * @version 1.0
 */
package com.studentgrade.dao;

import java.math.BigDecimal;

import com.studentgrade.bean.Person;

/**
 * <p>Title: PersonMapper<��p>
 * <p>Description:person���Ľӿڣ�dao <��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��10��
 */
public interface PersonMapper {
	
    int deleteByPrimaryKey(BigDecimal iid);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(BigDecimal iid);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    void updateSname(Person record);
    
    String getPasswordByIid(BigDecimal iid);
}