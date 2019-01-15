package com.sinoyd.survey.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinoyd.frame.base.repository.CommonRepository;
import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.PageBean;
import com.sinoyd.survey.entity.*;
import com.sinoyd.survey.repository.OptionRepository;
import com.sinoyd.survey.repository.QuestionRepository;
import com.sinoyd.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-27 15:39
 */
@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CodeContrastService codeContrastService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private BriefRecordService briefRecordService;

    @Autowired
    private ScoreContrastService scoreContrastService;

    public void getSurveyByPage(PageBean pageBean, BaseCriteria surveyCriteria) {
        pageBean.setEntityName("Survey a");
        pageBean.setSelect("Select a");
        commonRepository.findByPage(pageBean, surveyCriteria);
    }

    public Survey getOne(Integer surveyId) {                                        //根据id获取一套题目 包括问题和选项
        Survey survey = surveyRepository.findOne(surveyId);                          //获取survey
        survey.setQuestions(new ArrayList<Question>());                         //设置survey下的question
        List<Question> questions = questionRepository.getAllBySurveyId(surveyId);       //获取surveyid有关的问题集合

        Map<Integer, CodeContrast> contrastMap = codeContrastService.getContrastToMap();

        for (Question question : questions) {
            question.setOptions(new ArrayList<Option>());
        }
        List<Option> options = optionRepository.getAllBySurveyId(surveyId);               //获取所有的option 并进行设置 设置题目序号 将code匹配codeid

//        for (Question question : questions) {
//            question.setOptions(options.stream().filter(x -> x.getQuestionId().equals(question.getId())).collect(Collectors.toList()));
//        }

        for (Option eachOption : options) {
            eachOption.setId(eachOption.getId() - eachOption.getId() / 10 * 10);
            eachOption.setCode((eachOption.getCodeId() != null) ? (contrastMap.get(eachOption.getCodeId()).getCode()) : "");
            eachOption.setCodeId(null);
            if (eachOption.getId() == 0) {
                eachOption.setId(10);
            }
        }
        for (Question eachQuestion : questions) {                                   //将option添加到question中去

            for (Option eachOption : options) {
                if (eachQuestion.getId().equals(eachOption.getQuestionId())) {
                    eachQuestion.getOptions().add(eachOption);
                }
            }
        }

        survey.setQuestions(questions);
        return survey;
    }


    public Object getResult(Map<String, Object> model) {
        Staff staff = JSON.parseObject(JSON.toJSONString(model.get("staff")), new TypeReference<Staff>() {
        });
        staff = staffService.saveOrUpdate(staff);
        String noncestr = (String) model.get("noncestr");
        ArrayList<Question> questions = JSON.parseObject(JSON.toJSONString(model.get("questions")), new TypeReference<ArrayList<Question>>() {
        });
        String require = questionService.require(questions);
        if (require.equals("所有题目的选项得分加起来均为十分")) {
            BriefRecord briefRecord = new BriefRecord();                            //保存简要信息
            briefRecord.setStaffId(staff.getId());
            briefRecord.setSurveyId(Integer.parseInt(noncestr.split("-")[0]));
            briefRecordService.save(briefRecord);
            List<Record> records = new ArrayList<>();
            Map<String, Integer> capacity = codeContrastService.getContrastToMapForScore();          //计算总共的能力值
            for (Question eachQuestion : questions) {
                for (Option eachOption : eachQuestion.getOptions()) {
                    String code = eachOption.getCode();
                    Record record = new Record();
                    if (code != null && !code.equals("")) {
                        Integer previousScore = capacity.get(code);
                        capacity.put(code, previousScore + eachOption.getScore());
                        record.setCode(code);
                    } else {
                        Integer previousScore = capacity.get("null");
                        capacity.put("null", previousScore + eachOption.getScore());
                        record.setCode("null");
                    }
                    record.setStaffId(staff.getId());
                    record.setSurveyId(Integer.parseInt(noncestr.split("-")[0]));
                    record.setQuestionId(eachQuestion.getId());
                    record.setOptionId(eachOption.getId());
                    record.setBriefId(briefRecord.getId());
                    record.setScore(eachOption.getScore());
                    record.setStaffId(briefRecord.getStaffId());
                    records.add(record);
                }
            }
            recordService.saveRecord(records);
            capacity.remove("null");                                           //将null的成绩去除 不返回到前端
            this.convert(capacity);                                                 //将成绩换算
            briefRecord.setCF(capacity.get("CF"));                                  //将提交的数据经过计算之后存入数据库中
            briefRecord.setCO(capacity.get("CO"));
            briefRecord.setIM(capacity.get("IM"));
            briefRecord.setME(capacity.get("ME"));
            briefRecord.setPL(capacity.get("PL"));
            briefRecord.setRI(capacity.get("RI"));
            briefRecord.setSH(capacity.get("SH"));
            briefRecord.setSP(capacity.get("SP"));
            briefRecord.setTW(capacity.get("TW"));
            StringBuilder capacityToSave = new StringBuilder();
            final Map<String, ScoreContrast> HIGH_LEVEL_SCORE = scoreContrastService.highLevelScores();
            final Map<String, ScoreContrast> MIDDLE_LEVEL_SCORE = scoreContrastService.middleLevelScores();
            List<SurveyAccept> acceptInfos = new ArrayList<>();
            List<CodeContrast> codeContrastList = codeContrastService.getContrastList();
            for (Map.Entry<String, Integer> score : capacity.entrySet()) {
                SurveyAccept info = new SurveyAccept();
                info.setStaff_id(staff.getId());
                info.setSurvey_id(Integer.parseInt(noncestr.split("-")[0]));
                info.setCode(score.getKey());
                info.setScore(score.getValue());
                info.setCapacity(this.levelContrast(score.getKey(), score.getValue(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
                if(info.getCapacity().equals(2)){
                    capacityToSave.append(codeContrastList.stream().filter(codeContrast -> info.getCode().equals(codeContrast.getCode())).findFirst().get().getValue());
                    capacityToSave.append(',');
                }
                acceptInfos.add(info);
            }
            capacityToSave.deleteCharAt(capacityToSave.length()-1);
            briefRecord.setCapacity(capacityToSave.toString());
            briefRecordService.update(briefRecord);
            return acceptInfos;
        } else {
            return require;
        }
    }

    public Integer levelContrast(String code, Integer score, Map<String, ScoreContrast> HIGH_LEVEL_SCORE, Map<String, ScoreContrast> MIDDLE_LEVEL_SCORE) {
        if (score >= HIGH_LEVEL_SCORE.get(code).getScore()) {
            return 2;
        } else if (score >= MIDDLE_LEVEL_SCORE.get(code).getScore()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void convert(Map<String, Integer> capacity) {                      //此函数将成绩结果换算成百分制的结果
        final List<ScoreContrast> SCORE_CONTRAST_LIST = scoreContrastService.findAll();
        for (Map.Entry<String, Integer> entry : capacity.entrySet()) {
            for (ScoreContrast contrast : SCORE_CONTRAST_LIST) {
                if (entry.getKey().equals(contrast.getCode())) {
                    if (entry.getValue().equals(contrast.getSumScore())) {
                        entry.setValue(contrast.getScore());
                        break;
                    }
                }
            }
        }
    }

    public Object summary(Integer limit,Integer page,String staffName) {
        SummaryPage summaryPage = new SummaryPage();
        Integer currentPage = 0;
        Integer limited = 0;
        Long count = 0L;
        if(limit == null){
            limited = 10;
        }else if(limit < 1 || limit > 100){
            limited = 10;
        }else{
            limited = limit;
        }

        if(page == null){               //page 要显示的页面
            page = 1;
        }else if(page < 1){
            page = 1;
        }
        currentPage = page ;

        List<BriefRecord> briefRecordsList;
        List<Staff> staffList;

        if(staffName==null||staffName.equals("")){          //当没有查询条件的时候
            count = briefRecordService.count();
            briefRecordsList = briefRecordService.getListByPage(currentPage,limited);
            staffList = staffService.findStaffByIds(briefRecordsList.stream().map(BriefRecord::getStaffId).collect(Collectors.toList()));
        }else {
            staffList = staffService.findStaffByName(staffName);
            if(staffList.size()==0){
                summaryPage.setCurrentPage(0);
                summaryPage.setCount(0L);
                summaryPage.setLimit(limited);
                summaryPage.setMessage("查无此用户 请重新检索");
                return summaryPage;
            }
            count = briefRecordService.countByStaffId(staffList.stream().map(staff -> staff.getId()).collect(Collectors.toList()));
            briefRecordsList = briefRecordService.getListByPageAndStaffIds(staffList.stream().map(staff -> staff.getId()).collect(Collectors.toList()),currentPage,limited );
        }

        if(count.equals(0)){
            summaryPage.setCurrentPage(0);
            summaryPage.setCount(0L);
            summaryPage.setLimit(limited);
            summaryPage.setMessage("无返回条目 请重新检索");
            return summaryPage;
        }

        List<Survey> surveyList = surveyRepository.findAllByIdIn(briefRecordsList.stream().map(BriefRecord::getSurveyId).collect(Collectors.toList()));
        List<SearchAccept> acceptList = new ArrayList<>();      //用来存放接收信息
        final Map<String, ScoreContrast> HIGH_LEVEL_SCORE = scoreContrastService.highLevelScores();
        final Map<String, ScoreContrast> MIDDLE_LEVEL_SCORE = scoreContrastService.middleLevelScores();
        for(BriefRecord eachRecord:briefRecordsList){
            SearchAccept accept = new SearchAccept();
            //获取简要答题信息及结果
            Map<String,Integer> codeLevel = new HashMap<>();
            codeLevel.put("CF",this.levelContrast("CF", eachRecord.getCF(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("CO",this.levelContrast("CO", eachRecord.getCO(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("IM",this.levelContrast("IM", eachRecord.getIM(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("ME",this.levelContrast("ME", eachRecord.getME(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("PL",this.levelContrast("PL", eachRecord.getPL(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("RI",this.levelContrast("RI", eachRecord.getRI(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("SH",this.levelContrast("SH", eachRecord.getSH(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("SP",this.levelContrast("SP", eachRecord.getSP(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            codeLevel.put("TW",this.levelContrast("TW", eachRecord.getTW(), HIGH_LEVEL_SCORE, MIDDLE_LEVEL_SCORE));
            eachRecord.setCodeLevel(codeLevel);
            accept.setBriefRecord(eachRecord);

            //设置员工信息
            accept.setStaff(staffList.stream().filter(staff -> eachRecord.getStaffId().equals(staff.getId())).findFirst().orElse(null));

            //设置问卷信息
            accept.setSurvey(surveyList.stream().filter(survey -> eachRecord.getSurveyId().equals(survey.getId())).findFirst().orElse(null));

            acceptList.add(accept);
        }
        summaryPage.setCurrentPage(currentPage);                //设置返回对象的页面参数
        summaryPage.setLimit(limited);
        summaryPage.setCount(count);
        summaryPage.setSearchAcceptList(acceptList);
        return summaryPage;
    }
}
