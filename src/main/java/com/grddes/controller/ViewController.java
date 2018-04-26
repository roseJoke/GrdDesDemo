package com.grddes.controller;


import com.grddes.service.HouseService;
import com.grddes.service.UserService;
import com.grddes.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */

@Controller
@RequestMapping
public class ViewController {

    @Autowired
    WorkService workService;

    @Autowired
    HouseService houseService;

    @Autowired
    UserService userService;

    ModelAndView mv;

    @RequestMapping(value={"/","/index"})
    @ResponseBody
    public ModelAndView showpg(){
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }



    @RequestMapping(value="/pgtop")
    @ResponseBody
    public ModelAndView patop(){
        mv=new ModelAndView("PageTop");
        return mv;
    }


   /* @RequestMapping(value="/sehouse")
    @ResponseBody
    public ModelAndView sehouse(){
        mv=new ModelAndView("seclecthouse");
        return mv;
    }*/

    @RequestMapping(value="/sework")
    @ResponseBody
    public ModelAndView sework(){
        mv=new ModelAndView("seclectwork");
        return mv;
    }


    @RequestMapping(value="/adwork")
    @ResponseBody
    public ModelAndView adwork(HttpServletRequest request, HttpSession session){
        if(session.getAttribute("user")==null){
            mv=new ModelAndView("nologin");
            mv.addObject("msg","未检测到您的登录信息，请先登录!");
        }else {
            int id1 = workService.backMaxId() + 1;
            request.setAttribute("id", id1);
            mv = new ModelAndView("addwork");
        }
        return mv;
    }

    @RequestMapping(value="/adhouse")
    @ResponseBody
    public ModelAndView adhouse(HttpServletRequest request,HttpSession session){
        if(session.getAttribute("user")==null) {
            mv=new ModelAndView("nologin");
            mv.addObject("msg","未检测到您的登录信息，请先登录!");

        }else{
            int id = houseService.backMaxId() + 1;
            request.setAttribute("id", id);
            mv = new ModelAndView("addhouse");
        }
        return mv;
    }

    /**
     * update 密码
     * @return
     */
    @RequestMapping(value="/uppassword")
    @ResponseBody
    public ModelAndView updatepassword(){
        ModelAndView mv=new ModelAndView("updatePassword");
        return mv;
    }

    /**
     *  返回注册视图
     * @return
     */
    @RequestMapping(value="/register")
    @ResponseBody
    public ModelAndView register(){
        ModelAndView mv=new ModelAndView("register");
        return mv;
    }

    @RequestMapping(value="/login")
    @ResponseBody
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }

    /**
     * 返回地址视图
     * @return
     */
    @RequestMapping(value="/backaddress")
    @ResponseBody
    public ModelAndView sec(){
        ModelAndView mv=new ModelAndView("addressSec");
        return mv;
    }

    @RequestMapping(value="/upload")
    @ResponseBody
    public ModelAndView addwork(){
        ModelAndView mv=new ModelAndView("fileupload");
        return mv;
    }


    /**
     *  管理页面跳转
     * @return
     */
    @RequestMapping(value="/m_index")
    @ResponseBody
    public ModelAndView mindex(){
        ModelAndView mv=new ModelAndView("m_index");
        return mv;
    }
    @RequestMapping(value="/m_adduser")
    @ResponseBody
    public ModelAndView mindex1(){
        int max_id = userService.backMaxId() + 1;
        ModelAndView mv=new ModelAndView("m_adduser");
        mv.addObject("max_id",max_id);
        return mv;
    }

    @RequestMapping(value="/m_userlist")
    @ResponseBody
    public ModelAndView mindex2(){
        ModelAndView mv=new ModelAndView("m_userlist");
        return mv;
    }

    @RequestMapping(value="/m_houselist")
    @ResponseBody
    public ModelAndView mindex3(){
        ModelAndView mv=new ModelAndView("m_houselist");
        return mv;
    }

    @RequestMapping(value="/m_worklist")
    @ResponseBody
    public ModelAndView mindex4(){
        ModelAndView mv=new ModelAndView("m_worklist");
        return mv;
    }

    @RequestMapping(value="/mywork")
    @ResponseBody
    public ModelAndView mindex4d(){
        ModelAndView mv=new ModelAndView("mywork");
        return mv;
    }

    @RequestMapping(value="/myhouse")
    @ResponseBody
    public ModelAndView mindex4dd(){
       mv=new ModelAndView("myhouse");
        return mv;
    }
    /*
    * WebSocket
    *
    * */
    @RequestMapping(value="/websocket")
    @ResponseBody
    public ModelAndView mindex4ddd(){
        mv=new ModelAndView("websocket/chat_index");
        return mv;
    }
}
