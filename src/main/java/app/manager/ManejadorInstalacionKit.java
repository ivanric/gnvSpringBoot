package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.manager.ManejadorActaRecepcion.objActaRecepcion;
import app.manager.ManejadorServicios.objCilindro;
import app.manager.ManejadorServicios.objMarcaCilindro;
import app.manager.ManejadorServicios.objMarcaReductor;
import app.manager.ManejadorServicios.objOrdenServicio;
import app.manager.ManejadorServicios.objReductor;
import app.models.ActaRecepcion;
import app.models.Cilindro;
import app.models.InstalacionCilindro;
import app.models.MarcaCilindro;
import app.models.MarcaReductor;
import app.models.OrdenServicio;
import app.models.Persona;
import app.models.Reductor;
import app.models.RegistroKit;
import app.models.TransferenciaBeneficiario;
@Service
public class ManejadorInstalacionKit {
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public class objRegistroKit implements RowMapper<RegistroKit>{
		@Override
		public RegistroKit mapRow(ResultSet rs, int arg1) throws SQLException {
			RegistroKit r= new RegistroKit();
			r.setIdregistroKit(rs.getInt("idregistroKit"));
			r.setFechaConversion(rs.getString("fechaConversion"));
			r.setFechaInstalacion(rs.getString("fechaInstalacion"));
			r.setIdordserv(rs.getInt("idordserv"));
			r.setDesinstaladoKitGlpSiNo(rs.getInt("desinstaladoKitGlpSiNo"));
			r.setCertificadoDesintalacionSiNo(rs.getInt("certificadoDesintalacionSiNo"));
			r.setIdreduc(rs.getInt("idreduc"));
			r.setNumSerieRegulador(rs.getString("numSerieRegulador"));
			r.setCertificadoKit(rs.getString("certificadoKit"));
			r.setNumSerieMotor(rs.getString("numSerieMotor"));
			r.setLogin(rs.getString("login"));
			try {
				r.setReductor(metReductor(rs.getInt("idreduc")));
			} catch (Exception e) {
				r.setReductor(null);
			}
			return r;
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

	public class objInstalacionCilindro implements RowMapper<InstalacionCilindro>{
		@Override
		public InstalacionCilindro mapRow(ResultSet rs, int arg1) throws SQLException {
			InstalacionCilindro ic=new InstalacionCilindro();
			ic.setIdcil(rs.getInt("idcil"));
			ic.setSerie(rs.getString("serie"));
			ic.setIdregistroKit(rs.getInt("idregistroKit"));
			try {
				ic.setCilindro(metCilindro(rs.getInt("idcil")));
			} catch (Exception e) {
				ic.setCilindro(null);
			}
			return ic;
		}
	}
	public Reductor metReductor(int idreduc){
		return this.db.queryForObject("select * from reductor where idreduc=?", new objReductor(),idreduc);
	}
	public Cilindro metCilindro(int idcil){
		return this.db.queryForObject("select * from cilindro where idcil=?", new objCilindro(),idcil);
	}
	public MarcaCilindro metMarcaCilindro(int idmarccil){
		return this.db.queryForObject("select * from marcaCilindro where idmarccil=?", new objMarcaCilindro(),idmarccil);
	}
	public MarcaReductor metMarcaReductor(int idmarcred){
		return this.db.queryForObject("select * from marcaReductor where idmarcred=?", new objMarcaReductor(),idmarcred);
	}
	public List<RegistroKit> Lista(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		//int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="select r.* from registroKit r join ordenServicio os on r.idordserv=os.idordserv JOIN solicitud s ON s.idsolt=os.idsolt  WHERE os.numords LIKE ? or s.numSolt LIKE ? ORDER BY r.idregistroKit ASC";
		return this.db.query(sql, new objRegistroKit(),"%"+filtro+"%","%"+filtro+"%");
	}
	public Object[] registrar(HttpServletRequest req,Persona xuser) {
		String sql="";
		Object [] resp=new Object[2];
		int idRegistroKit=idRegistroKit();
		String login=xuser.getUsuario().getLogin();
		String idOrdServ=req.getParameter("idordserv");
		int idOS=Integer.parseInt(idOrdServ);
		String idReduc=req.getParameter("reductor");
		int idR=Integer.parseInt(idReduc);
		String FechaConversion=req.getParameter("fechaConversion");
		String FechaInstalacion=req.getParameter("fechaInstalacion");
		String DesintalacionKitGlp=req.getParameter("desintalacionKitGlp");
		
		System.out.println("DesintalacionKitGlp: "+DesintalacionKitGlp);
		int DestKit;
		if(DesintalacionKitGlp==null) {
			DestKit=-1;
		}else {
			
			DestKit=Integer.parseInt(DesintalacionKitGlp);
		}
	
		String CertificadoDesinstacion=req.getParameter("CertificadoDesinstacion");
		int CerDes;
		if (CertificadoDesinstacion==null) {
			CerDes=-1;
		} else {
			CerDes=Integer.parseInt(CertificadoDesinstacion);
		}
		String SerieRegulador=req.getParameter("serieRegulador");
		String CertificadoKit=req.getParameter("certificadoKit");
		String SerieMotor=req.getParameter("serieMotor");
		System.out.println("idOrdServ: "+idOrdServ+" idReduc: "+idReduc+" FechaConversion: "+FechaConversion+" FechaInstalacion: "+FechaInstalacion+" DesintalacionKitGlp: "+DesintalacionKitGlp+" CertificadoDesinstacion: "+CertificadoDesinstacion+" SerieRegulador: "+SerieRegulador+" CertificadoKit: "+CertificadoKit+" SerieMotor: "+SerieMotor);
		String[] series=req.getParameterValues("serie[]");
		String[] cilindros=req.getParameterValues("cilindro[]");
		for (String i : series) {
			System.out.println("serie : "+i);
		}
		for (String i : cilindros) {
			System.out.println("codcil: "+i);
		}
		
		try {
			sql="INSERT INTO registroKit(idregistroKit,idordserv,fechaConversion,fechaInstalacion,desinstaladoKitGlpSiNo,certificadoDesintalacionSiNo,idreduc,numSerieRegulador,certificadoKit,numSerieMotor,login) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idRegistroKit,idOS,FechaConversion,FechaInstalacion,DestKit,CerDes,idR,SerieRegulador,CertificadoKit,SerieMotor,login);
			String sql1="INSERT INTO instalacionCilindro(idcil,serie,idregistroKit) VALUES(?,?,?)";
			for (int i = 0; i < cilindros.length; i++) {
				this.db.update(sql1,cilindros[i],series[i],idRegistroKit);
			} 
			this.db.update("UPDATE ordenServicio SET instaladoSiNo=1 WHERE idordserv=?",idOS);
			resp[0]=true;
			//resp[1]=idrecep;
			return resp;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resp[0]=false;
			return resp;
		}
	}
	public List<InstalacionCilindro> ListaCilindro(int id) {
		String sql="select * from instalacionCilindro where idregistroKit=?";
		return this.db.query(sql,new objInstalacionCilindro(),id);
	}
	public RegistroKit verInstacionKit(int id) {
		String sql="select * from registroKit where idregistroKit=?";
		return this.db.queryForObject(sql,new objRegistroKit(),id);
	}

	public int idRegistroKit(){
		String sql="select COALESCE(max(idregistroKit),0)+1 as idregistroKit from registroKit";
		return db.queryForObject(sql, Integer.class);
	}
	/*ORDEN PAGO*/
	public RegistroKit getRegistroKitOP(int id) {
		String sql="select rk.* from registroKit rk,actaRecepcion ar where rk.idordserv=ar.idordserv AND ar.idordserv=?";
		return this.db.queryForObject(sql,new objRegistroKit(),id);
	}
	/*TRANSFERENCIA BENEFICIARIO*/
	public RegistroKit getRegistroKitTB(int id) {
		//String sql="SELECT rk.* FROM registroKit rk JOIN ordenServicio os ON os.idordserv=rk.idordserv JOIN solicitud s ON s.idsolt=os.idsolt JOIN trasladoBeneficiario tb ON tb.idsolt=s.idsolt WHERE tb.idsolt=?";
		String sql="SELECT rk.* FROM registroKit rk JOIN ordenServicio os ON os.idordserv=rk.idordserv JOIN solicitud s ON s.idsolt=os.idsolt  WHERE s.idsolt=?";
		return this.db.queryForObject(sql,new objRegistroKit(),id);
	}
	public List<RegistroKit> FiltroRegistroKitTB(String cadena){
		String sql="SELECT rk.* FROM registroKit rk,ordenServicio os,solicitud s,vehiculo veh,beneficiario b,persona p, benVehSolt bvs \r\n" + 
				"WHERE rk.idordserv=os.idordserv AND os.idsolt=s.idsolt AND os.instaladoSiNo=1 AND bvs.idben=b.idben AND b.estado=1 AND bvs.placa=veh.placa AND bvs.idsolt=s.idsolt and b.idper=p.idper and (os.numords LIKE ? or s.numSolt LIKE ? or p.ci LIKE ?) ";
		return this.db.query(sql, new objRegistroKit(),'%'+cadena+'%','%'+cadena+'%','%'+cadena+'%');
	}
	public RegistroKit getRegistroKitTBbyIdTrasl(int id) {
		String sql="select rk.* from registroKit rk,trasladoBeneficiario tb,solicitud s,ordenServicio os\r\n" + 
				"WHERE tb.idsolt=s.idsolt AND s.idsolt=os.idsolt  AND rk.idordserv=os.idordserv AND tb.idtrasl=?";
		return this.db.queryForObject(sql,new objRegistroKit(),id);
	}

}
