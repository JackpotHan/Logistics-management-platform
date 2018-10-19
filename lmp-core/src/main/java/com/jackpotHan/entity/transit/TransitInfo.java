package com.jackpotHan.entity.transit;

import com.jackpotHan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 运输配送信息
 */
@Table(schema = "`lmp-transit", name = "t_transit_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitInfo extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    // 出入库中转、到达网点、开始配置、正常签收、异常
    private String status;

    @Column(name = "outlet_address")
    private String outletAddress;   //发往地

}
