import java.util.ArrayList;
import java.util.Collections;

public class DigPow {

	
	/*
	 * La mieux notée...
	 * 
	 * 
	public static long digPow(int n, int p) {
    String intString = String.valueOf(n);
    long sum = 0;
    for (int i = 0; i < intString.length(); ++i, ++p)
      sum += Math.pow(Character.getNumericValue(intString.charAt(i)), p);
    return (sum % n == 0) ? sum / n : -1;
  	}
	 * 
	 */
	
	/*
	 * Ma préférée : 
	 *  
	 * public static long digPow(int n, int p) {
    List<Integer> digits = new ArrayList<>();
        int temp = n;
        while (temp > 0) {
            digits.add(0, temp%10);
            temp /= 10;
        }
        final AtomicInteger value = new AtomicInteger(p);
        int sum = (int)digits
            .stream()
            .mapToDouble(Integer::doubleValue)
            .map(i -> Math.pow(i, value.getAndIncrement()))
            .sum();
        if(sum%n == 0)
            return sum/n;
        return -1;
  }
	 */
	
	public static long digPow(int n, int p) {
		
		int nToParse = n;

		ArrayList<Integer> digits = new ArrayList<Integer>();
		do {
			digits.add(nToParse % 10);
			nToParse /= 10;
		} while (nToParse > 0);

		Collections.reverse(digits);

		int index = 0;
		Double sum = 0.0;
		while (index < digits.size()) {
			sum += Math.pow(digits.get(index), p + index);
			index++;
		}

				
		if ((sum%n) == 0 )
		{
			return (long) (sum/n);
		}
		
		return -1;
	}

	public static void main(String[] args) {
		digPow(89, 1);
	}

}