package com.jackpotHan.entity.base;

import com.jackpotHan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @description:地域信息实体类，主要包含 省市区(县)
 */

@Table(schema = "`lmp-base`",name = "t_area")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "province")
    private String province; // 省

    @Column(name = "city")
    private String city; // 城市

    @Column(name = "district")
    private String district; // 区域

    @Column(name = "psot_code")
    private String postCode; // 邮编

    @Column(name = "city_code")
    private String cityCode; // 城市编码

    @Column(name = "short_code")
    private String shortCode; // 简码

}
