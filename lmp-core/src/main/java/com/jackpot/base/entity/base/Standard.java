package com.jackpot.base.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:收派标准
 */

@Table(schema = "`lmp-base`",name = "t_standard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Standard extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id; // 主键

    @Column(name = "standard_name")
    private String standardName; // 标准名称

    @Column(name = "min_weight")
    private Double minWeight; // 最小重量

    @Column(name = "max_weight")
    private Double maxWeight; // 最大重量

    @Column(name = "min_length")
    private Double minLength; // 最小长度

    @Column(name = "max_length")
    private Double maxLength; // 最大长度

    @Column(name = "gmt_operate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtOperate;// 操作时间

    @Column(name = "operator")
    private String operator; // 操作员

    @Column(name = "operate_company")
    private String operateCompany; // 操作单位

}
