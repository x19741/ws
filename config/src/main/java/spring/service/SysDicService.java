package spring.service;

import spring.entity.SysDic;
import spring.entity.SysDicType;

import java.util.List;

public interface SysDicService extends BaseService<SysDic>{
    List<SysDic> select(String dictype) throws Exception;
    List<SysDicType> selectType();

    SysDic selectOneById(int id);

    int delete(int id);

    int update(SysDic sys);

    int add(SysDic sys);

    List<SysDic> selectSysDicByCon(SysDic sys);
}
