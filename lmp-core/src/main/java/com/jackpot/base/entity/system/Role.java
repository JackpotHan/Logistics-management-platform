package com.jackpot.base.entity.system;


import com.fasterxml.jackson.annotation.JsonFormat;
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


@Table(schema = "`system`",name = "t_role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseObject {
	@Id
	@Column(name = "id")
	private Integer id;//

	@Column(name = "name")
	private String name;//角色名称

	@Column(name = "code")
	private String code;//角色代码

	@Column(name = "status")
	private Integer status;//0正常1停用

	@Column(name = "description")
	private String description;//描述

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(name = "gmt_operate")
	private Date gmtOperate;//创建时间

	@Column(name = "operator")
	private String operator;//操作者

	@Transient
	private List<Integer> roleIds = Lists.newArrayList();

	@Transient
	private List<Integer> statusList = Lists.newArrayList();

	/**
	 * Reason: 根据数据组装成Criteria对象.
	 *
	 * @return
	 */
	public Example getRoleCriteria() {
		Example example = new Example(Role.class);
		Example.Criteria roleCriteria = example.createCriteria();

		if (BaseUtil.isNotEmpty(name)) {
			roleCriteria.andEqualTo("name",name);
		}
		if (BaseUtil.isNotEmpty(code)) {
			roleCriteria.andEqualTo("code",code);
		}
		if (BaseUtil.isNotEmpty(status)) {
			roleCriteria.andEqualTo("status",status);
		}
		if (BaseUtil.isNotEmpty(statusList)) {
			roleCriteria.andIn("status",statusList);
		}
		if (BaseUtil.isNotEmpty(roleIds)) {
			roleCriteria.andIn("id",roleIds);
		}
		return example;
	}

}

