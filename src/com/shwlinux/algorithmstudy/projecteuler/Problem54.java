package com.shwlinux.algorithmstudy.projecteuler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Problem54 {
	public static void main(String[] args) {
//		String[] p2 = {"2H","2D","4C","4D","4S"};
//		
//		String[] p1 = {"3C","3D","3S","9S","9D"};
//		
//		System.out.println(doGame(p1, p2));
		int nRet = 0;
		File poker = new File("/Users/shwlinux/Documents/workspace/AlgorithmStudy/poker.txt");
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(poker));
			
			String round = "";
			while((round = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(round, " ");
				
				String[] player1 = new String[5];
				String[] player2 = new String[5];
				
				for(int i = 0; i < 5; i++) {
					player1[i] = st.nextToken();
				}
				
				for(int i = 0; i < 5; i++) {
					player2[i] = st.nextToken();
				}
				
				// ÆÄ½Ì ¿Ï·á.
				
				boolean isPlayer1Win = doGame(player1, player2);
				
				if(isPlayer1Win)
					nRet++;						
			}
		} catch (Exception e) {
			
		} finally {
			try { br.close(); } catch (Exception e ){ e.printStackTrace(); }
		}
		
		System.out.println(nRet);
	}
	
	public static boolean doGame(String[] p1, String[] p2) {
		int p1hand = getHandAndRank(p1);
		int p2hand = getHandAndRank(p2);

		if(p1hand > p2hand)
			return true;
		else if (p1hand < p2hand)
			return false;
		else {
			int nRet = compareHighestCard(p1, p2);
			
			if(nRet > 0)
				return true;
			else
				return false;
		}		
	}
	
	public static int getHandAndRank(String[] pokers) {
		int nRet = 0;
		if((nRet = isRoyalFlush(pokers)) != 0)
			return nRet;
		else if((nRet = isStraightFlush(pokers)) != 0)
			return nRet;
		else if((nRet = isFourOfAKind(pokers)) != 0)
			return nRet;
		else if((nRet = isFullHouse(pokers)) != 0)
			return nRet;
		else if((nRet = isFlush(pokers)) != 0)
			return nRet;
		else if((nRet = isStraight(pokers)) != 0)
			return nRet;
		else if((nRet = isThreeOfAKind(pokers)) != 0)
			return nRet;
		else if((nRet = isTwoPairs(pokers)) != 0)
			return nRet;
		else if((nRet = isOnePair(pokers)) != 0)
			return nRet;
		else if((nRet = isHighCard(pokers)) != 0)
			return nRet;
		else
			return nRet;
	}
	
	private static int isThreeOfAKind(String[] pokers) {
		int rank = 4;
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}
		
		Arrays.sort(values);
		
		for(int i = 0; i < 3; i++) {
			if(values[i] == values[i + 1] && values[i + 1] == values[i + 2]) {
				int value = values[i];
				return Integer.parseInt(String.valueOf(rank) + String.valueOf(value));
			}				
		}
		
		return 0;
	}

	private static int isStraight(String[] pokers) {
		int rank = 5;
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}
		
		Arrays.sort(values);
		
		for(int i = 0; i < 4; i++) {
			if(values[i] + 1 != values[i + 1])
				return 0;
		}
		
		return Integer.parseInt(String.valueOf(rank) + String.valueOf(values[4]));
	}

	private static int isFlush(String[] pokers) {
		String suit = getSuit(pokers[0]);
		for(int i = 1; i < pokers.length; i++) {
			String temp = getSuit(pokers[i]);
			
			if(!suit.equals(temp))
				return 0;
		}
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}
		Arrays.sort(values);
		return Integer.parseInt("6" + Integer.toString(values[4]));
	}

	private static int isHighCard(String[] pokers) {
		int rank = 1;
		
		return rank;
	}

	private static int isOnePair(String[] pokers) {
		int rank = 2;
		
		int[] values = new int[pokers.length];
		
		for(int i = 0; i < pokers.length; i++) {
			values[i] = getValue(pokers[i]);
		}
		
		Arrays.sort(values);
		
		for(int i = 0; i < pokers.length - 1; i++) {
			if(values[i] == values[i + 1]) {
				int value = values[i];
				return Integer.parseInt(String.valueOf(rank) + String.valueOf(value));
			}				
		}
		
		return 0;
	}

	private static int isTwoPairs(String[] pokers) {
		int rank = 3;
		int max = 0;
		int temp = -1;
	
		String[] tempPokers = new String[3];
		if((temp = hasPair(pokers, tempPokers)) != -1) {
			if (temp > max)
				max = temp;
		}
		else
		{
			return 0;
		}
		
		String[] tempPokers2 = new String[1];
		if((temp = hasPair(tempPokers, tempPokers2)) != -1) {
			if (temp > max)
				max = temp;
		}
		else
		{
			return 0;
		}
		
		return Integer.parseInt(String.valueOf(rank) + String.valueOf(max));
	}

	private static int hasPair(String[] pokers, String[] temp) {
		boolean bFind = false;
		int first = 0, second = 0;
		
		for(int i = 0; i < pokers.length - 1; i++) {
			for(int j = i + 1; j < pokers.length; j++) {
				if(getValue(pokers[i]) == getValue(pokers[j])) {
					bFind = true;
					first = i;
					second = j;
					break;
				}
			}
			
			if(bFind)
				break;
		}
		
		if(bFind) {
			int value = getValue(pokers[first]);
			
			int j = 0;
			for(int i = 0; i < pokers.length; i++) {
				if(i != first && i != second) {
					temp[j] = pokers[i];
					j++;
				}
			}

			return value;
		}
		
		return -1;
	}
	
	private static int isFullHouse(String[] pokers) {
		int rank = 7;
		
		Arrays.sort(pokers, new Comparator<String>() {			
			public int compare(String s1, String s2) {
				return getValue(s1) - getValue(s2);
			}
		});
		
		for(int i = 0; i < pokers.length - 2; i++) {
			if(getValue(pokers[i]) == getValue(pokers[i + 1]) && getValue(pokers[i + 1]) == getValue(pokers[i + 2])) {
				if( !getSuit(pokers[i]).equals(pokers[i + 1]) &&
					!getSuit(pokers[i]).equals(pokers[i + 2]) &&
					!getSuit(pokers[i+1]).equals(pokers[i + 2])) {
					
					int first = 0;
					int second = 0;
					
					for(int j = 0; j < 5; j++) {
						if(j != i && j != i + 1 && j != i + 2) {
							if(first == 0)
								first = j;
							else {
								second = j;
								break;
							}
						}
					}
					
					if(getValue(pokers[first]) == getValue(pokers[second]) &&
						!getSuit(pokers[first]).equals(getSuit(pokers[second]))) {
						int value = getValue(pokers[i]);
					
						return Integer.parseInt(String.valueOf(rank) + String.valueOf(value));
					}
				}
			}
		}
		return 0;
	}

	private static int isFourOfAKind(String[] pokers) {
		int rank = 8;
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}
		
		Arrays.sort(values);
		
		for(int i = 0; i < 2; i++) {
			if(values[i] == values[i + 1] && values[i + 1] == values[i + 2] && values[i + 2] == values[i + 3]) {
				int value = values[i];
				return Integer.parseInt(String.valueOf(rank) + String.valueOf(value));
			}				
		}
		
		return 0;
	}

	private static int isStraightFlush(String[] pokers) {
		int rank = 9;
		
		String temp = getSuit(pokers[0]);
		
		for(int i = 1; i < 5; i++) {
			if(!temp.equals(getSuit(pokers[i]))) {
				return 0;
			}
		}
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}
		
		Arrays.sort(values);
		
		int tempValue = values[0];
		
		for(int i = 1; i < 5; i++) {
			if(values[i] != tempValue + 1) {
				return 0;
			}
			
			tempValue = values[i];
		}
		
		return Integer.parseInt(String.valueOf(rank) + String.valueOf(tempValue));
	}

	private static int isRoyalFlush(String[] pokers) {
		int rank = 10;
		
		String suit = getSuit(pokers[0]);
		for(int i = 1; i < 5; i++) {
			if(!getSuit(pokers[i]).equals(suit))
				return 0;
		}
		
		int[] values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			values[i] = getValue(pokers[i]);
		}

		Arrays.sort(values);
		
		if(values[0] == 20 &&
		   values[1] == 21 &&
		   values[2] == 22 &&
		   values[3] == 23 &&
		   values[4] == 24) {
			return Integer.parseInt(String.valueOf(rank) + String.valueOf(24));
		}
		return 0;
	}

	public static int compareHighestCard(String[] p1, String[] p2) {
		int[] p1values = new int[5];
		int[] p2values = new int[5];
		
		for(int i = 0; i < 5; i++) {
			p1values[i] = getValue(p1[i]);
			p2values[i] = getValue(p2[i]);
		}
		
		Arrays.sort(p1values);
		Arrays.sort(p2values);			
			
		for(int i = 4; i >= 0; i--) {
			if(p1values[i] > p2values[i])
				return 1;
			else if(p1values[i] < p2values[i])
				return -1;
		}
			
		return 0;
	}
	
	public static String getSuit(String poker) {
		return poker.substring(1, 2);
	}
	
	public static int getValue(String poker) {
		String temp = poker.substring(0, 1);
		
		if(temp.equals("T")) {
			return 20;
		}
		else if(temp.equals("J")) {
			return 21;
		}
		else if(temp.equals("Q")) {
			return 22;
		}
		else if(temp.equals("K")) {
			return 23;
		}
		else if(temp.equals("A")) {
			return 24;
		} 
		else {
			return Integer.parseInt(poker.substring(0, 1)) + 10;
		}
	}
}

