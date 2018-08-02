package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description: 档案类，记录所有的分类信息，在子档中
 */
@Table(name = "T_ARCHIVE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Archive extends BaseObject {
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id; // 主键
	@Column(name = "C_ARCHIVE_NUM", unique = true )
	private String archiveNum;// 档案编号
	@Column(name = "C_ARCHIVE_NAME")
	private String archiveName; // 档案名称
	@Column(name = "C_REMARK")
	private String remark; // 备注
	@Column(name = "C_HASCHILD")
	private Integer hasChild;// 是否分级 0代表不分级 1代表分级
	@Column(name = "C_OPERATING_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date operatingTime;// 操作时间
	@Column(name = "C_OPERATOR")
	private String operator; // 操作员
	@Column(name = "C_OPERATING_COMPANY")
	private String operatingCompany; // 操作单位

}
