package spring.service.impl;

import spring.entity.SysLog;
import spring.mapper.SysLogMapper;
import spring.mappers.Mapper;
import spring.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public int insertLog(SysLog sysLog) {

        sysLog.setId((int) System.currentTimeMillis());
        return sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public Mapper<SysLog> getMapper() {
        return sysLogMapper;
    }



}
