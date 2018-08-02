package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @description:地域信息实体类，主要包含 省市区(县)
 */

@Table(name = "T_AREA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area extends BaseObject {

    @Id
    @Column(name = "C_ID")
    private String id;
    @Column(name = "C_PROVINCE")
    private String province; // 省
    @Column(name = "C_CITY")
    private String city; // 城市
    @Column(name = "C_DISTRICT")
    private String district; // 区域
    @Column(name = "C_POSTCODE")
    private String postcode; // 邮编
    @Column(name = "C_CITYCODE")
    private String citycode; // 城市编码
    @Column(name = "C_SHORTCODE")
    private String shortcode; // 简码

}
