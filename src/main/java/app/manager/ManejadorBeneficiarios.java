package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.models.Beneficiario;
import app.models.Documento;
import app.models.Persona;
import app.models.Telefono;
import app.models.TransferenciaBeneficiario;


@Service
public class ManejadorBeneficiarios {
private JdbcTemplate db;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public class objPersona implements RowMapper<Persona>{
		@Override
		public Persona mapRow(ResultSet rs, int arg1) throws SQLException {
			Persona p= new Persona();
			p.setIdper(rs.getInt("idper"));
			p.setCi(rs.getString("ci"));
			p.setCiCod(rs.getString("ciCod"));
			p.setNombres(rs.getString("nombres"));
			p.setAp(rs.getString("ap"));
			p.setAm(rs.getString("am"));
			p.setGenero(rs.getString("genero"));
			p.setDireccion(rs.getString("direccion"));
			p.setEmail(rs.getString("email"));
			p.setFoto(rs.getString("foto"));
			p.setEstado(rs.getInt("estado"));
			try {
				p.setBeneficiario(obtenerBeneficiario(rs.getInt("idper")));
			} catch (Exception e) {
				p.setBeneficiario(null);
			}
			try {
				p.setListaTelf(metListaTelefonos(rs.getInt("idper")));
			} catch (Exception e) {
				p.setListaTelf(null);
			}
			
			return p;
	    }
	}
	public class objBeneficiario implements RowMapper<Beneficiario>{
		@Override
		public Beneficiario mapRow(ResultSet rs, int arg1) throws SQLException {
			Beneficiario b= new Beneficiario();
			b.setIdben(rs.getInt("idben"));
			b.setInstitucion(rs.getString("institucion"));
			b.setDescripcion(rs.getString("descripcion"));
			b.setEstado(rs.getInt("estado"));
			b.setIdper(rs.getInt("idper"));
			try {
				b.setDocumentos(getDocumentos(rs.getInt("idben")));
			} catch (Exception e) {
				// TODO: handle exception
				b.setDocumentos(null);
			}
			return b;
	    }
	}
	public class objDocumento implements RowMapper<Documento>{
		@Override
		public Documento mapRow(ResultSet rs, int arg1) throws SQLException {
			Documento d= new Documento();
			d.setIddocb(rs.getInt("iddocb"));
			d.setNombre(rs.getString("nombre"));
			d.setEstado(rs.getInt("estado"));
			return d;
	    }
	}
	public class objTelefono implements RowMapper<Telefono>{
		@Override
		public Telefono mapRow(ResultSet rs, int arg1) throws SQLException {
			Telefono t= new Telefono();
			t.setIdper(rs.getInt("idper"));
			t.setNumero(rs.getString("numero"));
			return t;
	    }
	}
	public class objTBeneficiario implements RowMapper<TransferenciaBeneficiario>{
		@Override
		public TransferenciaBeneficiario mapRow(ResultSet rs, int arg1) throws SQLException {
			TransferenciaBeneficiario tf= new TransferenciaBeneficiario();
			tf.setIdtrasl(rs.getInt("idtrasl"));
			tf.setIdsolt(rs.getInt("idsolt"));
			tf.setIdbenActual(rs.getInt("idbenActual"));
			tf.setIdbenNuevo(rs.getInt("idbenNuevo"));
			tf.setFechaTraslado(rs.getString("fechaTraslado"));
			tf.setMotivoTraslado(rs.getString("motivoTraslado"));
			tf.setLogin(rs.getString("login"));
			try {
				tf.setPersonaAnteriorBenf(metPersona(rs.getInt("idbenActual")));
			} catch (Exception e){
				tf.setPersonaAnteriorBenf(null);
			}
			try {
				tf.setPersonaNuevoBenf(metPersona(rs.getInt("idbenNuevo")));
			} catch (Exception e){
				tf.setPersonaNuevoBenf(null);
			}
			return tf;
	    }
	}
	 
	public List<Map<String,Object>> data(){
		System.out.println("entra data");
		return this.db.queryForList("select * from beneficiario", new Object[]{});
	}
	
	public Beneficiario obtenerBeneficiario(int idper){
		return this.db.queryForObject("select * from beneficiario where idper=?", new objBeneficiario(),idper);
	}
	public List<Documento> getDocumentos(int idben){
		return this.db.query("SELECT d.* FROM docBeneficiario d,beneficiario b,bendoc bd WHERE d.iddocb=bd.iddocb and b.idben=bd.idben and b.idben=?", new objDocumento(),idben);
	}
	public List<Persona> Lista(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="select p.*,b.* from persona p join beneficiario b on b.idper=p.idper where (concat(p.ap,' ',p.am,' ',p.nombres) LIKE ? or p.ci LIKE ?) and (b.estado=? or ?=-1) ORDER BY b.idben ASC";
		return this.db.query(sql, new objPersona(),"%"+filtro+"%","%"+filtro+"%",estado,estado);
	}
	
	public List<Documento> listaDocumentos(){
		String sql="SELECT * FROM docBeneficiario WHERE estado=1 and tipo='b' ORDER BY iddocb ASC";
		//String sql="SELECT * FROM docBeneficiario WHERE estado=1  ORDER BY iddocb ASC";
		return this.db.query(sql,new objDocumento());
	}

	
	
	public boolean verificarCi(String ci){
		System.out.println("entro sql_placa:"+ci);
		String sql="";
		try {
			sql="SELECT COUNT(ci) FROM persona WHERE ci=?";
			int data=this.db.queryForObject(sql,Integer.class,ci);
			System.out.println("ver????:"+data);
			if(data!=0){
				return true;	
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("entro catch exiteCi");	
			return false;
		}
		
	}
	public boolean registrar(HttpServletRequest req,Persona p,String [] iddocb,String tel[]){
		int idper= generarIdPer();
		int idben= generarIdBen();
		String sql="";
		try {
			sql="INSERT INTO persona(idper,ci,ciCod,nombres,ap,am,genero,direccion,email,estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idper,p.getCi(),p.getCiCod(),p.getNombres().toUpperCase(),p.getAp().toUpperCase(),p.getAm().toUpperCase(),p.getGenero(),p.getDireccion().toUpperCase(),p.getEmail(),1);
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],idper);
				}
			}	
			sql="INSERT INTO beneficiario(idben,institucion,descripcion,estado,idper) VALUES(?,?,?,?,?)";
			this.db.update(sql,idben,req.getParameter("institucion").toUpperCase(),req.getParameter("descripcion").toUpperCase(),1,idper);
			sql="INSERT INTO bendoc(idben,iddocb) VALUES(?,?)";
			for (int i = 0; i < iddocb.length; i++) {
				this.db.update(sql,idben,Integer.parseInt(iddocb[i]));	
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Persona datosModificar(HttpServletRequest req){
		String sql="";
		Persona p=null;
		int idben=Integer.parseInt(req.getParameter("idben"));
		System.out.println("idben: "+idben);
		try {
			sql="SELECT p.* FROM persona p,beneficiario b WHERE b.idper=p.idper and b.idben=?";
			p=this.db.queryForObject(sql,new objPersona(),idben);
		} catch (Exception e) {
			p=null;
		}
		return p;
	}
	public List<Map<String, Object>> ListaTelefonos(int idper){
		String sql="select * from telefono where idper=?";
		return this.db.queryForList(sql,new Object[] {idper});
	}

	public boolean modificar(HttpServletRequest req,Persona p,String [] iddocb,String [] tel){
		String sql="";
		try {
			sql="UPDATE persona SET ci=?,ciCod=?,nombres=?,ap=?,am=?,genero=?,direccion=?,email=? WHERE idper=?";
			this.db.update(sql,p.getCi(),p.getCiCod(),p.getNombres().toUpperCase(),p.getAp().toUpperCase(),p.getAm().toUpperCase(),p.getGenero(),p.getDireccion().toUpperCase(),p.getEmail(),req.getParameter("idper"));
			sql="delete from telefono where idper=?";
			this.db.update(sql,new Object[] {req.getParameter("idper")});
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],req.getParameter("idper"));
				}
			}
			sql="UPDATE beneficiario SET institucion=?,descripcion=? WHERE idben=?";
			this.db.update(sql,req.getParameter("institucion"),req.getParameter("descripcion"),req.getParameter("idben"));
//			sql="INSERT INTO bendoc(idben,iddocb) VALUES(?,?)";
//			for (int i = 0; i < iddocb.length; i++) {
//				this.db.update(sql,idben,iddocb[i]);	
//			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean eliminar(Integer id){
		String sql="";
		try {
			sql="UPDATE beneficiario SET estado=0 WHERE idben=?";
			int a=this.db.update(sql,id);
			this.db.update(sql,id);
			System.out.println("sql_elimino: "+a);
			if (a==1) {
				return true;	
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean habilitar(Integer id){
		String sql="";
		try {
			sql="UPDATE beneficiario SET estado=1 WHERE idben=?";
			int a=this.db.update(sql,id);
			this.db.update(sql,id);
			System.out.println("sql_habilito: "+a);
			if (a==1) {
				return true;	
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int generarIdPer(){
		String sql="select COALESCE(max(idper),0)+1 as idper from persona";
		return db.queryForObject(sql, Integer.class);
	}
	public int generarIdBen(){
		String sql="select COALESCE(max(idben),0)+1 as idben from beneficiario";
		return db.queryForObject(sql, Integer.class);
	}
	public List<Persona> Listabenficiario(HttpServletRequest req){
		String busq=req.getParameter("texto");
		System.out.println("llego: "+busq=="");
		System.out.println("data: "+busq);
		String sql="select p.* from beneficiario b, persona p where b.idper=p.idper AND b.estado=1 and (concat(p.nombres,' ',p.ap,' ',p.am)LIKE ? or p.ci LIKE ?)";
		return this.db.query(sql,new objPersona(),"%"+busq+"%","%"+busq+"%");
	}
	//TRANSFERENCIA BENEFICIARIO 
	public List<Telefono> metListaTelefonos(int idper){
		String sql="select * from telefono where idper=?";
		return this.db.query(sql,new objTelefono(),idper);
	}
	public int generarIdTraslBen(){
		String sql="select COALESCE(max(idtrasl),0)+1 as idtrasl from trasladoBeneficiario";
		return db.queryForObject(sql, Integer.class);
	}
	public List<Documento> listaDocumentosTB(){
		String sql="SELECT * FROM docBeneficiario WHERE estado=1  ORDER BY iddocb ASC";
		return this.db.query(sql,new objDocumento());
	} 
	public Persona metPersona(int idben){
		String sql="";
		try {
			sql="SELECT p.* FROM persona p JOIN beneficiario b ON b.idper=p.idper and b.idben=?";
			return this.db.queryForObject(sql,new objPersona(),idben);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public List<TransferenciaBeneficiario> ListaTB(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="select tb.* from trasladoBeneficiario tb JOIN solicitud s ON s.idsolt=tb.idsolt JOIN beneficiario b ON b.idben=tb.idbenNuevo JOIN persona p ON p.idper=b.idper WHERE (concat(p.ap,' ',p.am,' ',p.nombres) LIKE ? or p.ci LIKE ? or s.numSolt LIKE ?) and (b.estado=? or ?=-1) ORDER BY b.idben ASC";
		return this.db.query(sql, new objTBeneficiario(),"%"+filtro+"%","%"+filtro+"%","%"+filtro+"%",estado,estado);
	}
	public Object[]  registrarTB(HttpServletRequest req,String [] iddocb,String tel[],Persona p){
		int idper= generarIdPer();
		int idbenNuevo= generarIdBen();
		int idtrasl= generarIdTraslBen();
		Object [] resp=new Object[2];
		String login=p.getUsuario().getLogin();
		
		String sql="";
		String ci=req.getParameter("ciN");
		String ciCod=req.getParameter("ciCodN");
		String nombres=req.getParameter("nombresN");
		String ap=req.getParameter("apN");
		String am=req.getParameter("amN");
		String genero=req.getParameter("generoN");
		String direccion=req.getParameter("direccionN");
		String email=req.getParameter("emailN");
		String institucion=req.getParameter("institucionN");
		String descripcion=req.getParameter("descripcionN");
		
		String placaActual=req.getParameter("placaActual");
		int idsoltActual=Integer.parseInt(req.getParameter("idsoltActual"));
		int idbenActual=Integer.parseInt(req.getParameter("idbenActual"));
		try {
			sql="INSERT INTO persona(idper,ci,ciCod,nombres,ap,am,genero,direccion,email,estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idper,ci,ciCod,nombres.toUpperCase(),ap.toUpperCase(),am.toUpperCase(),genero,direccion.toUpperCase(),email,1);
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],idper);
				}
			}	
			sql="INSERT INTO beneficiario(idben,institucion,descripcion,estado,idper) VALUES(?,?,?,?,?)";
			this.db.update(sql,idbenNuevo,institucion.toUpperCase(),descripcion.toUpperCase(),1,idper);
			sql="INSERT INTO bendoc(idben,iddocb) VALUES(?,?)";
			for (int i = 0; i < iddocb.length; i++) {
				this.db.update(sql,idbenNuevo,Integer.parseInt(iddocb[i]));	
			}
			
			//AQUI CAMBIA EL ESTADO DEL ANTERIOR BENEFICIARIO Y DE AHI SE TRASLADA DE BENEFICIARIO AFECTANDO LA TABLA benVehSolt
			sql="UPDATE beneficiario SET estado=? WHERE idben=?";
			this.db.update(sql,0,idbenActual);
			
			//TRASLADANDO BENEFICIARIO
			sql="INSERT INTO benVehSolt(idben,placa,idsolt) VALUES(?,?,?)";
			this.db.update(sql,idbenNuevo,placaActual,idsoltActual);
			
			//REGISTRANDO EL TRASLADO
			sql="INSERT INTO trasladoBeneficiario(idtrasl,idsolt,idbenActual,motivoTraslado,idbenNuevo,login) VALUES(?,?,?,?,?,?)";
			this.db.update(sql,idtrasl,idsoltActual,idbenActual,descripcion.toUpperCase(),idbenNuevo,login);
			
			resp[0]=true;
			resp[1]=idtrasl;
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			resp[0]=false;
			return resp;
		}
	}
	public TransferenciaBeneficiario verTBeneficiario(int id) {
		String sql="select * from trasladoBeneficiario where idtrasl=?";
		return this.db.queryForObject(sql,new objTBeneficiario(),id);
	}
	public List<Telefono> ListaTelf_TB(int id){
		String sql="SELECT t.* FROM persona p,telefono t  where p.idper=t.idper and p.idper=?";
		return this.db.query(sql,new objTelefono(),id);
	}
}
