package com.codefans.interview.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-12-08 22:56
 *
 * https://leetcode.com/problems/add-two-numbers/
 *
 * 以下两种情况的进位没有考虑到
 * (1)
 *      [5]
        [5]
   (2)
        [1]
        [9,9]
 *
 */

public class No2AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resNode = null;
        int upStep = 0;
        if(l1 != null && l2 != null) {
            int tmp = l1.val + l2.val;
            if(tmp >= 10) {
                resNode = new ListNode(tmp%10);
                upStep = tmp/10;
            } else {
                resNode = new ListNode(tmp);
            }
        }
        ListNode nextNode01 = l1.next;
        ListNode nextNode02 = l2.next;
        ListNode nextNode = resNode;
        ListNode next = null;

        while(nextNode01 != null || nextNode02 != null) {

            if(nextNode01 != null && nextNode02 != null) {
                int tmp = nextNode01.val + nextNode02.val + upStep;
                upStep = 0;
                if(tmp >= 10) {
                    next = new ListNode(tmp%10);
                    upStep = tmp/10;
                } else {
                    next = new ListNode(tmp);
                }
                nextNode01 = nextNode01.next;
                nextNode02 = nextNode02.next;
            } else if(nextNode01 != null && nextNode02 == null) {
                int tmp = nextNode01.val + upStep;
                upStep = 0;
                if(tmp >= 10) {
                    next = new ListNode(tmp%10);
                    upStep = tmp/10;
                } else {
                    next = new ListNode(tmp);
                }

                nextNode01 = nextNode01.next;
            } else if(nextNode01 == null && nextNode02 != null) {
                int tmp = nextNode02.val + upStep;
                upStep = 0;
                if(tmp >= 10) {
                    next = new ListNode(tmp%10);
                    upStep = tmp/10;
                } else {
                    next = new ListNode(tmp);
                }
                nextNode02 = nextNode02.next;
            } else {
                break;
            }
            nextNode.next = next;
            nextNode = nextNode.next;
        }
        if(upStep > 0) {
            nextNode.next = new ListNode(upStep);
        }
        nextNode = resNode;
        return nextNode;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        No2AddTwoNumbers no2AddTwoNumbers = new No2AddTwoNumbers();
        no2AddTwoNumbers.runCode();
    }

    public void runCode() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }

}
