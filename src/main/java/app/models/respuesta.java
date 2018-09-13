package app.models;

public class respuesta {
	protected String msg;
	protected boolean status;
	public respuesta() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "respuesta [msg=" + msg + ", status=" + status + "]";
	}
	
	
	
}
