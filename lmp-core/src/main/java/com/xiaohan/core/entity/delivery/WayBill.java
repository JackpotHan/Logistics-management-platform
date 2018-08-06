package com.xiaohan.core.entity.delivery;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @description:运单实体类
 */
@Table(schema = "`delivery`",name = "t_way_bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WayBill extends BaseObject {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "way_bill_no")
    private String wayBillNo; // 运单编号

    @Column(name = "order_no")
    private String order_no; // 订单编号

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
    private Integer payType;// 支付类型编号：寄付日结、寄付月结、到付

    @Column(name = "weight")
    private Double weight;// 托寄物重量

    @Column(name = "remark")
    private String remark; // 备注

    @Column(name = "arrive_city")
    private String arriveCity; // 到达地

    @Column(name = "feeitem_num")
    private Integer feeitemnum; // 实际件数

    @Column(name = "actlwei")
    private Double actlweit; // 实际重量

    @Column(name = "vol")
    private String vol; // 体积 输入格式为1*1*1*1，表示长*宽*高*数量

    @Column(name = "fload_req")
    private Integer floadreq; // 配载要求 (比如录入1=无，2=禁航，4=禁航空铁路)

    @Column(name = "way_bill_type")
    private Integer wayBillType; // 运单类型（类型包括：正常单据、异单、调单）
    /*
     * 运单状态： 1 待发货、 2 派送中、3 已签收、4 异常
     */
    @Column(name = "sign_status")
    private Integer signStatus; // 签收状态

    /*
     * 1、新增修改单据状态为“否” 2、作废时需将状态置为“是” 3、取消作废时需要将状态置为“否”
     */
    @Column(name = "del_tag")
    private String delTag; // 作废标志

}
