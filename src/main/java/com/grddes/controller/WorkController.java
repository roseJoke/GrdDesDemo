package com.grddes.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grddes.model.User;
import com.grddes.model.Work;
import com.grddes.service.WorkService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.grddes.model.User.Manger;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/14  10:32
 */
@RestController
public class WorkController {

    @Autowired
    WorkService workService;

    ModelAndView mv;

    Work work;

    /**
     * 返回数据库最大id
     *
     * @return
     */
    @RequestMapping(value = "/maxId")
    public int backMaxId() {
        return workService.backMaxId();
    }

    /**
     * 添加一个工作实体（没测试）state 与user_id获取问题
     *
     * @param work
     * @param
     * @return
     */
    @RequestMapping(value = "/addwork")
    public ModelAndView addWork(Work work, HttpSession session) throws Exception {

        /* 当前时间*/
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /* session中获取用户uid*/
        User user = (User) session.getAttribute("user");

        work.setuId(user.getuId());
        work.setState(true);
        work.setReleasetime(df.format(day));
        int num = workService.addWork(work);
        mv = new ModelAndView("success");
        mv.addObject("msg", "添加了" + num + "条");
        return mv;
    }

    /**
     * 通过user_id删除相关实体
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/deleteByUId")
    public ModelAndView delete(int userid) {
        workService.deleteByUserId(userid);
        mv.setViewName("success");
        return mv;
    }

    /**
     * 通过实体id删除一个实体
     *
     * @param workid
     * @return
     */
    @RequestMapping(value = "/deleteByWorkid")
    public ModelAndView deleteByid(int workid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getuType() >= Manger) {
            workService.deleteWorkById(workid);
            mv = new ModelAndView("m_success");
            mv.addObject("msg", "您删除了一条数据！");
        } else {
            workService.deleteWorkById(workid);
            mv = new ModelAndView("success");
            mv.addObject("msg", "您删除了一条数据！");
        }

        return mv;
    }


    /**
     * 查找work实体，用于更新回填数据   与updateByworkid通用（updateById辅助方法）
     *
     * @param workid
     * @param request
     */
    @RequestMapping(value = "/getWorkBywid")
    public void getWorkBywid(int workid, HttpServletRequest request) {
        work = workService.selectWorkByWorkId(workid);
        request.setAttribute("work", work);
    }

    /**
     * 通过work id 来更新实体   (数据回填  需考虑数据库字段数)
     *
     * @param work
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateByWorkid")
    public ModelAndView updateById(Work work, HttpServletRequest request) {
        workService.updateById(work);
        mv.setViewName("success");
        return mv;
    }


    /**
     * 实体动态模糊查询（地址）work
     *
     * @param work
     * @return
     */
    @RequestMapping(value = "/selectWorkSelective")
    public ModelAndView selectByAddress(Work work, int pageNum, HttpSession session) {
        if (session.getAttribute("user") == null) {
            mv = new ModelAndView("nologin");
            mv.addObject("msg", "未检测到您的登录信息，请先登录!");

        } else {
            User user = (User) session.getAttribute("user");
            if (user.getuType() >= Manger) {
                Page<?> page = PageHelper.startPage(pageNum, 8);
                List<Work> worklist = workService.selectWorkSelective(work);
                PageInfo<?> pagehelper = page.toPageInfo();
                mv = new ModelAndView("m_worklist");
                mv.addObject("worklist", worklist);
                mv.addObject("pagehelper", pagehelper);
            } else {
                Page<?> page = PageHelper.startPage(pageNum, 8);
                List<Work> worklist = workService.selectWorkSelective(work);
                PageInfo<?> pagehelper = page.toPageInfo();
                mv = new ModelAndView("seclectwork");
                mv.addObject("worklist", worklist);
                mv.addObject("pagehelper", pagehelper);
            }
        }
        return mv;


    }

    /**
     * 查询某个用户的所有零工(用户查询自己发布的零工)
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectByUserId")
    public ModelAndView selectByUserId(HttpSession session, int pageNum) {

        int userid = ((User) (session.getAttribute("user"))).getuId();//获取session中uid的值作为参数
        Page<?> page = PageHelper.startPage(pageNum, 8);
        List<Work> worklist = workService.selectByUserId(userid);
        PageInfo<?> pagehelper = page.toPageInfo();
        mv = new ModelAndView("mywork");
        mv.addObject("worklist", worklist);
        mv.addObject("pagehelper", pagehelper);
        return mv;
    }

    /**
     * 管理用  删除所有状态失去的work
     *
     * @return
     */
    @RequestMapping(value = "/deleteAllStatefalse")
    public ModelAndView deleteAllStatefalse() {
        int num = workService.deleteAllStatefalse();
        mv = new ModelAndView("m_success");
        mv.addObject("msg", "清除了所有状态结束的零工");
        return mv;
    }

    /**
     * 改变零工状态
     *
     * @return
     */
    @RequestMapping(value = "/changeState")
    public ModelAndView changeState(int workid, Boolean state, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getuType() >= Manger) {
            workService.changeStateByworkid(workid, state);
            System.out.println("state");
            mv = new ModelAndView("m_success");
            mv.addObject("msg", "零工状态改变！");
        } else {
            workService.changeStateByworkid(workid, state);
            System.out.println("state");
            mv = new ModelAndView("success");
            mv.addObject("msg", "零工状态改变！");
        }
        return mv;
    }


    /*批量删除( 批量删除|批量修改状态 )自己的work*/


   /* @RequestMapping(value = "/ListAllWorks")*/
   /* public ModelAndView ListAllHouses(int pageNum, int pageSize, HttpSession session) {

        Page<?> page=PageHelper.startPage(pageNum, 8);
        List<Work> worklist = workService.ListAllHouses();
        PageInfo<?> pagehelper=page.toPageInfo();
        if (session.getAttribute("user") == null) {
            mv = new ModelAndView("nologin");
            mv.addObject("msg", "未检测到您的登录信息，请先登录!");

        } else {
            User user = (User) session.getAttribute("user");
            if (user.getuType() >= Manger) {
                mv = new ModelAndView("m_worklist");
                mv.addObject("worklist", worklist);
                mv.addObject("pagehelper",pagehelper);

            } else {
                mv = new ModelAndView("seclectwork");
                mv.addObject("worklist", worklist);
                mv.addObject("pagehelper",pagehelper);
            }
        }
        return mv;
    }*/

}
