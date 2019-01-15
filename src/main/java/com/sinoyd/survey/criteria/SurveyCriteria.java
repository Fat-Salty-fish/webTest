package com.sinoyd.survey.criteria;
import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.StringUtils;
import lombok.Data;
import java.util.Date;

/**
 * @Description 实现模糊检索和分页搜索功能
 * @auther 李忠杰
 * @create 2018-12-27 15:42
 */
@Data
public class SurveyCriteria extends BaseCriteria {
    private String surveyName;

    @Override
    public String getCondition() {
        values.clear();
        StringBuilder condition = new StringBuilder();
        if (StringUtils.isNotNullAndEmpty(this.surveyName)) {
            condition.append("and surveyName like :surveyName");
            values.put("surveyName","%" + this.surveyName + "%");
        }
        return condition.toString();
    }
}
