package com.lmp.admin.model;

import com.jackpot.base.base.BaseObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_admin_user")
public class User extends BaseObject {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    @Column(name = "org_id")
    private Integer orgId; //机构ID

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 盐值
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 状态 0冻结 1正常
     */
    @Column(name = "status")
    private String status;

    /**
     * 操作人
     */
    @Column(name = "operate_user")
    private String operateUser;

    @Column(name = "type")
    private Integer type; //1: 管理员  2:虚拟合伙人

    /**
     * 创建管理员ID
     */
    @Column(name = "creator_id")
    private Integer creatorId;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}