package spring.service;

import spring.entity.SysDicType;
import spring.util.ReType;

public interface SysDicTypeService extends  BaseService<SysDicType> {


    ReType show3(SysDicType dicType, Integer pageNumber, Integer pageSize);
}
