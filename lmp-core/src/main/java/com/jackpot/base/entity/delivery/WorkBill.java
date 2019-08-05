package com.jackpot.base.entity.delivery;

import com.jackpot.base.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description:工单
 */
@Table(schema = "`delivery`",name = "t_work_bill")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkBill extends BaseObject {
    @Id
    @Column(name = "id")
    private Integer id; // 主键

    @Column(name = "type")
    private Integer type; // 工单类型 新,追,销

    /*
     * 新单:没有确认货物状态的 已通知:自动下单下发短信 已确认:接到短信,回复收信确认信息 已取件:已经取件成功,发回确认信息 生成工作单 已取消:销单
     * 
     */
    @Column(name = "pick_state")
    private Integer pickState; // 取件状态

    @Column(name = "gmt_create")
    private Date gmtCreate; // 工单生成时间

    @Column(name = "attach_bill_times")
    private Integer attachBillTimes; // 追单次数

    @Column(name = "remark")
    private String remark; // 订单备注

    @Column(name = "sms_number")
    private String smsNumber; // 短信序号

    @Column(name = "order_no")
    private String orderNo; // 订单号

}
