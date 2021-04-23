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
