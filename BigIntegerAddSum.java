public class BigIntegerAddSum {
	static boolean isSmaller = false;

	static boolean isSmaller(String num1, String num2) {
		int n1 = num1.length(), n2 = num2.length();
		if (n1 < n2)
			return true;
		if (n2 < n1)
			return false;

		for (int i = 0; i < n1; i++)
			if (num1.charAt(i) < num2.charAt(i))
				return true;
			else if (num1.charAt(i) > num2.charAt(i))
				return false;

		return false;
	}

	static String findDiff(String num1, String num2) {
		isSmaller = isSmaller(num1, num2);
		if (isSmaller) {
			String t = num1;
			num1 = num2;
			num2 = t;
		}
		String result = "";
		int n1 = num1.length(), n2 = num2.length();

		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();

		int carry = 0;
		for (int i = 0; i < n2; i++) {
			int sub = ((int) (num1.charAt(i) - '0') - (int) (num2.charAt(i) - '0') - carry);
			if (sub < 0) {
				sub = sub + 10;
				carry = 1;
			} else
				carry = 0;

			result += (char) (sub + '0');
		}

		for (int i = n2; i < n1; i++) {
			int sub = ((int) (num1.charAt(i) - '0') - carry);

			if (sub < 0) {
				sub = sub + 10;
				carry = 1;
			} else
				carry = 0;
			result += (char) (sub + '0');
		}
		result = new StringBuilder(result).reverse().toString();
		return isSmaller ? "-" + result : result;
	}

	static String findSum(String num1, String num2) {
		if (num1.length() > num2.length()) {
			String t = num1;
			num1 = num2;
			num2 = t;
		}
		String sum = "";
		int n1 = num1.length(), n2 = num2.length();
		int diff = n2 - n1;

		int carry = 0;
		for (int i = n1 - 1; i >= 0; i--) {
			int sumNum = ((int) (num1.charAt(i) - '0') + (int) (num2.charAt(i + diff) - '0') + carry);
			sum += (char) (sumNum % 10 + '0');
			carry = sumNum / 10;
		}

		for (int i = n2 - n1 - 1; i >= 0; i--) {
			int sumNum = ((int) (num2.charAt(i) - '0') + carry);
			sum += (char) (sumNum % 10 + '0');
			carry = sumNum / 10;
		}

		if (carry > 0)
			sum += (char) (carry + '0');
		sum = new StringBuilder(sum).reverse().toString();
		return isSmaller ? "-" + sum : sum;
	}

	public static void main(String[] args) {
		String operator = args[0];
		String num1 = args[1];// "12";
		String num2 = args[2];// "198111999999999";
		if ("ADD".equals(operator)) {
			if (num1.startsWith("-") && num2.startsWith("-"))
				System.out.println("-" + findSum(num1.replace("-", ""), num2.replace("-", "")));
			else if (!num1.startsWith("-") && !num2.startsWith("-"))
				System.out.println(findSum(num1, num2));
			else if (num1.startsWith("-"))
				System.out.println(findDiff(num2, num1.replace("-", "")));
			else if (num2.startsWith("-"))
				System.out.println(findDiff(num1, num2.replace("-", "")));
		}
		if ("SUB".equals(operator)) {
			if (num1.startsWith("-") && num2.startsWith("-"))
				System.out.println(findDiff(num2.replace("-", ""), num1.replace("-", "")));
			else if (!num1.startsWith("-") && !num2.startsWith("-"))
				System.out.println(findDiff(num1, num2));
			else if (num1.startsWith("-"))
				System.out.println("-" + findSum(num1.replace("-", ""), num2));
			else if (num2.startsWith("-"))
				System.out.println(findSum(num1, num2.replace("-", "")));
		}
	}
}