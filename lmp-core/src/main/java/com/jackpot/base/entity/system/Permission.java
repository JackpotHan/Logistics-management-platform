package com.jackpot.base.entity.system;

import com.google.common.collect.Lists;
import com.jackpot.base.base.BaseObject;
import com.jackpot.base.base.BaseUtil;
import lombok.*;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

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

	@Column(name = "sort")
	private Integer sort;	//排序

	@Transient
	private List<Integer> permissionIds = Lists.newArrayList();

	@Transient
	private List<Integer> levelList = Lists.newArrayList();

	@Transient
	private List<Integer> statusList = Lists.newArrayList();

	@Transient
	private List<String> categories = Lists.newArrayList();


	/**
	 * Reason: 根据数据组装成Criteria对象.
	 *
	 * @return
	 */
	public Example getPermissionCriteria() {
		Example example = new Example(Permission.class);
		Example.Criteria permissionCriteria = example.createCriteria();

		if (BaseUtil.isNotEmpty(name)) {
			permissionCriteria.andEqualTo("name",name);
		}
		if (BaseUtil.isNotEmpty(value)) {
			permissionCriteria.andEqualTo("value",value);
		}
		if (BaseUtil.isNotEmpty(path)) {
			permissionCriteria.andEqualTo("path",path);
		}
		if (BaseUtil.isNotEmpty(status)) {
			permissionCriteria.andEqualTo("status",status);
		}
		if (BaseUtil.isNotEmpty(statusList)) {
			permissionCriteria.andIn("status",statusList);
		}
		if (BaseUtil.isNotEmpty(level)) {
			permissionCriteria.andEqualTo("level",level);
		}
		if (BaseUtil.isNotEmpty(levelList)) {
			permissionCriteria.andEqualTo("level",levelList);
		}
		if (BaseUtil.isNotEmpty(category)) {
			permissionCriteria.andEqualTo("category",category);
		}
		if (BaseUtil.isNotEmpty(categories)) {
			permissionCriteria.andIn("category",categories);
		}
		if (BaseUtil.isNotEmpty(type)){
			permissionCriteria.andEqualTo("type",type);
		}
		if (BaseUtil.isNotEmpty(permissionIds)) {
			permissionCriteria.andIn("id",permissionIds);
		}
		return example;
	}


}

