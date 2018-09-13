package app.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorPermisos;
import app.manager.ManejadorRoles;
import app.models.Modulo;
import app.models.Opcion;
import app.models.Proceso;
import app.models.Rol;

@RequestMapping("/RestPermisos/")
@RestController
public class RestPermisos {
	@Autowired
	ManejadorRoles manejadorRoles;
	@Autowired 
	ManejadorPermisos manejadorPermisos;
		
	@RequestMapping("dataPermise")
	public ResponseEntity<List<List<?>>> dataPermise(){
		List<List<?>> lista=new ArrayList<>(); 
		List<Rol> Roles=this.manejadorRoles.ListaRoles();
		List<Modulo> Modulos=this.manejadorRoles.ListaMenus();
		List<Proceso> Procesos=this.manejadorRoles.ListaProcesos();
		List<Opcion> Opciones=this.manejadorRoles.ListaOpciones();
		lista.add(Roles);
		lista.add(Modulos);
		lista.add(Procesos);
		lista.add(Opciones);
		return new ResponseEntity<List<List<?>>>(lista,HttpStatus.OK);
	}
	@RequestMapping("dataMenus")
	public ResponseEntity<List<List<?>>> dataMenus(HttpServletRequest req){
		int idrol=Integer.parseInt(req.getParameter("idrol"));
		System.out.println("idrol: "+idrol);
		List<List<?>> lista=new ArrayList<>(); 
		List<Modulo> Menus=this.manejadorRoles.ListaMenus();
		List<Modulo> MenuRol=this.manejadorRoles.ListaMenuRol(idrol);
		lista.add(Menus);
		lista.add(MenuRol);
		return new ResponseEntity<List<List<?>>>(lista,HttpStatus.OK);
	}
	@RequestMapping("dataSubMenus")
	public ResponseEntity<List<List<?>>> dataSubMenus(HttpServletRequest req){
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		System.out.println("idMod: "+idMod);
		List<List<?>> lista=new ArrayList<>(); 
		List<Proceso> Menus=this.manejadorRoles.ListaProcesos();
		List<Proceso> MenuRol=this.manejadorRoles.ListaProcMod(idRol,idMod);
		lista.add(Menus);
		lista.add(MenuRol);
		return new ResponseEntity<List<List<?>>>(lista,HttpStatus.OK);
	}
	@RequestMapping("dataOpciones")
	public ResponseEntity<List<List<?>>> dataOpciones(HttpServletRequest req){
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		int idProc=Integer.parseInt(req.getParameter("idProc"));
		System.out.println("idProc: "+idProc);
		List<List<?>> lista=new ArrayList<>(); 
		List<Opcion> Opciones=this.manejadorRoles.ListaOpcionesByIdproc(idProc);
		List<Opcion> ProcOpc=this.manejadorRoles.ListaOpcProc(idRol,idMod,idProc);
		lista.add(Opciones);
		lista.add(ProcOpc);
		return new ResponseEntity<List<List<?>>>(lista,HttpStatus.OK);
	}
	@RequestMapping("addOpcion")
	public ResponseEntity<Map<String, Object>> addOpcion(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<>();
		boolean resp=this.manejadorPermisos.addOpcion(req);
		mapa.put("status", resp);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.OK);
	}
	@RequestMapping("removeOpcion")
	public ResponseEntity<Map<String, Object>> removeOpcion(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<>();
		boolean resp=this.manejadorPermisos.removeOpcion(req);
		mapa.put("status", resp);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.OK);
	}
	@RequestMapping("removeProceso")
	public ResponseEntity<Map<String, Object>> removeProceso(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<>();
		boolean resp=this.manejadorPermisos.removeProceso(req);
		mapa.put("status", resp);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.OK);
	}
	@RequestMapping("removeModulo")
	public ResponseEntity<Map<String, Object>> removeModulo(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<>();
		boolean resp=this.manejadorPermisos.removeModulo(req);
		mapa.put("status", resp);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.OK);
	}
	
}
