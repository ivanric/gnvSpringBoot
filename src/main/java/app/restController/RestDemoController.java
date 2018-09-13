package app.restController;




import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorRoles;
import app.manager.ManejadorUsuarios;
import app.models.Persona;
import app.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Rob Winch
 */

@RestController
@RequestMapping(value="/demo/")
public class RestDemoController {
	@Autowired 
	ManejadorUsuarios manejadorUsuarios;
	@Autowired 
	ManejadorRoles manejadorRoles;
	
	
	@RequestMapping(value="/loginjwt")
//	public ResponseEntity<Map<String, Object>> da(Usuario u){
	public ResponseEntity<Map<String, Object>> da(Usuario u){
		Map<String , Object> resp=new HashMap<>();
		System.out.println("User: "+u.toString());
		Persona xusuario=this.manejadorUsuarios.iniciarSession("admin","admin");
		if (xusuario!=null) {
			String Clave="my clave";
			long tiempo =System.currentTimeMillis();
			System.out.println("Tiempo: "+tiempo);
			String jwt=Jwts.builder()
							   .signWith(SignatureAlgorithm.HS256,Clave)//clave como se encriptara
							   .setSubject("Ivan Mamani Corzo")//clain
							   .setIssuedAt(new Date(tiempo))//tiempo creado
							   .setExpiration(new Date(tiempo+300000))//tiempo de expiracion de 15 minutos
							   .claim("email", "ivan@gmail.com")//clain adicional
							   .compact();
//			JsonObject json=Json.cre
			resp.put("TOKEN", jwt);
			return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
		}					   
		return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/validarjwt")
	public String validarjwt(HttpServletRequest req,String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
//		String jwt=req.getParameter("json");
		Claims claims=Jwts.parser()
					      .setSigningKey("my clave".getBytes("UTF-8"))
					      .parseClaimsJws(jwt).getBody();
		
//		String data1=claims.getId();
		System.out.println("ID: "+claims.getId());
		System.out.println("Subject: "+claims.getSubject());
		System.out.println("Subject: "+claims.get("email"));
		return "aqui";
					      
	}
	
	@RequestMapping(value = "/demo", produces = "application/json")
	public Persona helloUser(Principal principal,HttpServletRequest req,Usuario u) {
		System.out.println("User: "+u.toString());
		Persona xusuario=this.manejadorUsuarios.iniciarSession("admin","admin");
//		List<?> l=new ArrayList<>();
		HttpSession session=req.getSession();
		//AQUI SE CREA LA SESSION
		session.setAttribute("user",xusuario);
		Persona p=(Persona) session.getAttribute("user");
		System.out.println("Session recuperada:"+p);
		return p;
	}

	@RequestMapping("/logout")
	public String logout(Principal p,HttpServletRequest req) {
		HttpSession session=req.getSession();
		Persona per=new Persona();
		//AQUI RECUPERA LA SESSION
		try {
			per=(Persona) session.getAttribute("user");
			System.out.println("RECUPERADO SESSION: "+per.toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
		
		
//		session.invalidate();
		return "HOLA";
				
	}
}