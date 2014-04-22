package com.my.mybatis.share;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.mybatis.model.EEmpiLog;
import com.my.mybatis.model.EEmpiLogExample;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			EEmpiLogExample ee = new EEmpiLogExample();
			ee.setTableCondition("100");
			List<EEmpiLog> empiLogList = session.selectList("com.my.mybatis.dao.EEmpiLogMapper.selectByExample", ee);
			System.out.println(empiLogList.size());
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
    }
}
