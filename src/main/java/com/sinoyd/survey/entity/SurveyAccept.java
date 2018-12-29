/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 15:04
 */
package com.sinoyd.survey.entity;

import lombok.Data;

@Data
public class SurveyAccept {
    private Integer staff_id;
    private Integer survey_id;
    private String code;
    private Integer score;
}
