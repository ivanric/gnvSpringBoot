package app.utilidades;




public class pruebas {
	public static void main(String[] args) {
		String path="\\home\\hola\\jaja";
		System.out.println(path);
		path = path.replaceAll("\\\\", "/");
		System.out.println(path);
	}

}
