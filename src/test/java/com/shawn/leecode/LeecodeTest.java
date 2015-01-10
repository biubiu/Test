package com.shawn.leecode;

import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.assertTrue;
/**
 * User: Shawn cao
 * Date: 14/12/24
 * Time: PM1:19
 */
public class LeecodeTest {

    @Test
    public void Q1twoSumTest(){
        int[] arr = Q1TwoSum.twoSum(new int[]{572,815,387,418,434,530,376,190,196,74,830,561,973,771,640,37,539,369,327,51,623,575,988,44,659,48,22,776,487,873,486,169,499,82,128,31,386,691,553,848,968,874,692,404,463,285,745,631,304,271,40,921,733,56,883,517,99,580,55,81,232,971,561,683,806,994,823,219,315,564,997,976,158,208,851,206,101,989,542,985,940,116,153,47,806,944,337,903,712,138,236,777,630,912,22,140,525,270,997,763,812,597,806,423,869,926,344,494,858,519,389,627,517,964,74,432,730,843,673,985,819,397,607,34,948,648,43,212,950,235,995,76,439,614,203,313,180,760,210,813,920,229,615,730,359,863,678,43,293,978,305,106,797,769,3,700,945,135,430,965,762,479,152,121,935,809,101,271,428,608,8,983,758,662,755,190,632,792,789,174,869,622,885,626,310,128,233,82,223,339,771,741,227,131,85,51,361,343,641,568,922,145,256,177,329,959,991,293,850,858,76,291,134,254,956,971,718,391,336,899,206,642,254,851,274,239,538,418,21,232,706,275,615,568,714,234,567,994,368,54,744,498,380,594,415,286,260,582,522,795,261,437,292,887,405,293,946,678,686,682,501,238,245,380,218,591,722,519,770,359,340,215,151,368,356,795,91,250,413,970,37,941,356,648,594,513,484,364,484,909,292,501,59,982,686,827,461,60,557,178,952,218,634,785,251,290,156,300,711,322,570,820,191,755,429,950,18,917,905,905,126,790,638,94,857,235,889,611,605,203,859,749,874,530,727,764,197,537,951,919,24,341,334,505,796,619,492,295,380,128,533,600,160,51,249,5,837,905,747,505,82,158,687,507,339,575,206,28,29,91,459,118,284,995,544,3,154,89,840,364,682,700,143,173,216,290,733,525,399,574,693,500,189,590,529,972,378,299,461,866,326,43,711,460,426,947,391,536,26,579,304,852,158,621,683,901,237,22,225,59,52,798,262,754,649,504,861,472,480,570,347,891,956,347,31,784,581,668,127,628,962,698,191,313,714,893}, 101);
        Arrays.stream(arr).forEach(System.out::println);
       // assertTrue(Arrays.equals(arr,new int[]{1,2}));
    }

    //             (2 -> 4 -> 3) + (5 -> 6 -> 4)
    @Test
    public void Q2addTwoNumTest(){

        Q2AddTwoNumbers.ListNode l1 = new Q2AddTwoNumbers.ListNode(2);
        l1.next = new Q2AddTwoNumbers.ListNode(4);
        l1.next.next = new Q2AddTwoNumbers.ListNode(3);

        Q2AddTwoNumbers.ListNode l2 = new Q2AddTwoNumbers.ListNode(5);
        l2.next = new Q2AddTwoNumbers.ListNode(6);
        l2.next.next = new Q2AddTwoNumbers.ListNode(4);

        Q2AddTwoNumbers q2 = new Q2AddTwoNumbers();
        Q2AddTwoNumbers.ListNode resutl = q2.addTwoNumbers(l1, l2);
        System.out.println(resutl.val);
        System.out.println(resutl.next.val);
        System.out.println(resutl.next.next.val);

    }

    @Test
    public void Q27RemoveElements(){
        Q27RemoveElement q27 = new Q27RemoveElement();
        int[] arr = new int[]{1,2,3,4,5,6,7,7,8,8,9,9,0,0,0,1,2,3,2,1,3,12313,123123,4345,56452,123,123131,0};


        int result = q27.removeElement(arr,0);
        Arrays.stream(arr).forEach(System.out::println);
        assertTrue(result == 24);
    }

    @Test
    public void Q26RemoveDuplicates(){
        Q26RemoveDuplicatesfromSortedArray q26 = new Q26RemoveDuplicatesfromSortedArray();
        int[] arr = new int[]{1,1,1,2,2,3,4,5,6,6,6,6,6,6};
        int result = q26.removeDuplicates(arr);
        //Arrays.stream(arr).forEach(System.out::println);
        //System.out.println("--------" + result);
    }


    @Test
    public void Q58LengthOflastWord(){
        String s ="Hello World";
        int len = new Q58LengthOfLastWord().lengthOfLastWord(s);
        assertTrue(len == 5);
    }

    @Test
    public void Q66PlusOne(){
        int[] arr = new int[]{1,0,0,0};
        int[] result = new Q66PlusOne().plusOne(arr);
        Arrays.stream(result).forEach(System.out::print);
        assertTrue(Arrays.equals(result, new int[]{1, 0, 0, 1}));
        System.out.println("==========================");
        arr = new int[]{9,9,9,9};
        result = new Q66PlusOne().plusOne(arr);
        Arrays.stream(result).forEach(System.out::print);
        assertTrue(Arrays.equals(result, new int[]{1, 0, 0, 0,0}));
    }

    @Test
    public void Q151ReverseWords(){
        String s = "  the    sky is     blue";
        String result = new Q151ReverseWords().reverseWords(s);

        assertTrue("blue is sky the".equals(result));
    }

    @Test
    public void Q83RemoveDuplicateFromSortedList(){

        Q83RemoveDuplicatesFromSortedList.ListNode listNode = new Q83RemoveDuplicatesFromSortedList.ListNode(1);
        listNode.next = new Q83RemoveDuplicatesFromSortedList.ListNode(1);
        listNode.next.next = new Q83RemoveDuplicatesFromSortedList.ListNode(1);
        listNode.next.next.next = new Q83RemoveDuplicatesFromSortedList.ListNode(2);
        listNode.next.next.next.next = new Q83RemoveDuplicatesFromSortedList.ListNode(3);
        listNode.next.next.next.next.next = new Q83RemoveDuplicatesFromSortedList.ListNode(3);
        Q83RemoveDuplicatesFromSortedList.ListNode list = new Q83RemoveDuplicatesFromSortedList().deleteDuplicates(listNode);
        Q83RemoveDuplicatesFromSortedList.ListNode current = list;
        while(current!=null){
            System.out.print(current.val);
            current = current.next;
        }
    }
}
