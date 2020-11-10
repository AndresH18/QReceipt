package consola;
import sqlt2.SQLITE;

public class Consola extends SQLITE {

	public static void main(String[] args) {
//		IManejoDatos m = new ManejoDatos();
//		m.cambiarEstado("28", false);

		Consola s = new Consola();

		s.mostartDataBaseCompleta();

	}
//	public static void main(String[] args) {
//		// agregar y ---verificar(USER, PASSWORD)
//		Scanner sc = new Scanner(System.in);
//		SQLITE s = new SQLITE();
//		
//		s.mostartDataBaseCompleta();
//
////		System.out.println("insertar usuario y contrase" + (char) 241 + "a");
////
////		s.insertar(sc.nextLine(), sc.nextLine());
////		s.insertar(" sssdsdd", "jkjdksjdk");
////		s.insertar("kjksjdkljfhldh", "jdkjdfjhd hjkdhf");
////		s.insertar("andress", "pass");
////		s.insertar("USER", "PASS");
////		System.out.println();
////		s.mostartDataBaseCompleta();
////		System.out.println();
////		s.mostarBaseParcial();
////		System.out.println("=============");
////		for (String a : s.getAdmin()) {
////			System.out.println(a);
////		}
//
//		sc.close();
// }

}
