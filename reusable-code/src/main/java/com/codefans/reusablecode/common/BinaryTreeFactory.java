package com.codefans.reusablecode.common;

import com.codefans.reusablecode.datastructure.binarytree.BinaryTreeNode;

/**
 * @author: caishengzhi
 * @date: 2017-09-28 10:52
 **/
public class BinaryTreeFactory implements TreeFactory<BinaryTreeNode> {

    private final String TREE_TYPE_BINARY = "binary";

    @Override
    public BinaryTreeNode create(String treeType) {
        BinaryTreeNode binaryTreeNode = null;
        if(TREE_TYPE_BINARY.equals(treeType)) {
            binaryTreeNode = this.createBinaryTreeNode();
        }
        return binaryTreeNode;
    }

    /**
     *      10
     *     /  \
     *    9    4
     *   / \  / \
     *  11 3 5   12
     * / \  / \
     *13 6 8  14
     * 先序遍历：10, 9, 11, 13, 6, 3, 4, 5, 8, 14, 12
     * 中序遍历：13, 11, 6, 9, 3, 10, 8, 5, 14, 4, 12
     * 后序遍历：13, 6, 11, 3, 9, 8, 14, 5, 12, 4, 10
     * @return
     */
    
    /**
     *      10
     *     /  \
     *    9    4
     *   / \  /
     *  11 3 5
     * 先序遍历：10, 9, 11, 3, 4, 5
     * 中序遍历：11, 9, 3, 10, 5, 4
     * 后序遍历：11, 3, 9, 5, 4, 10
     * @return
     */
    public BinaryTreeNode createBinaryTreeNode() {
        BinaryTreeNode rootNode = new BinaryTreeNode();
        rootNode.setValue(10);
        BinaryTreeNode left = new BinaryTreeNode();
        left.setValue(9);
        BinaryTreeNode right = new BinaryTreeNode();
        right.setValue(4);
        rootNode.setLeft(left);
        rootNode.setRight(right);

        BinaryTreeNode threeLeftPartLeftNode = new BinaryTreeNode();
        threeLeftPartLeftNode.setValue(11);
        BinaryTreeNode threeLeftPartRightNode = new BinaryTreeNode();
        threeLeftPartRightNode.setValue(3);
        left.setLeft(threeLeftPartLeftNode);
        left.setRight(threeLeftPartRightNode);

        BinaryTreeNode threeRightPartLeftNode = new BinaryTreeNode();
        threeRightPartLeftNode.setValue(5);
//        BinaryTreeNode threeRightPartRightNode = new BinaryTreeNode();
//        threeRightPartRightNode.setValue(12);
        right.setLeft(threeRightPartLeftNode);
//        right.setRight(threeRightPartRightNode);

//        BinaryTreeNode fourLeftPartLeftNode = new BinaryTreeNode();
//        fourLeftPartLeftNode.setValue(13);
//        BinaryTreeNode fourLeftPartRightNode = new BinaryTreeNode();
//        fourLeftPartRightNode.setValue(6);
//        threeLeftPartLeftNode.setLeft(fourLeftPartLeftNode);
//        threeLeftPartLeftNode.setRight(fourLeftPartRightNode);
//
//        BinaryTreeNode fourRightPartLeftNode = new BinaryTreeNode();
//        fourRightPartLeftNode.setValue(8);
//        BinaryTreeNode fourRightPartRightNode = new BinaryTreeNode();
//        fourRightPartRightNode.setValue(14);
//        threeRightPartLeftNode.setLeft(fourRightPartLeftNode);
//        threeRightPartLeftNode.setRight(fourRightPartRightNode);

        return rootNode;
    }


}
