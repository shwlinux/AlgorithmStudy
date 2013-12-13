package com.shwlinux.algorithmstudy.Math;

public class CustomMath {
	public static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    return true;
	}
	
	public static int GCD(int a, int b) {
	   if (b==0) return a;
	   return GCD(b,a%b);
	}
	
	public static boolean isRelativePrime(int n, int m) {
		int gcd = GCD(n, m);
		
		if(gcd == 1)
			return true;
		else
			return false;
	}
	
	public static int numberOfRelativelyPrime(int n) {
		int nRet = 1;

		for(int i = 2; i < n; i++) {
			if(n % 2 == 0 && i % 2 == 0)
				continue;
			if(n % 3 == 0 && i % 3 == 0)
				continue;
			if(n % 5 ==0 && i % 5 == 0)
				continue;
			if(n % 7 == 0 && i % 7 ==0)
				continue;
			
			if (isRelativePrime(n, i))
				nRet++;
		}
		return nRet;
	}
	
	public static double getTotient(int n) {
		double dRet = (double)n / (double)numberOfRelativelyPrime(n);
		return dRet;
	}
}
