package com.codefans.interview.algorithm.special;

import com.codefans.interview.algorithm.leetcode.ListNode;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Author: codefans
 * @Date: 2018-12-11 20:26
 * 从尾到头打印链表
 */

public class PrintLinkedListFromEndToHead {

    private LinkedListNode firstNode;

    public static void main(String[] args) {
        PrintLinkedListFromEndToHead printLinkedListFromEndToHead = new PrintLinkedListFromEndToHead();
        printLinkedListFromEndToHead.runCode();
    }

    public void runCode() {
        this.initLinkedList();

        System.out.println("print from header:");
        this.printFromHead();

        System.out.println("print from end:");
        this.printFromEnd();
        System.out.println("print from end by recursion:");
        this.printFromEndRecursion(firstNode);

        System.out.println("print from end by reverse LinkedList");
        this.printFromEndByReverseLinkedList(firstNode);

        //重新初始化firstNode
        this.initLinkedList();
        System.out.println("reverseLinkedList:");
        reverseLinkedList(firstNode);

    }

    public void initLinkedList() {
        firstNode = new LinkedListNode(1);
        LinkedListNode secondNode = new LinkedListNode(2);
        firstNode.next = secondNode;
        LinkedListNode thirdNode = new LinkedListNode(3);
        secondNode.next = thirdNode;

    }

    public void printFromHead() {
        LinkedListNode node = firstNode;
        while(node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    /**
     * 比递归好
     */
    public void printFromEnd() {
        LinkedListNode node = firstNode;
        Stack<Integer> valStack = new Stack<Integer>();
        while(node != null) {
            valStack.push(node.value);
            node = node.next;
        }

        Iterator<Integer> iter = valStack.iterator();
        while(iter.hasNext()) {
            System.out.println(valStack.pop());
        }

    }

    /**
     * 可能会因为栈深度太深而报错
     * @param node
     */
    public void printFromEndRecursion(LinkedListNode node) {
        if(node == null) {
            return;
        }
        printFromEndRecursion(node.next);
        System.out.println(node.value);
    }

    /**
     * 翻转链表, 再打印
     */
    public void printFromEndByReverseLinkedList(LinkedListNode node) {
//        LinkedListNode node = firstNode;
        Stack<LinkedListNode> nodeStack = new Stack<LinkedListNode>();
        while(node != null) {
            nodeStack.push(node);
            node = node.next;
        }

        Iterator<LinkedListNode> iter = nodeStack.iterator();
        LinkedListNode first = null;
        LinkedListNode oneNode = null;
        LinkedListNode lastNode = null;
        while(iter.hasNext()) {
            oneNode = nodeStack.pop();
            if(first == null) {
                first = oneNode;
            }
            if(lastNode != null) {
                lastNode.next = oneNode;
            }
            lastNode = oneNode;
            oneNode.next = null;
        }

        while(first != null) {
            System.out.println(first.value);
            first = first.next;
        }

    }

    /**
     * 翻转链表, 再打印
     */
    public void reverseLinkedList(LinkedListNode current) {
//        LinkedListNode current = firstNode;

        if(current == null) {
            System.out.println("current is null");
            return;
        }

        LinkedListNode prev = null;
        LinkedListNode next = null;

        while(current != null) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

//        System.out.println(prev.value);

        while(prev != null) {
            System.out.println(prev.value);
            prev = prev.next;
        }

    }

}
