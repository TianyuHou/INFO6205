import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		//test Question1
		int[] nums = {2,5,1,2,3,9,5,4,6};
		CircularLinkedList cll = solution.createSortedCircularLinkedList(nums);
		cll.display(); // 1 -> 2 -> 2 -> 3 -> 4 -> 5 -> 5 -> 6 -> 9;
		System.out.println();
		System.out.println(cll.end.val); // 9
		System.out.println(cll.end.next.val); // 1
		System.out.println();
		
		//test Question2
		TreeNode root = solution.new TreeNode(0);
		root.left = solution.new TreeNode(1);
		root.right = solution.new TreeNode(2);
		root.left.left = solution.new TreeNode(3);
		root.left.right = solution.new TreeNode(4);
		root.right.left = solution.new TreeNode(5);
		root.right.right = solution.new TreeNode(6);
		
		TreeNode node = solution.new TreeNode(1);
		node.left = solution.new TreeNode(4);
		node.right = solution.new TreeNode(3);
		//true
		System.out.println(solution.existMirrorTree(root,node));
		System.out.println();
		
		//test Question3
		List<Vertex> list = new ArrayList<>();
		for(int i = 0; i<9; i++) {
			Vertex v = solution.new Vertex(i);
			list.add(v);
		}
		list.get(0).adjacentVertex.add(list.get(1));
		list.get(0).adjacentVertex.add(list.get(2));
		list.get(0).adjacentVertex.add(list.get(3));
		
		list.get(1).adjacentVertex.add(list.get(4));
		list.get(2).adjacentVertex.add(list.get(4));
		
		list.get(4).adjacentVertex.add(list.get(5));
		list.get(3).adjacentVertex.add(list.get(5));
		
		list.get(6).adjacentVertex.add(list.get(7));
		
		solution.floodFill(list);
		// 1 1 1 1 1 1 2 2 3
		for(Vertex v : list) {
			System.out.print(v.color+" ");
		}
	}

	// 1. Create Sorted Circular Link List
	public CircularLinkedList createSortedCircularLinkedList(int[] nums) {
		CircularLinkedList circularLinkedList = new CircularLinkedList();
		for(int n : nums) {
			circularLinkedList.insert(n);
		}
		return circularLinkedList;
	}

	class CircularLinkedList {
		ListNode start;
		ListNode end;
		int size;

		public CircularLinkedList() {
			this.start = null;
			this.end = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void insert(int val) {
			ListNode node = new ListNode(val);
			if (start == null) {
				start = node;
				end = node;
				start.next = end;
			} else if (val <= start.val) {
				end.next = node;
				node.next = start;
				start = node;
			} else if (val >= end.val) {
				end.next = node;
				node.next = start;
				end = node;
			} else {
				ListNode cur = start;
				ListNode pre = end;
				while (cur != end && cur.val < val) {
					pre = pre.next;
					cur = cur.next;
				}
				pre.next = node;
				node.next = cur;
			}
			size++;
		}
		
		public void display() {
			ListNode tmp = start;
			while(tmp != end) {
				System.out.print(tmp.val+" -> ");
				tmp = tmp.next;
			}
			System.out.print(tmp.val);
		}

		public void deleteAtpos(int pos) {
			if (pos == 1 && size == 1) {
				start = null;
				end = null;
				size = 0;
				return;
			}
			if (pos == 1) {
				start = start.next;
				end.next = start;
				size--;
				return;
			}
			if (pos == size) {
				ListNode tmp = start;
				while(tmp.next != end) {
					tmp = tmp.next;
				}
				tmp.next = start;
				end = tmp;
				size--;
				return;
			}
			ListNode node = start;
			pos = pos - 1;
			for (int i = 1; i < size - 1; i++) {
				if (i == pos) {
					ListNode tmp = node.next;
					tmp = tmp.next;
					node.next=tmp;
					break;
				}
				node = node.next;
			}
			size--;
		}
	}

	class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	
	//2. Check if Mirror of a subtree exists in a tree
	public boolean existMirrorTree(TreeNode root, TreeNode node) {
		if(root == null && node == null) return true;
		if((root == null && node != null) || (root != null && node == null)) return false;
		if(root.val == node.val) {
			if(helper(root,node)) return true;
		}
		return existMirrorTree(root.left, node) || existMirrorTree(root.right, node);
	}
	
	private boolean helper(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		return left.val == right.val && helper(left.left,right.right) && helper(left.right,right.left);
	}
	
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	//3. FloodFill program
	public void floodFill(List<Vertex> list) {
		if(list == null || list.size() == 0) return;
		Queue<Vertex> queue = new LinkedList<>();
		HashSet<Vertex> set = new HashSet<>();
		int color = 1;
		for(Vertex v : list) {
			if(!set.contains(v)) {
				v.color = color;
				queue.add(v);
				set.add(v);
				while(!queue.isEmpty()) {
					Vertex tmp = queue.poll();
					for(Vertex neighbour : tmp.adjacentVertex) {
						if(set.contains(neighbour)) continue;
						neighbour.color = color;
						queue.add(neighbour);
						set.add(neighbour);
					}
				}
				//change color;
				color++;
			}
		}
	}

	class Vertex{
		public int id;
		public int color;
		public List<Vertex> adjacentVertex = new ArrayList<>();
		
		public Vertex(int id){
			this.id = id;
		}
	}
}
