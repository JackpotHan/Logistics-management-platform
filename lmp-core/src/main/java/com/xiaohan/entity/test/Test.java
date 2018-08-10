package com.xiaohan.entity.test;

import com.xiaohan.base.BaseObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @Author: Hanjt
 * @Date: 2018/8/10 17:09
 * @Description:
 */
@Table(schema = "`lmp-test`",name = "t_test")
@Setter
@Getter
@Builder
@ToString
public class Test extends BaseObject {
    private Long id;

    private String userName;

    private Integer age;

    private Double grade;

}
