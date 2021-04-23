  static TreeNode getLca(TreeNode root, int p, int q) {
		// if left side m h hi nhi ek bhi node ya fir right side m nhi h to null return
		// krege
		if (root == null)
			return null;
		// if left ya right m s ek bhi value match horhi parent tk to vhi root return
		// hoga
		if (root.val == p || root.val == q)
			return root;
		// left and right k liye search krege
		TreeNode leftLca = getLca(root.left, p, q);
		TreeNode rightLca = getLca(root.right, p, q);
		// if dono left and right are not null then the root node is lca only
		if (leftLca != null && rightLca != null)
			return root;
		// else return not null node if possible
		return leftLca != null ? leftLca : rightLca;
	}

	static class TreeNode {
		TreeNode left, right;
		int val;

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.left = left;
			this.right = right;
			this.val = val;
		}
	}
/* 
	Algo-Alert
	The approach is pretty intuitive. Traverse the tree in a depth first manner. The moment you encounter either of the nodes p or q, return some boolean flag. The flag helps to determine if we found the required nodes in any of the paths. The least common ancestor would then be the node for which both the subtree recursions return a True flag. It can also be the node which itself is one of p or q and for which one of the subtree recursions returns a True flag.

	Let us look at the formal algorithm based on this idea.

	Algorithm

	Start traversing the tree from the root node.
	If the current node itself is one of p or q, we would mark a variable mid as True and continue the search for the other node in the left and right branches.
	If either of the left or the right branch returns True, this means one of the two nodes was found below.
	If at any point in the traversal, any two of the three flags left, right or mid become True, this means we have found the lowest common ancestor for the nodes p and q.
	Let us look at a sample tree and we search for the lowest common ancestor of two nodes 9 and 11 in the tree.
*/
