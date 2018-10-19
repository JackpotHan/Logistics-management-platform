package com.jackpotHan.entity.delivery;

import com.jackpotHan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:订单实体类
 */

@Table(schema = "`lmp-delivery`",name = "t_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id;// 主键

    @Column(name = "order_no")
    private String orderNo;// 订单号

    @Column(name = "mobile")
    private String mobile; // 手机号

    @Column(name = "customer_no")
    private Integer customerNo; // 客户编号

    @Column(name = "send_name")
    private String sendName; // 寄件人姓名

    @Column(name = "send_mobile")
    private String sendMobile;// 寄件人电话

    @Column(name = "send_address")
    private String sendAddress;// 寄件人详细地址信息

    @Column(name = "rec_name")
    private String recName;// 收件人姓名

    @Column(name = "rec_mobile")
    private String recMobile;// 收件人电话

    @Column(name = "rec_address")
    private String recAddress;// 收件人详细地址信息

    @Column(name = "send_pro_no")
    private Integer sendProNo; // 快递产品类型编号：速运当日、速运次日、速运隔日

    @Column(name = "goods_type")
    private Integer goodsType;// 托寄物类型：文件、衣服 、食品、电子商品

    @Column(name = "pay_type")
    private Integer payType;// 支付类型：寄付日结、寄付月结、到付

    @Column(name = "weight")
    private Double weight;// 托寄物重量

    @Column(name = "order_type")
    private Integer orderType; // 分单类型 1 自动分单 2 人工分单

    @Column(name = "status")
    private String status;    // 订单状态 1 待取件 2 运输中 3 已签收 4 异常

    @Column(name = "gmt_order")
    private Date gmtOrder;    // 下单时间

    @Column(name = "remark")
    private String remark; // 备注

}
