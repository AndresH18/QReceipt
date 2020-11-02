package accesoDatos.conexionDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import accesoDatos.exceptions.AccesoDatosException;
import accesoDatos.exceptions.EscrituraDatosException;
import accesoDatos.exceptions.LecturaDatosException;

public class AccesoRegistro implements IAccesoRegistro {

	public AccesoRegistro(String nombreArchivo) {
		try {
			crear(nombreArchivo);
		} catch (AccesoDatosException e) {

		}
	}

	@Override
	public boolean existe(String nombreArchivo) throws AccesoDatosException {
		File file = new File(nombreArchivo);
		return file.exists();
	}

	@Override
	public void crear(String nombreArchivo) throws AccesoDatosException {
		if (!existe(nombreArchivo)) {
			final String directorio = this.getDirectory(nombreArchivo);
			File directory = new File(directorio);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			final File file = new File(nombreArchivo);
			try {
				PrintWriter salida = new PrintWriter(new FileWriter(file));
				salida.close();
				Path path = file.toPath();
				Files.setAttribute(path, "dos:hidden", true);

			} catch (IOException e) {
				throw new AccesoDatosException("Error al Crear Datos: " + e.getMessage());
			}
		}
	}

	@Override
	public void borrar(String nombreArchivo, boolean confirmar) throws AccesoDatosException {
		if (existe(nombreArchivo)) {
			File file = new File(nombreArchivo);
			file.delete();
		}
	}

	@Deprecated
	@Override
	public boolean buscar2(String nombreArchivo, String datos) throws LecturaDatosException {
		File file = new File(nombreArchivo);
		boolean retu = false;

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(file));
			String linea = null;
			linea = entrada.readLine();
			while (linea != null) {
				if (linea.equalsIgnoreCase(nombreArchivo)) {
					retu = true;
					break;
				}
			}
			entrada.close();
		} catch (IOException e) {
			throw new LecturaDatosException("ERROR AL LEER LOS DATOS: " + e.getMessage());
		}
		return retu;
	}

	@Override
	public int buscar(String nombreArchivo, String dato) throws AccesoDatosException {
		List<String> lista = listar(nombreArchivo);

		for (int i = 0; i < lista.size(); i++) {
			if (dato.equalsIgnoreCase(lista.get(i))) {
				return i;
			}

		}
		return -1;
	}

	@Override
	public void agregar(String nombreArchivo, String dato) throws AccesoDatosException, EscrituraDatosException {
		crear(nombreArchivo);

		File file = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(file, true));
			salida.println(dato);
			salida.close();
		} catch (IOException e) {
			throw new EscrituraDatosException("ERROR AL ESCRIBIR: " + e.getMessage());
		}

	}

	@Deprecated
	@Override
	public void cambiar(String nombreArchivo, String dato1, String dato2)
			throws AccesoDatosException, LecturaDatosException, EscrituraDatosException {

		List<String> lista = listar(nombreArchivo);
		File file = new File(nombreArchivo);

		borrar(nombreArchivo, true);

		if (lista != null) {
			try {
				PrintWriter salida = new PrintWriter(new FileWriter(file, true));
				for (String string : lista) {
					if (!string.equals(dato1)) {
						salida.println(string);
					} else {
						salida.println(dato2);
					}
				}
				salida.close();
			} catch (IOException e) {
				throw new AccesoDatosException("ERROR ACCESO DATOS: " + e.getMessage());
			}
		}
		throw new AccesoDatosException("EL ARCHIVO ESTA VACIO");
	}

	@Deprecated
	@Override
	public void cambiar(String nombreArchivo, String dato, boolean estado) throws AccesoDatosException {

		// List<String> lista = listar(nombreArchivo);
		File file = new File(nombreArchivo);

		borrar(nombreArchivo, true);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(file, true));
			salida.close();
		} catch (IOException e) {
			throw new AccesoDatosException("ERROR ACCESO DATOS: " + e.getMessage());
		}

	}

	@Override
	public List<String> listar(String nombreArchivo) throws AccesoDatosException, LecturaDatosException {

		this.crear(nombreArchivo);

		List<String> temp = new ArrayList<String>();

		File file = new File(nombreArchivo);

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(file));
			String linea = null;
			linea = entrada.readLine();

			while (linea != null) {
				temp.add(linea);
				linea = entrada.readLine();
			}
			entrada.close();

		} catch (IOException e) {
			throw new LecturaDatosException("ERROR LEYENDO DATOS: " + e.getMessage());
		}

		return temp;
	}

	private String getDirectory(String path) {
		String[] temp = path.split("\\\\");
		StringBuilder sb = new StringBuilder();
		final int index = temp.length - 1;

		for (int i = 0; i < index; i++) {
			sb.append(temp[i]).append("\\");
		}
		final int endIndex = sb.toString().length() - 1;
		String temp2 = sb.toString().substring(0, endIndex);

		return temp2;
	}

}
