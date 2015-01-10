package com.shawn.leecode;

/**
 * User: Shawn cao
 * Date: 14/12/31
 * Time: PM2:29
 */
/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */
public class Q83RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        for(ListNode p=head; p!=null && p.next!=null; ){
            if (p.val == p.next.val){
                p.next = p.next.next;
                continue;
            }
            p=p.next;
        }
        return head;
    }



    public static class ListNode {
             int val;
             ListNode next;
             ListNode(int x) {
                     val = x;
                     next = null;
                 }
         }
}

