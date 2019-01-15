package com.sinoyd.survey.service;
import com.sinoyd.survey.entity.ScoreContrast;
import com.sinoyd.survey.repository.ScoreContrastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-01-02 10:55
 */
@Service
public class ScoreContrastService {
    @Autowired
    private ScoreContrastRepository scoreContrastRepository;

    public List<ScoreContrast> findAll(){
        return scoreContrastRepository.findAll();
    }

    public Map<String,ScoreContrast> highLevelScores(){
        List<ScoreContrast> highScore = scoreContrastRepository.findHighScore();
        Map<String,ScoreContrast> highScoresMap = new HashMap<>();
        for(ScoreContrast contrast:highScore){
            highScoresMap.put(contrast.getCode(),contrast);
        }
        return highScoresMap;
    }

    public Map<String,ScoreContrast> middleLevelScores(){
        List<ScoreContrast> middleScore = scoreContrastRepository.findMiddleScore();
        Map<String,ScoreContrast> middleScoresMap = new HashMap<>();
        for(ScoreContrast contrast:middleScore){
            middleScoresMap.put(contrast.getCode(),contrast);
        }
        return middleScoresMap;
    }

}
