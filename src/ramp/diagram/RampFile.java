package ramp.diagram;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


public class RampFile {
	
	public static void saveToFile(GUIData data, String filepath) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		String jsonString = gson.toJson(data);
		try {
			PrintWriter out = new PrintWriter(filepath);
			out.print(jsonString);
			out.close();
		} catch (FileNotFoundException e) {
			throw new Exception("Could not create file.");
		}
	}
	
	public static GUIData loadFromFile(String filepath) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		JsonReader jsonReader;
		try {
			jsonReader = new JsonReader(new FileReader(filepath));
			GUIData data = gson.fromJson(jsonReader,GUIData.class);
			return data;
		} catch (FileNotFoundException e) {
			throw new Exception("Could not read file.");
		}
	}
}
