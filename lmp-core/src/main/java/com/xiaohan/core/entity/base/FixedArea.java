package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @description:定区
 */
@Table(name = "T_FIXED_AREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedArea extends BaseObject {

    @Id
    @Column(name = "C_ID")
    private String id; // 主键
    @Column(name = "C_FIXED_AREA_NAME", unique = true)
    private String fixedAreaName; // 定区名称
    @Column(name = "C_FIXED_AREA_LEADER", unique = true)
    private String fixedAreaLeader;// 定区负责人
    @Column(name = "C_TELEPHONE")
    private String telephone;// 联系电话
    @Column(name = "C_COMPANY")
    private String company; // 所属单位
    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operatingTime;// 操作时间
    @Column(name = "C_OPERATOR")
    private String operator; // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    private String operatingCompany; // 操作单位


}
