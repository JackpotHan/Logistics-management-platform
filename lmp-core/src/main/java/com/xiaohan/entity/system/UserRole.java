package com.xiaohan.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(schema = "`system`",name = "user_role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseObject {
	@Id
	@Column(name = "user_id")
	private Integer userId;//用户ID

	@Column(name = "role_id")
	private Integer roleId;//角色ID

	@Transient
	private Integer roleStatus;

}

