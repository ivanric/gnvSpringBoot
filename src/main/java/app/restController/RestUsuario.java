package app.restController;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.manager.ManejadorModulos;
import app.manager.ManejadorOpciones;
import app.manager.ManejadorProcesos;
import app.manager.ManejadorRoles;
import app.manager.ManejadorUsuarios;
import app.models.Modulo;
import app.models.Persona;
import app.models.Proceso;
import app.models.Rol;
import app.models.Usuario;
import app.models.respuesta;

import app.utilidades.CargarArchivos;

//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/RestUsuario/")
@RestController
public class RestUsuario {
	@Autowired
	ServletContext context;
	
	@Autowired
	private CargarArchivos appArchivos;
	
	@Autowired 
	ManejadorUsuarios manejadorUsuarios;
	@Autowired 
	ManejadorRoles manejadorRoles;
	@Autowired 
	ManejadorModulos manejadorModulos;
	@Autowired 
	ManejadorProcesos manejadorProcesos;
	@Autowired
	ManejadorOpciones manejadorOpciones;
	
	
	@Transactional
	@ResponseBody 
	@RequestMapping({"/validar"})	
	public  ResponseEntity<Map<String, Object>> validar(HttpServletRequest request,HttpSession session,Principal principal)  throws IOException  {			
		String xlogin=request.getParameter("login");
		String xpassword=request.getParameter("password");
		Map<String, Object> Data=new HashMap<>();
		try {
			Persona xusuario=this.manejadorUsuarios.iniciarSession(xlogin,xpassword);
			System.out.println(xusuario);
			List<Rol> ListaRoles=this.manejadorRoles.ControlRoles(xusuario.getIdper());
			System.out.println("RolesFuera: "+ListaRoles.toString());
			if(xusuario!=null){
				if(xusuario.getUsuario().getEstado()!=1){
					Data.put("msg","Usuario no esta activo");
					Data.put("status",false);
				}else {
					if(ListaRoles.size()==0){
						Data.put("msg","Este usuario no tiene Roles");
						Data.put("status",false);
					}
					else{
						System.out.println("sessionooo");
						session.setAttribute("xusuario",xusuario);
						Data.put("msg","Usuario registrado con Exito");
						Data.put("status",true);
					}
				}

			}
//			else{
//				Data.put("msg","error serv");
//				Data.put("status",false);
//			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println(e.getMessage());
			Data.put("msg","Usuario incorrecto, por favor verifique login y clave.");
			Data.put("status",false);

		}


		return new ResponseEntity<Map<String,Object>>(Data,HttpStatus.OK);
	} 
	
   
	
	

	@ResponseBody 
	@RequestMapping(value="MenuRol")
//	public ResponseEntity<List<Modulo>>  ModalAddR(Model model,HttpServletRequest reques,@RequestParam String idrol){
	public ResponseEntity<List<Modulo>>  ModalAddR(Model model,HttpServletRequest reques,@RequestParam String idrol){
//		Persona persona=(Persona)reques.getSession().getAttribute("xusuario");	
		System.out.println("idrol: "+idrol);
		int codrol=Integer.parseInt(idrol);
		List<Modulo> xmenus = this.manejadorModulos.LisRolmenus(codrol);
		for (int i = 0; i < xmenus.size(); i++) {
			Modulo mx = xmenus.get(i);
			mx.setProcesos( this.manejadorProcesos.getProcesosByMenu( mx.getIdmod(),codrol));
//			System.out.println("modulos: "+mx);
			for (int j = 0; j < mx.getProcesos().size(); j++) {
				Proceso p=mx.getProcesos().get(j);
				p.setOpciones(this.manejadorOpciones.getOpcionesByRolMenuProc(codrol,mx.getIdmod(),p.getIdproc()));
			}
		}
		List<Modulo> m=new ArrayList<Modulo>();
		m.add(new Modulo(1, "m1", "", 1, null));
		respuesta e= new respuesta();
		e.setMsg("men1");
		e.setStatus(true);
		return new ResponseEntity<List<Modulo>>(xmenus,HttpStatus.OK);
	}
	
	@ResponseBody 
	@RequestMapping(value="obtenerRoles")
	public ResponseEntity<List<Rol>> obtenerRol(HttpServletRequest request,HttpServletResponse response){
		System.out.println("llegue ObtenerRoles");
		HttpSession sesion=request.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		List<Rol> Roles=null;
		try {
			if (xuser==null) {
				System.out.println(" usuarui nullo");
				response.sendRedirect("/index");
				return new ResponseEntity<List<Rol>>(HttpStatus.NOT_FOUND);
			} else {
				System.out.println("usuario ok");
				Roles=this.manejadorRoles.ListarRolUsuario(xuser.getIdper());			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("newRol: "+Roles);
		return new ResponseEntity<List<Rol>>(Roles,HttpStatus.OK);
	}
	@ResponseBody 
	@RequestMapping(value="getUserSession")
	public ResponseEntity<Map<String,Object>> getUserSession(HttpServletRequest request,HttpServletResponse response){
		System.out.println("llegue ObtenerRoles");
		HttpSession sesion=request.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		Map<String, Object> resp=new HashMap<>();
		try {
			if (xuser!=null) {
				resp.put("user", xuser);
			}else {
				System.out.println(" usuarui nullo");
				response.sendRedirect("/index");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
}
