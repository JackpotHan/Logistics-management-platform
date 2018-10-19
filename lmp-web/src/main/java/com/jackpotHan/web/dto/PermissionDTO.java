package com.jackpotHan.web.dto;

import com.jackpotHan.entity.system.Permission;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Chen Lingang
 * @version $Id: PermissionDTO, v 0.1 17/5/11 下午4:59
 */
public class PermissionDTO extends Permission {

    /**
     * 权限选择类型
     * <p>0未关联,1用户权限关联 2角色权限关联
     */
    private Integer selectType = NumberUtils.INTEGER_ZERO;

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }
}
