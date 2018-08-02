package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @description:子档案类，记录了档案分级后的子信息
 */
@Entity
@Table(name = "T_SUB_ARCHIVE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubArchive extends BaseObject {
	@Id
	@Column(name = "C_ID")
	private Integer id; // 主键
	@Column(name = "C_SUB_ARCHIVE_NAME")
	private String subArchiveName; // 子档名称
	@Column(name = "C_MNEMONIC_CODE")
	private String mnemonicCode; // 助记码
	@Column(name = "C_REMARK")
	private String remark; // 备注
	@Column(name = "C_MOTHBALLED")
	private Character mothballed; // 封存标志
	@Column(name = "C_OPERATING_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date operatingTime;// 操作时间
	@Column(name = "C_OPERATOR")
	private String operator; // 操作员
	@Column(name = "C_OPERATING_COMPANY")
	private String operatingCompany; // 操作单位
}
