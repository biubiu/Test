package com.shawn.general;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;


/**
 * @author Shawn Cao
 */
public class ExceptionTest {



    @Test
    public void testChecked(){
        String str1="aaa",str2="bbb",str3="";
        checkNull(str1);
        checkNull(str2);
        try{
            checkNull(str3);
            fail();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkNull(String str){
        if(Strings.isNullOrEmpty(str)){
            throw new IllegalArgumentException("input cannot be null");
        }
    }

    @Test
    public void testOptional(){
        List l = null;
        Optional o =Optional.ofNullable(l);
        System.out.println(o);
    }
}
