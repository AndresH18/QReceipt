package numeros;

public class Num6 {

	private String numeroInt;
	private String numeroString;
	

	public final static String FORMATO_VALIDO = "[0-9]+";

	private final static String[] UNITS = { "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ",
			"NUEVE " };

	private final static String[] DECENAS = { "", "DIEZ ", "VEINTE ", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ",
			"SETENTA ", "OCHENTA ", "NOVENTA " };

	private final static String[] DECENAS2 = { "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ",
			"DIECISIETE ", "DIECIOCHO ", "DIECINUEVE " };
	private final static String[] DECENAS3 = { "VEINTE ", "VEINTIUN ", "VEINTIDOS ", "VEINTITRES ", "VEINTICUATRO ", "VEINTICINCO ", "VEINTISEIS ",
			"VEINTISIETE ", "VEINTIOCHO ", "VEINTINUEVE " };

	private final static String[] CENTENAS = { "", "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ",
			"QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

	public Num6(String numeroInt) {
			if(numeroInt != null) {
				this.numeroInt = numeroInt;
				completarTripletas();
				this.numeroString = enPalabras();
			}else{
				System.out.println("hubo un error y no se pudo completar la accion");
				this.numeroString = "ERROR";
			}

	}
	public String getNumeroString(){
		return numeroString;
	}
	private void completarTripletas() {
		String temp = this.numeroInt;
		String completa;

		if (temp.length()%3 != 0) {
			switch (temp.length() % 3) {
			case 1:
				completa = "00" + temp;
				break;
			case 2:
				completa = "0" + temp;
				break;

			default:
				completa = "whaaaaaaat".toUpperCase();
				break;
			}
			this.numeroInt = completa;
		}
	}

	private String[] separarTripletas() {
		String temp = numeroInt;
		String[] sep = new String[temp.length() / 3];
		int cont = 0;
		for (int i = 3; i <= temp.length(); i += 3) {
			sep[cont] = temp.substring(i - 3, i);
			cont++;
		}
		return sep;
	}

	private String enPalabras() {
		String[] sep = separarTripletas();
		String palabra = "";
		boolean skipUnit = false;

		for (int i = 0; i < sep.length; i++) {
			skipUnit = false;
			// for (int j = 0; j < 3; j++) {
			for (int j = 0; j < sep[i].length(); j++) {
				// System.out.println("HHHHHHHH" + sep[i].length());
				switch (j + 1) {
				case 1:
					if(Character.getNumericValue(sep[i].charAt(0)) == 1 && Character.getNumericValue(sep[i].charAt(1))==0 && Character.getNumericValue(sep[i].charAt(2))==0) {
						palabra += "CIEN ";
					}else {
						palabra += CENTENAS[Character.getNumericValue(sep[i].charAt(j))];
					}
					break;
				case 2:
					if (Character.getNumericValue(sep[i].charAt(j)) == 1) {
						palabra += DECENAS2[Character.getNumericValue(sep[i].charAt(j + 1))];
						skipUnit = true;
					}else if(Character.getNumericValue(sep[i].charAt(j)) == 2) {
						palabra += DECENAS3[Character.getNumericValue(sep[i].charAt(j+1))];
						skipUnit = true;
					}else {
						palabra += DECENAS[Character.getNumericValue(sep[i].charAt(j))];
					}
					break;
				case 3:
//					if (!skipUnit) {
//						palabra += UNITS[Character.getNumericValue(sep[i].charAt(j))];
						
					if (!skipUnit) {
						if(i==sep.length-2
								&& Character.getNumericValue(sep[i].charAt(0)) == 0
								&& Character.getNumericValue(sep[i].charAt(1)) == 0
								&& Character.getNumericValue(sep[i].charAt(2)) == 1) {
									// System.out.println("HEEEEE");
								
//							//
////							System.out.println("ENTRO");
////							System.out.println(sep[i]);
////							System.out.println("i: " + i + " == sep.length-2: " + (sep.length-2));
////							System.out.println("sep[i].charAt(0) = " + sep[i].charAt(0));
////							System.out.println("sep[i].charAt(1) = " + sep[i].charAt(1));
//							//			
							
						}else {
						
						//QUE no haya un zero
						if(Character.getNumericValue(sep[i].charAt(2)) != 0 
								&& Character.getNumericValue(sep[i].charAt(1)) != 0) {
								palabra += "Y ";
							}
						palabra += UNITS[Character.getNumericValue(sep[i].charAt(j))];
						} 
					}
					break;

				default:
					break;
				}
				
			}


			switch (sep.length) {
			// TODO: mejorar sintaxis: actual[1000 --> un mil]; debe ser[1000-->mil] 
			//FIXME: Cambiar billones por mil millones
			//BILLON
			case 5:
				if (i == 0) {
					// 012
					// 2 !=1
					// 1 !=0
					// 0 !=0
					if (Character.getNumericValue(sep[i].charAt(2)) != 1
							|| Character.getNumericValue(sep[i].charAt(0)) != 0
							|| Character.getNumericValue(sep[i].charAt(1)) != 0) {
						palabra += "BILLONES ";
					} else {
						palabra += "BILLON";
					}
				} else if (i == 1) {
					// palabra += "MIL ";
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				} else if (i == 2) {
					// 012
					// 2 !=1
					// 1 !=0
					// 0 !=0
					if (Character.getNumericValue(sep[i].charAt(2)) != 1
							|| Character.getNumericValue(sep[i].charAt(0)) != 0
							|| Character.getNumericValue(sep[i].charAt(1)) != 0) {
						palabra += "MILLONES ";
					} else {
						palabra += "MILLON ";
					}
				} else if (i == 3) {
					// palabra += "MIL ";
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				}
				
				break;
			//MIL MILLONES
			case 4:
				if (i == 0) {
					// palabra += "MIL ";
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				} else if (i == 1) {
					// 012
					// 2 !=1
					// 1 !=0
					// 0 !=0
					if (Character.getNumericValue(sep[i].charAt(2)) != 1
							|| Character.getNumericValue(sep[i].charAt(0)) != 0
							|| Character.getNumericValue(sep[i].charAt(1)) != 0) {
						palabra += "MILLONES ";
					} else {
						palabra += "MILLON ";
					}
				} else if (i == 2) {
					// palabra += "MIL ";
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				} else {
				}
				
				break;
			//MILLONES
			case 3:
				if (i == 0) {
					// 012
					// 2 !=1
					// 1 !=0
					// 0 !=0
					if (Character.getNumericValue(sep[i].charAt(2)) != 1
							|| Character.getNumericValue(sep[i].charAt(0)) != 0
							|| Character.getNumericValue(sep[i].charAt(1)) != 0) {
						palabra += "MILLONES ";
					} else {
						palabra += "MILLON ";
					}
				} else if (i == 1) {
					//FIXME
					//FIXME
					//FIXME
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				} else {
				}
				
				break;
			//MIL
			case 2:
				if (i == 0) {
					// palabra += "MIL ";
					buscarSiEsZero:
					for(int lol = 0; lol<3; lol++) {
						if(sep[i].charAt(lol) != '0') {
							palabra += "MIL ";
						break buscarSiEsZero;
						}
					}
				} else {
				}
				
				break;
			//NADA (centenas)
			default:
				break;
			}
		}
		return palabra;
	}
	

}