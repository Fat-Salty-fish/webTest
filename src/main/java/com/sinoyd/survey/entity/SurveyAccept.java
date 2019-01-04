package com.sinoyd.survey.entity;
import lombok.Data;
/**
 * @Description 这个对象用于提交成绩时 返回给前端的参数 每一个SurveyAccept都对应一个能力的成绩
 *              capacity对应这个能力的等级 0为差 1为标准 2为优秀
 *              对应颜色为 紫色 绿色 橙红色
 * @auther 李忠杰
 * @create 2018-12-29 15:04
 */
@Data
public class SurveyAccept {
    private Integer staff_id;
    private Integer survey_id;
    private String code;
    private Integer score;
    private Integer capacity;
}
