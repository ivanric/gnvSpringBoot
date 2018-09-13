package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import app.manager.ManejadorBeneficiarios.objTelefono;
import app.manager.ManejadorSolicitudes.objSolicitud;
import app.models.Aprobacion;
import app.models.Beneficiario;
import app.models.Combustible;
import app.models.CombustibleVehiculo;
import app.models.Documento;
import app.models.MarcaVehiculo;
import app.models.ModeloVehiculo;
import app.models.Persona;
import app.models.Solicitud;
import app.models.Telefono;
import app.models.TipoAprobador;
import app.models.TipoMotorVehiculo;
import app.models.TipoServicioVehiculo;
import app.models.TipoVehiculo;
import app.models.Vehiculo;
import app.models.respuesta;


@Service
public class ManejadorSolicitudes{
private JdbcTemplate db;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	
	
	//@aui empieza los objetos
	public class objSolicitud implements RowMapper<Solicitud>{
		@Override
		public Solicitud mapRow(ResultSet rs, int arg1) throws SQLException {
			Solicitud s= new Solicitud();
			s.setIdsolt(rs.getInt("idsolt"));
			s.setNumSolt(rs.getString("numSolt"));
			s.setFecha(rs.getString("fechaCreacion"));
			s.setObservaciones(rs.getString("observaciones"));
			s.setAprobadoSiNo(rs.getInt("aprobadoSiNo"));
			s.setLogin(rs.getString("login"));
			s.setEstado(rs.getInt("estado"));
			try {
				s.setVehiculo(metVehiculo(rs.getInt("idsolt")));
			} catch (Exception e) {
				s.setVehiculo(null);
			}
			try {
				s.setPersona(metObtPersona(rs.getInt("idsolt")));
			} catch (Exception e) {
				s.setPersona(null);
			}
			return s;
	    }
	}
	public class objVehiculo implements RowMapper<Vehiculo>{
		@Override
		public Vehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			Vehiculo v= new Vehiculo();
			v.setPlaca(rs.getString("placa"));
			v.setTjeta_prioridad(rs.getString("tjeta_prioridad"));
			v.setCilindrada(rs.getString("cilindrada"));
			v.setColor(rs.getString("color"));
			v.setNum_motor(rs.getString("num_motor"));
			v.setNum_chasis(rs.getString("num_chasis"));
			try {
				v.setCombustibleVehiculo(metConmbustibles(rs.getString("placa")));
			} catch (Exception e) {
				// TODO: handle exception
				v.setCombustibleVehiculo(null);
			}
			try {
				v.setTipoVehiculo(metTipoVehiculo(rs.getInt("idtipv")));
			} catch (Exception e) {
				v.setTipoVehiculo(null);
			}
			try {
				v.setMarcaVehiculo(metMarcaVehiculo(rs.getInt("idmarcv")));
			} catch (Exception e) {
				v.setMarcaVehiculo(null);
			}
			try {
				v.setTipoServicio(metTipoServicio(rs.getInt("idTipSv")));
			} catch (Exception e) {
				v.setTipoServicio(null);
			}
			try {
				v.setTipoMotor(metTipoMotor(rs.getInt("idtipoMotorVeh")));
			} catch (Exception e) {
				v.setTipoMotor(null);
			}
			try {
				v.setModeloVehiculo(metModeloVehiculo(rs.getInt("idmodv")));
			} catch (Exception e) {
				v.setModeloVehiculo(null);
			}

			return v;
	    }
	}
	public class objetoPersona implements RowMapper<Persona>{
		@Override
		public Persona mapRow(ResultSet rs, int arg1) throws SQLException {
			Persona p= new Persona();
			p.setIdper(rs.getInt("idper"));
			p.setCi(rs.getString("ci"));
			p.setNombres(rs.getString("nombres"));
			p.setAp(rs.getString("ap"));
			p.setAm(rs.getString("am"));
			p.setGenero(rs.getString("genero"));
			p.setDireccion(rs.getString("direccion"));
			p.setEmail(rs.getString("email"));
			p.setFoto(rs.getString("foto"));
			p.setEstado(rs.getInt("estado"));
			try {
				p.setBeneficiario(metBeneficiario(rs.getInt("idper")));
			}catch (Exception e){
				p.setBeneficiario(null);
			}
			try {
				p.setListaTelf(metListaTelefonos(rs.getInt("idper")));
			} catch (Exception e) {
				// TODO: handle exception
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
	
	public class objCombustibleVehiculo implements RowMapper<CombustibleVehiculo>{
		@Override
		public CombustibleVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			CombustibleVehiculo c= new CombustibleVehiculo();
			c.setIdcomb(rs.getInt("idcomb"));
			c.setNombre(rs.getString("nombre"));
			c.setDetalle(rs.getString("detalle"));
			c.setEstado(rs.getInt("estado"));
			return c;
	    }
	}
	
	public class objTipoVehiculo implements RowMapper<TipoVehiculo>{
		@Override
		public TipoVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			TipoVehiculo tp= new TipoVehiculo();
			tp.setIdtipv(rs.getInt("idtipv"));
			tp.setNombre(rs.getString("nombre"));
			tp.setEstado(rs.getInt("estado"));
			return tp;
	    }
	}
	
	public class objMarcaVehiculo implements RowMapper<MarcaVehiculo>{
		@Override
		public MarcaVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			MarcaVehiculo mv= new MarcaVehiculo();
			mv.setIdmarcv(rs.getInt("idmarcv"));
			mv.setNombre(rs.getString("nombre"));
			mv.setEstado(rs.getInt("estado"));
			return mv;
	    }
	}
	
	public class objTipoServicio implements RowMapper<TipoServicioVehiculo>{
		@Override
		public TipoServicioVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			TipoServicioVehiculo ts= new TipoServicioVehiculo();
			ts.setIdTipSv(rs.getInt("idTipSv"));
			ts.setNombre(rs.getString("nombre"));
			ts.setEstado(rs.getInt("estado"));
			return ts;
	    }
	}
	public class objTipoMotor implements RowMapper<TipoMotorVehiculo>{
		@Override
		public TipoMotorVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			TipoMotorVehiculo tm= new TipoMotorVehiculo();
			tm.setIdtipoMotorVeh(rs.getInt("idtipoMotorVeh"));
			tm.setNombre(rs.getString("nombre"));
			tm.setEstado(rs.getInt("estado"));
			return tm;
	    }
	}
	public class objModeloVehiculo implements RowMapper<ModeloVehiculo>{
		@Override
		public ModeloVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			ModeloVehiculo mv= new ModeloVehiculo();
			mv.setIdmodv(rs.getInt("idmodv"));
			mv.setNombre(rs.getString("nombre"));
			mv.setEstado(rs.getInt("estado"));
			return mv;
	    }
	}
	
	public class objAprobacion implements RowMapper<Aprobacion>{
		@Override
		public Aprobacion mapRow(ResultSet rs, int arg1) throws SQLException {
			Aprobacion a= new Aprobacion();
			a.setIdsolt(rs.getInt("idsolt"));
			a.setIdTipoAp(rs.getInt("idTipoAp"));
			a.setFechaAprob(rs.getDate("fechaAprob"));
			a.setLogin(rs.getString("login"));
			try {
				a.setTipoAprobador(metTipoAprobador(rs.getInt("idTipoAp")));
			} catch (Exception e) {
				// TODO: handle exception
				a.setTipoAprobador(null);
			}
			return a;
	    }
	}
	public class objTipoAprbador implements RowMapper<TipoAprobador>{
		@Override
		public TipoAprobador mapRow(ResultSet rs, int arg1) throws SQLException {
			TipoAprobador tpA= new TipoAprobador();
			tpA.setIdTipoAp(rs.getInt("idTipoAp"));
			tpA.setNombre(rs.getString("nombre"));
			tpA.setCodigo(rs.getString("codigo"));
			tpA.setJerarquia(rs.getInt("jerarquia"));
			tpA.setEstado(rs.getInt("estado"));
			return tpA;
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
	public Beneficiario metBeneficiario(int idper){
		return this.db.queryForObject("select * from beneficiario where idper=?", new objBeneficiario(),idper);
	}
	public List<Telefono> metListaTelefonos(int idper){
		String sql="select * from telefono where idper=?";
		return this.db.query(sql,new objTelefono(),idper);
	}
	public List<Documento> getDocumentos(int idben){
		return this.db.query("SELECT d.* FROM docBeneficiario d,beneficiario b,bendoc bd WHERE d.iddocb=bd.iddocb and b.idben=bd.idben and b.idben=?", new objDocumento(),idben);
	}
	
	//$empesemos
//	public Vehiculo metVehiculo(String placa){
//		return this.db.queryForObject("select * from vehiculo where placa=?", new objVehiculo(),placa);
//	}
	public Vehiculo metVehiculo(int idsolt){
		return this.db.queryForObject("select v.* from vehiculo v,benVehSolt bvs,solicitud s,beneficiario b where v.placa=bvs.placa AND s.idsolt=bvs.idsolt AND b.idben=bvs.idben AND b.estado=1 AND s.idsolt=?", new objVehiculo(),idsolt);
	}
	public List<CombustibleVehiculo> metConmbustibles(String placa){
		return this.db.query("SELECT c.* FROM combustible c,vehiculo v,combveh cv WHERE c.idcomb=cv.idcomb and v.placa=cv.placa and v.placa=?", new objCombustibleVehiculo(),placa);
	}
	public TipoVehiculo metTipoVehiculo(int idtipv){
		return this.db.queryForObject("select tp.* from tipoVehiculo tp where idtipv=? and tp.estado=1", new objTipoVehiculo(),idtipv);
	}
	public MarcaVehiculo metMarcaVehiculo(int idmarcv){
		return this.db.queryForObject("select * from marcaVehiculo  where idmarcv=? and estado=1", new objMarcaVehiculo(),idmarcv);
	}
	public TipoServicioVehiculo metTipoServicio(int idTipSv){
		return this.db.queryForObject("select * from tipoServicioVehiculo where idTipSv=? and estado=1", new objTipoServicio(),idTipSv);
	}
	public TipoMotorVehiculo metTipoMotor(int idtipoMotorVeh){
		return this.db.queryForObject("select * from tipoMotorVehiculo where idtipoMotorVeh=? and estado=1", new objTipoMotor(),idtipoMotorVeh);
	}
	public ModeloVehiculo metModeloVehiculo(int idmodv){
		return this.db.queryForObject("select * from modeloVehiculo where idmodv=? and estado=1", new objModeloVehiculo(),idmodv);
	}
	
	//cambiar aqui por placa de parametro
//	public Persona metObtPersona(int idben){
//		String sql="";
//		try {
//			sql="SELECT p.* FROM persona p JOIN beneficiario b ON b.idper=p.idper and b.idben=?";
//			return this.db.queryForObject(sql,new objetoPersona(),idben);
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			return null;
//		}
//	}
	public Persona metObtPersona(int idsolt){
		String sql="";
		try {
			sql="SELECT p.* FROM persona p JOIN beneficiario b ON b.idper=p.idper JOIN benVehSolt bvs ON bvs.idben=b.idben AND b.estado=1 JOIN solicitud s ON s.idsolt=bvs.idsolt WHERE  bvs.idsolt=?";
			return this.db.queryForObject(sql,new objetoPersona(),idsolt);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	//Solicitudes
	public List<Solicitud> Listar(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		//String sql="select s.* from solicitud s join vehiculo v on s.placa=v.placa where  (v.placa LIKE ?) and (s.estado=? or ?=-1) ORDER BY s.idsolt ASC";
		String sql="select s.* from solicitud s JOIN benVehSolt bvs ON bvs.idsolt=s.idsolt join vehiculo v on bvs.placa=v.placa JOIN beneficiario b ON b.idben=bvs.idben AND b.estado=1 where  (v.placa LIKE ?) and (s.estado=? or ?=-1) ORDER BY s.idsolt ASC";
		return this.db.query(sql, new objSolicitud(),"%"+filtro+"%",estado,estado);
	}
	
	public List<Documento> listaDocumentos(){
		String sql="SELECT * FROM docBeneficiario WHERE estado=1 ORDER BY iddocb ASC";
		return this.db.query(sql,new objDocumento());
	}
	
	//Metodos al adicionar
	public List<TipoVehiculo> tipoVehiculo(){
		String sql="select tp.* from tipoVehiculo tp where tp.estado=1";
		return this.db.query(sql,new objTipoVehiculo());
	}
	public List<MarcaVehiculo> marcaVehiculo(){
		String sql="select * from marcaVehiculo  where estado=1";
		return this.db.query(sql,new objMarcaVehiculo());
	}
	public List<ModeloVehiculo> modeloVehiculo(){
		String sql="select * from modeloVehiculo where estado=1";
		return this.db.query(sql, new objModeloVehiculo());
	}
	public List<TipoMotorVehiculo> tipoMotorVehiculo(){
		String sql="select * from tipoMotorVehiculo where estado=1";
		return this.db.query(sql, new objTipoMotor());
	}
	public List<TipoServicioVehiculo> tipoServicioVehiculo(){
		String sql="select * from tipoServicioVehiculo where estado=1";
		return this.db.query(sql,new objTipoServicio());
	}
	public List<CombustibleVehiculo> tipoCombustible(){
		String sql="SELECT * FROM combustible  WHERE estado=1";
		return this.db.query(sql,new objCombustibleVehiculo());
	}
	public int numeroSolicitud(){
		String sql="select COALESCE(max(idsolt),0)+1 as idsolt from solicitud";
		return db.queryForObject(sql, Integer.class);
	}
	public boolean verificarPlaca(String placa){
		System.out.println("entro sql_placa:"+placa);
		String sql="";
		try {
			sql="SELECT COUNT(placa) FROM vehiculo WHERE placa=? and estado=0";
			int data=this.db.queryForObject(sql,Integer.class,placa);
			System.out.println("ver????:"+data);
			if(data!=0){
				return true;	
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("entro catch");	
			return false;
		}
		
	}
	
	public int EstadoPlaca(String placa){
		System.out.println("entro sql_placa:"+placa);
		String sql="";
		try {
			//sql="SELECT COUNT(v.placa) FROM solicitud s, vehiculo v WHERE v.placa=s.placa and v.placa=? and v.estado=1";
			sql="SELECT COUNT(v.placa)FROM beneficiario b,vehiculo v,benVehSolt bvs WHERE b.idben=bvs.idben AND b.estado=1 AND v.placa=bvs.placa AND  v.placa=? and v.estado=1";
			int data=this.db.queryForObject(sql,Integer.class,placa);
			System.out.println("existe????:"+data);
			if(data!=0){
				return 1;	
			}else{
				return 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("entro catch");	
			return 0;
		}
		
	}
	
	public Vehiculo DatosVehiculo(String placa){
		System.out.println("placa:"+placa);
		String sql="";
		try {
			sql="SELECT * FROM vehiculo WHERE placa=? and estado=0";
			return  this.db.queryForObject(sql,new objVehiculo(),placa);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("entro catch error");	
			return null;
		}
		
	}
	public void BorrarCombustibles(String placa){
		String sql=" delete from combveh where placa= ?";
		this.db.update(sql,new Object[]{placa});
	}
	public Object[] registrar(HttpServletRequest req,Persona xuser,Vehiculo v,Solicitud s,int [] combustible){
		int idsolt= generarIdSol();
		int idben=Integer.parseInt(req.getParameter("idben"));
		Object []Respuesta=new Object[2];
		String sql="";
		try {
			if(!verificarPlaca(v.getPlaca())){
				System.out.println("placa nueva");
				sql="INSERT INTO vehiculo(placa,tjeta_prioridad,cilindrada,color,num_motor,num_chasis,idtipv,idmarcv,idTipSv,idtipoMotorVeh,idmodv) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				this.db.update(sql,v.getPlaca().toUpperCase(),v.getTjeta_prioridad().toUpperCase(),v.getCilindrada().toUpperCase(),v.getColor().toUpperCase(),v.getNum_motor().toUpperCase(),v.getNum_chasis().toUpperCase(),v.getIdtipv(),v.getIdmarcv(),v.getIdTipSv(),v.getIdtipoMotorVeh(),v.getIdmodv());	
			}else{
				System.out.println("placa existe");
				sql="UPDATE vehiculo SET tjeta_prioridad=?,cilindrada=?,color=?,num_motor=?,num_chasis=?,idtipv=?,idmarcv=?,idTipSv=?,idtipoMotorVeh=?,idmodv=?,estado=? WHERE placa=?";
				this.db.update(sql,v.getTjeta_prioridad().toUpperCase(),v.getCilindrada().toUpperCase(),v.getColor().toUpperCase(),v.getNum_motor().toUpperCase(),v.getNum_chasis().toUpperCase(),v.getIdtipv(),v.getIdmarcv(),v.getIdTipSv(),v.getIdtipoMotorVeh(),v.getIdmodv(),1,v.getPlaca());
				BorrarCombustibles(v.getPlaca());
			}
			sql="INSERT INTO combveh(placa,idcomb) VALUES(?,?)";
			for (int i = 0; i <combustible.length; i++){
				this.db.update(sql,v.getPlaca().toUpperCase(),combustible[i]);	
			}	
			sql="INSERT INTO solicitud(idsolt,numSolt,fechaCreacion,observaciones,login) VALUES(?,?,?,?,?)";
			this.db.update(sql,idsolt,s.getNumSolt(),s.getFecha(),s.getObservaciones().toUpperCase(),xuser.getUsuario().getLogin());	
			
			//sql="insert INTO benveh(placa,idben) VALUES(?,?)";
			sql="insert INTO benVehSolt(placa,idben,idsolt) VALUES(?,?,?)";
			this.db.update(sql,v.getPlaca().toUpperCase(),idben,idsolt);
			
			Respuesta[0]=true;
			Respuesta[1]=idsolt;
			return Respuesta;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro error");
			System.out.println("err:"+e.getMessage());
			e.printStackTrace();
			Respuesta[0]=false;
			return Respuesta;
		}
	}
	
	public boolean anular(Integer idsolt){
		String sql="";
		try {
			sql="UPDATE solicitud SET estado=0 WHERE idsolt=?";
			int a=this.db.update(sql,idsolt);
			System.out.println("sql_anulo: "+a);
			sql="UPDATE v SET v.estado=0 FROM vehiculo v JOIN benVehSolt bvs ON  bvs.placa=v.placa JOIN solicitud s ON s.idsolt=bvs.idsolt WHERE s.idsolt=?";
			this.db.update(sql,idsolt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/***************************************************APROBACIONES****************************************************/
	public TipoAprobador metTipoAprobador(int idTipoAp){
		String sql="SELECT tpA.* FROM tipoAprobador tpA,aprobacion a WHERE tpA.idTipoAp=a.idTipoAp AND a.idTipoAp=?";
		return this.db.queryForObject(sql,new objTipoAprbador(),idTipoAp);
	}
	
	public List<Solicitud> ListarSolAp(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		
		String sql="(select s.*,p.*\r\n" + 
				"from solicitud s join benVehSolt bvs on bvs.idsolt=s.idsolt JOIN beneficiario b ON b.idben=bvs.idben AND b.estado=1 JOIN persona p ON p.idper=b.idper JOIN vehiculo v ON v.placa=bvs.placa \r\n" + 
				"where  ((s.numSolt LIKE ?) or (p.ci LIKE ?)) and s.estado=1)\r\n" + 
				"UNION\r\n" + 
				"(select s.*,p.*\r\n" + 
				"from solicitud s join benVehSolt bvs on bvs.idsolt=s.idsolt JOIN beneficiario b ON b.idben=bvs.idben AND b.estado=1 JOIN persona p ON p.idper=b.idper JOIN vehiculo v ON v.placa=bvs.placa\r\n" + 
				"where  ((s.numSolt LIKE ?) or (p.ci LIKE ?)) and s.estado=0)ORDER BY s.idsolt ASC";
		return this.db.query(sql, new objSolicitud(),"%"+filtro+"%","%"+filtro+"%","%"+filtro+"%","%"+filtro+"%");
	}
	
	public List<Aprobacion> cargarAprobaciones(Solicitud s){
		String sql;
		try {
			sql="SELECT a.* FROM aprobacion a,solicitud s WHERE s.idsolt=a.idsolt AND s.idsolt=?";
			return this.db.query(sql, new objAprobacion(),s.getIdsolt());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	public Solicitud ListarSolById(HttpServletRequest req){
		String idsolt=req.getParameter("idsolt");
		String sql="select s.* from solicitud s where s.idsolt=? and s.estado=1 ORDER BY s.idsolt ASC";
		return this.db.queryForObject(sql, new objSolicitud(),idsolt);
	}
	public List<TipoAprobador> ListaTipoAprob(){
		String sql="";
		try {
			sql="select * from tipoAprobador where estado=1 ORDER BY jerarquia ASC";
			return this.db.query(sql, new objTipoAprbador());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
		
		
	}
	public Map<String, Object> UsuarioCreadorSolt(String login){
		String sql="select p.* from persona p, usuario u where u.idper=p.idper and u.login=?";
		return this.db.queryForMap(sql,new Object[] {login});
	}
	public List<Map<String, Object>> ListaTelefonos(int idper){
		String sql="";
		try {
			sql="select * from telefono where idper=?";
			return this.db.queryForList(sql,new Object[] {idper});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	public List<Map<String, Object>> ListaPausaAprob(HttpServletRequest req){
		String idsolt=req.getParameter("idsolt");
		String sql="select * from pausaAprobacion WHERE idsolt=?";
		return this.db.queryForList(sql,new Object[] {Integer.parseInt(idsolt)});
	}
	
	
	public List<Telefono> ListaTelf(int idsolt){
		String sql="SELECT t.* FROM telefono t,persona p,beneficiario b,solicitud s,benVehSolt bvs WHERE t.idper=p.idper and p.idper=b.idper AND b.idben=bvs.idben AND b.estado=1 AND s.idsolt=bvs.idsolt AND s.idsolt=?";
		return this.db.query(sql,new objTelefono(),idsolt);
	}
	public Solicitud verSolicitud(int idsolt) {
		String sql="select * from solicitud where idsolt=?";
		return this.db.queryForObject(sql,new objSolicitud(),idsolt);
	}
	
	/*ORDEN DE SERVICIO*/
	public List<Solicitud> FiltroSolicitudOS(String cadena){
		//String sql="SELECT s.* FROM solicitud s,beneficiario b,persona p WHERE s.idben=b.idben and s.estado=1  and aprobadoSiNo=1 and b.idper=p.idper and (s.numSolt LIKE ? or p.ci LIKE ?) and s.idsolt NOT IN (SELECT od.idsolt FROM ordenServicio od WHERE od.idsolt=s.idsolt)";
		String sql="SELECT s.* FROM solicitud s,beneficiario b,persona p,benVehSolt bvs WHERE bvs.idben=b.idben AND b.estado=1 AND bvs.idsolt=s.idsolt and s.estado=1 and s.aprobadoSiNo=1 and b.idper=p.idper and (s.numSolt LIKE ? or p.ci LIKE ?) and s.idsolt NOT IN (SELECT od.idsolt FROM ordenServicio od WHERE od.idsolt=s.idsolt)";
		return this.db.query(sql, new objSolicitud(),'%'+cadena+'%','%'+cadena+'%');
	}
	
	public int generarIdSol(){
		String sql="select COALESCE(max(idsolt),0)+1 as idsolt from solicitud";
		return db.queryForObject(sql, Integer.class);
	}
	public List<CombustibleVehiculo> listaCombustible(){
		String sql="SELECT * FROM combustible WHERE estado=1 ORDER BY idcomb ASC";
		return this.db.query(sql,new objCombustibleVehiculo());
	}

	
	/*ORDEN SERVICIO*/
	public Solicitud metSolicitud(int idsolt){
		return this.db.queryForObject("select * from solicitud where idsolt=?", new objSolicitud(),idsolt);
	}
	
	public Solicitud getSoltByOrdServ(int id) {
		return this.db.queryForObject("select s.* from solicitud s,ordenServicio os where os.idsolt=s.idsolt and os.idordserv=?", new objSolicitud(),id);
	}

}
