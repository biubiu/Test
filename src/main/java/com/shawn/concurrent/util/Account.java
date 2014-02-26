package com.shawn.concurrent.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Comparable<Account> {
    private int balance;
    public final Lock monitor = new ReentrantLock();

    public Account(final int initialBalance) {
        balance = initialBalance;
    }

    public void deposit(final int amount) {

        try {
            monitor.lock();
            if (amount > 0) {
                balance += amount;
            }
        } finally {
            monitor.unlock();
        }
    }


    public boolean withdraw(final int amount){
        try {
            monitor.lock();
            if(amount >0 && balance >= amount){
                balance-= amount;
                return true;
            }
            return false;
        }finally{
            monitor.unlock();
        }
    }
    @Override
    public int compareTo(Account other) {
        return new Integer(hashCode()).compareTo(other.hashCode());
    }

    @Override
    public String toString() {
        return String.valueOf(this.balance);
    }
}

class AccountService{
    public boolean transfer(final Account from, final Account to,final int amount) throws Exception{
      /*  final Account[] accounts = new Account[]{from,to};
        Arrays.sort(accounts);*/
        if(from.monitor.tryLock(1,TimeUnit.MILLISECONDS)){
            try{
                if(to.monitor.tryLock(1,TimeUnit.MILLISECONDS)){
                    try{
                        if(from.withdraw(amount)){
                            to.deposit(amount);
                            return true;
                        }else {
                            return false;
                        }
                    }finally{
                      from.monitor.lock();
                    }
                }
            }finally{
                to.monitor.unlock();
            }
        }
        throw new Exception("unable to acquire a lock");
    }

    public static void main(String[] args) throws Exception {
        Account from = new Account(500);
        Account to = new Account(1000);
        new AccountService().transfer(from,to, 300);
        System.out.format("from :%s  to:%s", from.toString(), to.toString());
    }
}
