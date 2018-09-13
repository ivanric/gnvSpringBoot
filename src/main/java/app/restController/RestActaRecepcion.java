package app.restController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorActaRecepcion;
import app.manager.ManejadorServicios;
import app.manager.ManejadorSolicitudes;
import app.models.ActaRecepcion;
import app.models.OrdenServicio;
import app.models.Persona;
import app.models.Solicitud;
import app.utilidades.GeneradorReportes;
import app.utilidades.URIS;


@RequestMapping("/RestActaRecepcion/")
@RestController
public class RestActaRecepcion {
	@Autowired
	ManejadorActaRecepcion manejadorActaRecepcion;
	@Autowired
	ManejadorServicios manejadorServicios;
	@Autowired 
	ManejadorSolicitudes manejadorSolicitudes;
	
	@RequestMapping(value="listar")
	public ResponseEntity<List<ActaRecepcion>> listarAR(HttpServletRequest req,HttpServletResponse res){	
		List<ActaRecepcion> ListActaRecep=this.manejadorActaRecepcion.Lista(req);
		System.out.println("listaPRE: "+ListActaRecep.toString());
		for (int i = 0; i < ListActaRecep.size(); i++) {
			ListActaRecep.get(i).setOrdenServicio(this.manejadorServicios.getOrdenServicioAR(ListActaRecep.get(i).getIdordserv()));
			ListActaRecep.get(i).getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(ListActaRecep.get(i).getIdordserv()));			
		}
		System.out.println("listaPOST: "+ListActaRecep.toString());
		return new ResponseEntity<List<ActaRecepcion>>(ListActaRecep,HttpStatus.OK);
	}
	@RequestMapping(value="datosModal")
	public ResponseEntity<List<Object>> dataOrdenServicio(){
		List<Object> lista=new ArrayList<Object>();
		Map<String, Object> mapa=new HashMap<>();
		Date date = new Date();
		String fecha= new SimpleDateFormat("yyyy-MM-dd").format(date);
		mapa.put("numRecep", this.manejadorActaRecepcion.numeroRecep());
		mapa.put("fecha",fecha);

		lista.add(mapa);
		System.out.println("ListaOrdenServicios: "+lista.toString());
		return new ResponseEntity<List<Object>>(lista,HttpStatus.OK);
	}
	@RequestMapping(value="FiltroOrdenServicio")
	public ResponseEntity<List<OrdenServicio>> FiltroSolicitud(HttpServletRequest req,HttpServletResponse res){
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String texto=req.getParameter("texto");
		System.out.println("texto: "+texto);
		 
		List<OrdenServicio> ListOrdenServicio=null;
		try {
			System.out.println("entro try");
			ListOrdenServicio=this.manejadorServicios.FiltroOrdenServicioAR(req.getParameter("texto"));
			System.out.println("Lista: "+ListOrdenServicio.toString());
			for (int i = 0; i < ListOrdenServicio.size(); i++) {
				ListOrdenServicio.get(i).setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(ListOrdenServicio.get(i).getIdordserv()));		
			}
			System.out.println("TAM: "+ListOrdenServicio.size());
			System.out.println("KU: "+ListOrdenServicio);
		} catch (Exception e) {
			System.out.println("entro catch");
			ListOrdenServicio=null;
		}
		return new ResponseEntity<List<OrdenServicio>>(ListOrdenServicio,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,HttpServletResponse res){
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try{
			Object[] consulta=this.manejadorActaRecepcion.registrar(req,xuser);
			System.out.println("resp: "+consulta);
			respuesta.put("estado", consulta[0]);
			respuesta.put("idRecep",Integer.parseInt(consulta[1].toString()));
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	@RequestMapping(value="Ver")
	public ResponseEntity<List<?>> VerAR(HttpServletRequest req,HttpServletResponse res){
//		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
		List<Object> lista=new ArrayList<>();
//		String Tramitador=us.getAp().toUpperCase()+" "+us.getAm().toUpperCase()+" "+us.getNombres().toUpperCase();
		String idrecep=req.getParameter("idrecep");
		System.out.println("idrecep: "+idrecep);

		ActaRecepcion actaR=this.manejadorActaRecepcion.verActaRecepcion(Integer.parseInt(idrecep));
		OrdenServicio ordServ=this.manejadorServicios.getOrdenServicioAR(actaR.getIdordserv()); 
		Solicitud solt=this.manejadorSolicitudes.getSoltByOrdServ(ordServ.getIdordserv());
		ordServ.setSolicitud(solt);
		actaR.setOrdenServicio(ordServ);
		
		lista.add(actaR);
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
	
	@Autowired
	DataSource dataSource;
	@RequestMapping("Imprimir")
	public  void Imprimir(HttpServletResponse res,HttpServletRequest req){
//		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
//		String Tramitador=us.getAp().toUpperCase()+" "+us.getAm().toUpperCase()+" "+us.getNombres().toUpperCase();
		URIS uris=new URIS(); 
		String id=req.getParameter("idRecep");
		System.out.println("idRecep: "+id);
		  
		String escudo=uris.imgJasperReport+"escudoGobernacion.png";        
		String nombreReporte="Acta de Recepcion",tipo="pdf", estado="inline";
		System.out.println("escudo: "+this.getClass().getResourceAsStream(escudo));
		      
		Map<String, Object> parametros=new HashMap<String, Object>();
		                       
		String url=uris.jasperReport+"actaRecepcion.jasper"; 	
	                                
		parametros.put("idRecep_param",Integer.parseInt(id));
//		parametros.put("tramitador_param",Tramitador);
		parametros.put("escudo_param",this.getClass().getResourceAsStream(escudo));
//		parametros.put("usuario", us.getNombre()+" "+us.getAp()+" "+us.getAm());
	     
		GeneradorReportes g=new GeneradorReportes();
		try{
			g.generarReporte(res, getClass().getResource(url), tipo, parametros, dataSource.getConnection(), nombreReporte, estado);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
