package com.grddes.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grddes.model.User;
import com.grddes.service.HouseService;
import com.grddes.service.UserService;
import com.grddes.service.WorkService;
import com.grddes.utils.MD5Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.WatchService;
import java.util.List;

import static com.grddes.model.User.*;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/12  18:00
 */


@RestController
public class UserController {
    @Autowired
    UserService userService;


    User user;

    ModelAndView mv;

    /**
     * @param username
     * @param password
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request) throws IOException {

        user = userService.getUserByName(username);
        if (user == null) {
            mv = new ModelAndView("login");
            mv.addObject("msg", "您输入的用户名不存在！");
            return mv;
        } else if (!(username.equals(user.getUserName()) && MD5Util.MD5(password).equals(user.getPassWord()))) {

            mv = new ModelAndView("login");
            mv.addObject("msg", "用户名或密码错误，请重新登陆");
            return mv;
        } else {
            request.getSession().setAttribute("user", user);
            switch (user.getuType()) {
                case 0:
                    mv = new ModelAndView("index");        //普通用户
                    return mv;
                case 1:
                    mv = new ModelAndView("m_index");     //管理员
                    return mv;

                default:
                    mv = new ModelAndView("m_index");       //超管
                    return mv;
            }
        }
    }

    /**
     * 管理员添加用户
     *
     * @param
     * @return
     */
    @RequestMapping("/adduserManager")
    public ModelAndView adduserManager(User user) {

        String passWord = MD5Util.MD5("111111");
        user.setPassWord(passWord);
        if (userService.getUserByName(user.getUserName()) != null) {
            mv = new ModelAndView("m_adduser");
            mv.addObject("msg", "您添加的用户名已存在！");
            return mv;

        } else {
            userService.addUser(user);
            mv = new ModelAndView("m_success");
            mv.addObject("msg", "添加了一个用户！");
            return mv;

        }
    }

    /**
     * 普通用户注册（由数据库查询最大id）
     *
     * @param request
     * @return
     */
    @RequestMapping("/adduser")
    public ModelAndView Register(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        String username = request.getParameter("username");
        String password = MD5Util.MD5(request.getParameter("password1"));
        user = userService.getUserByName(username);
        if (user != null) {
            request.setAttribute("msg", "您输入的用户名已存在！");
            mv = new ModelAndView("register");

        } else {
            user = new User(id, username, password, User_m);
            userService.addUser(user);
            request.setAttribute("msg", "注册成功，请登录！");
            mv = new ModelAndView("success");
        }


        return mv;
    }

    /**
     * 管理用户删除用户
     *
     * @param uId
     * @return
     */
    @RequestMapping("/deleteone")
    public ModelAndView deleteById(int uId, HttpServletRequest request, HttpSession session) {

        if (((User) session.getAttribute("user")).getuType() == MaxManger) {

            userService.deleteOne(uId);
            request.setAttribute("msg", "恭喜你，干掉了那家伙。");
            mv = new ModelAndView("m_success");

        } else if (((User) session.getAttribute("user")).getuType() == Manger) {
            int type = userService.getUserType(uId);
            System.out.println(uId + type);
            if (type < 1) {
                userService.deleteOne(uId);
                request.setAttribute("msg", "恭喜你，干掉了那家伙。");
                mv = new ModelAndView("m_success");
            } else {
                request.setAttribute("msg", "兄弟不要嘚瑟，你权限不够！");
                mv = new ModelAndView("m_fail");
            }
        }
       /*续加判断页面跳转*/
        return mv;
    }


    /**
     * 更新密码
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/updatepassword")
    public ModelAndView updatepassword(HttpServletRequest request, HttpSession session) {

        int user_id = ((User) session.getAttribute("user")).getuId();

        String password = MD5Util.MD5(request.getParameter("fristpass"));

        userService.updatePwd(user_id, password);
        mv.addObject("msg", "密码修改成功，请重新登录！");
        mv.setViewName("success");                     //应该先跳成功页面再返回登陆
        session.invalidate();
        return mv;
    }

    /**
     * 注销登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        return new ModelAndView("index");
    }


    /**
     * pageHelper分页（参数pageNum,pageSize）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    /*@RequestMapping("/SelectAllUser")
    public ModelAndView SelectAllUser(int pageNum, int pageSize) {
        Page<?> page= PageHelper.startPage(pageNum,8);
        List<User> userlist = userService.SelectAllUser();
        PageInfo<?> pagehelper=page.toPageInfo();
        mv.addObject("userlist", userlist);
        mv.addObject("pagehelper",pagehelper);
        mv.setViewName("m_userlist");
        return mv;
    }*/

    /**
     * 返回当前id最大值
     *
     * @return
     */
    @RequestMapping("/backid")
    public int backMaxId(HttpServletRequest request) {
        return userService.backMaxId();
    }

    /**
     * 条件查询用户
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectUserByElements")
    public ModelAndView selectUserByElements(User user, int pageNum) {
        Page<?> page = PageHelper.startPage(pageNum, 8);
        List<User> userlist = userService.selectUserByElements(user);
        PageInfo<?> pagehelper = page.toPageInfo();
        mv = new ModelAndView("m_userlist");
        mv.addObject("userlist", userlist);
        mv.addObject("pagehelper", pagehelper);
        return mv;
    }
}



