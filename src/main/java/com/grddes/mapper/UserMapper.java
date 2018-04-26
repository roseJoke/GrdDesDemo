package com.grddes.mapper;

import com.grddes.model.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByName(String username);

    int selectUserType(int id);

    int updatePwd(@Param("user_id") int user_id, @Param("pwd") String pwd);

    int backmaxid();

    User IsExistUserByName();

    List<User> SelectAllUser();

    List<User> selectUserByElements(@Param("user") User user);
}