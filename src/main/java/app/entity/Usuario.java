package app.entity;

public class Usuario {
	private String login;
	private String password;
	private Integer estado;
	private Integer idper;

	public Usuario() {
//		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String login, String password, Integer estado, Integer idper) {
		super();
		this.login = login;
		this.password = password;
		this.estado = estado;
		this.idper = idper;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getIdper() {
		return idper;
	}
	public void setIdper(Integer idper) {
		this.idper = idper;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", password=" + password + ", estado=" + estado + ", idper=" + idper + "]";
	}
	
}
