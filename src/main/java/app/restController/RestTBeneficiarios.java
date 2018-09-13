package app.restController;

import java.util.ArrayList;
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

import app.manager.ManejadorBeneficiarios;
import app.manager.ManejadorInstalacionKit;
import app.manager.ManejadorServicios;
import app.manager.ManejadorSolicitudes;
import app.models.Documento;
import app.models.Persona;
import app.models.RegistroKit;
import app.models.Telefono;
import app.models.TransferenciaBeneficiario;
import app.utilidades.GeneradorReportes;
import app.utilidades.URIS;

@RequestMapping("/RestTBeneficiarios/")
@RestController
public class RestTBeneficiarios {
	@Autowired
	ManejadorBeneficiarios manejadorBeneficiarios;
	@Autowired
	ManejadorInstalacionKit manejadorInstalacionKit;
	@Autowired
	ManejadorServicios manejadorServicios;  
	@Autowired 
	ManejadorSolicitudes manejadorSolicitudes;
	 
	@RequestMapping(value="listar")
	public ResponseEntity<List<TransferenciaBeneficiario>> listarTBneneficiarios(HttpServletRequest req,HttpServletResponse res){	
		List<TransferenciaBeneficiario> Tbeneficiarios=this.manejadorBeneficiarios.ListaTB(req);
		System.out.println("Tbeneficiarios: "+Tbeneficiarios);
		for (int i = 0; i < Tbeneficiarios.size(); i++) {
			Tbeneficiarios.get(i).setRegistroKit(this.manejadorInstalacionKit.getRegistroKitTB(Tbeneficiarios.get(i).getIdsolt()));
			Tbeneficiarios.get(i).getRegistroKit().setOrdenServicio(this.manejadorServicios.getOrdenServicioIK(Tbeneficiarios.get(i).getRegistroKit().getIdordserv()));
			Tbeneficiarios.get(i).getRegistroKit().getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(Tbeneficiarios.get(i).getRegistroKit().getIdordserv()));			
		}
		System.out.println("listaTBen: "+Tbeneficiarios.toString());
		return new ResponseEntity<List<TransferenciaBeneficiario>>(Tbeneficiarios,HttpStatus.OK);
	}
	@RequestMapping(value="documentosBeneficiario")
	public ResponseEntity<List<Documento>> docuemtosBeneficiario(HttpServletRequest req,HttpServletResponse res){	
		List<Documento> listaDocumentos=this.manejadorBeneficiarios.listaDocumentosTB();
		return new ResponseEntity<List<Documento>>(listaDocumentos,HttpStatus.OK);
	}
	@RequestMapping(value="FiltroInstalacionKit")
	public ResponseEntity<List<RegistroKit>> FiltroSolicitud(HttpServletRequest req,HttpServletResponse res){
//		HttpSession sesion=req.getSession(true);
//		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String texto=req.getParameter("texto");
		System.out.println("texto: "+texto);
		 
		List<RegistroKit> Lista=null;
		try {
			System.out.println("entro try");
			Lista=this.manejadorInstalacionKit.FiltroRegistroKitTB(req.getParameter("texto"));
			System.out.println("Lista: "+Lista.toString());
			for (int i = 0; i < Lista.size(); i++) {
				Lista.get(i).setOrdenServicio(this.manejadorServicios.getOrdenServicioIK(Lista.get(i).getIdordserv()));
				Lista.get(i).getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(Lista.get(i).getIdordserv()));		
				Lista.get(i).setCilindros(this.manejadorInstalacionKit.ListaCilindro(Lista.get(i).getIdregistroKit()));
			}																
			System.out.println("KU: "+Lista);
		} catch (Exception e) {
			System.out.println("entro catch");
			Lista=null;
		}
		return new ResponseEntity<List<RegistroKit>>(Lista,HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,HttpServletResponse res){
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		String[] documentos=req.getParameterValues("documentos[]");
		String[] telefonos=req.getParameterValues("telefono[]");
		Map<String, Object> respuesta=new HashMap<String, Object>();
		for (String i : documentos) {
			System.out.println("coddocb: "+i);
		}  
		for (String i : telefonos) {
			System.out.println("telefonos: "+i);
		}
		try {
			Object[] consulta=this.manejadorBeneficiarios.registrarTB(req,documentos,telefonos,xuser);
			respuesta.put("estado", consulta[0]);
			respuesta.put("idtrasl",Integer.parseInt(consulta[1].toString()));
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		respuesta.put("estado",true);
		return respuesta;
	}
	
	@RequestMapping(value="Ver")
	public ResponseEntity<List<?>> VerSolicitud(HttpServletRequest req,HttpServletResponse res){
//		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
		List<Object> lista=new ArrayList<>();
		String idtrasl=req.getParameter("idtrasl");
		System.out.println("idtrasl: "+idtrasl);
		TransferenciaBeneficiario tb=this.manejadorBeneficiarios.verTBeneficiario(Integer.parseInt(idtrasl));
		RegistroKit rk=this.manejadorInstalacionKit.getRegistroKitTBbyIdTrasl(Integer.parseInt(idtrasl));
		rk.setOrdenServicio(this.manejadorServicios.getOrdenServicioIK(rk.getIdordserv()));
		rk.getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(rk.getOrdenServicio().getIdordserv())); 
		rk.setCilindros(this.manejadorInstalacionKit.ListaCilindro(rk.getIdregistroKit()));
		tb.setRegistroKit(rk);
		lista.add(tb);
		lista.add(this.manejadorSolicitudes.listaDocumentos());
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
	@Autowired
	DataSource dataSource;
	@RequestMapping("Imprimir")
	public  void Imprimir(HttpServletResponse res,HttpServletRequest req){
		URIS uris=new URIS();
		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
		String Tramitador=us.getAp().toUpperCase()+" "+us.getAm().toUpperCase()+" "+us.getNombres().toUpperCase();    
		String id=req.getParameter("idtrasl");
//		System.out.println("idTrasl: "+id);
		
		TransferenciaBeneficiario tb=this.manejadorBeneficiarios.verTBeneficiario(Integer.parseInt(id));
		RegistroKit rk=this.manejadorInstalacionKit.getRegistroKitTBbyIdTrasl(Integer.parseInt(id));
		rk.setOrdenServicio(this.manejadorServicios.getOrdenServicioIK(rk.getIdordserv()));
		rk.getOrdenServicio().setSolicitud(this.manejadorSolicitudes.getSoltByOrdServ(rk.getOrdenServicio().getIdordserv())); 
		tb.setRegistroKit(rk);
		
		//System.out.println("ID1: "+tb.getPersonaAnteriorBenf().getIdper());
		//System.out.println("ID2: "+tb.getRegistroKit().getOrdenServicio().getSolicitud().getPersona().getIdper());
		
		String ListaTelefonosAB="",ListaTelefonosNB="";
		
		List<Telefono> ListaTelfAB=this.manejadorBeneficiarios.ListaTelf_TB(tb.getPersonaAnteriorBenf().getIdper());
//		System.out.println("ListaTelfAB: "+ListaTelfAB.toString());
		for (int i = 0; i < ListaTelfAB.size(); i++) {
//			System.out.println("ListaTelfAB: "+ListaTelfAB.get(i));
			ListaTelefonosAB+=ListaTelfAB.get(i).getNumero()+" ";
		}
		ListaTelefonosAB=ListaTelefonosAB.trim().replaceAll(" ","-");
//		System.out.println("ListaTelefonosAB: "+ListaTelefonosAB);
		 
		
		
		List<Telefono> ListaTelfNB=this.manejadorBeneficiarios.ListaTelf_TB(tb.getPersonaNuevoBenf().getIdper());
//		System.out.println("ListaTelfNB: "+ListaTelfNB.toString());
		for (int i = 0; i < ListaTelfNB.size(); i++) {
//			System.out.println("ListaTelfNB: "+ListaTelfNB.get(i));
			ListaTelefonosNB+=ListaTelfNB.get(i).getNumero()+" ";
		}
		ListaTelefonosNB=ListaTelefonosNB.trim().replaceAll(" ","-");
//		System.out.println("ListaTelefonosNB: "+ListaTelefonosNB);
	 	 
	
		  
		String escudo=uris.imgJasperReport+"escudobolivia.png";        
		String nombreReporte="TRANSFERENCIA BENEFICIARIO",tipo="pdf", estado="inline";
//		System.out.println("escudo: "+this.getClass().getResourceAsStream(escudo));
		      
		Map<String, Object> parametros=new HashMap<String, Object>();         
		String url=uris.jasperReport+"getTBeneficiario.jasper"; 	
	                                
		parametros.put("idTrasl_param",Integer.parseInt(id));
		parametros.put("telefonosAB_param",ListaTelefonosAB);
		parametros.put("telefonosNB_param",ListaTelefonosNB);
		parametros.put("tramitador_param",Tramitador);
		parametros.put("escudo_param",this.getClass().getResourceAsStream(escudo));
		GeneradorReportes g=new GeneradorReportes();
		try{
			g.generarReporte(res, getClass().getResource(url), tipo, parametros, dataSource.getConnection(), nombreReporte, estado);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
 