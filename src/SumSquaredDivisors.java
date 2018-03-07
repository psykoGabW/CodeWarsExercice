import java.util.ArrayList;
import java.util.List;

/*
 * Divisors of 42 are : 1, 2, 3, 6, 7, 14, 21, 42. 
 * These divisors squared are: 1, 4, 9, 36, 49, 196, 441, 1764. 
 * The sum of the squared divisors is 2500 which is 50 * 50, a square!

 * Given two integers m, n (1 <= m <= n) we want to find all integers between m and n whose sum of squared divisors is itself a square. 42 is such a number.

 * The result will be an array of arrays or of tuples (in C an array of Pair) or a string, each subarray having two elements, first the number whose squared divisors is a square and then the sum of the squared divisors.

#Examples:

list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
list_squared(42, 250) --> [[42, 2500], [246, 84100]]
The form of the exam

*/
public class SumSquaredDivisors {

	public static String listSquared(long m, long n) {
		// your code
		String result = "";
		for (long numberToParse = m; numberToParse <= n; numberToParse++) {
			List<Long> dividors = new ArrayList<Long>();
			for (Long i = 1L; i <= numberToParse; i++) {
				if ((numberToParse % i) == 0) {
					dividors.add(0, i);
				}
			}
			double squaredSum = (double) dividors
					.stream()
					.mapToDouble(Long::doubleValue)
					.map(i -> Math.pow(i, 2))
					.sum();
			
			double squareRoot =  Math.sqrt(squaredSum);
			
			if (squareRoot == Math.floor(squareRoot)) {
				result += (result==""?"":", ") + String.format("[%d, %.0f]", numberToParse, squaredSum);
			}

		}

		return "["+result+"]";		
	}

	public static void main(String[] args) {
		System.out.println(listSquared(1, 250));
	}
}