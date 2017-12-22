
/**Use three pointers referring to the largest number,the second largest number,
 * and the third largest number respectively.
 * First step to initialize the three pointers of the first three numbers in the array.
 * 
 * Second step to loop the rest numbers of the array. Compare each one with the number
 * which pointed by the third pointer.
 * 
 * Finally return the third largest number.
 * 
 * Time Complexity O(n), Space Complexity(1).
 * @author Tianyu Hou
 *
 */
public class find3rdLargestNumber {
	public static void main(String[] args) {
		int[] arr = { 4, 2, 12, 0, 5, 6, 10, 123, 7 };
		System.out.println(solution(arr));
	}
	
	
	public static int solution(int[] arr) {
		int firstptr;
		int secondptr;
		int thirdptr;

		if (arr.length < 3) {
			return -1;
		}

		// initialize the three pointers , find the third number in first three numbers.
		int i = 0;

		if (arr[i] <= arr[i + 1]) {
			firstptr = i + 1;
			secondptr = i;
		} else {
			firstptr = i;
			secondptr = i + 1;
		}

		if (arr[secondptr] > arr[i + 2]) {
			thirdptr = i + 2;
		} else {
			int tmp = secondptr;
			if (arr[i + 2] < arr[firstptr]) {
				secondptr = i + 2;
			} else {
				int temp = firstptr;
				firstptr = i + 2;
				secondptr = temp;
			}
			thirdptr = tmp;
		}
		// try to locate the third largest number from 4th number to the end;
		for (int j = 3; j < arr.length; j++) {
			if (arr[j] > arr[thirdptr]) {
				if (arr[j] > arr[secondptr]) {
					if (arr[j] > arr[firstptr]) {
						int temp1 = firstptr;
						int temp2 = secondptr;
						firstptr = j;
						secondptr = temp1;
						thirdptr = temp2;
					} else {
						int temp = secondptr;
						secondptr = j;
						thirdptr = temp;
					}
				} else {
					thirdptr = j;
				}
			}
		}
		return arr[thirdptr];
	}
}
