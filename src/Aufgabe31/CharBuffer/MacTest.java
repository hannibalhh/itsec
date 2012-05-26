package Aufgabe31.CharBuffer;

import java.math.BigInteger;
import CharReader.r;
import CharReader.Interface.CharReader;

public class MacTest {
	
	static final BigInteger key = BigInteger.valueOf(0x123);
	static final String path = "src/Aufgabe31/kafka";
//	static final CharReader clearm = r.file("src/Aufgabe31/kafka");
//	static final CharReader clearm = r.file("src/Aufgabe31/short");
	static final CharReader clearm = r.file(path);
	
	public static void main(String args[]){
//		easyTest();
		bruteForceTest();
	}
	
	public static void easyTest(){
		Message m = Message.create(clearm, key);
		System.out.println(m.k());
		System.out.println(m.mac());
	}	
	
	public static void bruteForceTest(){
		Message m=Message.create(clearm, key);
		System.out.println(m);
		Bf bf= Bf.create(15*8,m.mac(),path);
		System.out.println("Found HEX-String key: 0x"+bf.foundKey);
		System.out.println("tested Keys: "+bf.searchedKeys);
		System.out.println("Duration in ms: "+bf.lastDurationMS);		
	}
}
