package com.shawn.general;

import java.math.BigDecimal;

/**
 *
 * @author shawncao
 *minimizing the scope of a local variable
 *1.declare it where it is first used
 *2.every local variable declaration should contain a initializer
 *3. prefer for loop over while(less local variable than while)
 *for each over for loops
 *	1.filtering:traverse and remove using iterator
 *	2.transforming: traverse and replacel,list iterator or array
 *	3.parallel: iteration:traverse multiple,explicity control over iterator or index
 */
public class MinimizeScopeOfLV {
    public static void main(String[] args) {
        System.out.println(1.00-(0.9*1.00));

        BigDecimal bigDecimal = BigDecimal.valueOf(1.00);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(0.9);
        System.out.println(bigDecimal.subtract(bigDecimal2).multiply(BigDecimal.valueOf(1.00)));
    }
}
