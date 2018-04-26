package com.grddes.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grddes.model.House;
import com.grddes.model.Picture;
import com.grddes.model.User;
import com.grddes.service.HouseService;

import com.grddes.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.grddes.model.User.Manger;
/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 *
 *  bug : 组合查询问题很大
 */

@Controller
public class HouseController {

    ModelAndView mv;
    @Autowired
    PictureService pictureService;

    @Autowired
    private HouseService houseService;

    /**
     * 插入实体house
     *
     * @param hou
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addhouse", produces = {"application/json;charset=UTF-8"})
    public ModelAndView addHouseImg(@RequestParam("files[]") MultipartFile[] files, House hou, HttpSession session) {

        User user = (User) session.getAttribute("user");
        hou.setState(true);
        hou.setuId(user.getuId());
        houseService.addhouse(hou);


        String path = new Object() {
            public String getPath() {
                return this.getClass().getResource("/").getPath() + "static/img/";
            }
        }.getPath();

        File file = new File(path);
        OutputStream osw = null;
        String PicUrl;
        try {
            for (int i = 0; i < files.length; i++) {
                PicUrl = hou.getuId() + "" + hou.gethId() + hou.getHostname() + i + ".jpg";
                osw = new FileOutputStream(new File(file, PicUrl));
                byte[] buff = files[i].getBytes();

                int picMaxID = pictureService.backMaxid() + 1;
                Picture picture = new Picture(picMaxID, PicUrl, hou.gethId());
                pictureService.SavePic(picture);
                osw.write(buff);
                osw.flush();
                osw.close();
                osw = null;
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        }

        mv = new ModelAndView("success");
        mv.addObject("msg", "新增一条房源");
        return mv;
    }

    /**
     * 更新房屋实体
     *
     * @param hou
     * @return
     */
    @RequestMapping(value = "/updatehouse")
    public ModelAndView update(House hou) {

        int num = houseService.updatehouse(hou);
        mv.setViewName("listhouse");
        return mv;
    }

    /**
     * 根据hid查询某个实体
     *
     * @param hid
     * @return
     */
    @RequestMapping(value = "/selectHouseByHId")
    public ModelAndView selectHouse(int hid, HttpServletRequest request) {
        House house = houseService.selectHouse(hid);
        request.setAttribute("user", house);
        mv.setViewName("");
        return mv;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseController.class);

    /**
     * 根据条件查询（组合条件查询）
     *
     * @return
     */
    @RequestMapping(value = "/selectHouseByelments")
    public ModelAndView seleteByElement(House house, Double maxarea, Double maxprice, Integer pageNum, HttpSession session) {
        LOGGER.info(house.toString());
        LOGGER.info("页码：{}", pageNum);
        if (session.getAttribute("user") == null) {
            mv = new ModelAndView("nologin");
            mv.addObject("msg", "未检测到您的登录信息，请先登录!");

        } else {
            User user = (User) session.getAttribute("user");
            if (user.getuType() >= Manger) {
                Page<?> page = PageHelper.startPage(pageNum, 8);
                List<House> houselist = houseService.seleteByElements(house, maxarea, maxprice);
                PageInfo<?> pagehelper = page.toPageInfo();
                mv = new ModelAndView("m_houselist");
                mv.addObject("houselist", houselist);
                mv.addObject("pagehelper", pagehelper);
            } else {
                Page<?> page = PageHelper.startPage(pageNum, 8);
                List<House> houselist = houseService.seleteByElements(house, maxarea, maxprice);
                List<Picture> pictureList = pictureService.seletePicture();
                PageInfo<?> pagehelper = page.toPageInfo();
                System.out.println(pictureList.toString());
                mv = new ModelAndView("seclecthouse");
                mv.addObject("pictureList", pictureList);
                mv.addObject("houselist", houselist);
                mv.addObject("pagehelper", pagehelper);
            }
        }
        return mv;
    }

    /**
     * 根据user_id查询某个user的所有房子
     *
     * @param
     * @return
     */
    @RequestMapping("/selectHousesByUserID")
    public ModelAndView selectHousesByUserID(int pageNum, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userid = user.getuId();

        Page<?> page = PageHelper.startPage(pageNum, 8);
        List<House> houselist = houseService.selectHousesByUserID(userid);
        PageInfo<?> pagehelper = page.toPageInfo();
        mv = new ModelAndView("myhouse");
        mv.addObject("houselist", houselist);
        mv.addObject("pagehelper", pagehelper);
        return mv;
    }

    /**
     * 根据id删除房子信息
     *
     * @param hId
     * @return
     */
    @RequestMapping("/deleteHouseById")
    public ModelAndView deleteHouse(int hId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getuType() >= Manger) {

            int num = houseService.deleteHouse(hId);
            mv = new ModelAndView("m_success");
            mv.addObject("msg", "删除" + num + "条数据");
        } else {

            int num = houseService.deleteHouse(hId);
            mv = new ModelAndView("success");
            mv.addObject("msg", "删除" + num + "条数据");
        }
        return mv;
    }

    /**
     * 根据user_id 删除 某个user的所有房屋信息
     *
     * @param userid
     * @return
     */
    @RequestMapping("/deleteHouseByUId")
    public int deleteHouses(int userid) {
        return houseService.deleteHouses(userid);
    }

    /**
     * 根据hid改变房屋状态
     *
     * @param hid
     * @return
     */
    @RequestMapping(value = "/changeHouseState")
    public ModelAndView changeHouseState(int hId, Boolean state, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getuType() >= Manger) {
            houseService.changeHouseState(hId, state);
            mv = new ModelAndView("m_success");
            mv.addObject("msg", "房屋状态改变！");
        } else {
            houseService.changeHouseState(hId, state);
            mv = new ModelAndView("success");
            mv.addObject("msg", "房屋状态改变！");
        }
        return mv;
    }


}
