package com.shawn.template;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;


public class Template {
    //private String variableValue;
    private Map<String, String> variables;
    private String templateText;
    public Template(String templateText){
        variables = Maps.newLinkedHashMap();
        this.templateText = templateText;
    }

    public void set(String name,String value){
        //this.variableValue = value;
        variables.put(name, value);
    }

    public String evaluate(){
        /*String result = replaceVariable();
        checkForMissingValue(result);*/
      TemplateParse parser = new TemplateParse();
             List<Segment> segments = parser.parseSegments(templateText);
        return concatenate(segments);
        /*List<String> segments = parser.parse(templateText);
        StringBuilder result = new StringBuilder();
        for(String segment:segments){
            append(segment, result);
        }*/
      //  return result.toString();
    }

    private String concatenate(List<Segment> segments){
        StringBuilder result = new StringBuilder();
        for(Segment segment:segments){
            //segment.appendTo(result,variables);
            result.append(segment.evaluate(variables));
        }
        return result.toString();
    }

    private void append(String segment,StringBuilder result){
        if(isVariable(segment)){
            evaluateVariable(segment, result);
        }else{
            result.append(segment);
        }
    }


    public static boolean isVariable(String segment){
        return segment.startsWith("${") && segment.endsWith("}");
    }

    private void evaluateVariable(String segment,StringBuilder result){
        String var = segment.substring(2,segment.length()-1);
        if(!variables.containsKey(var)){
            throw new MissingValueException("No value for: " + segment);
        }
        result.append(variables.get(var));
    }
   /* private String replaceVariable(){
        String result = templateText;
        for(Entry<String,String> entry:variables.entrySet()){
            String regex = "\\$\\{"+entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
    private void checkForMissingValue(String result){
        Matcher m = Pattern.compile(".*\\$\\{.+\\}.*").matcher(result);
        if(m.find()){
            throw new MissingValueException("No value for " + m.group());
        }
    }*/
}
