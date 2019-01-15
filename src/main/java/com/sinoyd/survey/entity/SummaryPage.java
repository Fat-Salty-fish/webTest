package com.sinoyd.survey.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用于聚合搜索时返回的对象 实现了分页功能
 * @auther 李忠杰
 * @create 2019-01-03 15:56
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummaryPage {
    private Integer currentPage = 0;      //待显示页面
    private Long count = 0L;      //消息条数
    private Integer limit = 10;     //每页显示条数 默认10条
    private List<SearchAccept> searchAcceptList;    //搜索得到的数据
    private String message;     //在错误时显示的信息 未发生错误时返回为空
}
