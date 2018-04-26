package com.grddes.controller;


import com.grddes.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */


@RestController
@RequestMapping("/map")
public class MapController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    @Autowired
    MapService mapService;

    @GetMapping("/sele")
    public List name(String name) {

        List list = mapService.name(name);
        return list;
    }

    @GetMapping("/shen")
    public List name() {

        List list = mapService.getProvince();
        return list;
    }
}
