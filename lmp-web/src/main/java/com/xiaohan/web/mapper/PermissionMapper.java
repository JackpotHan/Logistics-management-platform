package com.xiaohan.web.mapper;

import com.xiaohan.entity.system.Permission;
import com.xiaohan.web.dto.PermissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT f.id,f.name,f.`value`,f.path,f.type,f.`level`,f.`status`,f.category,f.seq_no as seqNo,f.description,f.create_time as createTime,f.create_name as createName,f.update_time as updateTime,f.update_name as updateName,f.parent_id as parentId,f.menu_path as menuPath,f.menu_icon as menuIcon,f.product_code as productCode\n" +
            "from (\n" +
            "SELECT DISTINCT(d.permission_id)\n" +
            "from  (\n" +
            "SELECT b.id\n" +
            "from user_role a JOIN role b on a.user_id = ${userId} and a.role_id = b.id and b.`status`= ${roleStatus}) c join role_permission d on c.id = d.role_id) e join permission f on e.permission_id = f.id and f.`status` = ${permissionStatus} and f.type = ${permissionType}\n")
    List<Permission> selectUserMenuPermission(@Param("userId") Integer userId, @Param("roleStatus") Integer roleStatus, @Param("permissionStatus") Integer permissionStatus, @Param("permissionType") Integer permissionType);

    @Select("SELECT f.id,f.name,f.`value`,f.path,f.type,f.`level`,f.`status`,f.category,f.seq_no as seqNo,f.description,f.create_time as createTime,f.create_name as createName,f.update_time as updateTime,f.update_name as updateName,f.parent_id as parentId,f.menu_path as menuPath,f.menu_icon as menuIcon,f.product_code as productCode\n" +
            "from (\n" +
            "SELECT DISTINCT(d.permission_id)\n" +
            "from  (\n" +
            "SELECT b.id\n" +
            "from user_role a JOIN role b on a.user_id = ${userId} and a.role_id = b.id and b.`status`= ${roleStatus}) c join role_permission d on c.id = d.role_id) e join permission f on e.permission_id = f.id and f.`status` = ${permissionStatus}\n")
    List<Permission> selectUserPermission(@Param("userId") Integer userId, @Param("roleStatus") Integer roleStatus, @Param("permissionStatus") Integer permissionStatus);

    @Select("  SELECT  p.id,p.name,p.value,p.path,p.type,p.`level`,p.`status`,p.category,p.seq_no as seqNo,p.description,p.parent_id as parentId,p.menu_path as menuPath,p.menu_icon as menuIcon,p.create_time as createTime,p.create_name as createName,p.update_name as updateName,if(if(urp.id IS NOT NULL,2,0)=2,2,if(upp.id IS NOT NULL ,1,0)) AS 'selectType' from permission p\n" +
            "    LEFT JOIN (\n" +
            "        SELECT  p.* from  user u\n" +
            "        LEFT JOIN  user_role ur on u.id=ur.user_id\n" +
            "        LEFT JOIN  role r on r.id=ur.role_id AND r.status=0\n" +
            "        LEFT JOIN  role_permission rp on rp.role_id=r.id\n" +
            "        LEFT JOIN permission p on rp.permission_id=p.id\n" +
            "        WHERE  u.id=${userId}\n" +
            "    ) urp ON  urp.id=p.id\n" +
            "    LEFT JOIN (\n" +
            "        SELECT p.*\n" +
            "        from user u\n" +
            "          LEFT JOIN user_permission up ON u.id = up.user_id\n" +
            "          LEFT JOIN permission p ON up.permission_id = p.id\n" +
            "        WHERE u.id = ${userId}\n" +
            "    ) upp on upp.id=p.id\n" +
            "    WHERE p.status=0")
    List<PermissionDTO> getPermissionDTOs(@Param("userId") Integer userId);
}


