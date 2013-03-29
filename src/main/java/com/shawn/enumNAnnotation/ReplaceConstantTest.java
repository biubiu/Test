package com.shawn.enumNAnnotation;

import java.util.HashMap;
import java.util.Map;


/**
 * enum: classes export one instance for each enumeration constant via static final
 * 		 instance-controlled,singletons
 *
 */
public class ReplaceConstantTest {

    public static void main(String[] args) {
        opSymbol();
    }

    static void opSymbol(){
        for(Operation op:Operation.values()){
            //System.out.printf("op on %s is %s%n",op,op.show());
        }
    }
    static void opTest(){
        //System.out.println(Operation.PLUS.apply(1.0, 2.0));
        for(Operation op: Operation.values()){
            System.out.printf("op on %s is %f%n",op,op.apply(1.0, 2.0));
        }
    }

    static void conTest(){
        double marsWeight=2091923;
        double mass = marsWeight/Plant.MARS.surfaceGravity();
        for(Plant p: Plant.values()){
            System.out.printf("Weight on %s is %f%n",p,p.surfaceWeight(mass));
        }
    }
}
/*
 * 1.to associate data with enum constants , declare instance fields and write a constructor
 * 	 that takes the data and stores it in the fields
 * 2. immutable,all fields should be final
 *
 */
enum Plant{
    VENUS(4.869e+24,6.052e6),
    MARS(6.419e+23,3.393e6),
    NEPTUNE(1.024e+26,2.477e7);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    private static final double G = 6.67300E-11;
    private Plant(double mass,double radius){
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G*mass/(radius*radius);
    }

    public double mass(){
        return mass;
    }

    public double radius(){
        return radius;
    }
    public double surfaceGravity(){
        return surfaceGravity;
    }

    public double surfaceWeight(double mass){
        return mass*surfaceGravity;
    }
}


interface Show{
       public  String show();
  }

enum Operation implements Show{
    PLUS("+"){
        @Override
        double apply(double x, double y) {
            return x+y;
        }
        public String show() {
            return this.getSymbol();
        }
    },
    MINUS("-"){
        @Override
        double apply(double x, double y) {
            return x-y;
        }

        public String show() {
            return this.getSymbol();
        }
    };

    private final String symbol;
    private Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }
    //abstract method to implement in each Operation
    abstract double apply(double x, double y);

    //using string to get Label
    public static final Map<String, Operation> StringToEnum = new HashMap<String, Operation>();
    static{
        for (Operation op:values()) {
            StringToEnum.put(op.toString(), op);
        }
    }
    public static Operation getOperation(String symbol){
        return StringToEnum.get(symbol);
    }
}

