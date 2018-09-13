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

import app.manager.ManejadorBeneficiarios.objPersona;
import app.manager.ManejadorSolicitudes.objBeneficiario;
import app.manager.ManejadorSolicitudes.objSolicitud;
import app.manager.ManejadorSolicitudes.objTelefono;
import app.manager.ManejadorSolicitudes.objVehiculo;
import app.manager.ManejadorUsuarios.objUsuario;
import app.models.Beneficiario;
import app.models.ChipRom;
import app.models.Cilindro;
import app.models.Combustible;
import app.models.CombustibleVehiculo;
import app.models.FactorCobro;
import app.models.InstalacionCilindro;
import app.models.MarcaCilindro;
import app.models.MarcaReductor;
import app.models.OrdenServicio;
import app.models.Persona;
import app.models.Reductor;
import app.models.Servicio;
import app.models.Solicitud;
import app.models.Taller;
import app.models.Telefono;
import app.models.TipoMotorVehiculo;
import app.models.Usuario;
import app.models.Vehiculo;

@Service
public class ManejadorServicios {
	private JdbcTemplate db;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}

	
	public class objTaller implements RowMapper<Taller>{
		@Override
		public Taller mapRow(ResultSet rs, int arg1) throws SQLException {
			Taller t= new Taller();
			t.setIdtall(rs.getInt("idtall"));
			t.setNombretall(rs.getString("nombretall"));
			t.setDireccion(rs.getString("direccion"));
			t.setFecha_registro(rs.getString("fecha_registro"));
			t.setEstado(rs.getInt("estado"));
			try {
				t.setPersona(metPersona(rs.getInt("idper")));
			}catch (Exception e){
				t.setPersona(null);
			}
			return t;
	    }
	}
	public class objChipRom implements RowMapper<ChipRom>{
		@Override
		public ChipRom mapRow(ResultSet rs, int arg1) throws SQLException {
			ChipRom c= new ChipRom();
			c.setIdRom(rs.getInt("idRom"));
			c.setNombreChip(rs.getString("nombreChip"));
			c.setFecha(rs.getString("fecha"));
			c.setBloqueado(rs.getInt("bloqueado"));
			return c;
	    }
	}

	public class objOrdenServicio implements RowMapper<OrdenServicio>{
		@Override
		public OrdenServicio mapRow(ResultSet rs, int arg1) throws SQLException {
			OrdenServicio os= new OrdenServicio();
			os.setIdordserv(rs.getInt("idordserv"));
			os.setNumords(rs.getInt("numords"));
			os.setIdsolt(rs.getInt("idsolt"));
			os.setFechaords(rs.getString("fechaords"));
			try {
				os.setTaller(metTaller(rs.getInt("idtall")));
			}catch (Exception e){
				os.setTaller(null);
			}
			try {
				os.setChipRom(metChip(rs.getInt("idRom")));
			}catch (Exception e){
				os.setChipRom(null);
			}
			try {
				os.setServicio(metServicio(rs.getInt("idserv")));
			}catch (Exception e){
				os.setServicio(null);
			}
			os.setInstaladoSiNo(rs.getInt("instaladoSiNo"));
			try {
				os.setPersona(metUsuario(rs.getString("login")));
			} catch (Exception e) {
				// TODO: handle exception
				os.setPersona(null);
			}
			
			return os;
	    }
	}


	public class objServicios implements RowMapper<Servicio>{
		@Override
		public Servicio mapRow(ResultSet rs, int arg1) throws SQLException {
			Servicio s= new Servicio();
			s.setIdserv(rs.getInt("idserv"));
			try {
				s.setCilindro(metCilindro(rs.getInt("idcil")));
			} catch (Exception e) {
				s.setCilindro(null);
			}
			try {
				s.setReductor(metReductor(rs.getInt("idreduc")));
			} catch (Exception e) {
				s.setReductor(null);
			}
			s.setFactorCobro(rs.getString("factorCobro"));

			try {
				s.setTipoMotorVehiculo(metTipoMotorVehiculo(rs.getInt("idtipoMotorVeh")));
			} catch (Exception e) {
				s.setTipoMotorVehiculo(null);
			}
			try {
				s.setCombustible(metCombustible(rs.getInt("idcomb")));
			} catch (Exception e) {
				// TODO: handle exception
				s.setCombustible(null);
			}
			try {
				s.setPersona(metUsuario(rs.getString("login")));
			} catch (Exception e) {	
				s.setPersona(null);
			}
			
			s.setNumPistones(rs.getInt("numPistones"));
			s.setPrecioDolares(rs.getString("precioDolares"));
			s.setPrecioBolivianos(rs.getString("precioBolivianos"));
			s.setPrecioTotal(rs.getString("precioTotal"));
			s.setFecha(rs.getString("fecha"));
			s.setEstado(rs.getInt("estado"));
			return s;
	    }
	}
	
	public class objMarcaReductor implements RowMapper<MarcaReductor>{
		@Override
		public MarcaReductor mapRow(ResultSet rs, int arg1) throws SQLException {
			MarcaReductor mr= new MarcaReductor();
			mr.setIdmarcred(rs.getInt("idmarcred"));
			mr.setNombre(rs.getString("nombre"));
			mr.setEstado(rs.getInt("estado"));
			return mr;
	    }
	}
	public class objMarcaCilindro implements RowMapper<MarcaCilindro>{
		@Override
		public MarcaCilindro mapRow(ResultSet rs, int arg1) throws SQLException {
			MarcaCilindro mc= new MarcaCilindro();
			mc.setIdmarccil(rs.getInt("idmarccil"));
			mc.setNombre(rs.getString("nombre"));
			mc.setEstado(rs.getInt("estado"));
			return mc;
	    }
	}
	public class objCilindro implements RowMapper<Cilindro>{
		@Override
		public Cilindro mapRow(ResultSet rs, int arg1) throws SQLException {
			Cilindro c= new Cilindro();
			c.setIdcil(rs.getInt("idcil"));
			c.setCapacidad(rs.getString("capacidad"));
			c.setSerie(rs.getString("serie"));
			c.setIdmarccil(rs.getInt("idmarccil"));
			c.setEstado(rs.getInt("estado"));
			try {
				c.setMarcaCilindro(metMarcaCilindro(rs.getInt("idmarccil")));
			} catch (Exception e) {
				c.setMarcaCilindro(null);
			}
			return c;
	    }
	}
	public class objReductor implements RowMapper<Reductor>{
		@Override
		public Reductor mapRow(ResultSet rs, int arg1) throws SQLException {
			Reductor r= new Reductor();
			r.setIdreduc(rs.getInt("idreduc"));
			r.setSerie(rs.getString("serie"));
			r.setTipoTecnologia(rs.getString("tipoTecnologia"));
			r.setIdmarcred(rs.getInt("idmarcred"));
			r.setEstado(rs.getInt("estado"));
			try {
				r.setMarcaReductor(metMarcaReductor(rs.getInt("idmarcred")));
			} catch (Exception e) {
				r.setMarcaReductor(null);
			}
			return r;
	    }
	}
	public class objTelefono implements RowMapper<Telefono>{
		@Override
		public Telefono mapRow(ResultSet rs, int arg1) throws SQLException {
			Telefono t= new Telefono();
//			t.setIdper(rs.getInt("idper"));
			t.setNumero(rs.getString("numero"));	
			return t;
	    }
	}

	public class objTipoMotorVehiculo implements RowMapper<TipoMotorVehiculo>{
		@Override
		public TipoMotorVehiculo mapRow(ResultSet rs, int arg1) throws SQLException {
			TipoMotorVehiculo t= new TipoMotorVehiculo();
			t.setIdtipoMotorVeh(rs.getInt("idtipoMotorVeh"));
			t.setNombre(rs.getString("nombre"));;
			t.setEstado(rs.getInt("estado"));
			return t;
	    }
	}
	public class objCombustible implements RowMapper<Combustible>{
		@Override
		public Combustible mapRow(ResultSet rs, int arg1) throws SQLException {
			Combustible c= new Combustible();
			c.setIdcomb(rs.getInt("idcomb"));
			c.setNombre(rs.getString("nombre"));;
			c.setEstado(rs.getInt("estado"));
			return c;
	    }
	}
	public class objUsuario implements RowMapper<Usuario>{
		@Override
		public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
			Usuario u= new Usuario();
			u.setLogin(rs.getString("login"));
			u.setPassword(rs.getString("password"));
			u.setEstado(rs.getInt("estado"));
			u.setIdper(rs.getInt("idper"));
			return u;
	    }
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
				p.setUsuario(obtenerUsuario(rs.getInt("idper")));
			} catch (Exception e) {
				p.setUsuario(null);
			}
			try {
				p.setListaTelf(metListaTelf(rs.getInt("idper")));
			} catch (Exception e) {
				// TODO: handle exception
				p.setListaTelf(null);
			}
			return p;
	    }
	}

	
	public Persona metPersona(int idper){
		String sql="SELECT p.* FROM persona p JOIN taller t ON t.idper=p.idper and p.idper=?";
		return this.db.queryForObject(sql,new objPersona(),idper);
	}

	public Taller metTaller(int idtall){
		return this.db.queryForObject("select * from taller where idtall=?", new objTaller(),idtall);
	}
	public Servicio metServicio(int idserv){
		return this.db.queryForObject("select * from servicio where idserv=?", new objServicios(),idserv);
	}
	public ChipRom metChip(int idRom){
		return this.db.queryForObject("select * from chipRom where idRom=?", new objChipRom(),idRom);
	}
	
	
	public Usuario obtenerUsuario(int codper){
		return this.db.queryForObject("select * from usuario where idper=?", new objUsuario(),codper);
	}
	public List<Telefono> metListaTelf(int idper){
		return this.db.query("select * from telefono where idper=?", new objTelefono(),idper);
	}
	public MarcaReductor metMarcaReductor(int idmarcred){
		return this.db.queryForObject("select * from marcaReductor where idmarcred=?", new objMarcaReductor(),idmarcred);
	}
	public MarcaCilindro metMarcaCilindro(int idmarccil){
		return this.db.queryForObject("select * from marcaCilindro where idmarccil=?", new objMarcaCilindro(),idmarccil);
	}
	public Cilindro metCilindro(int idcil){
		return this.db.queryForObject("select * from cilindro where idcil=?", new objCilindro(),idcil);
	}
	public Reductor metReductor(int idreduc){
		return this.db.queryForObject("select * from reductor where idreduc=?", new objReductor(),idreduc);
	}

	public TipoMotorVehiculo metTipoMotorVehiculo(int idTipoM){
		return this.db.queryForObject("select * from tipoMotorVehiculo where idtipoMotorVeh=?", new objTipoMotorVehiculo(),idTipoM);
	}
	public Combustible metCombustible(int idTipoM){
		return this.db.queryForObject("select * from combustible where idcomb=?", new objCombustible(),idTipoM);
	}
	public Persona metUsuario(String login){
		return this.db.queryForObject("select p.* from persona p,usuario u where u.idper=p.idper AND u.login=?", new objPersona(),login);
	}
	public List<Servicio> ListarServicios(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="SELECT s.* FROM servicio s JOIN cilindro c ON c.idcil=s.idcil JOIN reductor r ON  r.idreduc=s.idreduc JOIN tipoMotorVehiculo tm ON tm.idtipoMotorVeh=s.idtipoMotorVeh JOIN usuario u ON u.login=s.login where  (r.tipoTecnologia LIKE ? OR c.capacidad LIKE ?) and (s.estado=? or ?=-1) ORDER BY s.idserv ASC";
		return this.db.query(sql, new objServicios(),"%"+filtro+"%","%"+filtro+"%",estado,estado);
	}
	public List<OrdenServicio> ListarOrdenServicio(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="SELECT os.* FROM ordenServicio os JOIN servicio serv ON serv.idserv=os.idserv JOIN taller tll ON  os.idtall=tll.idtall JOIN solicitud s ON s.idsolt=os.idsolt JOIN usuario u ON u.login=os.login where (tll.nombretall LIKE ?)  and (os.instaladoSiNo=? or ?=-1) ORDER BY os.idordserv ASC";
		return this.db.query(sql, new objOrdenServicio(),"%"+filtro+"%",estado,estado);
	}
	public List<Map<String,Object>> ListReductores(){
		String sql="select r.* FROM reductor r";
		return this.db.queryForList(sql,new Object[] {});
	}
	public List<Map<String,Object>> ListMarcaReductores(){
		String sql="select mr.* FROM marcaReductor mr";
		return this.db.queryForList(sql,new Object[] {});
	}
	
	public List<Map<String,Object>> ListCilindros(){
		String sql="select c.*,mc.* FROM cilindro c JOIN marcaCilindro mc ON c.idmarccil=mc.idmarccil";
		return this.db.queryForList(sql,new Object[] {});
	}
	public List<Map<String,Object>> ListSistemaMotor(){
		String sql="SELECT tp.* FROM tipoMotorVehiculo tp";
		return this.db.queryForList(sql,new Object[] {});
	}
	public List<Map<String,Object>> ListCombustibles(){
		String sql="SELECT * FROM combustible where estado=1";
		return this.db.queryForList(sql,new Object[] {});
	}
	
	public boolean registrar(HttpServletRequest req,Persona xuser) {
//		System.out.println("param1: "+req.getParameter("tipoTecnologia"));
//		System.out.println("param2: "+req.getParameter("capacidad"));
		String sql="";
		int idserv=generarIdServ();
		int idcil=Integer.parseInt(req.getParameter("capacidad"));
		int idreduc=Integer.parseInt(req.getParameter("tipoTecnologia"));
		int idtipoMotorVeh=Integer.parseInt(req.getParameter("sistemaMotor"));
		int idcomb=Integer.parseInt(req.getParameter("combustible"));
		String factCobro=req.getParameter("factorCobro");
		String precioDolares=req.getParameter("precioDolares");
		String precioBolivianos=req.getParameter("precioBolivianos");
		String precioTotal=req.getParameter("precioTotal");
		int numPistones=Integer.parseInt(req.getParameter("numPistones"));
		String login=xuser.getUsuario().getLogin();
		System.out.println("idserv: "+idserv+" idcil: "+idcil+" idreduc: "+idreduc+" idtipoMotorVeh: "+idtipoMotorVeh+" idfactCobro: "+factCobro+" precioD: "+precioDolares+" precioB:"+precioBolivianos);
		try {
			sql="INSERT INTO servicio(idserv,precioDolares,precioBolivianos,idcil,idreduc,factorCobro,idtipoMotorVeh,login,numPistones,idcomb,precioTotal) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idserv,precioDolares,precioBolivianos,idcil,idreduc,factCobro,idtipoMotorVeh,login,numPistones,idcomb,precioTotal);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public Object[] registrarOS(HttpServletRequest req,Persona xuser) {
//		System.out.println("param1: "+req.getParameter("tipoTecnologia"));
//		System.out.println("param2: "+req.getParameter("capacidad"));
		String sql="";
		Object []Respuesta=new Object[2];
		int idOrdServ=generarIdOrdServ();
		int numOrdServ=Integer.parseInt(req.getParameter("numOrdServ"));
		int idSolt=Integer.parseInt(req.getParameter("idsolt"));
		int idServ=Integer.parseInt(req.getParameter("idserv"));
		int idtall=Integer.parseInt(req.getParameter("idtall"));
		int idRom=Integer.parseInt(req.getParameter("idRom"));
		String login=xuser.getUsuario().getLogin();
		
		try {
			sql="INSERT INTO ordenServicio(idordserv,numords,idsolt,idtall,idserv,login,idRom) VALUES(?,?,?,?,?,?,?)";
			this.db.update(sql,idOrdServ,numOrdServ,idSolt,idtall,idServ,login,idRom);
			Respuesta[0]=true;
			Respuesta[1]=idOrdServ;
			return Respuesta;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Respuesta[0]=false;
			return Respuesta;
		}
	}
	public Servicio datosModificar(HttpServletRequest req){
		String sql="";
		Servicio s=null;
		int idserv=Integer.parseInt(req.getParameter("idserv"));
		System.out.println("idserv: "+idserv);
		try {
			sql="SELECT s.* FROM servicio s WHERE s.idserv=?";
			s=this.db.queryForObject(sql,new objServicios(),idserv);
		} catch (Exception e) {
			s=null;
		}
		return s;
	}
	public boolean modificar(HttpServletRequest req,Persona xuser){
		String sql="";
		int idserv=Integer.parseInt(req.getParameter("idserv"));
		int idcil=Integer.parseInt(req.getParameter("capacidad"));
		int idreduc=Integer.parseInt(req.getParameter("tipoTecnologia"));
		int idtipoMotorVeh=Integer.parseInt(req.getParameter("sistemaMotor"));
		int idcomb=Integer.parseInt(req.getParameter("combustible"));
		String factCobro=req.getParameter("factorCobro");
		String precioDolares=req.getParameter("precioDolares");
		String precioBolivianos=req.getParameter("precioBolivianos");
		String precioTotal=req.getParameter("precioModTotal");
		int numPistones=Integer.parseInt(req.getParameter("numPistones"));
		String login=xuser.getUsuario().getLogin();
		System.out.println(" idcil: "+idcil+" idreduc: "+idreduc+" idtipoMotorVeh: "+idtipoMotorVeh+" factCobro: "+factCobro+" precioD: "+precioDolares+" precioB:"+precioBolivianos);
		System.out.println("idcomb: "+idcomb);
		System.out.println("PrecioModTotal: "+precioTotal);
		try {
			sql="UPDATE servicio SET precioDolares=?,precioBolivianos=?,idcil=?,idreduc=?,factorCobro=?,idtipoMotorVeh=?,login=?,numPistones=?,idcomb=?,precioTotal=? WHERE idserv=?";
			this.db.update(sql,precioDolares,precioBolivianos,idcil,idreduc,factCobro,idtipoMotorVeh,login,numPistones,idcomb,precioTotal,idserv);
			
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
			sql="UPDATE servicio SET estado=0 WHERE idserv=?";
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
			sql="UPDATE servicio SET estado=1 WHERE idserv=?";
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
	
	/*ORDEN SERVICIO*/
	public Map<String,Object>	nitEmpresa(int idinst){
		String sql="select * from institucion where idinst=?";
		return db.queryForMap(sql, new Object[]{idinst});
	}
	public List<Telefono> ListaTelfTall(int idOrdServ){
		String sql="SELECT telf.numero FROM ordenServicio ords JOIN taller tall ON ords.idtall=tall.idtall JOIN persona per ON tall.idper=per.idper JOIN telefono telf ON per.idper=telf.idper WHERE idordserv=?";
		return this.db.query(sql,new objTelefono(),idOrdServ);
	}
	public List<Telefono> ListaTelfBen(int idOrdServ){
		String sql="SELECT DISTINCT(tel.numero) FROM ordenServicio ords JOIN solicitud solt ON solt.idsolt=ords.idsolt JOIN benVehSolt bvs ON solt.idsolt=bvs.idsolt JOIN beneficiario ben ON ben.idben=bvs.idben  AND ben.estado=1 JOIN persona per ON per.idper=ben.idper JOIN telefono tel ON tel.idper=per.idper WHERE idordserv=?";
		return this.db.query(sql,new objTelefono(),idOrdServ);
	}
	
	/*ORDEN SERVICIO*/
	public List<Map<String,Object>> ListTalleres(){
		String sql="select * FROM taller where estado=1";
		return this.db.queryForList(sql,new Object[] {});
	}
	public List<Map<String,Object>> ListChipRom(){
		String sql="select * FROM chipRom where bloqueado=0";
		return this.db.queryForList(sql,new Object[] {});
	}
	public List<Servicio> ListServicios(){
		String sql="select * FROM servicio where estado=1";
		return this.db.query(sql,new objServicios());
	}
	public List<Combustible> ListComb(){
		String sql="select * FROM combustible where estado=1";
		return this.db.query(sql,new objCombustible());
	}
	
	public int generarIdServ(){
		String sql="select COALESCE(max(idserv),0)+1 as idserv from servicio";
		return db.queryForObject(sql, Integer.class);
	}
	public int generarIdOrdServ(){
		String sql="select COALESCE(max(idordserv),0)+1 as idserv from ordenServicio";
		return db.queryForObject(sql, Integer.class);
	}
	public int numeroOrdenServicio(){
		String sql="select COALESCE(max(numords),0)+1 as numords from ordenServicio";
		return db.queryForObject(sql, Integer.class);
	}
	public OrdenServicio verOrdenServicio(int id) {
		String sql="select * from ordenServicio where idordserv=?";
		return this.db.queryForObject(sql,new objOrdenServicio(),id);
	}
	public List<Telefono> ListaTelf(int id){
		String sql="SELECT t.* FROM telefono t,persona p WHERE t.idper=p.idper AND p.idper=?";
		return this.db.query(sql,new objTelefono(),id);
	}
	
	/*ACTA DE RECEPCION*/
	public OrdenServicio getOrdenServicioAR(int id) {
		String sql="select os.*  from ordenServicio os,actaRecepcion ac where ac.idordserv=os.idordserv AND os.idordserv=?";
		return this.db.queryForObject(sql,new objOrdenServicio(),id);
	}
	public List<OrdenServicio> FiltroOrdenServicioAR(String cadena){
		String sql="SELECT os.* FROM ordenServicio os,solicitud s,vehiculo veh,beneficiario b,persona p, benVehSolt bvs WHERE os.idsolt=s.idsolt AND os.instaladoSiNo=1 AND bvs.idben=b.idben AND b.estado=1 AND bvs.placa=veh.placa AND bvs.idsolt=s.idsolt and b.idper=p.idper and (os.numords LIKE ? or s.numSolt LIKE ? or p.ci LIKE ?) and os.idordserv NOT IN (SELECT ar.idordserv  FROM actaRecepcion ar WHERE ar.idordserv=os.idordserv)";
		return this.db.query(sql, new objOrdenServicio(),'%'+cadena+'%','%'+cadena+'%','%'+cadena+'%');
	}
	/*Instalacion KIT*/
	public OrdenServicio getOrdenServicioIK(int id) {
		String sql="select os.*  from ordenServicio os,registroKit ik where ik.idordserv=os.idordserv AND os.idordserv=?";
		return this.db.queryForObject(sql,new objOrdenServicio(),id);
	}
	public List<OrdenServicio> FiltroOrdenServicioIK(String cadena){
		String sql="SELECT os.* FROM ordenServicio os,solicitud s,vehiculo veh,beneficiario b,persona p,benVehSolt bvs WHERE os.idsolt=s.idsolt AND os.instaladoSiNo=0 AND bvs.idben=b.idben AND b.estado=1 AND bvs.placa=veh.placa AND bvs.idsolt=s.idsolt and b.idper=p.idper and (os.numords LIKE ? or s.numSolt LIKE ? or p.ci LIKE ?) and os.idordserv NOT IN (SELECT r.idordserv  FROM registroKit r WHERE r.idordserv=os.idordserv)";
		return this.db.query(sql, new objOrdenServicio(),'%'+cadena+'%','%'+cadena+'%','%'+cadena+'%');
	}
	
	public List<Cilindro> ListaCilindros(){
		String sql="SELECT * FROM cilindro WHERE estado=1";
		return this.db.query(sql, new objCilindro());
	}
	public List<Reductor> ListaReductores(){
		String sql="SELECT * FROM reductor WHERE estado=1";
		return this.db.query(sql, new objReductor());
	}


}
