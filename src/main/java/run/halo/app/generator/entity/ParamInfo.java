package run.halo.app.generator.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Post data - ParamInfo
 *
 * @author ssatwa
 */
@Data
public class ParamInfo {

    private String input;
    private List<String> selectedTmpIds; 
    private Map<String,Object> options=new HashMap<String, Object>();

    @Data
    public static class NAME_CASE_TYPE {
        public static String CAMEL_CASE = "CamelCase";
        public static String UNDER_SCORE_CASE = "UnderScoreCase";
        public static String UPPER_UNDER_SCORE_CASE = "UpperUnderScoreCase";
    }

}
