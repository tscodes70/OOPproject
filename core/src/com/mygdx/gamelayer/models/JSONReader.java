package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class JSONReader {
	
	private JsonValue jsonvalue; 
	private FileHandle fileHandle;
	private JsonReader jsonReader;
	
	public JSONReader(String filePath) {
		try {
		// Load the JSON file
        fileHandle = Gdx.files.internal(filePath);
        jsonReader = new JsonReader();
        
        // Parse the JSON
        this.jsonvalue = jsonReader.parse(fileHandle);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getJsonString(String key) {
		return jsonvalue.getString(key);
	}
	
	public int getJsonInt(String key) {
		return jsonvalue.getInt(key);
	}
}