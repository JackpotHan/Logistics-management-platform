package com.xiaohan.core.entity.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


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

}

