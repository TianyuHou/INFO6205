import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class PrintTopViewofaBinaryTree {
	
	public static void main(String[] args) {
		//create Tree to Test;
	    TreeNode root =new TreeNode(0);
	    Tree tree = new Tree(root);
	    root.left = new TreeNode(-1);
	    root.left.left = new TreeNode(-2);
	    root.left.right = new TreeNode(3);
	    root.left.left.left = new TreeNode(-3);
	    root.left.left.right = new TreeNode(4);
	    root.left.right.right = new TreeNode(6);
	    root.right = new TreeNode(1);
	    root.right.left = new TreeNode(5);
	    root.right.right = new TreeNode(2);
	    root.right.right.right = new TreeNode(3);
	    root.right.left.right = new TreeNode(7);
	    
	    tree.inorderTraversal(root);
	    System.out.println();
	    solution(root);
	}
	
	public static void solution(TreeNode root) {
		//check base condition;
		if(root == null) {
			return;
		}
		
		//set a treemap to store like (index, value) -> (0,root.val);
		TreeMap<Integer,Integer> map = new TreeMap<>();
		
		//set a queue to do the level order traversal;
		LinkedList<queueItem> queue = new LinkedList<>();
		
		queue.add(new queueItem(root, 0));
		
		//level order traversal;
		while(!queue.isEmpty()) {
			
			queueItem qt= queue.remove();
			TreeNode node = qt.node;
			int dist = qt.distance;
			
			if(!map.containsKey(dist)) {
				map.put(dist, node.val);
			}
			
			if(node.left != null) {
				queue.add(new queueItem(node.left, dist-1));
			}
			
			if(node.right != null) {
				queue.add(new queueItem(node.right, dist+1));
			}
		}
		
		//print the value from left to right;
		Set<Integer> keyset = map.keySet();
		Iterator<Integer> it = keyset.iterator();
		while(it.hasNext()) {
			int key = it.next();
			System.out.print(map.get(key)+" ");
		}
	}

	
}

/**
 * Set a class to store the pair value of (distance to root, TreeNode);
 * @author Yu
 *
 */

class queueItem {
	int distance;
	TreeNode node;

		public queueItem(TreeNode node, int distance) {
			this.distance = distance;
			this.node = node;
		}
		
		
}

class TreeNode{
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int val) {
		this.val = val;
	}
}

class Tree{
	TreeNode root;

	public Tree(TreeNode root) {
		this.root = root;
	}
	
	public void inorderTraversal(TreeNode root) {
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.print(root.val+" ");
		inorderTraversal(root.right);
	}
}
