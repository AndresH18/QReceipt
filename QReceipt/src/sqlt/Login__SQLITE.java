package sqlt;

import java.sql.*;

public class Login__SQLITE {

	Connection c = null;
	Statement stmt = null;
	boolean idcheck;

	public void crear() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE LOGIN " + "(USER           TEXT    NOT NULL, "
					+ " PASS          TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			System.out.println("tabla creada");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Table created successfully");

	}

	public void insertar(String USER, String PASS) {
		String user = "\'" + USER + "\'";
		String pass = "\'" + PASS + "\'";

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
			c.setAutoCommit(false);

			String sql = "INSERT INTO LOGIN (USER,PASS) " + "VALUES (" + user + "," + pass + ");";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Insertar exitoso");
	}

	public String[] seleccionar() {

		String resultados = "";

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:LoginDatabase.db");
			System.out.println("Opened database successfully");

			c.setAutoCommit(false);

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN;");
			while (rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("pass");
				resultados = resultados.concat(user + "," + pass + ",");
			}
			String[] lista = resultados.split(",");

			rs.close();
			stmt.close();
			c.close();

			return lista;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}

	}

	public static void main(String args[]) {
		Login__SQLITE log = new Login__SQLITE();
		log.crear();
		log.insertar("XD", "No"); // Creando e insertando algo en la base de datos
		String tokens[] = log.seleccionar();
		for (String t : tokens) {
			System.out.println(t); // Imprimiendo la base de datos

		}
	}
}
