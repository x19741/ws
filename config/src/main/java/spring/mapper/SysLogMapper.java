package spring.mapper;

import spring.entity.SysLog;
import spring.mappers.Mapper;

import java.util.List;

public interface SysLogMapper extends Mapper<SysLog> {
    List<SysLog> selectListByPage( SysLog sysLog);
}