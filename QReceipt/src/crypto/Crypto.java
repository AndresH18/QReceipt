package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <b>Encripta y Decripta Texto</b><br>
 * Tambien convierte de String a Hexadecimal y viceversa
 *
 */
public class Crypto {

//    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        SecretKey secretKey = makeKey();
//        // System.out.println("secretKey[" + "Algorithm: " + secretKey.getAlgorithm() +
//        // " Encoded: " + secretKey.getEncoded().toString() + " Formta: " +
//        // secretKey.getFormat());
//        // String texto = "hola";
//        // do {
//        //     texto = sc.nextLine();
//        //     System.out.println("texto: " + texto);
//
//        //     String codificado = encriptar(texto, secretKey);
//        //     System.out.println("codificado: " + codificado);
//
//        //     String decodificado = decriptar(codificado, secretKey);
//        //     System.out.println("decodificado: " + decodificado);
//        // } while (true);
//        
//            //Texto
//        String texto = sc.nextLine();
//        System.out.println("Texto:\t\t" + texto);
//            //texto a formtato hexadecimal
//        String s1 = stringToHex(texto);
//        System.out.println("s1:\t\t" + s1);
//            //texto ehcadecimal a encriptado
//        String s2 = encriptar(s1, secretKey);
//        System.out.println("s2:\t\t" + s2);
//            //texto hexadecimal encriptado a hexadecimal (si, otra vez)
//        String s3 = stringToHex(s2);
//        System.out.println("s3:\t\t" + s3);
//
//        System.out.println("======================");
//            //hex a String
//        String ss1 = hexToString(s3);
//        System.out.println("ss1:\t\t" + ss1);
//
//        System.out.println((ss1.equals(s2))? true:false);
//            //desencriptar String
//        String ss2 = decriptar(ss1, secretKey);
//        System.out.println("ss2:\t\t" + ss2);
//
//        System.out.println((ss2.equals(s1))? true:false);
//            //hex a String 
//        String finall = hexToString(ss2);
//        System.out.println("final:\t\t" + finall);
//
//        System.out.println((finall.equals(texto))? true:false);
//        
//        
//        sc.close();
//    }
	/**
	 * <b>Genera una llave Simetrica para encriptar/desencriptar</b>
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public SecretKey makeKey() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// TODO: decidir una llave, tiene que tener 128 bits => esto es 16 Bytes
		final String keyString = "thisisa128bitkey";
		SecretKey secretKey;
		byte[] key;

		MessageDigest sha = null;

		key = keyString.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		secretKey = new SecretKeySpec(key, "AES");

		return secretKey;
	}

	/**
	 * <b>Encriptar</b>
	 * 
	 * @param texto
	 * @param secretKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public String encriptar(String texto, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(texto.getBytes("UTF-8")));

		return encrypted;
	}

	/**
	 * <b>Desencripta</b>
	 * 
	 * @param encrypted
	 * @param secretKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decriptar(String encrypted, SecretKey secretKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
		return new String(decrypted);
	}

	/**
	 * Convierte un <b>String</b> a formato <b>Hexadecimal</b>
	 * 
	 * @param texto
	 * @return
	 */
	public static String stringToHex(String texto) {
		StringBuilder sb = new StringBuilder();
		char[] cr = texto.toCharArray();
		for (char c : cr) {
			sb.append((String) Integer.toHexString(c));
		}
		return sb.toString();
	}

	/**
	 * Convierte una <b>String en formato Hexadecimal</b> a una <b>String normal</b>
	 * 
	 * @param hex
	 * @return
	 */
	public static String hexToString(String hex) {
		String result = new String();
		char[] charArray = hex.toCharArray();
		for (int i = 0; i < charArray.length; i += 2) {
			String st = "" + charArray[i] + "" + charArray[i + 1];
			char ch = (char) Integer.parseInt(st, 16);
			result += ch;
		}
		return result;
	}
}
