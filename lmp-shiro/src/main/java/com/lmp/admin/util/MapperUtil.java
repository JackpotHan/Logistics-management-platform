package com.lmp.admin.util;

import com.alibaba.fastjson.JSON;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapperUtil {

    public static Example generateExample(Object obj, String... fieldStrs) {
        if (obj == null || ArrayUtils.isEmpty(fieldStrs)) return null;
        Class clazz = obj.getClass();
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        for (String fieldStr : fieldStrs) {
            try {
                Field field = clazz.getDeclaredField(fieldStr);
                field.setAccessible(true);
                Object value = field.get(obj);
                criteria.andEqualTo(fieldStr, value);
            } catch (NoSuchFieldException e) {
                log.error("字段" + fieldStr + "错误", e);
                return null;
            } catch (IllegalAccessException e) {
                log.error("字段" + fieldStr + "不可访问", e);
                return null;
            }
        }
        return example;
    }

    /**
     * 设置匹配字段数据
     * @param t
     * @param criteria
     * @param fileds   where conditions
     * @since JDK 1.8
     */
    public static void setExampleCriteria(Class t, Example.Criteria criteria, Map<String, Object> params, String... fileds) throws BaseException {
        if (ObjectUtils.equals(t, null) || ArrayUtils.isEmpty(fileds)) {
            return;
        }
        for (String filed : fileds) {
            if (StringUtil.isEmpty(filed)) continue;
            for (Field f : t.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    String fieldName = f.getName();
                    if (!"serialVersionUID".equals(fieldName)
                            && fieldName.equals(filed.split("@")[0])) {
//                        Object fieldValue = f.get(t);
                        Object fieldValue = params.get(filed);
                        String logic = "";
                        if (!StringUtil.isEmpty(fieldValue)) {
                            String s = String.valueOf(fieldValue);
                            String[] split = s.split("\\|");
                            logic = s.split("!")[0];
                            if (split.length > 1) {
                                List<String> list = Arrays.asList(split);
                                params.put(fieldName, list);
                                fieldValue = params.get(fieldName);
                            }
                            /**
                             * xxx@startTime
                             * xxx@endTime
                             */
                            String[] split1 = filed.split("@");
                            if (split1.length > 0 && split1.length == 2) {
                                if (split1[1].contains("startTime")) {
                                    criteria.andGreaterThanOrEqualTo(fieldName, s);
                                } else if (split1[1].contains("endTime")) {
                                    criteria.andLessThanOrEqualTo(fieldName, s);
                                }
                                break;
                            }
                        } else
                            break;
                        if (fieldValue.getClass().getName().contains("List")) {
                            criteria.andIn(fieldName, JSON.parseObject(JSON.toJSONString(fieldValue), List.class));
                        } else if (!ObjectUtils.equals(fieldValue, null) && !StringUtil.isEmpty(logic)) {
                            switch (logic) {
                                case ">":
                                    criteria.andGreaterThan(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<":
                                    criteria.andLessThan(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<>":
                                    criteria.andNotEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case ">=":
                                    criteria.andGreaterThanOrEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<=":
                                    criteria.andLessThanOrEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                default:
                                    criteria.andEqualTo(fieldName, fieldValue);
                                    break;
                            }
                        }
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    log.error(message, e);
                    throw new BaseException(BaseCode.RESPCODE_FAIL.getCode(), "setExampleCriteria exception.");
                }
            }
        }
    }
}
