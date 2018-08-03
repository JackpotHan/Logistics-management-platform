package com.xiaohan.core.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description: 档案类，记录所有的分类信息，在子档中
 */
@Table(schema = "`lmp-base`",name = "t_archive")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Archive extends BaseObject {
	@Id
	@Column(name = "id")
	private Long id; // 主键

	@Column(name = "archive_no")
	private String archiveNo;// 档案编号

	@Column(name = "archive_name")
	private String archiveName; // 档案名称

	@Column(name = "remark")
	private String remark; // 备注

	@Column(name = "has_child")
	private Integer hasChild;// 是否分级 0代表不分级 1代表分级

	@Column(name = "gmt_operate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtOperate;// 操作时间

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "operate_company")
	private String operateCompany; // 操作单位

}
