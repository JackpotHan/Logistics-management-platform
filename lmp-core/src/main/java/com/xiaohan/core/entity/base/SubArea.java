package com.xiaohan.core.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @description:分区
 */
@Table(name = "T_SUB_AREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubArea extends BaseObject {

    @Id
    @Column(name = "C_ID")
    private String id;// 分区
    @Column(name = "C_START_NUM")
    private String startNum; // 起始号
    @Column(name = "C_ENDNUM")
    private String endNum; // 终止号
    @Column(name = "C_SINGLE")
    private String single; // 单双号
    @Column(name = "C_KEY_WORDS")
    private String keyWords; // 关键字
    @Column(name = "C_ASSIST_KEY_WORDS")
    private String assistKeyWords; // 辅助关键字

}
