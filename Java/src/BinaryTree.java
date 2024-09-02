import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    static class TreeNode{
        public char val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val)
        {
            this.val = val;
            left = right = null;
        }
        public TreeNode createBinaryTree()
        {
            TreeNode node1 = new TreeNode('A');
            TreeNode node2 = new TreeNode('B');
            TreeNode node3 = new TreeNode('C');
            TreeNode node4 = new TreeNode('D');
            TreeNode node5 = new TreeNode('E');
            TreeNode node6 = new TreeNode('F');
            TreeNode node7 = new TreeNode('G');
            TreeNode node8 = new TreeNode('H');

            node1.left = node2;
            node2.left = node4;
            node2.right = node5;
            node5.right = node8;
            node1.right = node3;
            node3.left = node6;
            node3.right = node7;
            return node1;
        }
        public void preOrder(TreeNode root)
        {
            if(null==root) return ;
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }

        public int size(TreeNode root)
        {
            if(null==root) return 0;
            return 1+size(root.left)+size(root.right);
        }

        /**
         * 统计树节点个数
         */
        private static int _size;
        public int size2(TreeNode root)
        {
            if(null==root) return 0;
            _size++;
            size2(root.left);
            size2(root.right);
            return _size;
        }

        /**
         * 二叉树统计叶子节点
         * @param root
         * @return
         */
        public int getLeafCount(TreeNode root)
        {
            if(root==null) return 0;
            if(root.left==null&&root.right==null) return 1;
            return getLeafCount(root.left)+getLeafCount(root.right);
        }

        public int getKLevelCount(TreeNode root,int k)
        {
            if(root==null)  return 0;
            if(k==1) return 1;
            return getKLevelCount(root.left,k-1)+getKLevelCount(root.right,k-1);
        }

        public int getHeight(TreeNode root)
        {
            return root==null?0:Math.max(getHeight(root.left),getHeight(root.right))+1;
        }
        public TreeNode findVal(TreeNode root,char val)
        {
            if(null==root)
                return null;
            if(val==root.val)
                return root;
            TreeNode ret = findVal(root.left,val);
            if(ret==null)
                ret = findVal(root.right,val);
            return ret;
        }

        public void levelOrder(TreeNode root)
        {
            if(null==root)  return ;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(queue.isEmpty())
            {
                TreeNode cur = queue.poll();
                System.out.println(cur.val);
                if(null!=cur.left)
                    queue.offer(cur.left);
                if(null!=cur.right)
                    queue.offer(cur.right);
            }
        }
    }
}
