package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysMenuRole;
import spring.mapper.SysMenuRoleMapper;
import spring.mappers.Mapper;
import spring.service.SysMenuRoleService;

@Service
@Transactional
public class SysMenuRoleServiceImpl extends BaseServiceImpl<SysMenuRole> implements SysMenuRoleService {

    @Autowired
    SysMenuRoleMapper sysMenuRoleMapper;
    @Override
    public Mapper<SysMenuRole> getMapper() {
        return sysMenuRoleMapper;
    }
}
