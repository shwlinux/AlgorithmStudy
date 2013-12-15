package com.shwlinux.algorithmstudy.projecteuler;
import static com.shwlinux.algorithmstudy.Math.CustomMath.*;

public class Problem64 {
	public static void main(String[] args) {
		
		int nRet = 0;
		
		for(int i = 1; i <= 13; i++) {
			if(isSquare(i))
				continue;
			
			int temp = getPeriod(i);
			
			if(temp % 2 != 0)
				nRet++;
		}
		
		System.out.println(nRet);
	}
	
	public static int getPeriod(int n) {
		int nPeriod = 0;
		int nFirst = (int) Math.floor(Math.sqrt(n));
		
		int numerator = 1;		
		int rootDenominator = n;
		int intDenominator = nFirst;
		
		int fNumerator = numerator;
		int fRootDenominator = rootDenominator;
		int fIntDenominator = intDenominator;
		
		boolean bPeriod = false;
		String numbers = "";
		for(int repeat = 0; ; repeat++) {
			if(repeat != 0) {
				if( numerator == fNumerator &&
					rootDenominator == fRootDenominator &&
					intDenominator == fIntDenominator)
					break;					
			}
			
			int newDenominator = (rootDenominator - (int)Math.pow(intDenominator, 2)) / numerator;
			
			for(int temp = intDenominator + nFirst; temp > 0; temp--) {			
				if(temp % newDenominator == 0) {
					int i = temp / newDenominator;
					numbers += String.valueOf(i);
					
					intDenominator -= newDenominator * i;
					intDenominator = Math.abs(intDenominator);
					numerator = newDenominator;
					break;
				}
			}
		}
		
		System.out.println(numbers);
		nPeriod = numbers.length();
		
		return nPeriod;
	}
}
