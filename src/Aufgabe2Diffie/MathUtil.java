package Aufgabe2Diffie;

import java.math.BigInteger;
import java.util.ArrayList;

public class MathUtil {

	public static boolean squareAndMultiply = true;
	public static void setSquareAndMultiply(boolean b){
		squareAndMultiply = b;
	}
	
	public static BigInteger squareAndMultiply(BigInteger base, BigInteger exp){
		byte[] bytearray=exp.toByteArray();

		BigInteger result=BigInteger.valueOf(1);
		
		ArrayList<Boolean> blubb=new ArrayList<Boolean>();
		
		for(int i=bytearray.length-1;i>=0;i--){

			//bring bits in better iterable shape ;)
			for(int j=0;j<8;j++){
				if(exp.bitLength()<=j)
					break;

				boolean h=(bytearray[i] & (1 << j)) >0 ;
				blubb.add(0, h);
			}
			
		}

		while(!blubb.get(0)){			
			blubb.remove(0);
		}
		
		for(boolean b:blubb){
			result=result.pow(2);
			if(b)
				result=result.multiply(base); 	
		}
		
		return result;
	}
	
	public static BigInteger squareAndMultiplyMod(BigInteger base, BigInteger exp, BigInteger mod){
		
		byte[] bytearray=exp.toByteArray();

		BigInteger result=BigInteger.valueOf(1);
		
		ArrayList<Boolean> blubb=new ArrayList<Boolean>();
		
		for(int i=bytearray.length-1;i>=0;i--){

			//bring bits in better iterable shape ;)
			for(int j=0;j<8;j++){
				if(exp.bitLength()<=j)
					break;

				boolean h=(bytearray[i] & (1 << j)) >0 ;
				blubb.add(0, h);
			}
			
		}

		while(!blubb.get(0)){			
			blubb.remove(0);
		}
		
		for(boolean b:blubb){
			result=result.pow(2).mod(mod);
			if(b)
				result=(result.multiply(base)).mod(mod); 	
		}
		
		return result;
	}
	
	
	
	public static BigInteger modPow(BigInteger base,BigInteger exp, BigInteger m){
		BigInteger biggi = null;
		long t = System.currentTimeMillis();
		if (squareAndMultiply)
			biggi = squareAndMultiplyMod(base,exp,m);
		else // standard implementation (delegation)
			biggi = base.modPow(exp, m);
		long t2 = System.currentTimeMillis();
		if (squareAndMultiply)
			System.out.println("squareAndMultiply: " + (t2-t));
		else
			System.out.println("modPow: " + (t2-t));
		return biggi;
	}	
}
