package com.steven.json;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 学习如何解析Json
 * 
 * @author steven
 *
 */
public class ReadJson {

	private static String JSON_FILE_PATH = "src/com/steven/json/json.txt";

	public static void main(String[] args) {

		JsonParser parser = new JsonParser();
		try {
			JsonObject object = (JsonObject) parser.parse(new FileReader(JSON_FILE_PATH));
			System.out.println("name:" + object.get("name").getAsString());
			System.out.println("age:" + object.get("age").getAsInt());

			JsonArray array = object.get("language").getAsJsonArray();
			for (int i = 0; i < array.size(); i++) {
				JsonObject item = array.get(i).getAsJsonObject();
				System.out.println("name:" + item.get("name").getAsString());
			}

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
