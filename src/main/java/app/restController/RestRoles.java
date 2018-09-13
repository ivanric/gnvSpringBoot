package app.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorRoles;
import app.manager.ManejadorServicios;
import app.manager.ManejadorSolicitudes;
import app.manager.ManejadorUsuarios;
import app.models.Persona;
import app.models.Rol;
 
@RequestMapping("/RestRoles/")
@RestController
public class RestRoles{ 
	@Autowired
	ManejadorServicios manejadorServicios;
	@Autowired
	ManejadorRoles manejadorRoles;
	@Autowired   
	ManejadorSolicitudes manejadorSolicitudes;
	@Autowired
	ManejadorUsuarios manejadorUsers;
	      
	@RequestMapping(value="listar")
	public ResponseEntity<List<Rol>> listarServicio(HttpServletRequest req,HttpServletResponse res){	
		List<Rol> roles=this.manejadorRoles.ListarRoles(req);
		System.out.println("listaRoles: "+roles.toString());
		return new ResponseEntity<List<Rol>>(roles,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,HttpServletResponse res){
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try{
			boolean consulta=this.manejadorRoles.registrar(req,xuser);
			System.out.println("resp: "+consulta);
			respuesta.put("estado", consulta);
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	@RequestMapping(value="datos-mod")
	public ResponseEntity<Rol> datosMod(HttpServletRequest req){
		Rol data=this.manejadorRoles.datosModificar(req);
		
		System.out.println("Rol a Modificar:"+data);
		return new ResponseEntity<Rol>(data,HttpStatus.OK);		
	}
	@Transactional
	@RequestMapping(value="modificar")
	public Map<String, Object> modificar(HttpServletRequest req,HttpServletResponse res){
		Map<String, Object> respuesta=new HashMap<String, Object>();
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		try {
			boolean consulta=this.manejadorRoles.modificar(req,xuser);
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
		String idrol=req.getParameter("idrol");
		System.out.println("idrol: "+idrol);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorRoles.eliminar(Integer.parseInt(idrol));
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
		String idrol=req.getParameter("idrol");
		System.out.println("idrol: "+idrol);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorRoles.habilitar(Integer.parseInt(idrol));
			respuesta.put("estado", resp);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);	
	}
	@RequestMapping(value="/getRoles/{id}",method=RequestMethod.POST)
	ResponseEntity<List<?>> getRoles(@PathVariable("id") Integer id){
		List<Object> lista=new ArrayList<Object>();
		String user=this.manejadorUsers.getUserByIdper(id);
		List<Rol> listaRoles=this.manejadorRoles.ListaRolesInRolUser();
		List<Rol> listaRolesByUser=this.manejadorRoles.listaRolesByIdper(user);
		
		lista.add(user);
		lista.add(listaRoles);
		lista.add(listaRolesByUser);
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
//	@Transactional
	@RequestMapping(value="asignarRolUser")
	public Map<String, Object> asignarRolUser(HttpServletRequest req,HttpServletResponse res){
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		String idper=req.getParameter("idper");
		try{
			String user=this.manejadorUsers.getUserByIdper(Integer.parseInt(idper));
			String [] roles=req.getParameterValues("roles[]");
			System.out.println("lista: "+roles);
			if(roles!=null) {
				for (String data : roles) {
					System.out.println(data);
				}

			}

			boolean consulta=this.manejadorRoles.registrarRolesUser(user, roles);
//			System.out.println("resp: "+consulta);
			respuesta.put("estado", consulta);
		}catch (Exception e){
			System.out.println("excepcion");
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	
}
