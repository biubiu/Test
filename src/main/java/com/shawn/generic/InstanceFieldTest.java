package com.shawn.generic;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-30
 * Time: PM8:56
 * To change this template use File | Settings | File Templates.
 */
public class InstanceFieldTest {

    public static void main(String[] args){
        String m = "SOLO" ;
        switch(Ensemble.valueOf(m)){
            case SOLO:
                System.out.println("solo...");
                break;
            case DECTET:
                System.out.println("dectet...");
                break;
            default:
                throw new AssertionError();
        }
    }
}

enum Ensemble{
    /*
     Never derive a value associated with an enum from its ordinal; store it in an instance field instead:
     */
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final Integer number;
    private Ensemble(Integer number){
        this.number = number;
    }
}
