package com.shawn.template;

import java.util.Map;

public class PlainText implements Segment{
    private String text;

    public PlainText(String text){
        this.text = text;
    }

    @Override
    public String evaluate(Map<String, String> variables){
        return text;
    }


    public boolean equals(Object other){
        return text.equals(((PlainText)other).text);
    }
}
