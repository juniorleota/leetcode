
public class ZigzagConversion {
	public static void main(String[] args) {
		// expect PAHNAPLSIIGYIR
		System.out.println(convert("PAYPALISHIRING",3));
	}
	
	public static String convert(String s, int numRows) {
		if (s.length() == 1 || numRows == 1) return s;
        int zig, zag;
		StringBuilder sb = new StringBuilder();
		for(int row=0; row<numRows; row++) {
			zig = (numRows - (row+1))*2;
			zag = row*2;
			int currentPtr = row;
			boolean isZig = true;
			while(currentPtr < s.length()) {
				sb.append(s.charAt(currentPtr));
				if (zig == 0) {
					currentPtr += zag;
				} else if (zag == 0) {
					currentPtr += zig;
				} else {
					if (isZig) {
						currentPtr += zig;
						isZig = false;
					} else {
						currentPtr += zag;
						isZig = true;
					}
				}
			}
		}
		return sb.toString();
	}
}
