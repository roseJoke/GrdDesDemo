package com.grddes.mapper;

import com.grddes.model.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer pId);

    int deleteByPrimaryHid(Integer hId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pId);

    List<Picture> selectByhId(Integer hId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    int backMaxid();

    void SavePic(Picture picture);

    List<Picture> seletePicture();
}