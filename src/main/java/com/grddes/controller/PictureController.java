package com.grddes.controller;

import com.grddes.model.Picture;
import com.grddes.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/5/2  11:07
 */
@RestController
public class PictureController {
    @Autowired
    PictureService pictureService;

    @RequestMapping("/getPicturesByHid")
    public List<Picture> getPicturesByHid(int hId){
        System.out.println("哈哈："+pictureService.seletePicturesByhId(hId));
        List<Picture> picturelist=pictureService.seletePicturesByhId(hId);
        return  picturelist;

    }
}
