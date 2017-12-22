
/**Use the method like quicksort to find the kth largest number.
 * but don't need to do it until the end.
 * Just to compare the kth with the index of pivot number.
 * If they are the same, then just return.
 * Time Complexity O(n), Space Complexity O(1). 
 * @author Tianyu Hou
 *
 */
public class findKthLargestNumber {
	public static void main(String[] args) {
		int[] arr = { 2, 4, 1, 8, 9, 10, 34, 76, 233 };
		System.out.println(solution(arr, 2));
	}

	public static int solution(int[] arr, int k) {
		return QuickSelect(arr, 0, arr.length - 1, arr.length - k);
	}

	/**
	 * incase of the largest number or the smallest number that we choose to be the
	 * pivot number, add this method to determine the median number sto optimize the
	 * average of complexity of time.
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	public static int findMedian(int[] arr, int l, int r) {
		int mid = (l + r) / 2;
		if (arr[l] > arr[mid]) {
			swap(arr, l, mid);
		}
		if (arr[l] > arr[r]) {
			swap(arr, l, r);
		}
		if (arr[mid] > arr[r]) {
			swap(arr, mid, r);
		}
		swap(arr, mid, r - 1);
		return arr[r - 1];
	}

	/**
	 * compare k with the index of pivot number,
	 * then determine which part to find kth number recursively.
	 * @param arr
	 * @param l
	 * @param r
	 * @param k
	 * @return
	 */
	public static int QuickSelect(int[] arr, int l, int r, int k) {
		int mid = partition(arr, l, r);
		if (k < mid && l < mid - 1) {
			return QuickSelect(arr, l, mid - 1, k);
		} else if (k > mid && mid + 1 < r) {
			return QuickSelect(arr, mid + 1, r, k);
		}
		return arr[k];
	}

	/**split the array, and find the position of pivot number.
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	public static int partition(int[] arr, int l, int r) {
		int pivot = findMedian(arr, l, r);
		int left = l;
		int right = r - 1;

		while (true) {
			while (left < right && arr[++left] < pivot)
				;
			while (left < right && arr[--right] > pivot)
				;

			if (left < right) {
				swap(arr, left, right);
			} else {
				break;
			}
		}

		swap(arr, left, r - 1);
		return left;

	}

	public static void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}

}
