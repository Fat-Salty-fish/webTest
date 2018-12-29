/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 9:59
 */
package com.sinoyd.survey.service;

import com.sinoyd.survey.entity.CodeContrast;
import com.sinoyd.survey.repository.CodeContrastRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeContrastService {
    @Autowired
    private CodeContrastRepository codeContrastRepository;

    public Map<Integer,CodeContrast> getContrastToMap()
    {
        List<CodeContrast> contrasts = (List<CodeContrast>) codeContrastRepository.findAll();
        Map<Integer,CodeContrast> contrastMap = new HashMap<>();
        for(CodeContrast codeContrast:contrasts)
        {
            contrastMap.put(codeContrast.getId(),codeContrast);
        }
        return contrastMap;
    }

    public Map<String,Integer> getContrastToMapForScore(){
        List<CodeContrast> contrasts =(List<CodeContrast>) codeContrastRepository.findAll();
        Map<String,Integer> contrastMap = new HashMap<>();
        for(CodeContrast codeContrast:contrasts) {
            contrastMap.put(codeContrast.getCode(),0 );
        }
        return contrastMap;
    }
}

