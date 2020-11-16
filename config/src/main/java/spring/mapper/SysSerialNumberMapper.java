package spring.mapper;

import spring.entity.SysSerialNumber;
import spring.mappers.Mapper;

import java.util.List;

public interface SysSerialNumberMapper extends Mapper<SysSerialNumber> {
    List<SysSerialNumber> getMaxNumByDayAndType(String type);
}