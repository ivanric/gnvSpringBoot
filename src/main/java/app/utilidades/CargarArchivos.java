package app.utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CargarArchivos {
	@Autowired
	ServletContext context;
	
	private String direccion=".//src//main//resources//static//archivos//fotos//";
	public void saveImagen(MultipartFile archivo,String nombreFoto) throws IOException {
		if (!archivo.isEmpty()) {
			byte[] bytes=archivo.getBytes();
			//System.out.println("UBICACION: "+System.getProperty("user.dir"));
			//System.out.println("ENLACE: "+context.getRealPath("files"));
			Path path=Paths.get(direccion+nombreFoto);
			Files.write(path,bytes);
		}
		
	}
	public void modifyImagen(MultipartFile foto,String nombreFotoAnterior,String nombreFotoNuevo) throws IOException {
		//System.out.println("MODIFICANDO FOTO"+ nombreFotoAnterior +" PARA "+nombreFotoNuevo);
		if (!foto.isEmpty()) {
			byte[] bytes=foto.getBytes();
			Path path=null;
			if(!nombreFotoAnterior.equals("user.png")) {
				System.out.println("Eliminando.."+nombreFotoAnterior);
				path=Paths.get(direccion+nombreFotoAnterior);
				Files.delete(path);
			}
			try {
				System.out.println("creando.."+nombreFotoNuevo);
				path=Paths.get(direccion+nombreFotoNuevo);
				Files.write(path,bytes);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			Files.write(path,bytes);
		}
		
	}
	/*
	private String direccion=System.getProperty("user.dir")+"/files/fotos/";
	public void saveImagen(MultipartFile archivo,String nombreFoto) throws IOException {
		System.out.println("ENTRO");
		System.out.println("Direccion: "+direccion);
		if (!archivo.isEmpty()) {
			byte[] bytes=archivo.getBytes();	
			
			Path path=Paths.get(direccion+nombreFoto);
			Files.write(path,bytes);
		}
		
	}*/
}
