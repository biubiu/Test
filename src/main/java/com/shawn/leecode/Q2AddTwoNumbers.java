package com.shawn.leecode;

/**
 * User: Shawn cao
 * Date: 14/12/25
 * Time: PM5:57
 */

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
public class Q2AddTwoNumbers {




    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int x=0 ,y=0 ,carry=0 , sum=0 ;
        ListNode h=null;
     while(l1!=null && l2 !=null){
        x = getValueAndMoveNext(l1);
         y = getValueAndMoveNext(l2);
         sum = x + y + carry;

         ListNode node = new ListNode(sum%10);
         h = node;
         h = node.next;
         carry = sum/10;
     }
        if(carry > 0){
            ListNode node = new ListNode(carry%10);
            h = node;
        }

     return h;
    }



    private int getValueAndMoveNext(ListNode node){
        int x = 0;
        if(node != null){
            x= node.val;
            node = node.next;
        }
        return x;
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



