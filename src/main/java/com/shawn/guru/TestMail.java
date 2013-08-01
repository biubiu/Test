package com.shawn.guru;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


public class TestMail {
    private static List<String> filterRegisterEmail(final Set<String> emails, RestTemplate restTemplate) {
        int batchNum = 2;
        List<String> registeredEmails = Lists.newArrayList();
        Object[] mails = emails.toArray();


        int nThreads = emails.size() / batchNum;
        ExecutorService executor = Executors.newFixedThreadPool(nThreads > 10 ? 10 : nThreads);
        List<Task> executableTasks = Lists.newArrayListWithCapacity(nThreads);
        for (int i = 0; i <= nThreads; i++) {
            //StringBuilder sb = new StringBuilder(request);
            List<String> validatingEmails=Lists.newArrayListWithCapacity(batchNum);
            int number = (emails.size() / batchNum == i ? (emails.size()) : ((i + 1) * batchNum));
            for (int j = i * batchNum; j < number; j++) {
              //  sb.append(mails[j]).append(",");
                validatingEmails.add((String)mails[j]);
            }
            EmailJson emailJson = new EmailJson();
            emailJson.setEmails(validatingEmails);
            Task task = new Task(emailJson, restTemplate);
            executableTasks.add(task);
        }


        try {
            executor.invokeAll(executableTasks);
            List<Future<List<String>>> runningResult = executor.invokeAll(executableTasks);
            for (Future<List<String>> e : runningResult) {
                try {
                    registeredEmails.addAll(e.get());
                } catch (ExecutionException e1) {
                    continue;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return registeredEmails;
    }

    public static void main(String[] args) {
        //testResponse();
        testCon();
        //testAuotLogin();
    }

    static void testAuotLogin(){
        RestTemplate restTemplate = new RestTemplate();
        AutoLoginParam param = new AutoLoginParam();
        param.setRecipientId("65498");
        param.setSenderId("0");
        param.setTemplate("applyCompany");
        String tString=restTemplate.postForObject("http://tmail.tianji.com/email/mtrck", param, String.class);
        System.out.println("t:" + tString);
    }
    static void testResponse(){
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject("http://192.168.1.147/rest/users/check_email?emails=scpundre@yahoo.com.cn,fengzhenxing@tianji.com",Response.class );
        System.out.println("res: " + response.toString());
    }

    static void testCon(){
        Set set = Sets.newHashSet("shawncao@tianji.com", "c.shangfei@gmail.com", "tj_shawn1@tianji.com", "scpundre@yahoo.com.cn", "aibo@tianji.com",
                "fengzhenxing@tianji.com", "tj_shawn2@tianji.com", "tj_shawn3@tianji.com", "tj_shawn4@tianji.com");
        RestTemplate restTemplate = new RestTemplate();
        List<String> emails = filterRegisterEmail(set, restTemplate);
        for (String email : emails) {
            System.out.println("resule: " + email);
        }


    }
}

class Task implements Callable<List<String>> {
    //private String requestUrl;
    private RestTemplate restTemplate;
    private EmailJson emailJson;
    public Task(EmailJson emailJson, RestTemplate restTemplate) {
      //  this.requestUrl = requestUrl;
        this.emailJson = emailJson;
        this.restTemplate = restTemplate;
    }

    public List<String> call() {
        String request = "http://192.168.1.143/rest/users/check_email";
        System.out.println(Thread.currentThread()+" rest : " +request);
        Response response = restTemplate.postForObject(request,emailJson, Response.class);
        System.out.println(Thread.currentThread() + " response: " + response.toString());
        return response.getData();
    }
}

@JsonSerialize(include = Inclusion.NON_NULL)
class EmailJson implements Serializable{

    private static final long serialVersionUID = 5730671196811871683L;
    List<String> emails;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
class Response implements Serializable{

    private static final long serialVersionUID = -4506375248259840450L;
    String state;
    List<String> data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
class AutoLoginParam {
    String senderId;
    String recipientId;
    String template;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

