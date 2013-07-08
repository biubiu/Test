package com.shawn.concurrent.fork_join;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class SimpleForkJoinSample {
    /*
     * conquer-divide ForkJoinPool: implements ExecutorSerive ForkJoinTask:base
     * class being executed in ForkJoinPool,execute fork(),join() operation
     */
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        Task task = new Task(products, 0, products.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do {
            System.out.printf("Main: Thread count: %d \n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Thread steal: %d \n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d \n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        pool.shutdown();
        for (Product e: products) {
            if(e.getPrice()!=12){
                System.out.printf("Product %s : %f \n",e.getName(),e.getPrice());
            }
        }
        System.out.println("Main: end of the program \n");
    }
}

class Task extends RecursiveAction {

    private static final long serialVersionUID = 8488921349109123098L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (last + first) / 2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task task1 = new Task(products, first, middle, increment);
            Task task2 = new Task(products, middle + 1, last, increment);
            invokeAll(task1, task2);
        }
    }

    private void updatePrices() {
        for (int i = 0; i < last; i++) {
            Product p = products.get(i);
            p.setPrice(p.getPrice() * (1 + increment));
        }
    }

}

class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class ProductListGenerator {
    public List<Product> generate(int size) {
        List<Product> ret = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
