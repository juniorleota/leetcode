
public class Palindrome {
	public static void main(String[] args) {
		System.out.println(longestPalindrome("bannnaaaa"));
	}
	
	public static String longestPalindrome(String s) {
		if (s.length() == 1) return s;
		char currChar, nextChar;
		String longestPalindrome = "";
		for(int i=0; i< s.length() - 1; i++) {
			currChar = s.charAt(i);
			nextChar = s.charAt(i+1);
			if  (currChar == nextChar) {
				longestPalindrome = getPalindrome(s, i, i+1, longestPalindrome);
			} 
			if (i>0 && s.charAt(i-1) == nextChar) {
				longestPalindrome = getPalindrome(s, i-1, i+1, longestPalindrome);
			}			
		}
		if (longestPalindrome.isEmpty()) {
			return s.charAt(0)+"";
		}
		return longestPalindrome;
	}
	
	public static String getPalindrome(String s, int leftPtr, int rightPtr, String longestPalindrome){
		int palindromeLeftPtr = leftPtr, palindromeRightPtr = rightPtr;
		while (s.charAt(leftPtr) == s.charAt(rightPtr)) {
			palindromeLeftPtr = leftPtr;
			palindromeRightPtr = rightPtr;
			if (leftPtr == 0 || rightPtr == s.length() - 1) {
				break;
			}
			leftPtr--;
			rightPtr++;
		}
		int palindromeLength = palindromeRightPtr - palindromeLeftPtr + 1;
		if (longestPalindrome.length() > palindromeLength ) {
			return longestPalindrome;
		}
		return s.substring(palindromeLeftPtr, palindromeRightPtr+1);
	}
}
