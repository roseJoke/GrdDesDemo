package com.grddes.mapper;

import com.grddes.model.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(Integer wId);

    int insert(Work record);

    int insertSelective(Work record);

    Work selectByPrimaryKey(Integer wId);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);

    int deleteByUserId(int userid);

    Work selectWorkByWorkId(int workid);

    List<Work> selectWorkSelective(@Param("work") Work work);

    List<Work> selectByUserId(int userid);

    int backmaxId();

    int deleteAllStatefalse();

    void changeStateByworkid( @Param("workid") int workid, @Param("state") Boolean state);

    List<Work> ListAllHouses();
}