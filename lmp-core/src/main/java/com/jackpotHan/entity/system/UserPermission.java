package com.jackpotHan.entity.system;

import com.jackpotHan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission extends BaseObject {
	@Id
	@Column(name = "user_id")
	private Integer userId;//用户ID

	@Column(name = "permission_id")
	private Integer permissionId;//权限ID

	@Transient
	private String operatorId;

}

