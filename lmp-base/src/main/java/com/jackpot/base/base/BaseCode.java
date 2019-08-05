package com.jackpot.base.base;

public enum BaseCode {

    OK("000000", "成功"),
    RESPCODE_FAIL("000001", "操作失败"),
    ERR("000001","未知异常"),
    UNKNOW_RESULT("000002", "操作结果未知"),
    SIGN_ERR("000003", "签名错误"),
    RESPCODE_SIGN_ERROR("000004", "签名失败"),
    REQUEST_PARAMETER_ERROR("000005", "请求参数缺失"),
    PARAM_ERR("000007", "参数错误"),
    PARAM_MISS("000006", "参数缺少"),
    RESPCODE_PARAMETER_ERROR("000008", "报文参数不正确"),
    STATUS_ERR("000009", "数据状态错误"),
    RESPCODE_OTHER_ERROR("000010", "其它错误"),
    BIZ_ERR("000011", "业务错误"),
    BIZ_WARN("000012", "业务警告"),
    TOKEN_ERR("000013", "token错误"),
    TOKEN_OVERTIME("000014", "token调用次数超限"),
    INVOKE_OVERRUN("000015", "接口调用次数超限"),
    TIME_OVER("000016", "请求接口时间超时"),
    INVALID_INTERFACE("000017", "接口尚未实现"),
    MESSAGE_TYPE_ERROR("000018", "消息类型错误"),
    UNAUTHORIZED_ERR("000019", "无操作权限"),
    LOGIN_SUCCESS("000020", "登录成功"),
    CAPTCHA_INPUT_ERR("000021", "验证码错误"),
    SESSION_LOSE("000022", "会话失效,请重新登录!"),
    USERNAME_PASSWORD_ERR("000023", "用户名密码错误"),
    USERNAME_FREEZED_ERR("000024", "账户冻结"),
    CAPTCHA_EMPTY_ERR("000025", "验证码不能为空"),
    USER_ACCOUNT_EXIST_ERR("000026", "用户名已存在"),
    ENUM_TYPE_ERR("000027", "枚举类型错误"),
    HTTP_REQUEST_ERR("000028", "http请求异常"),
    LOGOUT_SUCCESS("000029", "登出成功"),
    LOGIN_FAIL("000030", "登录失败"),
    REQUEST_LONG("000031", "请求时间失效"),
    REQUEST_REPEAT("000032","请勿重复提交请求"),
    NOT_VALID_ID("000033", "无效的ID"),
    USER_ACCOUNT_PHONE_ERR("000034", "用户手机号已存在"),
    ASSERT_ERROR("000035","断言错误"),
    TIME_OUT("000036","请求超时"),

    /*-----系统管理模块----*/
    ROLE_CODE_EXIST_ERR("001000", "角色编码已经存在"),
    PER_CODE_EXIST_ERR("001001", "资源编码已经存在"),
    PER_NO_ERR("001002", "无该资源集合"),
    PER_PARENT_NOT_ERR("001003", "父资源不能为空"),
    PARAM_AREA_HAVE_NULL("001004", "区域参数有必填项是空值"),
    PARAM_ORG_HAVE_NULL("001005", "机构参数有必填项是空值"),
    ADMIN_HAVE_PRODUCT_CAN_NOT_DELETE("001006", "该管理员已创建了产品，无法删除"),
    ADMIN_HAVE_SECOND_CLASSIFICATION_CAN_NOT_DELETE("001006", "该管理员已创建了产品二级分类，无法删除"),
    ADMIN_HAVE_ORG_CAN_NOT_DELETE("001006", "该管理员已创建了机构，无法删除"),
    ADMIN_DO_NOT_HAVE_ROLE("001007", "该管理员没有角色"),

    /*-----APP用户模块模块----*/
    VIRTUAL_PARTNER_NONE("002000", "虚拟合伙人不存在"),
    PARTNER_EXIST("002001", "该地区合伙人已存在"),
    PARTNER_NOW_EXIST("002002", "合伙人信息不存在"),
    ACCOUNT_NOT_EXIST("002003", "账户不存在"),
    ACCOUNT_HAS_EXIST("002004", "账户手机号已注册"),
    CONTACTS_NOT_HAVE_DEFAULT("002005", "没有默认联系人"),
    ACCOUNT_INFO_NOT_COMPLETE("002006", "农户信息未完善"),
    PARTNER_BANKCARD_NOT_EXIST("002007", "合伙人没有绑定银行卡"),
    ACCOUNT_IDNO_HAS_EXIST("002008", "账户身份证号已注册"),

    /*------产品模块------*/
    PRICE_MODEL_DATA_ERROR("003001", "价格模型数据错误"),
    PARTNER_COMMISSION_RATIO_DATA_ERROR("003002", "合伙人佣金比例数据错误"),
    CAN_NOT_EDIT_INSURANCE_COMMISSION_RATIO("003003", "不能编辑保险订单的佣金比例"),
    COMMISSION_RATIO_OVER_1("003004", "佣金比例之和超过1"),
    PRODUCT_HAVE_ORDER("003005", "产品已有订单，无法删除"),

    /*-------区域模块----------*/
    HAVE_SON_AREA_CAN_NOT_DELETE("004001", "有子级区域，无法删除"),
    HAVE_ACCOUNT_CAN_NOT_DELETE("004002", "该区域下有农户，无法删除"),
    HAVE_PARTNER_CAN_NOT_DELETE("004003", "该区域下有合伙人，无法删除"),
    HAVE_ORGANIZATION_CAN_NOT_DELETE("004004", "该区域下有管理员，无法删除"),

    /*-------产品二级分类模块-------*/
    HAVE_PRODUCT_CAN_NOT_DELETE("005001", "该分类下有产品，无法删除"),

    /*-------机构模块---------*/
    ORGANIZATION_HAVE_ADMIN("006001", "当前机构已有管理员，不可删除"),

    /*----文件上传下载----*/
    CAN_NOT_GET_UPLOAD_FILE("007001", "无法获取上传文件"),
    CAN_NOT_GET_RIGHT_FILE_NAME("007002", "无法获取到正确的文件名"),

    /*-------订单模块---------*/
    INVALID_STATUS("008001", "非法的状态"),
    NEED_TOTAL_AMOUNT("008002", "贷款理财产品必须输入金额"),
    DUPLICATE_ORDER_ON_SAME_PRODUCT("008003", "您已申请过该产品"),
    ONLY_LOAN_FINANCE_PARTNER_COMMISSION_RATIO("008004", "只能编辑贷款或理财订单的合伙人佣金比例"),
    HAVING_SETTLEMENT_DATA("008005", "已有结算数据"),
    ;


    private final String code;

    private final String msg;

    BaseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static boolean isStatus(String code) {
        for (BaseCode s : BaseCode.values()) {
            if (s.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
