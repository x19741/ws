package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysComFormField;
import spring.mapper.SysComFormFieldMapper;
import spring.mappers.Mapper;
import spring.service.SysComFormFieldService;

@Service
@Transactional
public class SysComFormFieldServiceImpl extends BaseServiceImpl<SysComFormField> implements SysComFormFieldService {

    @Autowired
    SysComFormFieldMapper sysComFormFieldMapper;
    @Override
    public Mapper<SysComFormField> getMapper() {
        return sysComFormFieldMapper;
    }
}
