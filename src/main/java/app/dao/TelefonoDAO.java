package app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Telefono;
import app.entity.TelefonoRowMapper;

@Transactional
@Repository
public class TelefonoDAO  implements ITelefonoDAO{
	@Autowired
	private  JdbcTemplate db;
	


	@Override
	public List<Telefono> getAllTelefonosById(int idper) {
		String sql="select * from telefono where idper=?";
		RowMapper<Telefono> rowMapper=new TelefonoRowMapper();
		return this.db.query(sql,rowMapper,idper);
	}
}
