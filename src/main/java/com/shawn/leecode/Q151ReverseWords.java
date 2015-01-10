package com.shawn.leecode;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * User: Shawn cao
 * Date: 14/12/31
 * Time: AM11:22
 */
/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".


Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
 */
public class Q151ReverseWords {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder(s.length());
        for(int i=words.length-1; i>=0; i--){
            sb.append(words[i]);
            if(i >0)
                sb.append(" ");
        }
         return sb.toString();
    }

}
