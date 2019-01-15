package com.sinoyd.survey.service;
import com.sinoyd.survey.entity.CodeContrast;
import com.sinoyd.survey.repository.CodeContrastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 9:59
 */
@Service
public class CodeContrastService {
    @Autowired
    private CodeContrastRepository codeContrastRepository;

    public Map<Integer,CodeContrast> getContrastToMap()                     //将code的id 与能力对应
    {
        List<CodeContrast> contrasts = (List<CodeContrast>) codeContrastRepository.findAll();
        Map<Integer,CodeContrast> contrastMap = new HashMap<>();
        for(CodeContrast codeContrast:contrasts)
        {
            contrastMap.put(codeContrast.getId(),codeContrast);
        }
        return contrastMap;
    }

    public List<CodeContrast> getContrastList() {
        return (List<CodeContrast>) codeContrastRepository.findAll();
    }

    public Map<String,Integer> getContrastToMapForScore(){                  //以code为键 建立结果集 存放计算结果
        List<CodeContrast> contrasts =(List<CodeContrast>) codeContrastRepository.findAll();
        Map<String,Integer> contrastMap = new HashMap<>();
        for(CodeContrast codeContrast:contrasts) {
            contrastMap.put(codeContrast.getCode(),0 );
        }
        contrastMap.put("null",0);
        return contrastMap;
    }
}

