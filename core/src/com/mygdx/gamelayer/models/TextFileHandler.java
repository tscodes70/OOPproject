package com.mygdx.gamelayer.models;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class TextFileHandler {

    private FileHandle fileHandle;
    private HashMap<String, Integer> map;
    
	public TextFileHandler(String filePath) {
        this.fileHandle = Gdx.files.local(filePath);
        this.map = new HashMap<>();

        try {
            String fileContent = fileHandle.readString();
            String[] lines = fileContent.split("\n"); // Split by newline to get each line

            for (String line : lines) {
                String[] parts = line.split(":", 2); // Split by delimiter, limit to 2 parts
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String valueString = parts[1].trim();

                    try {
                        // Convert the string value to an integer
                        Integer value = Integer.valueOf(valueString);
                        map.put(key, value);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing integer value for key '" + key + "': " + valueString);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeString(String text) {
        try {
            // Write the string to the file, overwriting existing content
            fileHandle.writeString(text, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendString(String text) {
        try {
            // Append the string to the file, preserving existing content
            fileHandle.writeString(text, true); // true to append
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateValueForKey(String key, Integer newValue) {
        // Update the map
        if (map.containsKey(key)) {
            map.put(key, newValue);

            // Write the entire map back to the file
            try {
                // Open the file handle for writing, clearing the file
                FileHandle fileHandleWritable = Gdx.files.local(fileHandle.path());
                fileHandleWritable.writeString("", false); // Clear the file content before rewriting

                // Rewrite each key-value pair
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String line = entry.getKey() + ": " + entry.getValue() + "\n";
                    fileHandleWritable.writeString(line, true); // Append each line to the file
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Key '" + key + "' not found. No update performed.");
        }
    }
    
    public HashMap<String, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Integer> map) {
		this.map = map;
	}


    
}