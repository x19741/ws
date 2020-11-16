package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysDepartment;
import spring.mapper.SysDepartmentMapper;
import spring.mappers.Mapper;
import spring.service.SysDepartmentService;

@Service
@Transactional
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements SysDepartmentService {

    @Autowired
    SysDepartmentMapper sysDepartmentMapper;

    @Override
    public Mapper<SysDepartment> getMapper() {
        return sysDepartmentMapper;
    }
}
