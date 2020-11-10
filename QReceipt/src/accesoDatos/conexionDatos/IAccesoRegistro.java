package accesoDatos.conexionDatos;

import java.util.List;

import accesoDatos.exceptions.AccesoDatosException;
import accesoDatos.exceptions.EscrituraDatosException;
import accesoDatos.exceptions.LecturaDatosException;

public interface IAccesoRegistro {

	boolean existe(String nombreArchivo) throws AccesoDatosException;

	void crear(String nombreArchivo) throws AccesoDatosException;

	void borrar(String nombreArchivo, boolean confirmar) throws AccesoDatosException;

	@Deprecated
	boolean buscar2(String nombreArchivo, String datos) throws LecturaDatosException;

	int buscar(String nombreArchivo, String datos) throws AccesoDatosException;

	void agregar(String nombreArchivo, String datos) throws AccesoDatosException, EscrituraDatosException;

	@Deprecated
	void cambiar(String nombreArchivo, String dato1, String dato2)
			throws AccesoDatosException, LecturaDatosException, EscrituraDatosException;

	@Deprecated
	void cambiar(String nombreArchivo, String dato, boolean estado) throws AccesoDatosException;

	List<String> listar(String nombreArchivo) throws AccesoDatosException, LecturaDatosException;

}
