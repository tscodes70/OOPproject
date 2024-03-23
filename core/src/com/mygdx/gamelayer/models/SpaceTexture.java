package com.mygdx.gamelayer.models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.mygdx.gameengine.interfaces.iOutput;

public class SpaceTexture extends Texture implements iOutput {

	public SpaceTexture(FileHandle file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(FileHandle file, boolean useMipMaps) {
		super(file, useMipMaps);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(FileHandle file, Format format, boolean useMipMaps) {
		super(file, format, useMipMaps);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(int width, int height, Format format) {
		super(width, height, format);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(int glTarget, int glHandle, TextureData data) {
		super(glTarget, glHandle, data);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(Pixmap pixmap, boolean useMipMaps) {
		super(pixmap, useMipMaps);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(Pixmap pixmap, Format format, boolean useMipMaps) {
		super(pixmap, format, useMipMaps);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(Pixmap pixmap) {
		super(pixmap);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(String internalPath) {
		super(internalPath);
		// TODO Auto-generated constructor stub
	}

	public SpaceTexture(TextureData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
