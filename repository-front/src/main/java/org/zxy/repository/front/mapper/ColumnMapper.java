package org.zxy.repository.front.mapper;
import org.apache.ibatis.annotations.Param;

import org.zxy.repository.front.entity.Column;

import java.util.List;

public interface ColumnMapper {
    List<Column> selectOrderByItemNumDesc();
    List<Column> selectOrderByCreateTimeDesc();
}