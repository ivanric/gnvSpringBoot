package app.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.entity.Persona;
import app.service.IUsuarioService;
import app.utilidades.CargarArchivos;
import app.utilidades.OpcionesAdicionales;

@RestController
@RequestMapping("/RestUser")
public class RestUsers {
	
	@Autowired 
	private  IUsuarioService iUsuarioService;
	@Autowired 
	private OpcionesAdicionales opcionesGenerales;
	@Autowired
	private CargarArchivos appArchivos;
	
	@RequestMapping//POR DEFECTO ES GET Y HACE REFERENCIA A LA RAIZ SI NO TIENE MAPEO
	ResponseEntity<List<Persona>> AllUsers(HttpServletRequest req,HttpServletResponse res){	
		System.out.println("Listar");
		List<Persona> lista=this.iUsuarioService.findAll(req);
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).setUsuario(opcionesGenerales.getUserByIdper(lista.get(i).getIdper()));
			lista.get(i).setListaTelf(opcionesGenerales.getTelefonosbyIdper(lista.get(i).getIdper()));
		}
		System.out.println("listaRestUsers: "+lista.toString());
		return new ResponseEntity<List<Persona>>(lista,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<Map<String, Object>>addUser(HttpServletRequest req,HttpServletResponse res,MultipartFile foto){
		System.out.println("ADD USER:");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		String nombreFoto;
		String[] telefonos=req.getParameterValues("telefono[]");
		try {
			if(foto!=null && foto.getSize()>0){
				nombreFoto="codper-"+this.iUsuarioService.generarIdPer()+foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf('.'));
				appArchivos.saveImagen(foto,nombreFoto);
			}else {
				nombreFoto="user.png";
			}
			if (iUsuarioService.save(req,telefonos, nombreFoto)) {
				respuesta.put("estado",true);
			} else {
				respuesta.put("estado",false);
			};
		} catch (Exception e) {
			respuesta.put("estado",false);
		} 
		return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.OK);
	}
	@RequestMapping(value="/dataModify/{id}",method=RequestMethod.POST)
	ResponseEntity<List<?>> dataModify(@PathVariable("id") Integer id){
		List<Object> lista=new ArrayList<Object>();
		Persona p=this.iUsuarioService.getPersonaById(id);
		p.setUsuario(this.iUsuarioService.getUsuarioById(p.getIdper()));
		p.setListaTelf(this.opcionesGenerales.getTelefonosbyIdper(p.getIdper()));
		lista.add(p);
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	ResponseEntity<Map<String, Object>>modifyUser(HttpServletRequest req,HttpServletResponse res,MultipartFile foto){
		System.out.println("MODIFY USER:");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		String nombreFotoNuevo,nombreFotoAnterior;
		String[] telefonos=req.getParameterValues("telefono[]");
		int idper=Integer.parseInt(req.getParameter("idper"));
		Persona per=iUsuarioService.getPersonaById(idper);
		try {
			if(foto!=null && foto.getSize()>0){
				System.out.println("fotoNombre: "+foto.getOriginalFilename());
				nombreFotoNuevo="modify_codper-"+idper+foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf('.'));
				nombreFotoAnterior=per.getFoto();
				appArchivos.modifyImagen(foto, nombreFotoAnterior, nombreFotoNuevo);
			}else {
				System.out.println("foto nula");
				nombreFotoNuevo=per.getFoto();
			}
			if (iUsuarioService.modify(req,telefonos, nombreFotoNuevo)) {
				respuesta.put("estado",true);
			} else {
				respuesta.put("estado",false);
			};
		} catch (Exception e) {
			respuesta.put("estado",false);
		} 
		return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.OK);
	}
	@RequestMapping(value="/changeStatus",method=RequestMethod.POST)
	ResponseEntity<Map<String, Object>>statusUser(HttpServletRequest req,HttpServletResponse res){
		System.out.println("STATUS USER:");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			if (iUsuarioService.changeStatus(req)) {
				respuesta.put("estado",true);
			} else {
				respuesta.put("estado",false);
			};
		} catch (Exception e) {
			respuesta.put("estado",false);
		} 
		return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.OK);
	}

}
