
public class ReverseInteger {
	public static void main(String[] args){
		
		assert reverse(-123) == -321 ;
		assert reverse(1534236469) == 0;
		
		assert willUnderflow(intToCharArray(-1534236469)) == true;
		assert willUnderflow(intToCharArray(-123)) == false;	
		
		assert reverse(-1534236469) == 0;
		char[] something = intToCharArray(-2147483648);
		assert willUnderflow(something) == true;
		
		assert willUnderflow("-1563847412".toCharArray()) == true;
		
		assert willUnderflow("-2147483412".toCharArray()) == false;
		
	}
	
	public static int reverse(int x){	
		char[] numString = Integer.toString(x).toCharArray();
		char[] reverseNumString = new char[numString.length];
		
		if(numString[0] == '-'){
			reverseNumString[0] = numString[0];
			
			if(willUnderflow(numString)){
				return 0;
			}
			
			for(int reverNumPtr = 1; reverNumPtr<reverseNumString.length; reverNumPtr++) {
				reverseNumString[reverNumPtr] = numString[numString.length-reverNumPtr];
			}
		} else {
			if(willOverflow(numString)){
				return 0;
			}
			
			for(int i = 0; i<reverseNumString.length; i++) {
				reverseNumString[i] = numString[numString.length-1-i];
			}
		}
		
		return Integer.valueOf(String.valueOf(reverseNumString));
	}
	
	private static boolean willUnderflow(char[] numString) {
		char[] minNum = Integer.toString(Integer.MIN_VALUE).toCharArray();
		if(numString.length == minNum.length){
			for(int i=1; i<numString.length; i++) {
				if (minNum[i] > numString[numString.length-i]) {
					return false;
				} else if (minNum[i] < numString[numString.length-i]) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean willOverflow(char[] numString) {
		char[] maxNum = Integer.toString(Integer.MAX_VALUE).toCharArray();
		if(numString.length == maxNum.length){
			for(int i=0; i<numString.length; i++) {
				if (maxNum[i] > numString[numString.length-1-i]) {
					return false;
				} else if (maxNum[i] < numString[numString.length-1-i]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static char[] intToCharArray(int num){
		return Integer.toString(num).toCharArray();
	}
}
