package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:车辆
 */
@Table(name = "T_VEHICLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle extends BaseObject {

	@Id
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_ROUTE_TYPE")
	private String routeType; // 线路类型
	@Column(name = "C_ROUTE_NAME")
	private String routeName; // 线路名称
	@Column(name = "C_SNIPPER")
	private String shipper; // 承运商
	@Column(name = "C_DRIVER")
	private String driver; // 司机
	@Column(name = "C_VEHICLE_NUM")
	private String vehicleNum; // 车牌号
	@Column(name = "C_TELEPHONE")
	private String telephone; // 电话
	@Column(name = "C_VEHICLE_TYPE")
	private String vehicleType;// 车型
	@Column(name = "C_TON")
	private Integer ton; // 吨控
	@Column(name = "C_REMARK")
	private String remark;// 备注

}
