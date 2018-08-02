package com.xiaohan.core.entity.take_delivery;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:订单实体类
 */

@Table(name = "T_ORDER")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseObject {
    @Id
    @Column(name = "C_ID")
    private Integer id;// 主键
    @Column(name = "C_ORDER_NUM")
    private String orderNum;// 订单号

    @Column(name = "C_TELEPHONE")
    private String telephone; // 来电号码

    @Column(name = "C_CUSTOMER_ID")
    private Integer customer_id; // 客户编号

    @Column(name = "C_SEND_NAME")
    private String sendName; // 寄件人姓名
    @Column(name = "C_SEND_MOBILE")
    private String sendMobile;// 寄件人电话
    @Column(name = "C_SEND_COMPANY")
    private String sendCompany;// 寄件人公司

    @Column(name = "C_SEND_ADDRESS")
    private String sendAddress;// 寄件人详细地址信息

    @Column(name = "C_REC_NAME")
    private String recName;// 收件人姓名
    @Column(name = "C_REC_MOBILE")
    private String recMobile;// 收件人电话
    @Column(name = "C_REC_COMPANY")
    private String recCompany;// 收件人公司

    @Column(name = "C_REC_ADDRESS")
    private String recAddress;// 收件人详细地址信息

    @Column(name = "C_SEND_PRO_NUM")
    private String sendProNum; // 快递产品类型编号：速运当日、速运次日、速运隔日
    @Column(name = "C_GOODS_TYPE")
    private String goodsType;// 托寄物类型：文件、衣服 、食品、电子商品
    @Column(name = "C_PAY_TYPE_NUM")
    private String payTypeNum;// 支付类型编号：寄付日结、寄付月结、到付
    @Column(name = "C_WEIGHT")
    private Double weight;// 托寄物重量
    @Column(name = "C_REMARK")
    private String remark; // 备注

    @Column(name = "C_SEND_MOBILE_MSG")
    private String sendMobileMsg; // 给快递员捎话

    // 分单类型 1 自动分单 2 人工分单
    @Column(name = "C_ORDER_TYPE")
    private String orderType;

    // 订单状态 1 待取件 2 运输中 3 已签收 4 异常
    @Column(name = "C_STATUS")
    private String status;

    // 下单时间
    @Column(name = "C_ORDER_TIME")
    private Date orderTime;

    // 运单
    @OneToOne(mappedBy = "order")
    private WayBill wayBill;


}
