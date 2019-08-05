package com.lmp.admin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class StringUtil {
    // private static final Logger LOGGER = Logger.getLogger(StringUtil.class);
    private static final String ISO_8859_1 = "ISO-8859-1";

    private static final String UTF_8 = "UTF-8";

    public static boolean exist(String str, String sub) {
        int i = str.indexOf(sub);
        return i != -1;
    }

    public static boolean exist(String str, char c) {
        int i = str.indexOf(c);
        if (i != -1) {
            return true;
        } else {
            return false;
        }
    }

    public static String getLastSubString(String str, String sub) {
        int i = str.lastIndexOf(sub);
        if (i != -1) {
            return str.substring(i + sub.length());
        } else {
            return "";
        }
    }

    public static String getLastSubString(String str, char c) {
        int i = str.lastIndexOf(c);
        if (i != -1) {
            return str.substring(i + 1);
        } else {
            return "";
        }
    }

    public static String getFirstSubString(String str, String sub) {
        int i = str.lastIndexOf(sub);
        if (i != -1) {
            return str.substring(0, i);
        } else {
            return "";
        }
    }

    public static String getFirstSubString(String str, char c) {
        int i = str.lastIndexOf(c);
        if (i != -1) {
            return str.substring(0, i);
        } else {
            return "";
        }
    }

    public static String reverse(String str) {
        if (str == null || str.equalsIgnoreCase("")) {
            return "";
        }

        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }

    public static String replace(String str, String pat, String val, int start) {
        if (val == null) {
            val = String.valueOf(val); // "null"
        }

        // Look for pattern
        int index = str.indexOf(pat, start);

        while (index >= 0) {

            // Copy part before pattern, value and part after pattern
            StringBuffer buf = new StringBuffer(str.length());
            buf.append(str.substring(0, index));
            buf.append(val);
            buf.append(str.substring(index + pat.length()));
            str = buf.toString();

            // Look for pattern again, starting after value
            index = str.indexOf(pat, index + val.length());
        }
        return str;
    } // End replace

    public static String format(String s) {
        if (s == null)
            s = "";
        return s;
    }

    // 字符串转义，防止出现分隔符重复的错误
    public static String encode(String value) {
        value = value.replaceAll("/", "//").replaceAll(";", "/?").replaceAll(",", "/.").replaceAll("=", "/]")
                .replaceAll("\n", "/n");
        return value;
    }

    /**
     * 一下三个方法主要是针对时间问题 可能效率比较低 目的是为了时间格式的统一 将指定的时间字符串转换为全格式[yyyy-MM-dd
     * HH:mm:ss.SSSSSSSSS]的TimeStamp字符串
     *
     * @param value String
     * @return String
     */
    public static String convertTimeStampFormatString(String value) {
        SimpleDateFormat timestamp_parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        String stringvalue = null;
        try {// timestamp格式[yyyy-MM-dd HH:mm:ss.SSSSSSSSS]的判断
            timestamp_parse.parse(value);
            return value;
        } catch (ParseException e) {
        }

        try {// datetime格式[yyyy-MM-dd HH:mm:ss]的判断
            SimpleDateFormat date_parse2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stringvalue = timestamp_parse.format(date_parse2.parse(value));
            return stringvalue;
        } catch (ParseException e) {
        }

        try {// date格式[yyyy-MM-dd]的判断
            SimpleDateFormat date_parse3 = new SimpleDateFormat("yyyy-MM-dd");
            stringvalue = timestamp_parse.format(date_parse3.parse(value));
            return stringvalue;
        } catch (ParseException e) {
        }

        try {// time格式[HH:mm:ss]的判断
            SimpleDateFormat time_parse = new SimpleDateFormat("HH:mm:ss");
            stringvalue = timestamp_parse.format(time_parse.parse(value));
            return stringvalue;
        } catch (ParseException e) {
        }

        return "";
    }

    /**
     * 将指定的时间字符串转换为Date格式[yyyy-MM-dd]字符串 value 必须是Date格式或者是TimeStamp格式
     *
     * @param value String
     * @return String llk
     */
    /*
     * public static String convertDateFormatString(String value){
     * SimpleDateFormat timestamp_parse = new SimpleDateFormat(
     * "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"); SimpleDateFormat date_parse = new
     * SimpleDateFormat("yyyy-MM-dd"); String stringvalue = null; boolean parse
     * = true;//是否被解析 try{ date_parse.parse(value); return value;//解析成功说明格式正确
     * 直接返回 } catch (ParseException e) { } try {//timestamp格式[yyyy-MM-dd
     * HH:mm:ss.SSSSSSSSS]的判断 stringvalue =
     * date_parse.format(timestamp_parse.parse(value)); } catch (ParseException
     * e) { parse = false; } if(parse) return stringvalue; return null; }
     */
    public static String convertDateFormatString(String value) {
        SimpleDateFormat timestamp_parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        SimpleDateFormat date_parse = new SimpleDateFormat("yyyy-MM-dd");
        String stringvalue = null;
        boolean parse = true;// 是否被解析
        try {// timestamp格式[yyyy-MM-dd HH:mm:ss.SSSSSSSSS]的判断
            stringvalue = date_parse.format(timestamp_parse.parse(value));
        } catch (ParseException e) {
            parse = false;
        }
        if (parse)
            return stringvalue;
        try {
            date_parse.parse(value);
            return value;// 解析成功说明格式正确 直接返回
        } catch (ParseException e) {
        }
        return null;
    }

    /*
     * /** 将指定的时间字符串转换为Time格式[HH:mm:ss]字符串 value 必须是Time格式或者是TimeStamp格式
     *
     * @param value String
     *
     * @return String
     */
    public static String convertTimeFormatString(String value) {
        SimpleDateFormat timestamp_parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        SimpleDateFormat time_parse = new SimpleDateFormat("HH:mm:ss");
        String stringvalue = null;
        boolean parse = true;// 是否被解析
        try {
            time_parse.parse(value);
            return value;// 解析成功说明格式正确 直接返回
        } catch (ParseException e) {
        }
        try {// timestamp格式[yyyy-MM-dd HH:mm:ss.SSSSSSSSS]的判断
            stringvalue = time_parse.format(timestamp_parse.parse(value));
        } catch (ParseException e) {
            parse = false;
        }
        if (parse)
            return stringvalue;
        return null;
    }

    // 去掉string字段结尾的不可见字符。
    public static String trimString(String value) {
        if (value == null || value.length() == 0)
            return value;
        int len = value.length();
        while ((len > 0) && (value.charAt(len - 1) <= ' ')) {
            len--;
        }
        return value.substring(0, len);
    }

    /***
     * 字符串不为null且不等于""
     *
     * @param str
     * @return
     */
    public static boolean isValid(String str) {
        if (str != null && !"".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * 字符串为null或者等于""
     *
     * @param str
     * @return
     */
    public static boolean isInvalid(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGSDWValid(String oldPks, String... newPks) {
        boolean isValidPks = false;

        if (isValid(oldPks)) {
            for (String columnName : newPks) {
                String[] oldColumnName = oldPks.split(";")[0].split(",");
                if (oldColumnName.length == newPks.length) {
                    for (String columnName2 : oldColumnName) {
                        if (columnName.equals(columnName2)) {
                            isValidPks = true;
                            break;
                        } else {
                            isValidPks = false;
                        }
                    }
                }
                if (!isValidPks) {
                    break;
                }
            }
        } else {
            isValidPks = false;
        }

        return isValidPks;
    }

    public static String getCurrentDateTimeFormatString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());
    }

    /**
     * @param tableName
     * @param recordSize     {size1, size2...}
     * @param costTime       {costTime1, costTime2...}
     * @param avgSpeedMapArr avgSpeedMap<tableName, new Long[]{平均速度, 总大小, 用的总时间}>
     * @return Long[]
     * @Title: countAvgSpeed
     * @Description: 统计平均速度
     * @author sunxiaolu
     * @date: 2013-10-28 pm 8:38:37
     */
    public static Long[] countAvgSpeed(String tableName, Long[] recordSize, Long[] costTime,
                                       List<Map<String, Long[]>> avgSpeedMapArr) {
        synchronized (avgSpeedMapArr) {
            int length = avgSpeedMapArr.size();
            Long[] avgSpeedArr = new Long[length];
            for (int i = 0; i < length; i++) {
                Map<String, Long[]> avgSpeedMap = avgSpeedMapArr.get(i);
                Long[] size = avgSpeedMap.get(tableName);
                Long rs = recordSize[i];
                Long ct = costTime[i];
                if (size == null) {
                    size = new Long[]{(rs * 1000) / (ct == 0 ? 1 : ct), rs, ct};
                    avgSpeedMap.put(tableName, size);
                } else {
                    size[1] = size[1] + rs;
                    size[2] = size[2] + ct;
                    size[0] = (size[1] * 1000) / (size[2] == 0 ? 1 : size[2]);
                }
                avgSpeedArr[i] = size[0];
            }
            return avgSpeedArr;
        }
    }

    /**
     * @param tableName
     * @param avgSpeedMapArr avgSpeedMap<tableName, new Long[]{平均速度, 总大小, 用的总时间}>
     * @return void
     * @Title: reCountAvgSpeed
     * @Description: 重置平均速度
     * @author sunxiaolu
     * @date: 2013-10-28 pm 8:38:37
     */
    public static void reCountAvgSpeed(String tableName, List<Map<String, Long[]>> avgSpeedMapArr) {
        synchronized (avgSpeedMapArr) {
            int length = avgSpeedMapArr.size();
            for (int i = 0; i < length; i++) {
                Map<String, Long[]> avgSpeedMap = avgSpeedMapArr.get(i);
                Long[] intoDbRecordSize = avgSpeedMap.get(tableName);
                if (intoDbRecordSize != null) {
                    intoDbRecordSize[0] = 0L;
                    intoDbRecordSize[1] = 0L;
                    intoDbRecordSize[2] = 0L;
                }
            }
        }
    }

    public static List<String> isBlank(final String... strs) {
        List<String> result = null;
        int strsLen;
        if (strs == null || (strsLen = strs.length) == 0) {
            return result;
        }
        result = new ArrayList<String>(strs.length);
        for (int i = 0; i < strsLen; i++) {
            try {
                if (isInvalid(strs[i]))
                    result.set(i, "true");
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String CUSTOMER_MOBILE = "customerMobile";
//        
//        String CUSTOMER_IDENTITY_NO = "customerIdentityNo";
//        
//        String CUSTOMER_NAME = "customerName";
        // System.out.println(convertTimeStampFormatString("2010-06-23
        // 13:33:49"));
        // System.out.println(convertTimeStampFormatString("2010-07-14
        // 10:00:12.307782"));
        //
        // Map<String, Long[]> avgSpeed = new HashMap<String, Long[]>();
        // Map<String, Long[]> avgSpeed2 = new HashMap<String, Long[]>();
        // List<Map<String, Long[]>> arrayList = new ArrayList<Map<String,
        // Long[]>>();
        // arrayList.add(avgSpeed);
        // arrayList.add(avgSpeed2);
        // for (int i = 0; i < 10; i++) {
        // StringUtil.countAvgSpeed("1", new Long[]{(long)1 + i, (long)1 + i},
        // new Long[]{(long)i + 1, (long)1 + i}, arrayList);
        // }
        // StringUtil.reCountAvgSpeed("1", arrayList);

//        try {
//            System.out.println(isEmpty(new String[]{"1", null, "1"}));
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }

        //        System.out.println(filterMobile("13899991111", "*", 3, 7));
//        String responseContent = "{\"respCode\":\"00\",\"respDesc\":\"成功\",\"respData\":{\"pageNum\":1,\"pageSize\":10,\"size\":10,\"orderBy\":null,\"startRow\":1,\"endRow\":10,\"total\":123,\"pages\":13,\"list\":[{\"id\":156,\"partnerCode\":\"P606617062000000001\",\"partnerName\":\"智钱测试版\",\"appId\":\"968AA285BF219399AC9F3865DE1D6102\",\"appName\":\"智钱测试APP\",\"customerNo\":\"C106617062000000001\",\"customerOutNo\":\"17345817794505057\",\"customerName\":\"曾庆达\",\"customerMobile\":\"17794505057\",\"customerEmail\":\"465119455@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881921115111\",\"customerLevel\":null,\"customerSrc\":\"2\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-06-20 05:59:51\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"85ABF12EA29D333BB73D81F16E12454F\"},{\"id\":157,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617070600000001\",\"customerOutNo\":\"G0518058445290\",\"customerName\":\"彭桢\",\"customerMobile\":\"18058445290\",\"customerEmail\":\"244219956@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"43042619910603437X\",\"customerLevel\":null,\"customerSrc\":\"2\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-06 04:18:03\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"A7F1B4542586AFDA5257010A2DCAA8B4\"},{\"id\":159,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"968AA285BF219399AC9F3865DE1D6102 6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000002\",\"customerOutNo\":\"70185315705815842\",\"customerName\":\"丁攀\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 15:48:32\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"F350B353E79A7EEA3095144418C25076\"},{\"id\":160,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"968AA285BF219399AC9F3865DE1D6102\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000003\",\"customerOutNo\":\"37567315705815842\",\"customerName\":\"丁攀\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 16:27:46\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"CE98F57A20975D04A626518CDD48C5AA\"},{\"id\":161,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"968AA285BF219399AC9F3865DE1D6102\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000004\",\"customerOutNo\":\"10211513738076654\",\"customerName\":\"周涌\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 16:36:17\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"15B793F5517B06FE96D3540EA658ED8A\"},{\"id\":162,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"968AA285BF219399AC9F3865DE1D6102\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000005\",\"customerOutNo\":\"10211513738076653\",\"customerName\":\"\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 16:40:58\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"18346FC8DA62ACC14EAA1C5C066C1D41\"},{\"id\":163,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000006\",\"customerOutNo\":\"testhanyin676\",\"customerName\":\"齐高阳\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 16:49:09\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"168619D9934A36D93473B043804DD162\"},{\"id\":164,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000007\",\"customerOutNo\":\"testhanyin677\",\"customerName\":\"齐高阳\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 17:10:00\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"A633B54BCED570A1833FACDF1CA1A24A\"},{\"id\":165,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000008\",\"customerOutNo\":\"testhanyin678\",\"customerName\":\"齐高阳\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881199211157059\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 17:14:37\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"58740D02AFD7F2E76DDE8E7791034AB3\"},{\"id\":166,\"partnerCode\":\"P606617061300000001\",\"partnerName\":\"总测试账户\",\"appId\":\"6D2CE03E3FE7E7DC1B8603ED2D57D022\",\"appName\":\"测试总应用\",\"customerNo\":\"C106617071300000009\",\"customerOutNo\":\"testhanyin679\",\"customerName\":\"齐高阳\",\"customerMobile\":\"15603916435\",\"customerEmail\":\"1045243113@qq.com\",\"customerAddress\":\"浙江省杭州市\",\"customerZipCode\":\"310001\",\"customerIdentityType\":\"1\",\"customerIdentityNo\":\"410881921115111\",\"customerLevel\":null,\"customerSrc\":\"1\",\"customerStatus\":\"1\",\"gmtCreate\":\"2017-07-13 18:07:39\",\"gmtModify\":null,\"customerIdentityExpDate\":null,\"riskDesc\":null,\"signature\":\"FEAA1B17A43086A94708E2B1CBF14706\"}],\"firstPage\":1,\"prePage\":0,\"nextPage\":2,\"lastPage\":8,\"isFirstPage\":true,\"isLastPage\":false,\"hasPreviousPage\":false,\"hasNextPage\":true,\"navigatePages\":8,\"navigatepageNums\":[1,2,3,4,5,6,7,8]}}";
//        int customeNameIndexOf = responseContent.indexOf(CUSTOMER_NAME);
//        if(customeNameIndexOf > -1) {
//            while(customeNameIndexOf > -1) {
//                int startReplaceIndex = customeNameIndexOf + CUSTOMER_NAME.length() + 3;
//                int endReplaceIndex = responseContent.indexOf("\"", startReplaceIndex);
//                String customerName = responseContent.substring(startReplaceIndex, endReplaceIndex);
//                int length = customerName.length();
//                if(StringUtil.isValid(customerName) && length > 1) {
//                    if(length == 2) endReplaceIndex++;
//                    responseContent = StringUtil.filterStr(responseContent, "*", startReplaceIndex + 1, endReplaceIndex - 1);
//                }
//                customeNameIndexOf = responseContent.indexOf(CUSTOMER_NAME, endReplaceIndex);
//            }
//        } 
//        
//        int customerIdentityNoIndexOf = responseContent.indexOf(CUSTOMER_IDENTITY_NO);
//        if(customerIdentityNoIndexOf > -1) {
//            while(customerIdentityNoIndexOf > -1) {
//                int startReplaceIndex = customerIdentityNoIndexOf + CUSTOMER_IDENTITY_NO.length() + 3;
//                int endReplaceIndex = responseContent.indexOf("\"", startReplaceIndex);
//                String customerIdentityNo = responseContent.substring(startReplaceIndex, endReplaceIndex);
//                int length = customerIdentityNo.length();
//                if(StringUtil.isValid(customerIdentityNo) && length > 1) {
//                    if(length == 2 || length == 15) endReplaceIndex = endReplaceIndex + 1;
//                    if(length == 15) startReplaceIndex = startReplaceIndex - 2;
//                    responseContent = StringUtil.filterStr(responseContent, "*", startReplaceIndex + 10, endReplaceIndex - 4);
//                }
//                customerIdentityNoIndexOf = responseContent.indexOf(CUSTOMER_IDENTITY_NO, endReplaceIndex);
//            }
//        } 
//        
//        int customerMobileIndexOf = responseContent.indexOf(CUSTOMER_MOBILE);
//        if(customerMobileIndexOf > -1) {
//            while(customerMobileIndexOf > -1) {
//                int startReplaceIndex = customerMobileIndexOf + CUSTOMER_MOBILE.length() + 3;
//                int endReplaceIndex = responseContent.indexOf("\"", startReplaceIndex);
//                String customerIdentityNo = responseContent.substring(startReplaceIndex, endReplaceIndex);
//                int length = customerIdentityNo.length();
//                if(StringUtil.isValid(customerIdentityNo) && length > 1) {
//                    if(length == 2 || length == 15) endReplaceIndex = endReplaceIndex + 1;
//                    if(length == 15) startReplaceIndex = startReplaceIndex - 2;
//                    responseContent = StringUtil.filterStr(responseContent, "*", startReplaceIndex + 3, endReplaceIndex - 4);
//                }
//                customerMobileIndexOf = responseContent.indexOf(CUSTOMER_MOBILE, endReplaceIndex);
//            }
//        }

//        responseContent = filterJsonStr(responseContent, CUSTOMER_NAME, 1, 1); 
//        
//        responseContent = filterJsonStr(responseContent, CUSTOMER_IDENTITY_NO, 10, 4);
//        
//        responseContent = filterJsonStr(responseContent, CUSTOMER_MOBILE, 3, 4);
//        
//        System.out.println(responseContent);
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher("[总计]主叫01111次共0分钟；被叫0次共0分钟；号码数0个");
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }
//        System.out.println(strTailDigital("[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个", "主叫0次共"));
//        System.out.println(strTailDigital("总计]主叫0.0次共0分钟", "主叫"));
//        Map<String, String> map = addressResolution("浙江杭州");
//        System.out.println();
//        System.out.println( strTailDigital("晚间活跃频率占全天的1.58%","晚间活跃频率占全天的"));
//        System.out.println(isEmpty("1`","1"));


//        String evidence = "[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个";
//        String contactNum = StringUtil.strTailDigital(evidence, "号码数");
//        String cellNum = StringUtil.strTailDigital(evidence, "主叫");
//        String passiveCellNum = StringUtil.strTailDigital(evidence, "被叫");
//        String cellMinutes = StringUtil.strTailDigital(evidence, "主叫" + cellNum + "次共");
//        String passiveCell = StringUtil.strTailDigital(evidence, "被叫" + passiveCellNum + "次共");
//        System.out.println();
    }

    /**
     * 过滤指定字符串数据
     *
     * @param jsonStr
     * @param key
     * @param start
     * @param end
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String filterJsonStr(String jsonStr, String key, int start, int end) {
        try {
            int index = jsonStr.indexOf(key);
            if (index > -1) {
                while (index > -1) {
                    int startReplaceIndex = index + key.length() + 3;
                    int endReplaceIndex = jsonStr.indexOf("\"", startReplaceIndex);
                    String customerName = jsonStr.substring(startReplaceIndex, endReplaceIndex);
                    int length = customerName.length();
                    if (StringUtil.isValid(customerName) && length > 1) {
                        if (length == 2 || length == 15) endReplaceIndex = endReplaceIndex + 1;
                        if (length == 15) startReplaceIndex = startReplaceIndex - 2;
                        jsonStr = StringUtil.filterStr(jsonStr, "*", startReplaceIndex + start, endReplaceIndex - end);
                    }
                    index = jsonStr.indexOf(key, endReplaceIndex);
                }
            }
        } catch (Exception e) {
            return jsonStr;
        }
        return jsonStr;
    }

    public static <T> boolean isEmpty(@SuppressWarnings("unchecked") T... objs) {
        if (Objects.isNull(objs) || objs.length == 0) {
            return true;
        }
        for (Object obj : objs) {
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                return collection.isEmpty();
            }
            if (obj instanceof Map) {
                Map map = (Map) obj;
                return map.isEmpty();
            }
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).isEmpty();
            }
            if (obj == null || obj.toString().length() == 0)
                return true;
        }
        return false;
    }

    /** 判断对象是否为空 **/
    public static boolean isNotEmpty(Object... objs) {
        return !isEmpty(objs);
    }

    public static Map<String, Object> createRespMap(String respCode, String respDesc) {
        Map<String, Object> map = new HashMap<>();
        map.put("respCode", respCode);
        map.put("respDesc", respDesc);
        return map;
    }


    public static void responseMessage(HttpServletResponse response, String result) {
        PrintWriter out = null;
        try {
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.print(result);
            out.flush();
        } catch (IOException e) {
            // ignore
        } finally {
            StreamUtil.closeWriter(out);
        }
    }

    public static void responseMessage(PrintWriter out, String result) {
        try {
            out.print(result);
            out.flush();
        } finally {
            StreamUtil.closeWriter(out);
        }
    }

    public static String getEmailType(String email) throws Exception {
        if (isInvalid(email))
            throw new Exception("email is not blank!");
        int atIndex = email.indexOf("@");
        String message = "don't support email type!";
        if (atIndex <= 0 || email.length() == atIndex + 1)
            throw new Exception(message);
        switch (email.substring(atIndex + 1)) {
            case "qq.com":
                return "QQ";
            case "vip.qq.com":
                return "QQ";
            case "foxmail.com":
                return "FOXMAIL";
            case "126.com":
                return "MAIL126";
            case "vip.126.com":
                return "MAIL126";
            case "163.com":
                return "MAIL163";
            case "vip.163.com":
                return "MAIL163";
            case "aliyun.com":
                return "ALIYUN";
            case "gmail.com":
                return "GMAIL";
            case "sina.cn":
                return "SINA";
            case "sina.com":
                return "SINA";
            case "sina.com.cn":
                return "SINA";
            case "2008.sina.com":
                return "SINA";
            case "vip.sina.com":
                return "SINA";
            case "tom.com":
                return "TOM";
            case "hotmail.com":
                return "HOTMAIL";
            case "hotmail.com.hk":
                return "HOTMAIL";
            case "outlook.com":
                return "OUTLOOK";
            case "live.com":
                return "LIVE";
            case "live.cn":
                return "LIVE";
            case "msn.com":
                return "MSN";
            case "msn.cn":
                return "MSN";
            case "139.com":
                return "MAIL139";
            case "yeah.net":
                return "YEAH";
            case "21cn.com":
                return "MAIL21CN";
            case "wo.com.cn":
                return "WO";
            case "wo.cn":
                return "WO";
            case "sohu.com":
                return "SOHU";
            case "vip.sohu.com":
                return "SOHU";
            case "189.cn":
                return "MAIL189";
            default:
                throw new Exception(message);
        }
    }

    /**
     * <b>解析错误结果
     *
     * @param message
     * @param error
     * @return 错误结果json串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String getErrorResult(String message, String error) {
        String result;
        JSONObject jo = new JSONObject();
        jo.put("error_description", message);
        jo.put("error", error);
        result = jo.toString();
        return result;
    }

    /**
     * 为空替换
     *
     * @param checkStr
     * @param replace
     * @return
     */
    public static String isEmptyReplace(Object checkStr, String replace) {
        return isEmpty(checkStr) ? replace : checkStr.toString();
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }


    /**
     * 功能类似logger输出日志语句
     *
     * @param message
     * @param argArray
     * @return
     */
    public static String transFormat(String message, Map<String, String> argArray) {
        if (message == null) {
            return null;
        }
        if (argArray == null || argArray.size() == 0) {
            return message;
        }
        for (Map.Entry<String, String> entry : argArray.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return message;
    }

    /**
     * @return
     */
    public static JSONObject transFormatToJSONString(String message, Object... objects) {
        JSONObject jsonObject = new JSONObject();
        if (message == null) {
            return new JSONObject();
        }
        if (objects == null || objects.length <= 0) {
            return new JSONObject();
        }
        if (message.indexOf("{") < 0 && message.indexOf("}") < 0) {
            return new JSONObject();
        }
        jsonObject.put(message.substring(message.indexOf("{") + 1, message.indexOf("}")), objects[0].toString());
        objects = org.apache.commons.lang3.ArrayUtils.remove(objects, 0);
        JSONObject ttt = transFormatToJSONString(message.substring(message.indexOf("}") + 1), objects);
        if (ttt != null)
            jsonObject.putAll(ttt);
        return jsonObject;
    }

    public static String transFormat(String message, Object[] argArray) {
        return transFormat(message, argArray, "{}");
    }

    public static String transFormat(String message, Object[] argArray, String deleStr) {
        if (message == null) {
            return null;
        }
        if (argArray == null) {
            return message;
        }
        int i = 0;
        int j;
        StringBuilder sbuf = new StringBuilder(message.length() + 32 * argArray.length);
        for (int L = 0; L < argArray.length; L++) {
            j = message.indexOf(deleStr, i);
            if (j == -1) {
                // no more variables
                if (i == 0) { // this is a simple string
                    return message;
                } else { // add the tail string which contains no variables and return
                    break;
                }
            } else {
                sbuf.append(message, i, j);
                sbuf.append(argArray[L]);
                i = j + deleStr.length();
            }
        }
        sbuf.append(message, i, message.length());
        return sbuf.toString();
    }

    /**
     * 过滤字符串
     *
     * @param replaceStr        原字符串
     * @param withChar          被替换成字符
     * @param startReplaceIndex 原字符串替换开始位置
     * @param endReplaceIndex   原字符串替换结束位置
     * @return 被替换后的字符串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String filterStr(String replaceStr, String withChar, int startReplaceIndex, int endReplaceIndex) {
        if (isInvalid(replaceStr) || replaceStr.length() < endReplaceIndex) {
            return "";
        }
        if (replaceStr.length() < startReplaceIndex || startReplaceIndex >= endReplaceIndex) {
            return replaceStr;
        }
        StringBuilder stringBuilder = new StringBuilder(replaceStr.substring(0, startReplaceIndex));
        for (int startI = 0; startI < (endReplaceIndex - startReplaceIndex); startI++) {
            stringBuilder.append(withChar);
        }
        stringBuilder.append(replaceStr.substring(endReplaceIndex));
        return stringBuilder.toString();
    }

    /**
     * 比较2个字符串不相等
     *
     * @param firstStr
     * @param senondStr
     * @return
     */
    public static boolean notEquals(String firstStr, String senondStr) {
        return !equals(firstStr, senondStr);
    }

    /**
     * 比较2个字符串相等
     *
     * @param cs1
     * @param cs2
     * @return
     */
    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return regionMatches(cs1, false, 0, cs2, 0, cs1.length());
    }

    static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
                                 final CharSequence substring, final int start, final int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;

        // Extract these first so we detect NPEs the same as the java.lang.String version
        final int srcLen = cs.length() - thisStart;
        final int otherLen = substring.length() - start;

        // Check for invalid parameters
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }

        // Check that the regions are long enough
        if (srcLen < length || otherLen < length) {
            return false;
        }

        while (tmpLen-- > 0) {
            final char c1 = cs.charAt(index1++);
            final char c2 = substring.charAt(index2++);

            if (c1 == c2) {
                continue;
            }

            if (!ignoreCase) {
                return false;
            }

            // The same check as in String.regionMatches():
            if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
                    && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 截取字符串
     *
     * @param str 原字符串
     * @return 截取后的字符串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String subStr(String str) {
        return subStr(str, 0);
    }

    /**
     * 截取字符串
     *
     * @param str       原字符串
     * @param maxLength 允许最大返回长度
     * @return 截取后的字符串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String subStr(String str, int maxLength) {
        if (isInvalid(str)) {
            return str;
        }
        if (maxLength <= 0) {
            maxLength = 512;
        }
        return str.length() > maxLength ? str.substring(0, maxLength) : str;
    }

    /**
     * 转码字符串
     *
     * @param str       原字符串
     * @param srcEncode src原始字符集
     * @param tarEncode src需要转换为字符集
     * @return 转码后的字符串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String convertEncoding(String str, String srcEncode, String tarEncode) {
        String encodingString = str;
        if (isInvalid(srcEncode)) {
            srcEncode = ISO_8859_1;
        }
        if (isInvalid(tarEncode)) {
            tarEncode = UTF_8;
        }
        if (isValid(str)) {
            try {
                encodingString = new String(str.getBytes(srcEncode), tarEncode);
            } catch (Exception e) {
            }
        }
        return encodingString;
    }

    /**
     * 转码字符串
     *
     * @param str 原字符串
     * @return 转码后的字符串
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String convertEncoding(String str) {
        return convertEncoding(str, ISO_8859_1, UTF_8);
    }

    /**
     * 验证返回Object值不符合规范
     *
     * @param respData
     * @return 如果是null或者是空json串返回 true 其它返回false
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static boolean inValidResult(Object respData) {
        try {
            return respData == null || "{}".equals(JSON.toJSONString(respData));
        } catch (Exception e) {
            // ignore
        }
        return true;
    }

    /**
     * 验证返回Object值符合规范
     *
     * @param respData
     * @return 如果不是null或者不是空json串返回 true 其它返回false
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static boolean validResult(Object respData) {
        try {
            return !(inValidResult(respData));
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    /**
     * 判断是否含有中文字符
     *
     * @param str
     * @return
     */
    public static boolean isChineseByScript(String str) {
        for (char c : str.toCharArray()) {
            Character.UnicodeScript sc = Character.UnicodeScript.of(c);
            if (sc == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    /**
     * 点号转驼峰
     *
     * @param str
     * @return
     */
    public static StringBuffer camel(StringBuffer str) {
        //利用正则删除下划线，把下划线后一位改成大写
        Pattern pattern = Pattern.compile("\\.(\\w)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return camel(sb);
    }

    /**
     * 获取中文后的数值
     *
     * @return
     */
    public static String strTailDigital(String msg, String key) {
        Pattern pattern = Pattern.compile(key + "((\\d+\\.\\d+)|(\\d+))");
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
            return matcher.group(0).replaceAll(key, "");
        } else {
            return "";
        }
    }

    /**
     * 通过身份证号码获取出生日期、性别、年龄
     * {"birthday":"1993-09-07","sexCode":"M","age":"25"}
     *
     * @param certificateNo
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static Map<String, String> getBirAgeSex(String certificateNo) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
    }

    /**
     * 解析地址
     *
     * @param address
     * @return
     * @author lin
     * {"province":"浙江省","city":"温州市","county":"苍南县","town":"龙港镇","village":"吴宅街40号"}
     */
    public static Map<String, String> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        Map<String, String> row = new LinkedHashMap<String, String>();
        while (m.find()) {
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            town = m.group("town");
            row.put("town", town == null ? "" : town.trim());
            village = m.group("village");
            row.put("village", village == null ? "" : village.trim());
        }
        return row;
    }
}
