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

    /**
     * 返回表中最大ID
     * @return
     */
    public int backMaxid() {
        return pictureMapper.backMaxid();
    }


    /**
     * 存储图片
     * @param picture
     */
    public void SavePic(Picture picture) {
        pictureMapper.insertSelective(picture);
    }


    /**
     * 删除图片By房屋ID
     * @param id
     * @return
     */
    public int deleteByhId(int id) {
       return pictureMapper.deleteByPrimaryHid(id);
    }

    /**
     *  判断房屋ID对应图片是否存在
     * @param hId
     * @return
     */
    public boolean PictureExist(int hId) {
        boolean flag;
        if(pictureMapper.selectPicturesByhId(hId)!=null){
            flag=true;
        } else{
            flag=false;
        }
        return flag;
    }

    /**
     *  查询当前房屋图片
     * @param hId
     * @return
     */
    public List<Picture> seletePicturesByhId(int hId){
        return pictureMapper.selectPicturesByhId(hId);
    }
}
