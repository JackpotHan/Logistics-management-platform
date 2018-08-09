package com.xiaohan.web.mapper;

import com.xiaohan.entity.system.RolePermission;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;

/**
 * it extends that:
 * SelectOneMapper
 * SelectByExampleMapper
 * SelectByPrimaryKeyMapper
 * InsertSelectiveMapper
 * DeleteByExampleMapper
 * UpdateByExampleSelectiveMapper
 * UpdateByPrimaryKeySelectiveMapper
 */
public interface BaseMapper<T> extends InsertSelectiveMapper<T>,DeleteByExampleMapper<RolePermission>, UpdateByExampleSelectiveMapper<T>, UpdateByPrimaryKeySelectiveMapper<T>, SelectOneMapper<T>, SelectByPrimaryKeyMapper<T>, SelectMapper<T>, SelectByExampleMapper<T>, SelectByExampleRowBoundsMapper<T>,SelectCountByExampleMapper<T> {
}
