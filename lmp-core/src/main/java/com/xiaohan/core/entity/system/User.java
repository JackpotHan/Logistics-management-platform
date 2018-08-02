package com.xiaohan.core.entity.system;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:后台用户
 */
@Table(name = "T_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{

    @Id
    @Column(name = "C_ID")
    private int id; // 主键
    @Column(name = "C_BIRTHDAY")
    private Date birthday; // 生日
    @Column(name = "C_GENDER")
    private String gender; // 性别
    @Column(name = "C_PASSWORD")
    private String password; // 密码
    @Column(name = "C_REMARK")
    private String remark; // 备注
    @Column(name = "C_STATION")
    private String station; // 状态
    @Column(name = "C_TELEPHONE")
    private String telephone; // 联系电话
    @Column(name = "C_USERNAME", unique = true)
    private String username; // 登陆用户名
    @Column(name = "C_NICKNAME")
    private String nickname; // 真实姓名

}
