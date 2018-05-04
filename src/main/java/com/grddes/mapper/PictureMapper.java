package com.grddes.mapper;

import com.grddes.model.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer pId);

    int deleteByPrimaryHid(Integer hId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pId);

    int updateByPrimaryKey(Picture record);

    int backMaxid();

    List<Picture> selectPicturesByhId(int hId);
}