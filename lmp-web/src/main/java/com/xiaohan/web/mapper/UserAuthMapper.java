package com.xiaohan.web.mapper;

import com.xiaohan.entity.system.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth> {

   @Select("SELECT f.*\n" +
           "from\n" +
           "(SELECT\n" +
           "\t`u`.`id` AS `id`,\n" +
           "\t`u`.`account_name` AS `accountName`,\n" +
           "\t`u`.`email` AS `email`,\n" +
           "\t`u`.`mobile` AS `mobile`,\n" +
           "\t`r`.`name` AS `roleName`,\n" +
           "\t`r`.`code` AS `roleCode`,\n" +
           "\t`p`.`name` AS `permissionName`,\n" +
           "\t`p`.`value` AS `permissionValue`,\n" +
           "\t`p`.`type` AS `permissionType`,\n" +
           "\t`p`.`level` AS `permissionLevel`,\n" +
           "\t`p`.`category` AS `permissionCategory`\n" +
           "from\n" +
           "\t(\n" +
           "\t\t(\n" +
           "\t\t\t(\n" +
           "\t\t\t\t(\n" +
           "\t\t\t\t\t`user` `u`\n" +
           "\t\t\t\t\tJOIN `user_role` `ur`\n" +
           "\t\t\t\t)\n" +
           "\t\t\t\tJOIN `role` `r`\n" +
           "\t\t\t)\n" +
           "\t\t\tJOIN `role_permission` `rp`\n" +
           "\t\t)\n" +
           "\t\tJOIN `permission` `p`\n" +
           "\t)\n" +
           "WHERE\n" +
           "\t(  (u.id = ${userId}) AND\n" +
           "\t\t(`u`.`id` = `ur`.`user_id`)\n" +
           "\t\tAND (`ur`.`role_id` = `r`.`id`)\n" +
           "\t\tAND (\n" +
           "\t\t\t`ur`.`role_id` = `rp`.`role_id`\n" +
           "\t\t)\n" +
           "\t\tAND (\n" +
           "\t\t\t`rp`.`permission_id` = `p`.`id`\n" +
           "\t\t)\n" +
           "\t\tAND (`u`.`status` = 0)\n" +
           "\t\tAND (`r`.`status` = 0)\n" +
           "\t\tAND (`p`.`status` = 0)\n" +
           "\t)) f")
    List<UserAuth> selectUserAuth(@Param("userId") Integer userId);

}
