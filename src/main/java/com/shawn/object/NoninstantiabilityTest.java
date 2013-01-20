package com.shawn.object;

/**
 * write class just grouping of static methods and static field
 * made noninstantiable by including a private constructor
 * @author shawncao
 *
 */
public class NoninstantiabilityTest {

}

class Utility{
    private Utility() {
        throw new AssertionError();
    }
}
