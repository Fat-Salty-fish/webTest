package com.sinoyd.survey.entity;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 在聚合搜索时用于返回到前端进行整合信息展示 包含简要信息 员工信息以及问卷信息
 * @auther 李忠杰
 * @create 2019-01-02 9:58
 */
@Data
public class SearchAccept {
    private BriefRecord briefRecord;                //简要信息以及成绩汇总
    private Staff staff;                            //员工信息
    private Survey survey;                          //问卷信息
}
