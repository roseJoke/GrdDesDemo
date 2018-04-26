package com.grddes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/4/9  14:38
 */
@RestController
public class UploadController {
    ModelAndView mv;

    @RequestMapping("/uploadfile")
    public ModelAndView recivephotofile(@RequestParam("files[]") MultipartFile[] files) {
        System.out.println(files.toString());
        System.out.println(files.length);
        File file = new File("F://savePicture//");
        OutputStream osw = null;
        try {
            for (int i = 0; i < files.length; i++) {
                osw = new FileOutputStream(new File(file, "abc" + i + ".jpg"));
                byte[] buff = files[i].getBytes();
                osw.write(buff);
                osw.flush();
                osw.close();
                osw = null;
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        }finally {

            try {
                osw.flush();
                osw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        mv = new ModelAndView("success");
        return mv;
    }
}
