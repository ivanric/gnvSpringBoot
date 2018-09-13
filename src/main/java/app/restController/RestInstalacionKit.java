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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorInstalacionKit;
import app.manager.ManejadorServicios;
import app.manager.ManejadorSolicitudes;
import app.models.Cilindro;
import app.models.InstalacionCilindro;
import app.models.OrdenServicio;
import app.models.Persona;
import app.models.Reductor;
import app.models.RegistroKit;
import app.models.Solicitud;

@RequestMapping("/RestInstalacionKit/")
@RestController
public class RestInstalacionKit {
	@Autowired
	ManejadorInstalacionKit manejadorInstalacionKit;
	@Autowired
	ManejadorServicios manejadorServicios;
	@Autowired 
	ManejadorSolicitudes manejadorSolicitudes;
	
	@RequestMapping(value="listar")
	public ResponseEntity<List<RegistroKit>> listarBneneficiarios(HttpServletRequest req,HttpServletResponse res){	
		List<RegistroKit> ListRegistro=this.manejadorInstalacionKit.Lista(req);
		System.out.println("listaPRE: "+ListRegistro.toString());
		for (int i = 0; i < ListRegistro.size(); i++){
			ListRegistro.get(i).setOrdenServicio(this.manejadorServicios.getOrdenServicioIK(ListRegistro.get(i).getIdordserv()));
			ListRegistro.get(i).getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(ListRegistro.get(i).getIdordserv()));			
		}
		System.out.println("listaPOST: "+ListRegistro.toString());
		return new ResponseEntity<List<RegistroKit>>(ListRegistro,HttpStatus.OK);
	}
	@RequestMapping(value="datosModal")
	public ResponseEntity<List<Object>> dataOrdenServicio(){
		List<Object> lista=new ArrayList<Object>();
		Map<String, Object> mapa=new HashMap<>();
		Date date = new Date();
		String fecha= new SimpleDateFormat("yyyy-MM-dd").format(date);
		mapa.put("fecha",fecha);
		
		List<Cilindro> listaCil=this.manejadorServicios.ListaCilindros();
		List<Reductor> listaRed=this.manejadorServicios.ListaReductores();
		
		lista.add(mapa);
		lista.add(listaRed);
		lista.add(listaCil);
		System.out.println("Lista: "+lista.toString());
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
			ListOrdenServicio=this.manejadorServicios.FiltroOrdenServicioIK(req.getParameter("texto"));
			for (int i = 0; i < ListOrdenServicio.size(); i++) {
				ListOrdenServicio.get(i).setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(ListOrdenServicio.get(i).getIdordserv()));		
			}
			System.out.println("TAM: "+ListOrdenServicio.size());
			System.out.println("KU: "+ListOrdenServicio);
		} catch (Exception e) {
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
			Object[] consulta=this.manejadorInstalacionKit.registrar(req,xuser);
			System.out.println("resp: "+consulta);
//			respuesta.put("estado", true);
			respuesta.put("estado", consulta[0]);
//			respuesta.put("idRecep",Integer.parseInt(consulta[1].toString()));
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return respuesta;
	}
	@RequestMapping(value="Ver")
	public ResponseEntity<List<?>> VerSolicitud(HttpServletRequest req,HttpServletResponse res){
//		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
		List<Object> lista=new ArrayList<>();
//		String Tramitador=us.getAp().toUpperCase()+" "+us.getAm().toUpperCase()+" "+us.getNombres().toUpperCase();
		String idregistroKit=req.getParameter("idregistroKit");
		System.out.println("idregistroKit: "+idregistroKit);

		RegistroKit rg=this.manejadorInstalacionKit.verInstacionKit(Integer.parseInt(idregistroKit));
		OrdenServicio ordServ=this.manejadorServicios.getOrdenServicioIK(rg.getIdordserv()); 
		Solicitud solt=this.manejadorSolicitudes.getSoltByOrdServ(ordServ.getIdordserv());
		ordServ.setSolicitud(solt);
		List<InstalacionCilindro> InsCil=this.manejadorInstalacionKit.ListaCilindro(rg.getIdregistroKit());
		rg.setOrdenServicio(ordServ);
		rg.setCilindros(InsCil);
		lista.add(rg);
		//lista.add(this.manejadorServicios.ListaCilindros());
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
}
