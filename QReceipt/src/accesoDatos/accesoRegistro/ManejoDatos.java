package accesoDatos.accesoRegistro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import accesoDatos.conexionDatos.*;
import accesoDatos.exceptions.*;

public class ManejoDatos implements IManejoDatos {

	private final IAccesoRegistro registro;

	public ManejoDatos() {
		this.registro = new AccesoRegistro(NOMBRE_ARCHIVO);
	}

	@Override
	public void inicializarRegitro() {

		try {
			if (this.registro.existe(NOMBRE_ARCHIVO)) {
				this.registro.borrar(NOMBRE_ARCHIVO, true);
				this.registro.crear(NOMBRE_ARCHIVO);

			} else {
				this.registro.crear(NOMBRE_ARCHIVO);
			}
		} catch (AccesoDatosException e) {
			e.printStackTrace(System.out);
			System.err.println("ERROR AL INICIAR REGISTRO");
		}

	}

	@Override
	public void agregarEntrada(String dato, boolean estado) {

		try {
			this.registro.agregar(NOMBRE_ARCHIVO, dato);
			this.registro.agregar(NOMBRE_ARCHIVO, String.valueOf(estado));
			System.err.println("AGREGAR EXITOSO");
		} catch (AccesoDatosException e) {
			e.printStackTrace();
			System.err.println("ERROR AL AGREGAR ENTRADA");
		}
	}

	@Override
	public boolean cambiarEstado(String dato, boolean estado) {
		try {
			List<String> lista = this.registro.listar(NOMBRE_ARCHIVO);

			// final int a = this.registro.buscar(nombreArchivo, dato) + 1;
			final int a = this.registro.buscar(NOMBRE_ARCHIVO, dato);

			this.registro.borrar(NOMBRE_ARCHIVO, true);
			this.registro.crear(NOMBRE_ARCHIVO);
			if (a != -1) {
				for (int i = 0; i < lista.size(); i++) {
					// if (i == a) {
					// if (i == (a + 1) && (a != -1)) {
					if (i == (a + 1)) {
						this.registro.agregar(NOMBRE_ARCHIVO, String.valueOf(estado));
						continue;
					} else {
						this.registro.agregar(NOMBRE_ARCHIVO, lista.get(i));
						continue;
					}
				}
				return true;
			}
		} catch (AccesoDatosException e) {
			e.printStackTrace();
			System.err.println("ERROR AL MODIFICAR EL REGISTRO");
			return false;
		}
		return false;
	}

	@Override
	public boolean existe(String dato) {
		try {
			if (this.registro.buscar(NOMBRE_ARCHIVO, dato) != -1) {
				return true;
			}
		} catch (AccesoDatosException e) {
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean getEstado(String dato) {
		if (existe(dato)) {
			try {
				ArrayList<String> lista = (ArrayList<String>) this.registro.listar(NOMBRE_ARCHIVO);

				final int a = this.registro.buscar(NOMBRE_ARCHIVO, dato);

				if (a != -1) {
					String name = lista.get(a+1);
					boolean b  = Boolean.parseBoolean(name);
					// boolean b = Boolean.getBoolean(lista.get(a + 1));
					return b;
					// return Boolean.getBoolean(lista.get(a + 1));
				}

			} catch (AccesoDatosException e) {
				e.printStackTrace();
				System.err.println("ERROR: " + e.getLocalizedMessage());
			}
		}
		return false;
	}

	/**
	 * CAUTION WARNING TODO Para copiar archivos
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	@Override
	public void crearArchivo() {
		try {
			this.registro.crear(NOMBRE_ARCHIVO);
		} catch (AccesoDatosException e) {
			e.printStackTrace();
		}
	}

}
