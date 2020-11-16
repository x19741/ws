package spring.mapper;



import spring.entity.SysUser;
import spring.mappers.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    SysUser selectByUsername(@Param("username")String username);

    List<SysUser> selectListByPage2(SysUser user);

    int updateOpenidNull(@Param("openid") String openid);
}