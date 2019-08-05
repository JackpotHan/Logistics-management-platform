package com.jackpot.base.entity.transit;

import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 出入库信息
 */
@Table(schema = "`lmp-transit",name = "t_in_out_storage_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InOutStorageInfo extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "operation")
    private Integer operation; // 操作类型 ： 入库、出库、到达网点

    @Column(name = "address")
    private String address; // 仓库、网点 地址

    @Column(name = "description")
    private String description; // 描述


}
