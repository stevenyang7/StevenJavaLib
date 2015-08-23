package com.steven.cxf;

import javax.jws.WebService;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 采用CXF创建ws服务，目前是基于代码方式。 ServerFactoryBean: 不支持注解的方式，此方式不灵活
 * JaxWsServerFactoryBean: 支持注解和日志 ws访问流程： 1、 在调用方法的时候先发送一条get请求去访问远程的wsdl文件
 * (此文件中有相应的公共接口和可以调用的方法), 此流程称为"握手" 2、然后在客户端发送 post请求传输 soap数据交给服务器
 * 3、最后服务器返回soap格式给客户端 前面所讲的ws服务都是硬编码的服务, Service应该是单例模式, 如果有Spring
 * CXF需要交给Spring管理
 * 
 * @author steven
 *
 */
@WebService(serviceName = "CXFWervice")
public class CXFWervice {
	public String sayHello(String name) {
		return name + "，你好!";
	}

	public static void main(String[] args) {
		String address="http://127.0.0.1:8888/hello";
		JaxWsServerFactoryBean bean=new JaxWsServerFactoryBean();
		// 设置ws服务地址
		bean.setAddress(address);
		// 设置服务的实现类
		bean.setServiceBean(new CXFWervice());
		// 支持日志: 如果有请求则会在控制台打印
		bean.getInInterceptors().add(new LoggingInInterceptor());
		// 打印返回的日志信息
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
		// 发布ws服务
		bean.create();
		System.out.println(address + "?WSDL");
		// 生成客户端的代码
		// wsimport -s . -p com.steven.cxf.client http://127.0.0.1:8888/hello?WSDL
	}

}
