/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<TreeNode> generateTrees(int n) {
    return generateTree(1,n);
  }
  
  List<TreeNode> generateTree(int start, int end) {
    var res = new ArrayList<TreeNode>();
    if (start == end) {
      res.add(new TreeNode(start));
      return res;
    }
    
    for(int i = start; i <= end; i++) {
      // given i as root, return its left and right possible permutations
      
      // generate left range
      List<TreeNode> leftTrees = null;
      if (i > start) {
        leftTrees = generateTree(start, i-1);
      }
      
      // right tree range
      List<TreeNode> rightTrees = null;
      if (i < end) {
        rightTrees = generateTree(i+1, end);
      }
      
      // merge left and right
      res.addAll(mergeTreePerm(i, leftTrees, rightTrees));
    }
    
    return res;
  }
  
  List<TreeNode> mergeTreePerm(int n, List<TreeNode> left, List<TreeNode> right) {
    var res = new ArrayList<TreeNode>();
    if (right == null) {
      for(TreeNode leftTree: left) {
        var root = new TreeNode(n);
        root.left = leftTree;
        
        res.add(root);
      }
    } else if (left == null) {
      for(TreeNode rightTree: right) {
        var root = new TreeNode(n);
        root.right = rightTree;
        
        res.add(root);
      }
    } else {
      for(TreeNode leftTree: left) {
        for(TreeNode rightTree: right) {
          var root = new TreeNode(n);
          root.left = leftTree;
          root.right = rightTree;

          res.add(root);
        }
      }
    }
    
    return res;
  }
}
