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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import app.manager.ManejadorBeneficiarios;
import app.manager.ManejadorServicios;
import app.manager.ManejadorSolicitudes;
import app.models.CombustibleVehiculo;
import app.models.Documento;
import app.models.OpcionesVehiculo;
import app.models.Persona;
import app.models.Solicitud;
import app.models.Telefono;
import app.models.Vehiculo;
import app.utilidades.GeneradorReportes;
import app.utilidades.URIS;


@RequestMapping("/RestSolicitudes/")
@RestController 
public class RestSolicitudes {        
	@Autowired      
	ManejadorSolicitudes manejadorSolicitudes;
	@Autowired
	ManejadorBeneficiarios manejadorBeneficiarios;
	@Autowired
	ManejadorServicios manejadorServicios;
	 
	@RequestMapping(value="listar")
	public ResponseEntity<List<Solicitud>> listarBneneficiarios(HttpServletRequest req,HttpServletResponse res){	
		List<Solicitud> solicitudes=this.manejadorSolicitudes.Listar(req);
		System.out.println("listaSolt: "+solicitudes.toString());
		return new ResponseEntity<List<Solicitud>>(solicitudes,HttpStatus.OK);
	}
	@RequestMapping({"busqueda_benficiario"})
	public ResponseEntity<List<?>> busqueda_benficiario(HttpServletRequest req,HttpServletResponse res){
		List<?> listaBen=this.manejadorBeneficiarios.Listabenficiario(req);
//		System.out.println("l: "+listaBen);
		return new ResponseEntity<List<?>>(listaBen,HttpStatus.OK);
	}
	@RequestMapping({"opcionesVehiculo"})
	public ResponseEntity<OpcionesVehiculo> opcionesVehiculo(HttpServletRequest req,HttpServletResponse res){
		OpcionesVehiculo lista= new OpcionesVehiculo();
		lista.setTipoVehiculo(this.manejadorSolicitudes.tipoVehiculo());
		lista.setMarcaVehiculo(this.manejadorSolicitudes.marcaVehiculo());
		lista.setModeloVehiculo(this.manejadorSolicitudes.modeloVehiculo());
		lista.setTipoMotor(this.manejadorSolicitudes.tipoMotorVehiculo());
		lista.setTipoServicio(this.manejadorSolicitudes.tipoServicioVehiculo());
		lista.setCombustibles(this.manejadorSolicitudes.tipoCombustible());
		lista.setNumSolt(this.manejadorSolicitudes.numeroSolicitud());
		System.out.println("lista_listas: "+lista);
		return new ResponseEntity<OpcionesVehiculo>(lista,HttpStatus.OK);
	}
	@RequestMapping ({"existePlaca"})
	public ResponseEntity<Map<String, Object>> existe(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<String, Object>();
		String placa=req.getParameter("placa");
		int existe;
		System.out.println("tam_"+placa.length());
		//VERIFICA PRIMERO SI EXISTE ALGUNA PLACA QUE ESTE DISPONIBLE 
		if(this.manejadorSolicitudes.verificarPlaca(placa)){
			existe=2;
		}else{
			//SI LA PLACA ESTA EN USO SE VERIFICA EN QUE  ESTADO ESTA PLACA
			existe=this.manejadorSolicitudes.EstadoPlaca(placa);			
		}
		System.out.println("existe: "+existe);
		mapa.put("estado", existe);
		return new ResponseEntity<Map<String,Object>>(mapa,HttpStatus.OK);
	}
	@RequestMapping({"PlacaDatos"})
	public ResponseEntity<Vehiculo> DatosVeh(HttpServletRequest req){
		String placa=req.getParameter("placa");
		System.out.println("la placa es : "+placa);
		Vehiculo veh=this.manejadorSolicitudes.DatosVehiculo(placa);
		return new ResponseEntity<Vehiculo>(veh,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,HttpServletResponse res,Solicitud s,Vehiculo v,@RequestParam int [] combustible){
		System.out.println("SolicitudSolicitud: "+s.toString());
		System.out.println("VehiculoSolicitud: "+v.toString());
		
		HttpSession sesion=req.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		
		System.out.println("tamanio: "+combustible.length);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		
		for (int i : combustible) {
			System.out.println("cod_combustible: "+i);
		}
		try {
			Object[] RespSolicitud=this.manejadorSolicitudes.registrar(req,xuser,v,s,combustible);
			respuesta.put("estado", RespSolicitud[0]);
			respuesta.put("idsolt", Integer.parseInt(RespSolicitud[1].toString()));
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}

		return respuesta;
	}

	@Transactional
	@RequestMapping(value="anular")
	public Map<String, Object> anular(HttpServletRequest req,HttpServletResponse res){
		String idsolt=req.getParameter("idsolt");
		System.out.println("idSolt_servidor"+idsolt);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean solicitud=this.manejadorSolicitudes.anular(Integer.parseInt(idsolt));
			respuesta.put("estado", solicitud);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// TODO: handle exception
			respuesta.put("estado",false);
		}
//		respuesta.put("estado", true);
		return respuesta;
	}
	
	

	@RequestMapping(value="FiltroSolicitudOS")
	public ResponseEntity<List<Solicitud>> FiltroSolicitud(HttpServletRequest req,HttpServletResponse res){
		String texto=req.getParameter("texto");
		System.out.println("texto: "+texto);
		 
		List<Solicitud> solt=null;
		try {
			solt=this.manejadorSolicitudes.FiltroSolicitudOS(req.getParameter("texto"));
			System.out.println("TAM: "+solt.size());
			System.out.println("KU: "+solt);
		} catch (Exception e) {
			solt=null;
		}
		return new ResponseEntity<List<Solicitud>>(solt,HttpStatus.OK);
	}
	@RequestMapping(value="Ver")
//	public ResponseEntity<List<List<?>>> VerSolicitud(HttpServletRequest req,HttpServletResponse res){
	public ResponseEntity<List<?>> VerSolicitud(HttpServletRequest req,HttpServletResponse res){
//		List<List<?>> lista=new ArrayList<List<?>>();
		List<Object> lista=new ArrayList<>();
		String ListaTelefonos="";
		String idsolt=req.getParameter("idsolt");
		System.out.println("idsolt: "+idsolt);
		  
		List<Telefono> ListaTelf=this.manejadorSolicitudes.ListaTelf(Integer.parseInt(idsolt));
		Solicitud solt=this.manejadorSolicitudes.verSolicitud(Integer.parseInt(idsolt));
		System.out.println("ListaTelefonos: "+ListaTelf.toString());
		
		for (int i = 0; i < ListaTelf.size(); i++) {
			System.out.println("ListaTelS: "+ListaTelf.get(i));
			ListaTelefonos+=ListaTelf.get(i).getNumero()+" ";
		}
		ListaTelefonos=ListaTelefonos.trim().replaceAll(" ","-");
		System.out.println("ListaTelefonos: "+ListaTelefonos);
		Map<String, Object> mapa1=new HashMap<>();
		mapa1.put("listaTelefonos", ListaTelefonos);
		List<Documento> listaDoc=this.manejadorBeneficiarios.listaDocumentos();
		List<CombustibleVehiculo> listaComb=this.manejadorSolicitudes.listaCombustible();
		lista.add(solt);
		lista.add(mapa1);
		lista.add(listaDoc);
		lista.add(listaComb);
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);
	}
	@Autowired
	DataSource dataSource;
	@RequestMapping(value="Imprimir/{id}",method=RequestMethod.GET)
	public  void Imprimir(HttpServletResponse res,HttpServletRequest req,@PathVariable("id") Integer id){
		URIS uris=new URIS();
		Persona us=(Persona)req.getSession(true).getAttribute("xusuario");
		String ListaTelefonos="",Tramitador=us.getAp().toUpperCase()+" "+us.getAm().toUpperCase()+" "+us.getNombres().toUpperCase();
		int idsolt=id;
		System.out.println("idsoltIMPRIMIR: "+idsolt);
		String nombreReporte="Solicitud",tipo="pdf", estado="inline";
		
		Map<String, Object> nitSQL=this.manejadorServicios.nitEmpresa(1); 
		String nit_patam=(String) nitSQL.get("nitInst");
//		System.out.println("nit_param: "+nit_patam);
		
		List<Telefono> ListaTelf=this.manejadorSolicitudes.ListaTelf(idsolt);
//		System.out.println("ListaTelefonos: "+ListaTelf.toString());
		
		for (int i = 0; i < ListaTelf.size(); i++) {
			System.out.println("ListaTelS: "+ListaTelf.get(i));
			ListaTelefonos+=ListaTelf.get(i).getNumero()+" ";
		}
		ListaTelefonos=ListaTelefonos.trim().replaceAll(" ","-");
	
		String direccionBol=uris.imgJasperReport+"escudobolivia.png";
		System.out.println("Dire: "+direccionBol);             
		
		System.out.println("escudo: "+this.getClass().getResourceAsStream(direccionBol));
		String subReportInst=uris.jasperReport+"getEmpresa.jasper";
		
		System.out.println("subReport: "+subReportInst);
		Map<String, Object> parametros=new HashMap<String, Object>();
		                      	
		String url=uris.jasperReport+"solicitud.jasper"; 	
	                                
		parametros.put("nit_param",nit_patam);
		parametros.put("idsolt_param",idsolt);
		parametros.put("telefonos_param",ListaTelefonos);
		parametros.put("tramitador_param",Tramitador);

		parametros.put("input_param",this.getClass().getResourceAsStream(direccionBol));
		parametros.put("subreport_inst_param",this.getClass().getResourceAsStream(subReportInst));

		GeneradorReportes g=new GeneradorReportes();
		try{
			
			g.generarReporte(res, getClass().getResource(url), tipo, parametros, dataSource.getConnection(), nombreReporte, estado);	
		  
//			InputStream jasperStream = this.getClass().getResourceAsStream(url);
//
//		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
//
//		    res.setContentType("application/pdf");
//		    res.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
//
//		    final ServletOutputStream outStream = res.getOutputStream();
//		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
}
