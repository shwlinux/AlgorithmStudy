package com.shwlinux.algorithmstudy.projecteuler;

import java.util.Iterator;
import java.util.Vector;
import static com.shwlinux.algorithmstudy.Math.CustomMath.*;

public class Problem60 {
	static Vector<Vector<Long>> vector; 
	public static void main(String[] args) {
		vector = new Vector<Vector<Long>>();
		for(long i = 3; ; i++) {
			if(isPrime(i)) {
				if(update(i))
					return ;			
			}			
		}
	}
	
	public static boolean update(long i) {
		Iterator<Vector<Long>> iter = vector.iterator();
		
		while(iter.hasNext()) {
			Vector<Long> subVector = iter.next();
			
			if(primePairSet(subVector, i)) {
				subVector.add(i);
				
				if(subVector.size() == 5) {
					long nSum = 0;
					Iterator<Long> subIter = subVector.iterator();
					
					while(subIter.hasNext()) {
						long temp = subIter.next();
						nSum += temp;
					}
					
					System.out.println("answer : " + nSum);
					
					return false;
				}
			}
		}
		
		{
			Vector<Long> newSubVector = new Vector<Long>();
			
			newSubVector.add(i);
			vector.add(newSubVector);
		}
		
		return false;
	}
	
	public static boolean primePairSet(Vector<Long> subVector, long i) {
		boolean bRet = true;
		
		Iterator<Long> iter = subVector.iterator();
		
		while(iter.hasNext()) {
			long temp = iter.next();
			
			long pre = Long.parseLong(Long.toString(i) + Long.toString(temp));
			long fore = Long.parseLong(Long.toString(temp) + Long.toString(i));
			
			if(!isPrime(pre) || !isPrime(fore)) { 
				bRet = false;
				break;
			}
		}
		
		return bRet;
	}
}
