package com.xiaohan.core.entity.transit;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 出入库信息
 */
@Table(name = "T_IN_OUT_STORAGE_INFO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InOutStorageInfo {
    @Id
    @Column(name = "C_ID")
    private Integer id;

    @Column(name = "C_OPERATION")
    private String operation; // 操作类型 ： 入库、出库、到达网点

    @Column(name = "C_ADDRESS")
    private String Address; // 仓库、网点 地址

    @Column(name = "C_DESCRIPTION")
    private String description; // 描述


}
