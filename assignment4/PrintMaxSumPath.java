public class PrintMaxSumPath {
	class TreeNode{
		int val;
		TreeNode right;
		TreeNode left;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		PrintMaxSumPath p = new PrintMaxSumPath();
		TreeNode root = p.new TreeNode(-4);
		root.left = p.new TreeNode(-1);
		root.right = p.new TreeNode(3);
		root.left.left = p.new TreeNode(3);
		root.left.right = p.new TreeNode(6);
		root.left.left.right = p.new TreeNode(-2);
		root.left.right.left = p.new TreeNode(5);
		root.left.right.right = p.new TreeNode(7);
		root.right.left = p.new TreeNode(4);
		root.right.right = p.new TreeNode(9);
		System.out.println(p.solution(root));
	}
	
	public int solution(TreeNode root) {
        if(root == null) return 0;
        int[] max = {root.val};
        dfs(root,max);
        return max[0];
    }
    
    private int dfs(TreeNode node, int[] max){
        if(node == null) return 0;
        int left = Math.max(0,dfs(node.left,max));
        int right = Math.max(0,dfs(node.right,max));
        max[0] = Math.max(max[0],left+right+node.val);
        return Math.max(left,right)+node.val;
    }
	
}
