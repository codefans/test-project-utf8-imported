package com.codefans.reusablecode.datastructure.link;

import com.codefans.reusablecode.common.BinaryTreeFactory;
import com.codefans.reusablecode.common.TreeFactory;
import com.codefans.reusablecode.datastructure.binarytree.BinaryTreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: caishengzhi
 * @date: 2017-09-28 10:38
 **/
public class BinaryTreeNodeTest {

    BinaryTreeNode binaryTreeNode = null;

    @Before
    public void before() {
        TreeFactory<BinaryTreeNode> treeFactory = new BinaryTreeFactory();
        this.binaryTreeNode = treeFactory.create("binary");
    }

    /**
     * 10, 9, 4, 11, 3, 5,
     */
    @Test
    public void iterateByLevel() {

//        this.iterateLeft(binaryTreeNode);
//        this.iterateRight(binaryTreeNode);
        this.iterateByLevel(binaryTreeNode);
//        this.iterateByLevelRecursion();

    }

    /**
     * 10, 4, 9, 5, 3, 11,
     *
     */
    @Test
    public void invertBinaryTree() {
        this.invertBinaryTree(binaryTreeNode);
        this.iterateByLevel(binaryTreeNode);
    }

    /**
     * 翻转二叉树：即左右子数对换
     * @param root
     * @return
     */
    public BinaryTreeNode invertBinaryTree(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        root.setLeft(invertBinaryTree(root.getLeft()));
        root.setRight(invertBinaryTree(root.getRight()));

        BinaryTreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);

        return root;
    }

    public void iterateLeft(BinaryTreeNode binaryTreeNode) {
        BinaryTreeNode node = binaryTreeNode;
        BinaryTreeNode left = null;
        BinaryTreeNode right = null;
        while(node != null || left != null || right != null) {
            System.out.println(node.getValue());
            node = node.getLeft();
            left = node.getLeft();
            right = node.getRight();
        }
    }

    public void iterateRight(BinaryTreeNode binaryTreeNode) {
        BinaryTreeNode node = binaryTreeNode;
        while(node != null) {
            System.out.println(node.getValue());
            node = node.getRight();
        }
    }

    /**
     *      10
     *     /  \
     *    9    4
     *   / \  /
     *  11 3 5
     * @param node
     */
    public void iterateByLevel(BinaryTreeNode node) {

        LinkedBlockingQueue<BinaryTreeNode> queue = new LinkedBlockingQueue<BinaryTreeNode>();

        System.out.print(node.getValue() + ", ");
        BinaryTreeNode leftNode = node.getLeft();
        if(leftNode != null) {
            queue.offer(leftNode);
        }
        BinaryTreeNode rightNode = node.getRight();
        if(rightNode != null) {
            queue.offer(rightNode);
        }

        while(!queue.isEmpty()) {
            BinaryTreeNode btNode = queue.poll();
            System.out.print(btNode.getValue() + ", ");
            leftNode = btNode.getLeft();
            if(leftNode != null) {
                queue.offer(leftNode);
            }
            rightNode = btNode.getRight();
            if(rightNode != null) {
                queue.offer(rightNode);
            }
        }




    }

    public void iterateByLevelRecursion() {
        this.iterateByLevelRecursion(binaryTreeNode, new LinkedBlockingQueue<BinaryTreeNode>());
    }

    public void iterateByLevelRecursion(BinaryTreeNode node, LinkedBlockingQueue<BinaryTreeNode> queue) {
        if(node == null && queue.isEmpty()) {
            return;
        }
        System.out.print(node.getValue() + ", ");
        BinaryTreeNode leftNode = node.getLeft();
        if(leftNode != null) {
            queue.offer(leftNode);
        }
        BinaryTreeNode rightNode = node.getRight();
        if(rightNode != null) {
            queue.offer(rightNode);
        }
        this.iterateByLevelRecursion(queue.poll(), queue);
    }


    @Test
    public void beforeFirstIterate() {
//        beforeFirst(binaryTreeNode);
        this.beforeFirstByNotRecursion(binaryTreeNode);
    }

    /**
     * 10, 9, 11, 3, 4, 5,
     * @param node
     */
    public void beforeFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        System.out.print(node.getValue() + ", ");
        beforeFirst(node.getLeft());
        beforeFirst(node.getRight());
    }

    /**
     * 10, 9, 11, 3, 4, 5,
     * @param node
     */
    public void beforeFirstByNotRecursion(BinaryTreeNode node) {

        //method1
//        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
//        while(node != null || !stack.isEmpty()) {
//            if(node != null) {
//                System.out.print(node.getValue() + ", ");
//                stack.push(node);
//                node = node.getLeft();
//            } else {
//                node = stack.pop();
//                node = node.getRight();
//            }
//        }

        //method2
        try {
            Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
            LinkedBlockingQueue<BinaryTreeNode> queue = new LinkedBlockingQueue<BinaryTreeNode>();
//            List<BinaryTreeNode> values = new ArrayList<BinaryTreeNode>();

            while(node != null || !stack.isEmpty()) {
                if(node != null) {
//                    System.out.print(node.getValue() + ", ");
                    stack.push(node);
                    queue.put(node);
//                    values.add(node.getValue());
                    node = node.getLeft();
                } else {
                    BinaryTreeNode rootNode = stack.pop();
                    node = rootNode.getRight();
                }
            }


            //依次出栈
            int size = queue.size();
            while(size > 0){
                BinaryTreeNode n = queue.poll();
                if(n != null) {
                    System.out.print(n.getValue() + ", ");
                }
                size--;
            }
            System.out.println();

//            for(int i = 0; i < values.size(); i ++) {
//                if(i != 0) {
//                    System.out.print(", ");
//                }
//                System.out.print(values.get(i));
//            }
//            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void midFirstIterate() {
        this.midFirst(binaryTreeNode);
//        this.midFirstByNotRecursion(binaryTreeNode);
    }

    /**
     * 11, 9, 3, 10, 5, 4
     * @param node
     */
    public void midFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        midFirst(node.getLeft());
        System.out.print(node.getValue() + ", ");
        midFirst(node.getRight());
    }

    /**
     * 11, 9, 3, 10, 5, 4,
     * @param node
     */
    public void midFirstByNotRecursion(BinaryTreeNode node) {

        //method1
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.print(node.getValue() + ", ");
                node = node.getRight();
            }
        }


        //method2
//        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
//        Stack<BinaryTreeNode> optStack = new Stack<BinaryTreeNode>();
//        while(node != null || !stack.isEmpty()) {
//            if(node != null) {
//                stack.push(node);
//                node = node.getRight();
//            } else {
//                BinaryTreeNode rootNode = stack.pop();
//                optStack.push(rootNode);
//                node = rootNode.getLeft();
//            }
//        }
//        //依次出栈
//        while(!optStack.isEmpty()){
//            BinaryTreeNode n = optStack.pop();
//            System.out.print(n.getValue()+", ");
//        }
//        System.out.println();



    }


    @Test
    public void afterFirstIterate() {
//        this.afterFirst(binaryTreeNode);
        this.afterFirstByNotRecursion(binaryTreeNode);
    }

    /**
     * 11, 3, 9, 5, 4, 10
     * @param node
     */
    public void afterFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        afterFirst(node.getLeft());
        afterFirst(node.getRight());
        System.out.print(node.getValue() + ", ");
    }

    /**
     * 11, 3, 9, 5, 4, 10
     * @param node
     */
    public void afterFirstByNotRecursion(BinaryTreeNode node) {

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> optStack = new Stack<BinaryTreeNode>();

        while(node != null || !stack.isEmpty()) {

            if(node != null) {
                stack.push(node);
                optStack.push(node);
                node = node.getRight();
            } else {
//                optStack.push(null);
                BinaryTreeNode rootNode = stack.pop();
                node = rootNode.getLeft();

            }

        }

        //依次出栈
        while(!optStack.isEmpty()){
            BinaryTreeNode n = optStack.pop();
//            if(n == null)
//                System.out.print("");
//            else
                System.out.print(n.getValue()+", ");
        }
        System.out.println();


    }



    /**
     * 计算节点个数
     */
    @Test
    public void nodeCountTest() {

        int nodeCount = this.nodeCountByRecursion(binaryTreeNode);
        System.out.println("递归-节点总数为:" + nodeCount);
        nodeCount = this.nodeCountByNotRecursion(binaryTreeNode);
        System.out.println("非递归-节点总数为:" + nodeCount);

        int leafCount = this.leafCountByRecursion(binaryTreeNode);
        System.out.println("递归-叶子节点个数为:" + leafCount);
        int leafCount2 = this.leafCountByNotRecursion(binaryTreeNode);
        System.out.println("非递归-叶子节点个数为:" + leafCount2);

    }

    //递归计算叶子节点个数
    public int leafCountByRecursion(BinaryTreeNode binaryTreeNode) {
        if(binaryTreeNode == null) {
            return 0;
        }
       if(binaryTreeNode.getLeft() == null && binaryTreeNode.getRight() == null) {
           return 1;
       }
       return leafCountByRecursion(binaryTreeNode.getLeft()) + leafCountByRecursion(binaryTreeNode.getRight());
    }

    /**
     * 非递归-统计叶子节点
     * @param binaryTreeNode
     * @return
     */
    public int leafCountByNotRecursion(BinaryTreeNode binaryTreeNode) {
        int leafCount = 0;

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = binaryTreeNode;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                if(node.getRight() == null && node.getLeft() == null) {
                    leafCount ++;
                }
                stack.push(node);
                node = node.getLeft();
            } else {
                BinaryTreeNode btNode = stack.pop();
                node = btNode.getRight();
            }
        }

        return leafCount;
    }

    //递归统计节点总数
    public int nodeCountByRecursion(BinaryTreeNode binaryTreeNode) {
        if(binaryTreeNode == null) {
            return 0;
        }
        return 1 + nodeCountByRecursion(binaryTreeNode.getLeft()) + nodeCountByRecursion(binaryTreeNode.getRight());
    }

    //非递归统计节点总数
    public int nodeCountByNotRecursion(BinaryTreeNode binaryTreeNode) {
        int nodeCount = 0;

        BinaryTreeNode node = binaryTreeNode;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                nodeCount++;
                node = node.getLeft();
            } else {
                BinaryTreeNode btNode = stack.pop();
                node = btNode.getRight();
            }
        }

        return nodeCount;
    }




}
