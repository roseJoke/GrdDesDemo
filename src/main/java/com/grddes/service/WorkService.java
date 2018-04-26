package com.grddes.service;

import com.github.pagehelper.PageHelper;
import com.grddes.mapper.WorkMapper;
import com.grddes.model.Work;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/3/14  10:36
 */
@Service
public class WorkService {

    @Autowired
    WorkMapper workMapper;

    public int addWork(Work work) {
        return workMapper.insert(work);
    }



    public int updateById(Work work) {
        return workMapper.updateByPrimaryKey(work);
    }


    public List<Work> selectWorkSelective(Work work) {
       return workMapper.selectWorkSelective(work);

    }

    public List<Work> selectByUserId(int userid) {

        return workMapper.selectByUserId(userid);

    }

    public int backMaxId(){
        return workMapper.backmaxId();

    }

    public int deleteAllStatefalse() {
        return workMapper.deleteAllStatefalse();
    }

    public int deleteByUserId(int userid) {
        return workMapper.deleteByUserId(userid);
    }


    public int deleteWorkById(int workid) {
        return workMapper.deleteByPrimaryKey(workid);
    }

    public Work selectWorkByWorkId(int workid) {
        return workMapper.selectWorkByWorkId(workid);
    }

    public void changeStateByworkid(int workid, Boolean state) {
        workMapper.changeStateByworkid(workid,state);
    }

    public List<Work> ListAllHouses() {

        return workMapper.ListAllHouses();
    }
}
