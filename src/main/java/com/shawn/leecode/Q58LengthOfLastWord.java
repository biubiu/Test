package com.shawn.leecode;

/**
 *
 Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 For example,
 Given s = "Hello World",
 return 5.
 */
public class Q58LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        String[] strs = s.split(" ");
        if(strs.length>0){
            return strs[strs.length-1].length();
        }
        return 0;
    }
}
