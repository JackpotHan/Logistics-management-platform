package com.jackpot.base.entity.transit;

import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 配送信息
 */
@Table(schema = "`lmp-transit",name = "t_delivery_info ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryInfo extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "courier_no")
    private String courierNo;

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "description")
    private String description; // 描述

}
