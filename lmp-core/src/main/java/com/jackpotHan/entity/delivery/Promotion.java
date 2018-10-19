package com.jackpotHan.entity.delivery;

import com.jackpotHan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:促销信息实体类
 */
@Table(schema = "`order`",name = "t_promotion")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promotion extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title; // 宣传概要(标题)

    @Column(name = "title_img")
    private String titleImg; // 宣传图片

    @Column(name = "active_scope")
    private String activeScope;// 活动范围

    @Column(name = "gmt_start")
    private Date gmtStart; // 发布时间

    @Column(name = "gmt_end")
    private Date gmtEnd; // 失效时间

    @Column(name = "gmt_update")
    private Date gmtUpdate; // 更新时间

    @Column(name = "operator")
    private String operator;// 更新人

    @Column(name = "update_company")
    private String updateCompany; // 更新单位

    @Column(name = "status")
    private String status ; // 状态 可取值：1.进行中(默认) 2. 已结束

    @Column(name = "description")
    private String description; // 宣传内容(活动描述信息)

}
