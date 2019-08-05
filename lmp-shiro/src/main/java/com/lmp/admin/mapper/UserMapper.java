package com.lmp.admin.mapper;

import com.lmp.admin.dto.UserDTO;
import com.lmp.admin.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User> {

    List<UserDTO> list(@Param("userDTO") UserDTO userDTO);
}