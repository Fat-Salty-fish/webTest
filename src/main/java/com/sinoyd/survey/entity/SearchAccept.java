/**
 * @Description
 * @auther 李忠杰
 * @create 2019-01-02 9:58
 */
package com.sinoyd.survey.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SearchAccept {
    private BriefRecord briefRecord;                //简要信息以及成绩汇总
    private Staff staff;                            //员工信息
    private Survey survey;                          //问卷信息
}
