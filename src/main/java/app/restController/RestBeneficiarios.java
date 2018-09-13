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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorBeneficiarios;
import app.models.Documento;
import app.models.Persona;

@RequestMapping("/RestBeneficiarios/")
@RestController
public class RestBeneficiarios {
	@Autowired
	ManejadorBeneficiarios manejadorBeneficiarios;
	
	@RequestMapping(value="listar")
	public ResponseEntity<List<Persona>> listarBneneficiarios(HttpServletRequest req,HttpServletResponse res){	
		List<Persona> beneficiarios=this.manejadorBeneficiarios.Lista(req);
		System.out.println("listaBen: "+beneficiarios.toString());
		return new ResponseEntity<List<Persona>>(beneficiarios,HttpStatus.OK);
	}
	@RequestMapping(value="documentosBeneficiario")
	public ResponseEntity<List<Documento>> docuemtosBeneficiario(HttpServletRequest req,HttpServletResponse res){	
		List<Documento> listaDocumentos=this.manejadorBeneficiarios.listaDocumentos();
		return new ResponseEntity<List<Documento>>(listaDocumentos,HttpStatus.OK);
	}
	
	@RequestMapping({"existeCi"})
	public ResponseEntity<Map<String, Object>> existeCi(HttpServletRequest req){
		Map<String, Object> mapa=new HashMap<String, Object>();
		String ci=req.getParameter("ci");
		boolean existe;
		System.out.println("tam_"+ci.length());
		
		if(this.manejadorBeneficiarios.verificarCi(ci)){
			existe=true;
		}else{
			existe=false;			
		}
		System.out.println("existe: "+existe);
		mapa.put("estado", existe);
		return new ResponseEntity<Map<String,Object>>(mapa,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="adicionar")
	public Map<String, Object> adicionar(HttpServletRequest req,HttpServletResponse res,Persona p,@RequestParam String ci){
		System.out.println("Pers: "+p);
//		System.out.println("ci: "+ci);
		String[] documentos=req.getParameterValues("documentos[]");
		String[] telefonos=req.getParameterValues("telefono[]");
//		System.out.println("documentosArray: "+documentos.toString());
		System.out.println("tamanioDocArray: "+documentos.length);
		System.out.println("TelefonosArray: "+telefonos.length);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		for (String i : documentos) {
			System.out.println("coddocb: "+i);
		}
		for (String i : telefonos) {
			System.out.println("telefonos: "+i);
//			System.out.println("vacio? : "+i.equals(null));
			System.out.println("vacio? : "+!i.equals(""));
			System.out.println("vacio? : "+i.equals(""));
		}
		try {
			boolean consulta=this.manejadorBeneficiarios.registrar(req, p, documentos,telefonos);
			System.out.println(consulta);
			respuesta.put("estado", consulta);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		respuesta.put("estado",true);
		return respuesta;
	}
	@RequestMapping(value="datos-mod")
	public ResponseEntity<List<?>> datosMod(HttpServletRequest req){
		List<Object> lista=new ArrayList<Object>();
		Persona BeneficiarioDatos=this.manejadorBeneficiarios.datosModificar(req);
		List<?> ListaTelefono=this.manejadorBeneficiarios.ListaTelefonos(BeneficiarioDatos.getIdper());
		List<Documento> listaDocumentos=this.manejadorBeneficiarios.getDocumentos(Integer.parseInt(req.getParameter("idben")));
		lista.add(BeneficiarioDatos);
		lista.add(ListaTelefono);
		lista.add(listaDocumentos);
		System.out.println("Persona a Modificar:"+lista);
		return new ResponseEntity<List<?>>(lista,HttpStatus.OK);		
	}
	@Transactional
	@RequestMapping(value="modificar")
	public Map<String, Object> modificar(HttpServletRequest req,HttpServletResponse res,Persona p,@RequestParam String ci){
		Map<String, Object> respuesta=new HashMap<String, Object>();
		String[] documentos=req.getParameterValues("documentos[]");
		String[] telefonos=req.getParameterValues("telefono[]");
		for (String i : documentos) {
			System.out.println("coddocb_modifocado: "+i);
		}
		
		try {
			boolean consulta=this.manejadorBeneficiarios.modificar(req, p, documentos,telefonos);
			System.out.println(consulta);
			respuesta.put("estado", consulta);
		} catch (Exception e) {
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
		String idben=req.getParameter("idben");
		System.out.println("idben_servidor: "+idben);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorBeneficiarios.eliminar(Integer.parseInt(idben));
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
		String idben=req.getParameter("idben");
		System.out.println("idben_servidor: "+idben);
		Map<String, Object> respuesta=new HashMap<String, Object>();
		try {
			boolean resp=this.manejadorBeneficiarios.habilitar(Integer.parseInt(idben));
			respuesta.put("estado", resp);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			respuesta.put("estado",false);
		}
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);	
	}
	

	
}
