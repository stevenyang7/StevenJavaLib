package com.steven.cxf.room.service.client;

import org.apache.cxf.jaxrs.client.WebClient;

import com.steven.cxf.room.service.po.Room;

/**
 * 这个类用起来很简单，给WebClient的静态方法create一个参数，该参数是服务器地址的字符串。
 * client.path("roomservice/room/001")
 * .accept("application/xml").get(Room.class);
 * 这句代码，是告诉client要访问的restful资源，以及格式，最后的get，也就是对这个资源发送一个http
 * GET请求，参数Room.class是告诉client要以什么方式接受服务器发送来的response。
 * （服务器端和客户端的格式应该一致，所以这里用的和服务器端用的是一个POJO，
 * 如果客户端不是用CXF，那么应该定义一个scheme,这样才能让双方明白来回发送的XML应该怎么处理，暂时也没深入，回头再研究。）
 * DELETE方法很好理解，就是把你指定的资源删除掉，所以delete()不用带参数。 POST，PUT方法有点不同，
 * 
 * post(Object body, Class<Room> ) put(Object body, Class<Room> )
 * 
 * 第一参数是你要传给服务器的POJO， 第二个参数是告诉client要将server返回的response转化为的POJO的类型。
 * 第二个参数可以不写，如果不写，收到的将是一个response。
 * 
 * @author steven
 *
 */
public class RoomClient {

	static WebClient client;

	static void get() {
		Room room = client.path("roomservice/room/001").accept("application/xml").get(Room.class);
		System.out.println("get the room which id is:" + room.getId());
	}

	static void post() {
		Room room = new Room();
		room.setId("003");
		client.path("roomservice/room").accept("application/xml").post(room, Room.class);
	}

	static void delete(String roomId) {
		client.path("roomservice/room/" + roomId).accept("application/xml").delete();
	}

	static void put() {
		Room room = new Room();
		room.setId("006");
		client.path("roomservice/room/003").accept("application/xml").put(room);
	}

	public static void main(String[] args) {
		client = WebClient.create("http://localhost:9999/");
		delete("001");

	}

}
