package binaryTree;

import java.util.*;

/*
        1
      3   5
    7  11  17

1 3 7 -1 -1 11 -1 -1 5 17 -1 -1 -1
 */
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    Node() {
        this.data = 0;
        left = null;
        right = null;
    }
}

public class Binary {
    Node root;

    Binary() {
        root = null;
    }

    Node buildTree(Node root) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data: ");
        int data = sc.nextInt();
        root = new Node(data);

        if (data == -1) {
            return null;
        }
        System.out.println("Enter data to insert in left " + root.data);
        root.left = buildTree(root.left);
        System.out.println("enter data to insert at right " + root.data);
        root.right = buildTree(root.right);

        return root;

    }

    //printing tree in level order traversal
//    https://leetcode.com/problems/binary-tree-level-order-traversal/
    void levelOrderTraversal(Node root) {


        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp == null) {
                // Current level traversal completed
                // Print a new line
                System.out.println();
                if (!q.isEmpty()) {
                    // Queue has more elements to process
                    q.add(null);
                }
            } else {
                System.out.print(temp.data + "   ");
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }


        }
    }

    //building tree from level order traversal
    void buildTreeFromLevelOrderTraversal(Node root) {
        Scanner sc = new Scanner(System.in);
        Queue<Node> q = new LinkedList<>();
        System.out.print("Enter data for root:");
        int data = sc.nextInt();
        root = new Node(data);
        q.add(root);

        while (!q.isEmpty()) {

            Node temp = q.peek();
            q.remove();

            System.out.print("Enter left node for " + temp.data + ": ");
            int leftdata = sc.nextInt();

            if (leftdata != -1) {
                temp.left = new Node(leftdata);
                q.add(temp.left);
            }

            System.out.print("enter right node for " + temp.data + ": ");
            int rightdata = sc.nextInt();
            if (rightdata != -1) {
                temp.right = new Node(rightdata);
                q.add(temp.right);
            }

        }
    }

    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }


    //line which is below this and returning the Node type is only for the level order traversal when we will create tree
    //using buildTreeFromLevelOrderTraversal this method because there is issues while printing the level wise traversal
    Node buildTreeFromLevelOrderTraversalforThis(Node root) {

        Scanner sc = new Scanner(System.in);
        Queue<Node> q = new LinkedList<>();
        System.out.print("Enter data for root:");
        int data = sc.nextInt();
        root = new Node(data);
        q.add(root);

        while (!q.isEmpty()) {

            Node temp = q.peek();
            q.remove();

            System.out.print("Enter left node for " + temp.data + ": ");
            int leftdata = sc.nextInt();

            if (leftdata != -1) {
                temp.left = new Node(leftdata);
                q.add(temp.left);
            }

            System.out.print("enter right node for " + temp.data + ": ");
            int rightdata = sc.nextInt();
            if (rightdata != -1) {
                temp.right = new Node(rightdata);
                q.add(temp.right);
            }

        }
        return root;
    }

    int CountLeafNode(Node root) {
        return UsingInorder(root);
    }

    int UsingInorder(Node root) {
        int count = 0;
        if (root == null) {
            return 0;
        }

        count += UsingInorder(root.left);
        if (root.left == null && root.right == null) {
            count++;
        }
        count += UsingInorder(root.right);
        return count;

    }

//    https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/

    public int maxDepth(Node root) {
        /*
        Pretty simple forward we just called a recursion for the left subtree which will return the
        height of the left subtree and another recursion will return height of right subtree then we
        will simply return the maximum from both of them + 1 and for every left node that recursion call
        will go left and right and will find null so that leaf node will find max(0 , 0 ) + 1 to their
        parent node and that node will also do same for their recursive call

*/
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }


    //    https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(Node root) {
         /*
        Pretty simple forward we just called a recursion for the left subtree which will return the
        height of the left subtree and another recursion will return height of right subtree then we
        will simply return the minimum from both of them + 1 if and only if left and right both will not be
        equal to 0(Zero) and for every left node that recursion call will go left and right and will find null              so that leaf node will find min(0 , 0 ) + 1 to their
        parent node and that node will also do same for their recursive call and
        if any subtree will be equal to zero then we will return the maximum depth because if left node
        will be null then that is not any subtree, so we can't say that will be depth of that tree because
        there is no node


        */
        int count = 0;
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left > 0 && right > 0) {
            return Math.min(left, right) + 1;
        }

        return Math.max(left, right) + 1;
    }

//    https://leetcode.com/problems/diameter-of-binary-tree/

    //https://www.geeksforgeeks.org/problems/diameter-of-binary-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    int diameter(Node root) {
//        // Your code here
//        if(root == null){
//            return 0;
//        }
//        int op1 = diameter(root.left);
//        int op2 = diameter(root.right);
//        //this is giving TLE because on every recursive call I'm also calling height
//        //and height is of O(n) time complexity, it become o(N^2) time complexity
//        //so this is not a good idea
//        int op3 = maxDepth(root.left) + maxDepth(root.right) + 1;
//
//        return Math.max(op1 ,Math.max(op2 , op3));


        //this will be the optimized one
        /*
        Now its time complexity is O(N) and in this I'm returning the

        This method will take only O(N) time complexity because in this first we declare a variable of pair
        class types in which I'm storing the length as well as the height of the binary node in the previous
        method we were doing in that time complexity was the o(N^2) after that to improve that
        I'm doing by this method in this first variable which is of pair class types represents the diameter and
        second variable represents the height of the binary tree

         */
        return diameterFast(root).first;
    }

    public Pair diameterFast(Node root) {
        // base case
        if (root == null) {
            return new Pair(0, 0);
        }

        Pair left = diameterFast(root.left);
        Pair right = diameterFast(root.right);

        int op1 = left.first;
        int op2 = right.first;
        int op3 = left.second + right.second + 1;

        Pair ans = new Pair(Math.max(op1, Math.max(op2, op3)), Math.max(left.second, right.second) + 1);

        return ans;
    }

    class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }


//    https://www.geeksforgeeks.org/problems/check-for-balanced-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
//    https://leetcode.com/problems/balanced-binary-tree/

    boolean isBalanced(Node root) {
        /*
        In this I'm just checking at evry step that is my node is balace and that node is saying let me ask my
        child node and if the node become null we returned true if and did same for the right binary tree
        and if the left and right both are true and we find the difference of height of the binary left and
        right binary tree is less than equal to 1 then returned true otherwise false
*/
        // Your code here
        //base case
        if (root == null) {
            return true;
        }
        boolean lt = isBalanced(root.left);
        boolean rt = isBalanced(root.right);

        boolean diif = Math.abs(height(root.left) - height(root.right)) <= 1;

        if (lt && rt && diif) {
            return true;
        }
        return false;

    }

    public int height(Node root) {
        int count = 0;
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right);
    }

    //    https://leetcode.com/problems/same-tree/
//    https://www.geeksforgeeks.org/problems/determine-if-two-trees-are-identical/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    public boolean isSameTree(Node p, Node q) {
/*
        Pretty simple and clear just checked base condition if the root node or any other node of both
        tree is null then return false and if any one of node is null is others is not null then return false
        and after that we call a recursive call for the left subtree and again we call other recursive call for
        the right subtree and after that we compare the data of both root and stored into a variable of boolean type
        and finally checked if all these three boolean variables are true or not if not then return false otherwise return
        true
 */
        //base case
        if (p == null && q == null) {
            return true;
        }

        if (p == null && q != null) {
            return false;
        }

        if (p != null && q == null) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        boolean data = p.data == q.data;
        if (left && right && data) {
            return true;
        }
        return false;

    }

    //    https://leetcode.com/problems/symmetric-tree/
//    https://www.geeksforgeeks.org/problems/symmetric-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    public static boolean isSymmetric(Node root) {
        // add your code here;
        return false;
    }

    //    https://www.geeksforgeeks.org/problems/children-sum-parent/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    public static int isSumProperty(Node root) {
        // add your code here
//        return (int) (Math.pow(2 ,3 ) -1);
        return 0;

    }


    //    https://leetcode.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal(Node root) {
        /*
        This is the first way to do so in which we declare list inside the function we can do same after
        declaring the list outside the function and keep adding elements into the list and finally return
        the list here code's present is of

        //this is the second way where i put the list above the function and keep adding the element into them
        */
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            //no need to add elements into the array just return the simple previous list
            return list;

        }
        list.add(root.data);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }

    //    https://leetcode.com/problems/binary-tree-postorder-traversal/
    //done
    public List<Integer> postorderTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            //no need to add nany new elements into the list just return the list as that contains till
            //previous iteration
            return list;
        }
        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.data);
        return list;
    }

//    https://www.geeksforgeeks.org/problems/construct-tree-1/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
//    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//    submitted on leetcode ->

    public int findPosition(int[] inorder, int startindex, int ele, int n) {
        for (int i = startindex; i < n; i++) {
            if (inorder[i] == ele) {
                return i;
            }
        }
        return -1;
    }

    public Node solve(int[] inorder, int[] preorder, int[] index, int inOrderStart, int inOrderEnd, int n) {
        if (index[0] >= n || inOrderStart > inOrderEnd) {
            return null;
        }
        int ele = preorder[index[0]];
        Node root = new Node(ele);
        int position = findPosition(inorder, inOrderStart, ele, n);
        index[0]++;

        root.left = solve(inorder, preorder, index, inOrderStart, position - 1, n);
        root.right = solve(inorder, preorder, index, position + 1, inOrderEnd, n);

        return root;
    }

    //I have commented this just because I can create tree for
//    public Node buildTree(int[] inorder, int[] preorder, int n) {
//        int[] preOrderIndex = {0};
//        return solve(inorder, preorder, preOrderIndex, 0, n - 1, n);
//    }


//    https://www.geeksforgeeks.org/problems/tree-from-postorder-and-inorder/1
//    https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/


    public int findPosition2(int[] in, int ele, int n) {
        for (int i = 0; i < n; i++) {
            if (in[i] == ele) {
                return i;
            }
        }
        return -1;
    }

    public Node solveSecond(int[] inorder, int[] post, int[] index, int inOrderStart, int inOrderEnd, int n) {
        //base condition will change partially
        if (index[0] < 0 || inOrderStart > inOrderEnd) {
            return null;
        }
        //creation of a new node
        int ele = post[index[0]];
        Node root = new Node(ele);

        //find index of element in Inorder array
        int position = findPosition2(inorder, ele, n);
        index[0]--;

//        recursive calls
        root.right = solveSecond(inorder, post, index, position + 1, inOrderEnd, n);
        root.left = solveSecond(inorder, post, index, inOrderStart, position - 1, n);


        return root;
    }

    Node buildTree(int in[], int post[], int n) {
        // Your code here
        int[] postOrderIndex = {n - 1};
//
//        int postOrderIndex =

        Node ans = solveSecond(in, post, postOrderIndex, 0, n - 1, n);

        return ans;

    }

//gfg potd
//    https://www.geeksforgeeks.org/problems/vertical-sum/1

    ArrayList<Integer> al;

    public ArrayList<Integer> verticalSum(Node root) {
        // add your code here
        al = new ArrayList<>();
        int hd = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        vsum(root, hd, map);
        al.addAll(map.values());

        return al;
    }

    public void vsum(Node root, int hd, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        vsum(root.left, hd - 1, map);
        map.put(hd, map.getOrDefault(hd, 0) + root.data);

        vsum(root.right, hd + 1, map);

    }


    //    https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    //leetcode 987 -> hard do it by hard try
    List<List<Integer>> list;

    public List<List<Integer>> verticalTraversal(Node root) {
        return list;
    }





    public static void main(String[] args) {
        Node root = null;


        Binary bt = new Binary();

//        bt.inorder(root);
//        bt.levelOrderTraversal(root);

        //creating a tree;
//        root = bt.buildTree(root);
//        bt.inorder(root);
//        System.out.println();
//        bt.levelOrderTraversal(root);

        //this prints the tree in level order -> level by level
//        bt.levelOrderTraversal(root);

//      building tree from level order traversal
//        root = bt.buildTreeFromLevelOrderTraversalforThis(root);
//        bt.levelOrderTraversal(root);

//        System.out.println(bt.CountLeafNode(root));;

//        root = bt.buildTree(root);
//        System.out.println(bt.diameter(root));

        int[] inorder = {4, 8, 2, 5, 1, 6, 3, 7};
        int[] postorder = {8, 4, 5, 2, 6, 7, 3, 1};


        Node r = bt.buildTree(inorder, postorder, inorder.length);
        System.out.println(bt.preorderTraversal(r));


    }


}
/*
7
7 3 11 1 17 3 18
1 3 7 11 3 17 18
 */
