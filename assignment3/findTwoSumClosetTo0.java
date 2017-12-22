import java.util.Arrays;

public class findTwoSumClosetTo0 {
	public static void main(String[] args) {
		int[] arr = {-9,5,1,3,-10,15,12};
		findSumOfTwoClosetTo0(arr);
	}
	
	/**1.Sort the array;
	 * 2.Calculate the sum of first item and end item;
	 * 3.Calculate the diff between the sum and 0;
	 * 4.Init a pointer to record the previous diff;
	 * 5.If the current diff is larger than the previous one then return;
	 * 6.If no diff is larger than the previous one then determine to return(first item with first+1 item or the last item with the last-1 item);
	 * @param arr
	 */
	public static void findSumOfTwoClosetTo0(int[] arr) {
		// Base condition;
		if (arr == null || arr.length == 0) {
			return;
		}

		// sort the array;
		Arrays.sort(arr);

		// init the variables;
		int start = 0;
		int end = arr.length - 1;
		int sum = arr[start] + arr[end];
		int diff = Math.abs(sum);
		
		//record the previous index of start item and end item;
		int tempStart = start;
		int tempEnd = end;

		while (start < end) {
			//current sum;
			sum = arr[start] + arr[end];
			
			//compare current diff with the previous diff;
			//find the most smallest diff and return;
			if (Math.abs(sum) > diff) {
				System.out.println(
						"Start = " + tempStart + ", End = " + tempEnd + ", Sum = " + (arr[tempStart] + arr[tempEnd]));
				return;
			}
			
			//record current diff;
			diff = Math.abs(sum);
			if (sum == 0) {
				System.out.println("Start = " + start + ", End = " + end + ", Sum = " + 0);
				return;
			} else if (sum < 0) {
				tempStart = start;
				start++;
			} else {
				tempEnd = end;
				end--;
			}
		}
		
		//if end moves to the first; then add one;
		if (end == 0) {
			end++;
		}
		
		//if start moves to the end; then minus one;o
		if (start == arr.length - 1) {
			start--;
		}

		System.out.println("Start = " + start + ", End = " + end + ", Sum = " + (arr[start] + arr[end]));
	}

}
