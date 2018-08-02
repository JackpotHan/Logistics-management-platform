package com.xiaohan.core.entity.base;

import javax.persistence.*;

import com.xiaohan.base.BaseObject;
import lombok.*;

/**
 * @description:快递员
 */
@Table(name = "T_COURIER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier extends BaseObject {

    @Id
    @Column(name = "C_ID")
    private Integer id; // 主键
    @Column(name = "C_COURIER_NUM", unique = true)
    private String courierNum; // 快递员工号
    @Column(name = "C_NAME")
    private String name; // 快递员姓名
    @Column(name = "C_TELEPHONE")
    private String telephone; // 快递员联系电话
    @Column(name = "C_PDA")
    private String pda; // PDA号
    @Column(name = "C_DELTAG")
    private Character deltag; // 作废标志 1 为标记作废
    @Column(name = "C_CHECK_PWD")
    private String checkPwd; // 查台密码
    @Column(name = "C_TYPE")
    private String type; // 取件员类型
    @Column(name = "C_COMPANY")
    private String company; // 单位
    @Column(name = "C_VEHICLE_TYPE")
    private String vehicleType; // 车辆类型
    @Column(name = "C_VEHICLE_NUM")
    private String vehicleNum; // 车牌号

}
