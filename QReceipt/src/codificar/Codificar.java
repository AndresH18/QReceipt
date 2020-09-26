package codificar;

import java.util.Random;
import numeros.Num5;

public class Codificar extends ManejoInformacion_new {

	private char keyChar;
	// private String keyString;
	private String[] info;
	private String coded;

	// TODO: USAR LA LIBRERIA PARA CODIFICAR LA INFO en un QR

	public Codificar(String[] info) {
		super();

		this.info = info;
		this.keyChar = createKeyChar();
		// this.keyString = numInfo();
		translate();

	}

	public String getCoded() {
		return coded;
	}

	@Override
	protected String infoKey() {
		String temp = keyChar + String.valueOf(info.length) + keyChar;
		return temp;
	}

	@Override
	protected void translate() {
		// ENCODE
		String coded = infoKey();
		for (String temp : info) {
			coded += temp + keyChar;
		}
		this.coded = coded;
	}

	// TODO: make the javadoc
	/**
	 * @return
	 */
	private char createKeyChar() {
		Random r = new Random();
		int i;
		/*
		 * (94)+33 -> [0+33]-[(93+1)+33] (10) -> 0-9 (11) -> 0-10 (11)+5 ->[0+5]-[10+5]
		 * = 5-15 (6)+5 ->[0+5]-[5+5] = 5-10
		 */
		do {
			i = r.nextInt(94) + 33;
			if (Character.toString(i).matches(Num5.FORMATO_VALIDO) || i == 96 || i == 39 || i == 34 || charInInfo(i)) {
				// System.out.println("caracter presente, repetir aleatorio".toUpperCase());
				continue;
			} else {
				// System.out.println("broke, found character");
				break;
			}
		} while (true);
		
		return ((char) i);
	}

	// TODO: make the javadoc
	/**
	 * FIXME: make my description look good, please? revisa si el caracter esta en
	 * alguno de los elementos del array
	 * 
	 * @param i {@code int}
	 * @return //TODO: porque retorna true o false
	 */
	private boolean charInInfo(int i) {
		String[] temp = info;
		for (String a : temp) {
			if (a.indexOf(i) != (-1)) {
				return true;
			}
		}
		return false;
	}
	

}