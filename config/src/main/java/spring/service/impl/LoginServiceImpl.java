package spring.service.impl;


import spring.entity.SysUser;
import spring.mapper.SysUserMapper;
import spring.mappers.Mapper;
import spring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginServiceImpl extends BaseServiceImpl<SysUser> implements LoginService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public Mapper<SysUser> getMapper() {
        return sysUserMapper;
    }

    public SysUser selectByUsername(String username){
        return sysUserMapper.selectByUsername(username);
    }



}
