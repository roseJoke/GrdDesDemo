package com.grddes.mapper;



import com.grddes.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */

@Repository
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    Integer selectByName(String name);

    List<String> selectByParentid(Integer id);

    List getProvinces();
}