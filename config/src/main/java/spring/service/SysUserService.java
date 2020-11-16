package spring.service;

import spring.entity.SysUser;

public interface SysUserService  extends BaseService<SysUser> {

    boolean updateOpenidNull(String openid);

    SysUser selectOneByCon(SysUser sys);

}
