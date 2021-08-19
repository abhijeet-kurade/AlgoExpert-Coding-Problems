import java.util.*;

public class TreeAlgo {

    public static void main(String[] args) {
        TreeAlgo treeAlgo = new TreeAlgo();

        /*BinarySearchTree bst = new BinarySearchTree();
        bst.insert(16);
        bst.insert(11);
        bst.insert(46);
        bst.insert(35);
        bst.insert(50);
        bst.insert(5);
        bst.insert(9);
        bst.insert(1);

        System.out.println(bst.contains(1));
        bst.remove(1);
        System.out.println(bst.contains(1));*/

        BTree btree = new BTree();

        BTreeNode root = btree.createBSTree11();
        //btree.inOrderBTreeTraversal(root);
        System.out.println();
        //btree.preOrderBTreeTraversal(root);
        btree.findNodesDistanceK(root, 6, 4);





    }


    public void printTree(HashMap<String, ArrayList<String>> relation_tree, String root){

        Stack<String> stack = new Stack<>();
        Stack<Integer> depth_stack = new Stack<>();
        stack.push(root);
        depth_stack.push(0);
        while(stack.size() != 0){
            String node = stack.pop();
            int depth = depth_stack.pop();
            for(int i=0; i<depth; i++)
                System.out.print("   ");
            System.out.println(node);
            if(relation_tree.get(node) == null) continue;
            int no_of_children = relation_tree.get(node).size();
            for(int i=no_of_children-1; i>=0; i--){
                stack.push(relation_tree.get(node).get(i));
                depth_stack.push(depth+1);
            }
        }


    }
    public void treePrint(){
        ArrayList<Relation> list = new ArrayList<>();

        list.add(new Relation("Animal", "Mammal"));
        list.add(new Relation("Animal", "Bird"));
        list.add(new Relation("Lieform", "Animal"));
        list.add(new Relation("Cat", "Lion"));
        list.add(new Relation("Mammal", "Cat"));
        list.add(new Relation("Animal", "Fish"));

        HashMap<String, ArrayList<String>> relation_tree = new HashMap<>();
        HashSet<String> parent = new HashSet<>();
        for(Relation r : list) parent.add(r.parent);


        for(Relation r : list){
            if(parent.contains(r.child))
                parent.remove(r.child);
            if(relation_tree.get(r.parent) != null){
                ArrayList<String> children = relation_tree.get(r.parent);
                children.add(r.child);
                relation_tree.put(r.parent, children);
            }
            else{
                ArrayList<String> children = new ArrayList<>();
                children.add(r.child);
                relation_tree.put(r.parent, children);
            }
        }
        System.out.println(relation_tree);
        System.out.println(parent);
        String p = parent.stream().toList().get(0);
        printTree(relation_tree, p);



    }


}


class BinarySearchTree{
    BTreeNode root;

    public void insert(int num){
        if(this.root == null){
            this.root = new BTreeNode(num);
            return;
        }
        /*BTreeNode new_node = new BTreeNode(num);
        BTreeNode node = this.root;
        BTreeNode prev = null;
        while(node != null){
            prev = node;
            if(num > node.val)
                node = node.right;
            else if(num < node.val)
                node = node.left;
            else
                return;
        }
        if(prev.val < num)
            prev.left = new_node;
        else
            prev.right = new_node;
        return;*/
        BTreeNode node = this.root;
        while(true){
            if(num < node.val){
                // go left
                if(node.left == null){
                    node.left = new BTreeNode(num);
                    return;
                }
                node = node.left;
            }
            else{
                // go right
                if(node.right == null){
                    node.right = new BTreeNode(num);
                    return;
                }
                node = node.right;
            }
        }
    }

    public void remove(int num){

        if(root == null)
            return;
        /*BTreeNode node = contains(num);
        if(node == null) return;

        if(node.right == null) {
            System.out.println("Removing "+num);
            node = node.left;
        }
        else{
            BTreeNode successor = removeSuccessor(node);
            System.out.println("Successor is "+successor.val);
            node.val = successor.val;
        }*/

        if(!contains(num)) return;
        BTreeNode node = this.root;
        BTreeNode parent = null;
        while(node.val != num){
            parent = node;
            if(node.val > num)
                node = node.left;
            else
                node = node.right;
        }
        if(node.right == null) {
            System.out.println("Removing "+num);
            if(parent.left == node) parent.left = node.left;
            else parent.right = node.left;
        }
        else{
            BTreeNode successor = removeSuccessor(node);
            if(parent.left == node) parent.left = node.left;
            else parent.right = node.left;
        }

        return;
    }

    public BTreeNode removeSuccessor(BTreeNode root){
        BTreeNode node = root.right;
        BTreeNode prev = null;
        while(node.left != null){
            prev = node;
            node = node.left;
        }
        if(prev != null) prev.left = node.right;
        else root.right = null;
        return node;
    }

    public boolean contains(int num){
        if(this.root == null)
            return false;
        BTreeNode node = this.root;
        while(node != null){
            if(node.val == num) return true;
            if(node.val > num) node = node.left;
            else  node = node.right;
        }
        return false;
    }
}

class Relation {
    String parent;
    String child;

    public Relation(String parent, String child){
        this.parent = parent;
        this.child = child;
    }
}

class TreeNode{
    int val;
    List<TreeNode> children;
    public TreeNode(int val){
        this.val = val;
        children = new ArrayList<>();
    }
}

class TreeTraversals{

    public TreeNode buildTree(){

        TreeNode root = new TreeNode(1);
        root.children.add(new TreeNode(2));
        root.children.get(0).children.add(new TreeNode(4));
        root.children.get(0).children.add(new TreeNode(5));
        root.children.get(0).children.add(new TreeNode(6));
        root.children.add(new TreeNode(3));
        root.children.get(1).children.add(new TreeNode(7));
        root.children.get(1).children.get(0).children.add(new TreeNode(8));
        root.children.get(1).children.get(0).children.add(new TreeNode(9));
        root.children.get(1).children.get(0).children.add(new TreeNode(10));

        return root;

    }

    public void inorder(TreeNode root){

    }
    // Implemented
    public void preorder(TreeNode root){
        if(root == null) return;
        System.out.print(root.val + " ");
        for(TreeNode node : root.children){
            preorder(node);
        }

    }
    // Implemented
    public void preorderIterative(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            int len = node.children.size() - 1;
            for(int i= len; i>=0; i--){
                stack.push(node.children.get(i));
            }
        }

    }
    // Implemented
    public void postorder(TreeNode root){
        if(root == null) return;
        for(TreeNode node : root.children){
            postorder(node);
        }
        System.out.print(root.val + " ");
    }
    // Implemented
    public void postorderIterative(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            output.push(node);
            for(TreeNode i : node.children)
                stack.push(i);
        }
        while(!output.isEmpty())
            System.out.print(output.pop().val +" ");

    }
    // Implemented
    public void breathFirstSearch(TreeNode root){
        //  BFS traversal is the same as LevelOrder Traversal
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() != 0){
            int nextLevel = queue.size();
            while ( nextLevel-- != 0){
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                for(TreeNode nd : node.children)
                    queue.add(nd);
            }
        }
    }

    public void depthFirstSearch(TreeNode root){

    }
    // Implemented
    public void levelOrder(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() != 0){
            int nextLevel = queue.size();
            while ( nextLevel-- != 0){
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                for(TreeNode nd : node.children)
                    queue.add(nd);
            }
        }
    }

}

class BTreeNode{
    public int val;
    public BTreeNode left;
    public BTreeNode right;

    public BTreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
    public BTreeNode(int val, BTreeNode left, BTreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

class BTree{

    public BTreeNode createBSTree(){
        BTreeNode root = new BTreeNode(45);
        root.left = new BTreeNode(25);
        root.left.left = new BTreeNode(20);
        root.left.left.left = new BTreeNode(9);
        root.left.left.left.left = new BTreeNode(4);
        root.left.left.right = new BTreeNode(23);
        root.left.right = new BTreeNode(31);
        root.right = new BTreeNode(50);
        return root;
    }

    public BTreeNode createBSTree11(){
        BTreeNode root = new BTreeNode(1);


        root.left = new BTreeNode(2);
        root.right = new BTreeNode(3);

        root.left.left = new BTreeNode(4);
        root.left.right = new BTreeNode(5);

        root.right.left = new BTreeNode(6);
        root.right.right = new BTreeNode(7);


        root.right.left.right = new BTreeNode(8);

        root.right.left.right.left = new BTreeNode(9);
        root.right.left.right.right = new BTreeNode(10);

        root.right.left.right.left.left = new BTreeNode(11);
        root.right.left.right.right.right = new BTreeNode(12);

        root.right.left.right.left.left.left = new BTreeNode(13);
        root.right.left.right.right.right.left = new BTreeNode(14);


        root.right.right.left = new BTreeNode(16);
        root.right.right.right = new BTreeNode(17);

        root.right.right.left.left = new BTreeNode(18);
        root.right.right.right.left = new BTreeNode(19);

        return root;
    }

    public boolean fillSides(BTreeNode node, int target, HashMap<Integer, Character> sideOfTarget, int[] dist, int depth){
        if(node == null) return false;

        boolean left = fillSides(node.left, target, sideOfTarget, dist, depth+1);
        boolean right = fillSides(node.right, target, sideOfTarget, dist, depth+1);
        if(node.val == target){
            sideOfTarget.put(node.val, 'N');
            dist[0] = depth;
            return true;
        }
        if(left || right) {
            char temp = left ? 'L' : 'R';
            sideOfTarget.put(node.val, temp);
            return true;
        }
        else {
            sideOfTarget.put(node.val, 'N');
            return false;
        }
    }
    public void getKDistanceNodes(BTreeNode node, int target, int distance, HashMap<Integer, Character> sideOfTarget, ArrayList<Integer> kDistNodes){
        if(node == null) return;

        char side = sideOfTarget.get(node.val);
        if(distance == target) kDistNodes.add(node.val);
        if(side == 'R'){
            getKDistanceNodes(node.left, target, distance+1, sideOfTarget, kDistNodes);
            getKDistanceNodes(node.right, target, distance-1, sideOfTarget, kDistNodes);
        }
        else if(side == 'L'){
            getKDistanceNodes(node.left, target, distance-1, sideOfTarget, kDistNodes);
            getKDistanceNodes(node.right, target, distance+1, sideOfTarget, kDistNodes);
        }
        else{
            getKDistanceNodes(node.left, target, distance+1, sideOfTarget, kDistNodes);
            getKDistanceNodes(node.right, target, distance+1, sideOfTarget, kDistNodes);
        }
        return;
    }
    public ArrayList<Integer> findNodesDistanceK(BTreeNode root, int target, int k){
        ArrayList<Integer> kDistNodes = new ArrayList<>();
        HashMap<Integer, Character> sideOfTarget = new HashMap<>();
        int[] dist = new int[1];
        fillSides(root, target, sideOfTarget, dist, 0);
        System.out.println(sideOfTarget );
        System.out.println(dist[0] + " " + target + " " + k) ;
        getKDistanceNodes(root, k, dist[0], sideOfTarget, kDistNodes);
        System.out.println(kDistNodes);

        return new ArrayList<>();
    }


    public void preOrderBTreeTraversal(BTreeNode node){
        if(node == null) return;
        System.out.print(node.val + " ");
        if(node.left != null) preOrderBTreeTraversal(node.left);
        if(node.right != null) preOrderBTreeTraversal(node.right);
    }
    public void inOrderBTreeTraversal(BTreeNode node){
        if(node == null) return;

        if(node.left != null) inOrderBTreeTraversal(node.left);
        System.out.print(node.val + " ");
        if(node.right != null) inOrderBTreeTraversal(node.right);
    }

    public void postOrderBTreeTraversal(BTreeNode node){
        if(node == null) return;

        if(node.left != null) postOrderBTreeTraversal(node.left);
        if(node.right != null) postOrderBTreeTraversal(node.right);
        System.out.print(node.val + " ");
    }


    public boolean validateBSTree(BTreeNode root, int min, int max){
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return validateBSTree(root.left, min, root.val) && validateBSTree(root.right, root.val, max);
    }

    public BTreeNode getPredecessor(BTreeNode root, BTreeNode node, BTreeNode predecessor){
        if(root == null) return predecessor;
        if(node.left != null){
            predecessor = node.left;
            while(predecessor.right != null)
                predecessor = predecessor.right;
        }
        if(node.left == null){
            if(root.val == node.val) return predecessor;
            if(root.val < node.val) {
                predecessor = root;
                return getPredecessor(root.right, node, predecessor);
            }
            if(root.val > node.val){
                return getPredecessor(root.left, node, predecessor);
            }
        }
        return predecessor;
    }

    public BTreeNode getSuccessor(BTreeNode root, BTreeNode node, BTreeNode successor){
        if(root== null) return successor;

        if(node.right != null){
            successor = node.right;
            while(successor.left != null)
                successor = successor.left;
        }
        if(node.right == null){
            if(root.val == node.val) return  successor;
            if(root.val < node.val) return getSuccessor(root.right, node, successor);
            if(root.val > node.val){
                successor = root;
                return getSuccessor(root.left, node, successor);
            }
        }


        return successor;
    }

    public BTreeNode getNodeByIntValue(BTreeNode root, int val){

        return root;
    }

    //BTreeNode start = null;
    //int chain = 0;
    public BTreeNode longestConsecutiveSequence(BTreeNode root){
        BTreeNode[] starts = new BTreeNode[]{root};
        int[] chains = new int[]{0};
        //start = root;
        consecutiveSequence(root, root, 1, root.val, starts, chains);
        return starts[0];
    }

    public void consecutiveSequence(BTreeNode node, BTreeNode curr_start, int curr_chain, int val, BTreeNode[] starts, int[] chains){
        if(node.val == val+1){
            curr_chain++;
            if(curr_chain > chains[0] /*chain*/){
                starts[0] = curr_start;
                chains[0] = curr_chain;
                //chain = curr_chain;
                //start = curr_start;
            }
        }
        else {
            curr_start = node;
            curr_chain = 1;
        }

        if(node.left != null) consecutiveSequence(node.left,curr_start, curr_chain, node.val, starts, chains);
        if(node.right != null) consecutiveSequence(node.right,curr_start, curr_chain, node.val, starts, chains);


    }

    public BTreeNode createTree(){
        BTreeNode root = new BTreeNode(10);
        root.left = new BTreeNode(4);
        root.right = new BTreeNode(50);
        root.left.left = new BTreeNode(51);
        root.left.left.left = new BTreeNode(9);
        root.left.left.left.left = new BTreeNode(94);
        root.left.right = new BTreeNode(51);
        //root.left.right.left = new BTreeNode(151);
        //root.left.right.left.left = new BTreeNode(511);
        return root;
    }

    public BTreeNode createTree1(){
        BTreeNode root = new BTreeNode(1);
        root.left = new BTreeNode(4);
        root.left.left = new BTreeNode(45);
        root.right = new BTreeNode(5);
        root.left.right = new BTreeNode(6);
        root.left.right.right = new BTreeNode(9);
        root.left.right.right.right = new BTreeNode(94);
        root.right.left = new BTreeNode(7);
        return root;
    }

    public int[] diameter(BTreeNode root){
        int[] pair = new int[2];

        if(root == null){
            pair[0] = 0; //Depth
            pair[1] = 0; // Diameter
            return pair;
         }
        int[] left = root.left !=  null ? diameter(root.left) : pair;
        int[] right = root.right !=  null ? diameter(root.right) :  pair;

        pair[0] = Math.max(left[0], right[0]) + 1;
        pair[1] = Math.max(left[0]+right[0], Math.max(left[1], right[1]));

        return pair;
    }

    public BTreeNode createTreeFromArray(int[] a){
        BTreeNode root = new BTreeNode(a[0]);

        return root;
    }

    public void inorderBTreeTraversalIterative(BTreeNode root){
        BTreeNode node = root;
        Stack<BTreeNode> stack = new Stack<>();
        //int count=0;
        while( node != null || stack.size() > 0 ){
            while(node != null){
                stack.push(node);
                //count++;
                node = node.left;
            }
            node = stack.pop();
            System.out.println(node.val);
            node =node.right;
        }
    }

    public BTreeNode invertBTree(BTreeNode root){

        if(root == null)
            return root;
        BTreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertBTree(root.left);
        invertBTree(root.right);

        return root;
    }


    public BTreeNode constructMaximumBTree(int[] nums){
        //https://leetcode.com/problems/maximum-binary-tree/
        return constructMaximumBTree1(nums, 0, nums.length-1);
    }

    public BTreeNode constructMaximumBTree1(int[] nums, int start, int end){
        if(start > end )
            return null;

        if(start == end )
            return new BTreeNode(nums[start]);

        int max = start;

        for(int i = start+1; i<=end; i++){
            if(nums[start] < nums[i]) start = i;
        }

        return new BTreeNode(nums[max], constructMaximumBTree1(nums, start, max - 1),
                constructMaximumBTree1(nums, max + 1, end));

        /*BTreeNode node = new BTreeNode(nums[max]);
        System.out.print(nums[max]+ " ");
        if(max == start){
            node.left = null;
            node.right = constructMaximumBTree1(nums,start+1, end);
        }
        else if(max == end){
            node.left = constructMaximumBTree1(nums,start, end-1);
            node.right = null;
        }
        else{
            node.left = constructMaximumBTree1(nums,start, max-1);
            node.right = constructMaximumBTree1(nums,max+1, end);
        }
        return node;*/
    }


    public void levelOrderBTreeTraversal(BTreeNode root){

        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int len = queue.size();

            for(int i=0; i<len; i++){
                BTreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

        }
    }

    public double[] averageOfLevels(BTreeNode root){
        if(root== null) return null;

        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Double> depth_average = new ArrayList<>();

        while(!queue.isEmpty()){
            int depth_nodes = queue.size();

            int depth_sum =0;
            for(int i=0; i<depth_nodes; i++){
                BTreeNode node = queue.poll();
                depth_sum += node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            double depth_avg = (double)depth_sum / depth_nodes;
            depth_average.add(depth_avg);
        }
        System.out.println(depth_average);

        double[] output = new double[depth_average.size()];


        int index =0;
        for(Double i : depth_average)
            output[index++] = i;

        return output;
    }

    public ArrayList<Integer> topViewOfBTree(BTreeNode root){
        ArrayList<Integer> view = new ArrayList<>();

        Queue<Pair> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();


        queue.add(new Pair(root, 0));
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int len = queue.size();
            for(int i=0; i<len; i++){
                Pair node = queue.poll();
                if(min > node.vertical || max < node.vertical){
                    //System.out.println(node.node.val + " "+node.vertical);
                    if(min > node.vertical) {
                        list.add(0,node.node.val);
                        min = node.vertical;
                    }
                    else  {
                        list.add(node.node.val);
                        max = node.vertical;
                    }
                }
                if(node.node.left != null) queue.add(new Pair(node.node.left, node.vertical-1));
                if(node.node.right != null) queue.add(new Pair(node.node.right, node.vertical+1));

            }
        }
        System.out.println(list);

        return view;
    }

    public boolean heightBalancedBinaryTree(BTreeNode root) {
        int[] val = getHeight(root);
        boolean return_val = val[0] == 0 ? true : false;
        return return_val;
    }
    public int[] getHeight(BTreeNode node){

        if(node == null) return new int[]{0, 0};

        int[] left = getHeight(node.left) ;
        int[] right = getHeight(node.right);

        if(left[0] == 1 || right[0] == 1) return new int[]{1, 0};

        if(Math.abs(left[1] - right[1]) > 1)  return new int[]{1, 0};

        return new int[]{ 0, Math.max(left[1], right[1]) +1 };

    }



}
class Pair{
    BTreeNode node;
    Integer vertical;

    public Pair(BTreeNode node, Integer vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}


