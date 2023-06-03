package com.peliculas.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArchivoServiceImple implements ArchivoService {

	@Override
	public void guardar(String archivo, InputStream bytes) {
		// TODO Auto-generated method stub
		try {
			this.eliminar(archivo);
			Files.copy(bytes, resolvePath(archivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ResponseEntity<Resource> get(String archivo) {
		// TODO Auto-generated method stub
		Resource resource = null;
		try {
			resource = new UrlResource(resolvePath(archivo).toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachemt; filename\"" + resource.getFilename() + "\"").body(resource);
	}

	private Path resolvePath(String archivo) {
		return Paths.get("archivos").resolve(archivo).toAbsolutePath();
	}

	@Override
	public void eliminar(String archivo) {
		// TODO Auto-generated method stub
		try {
			Files.deleteIfExists(resolvePath(archivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
