package com.steven.cxf.client;

/**
 * CXF客户端学习
 * 生成客户端的代码
 * wsimport -s . -p com.steven.cxf.client http://127.0.0.1:8888/hello?WSDL
 * @author steven
 *
 */
public class _CXFWerviceTest {

	public static void main(String[] args) {
		CXFWervice_Service cxfws=new CXFWervice_Service();
		CXFWervice port = cxfws.getCXFWervicePort();
		System.out.println(port.sayHello("admin"));
	}

}
