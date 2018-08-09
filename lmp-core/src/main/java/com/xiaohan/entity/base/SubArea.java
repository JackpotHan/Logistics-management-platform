package com.xiaohan.entity.base;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:分区
 */
@Table(schema = "`lmp-base`",name = "t_sub_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubArea extends BaseObject {

    @Id
    @Column(name = "id")
    private Long id;// 分区

    @Column(name = "start_no")
    private String startNo; // 起始号

    @Column(name = "end_no")
    private String endNo; // 终止号

    @Column(name = "single")
    private String single; // 单双号

    @Column(name = "keyword")
    private String keyword; // 关键字

    @Column(name = "assist_keyword")
    private String assistKeyword; // 辅助关键字

}
