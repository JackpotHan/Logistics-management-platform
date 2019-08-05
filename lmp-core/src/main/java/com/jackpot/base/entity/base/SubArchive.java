package com.jackpot.base.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:子档案类，记录了档案分级后的子信息
 */
@Entity
@Table(schema = "`lmp-base`",name = "t_sub_archive")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubArchive extends BaseObject {
	@Id
	@Column(name = "id")
	private Long id; // 主键

	@Column(name = "sub_archive_name")
	private String subArchiveName; // 子档名称

	@Column(name = "mnemonic_code")
	private String mnemonicCode; // 助记码

	@Column(name = "remark")
	private String remark; // 备注

	@Column(name = "mothballed")
	private Integer mothballed; // 封存标志

	@Column(name = "gmt_operate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date gmtOperate;// 操作时间

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "operate_company")
	private String operateCompany; // 操作单位
}
