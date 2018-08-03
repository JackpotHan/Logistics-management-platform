package com.xiaohan.core.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaohan.base.BaseObject;
import lombok.*;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @description:收派时间管理
 */
@Table(schema = "`lmp-base`",name = "t_take_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TakeTime extends BaseObject {
	@Id
	@Column(name = "id")
	private Integer id; // 主键

	@Column(name = "name")
	private String name; // 收派时间名称

	@Column(name = "gmt_weekday_on")
	@JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
	private Date gmtWeekdayOn; // 正常上班时间

	@Column(name = "gmt_weekday_off")
	@JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
	private Date gmtWeekdayOff; // 正常下班时间

	@Column(name = "gmt_weekend_on")
	@JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
	private Date gmtWeekendOn; // 周末上班时间

	@Column(name = "gmt_weekend_off")
	@JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
	private Date gmtWeekendOff; // 周末下班时间

	@Column(name = "status")
	private Integer status; // 状态

	@Column(name = "company")
	private String company; // 所属公司

	@Column(name = "gmt_operate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date gmtOperate;// 操作时间

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "operate_company")
	private String operateCompany; // 操作单位

}
