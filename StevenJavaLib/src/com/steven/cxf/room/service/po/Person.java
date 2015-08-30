package com.steven.cxf.room.service.po;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 一定要在类的前边加上annotation ，
 * 这样才能让这个person的信息在POJO和XML之间转换
 * @author steven
 *
 */
@XmlRootElement(name="Person")
public class Person {
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
