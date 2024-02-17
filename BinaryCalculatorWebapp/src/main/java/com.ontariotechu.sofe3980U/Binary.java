package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	
	
	/**
	 * Logical OR of two binary variables.
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 OR num2</i>.
	 */
	public static Binary or(Binary num1, Binary num2) {
		String bin1 = num1.number;
		String bin2 = num2.number;
		String result = "";

		// add leading zero padding to both numbers
		while (bin1.length() < bin2.length()) {
			bin1 = "0" + bin1;
		}
		while (bin2.length() < bin1.length()) {
			bin2 = "0" + bin2;
		}

		// Go from right to left and apply the OR operation on each pair of bits between
		// bin1 and bin2
		for (int i = 0; i < bin1.length(); i++) {
			if (bin1.charAt(i) == '1' || bin2.charAt(i) == '1') {
				result += "1";
			} else {
				result += "0";
			}
		}

		// return Binary object with the result
		return new Binary(result);
	}

	/**
	 * Logical AND of two binary variables.
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 AND num2</i>.
	 */
	public static Binary and(Binary num1, Binary num2) {
		String bin1 = num1.number;
		String bin2 = num2.number;
		String result = "";

		// add leading zero padding to both numbers
		while (bin1.length() < bin2.length()) {
			bin1 = "0" + bin1;
		}
		while (bin2.length() < bin1.length()) {
			bin2 = "0" + bin2;
		}

		// Go from right to left and apply the AND operation on each pair of bits
		// between
		// bin1 and bin2
		for (int i = 0; i < bin1.length(); i++) {
			if (bin1.charAt(i) == '1' && bin2.charAt(i) == '1') {
				result += "1";
			} else {
				result += "0";
			}
		}

		// return Binary object with the result
		return new Binary(result);
	}

	/**
	 * Multiplication of two binary variables.
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 x num2</i>.
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		// init new Binary number for result, start at 0
		Binary result = new Binary("0");
		
		// check for 0 edge case, because given add method does not for multiplying
		if (num1.number=="0" || num2.number=="0") return result;

		// reverse num2 to start multiplying from the least significant bit
		String num2Reversed = new StringBuilder(num2.number).reverse().toString();

		for (int i = 0; i < num2Reversed.length(); i++) {
			if (num2Reversed.charAt(i) == '1') {
				// For every '1' in num2, shift num1 left by 'i' positions
				StringBuilder shiftedNum1Builder = new StringBuilder(num1.number);
				for (int j = 0; j < i; j++) {
					// add 0's to shift
					shiftedNum1Builder.append("0");
				}
				String shiftedNum1 = shiftedNum1Builder.toString();

				// Add shifted number to the result
				result = add(result, new Binary(shiftedNum1));
			}
		}

		return result;
	}
}	
