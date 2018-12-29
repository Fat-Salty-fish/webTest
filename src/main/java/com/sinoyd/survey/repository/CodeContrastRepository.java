/**
 * @Description
 * @auther 李忠杰
 * @create 2018-12-29 9:59
 */
package com.sinoyd.survey.repository;

import com.sinoyd.survey.entity.CodeContrast;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CodeContrastRepository extends CrudRepository<CodeContrast,Integer> {
}
