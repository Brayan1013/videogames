package com.example.springboot.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUplodaImage {
	
	public String guardarImage(MultipartFile foto) throws IOException;
	
	public Path getPath(String foto);
	
	public void deleteImageAnterior(String foto);
	
	public Resource showImage(String foto) throws MalformedURLException;

}
