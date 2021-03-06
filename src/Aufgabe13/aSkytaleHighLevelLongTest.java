package Aufgabe13;

import StringWriter.w;
import Aufgabe11aCaesar.Caesar;
import Aufgabe11bSkytale.SkytaleHighLevel;
import CharReader.r;

//2x Keys -> H�ufigkeitsverteilung -> Sicherheit? -> Protokollieren
public class aSkytaleHighLevelLongTest {

	public static void main(String args[]){
//		ntimetest(2);
		test(78);
	}
	
	public static void test(int key){
		String phrase = r.file("src/Aufgabe12/kafka-strafkolonie01.txt").toString();
		SkytaleHighLevel c = SkytaleHighLevel.create(phrase, key);
		String crypt = c.crypt();
		w.file("src/Aufgabe13/SkytaleHighCrypted["+key+"]").write(crypt);
		SkytaleHighLevel c2 = SkytaleHighLevel.create(crypt, key);
		String decrypt = c2.decrypt();
		w.file("src/Aufgabe13/SkytaleHighDecrypted["+key+"]").write(decrypt);
	}
	
	public static long timetest(int key){
		String phrase = r.file("src/Aufgabe12/kafka-strafkolonie01.txt").toString();
		SkytaleHighLevel c = SkytaleHighLevel.create(phrase, key);
		long t = System.currentTimeMillis();
		c.crypt();
		return System.currentTimeMillis()-t;
	}
	
	public static void ntimetest(int n){
		long timeSum = 0;
		if (n> 100)
			System.out.println("To much n of ntimes");
		for (int i = 0;i<n+1;i++){
			timeSum += timetest(i);
		}
		System.out.println("average " + timeSum/n);
	}
	
}
