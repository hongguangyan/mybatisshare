package com.my.mybatis.plugins;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

@Intercepts({@Signature(
		  type= StatementHandler.class,
		  method = "prepare",
		  args = {Connection.class})})
public class TestPlugins implements Interceptor {
	
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

	@Override
	public Object intercept(Invocation paramInvocation) throws Throwable {
		// TODO Auto-generated method stub
		StatementHandler sh = (StatementHandler)paramInvocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(sh, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		BoundSql bSql = sh.getBoundSql();
        String sql = bSql.getSql();
        metaStatementHandler.setValue("delegate.boundSql.sql",sql);
		return paramInvocation.proceed();
	}

	@Override
	public Object plugin(Object paramObject) {
		// TODO Auto-generated method stub
		return Plugin.wrap(paramObject, this);
	}

	@Override
	public void setProperties(Properties paramProperties) {
		// TODO Auto-generated method stub
		System.out.println("##### do setProperties #####");
	}

}
