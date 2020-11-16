package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysRoleUser;
import spring.mapper.SysRoleUserMapper;
import spring.mappers.Mapper;
import spring.service.SysRoleUserService;

@Service
@Transactional
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUser> implements SysRoleUserService {

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public Mapper<SysRoleUser> getMapper() {
        return sysRoleUserMapper;
    }
}
