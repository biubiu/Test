package com.shawn.guru;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class StoreCredit {
    private static final String FILE_PATH = "~/";
    private int total = 0;

    public static void main(String[] args) {

    }

    private void printOutput(List<Integer[]> ints) throws Exception {
        PrintWriter pw = new PrintWriter(new File(FILE_PATH + "A-small-out.in"));
        for (int i = 0; i < ints.size(); i++) {
            pw.write("Case #"+(i+1) + ": ");
            for(int j = 0 ; j<ints.get(i).length;i++){
                pw.write(ints.get(i)[i]);
            }
            pw.write("\n");
        }
        pw.close();
    }

    private List<CreditConsumer> readList() throws Exception {
        List<CreditConsumer> credits = Lists.newArrayList();
        File file = new File(FILE_PATH + "A-small-practice.in");
        BufferedReader br = new BufferedReader(new FileReader(file));
        total = br.read();
        while (Strings.isNullOrEmpty(br.readLine())) {
            CreditConsumer cc = new CreditConsumer();
            cc.setCredits(Integer.parseInt(br.readLine()));
            String[] items = br.readLine().split(" ");
            List<Integer> itemPrice = Lists.newArrayListWithCapacity(items.length);
            for (int i = 0; i < items.length; i++) {
                itemPrice.add(Integer.parseInt(items[i]));
            }
            cc.setAvailibleItems(itemPrice);
            credits.add(cc);
        }
        br.close();
        return credits;
    }
}

class CreditConsumer {
    private int credits;
    private List<Integer> availibleItems;

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Integer> getAvailibleItems() {
        return availibleItems;
    }

    public void setAvailibleItems(List<Integer> availibleItems) {
        this.availibleItems = availibleItems;
    }

}
