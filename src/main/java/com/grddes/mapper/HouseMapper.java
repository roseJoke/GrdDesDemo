package com.grddes.mapper;

import com.grddes.model.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer hId);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer hId);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    int deleteByUserId(int userid);

    int backMaxId();

    List<House> selectHousesByUserID(int userid);

    void changeHouseState(@Param("hid") int hid,@Param("state") Boolean state);

    List<House> ListAllHouses();

    List<House> seleteByElements(@Param("hou") House hou, @Param("maxarea") Double maxarea, @Param("maxprice")Double maxprice);
}