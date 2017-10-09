package com.codefans.reusablecode.datastructure.link;

import com.codefans.reusablecode.common.BinaryTreeFactory;
import com.codefans.reusablecode.common.TreeFactory;
import com.codefans.reusablecode.datastructure.binarytree.BinaryTreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-09-28 10:38
 **/
public class LinkIteratorTest {

    BinaryTreeNode binaryTreeNode = null;

    @Before
    public void before() {
        TreeFactory<BinaryTreeNode> treeFactory = new BinaryTreeFactory();
        this.binaryTreeNode = treeFactory.create("binary");
    }

    @Test
    public void iterateByLevel() {

//        this.iterateLeft(binaryTreeNode);
        this.iterateRight(binaryTreeNode);


    }

    @Test
    public void beforeFirstIterate() {
        beforeFirst(binaryTreeNode);
    }

    public void beforeFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        System.out.println(node.getValue());
        beforeFirst(node.getLeft());
        beforeFirst(node.getRight());
    }

    @Test
    public void midFirstIterate() {
        this.midFirst(binaryTreeNode);
    }

    public void midFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        midFirst(node.getLeft());
        System.out.println(node.getValue());
        midFirst(node.getRight());
    }

    @Test
    public void afterFirstIterate() {
        this.afterFirst(binaryTreeNode);
    }

    public void afterFirst(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        afterFirst(node.getLeft());
        afterFirst(node.getRight());
        System.out.println(node.getValue());
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



}
