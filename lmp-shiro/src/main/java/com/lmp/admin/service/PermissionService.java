package com.lmp.admin.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseEnum;
import com.jackpot.base.base.BaseException;
import com.lmp.admin.dto.PermissionDTO;
import com.lmp.admin.mapper.PermissionMapper;
import com.lmp.admin.model.Permission;
import com.lmp.admin.util.DateUtil;
import com.lmp.admin.util.ObjectConvertUtil;
import com.lmp.admin.util.ParamEmptyUtil;
import com.lmp.admin.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService {

    @Resource
    private PermissionMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public void addPermission(PermissionDTO permissionDTO) {
        ParamEmptyUtil.validate(permissionDTO.getPerCode(), permissionDTO.getPerName(), permissionDTO.getPerUrl()
                , permissionDTO.getPerType());
        if (checkPerExist(permissionDTO.getPerCode())) {
            throw new BaseException(BaseCode.PER_CODE_EXIST_ERR);
        }
        Permission param = ObjectConvertUtil.convert(permissionDTO, Permission.class);
        param.setStatus(BaseEnum.ENABLED.getCode());
        param.setCreateTime(DateUtil.now());
        mapper.insertSelective(param);

    }

    @Transactional(rollbackFor = Exception.class)
    public void editPermission(PermissionDTO permissionDTO) {
        ParamEmptyUtil.validate(permissionDTO.getPerName(), permissionDTO.getPerUrl(),
                permissionDTO.getId(), permissionDTO.getPerType());
        Permission param = ObjectConvertUtil.convert(permissionDTO, Permission.class);
        param.setUpdateTime(DateUtil.now());
        mapper.updateByPrimaryKeySelective(param);

    }

    public List<Permission> queryPermissions(Permission permission) {
        return mapper.select(permission);
    }

    public PageInfo<PermissionDTO> queryPage(PermissionDTO permissionDTO) {
        Permission param = ObjectConvertUtil.convert(permissionDTO, Permission.class);
        Page<PermissionDTO> page = PageHelper.startPage(permissionDTO.getPageNum(), permissionDTO.getPageSize());
        Example example = new Example(param.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(param.getPerName())) {
            criteria.andLike("perName", "%" + param.getPerName() + "%");
        }
        if (!StringUtil.isEmpty(param.getPerCode())) {
            criteria.andLike("perCode", "%" + param.getPerCode() + "%");
        }
        if (!StringUtil.isEmpty(param.getPerType())) {
            criteria.andEqualTo("perType", param.getPerType());
        }
        if (!StringUtil.isEmpty(param.getStatus())) {
            criteria.andEqualTo("status", param.getStatus());
        }
        List<Permission> results = mapper.selectByExample(example);
        PageInfo<PermissionDTO> pageInfo = page.toPageInfo();
        pageInfo.setList(ObjectConvertUtil.convertList(results, PermissionDTO.class));
        return pageInfo;
    }

    public List<Permission> queryByIds(List<Long> ids) {
        ParamEmptyUtil.validate(ids);
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        List<Permission> permissions = mapper.selectByExample(example);
        return permissions;
    }

    /**
     * 检查资源编码是否存在，存在返回true
     *
     * @param perCode
     * @return
     */
    public boolean checkPerExist(String perCode) {
        Permission per = new Permission();
        per.setPerCode(perCode);
        Permission operationPermission = mapper.selectOne(per);
        if (operationPermission != null) {
            return true;
        }
        return false;
    }

    public List<PermissionDTO> queryCatalog() {
        Permission param = new Permission();
        //param.setStatus(BaseEnum.ENABLED.getCode());
        param.setPerType(BaseEnum.CATALOG.getCode());
        List<Permission> catalogs = mapper.select(param);
        return ObjectConvertUtil.convertList(catalogs, PermissionDTO.class);
    }

    public List<PermissionDTO> queryChildList(PermissionDTO permissionDTO) {
        ParamEmptyUtil.validate(permissionDTO.getParentId());
        Permission param = new Permission();
        param.setParentId(permissionDTO.getParentId());
        // param.setStatus(BaseEnum.ENABLED.getCode());
        List<Permission> childList = mapper.select(param);
        return ObjectConvertUtil.convertList(childList, PermissionDTO.class);
    }
}
