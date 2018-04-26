package com.grddes.service;


import com.grddes.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */

@Service
public class MapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

    @Autowired
    CityMapper cityMapper;

    public List<String> name(String name){

        Integer id = cityMapper.selectByName(name);
        if(id == null){
            return null;
        }
        List<String> list = cityMapper.selectByParentid(id);
        return list;
    }

    public List getProvince() {
        return cityMapper.getProvinces();
    }
}
