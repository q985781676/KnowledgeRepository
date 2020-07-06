package org.zxy.repository.back.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.zxy.repository.back.entity.Column;

public interface ColumnMapper {
    int deleteByPrimaryKey(Integer columnId);

    int insertSelective(Column record);

    Column selectByPrimaryKey(Integer columnId);

    int updateByPrimaryKeySelective(Column record);

    List<Column> selectAllByColumnName(@Param("columnName")String columnName);

}