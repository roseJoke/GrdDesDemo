package com.grddes.service;

import com.grddes.mapper.PictureMapper;
import com.grddes.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/4/25  9:52
 */
@Service
public class PictureService {

    @Autowired
    PictureMapper pictureMapper;

    public int backMaxid() {
        return pictureMapper.backMaxid();
    }

    public void SavePic(Picture picture) {
        pictureMapper.insertSelective(picture);
    }

    public List<Picture> seletePicture() {
        return pictureMapper.seletePicture();
    }

    public int deleteByhId(int id) {
       return pictureMapper.deleteByPrimaryHid(id);
    }

    public boolean seletePictureByhId(int id) {
        boolean flag;
        if(pictureMapper.selectByhId(id)!=null){
            flag=true;
        } else{
            flag=false;
        }
        return flag;
    }
}
