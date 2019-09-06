package com.example.springboot.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageImpl implements IUplodaImage {

	@Override
	public String guardarImage(MultipartFile foto) throws IOException {
		String uniqueFoto = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename().replace(" ", "");
		Path path = this.getPath(uniqueFoto);
		Files.copy(foto.getInputStream(), path);
		return uniqueFoto;
	}

	@Override
	public Path getPath(String foto) {
		Path path = Paths.get("imagenesUser").resolve(foto).toAbsolutePath();
		return path;
	}

	@Override
	public void deleteImageAnterior(String foto) {
		Path pathImageAnterior = getPath(foto);
		File file = pathImageAnterior.toFile();
		if (file.exists() && file.canRead()) {
			file.delete();
		}
	}

	@Override
	public Resource showImage(String foto) throws MalformedURLException {
		Path path = getPath(foto);
		Resource resource = new UrlResource(path.toUri());
		return resource;
	}

}
