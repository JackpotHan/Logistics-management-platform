package com.xiaohan.core.entity.transit;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 运输配送信息
 */
@Table(name = "T_TRANSIT_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitInfo extends BaseObject {
    @Id
    @Column(name = "C_ID")
    private Integer id;

    @Column(name = "C_STATUS")
    // 出入库中转、到达网点、开始配置、正常签收、异常
    private String status;

    @Column(name = "C_OUTLET_ADDRESS")
    private String outletAddress;

}
