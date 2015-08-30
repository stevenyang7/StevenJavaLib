package com.steven.cxf.room.service.dao;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.steven.cxf.room.service.po.Room;

/**
 * cxf发布restful只认你给他的类的class。
 * 所以你想让服务器返回一个room的列表给客户端，是不行的。所以，必须想别的办法，
 * 又写了一个POJO，这个POJO里只有一个属性，就是一个存放所有room的Map
 * 这样就能以list的形式显示出所有room了
 * @author steven
 *
 */
@XmlRootElement(name = "rooms")
public class Rooms {
	Map<String, Room> rooms;

	public Rooms() {
		rooms = RoomDAO.getMapOfRooms();
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}
}