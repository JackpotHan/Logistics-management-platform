package com.xiaohan.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:车辆
 */
@Table(schema = "`lmp-base`",name = "t_vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle extends BaseObject {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "route_type")
	private Integer routeType; // 线路类型  枚举暂定

	@Column(name = "route_name")
	private String routeName; // 线路名称

	@Column(name = "shipper")
	private String shipper; // 承运商

	@Column(name = "driver")
	private String driver; // 司机

	@Column(name = "vehicle_no")
	private String vehicleNo; // 车牌号

	@Column(name = "mobile")
	private String mobile; // 电话

	@Column(name = "vehicle_type")
	private Integer vehicleType;// 车型  枚举暂定

	@Column(name = "ton")
	private Integer ton; // 吨控

	@Column(name = "remark")
	private String remark;// 备注

}
