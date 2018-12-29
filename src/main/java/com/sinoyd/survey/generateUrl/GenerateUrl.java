/**
 * @Description 这个累用于生成一个url
 * @auther 李忠杰
 * @create 2018-12-28 14:59
 */
package com.sinoyd.survey.generateUrl;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
@Data
public class GenerateUrl {
    public static String[] generate(Integer surveyId,Integer length)
    {
        String[] urls = new String[length];
        for(int i=0;i<length;i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("url"+surveyId+"-"+i).append(RandomStringUtils.randomAlphabetic(10));
            urls[i]=stringBuilder.toString();
        }
        return urls;
    }
}
