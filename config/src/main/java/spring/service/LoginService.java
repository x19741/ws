package spring.service;

import spring.entity.SysUser;

public interface LoginService {
    SysUser selectByUsername(String username);
}
