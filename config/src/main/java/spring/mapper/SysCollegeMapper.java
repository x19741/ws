package spring.mapper;

import spring.entity.SysCollege;

public interface SysCollegeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysCollege record);

    int insertSelective(SysCollege record);

    SysCollege selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysCollege record);

    int updateByPrimaryKey(SysCollege record);
}