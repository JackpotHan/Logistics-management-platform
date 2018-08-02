package com.xiaohan.core.entity.transit;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 配送信息
 */
@Table(name = "T_DELIVERY_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryInfo {

    @Id
    @Column(name = "C_ID")
    private Integer id;

    @Column(name = "C_COURIER_NUM")
    private String courierNum;

    @Column(name = "C_COURIER_NAME")
    private String courierName;

    @Column(name = "C_DESCRIPTION")
    private String description; // 描述

}
