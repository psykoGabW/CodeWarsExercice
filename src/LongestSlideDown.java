import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class LongestSlideDown {

	private static int weightOfNode(int level, int index, int[][] nodesValue, Integer[][] nodesWeight) {
		int weight = 0;

		// Test of pyramid and "brick" level in order to stop calculation if needed
		boolean brokenChain = (nodesValue[level] == null || index < 0 || index >= nodesValue[level].length);

		if (!brokenChain) {
			// Check if this weight has already been calculated
			if (nodesWeight[level] != null && nodesWeight[level][index] != null) {
				// If weight is already buffered, we use it
				weight = nodesWeight[level][index];
			} else {
				// If weight is not in buffer, we calculate it.
				// Weight = nodeValue + Max(sub-nodes)
				weight = nodesValue[level][index];

				if (level < nodesValue.length - 1) {
					weight += Math.max(
							weightOfNode(level + 1, index, nodesValue, nodesWeight),
							weightOfNode(level + 1, index + 1, nodesValue, nodesWeight));
				}
			}

			if (nodesWeight[level] == null) {
				// If it's the first calculation of weight on this level we create this
				// level of weights
				nodesWeight[level] = new Integer[nodesValue[level].length];
			}
			if (nodesWeight[level][index] == null) {
				// We store node weight in buffer if it has not been buffered yet.
				nodesWeight[level][index] = weight;
			}
		} else {
			// If a number chain is broken, we will return a weight of 0
		}
		return weight;
	}

	public static int longestSlideDown(int[][] pyramid) {
		// Code Goes Here..
		int sum = 0;
		if (pyramid == null || pyramid.length == 0) {
			return sum;
		}

		// Search first level of pyramid that is not null

		int level = 0;
		for (level = 0; level < pyramid.length && (pyramid[level] == null || pyramid[level].length == 0); level++) {
		}
		if (level < pyramid.length) {
			// Use of Integer[][] to buffer weight of each nodes
			// Integer is used to allow weight of 0 (if there's negative integer in pyramid)
			Integer[][] nodesWeight = new Integer[pyramid.length][];

			// We use a set to parse pyramid with flat top
			Set<Integer> setSums = new TreeSet<Integer>();
			for (int x = 0; x < pyramid[level].length; x++) {
				setSums.add(weightOfNode(level, x, pyramid, nodesWeight));
			}
			if (!setSums.isEmpty()) {
				sum = Collections.max(setSums);
			}
		}

		return sum;
	}

	public static int longestSlideDown2(int[][] p) {

		for (int i = p.length - 1; i >= 1; i--)
			for (int j = 0; j < i; j++)
				p[i - 1][j] += Math.max(p[i][j], p[i][j + 1]);

		return p[0][0];
	}

	private static void chrono(long start) {
		System.out.println((System.nanoTime() - start) / 1.e9);
	}

	public static void main(String[] args) {

		int[][] pyramid = {
				{ 3 },
				{ 2, 1 },
				{},
				{ 2, 4, 8 },
				{ 8, 5, 9, 9 } };

		int lSD = longestSlideDown(pyramid);
		System.out.println("longestSlideDown (broken Pyramid) : " + lSD);

		lSD = longestSlideDown(null);
		System.out.println("longestSlideDown (null) : " + lSD);

		int[][] pyramidNull = {
		};
		lSD = longestSlideDown(pyramidNull);
		System.out.println("longestSlideDown (pyramid null bis) : " + lSD);

		int[][] hollowPyramid = {
				{},
				{ 2, 4 },
				{ 9, 5, 8 },
				{ 4, 2 }
		};
		lSD = longestSlideDown(hollowPyramid);
		System.out.println("longestSlideDown (hollowPyramid) : " + lSD);

		int[][] test = new int[][] {
				{ 75 },
				{ 95, 64 },
				{ 17, 47, 82 },
				{ 18, 35, 87, 10 },
				{ 20, 4, 82, 47, 65 },
				{ 19, 1, 23, 75, 3, 34 },
				{ 88, 2, 77, 73, 7, 63, 67 },
				{ 99, 65, 4, 28, 6, 16, 70, 92 },
				{ 41, 41, 26, 56, 83, 40, 80, 70, 33 },
				{ 41, 48, 72, 33, 47, 32, 37, 16, 94, 29 },
				{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 },
				{ 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
				{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
				{ 63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
				{ 4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23 },
				};
		
		System.out.println("Ma version");
		long start = System.nanoTime();
		lSD = longestSlideDown(test);
		chrono(start);
		System.out.println("Second Pyramid - exprected 1074 : " + lSD);

		System.out.println("version kata");
		start = System.nanoTime();
		longestSlideDown2(test);
		chrono(start);
		System.out.println("Second Pyramid - exprected 1074 : " + lSD);


		/*
		 * /3/ 2 \1\ 2 4 \8\ 8 5 9 \9\
		 */
		// => 21
	}

}