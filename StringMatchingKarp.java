package practise;

import java.util.Arrays;

/***
 * 
 * @author shruti.i
 *Below program uses Rabin Karp algorithm to calculate the no of matches in a string 
 *It uses the rolling Hash Calculation to reduce hash calculations each time 
 *Multiplier of 3 is used here ,Ideally for a String which can contain any and every charachter including special charcahters ,
 * this should be 127 
 */

public class StringMatchingKarp {
	
	private static int rollingHash=0;
	private static int  patternHash;

	public static void main(String args[]) {
		StringMatchingKarp karp=new StringMatchingKarp();
		String inputString="aabaabaabaabaab";
		String pattern="aab";
		int matches=karp.checkMatchingUsingRabinKarp(inputString,pattern);
		System.out.println("No of matches:::"+matches);
	}
	
	/**
	 * 
	 * @param inputString
	 * @param pattern
	 * @return
	 * Below is the  method which calculates the no of matches in the input string 
	 */
	public  int checkMatchingUsingRabinKarp(String inputString, String pattern){
		computePatternHash(pattern);
		int patternLength=pattern.length();
		int inputLength=inputString.length();
		char[] inputArray=inputString.toCharArray();
		initializeRollingHash(Arrays.copyOfRange(inputArray,0, patternLength));
		int matchCount=0;
		
		/*For the first set of charachters */
		if(StringMatchingKarp.patternHash==StringMatchingKarp.rollingHash) {
			matchCount++;
		}
		
		for(int i=0;i<inputLength-patternLength;i++) {
			boolean hashMatch=checkIfMatchExists(patternLength,inputArray[i],inputArray[patternLength+i]);
			if(hashMatch&&String.valueOf(Arrays.copyOfRange(inputArray,i+1, patternLength+1+i)).equals(pattern)) {
				matchCount++;
			}
			
		}
		return matchCount;
		
	}
	
	/**
	 * This method is called iteratively to find matches
	 * @param patternLength
	 * @param previousChar
	 * @param nextChar
	 * @return
	 */
	
	private boolean checkIfMatchExists(int patternLength,char previousChar,char nextChar) {
		int subtractor=(int)Math.pow(3,patternLength-1)*previousChar;
		StringMatchingKarp.rollingHash=(StringMatchingKarp.rollingHash-(subtractor))*3+nextChar;
		
		if(StringMatchingKarp.patternHash==StringMatchingKarp.rollingHash) {
			System.out.println("Hash Match Occured");
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
/**
 * This method calculates rolling hash for the first time for the first set of charcahters 
 * @param firstSet
 */
	
	private void initializeRollingHash(char [] firstSet) {
		// TODO Auto-generated method stub
		int patternLength=firstSet.length;
		int rollingHash=0;
		for(int k=0;k<patternLength;k++) {
			int multiplier=(int)Math.pow(3,patternLength-k-1);
			rollingHash+=(int) (firstSet[k])*multiplier;
		}
		StringMatchingKarp.rollingHash=rollingHash;
		System.out.println("Rolling Hash is :: "+rollingHash);
	}

	
/***
 * This method calculates the hash value for the pattern 
 * @param pattern
 */
	
	private void computePatternHash(String pattern) {
		int patternLength = pattern.length();
		int patternHash = 0; 
		for (int i = patternLength - 1; i >= 0; i--) {
			patternHash += (pattern.charAt(i)) * (int) (Math.pow(3,patternLength - 1- i));
		}
		StringMatchingKarp.patternHash=patternHash;
		System.out.println("Pattern Hash is"+patternHash);
	}
	

	

}
