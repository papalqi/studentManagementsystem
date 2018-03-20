/**
 * <p>Title: TestMapper.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月7日
 * @version 1.0
 */
package com.studentgrade.mapper;

import com.studentgrade.model.TestModel;

/**
 * <p>Title: TestMapper<／p>
 * <p>Description: 测试接口<／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月7日
 */
public interface TestMapper {
    int deleteByPrimaryKey(Short deptno);

    int insert(TestModel record);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Short deptno);

    int updateByPrimaryKeySelective(TestModel record);

    int updateByPrimaryKey(TestModel record);
}
