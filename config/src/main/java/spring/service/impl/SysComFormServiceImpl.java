package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysComForm;
import spring.mapper.SysComFormMapper;
import spring.mappers.Mapper;
import spring.service.SysComFormService;

@Service
@Transactional
public class SysComFormServiceImpl extends BaseServiceImpl<SysComForm> implements SysComFormService {

    @Autowired
    SysComFormMapper sysComFormMapper;
    @Override
    public Mapper<SysComForm> getMapper() {
        return sysComFormMapper;
    }
}
