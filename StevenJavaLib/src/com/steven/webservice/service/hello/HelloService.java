package com.steven.webservice.service.hello;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(name = "HelloService", // 服务实现类的名称
serviceName = "HelloWebService" // 默认在发布的服务实现者的名称后面添加Service
)
public class HelloService {

	public String sayHello(@WebParam(name="name")String name) {
		return "你好，" + name + "!";
	}

	public static void main(String[] args) {
		// 一个端口可以发布多个ws服务
		String address = "http://127.0.0.1:7777/ws";
		// 创建一个服务端点, banding服务的实现类
		Endpoint.publish(address, new HelloService());
		System.out.println("访问wsdl的地址为:" + address + "?WSDL");
	}

}
