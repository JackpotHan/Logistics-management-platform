package com.jackpot.base.entity.transit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: 签收信息
 */
@Table(schema = "`lmp-transit",name = "t_sign_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInfo extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sign_name")
    private String signName;

    @Column(name = "gmt_sign")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtSign;

    @Column(name = "sign_type")
    private Integer signType;

    @Column(name = "error_remark")
    private String errorRemark;

    @Column(name = "description")
    private String description; // 描述

}
