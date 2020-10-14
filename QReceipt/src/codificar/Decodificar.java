package codificar;

public class Decodificar implements ManejoInformacion_new {
	// TODO: USAR LA LIBRERIA PARA LEER EL QR

	private String codedInfo;
	private String keyString;
	private char keyChar;
	private int keyInt;
	private String[] deCoded;

	public Decodificar(String codedInfo) {
		super();
		this.codedInfo = codedInfo;
		this.keyString = infoKey();
		this.keyChar = readKeyChar();
		this.keyInt = readKey_Int();
		translate();

	}
	
	public String[] getDeCoded() {
		return deCoded;
	}

	@Override
	public String infoKey() {
		String l = codedInfo.substring(0, 3);
		// return numInfo.substring(0, 3);
		return l;
	}

	@Override
	public void translate() {

		int count = 3;
		String[] cos = new String[keyInt];
		for (int i = 0; i < cos.length; i++) {
			cos[i] = "";
		}
		for (int i = 0; i < cos.length; i++) {
			countString: 
			while (true) {
				if (codedInfo.charAt(count) != keyChar) {
					cos[i] += codedInfo.charAt(count);
					count++;
				} else {
					count++;
					break countString;
				}
			}
		}
		this.deCoded = cos;
	}

	private char readKeyChar() {
		// return this.codedInfo.charAt(0);
		char key = this.codedInfo.charAt(0);
		return key;
	}

	private int readKey_Int() {
		// return keyString.charAt(1);
		int key = Integer.parseInt(String.valueOf(keyString.charAt(1)));
		return key;
	}

}