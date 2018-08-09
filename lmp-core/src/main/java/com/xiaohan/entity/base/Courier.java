package com.xiaohan.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:快递员
 */
@Table(schema = "`lmp-base`",name = "t_courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id; // 主键

    @Column(name = "courier_no")
    private String courierNo; // 快递员工号

    @Column(name = "courier_name")
    private String courierName; // 快递员姓名

    @Column(name = "courier_mobile")
    private String courierMobile; // 快递员联系电话

    @Column(name = "pda")
    private String pda; // PDA号

    @Column(name = "deltag")
    private Integer deltag; // 作废标志 1 为标记作废

    @Column(name = "check_pwd")
    private String checkPwd; // 查台密码

    @Column(name = "type")
    private Integer type; // 取件员类型

    @Column(name = "company")
    private String company; // 单位

    @Column(name = "vehicle_type")
    private Integer vehicleType; // 车辆类型

    @Column(name = "vehicle_no")
    private String vehicleNo; // 车牌号

}
