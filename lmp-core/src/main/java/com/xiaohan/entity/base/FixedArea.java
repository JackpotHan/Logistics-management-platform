package com.xiaohan.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:定区
 */
@Table(schema = "`lmp-base`",name = "t_fixed_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedArea extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id; // 主键

    @Column(name = "fixed_area_no")
    private  String fixedAreaNo; //定区编号

    @Column(name = "fixed_area_name")
    private String fixedAreaName; // 定区名称

    @Column(name = "fixed_area_leader")
    private String fixedAreaLeader;// 定区负责人

    @Column(name = "mobile")
    private String mobile;// 联系电话

    @Column(name = "company")
    private String company; // 所属单位

    @Column(name = "gmt_operate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtOperate;// 操作时间

    @Column(name = "operator")
    private String operator; // 操作员

    @Column(name = "operate_company")
    private String operateCompany; // 操作单位


}
