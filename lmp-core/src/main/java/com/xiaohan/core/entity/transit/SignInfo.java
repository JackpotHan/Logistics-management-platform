package com.xiaohan.core.entity.transit;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: 签收信息
 */
@Table(name = "T_SIGN_INFO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInfo {
    @Id
    @Column(name = "C_ID")
    private Integer id;

    @Column(name = "C_SIGN_NAME")
    private String signName;

    @Column(name = "C_SIGN_TIME")
    private Date signTime;

    @Column(name = "C_SIGN_TYPE")
    private String signType;

    @Column(name = "C_ERROR_REMARK")
    private String errorRemark;

    @Column(name = "C_DESCRIPTION")
    private String description; // 描述

}
