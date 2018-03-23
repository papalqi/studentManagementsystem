/**
 * <p>Title: DatabaseSource.java<��p>
 * <p>Copyright: Copyright (c) 2018<��p>
 * <p>Company: Oracle Group4<��p>
 * @author XuChongtian
 * @date 2018��3��10��
 * @version 1.0
 */
package com.studentgrade.dao;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * <p>Title: DatabaseSource<��p>
 * <p>Description: ���MyBatis SqlSessionFactory<��p>
 * <p>Company: Oracle Group4<��p> 
 * @author XuChongtian
 * @date 2018��3��10��
 */
public class DatabaseSource {
	
	/**
	 * <p>Title: getSessionFactory<��p>
	 * <p>Description: SqlSessionFactory���𴴽�SqlSession��һ�������ɹ���
	 * �Ϳ�����SqlSessionʵ����ִ��ӳ����䣬commit��rollback��close�ȷ�����<��p>
	 */
	public static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "configure.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�������ݿ�ʧ��");
			e.printStackTrace();
		}
		return sessionFactory;
	}
}