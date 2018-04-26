package com.grddes.service;

import com.github.pagehelper.PageHelper;
import com.grddes.mapper.HouseMapper;
import com.grddes.model.House;
import org.junit.jupiter.api.AfterAll;
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
public class HouseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);
    @Autowired
    HouseMapper houseMapper;

    @Autowired
    PictureService pictureService;

    public int addhouse(House hou) {
        return houseMapper.insert(hou);
    }

    public int updatehouse(House hou) {
        return houseMapper.updateByPrimaryKey(hou);
    }

    public House selectHouse(int hid) {
        return houseMapper.selectByPrimaryKey(hid);
    }

    public List<House> selectHousesByUserID(int userid) {
       return houseMapper.selectHousesByUserID(userid);

    }

    public int deleteHouse(int id) {
       if(pictureService.seletePictureByhId(id)){
             pictureService.deleteByhId(id);
       }
       return houseMapper.deleteByPrimaryKey(id);

    }

    public int deleteHouses(int userid) {
       return houseMapper.deleteByUserId(userid);

    }

  

    public int backMaxId() {
        return houseMapper.backMaxId();
    }

    public void changeHouseState(int hid,Boolean state) {
        houseMapper.changeHouseState(hid,state);
    }

    public List<House> ListAllHouses() {

        return houseMapper.ListAllHouses();
    }

    public List<House> seleteByElements(House house, Double maxarea, Double maxprice) {
        return houseMapper.seleteByElements(house,maxarea,maxprice);
    }
}
