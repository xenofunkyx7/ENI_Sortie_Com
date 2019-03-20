package dao;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class TestJava {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		byte[] mdpHash = DaoHelper.hash("contenu");
		
		String test = mdpHash.toString();
		System.out.println("essai 1 : "+test);
		
		String mdp2 = "b";
		byte[] mdpHash2 = DaoHelper.hash(mdp2);
		
		
		int test2 = mdpHash2.hashCode();
		
		System.out.println("string mdp 2 : "+ mdp2);
		System.out.println("essai 2 int : "+mdpHash2);
		System.out.println("essai 2 : "+test2);

	}

}
