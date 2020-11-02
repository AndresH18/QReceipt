package accesoDatos.accesoRegistro;

public interface IManejoDatos {

	String NOMBRE_ARCHIVO = System.getProperty("user.dir") + "\\.docs\\.log.txt";

	void inicializarRegitro();

	void agregarEntrada(String dato, boolean estado);

	boolean cambiarEstado(String dato, boolean estado);

	boolean existe(String dato);

	boolean getEstado(String dato);

	void crearArchivo();

}
