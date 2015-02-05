package com.shawn.nio;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertFalse;

/**
 * @author Shawn Cao
 */
public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper();
    String link ="http://s.cjol.com/service/joblistjson.aspx?KeywordType=3&Location=2008&SearchType=1&ListType=2&page=2";
    @Test
    public void testMapJsonToObject() throws IOException {
        CJOLJsonObject object = objectMapper.readValue(new URL(link), new TypeReference<CJOLJsonObject>(){});

        System.out.println(object.JobListHtml)   ;
        assertFalse(Strings.isNullOrEmpty(object.JobListHtml));
    }


    @Test
    public void testReadJsonToGetCertianField() throws IOException{
        JsonNode node = objectMapper.readTree(new URL(link));
        String html = node.get("JobListHtml").asText();
        System.out.println(html);
        assertFalse(Strings.isNullOrEmpty(html));
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class CJOLJsonObject{


        public String JobListHtml;

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("JobListHtml", JobListHtml)
                    .toString();
        }

    }
}
