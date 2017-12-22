import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) {
		//test Question 1 twoSum
		Solution s = new Solution();
		int[] arr = {-2,-1,1,2,3,5,6,7,9,10,15};
		List<List<Integer>> res1 = s.findTwoSum(arr, 10);
		List<List<Integer>> res2 = s.findSumThree(arr, 10);
		for(List<Integer> list : res1) {
			System.out.println(list);
		}
		
		for(List<Integer> list : res2) {
			System.out.println(list);
		}
		
		//test Question 2 Tree
		TreeNode root = s.new TreeNode(0);
		root.left = s.new TreeNode(1);
		root.right = s.new TreeNode(2);
		root.left.left = s.new TreeNode(3);
		root.left.right = s.new TreeNode(4);
		root.right.left = s.new TreeNode(5);
		root.right.right = s.new TreeNode(6);
		
		List<Integer> res = s.printAllNodes(root, 2);
		for(int i:res) {
			System.out.println(i);
		}
	}
	
	//Question1
	public List<List<Integer>> findTwoSum(int[] arr, int k) {
		List<List<Integer>> res = new ArrayList<>();
 		if(arr == null || arr.length == 0) return res;
		int low = 0;
		int high = arr.length-1;
		while(low < high) {
			if(arr[low] + arr[high] == k) {
				res.add(Arrays.asList(arr[low],arr[high]));
				low++;
				high--;
			}
			else if(arr[low] + arr[high] < k) low++;
			else if(arr[low] + arr[high] > k) high--;
		}
		return res;
	}
	
	public List<List<Integer>> findSumThree(int[] arr, int k){
		List<List<Integer>> res = new ArrayList<>();
		if(arr == null || arr.length < 3) return res;
		int high = arr.length-1;
		for(int i = 0; i<arr.length-2; i++) {
			if(arr[i] + 2*arr[high] < k) continue;
			if(3*arr[i] > k) break;
			twoSum(res,arr,k-arr[i],arr[i],i+1);
		}
		return res;
	}
	
	private void twoSum(List<List<Integer>> res, int[] arr, int k, int first, int low) {
		int high = arr.length-1;
		while(low < high) {
			if(arr[low] + arr[high] == k) {
				res.add(Arrays.asList(first,arr[low],arr[high]));
				return;
			}else if(arr[low] + arr[high] < k) {
				low++;
			}else {
				high--;
			}
		}
	}
	
	//Question2
	public List<Integer> printAllNodes(TreeNode root, int k) {
		List<Integer> res = new ArrayList<>();
		if(root == null || k<1) return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int cur = 1;
		int level = 1;
		int next = 0;
		while(!queue.isEmpty()) {
			TreeNode tmp = queue.poll();
			if(level == k) {
				res.add(tmp.val);
			}
			cur--;
			
			if(tmp.left != null) {
				queue.add(tmp.left);
				next++;
			}
			
			if(tmp.right != null) {
				queue.add(tmp.right);
				next++;
			}
			
			if(cur == 0) {
				cur = next;
				next = 0;
				level++;
			}
		}
		
		return res;
	}
	
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
}
