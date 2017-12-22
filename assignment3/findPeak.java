public class findPeak {
	public static void main(String[] args) {
		int[] arr = {1,2,2,2,4,5,3,3,2,1};
		System.out.println(FindPeak(arr));
	}
	
	/**call the function findPeakHelper to execute recursively;
	 * @param arr
	 * @return
	 */
	public static int FindPeak(int[] arr) {
		return findPeakHelper(0, arr.length - 1, arr);
	}

	/**1.Inti the mid index of the array;
	 * 2.Check if mid item is the first index or the last index then the array dont have peak;
	 * 	 It can also this array is ascending or descending;
	 * 3.Otherwise, if mid item is both larger than the previous on and the next one, then it's the peak just return;
	 * 4.In a special situation, if mid item is the same with the next one, then we need to keep moving index until find the one that is not equal to mid;
	 *   When we find that item; we should compare them and determine go to which side to keep searching;
	 * @param start
	 * @param end
	 * @param arr
	 * @return
	 */
	private static int findPeakHelper(int start, int end, int[] arr) {
		// Base condition;
		if (start > end) {
			return -1;
		}

		// Init mid item;
		int mid = (start + end) / 2;

		// If mid item is the first item of the array or the end of the array;
		// It don't have a peak;
		if (mid == 0 || mid == arr.length - 1) {
			return -1;
		} else {
			
			// If mid item is larger than the previous item and the next item;
			// then it must be the peak;
			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			}

			// If mid item less than the next one;
			// then the peak must on the right side;
			if (arr[mid] < arr[mid + 1]) {
				return findPeakHelper(mid + 1, end, arr);

				// If mid item larger than the next one;
				// then the peak must on the left side;
			} else if (arr[mid] > arr[mid + 1]) {
				return findPeakHelper(start, mid - 1, arr);

				// If mid item equals to the next item;
				// then keep moving until one item is not equal to mid item;
				// Compare the value of them and determine which side to recursive;
			} else {

				// keep moving until first item not equal to mid item;
				for (int i = mid + 2; i < arr.length; i++) {
					if (arr[mid + 1] == arr[i]) {
						continue;
					}

					// If mid item larger than the item after it;
					// peak must on the left side;
					else if (arr[mid + 1] > arr[i]) {
						return findPeakHelper(start, mid-1, arr);

						// If mid item smaller than the item after it;
						// peak must on the right side;
					} else {
						return findPeakHelper(mid + 1, end, arr);
					}
				}

				// If the last item is also equal to mid Item;
				// then if have peak, it must on the left side;
				return findPeakHelper(start, mid - 1, arr);
			}
		}
	}
}
