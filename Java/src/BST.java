import com.sun.source.tree.Tree;

public class BST {
    static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root;
    public void insert(int val)
    {
        TreeNode node = new TreeNode(val);
        if(null==root) {
            root = node;
            return;
        }
        TreeNode parent = null;
        TreeNode cur = root;
        while(cur!=null)
        {
            if(cur.val<val) {
                parent = cur;
                cur = cur.right;
            }
            else if(cur.val==val) {
                return;
            }
            else
            {
                parent = cur;
                cur = cur.left;
            }
        }
        if(parent.val<val) {
            parent.right = node;
        }
        else {
            parent.left = node;
        }
    }
    public boolean isEmpty(){return root==null;}
    public void remove(int key)
    {
        if(isEmpty()) {
            return;
        }
        TreeNode parent = null;
        TreeNode cur = root;
        while(cur!=null)
        {
            if(cur.val<key)
            {
                parent = cur;
                cur = cur.right;
            }
            else if(cur.val == key) {
                //封装函数
                removeNode(parent,cur);
            }
            else
            {
                parent = cur;
                cur = cur.left;
            }
        }
        //删除对象不存在
        return ;

    }
    private void removeNode(TreeNode parent,TreeNode cur)
    {
        //分类讨论
        //根节点是特殊的节点,此时参数的cur为root,parent为null.
        //1. cur为叶子节点或者单分支节点.
        //2. cur的左右子树存在(不为空).--双分支节点.
        //---------------

        //故2*2分四种情况讨论
        //单分支与叶子节点是同一种处理方式
        if(cur.left==null)
        {
            if(cur==root) {
                root = cur.right;
            }
            else{
                //cur!=root
                parent.right = cur.right;
            }
        }
        else if(cur.right==null)
        {
            if(cur==root) {
                root = cur.left;
            }
            else{
                //cur!=root
                parent.left = cur.left;
            }
        }
        else //双分支节点,cur.left!=null&&cur.right!=null
        {
            //根节点与一般的双分支处理方法一样---(值)替换删除法
            //原理:----看解释

//            //左子树找最大值.
            //......

            //右子树找最小值.
           TreeNode targetParent = cur;
            TreeNode target = cur.right;
            while(target.left!=null)
            {
                targetParent = target;
                target = target.left;
            }
            cur.val = target.val;
            if(targetParent.left==target)
            {
                targetParent.left = target.right;
            }
            else {
                cur.right = cur.right.right;
            }
        }
    }
    public TreeNode search(int val)
    {
        return search(this.root,val);
    }
    public TreeNode searchByLoop(int val)
    {
        if(null==root)
            return null;
        TreeNode cur = root;
        while(cur!=null)
        {
            if(cur.val<val)
                cur = cur.right;
            else if(cur.val==val)
                return cur;
            else
                cur = cur.left;
        }
        return null;
    }
    public TreeNode search(TreeNode root,int val)
    {
        if(root==null)
        {
            return null;
        }
        if(root.val==val)
            return root;
        else if(root.val<val)
        {
            return search(root.right,val);
        }
        else
            return search(root.left,val);
    }
    public static final BST createBST()
    {
        BST bst = new BST();
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        //contact
        node5.left = node3;
        node3.left = node1;
        node1.left = node0;
        node1.right=node2;
        node3.right=node4;
        node5.right=node7;
        node7.right = node8;
        node8.right = node9;
        node7.left = node6;
        //
        bst.root = node5;
        return bst;
    }
}
