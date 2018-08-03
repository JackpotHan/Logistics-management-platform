package com.xiaohan.core.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(schema = "`system`",name = "t_permission")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseObject {
	@Id
	@Column(name = "id")
	private Integer id;//

	@Column(name = "name")
	private String name;//权限名称

	@Column(name = "value")
	private String value;//权限值

	@Column(name = "path")
	private String path;//对应方法路径

	@Column(name = "type")
	private Integer type;//0通用权限1菜单控制权限2系统功能控制权限

	@Column(name = "level")
	private Integer level;//权限等级0普通1重要

	@Column(name = "status")
	private Integer status;//0正常1停用

	@Column(name = "category")
	private String category;//类别

	@Column(name = "description")
	private String description;//描述

	@Column(name = "gmt_operate")
	private Date gmtOperate;//创建时间

	@Column(name = "operator")
	private String operator;//创建人

	@Column(name = "parent_id")
	private Integer parentId;//上级权限 菜单控制权限有效

	@Column(name = "menu_path")
	private String menuPath;//菜单路径 菜单控制权限有效

	@Column(name = "menu_icon")
	private String menuIcon;//菜单图标 菜单控制权限时候有效

}

