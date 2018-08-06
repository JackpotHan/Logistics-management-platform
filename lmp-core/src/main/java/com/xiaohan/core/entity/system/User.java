package com.xiaohan.core.entity.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(schema = "`system`",name = "t_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseObject {
	@Id
	@Column(name = "id")
	private Integer id;//主键

	@Column(name = "account_name")
	private String accountName;//登录名

	@Column(name = "nick_name")
	private String nickName;//昵称

	@Column(name = "password")
	private String password;//密码

	@Column(name = "salt")
	private String salt;//盐

	@Column(name = "real_name")
	private String realName;//真实姓名

	@Column(name = "type")
	private Integer type;//0普通员工,1部门管理员,2系统管理员

	@Column(name = "status")
	private Integer status;//0正常1停用2停职9离职

	@Column(name = "status_explain")
	private String statusExplain;//用户状态说明

	@Column(name = "organization")
	private String organization;//组织机构

	@Column(name = "description")
	private String description;//描述

	@Column(name = "email")
	private String email;//邮箱地址

	@Column(name = "qq")
	private String qq;//QQ即时通讯工具

	@Column(name = "mobile")
	private String mobile;//移动电话

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(name = "gmt_operate")
	private Date gmtOperate;//创建时间

	@Column(name = "operator")
	private String operator;//创建人

	@Transient
	private String checkCode;

}

