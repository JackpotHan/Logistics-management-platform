package com.xiaohan.core.entity.take_delivery;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:促销信息实体类
 */
@Table(name = "T_PROMOTION")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promotion extends BaseObject {

    @Id
    @Column(name = "C_ID")
    private Integer id;
    @Column(name = "C_TITLE")
    private String title; // 宣传概要(标题)
    @Column(name = "C_TITLE_IMG")
    private String titleImg; // 宣传图片
    @Column(name = "C_ACTIVE_SCOPE")
    private String activeScope;// 活动范围
    @Column(name = "C_START_DATE")
    private Date startDate; // 发布时间
    @Column(name = "C_END_DATE")
    private Date endDate; // 失效时间
    @Column(name = "C_UPDATE_TIME")
    private Date updateTime; // 更新时间
    @Column(name = "C_UPDATE_UNIT")
    private String updateUnit; // 更新单位
    @Column(name = "C_UPDATE_USER")
    private String updateUser;// 更新人 后续与后台用户关联
    @Column(name = "C_STATUS")
    private String status = "1"; // 状态 可取值：1.进行中 2. 已结束
    @Column(name = "C_DESCRIPTION")
    private String description; // 宣传内容(活动描述信息)

}
