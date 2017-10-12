package com.codefans.reusablecode.datastructure.binarytree;

/**
 * @author: caishengzhi
 * @date: 2017-09-28 10:40
 * 二叉树
 **/

public class BinaryTreeNode {

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    private int value;

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
