//package com.jackpot.base.feign.client;
//
//import java.util.List;
//
//import com.zmkj.web.config.FeignConfiguration;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.alibaba.fastjson.JSONObject;
//import com.zmkj.core.entity.CardAuth;
//import com.zmkj.core.entity.CardDebit;
//import com.zmkj.core.entity.CardSass;
//import com.zmkj.core.entity.CardSubacct;
//import com.zmkj.core.entity.RightsVip;
//import com.zmkj.core.entity.UserInfo;
//import com.zmkj.core.entity.UserSecondAccount;
//import com.zmkj.core.entity.UserSecurity;
//import com.zmkj.core.vo.PassBannerVO;
//import com.zmkj.core.vo.UserCardVO;
//import com.zmkj.core.zm.ZmCode;
//import com.zmkj.core.zm.ZmResult;
//
///**
// * @author: houjf
// * @date: 2019/01/16
// */
//@FeignClient(value = "bb-service-customer", configuration = FeignConfiguration.class,fallback = CustomerClient.CustomerClientHystrix.class)
//public interface CustomerClient {
//
//    /**
//     * 查询用户当前业务支持的卡片
//     * @param userNo       系统会员号
//     * @param businessCode 业务编码
//     * @return
//     */
//    @RequestMapping("userCard/getUserCardBySupport")
//    ZmResult getUserCardBySupport(@RequestParam("userNo") String userNo,
//                                  @RequestParam("businessCode") String businessCode);
//
//    /**
//     * 根据用户编号查询用户信息
//     * @param userNo
//     * @return
//     */
//    @RequestMapping("appUser/getUserInfoByUserNo")
//    ZmResult<UserInfo> getUserInfoByUserNo(@RequestParam("userNo") String userNo);
//
//    /**
//     * 查询信用卡信息
//     * @param userNo
//     * @param cardNo
//     * @return
//     */
//    @RequestMapping("userCard/getCreditCard")
//    ZmResult getCreditCard(@RequestParam("userNo") String userNo,
//                           @RequestParam("cardNo") String cardNo);
//
//    /**
//     * 获取卡片鉴权信息
//     * @return
//     */
//    @RequestMapping("cardAuth/getUserCardAuth")
//    ZmResult<CardAuth> getCardAuth(@RequestParam("cardNo") String cardNo,
//                                   @RequestParam("channelType") Integer channelType);
//
//    /**
//     * 某业务下的银行信息
//     * @param bankCode
//     * @param businessCode
//     * @return
//     */
//    @RequestMapping(value = "bank/getBankBusiness")
//    ZmResult getBankBusiness(@RequestParam("bankCode") String bankCode,
//                             @RequestParam("businessCode") String businessCode);
//
//    /**
//     * 根据用户编号及认证编码查询认证信息
//     * @param userNo
//     * @param authCode
//     * @return
//     */
//    @RequestMapping("userAuth/getAuthInfoByAuthCode")
//    ZmResult getAuthInfoByAuthCode(@RequestParam("userNo") String userNo,
//                                   @RequestParam("authCode") String authCode);
//
//    @RequestMapping(value = "userSecondAccount/queryUserSecondAccount", method = RequestMethod.POST)
//    ZmResult<UserSecondAccount> queryUserSecondAccount(@RequestBody JSONObject json);
//
//    /**
//     * 根据用户编号及卡类型查询卡片信息及卡片鉴权
//     * @param userNo
//     * @return
//     */
//    @RequestMapping("userCard/getCardAndCardAuthByUserNo")
//    ZmResult<JSONObject> getCardAndCardAuthByUserNo(@RequestParam("userNo") String userNo,
//                                                    @RequestParam("cardType") String cardType);
//
//    /**
//     * 根据用户手机号查询用户编号
//     * @param mobile
//     * @return
//     */
//    @RequestMapping("appUser/getUserNoByMobile")
//    ZmResult getUserNoByMobile(@RequestParam("mobile") String mobile);
//
//    /**
//     * 根据用户编号查询用户token
//     * @param userNo
//     * @return
//     */
//    @RequestMapping("appUser/getTokenByUserNo")
//    ZmResult getTokenByUserNo(@RequestParam("userNo") String userNo);
//
//    /**
//     * 根据卡号查询卡片信息
//     * @param cardNo
//     * @return
//     */
//    @RequestMapping("userCard/getCardInfoByCardNo")
//    ZmResult<UserCardVO> getCardInfoByCardNo(@RequestParam("cardNo") String cardNo);
//
//    /**
//     * 根据用户编号及卡片类型查询所有卡片
//     * @param userNo
//     * @param cardType
//     * @return
//     */
//    @RequestMapping("userCard/getUserAllCards")
//    ZmResult<List<UserCardVO>> getUserAllCards(@RequestParam("userNo") String userNo,
//                                               @RequestParam("cardType") Integer cardType);
//
//    /**
//     * 获取账户储蓄卡信息
//     * @param userNo
//     * @param cardNo
//     * @return
//     */
//    @RequestMapping("userCard/getDebtCard")
//    ZmResult<CardDebit> getDebtCard(@RequestParam("userNo") String userNo,
//                                    @RequestParam("cardNo") String cardNo);
//
//    /**
//     * 获取用户储蓄卡主卡 -- 结算卡信息
//     * @param userNo
//     * @return
//     */
//    @RequestMapping("userCard/getMainCard")
//    ZmResult<CardDebit> getMainDebitCard(@RequestParam("userNo") String userNo);
//
//    /**
//     * 获取用户账户余额信息
//     * @param userNo
//     * @return
//     */
//    @RequestMapping("cardSubacct/getCashierCardSubacct")
//    ZmResult getCashierCardSubacct(@RequestParam("userNo") String userNo);
//
//    /**
//     * 获取用户卡片信息
//     * @param userNo
//     * @param cardType  1-储蓄卡  2-信用卡
//     * @param channelType  3-boss  4-全渠道
//     * @return
//     */
//    @RequestMapping("userCard/getUserPaymentCards")
//    ZmResult getUserPaymentCards(@RequestParam("userNo") String userNo,
//                                 @RequestParam("cardType") Integer cardType,
//                                 @RequestParam("channelType") Integer channelType);
//
//    /**
//     * 修改卡认证信息
//     * @return
//     */
//    @RequestMapping("cardAuth/modifyCardAuth")
//    ZmResult<Boolean> modifyCardAuth(CardAuth cardAuth);
//
//    /**
//     * 查询用户二三类户信息
//     */
//    @GetMapping("/cardSubacct/getCardSubacct")
//    ZmResult<CardSubacct> getCardSubacct(@RequestParam("userNo") String userNo);
//
//    /**
//     * 更新二三类户信息
//     */
//    @PostMapping("/cardSubacct/modifyCardSubacct")
//    ZmResult<Boolean> modifyCardSubacct(@RequestBody CardSubacct cardSubacct);
//
//    /**
//     * 查询直付平台全渠道绑卡信息
//     */
//    @PostMapping("/cardSass/getCardSassByCardNo")
//    ZmResult<CardSass> getCardSassByCardNo(@RequestParam("cardNo") String cardNo);
//
//    /**
//     * 修改银联全渠道绑卡信息
//     */
//    @PostMapping("/cardSass/modifyCardSass")
//    ZmResult<Boolean> modifyCardSass(@RequestBody CardSass cardSass);
//
//    /**
//     * 查询用户安全信息
//     */
//    @RequestMapping("/userSecurity/getUserSecurity")
//    ZmResult<UserSecurity> getUserSecurity(@RequestParam("userNo") String userNo);
//
//    /**
//     * 根据产品码查找单品优惠券
//     * @param userNo
//     * @param productCode
//     * @return
//     */
//    @PostMapping("/passBb/getPassBbProductCode")
//    ZmResult getPassBbProductCode(@RequestParam("userNo") String userNo,
//                                  @RequestParam("productCode") String productCode);
//
//    /**
//     * 根据业务码查找品类优惠券
//     * @param userNo
//     * @param bizCode
//     * @return
//     */
//    @PostMapping("/passBb/getPassBbBizCode")
//    ZmResult getPassBbBizCode(@RequestParam("userNo") String userNo,
//                              @RequestParam("bizCode") Integer bizCode);
//
//    /**
//     * 根据用户编号和优惠券编号查找优惠券信息
//     * @param userNo
//     * @param passCode
//     * @return
//     */
//    @PostMapping("/passBb/getPassBbPassCode")
//    ZmResult getPassBbPassCode(@RequestParam("userNo") String userNo,
//                               @RequestParam("passCode") String passCode);
//
//    /**
//     * 根据手机号查询用户编号及token
//     * @param mobile
//     * @return
//     */
//    @RequestMapping("appUser/getUserNoAndTokenByMobile")
//    ZmResult getUserNoAndTokenByMobile(@RequestParam("mobile") String mobile);
//
//    /**
//     * 查询全渠道绑卡列表
//     */
//    @PostMapping("/cardSass/getCardSasss")
//    ZmResult<List<CardSass>> getCardSasss(@RequestBody CardSass cardSass);
//
//    /**
//     * 获取首页banner展示的优惠券及领取数量
//     * @return
//     */
//    @RequestMapping("/coupon/getPassBanner")
//    ZmResult<List<PassBannerVO>> getPassBanner();
//
//    /**
//     * 兑换券预下单
//     * @param productCode
//     * @param orderNo
//     * @return
//     */
//    @RequestMapping("/coupon/couponAdvance")
//    ZmResult couponAdvance(@RequestParam("productCode") String productCode,
//                           @RequestParam("orderNo") String orderNo);
//
//    /**
//     * 查询用户vip信息
//     */
//    @GetMapping("/vip/getRightsVip")
//    ZmResult<RightsVip> getRightsVip(@RequestParam("userNo") String userNo);
//
//    /**
//     * 修改VIP信息
//     */
//    @PostMapping("/vip/modifyRightsVip")
//    ZmResult<Boolean> modifyRightsVip(@RequestBody RightsVip rightsVip);
//
//    @Component
//    class CustomerClientHystrix implements CustomerClient {
//        @Override
//        public ZmResult getUserCardBySupport(String userNo, String businessCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<UserInfo> getUserInfoByUserNo(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getAuthInfoByAuthCode(String userNo, String authCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<UserSecondAccount> queryUserSecondAccount(JSONObject json) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getCreditCard(String userNo, String cardNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<CardAuth> getCardAuth(String cardNo, Integer channelType) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getBankBusiness(String bankCode, String businessCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getUserNoByMobile(String mobile) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getTokenByUserNo(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<UserCardVO> getCardInfoByCardNo(String cardNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<List<UserCardVO>> getUserAllCards(String userNo, Integer cardType) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<CardDebit> getDebtCard(String userNo, String cardNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<CardDebit> getMainDebitCard(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getUserPaymentCards(String userNo, Integer cardType, Integer channelType) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<CardSubacct> getCardSubacct(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<Boolean> modifyCardSubacct(CardSubacct cardSubacct) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<JSONObject> getCardAndCardAuthByUserNo(String userNo, String cardType) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<Boolean> modifyCardAuth(CardAuth cardAuth) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<Boolean> modifyCardSass(CardSass cardSass) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getPassBbProductCode(String userNo, String productCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getPassBbBizCode(String userNo, Integer bizCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getPassBbPassCode(String userNo, String passCode) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getUserNoAndTokenByMobile(String mobile) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<List<PassBannerVO>> getPassBanner() {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult couponAdvance(String productCode, String orderNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<CardSass> getCardSassByCardNo(String cardNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<UserSecurity> getUserSecurity(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<List<CardSass>> getCardSasss(CardSass cardSass) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<RightsVip> getRightsVip(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult<Boolean> modifyRightsVip(RightsVip rightsVip) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//
//        @Override
//        public ZmResult getCashierCardSubacct(String userNo) {
//            return ZmResult.newInstance(ZmCode.CUSTOMER_DOWN);
//        }
//    }
//}
