package sqlt2;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class SQLITE {

	private Connection c = null;
	private Statement stmt = null;
//	private boolean idCheck;

	public SQLITE() {
//		if((!new File("LoginDatabase.db").exists())){
//			crear();
//			insertar("Admin", "Admin");
//		}
		crear();
	}

	private void crear() {
//		if (!(new File("LoginDatabase.db").exists())) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");

			stmt = c.createStatement();
			String sql = "CREATE TABLE LOGIN " + "(USER           TEXT    NOT NULL, "
					+ " PASS          TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			c.close();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace(System.err);
			System.err.println("Crear: " + e.getClass().getName() + ": " + e.getMessage());
		} catch (SQLException e) {
//			e.printStackTrace(System.err);
			System.err.println("Crear: " + e.getClass().getName() + ": " + e.getMessage() + "\n");
		}
//		}
	}

	public String[][] getDatos() {

//		if (!(new File("LoginDatabase.db").exists())) {
//		crear();
//		}

		StringBuilder sb = new StringBuilder();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN;");
			while (rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("pass");

				sb.append(user).append(",").append(pass).append(",");
			}
			rs.clearWarnings();
			stmt.close();
			c.close();

			String[] s1 = sb.toString().split(",");
//			if (s1.length % 2 != 0) {
//				System.out.println("LA BASE DE DATOS FUE COMPROMETIDA");
//			}

			String[][] s2 = new String[s1.length / 2][2];
			int cont = 0;
			for (int i = 0; i < s2.length; i += 1) {
				s2[i][0] = s1[cont];
				s2[i][1] = s1[cont + 1];
				cont += 2;
			}
			return s2;

		} catch (ClassNotFoundException e) {
//			e.printStackTrace(System.err);
			System.err.println("GetDatos: " + e.getClass().getName() + ": " + e.getMessage());
		} catch (SQLException e) {
//			e.printStackTrace(System.err);
			System.err.println("GetDatos: " + e.getClass().getName() + ": " + e.getMessage());
		}
		return null;
	}
	/**
	 * <strong>TODAVIA NO IMPLEMENTADO<strong><br>
	 * BUSCAR USUARIOS OMITIENDO EL DEL ADMIN, EL CUAL SERIA EL PRIMERO
	 * @return
	 */
	private String[][] getDatos2() {

//		if (!(new File("LoginDatabase.db").exists())) {
//		crear();
//		}

		StringBuilder sb = new StringBuilder();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN;");
			rs.getString("user");
			rs.getString("pass");

			while (rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("pass");

				sb.append(user).append(",").append(pass).append(",");
			}
			rs.clearWarnings();
			stmt.close();
			c.close();

			String[] s1 = sb.toString().split(",");
//			if (s1.length % 2 != 0) {
//				System.out.println("LA BASE DE DATOS FUE COMPROMETIDA");
//			}

			String[][] s2 = new String[s1.length / 2][2];
			int cont = 0;
			for (int i = 0; i < s2.length; i += 1) {
				s2[i][0] = s1[cont];
				s2[i][1] = s1[cont + 1];
				cont += 2;
			}
			return s2;

		} catch (ClassNotFoundException e) {
//			e.printStackTrace(System.err);
			System.err.println("GetDatos: " + e.getClass().getName() + ": " + e.getMessage());
		} catch (SQLException e) {
//			e.printStackTrace(System.err);
			System.err.println("GetDatos: " + e.getClass().getName() + ": " + e.getMessage());
		}
		return null;
	}

	public void insertar(String USER, String PASS) {

//		if (!(new File("LoginDatabase.db").exists())) {
//
//		crear();
//		}
		if (!buscarUser(USER)) {
			String user = "\'" + USER + "\'";
			String pass = "\'" + PASS + "\'";
			try {

				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
				c.setAutoCommit(false);

				String sql = "INSERT INTO LOGIN (USER,PASS) " + "VALUES (" + user + "," + pass + ");";
				stmt = c.createStatement();

				stmt.executeUpdate(sql);

				stmt.close();
				c.commit();
				c.close();
				System.out.println("INSERTAR EXITOSO");
			} catch (ClassNotFoundException e) {
//				e.printStackTrace(System.err);
				System.err.println("Insertar: " + e.getClass().getName() + ": " + e.getMessage());
			} catch (SQLException e) {
//			e.printStackTrace(System.err);
				System.err.println("Insertar: " + e.getClass().getName() + ": " + e.getMessage());
			}
		}

	}
	
	/**
	 * @param USER
	 * @param PASS
	 * @return true, si se pudo insertar el usuario y contraseña(por lo tanto no existia el usuario antes)
	 */
	public boolean insertar2(String USER, String PASS) {
		
//		if (!(new File("LoginDatabase.db").exists())) {
//
//		crear();
//		}
		if (!buscarUser(USER)) {
			String user = "\'" + USER + "\'";
			String pass = "\'" + PASS + "\'";
			try {

				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
				c.setAutoCommit(false);

				String sql = "INSERT INTO LOGIN (USER,PASS) " + "VALUES (" + user + "," + pass + ");";
				stmt = c.createStatement();

				stmt.executeUpdate(sql);

				stmt.close();
				c.commit();
				c.close();
				System.out.println("INSERTAR EXITOSO");
				
				return true;
				
			} catch (ClassNotFoundException e) {
//				e.printStackTrace(System.err);
				System.err.println("Insertar: " + e.getClass().getName() + ": " + e.getMessage());
			} catch (SQLException e) {
//			e.printStackTrace(System.err);
				System.err.println("Insertar: " + e.getClass().getName() + ": " + e.getMessage());
			}
			System.out.println("ERROR AL INSERTAR");
		}
		
		return false;

	}

	/**
	 * 
	 * @param user
	 * @return true si el usuario ya existe en la base de datos
	 * 
	 */
	private boolean buscarUser(String user) {
		String[][] s = getDatos();
		for (int i = 0; i < s.length; i++) {
			if (s[i][0].equals(user)) {
				System.out.println("USUARIO YA EXISTE");
				return true;
			}
		}
		return false;
	}

	private void mostartDataBase() {

		String[][] s = getDatos();
		if (s != null) {
			for (int i = 0; i < s.length; i++) {
				System.out.println(s[i][0] + "::::" + s[i][1]);

			}
		}
	}

	public static void main(String[] args) {
		// agregar y ---verificar(USER, PASSWORD)
		Scanner sc = new Scanner(System.in);
		SQLITE s = new SQLITE();

//		System.out.println("insertar usuario y contrase" + (char) 241 + "a");
//
//		s.insertar(sc.nextLine(), sc.nextLine());
//		s.insertar(" sssdsdd", "jkjdksjdk");
//		s.insertar("kjksjdkljfhldh", "jdkjdfjhd hjkdhf");
//		s.insertar("andress", "pass");
		System.out.println();
		System.out.println("BASE DE DATOS");
		s.mostartDataBase();

		sc.close();

	}
}
