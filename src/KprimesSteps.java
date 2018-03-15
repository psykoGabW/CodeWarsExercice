import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KprimesSteps {

	private static boolean isKPrime(int k, long number) {
		if (k==1) {
			for(long i=2; i<number; i++) {
				if (number%i==0) {
					return false;
				}
			}
			return true;
		} else {
			for(long i=2; i <= Math.ceil(Math.pow((double)number, (double)1/k)) ; i++)
			{
				if (number%i==0 &&isKPrime(1,i) &&  isKPrime(k-1, number/i))  {
					return true;
				}			
			}
			return false;
		}
	}
	
	public static List<long[]> kprimesStep(int k, int step, long start, long nd) {
		// your code

		ArrayList<long[]> result = new ArrayList<long[]>();
		for (long l = start; l+step <= nd; l++) {
			if (isKPrime(k,l) && isKPrime(k,l+step)) {
				long[] numberPair = {l, l + step}; 				
				result.add(numberPair);
			}
		}
				
		return result;		
	}
	
	public static void main(String[] arg) {
		
		
		System.out.println("Start");
		System.out.println(Math.pow(4., 0.5));
		long start = System.nanoTime();

		List<long[]> kPrimes = kprimesStep(6, 8, 2627371, 2627581);
		// List<long[]> kPrimes = kprimesStep(10, 8, 2425364, 2425700);
		//List<long[]> kPrimes= kprimesStep(2,2,0,50);

		System.out.println("End");
		System.out.println((System.nanoTime() - start) / 1.e9);
		
		System.out.print("[");
		Iterator<long[]> itPair = kPrimes.iterator();
		while(itPair.hasNext()) {
			long[] pair = (long[])itPair.next();
			System.out.print(String.format("[%d,%d]", pair[0],pair[1]) + (itPair.hasNext()?",":""));			
		}
		System.out.println("]");
		
	}

}
