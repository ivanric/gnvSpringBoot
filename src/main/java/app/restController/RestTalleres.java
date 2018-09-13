package app.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorTalleres;
import app.models.Persona;
import app.models.Taller;
 
@RequestMapping("/RestTalleres/")
@RestController
public class RestTalleres{ 
	@Autowired
	ManejadorTalleres manejadorTalleres;

	      
	@RequestMapping(value="listar")
	public ResponseEntity<List<Taller>> listarServicio(HttpServletRequest req,HttpServletResponse res){	
		List<Taller> lista=this.manejadorTalleres.ListarTalleres(req);
		System.out.println("listaRoles: "+lista.toString());
		return new ResponseEntity<List<Taller>>(lista,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,Persona p,HttpServletResponse res){
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try{
			boolean consulta=this.manejadorTalleres.registrar(req,p);
			System.out.println("resp: "+consulta);
			respuesta.put("estado", consulta);
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	
	@RequestMapping(value="datos-mod")
	public ResponseEntity<Taller> datosMod(HttpServletRequest req){
		Taller data=this.manejadorTalleres.datosModificar(req);
		
		System.out.println("Rol a Modificar:"+data);
		return new ResponseEntity<Taller>(data,HttpStatus.OK);		
	}
	
	@Transactional
	@RequestMapping(value="modificar")
	public Map<String, Object> modificar(HttpServletRequest req,HttpServletResponse res,Persona p){
		Map<String, Object> respuesta=new HashMap<String, Object>();
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String[] telefonos=req.getParameterValues("telefono[]");
		System.out.println("p: "+p.toString());
		try {
			boolean consulta=this.manejadorTalleres.modificar(req, p,telefonos);
			System.out.println(consulta);
			respuesta.put("estado", consulta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	

	@RequestMapping(value="eliminar")
	public ResponseEntity<Map<String, Object>> elim(HttpServletRequest req){
		System.out.println("lego eliminar");
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String idtall=req.getParameter("idtall");
		System.out.println("idtall: "+idtall);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorTalleres.eliminar(Integer.parseInt(idtall));
			respuesta.put("estado", resp);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);	
	}
	@RequestMapping(value="habilitar")
	public ResponseEntity<Map<String, Object>> habil(HttpServletRequest req){
		System.out.println("lego eliminar");
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String idtall=req.getParameter("idtall");
		System.out.println("idtall: "+idtall);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorTalleres.habilitar(Integer.parseInt(idtall));
			respuesta.put("estado", resp);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);	
	}
	
}
