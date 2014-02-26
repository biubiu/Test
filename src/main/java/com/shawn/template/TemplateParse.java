package com.shawn.template;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class TemplateParse {

    private List<String> list;
    public TemplateParse() {
        list = Lists.newArrayList();
    }
    public List<String> parse(String template){
        int index = collectSegments(list, template);
        addTail(list, template, index);
        addEmptyStringIfTemplateWasEmpty(list);
        return list;
    }

    public List<Segment> parseSegments(String template){
        List<Segment> segments = Lists.newArrayList();
        List<String> strs = parse(template);
        for(String s:strs){
            if(Template.isVariable(s)){
                String name = s.substring(2,s.length()-1);
                segments.add(new Variable(name));
            }else{
                segments.add(new PlainText(s));
            }
        }
        return null;
    }
    private int collectSegments(List<String> segs,String src){
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(src);
        int index = 0;
        while(matcher.find()){
            addPrecedingPlainText(segs, src, matcher, index);
            addVariable(segs, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addTail(List<String> segs,String template,int index){
        if(index < template.length()){
            segs.add(template.substring(index));
        }
    }

    private void addVariable(List<String> segs,String src,Matcher m){
        segs.add(src.substring(m.start(),m.end()));
    }
    private void addPrecedingPlainText(List<String> segs,String src,Matcher m,int index){
        if(index!=m.start()){
            segs.add(src.substring(index,m.start()));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<String> segs){
        if(segs.isEmpty()){
            segs.add("");
        }
    }
}
