/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-27 15:42
 */
package com.sinoyd.survey.criteria;

import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.StringUtils;
import lombok.Data;

import java.util.Date;

@Data
public class SurveyCriteria extends BaseCriteria {
    private String surveyName;
    private String surveyShow;
    private String surveyType;
    private Date createdTime;

    @Override
    public String getCondition() {
        values.clear();
        StringBuilder condition = new StringBuilder();
        if(StringUtils.isNotNullAndEmpty(this.surveyName))
        {
            condition.append("and surveyName = :surveyName");
            values.put("surveyName","%" + this.surveyName + "%");
        }
        if(StringUtils.isNotNullAndEmpty(this.surveyType))
        {
            condition.append("and surveyType = :surveyType");
            values.put("surveyType","%" + this.surveyType + "%");
        }
        if(StringUtils.isNotNullAndEmpty(this.surveyShow))
        {
            condition.append("and surveyShow = :surveyShow");
            values.put("surveyShow","%" + this.surveyShow + "%");
        }
        return condition.toString();
    }
}
