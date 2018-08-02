package com.xiaohan.core.entity.base;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @description:收派标准
 */

@Table(name = "T_STANDARD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Standard {
    @Id
    @Column(name = "C_ID")
    private Integer id; // 主键
    @Column(name = "C_NAME")
    private String name; // 标准名称
    @Column(name = "C_MIN_WEIGHT")
    private Integer minWeight; // 最小重量
    @Column(name = "C_MAX_WEIGHT")
    private Integer maxWeight; // 最大重量
    @Column(name = "C_MIN_LENGTH")
    private Integer minLength; // 最小长度
    @Column(name = "C_MAX_LENGTH")
    private Integer maxLength; // 最大重量
    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operatingTime;// 操作时间
    @Column(name = "C_OPERATOR")
    private String operator; // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    private String operatingCompany; // 操作单位

}
