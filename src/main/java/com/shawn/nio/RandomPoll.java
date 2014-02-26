package com.shawn.nio;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * User: Shawn cao
 * Date: 14-2-19
 * Time: PM8:12
 */
public class RandomPoll {

    public static void main(String[] args) throws IOException{
        List<String> topics = readFile();
        for(String m: readFile()){
            System.out.println("---" + m);
        }
        Random random = new Random();
        int i = random.nextInt(topics.size());
        System.out.println(topics.get(i));
        finishedTopic(topics,i);
    }


    public static List<String> readFile() throws IOException {
        List<String> topicList = Lists.newArrayList();

        BufferedReader br = new BufferedReader(new FileReader("/Users/caocao024/Desktop/writing"));
        String line ="";
        while((line = br.readLine() )!=null){
            String tmp = br.readLine();
            if(tmp == null){
                break;
            }
            line = line+tmp;
            if(!Strings.isNullOrEmpty(line))
                topicList.add(line);

            line = null;
            tmp = null;
        }
        br.close();
        return topicList;
    }

    public static void finishedTopic(List<String> finish, int doneIndex) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/caocao024/Desktop/writing"));
        BufferedWriter bufferedWriterDone = new BufferedWriter(new FileWriter("/Users/caocao024/Desktop/writing_finished"));
        bufferedWriterDone.append("\n\n");
        bufferedWriterDone.append(finish.get(doneIndex));
        finish.remove(doneIndex);
        bufferedWriterDone.close();

        bufferedWriter.write(Joiner.on("\n\n").join(finish));
        bufferedWriter.close();

    }

}
