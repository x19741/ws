package spring.service.impl;

import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysRole;
import spring.entity.SysRoleUser;
import spring.entity.SysUser;
import spring.mapper.SysUserMapper;
import spring.mappers.Mapper;
import spring.service.ActivitiIdentityService;
import spring.service.SysRoleService;
import spring.service.SysRoleUserService;
import spring.service.SysUserService;
import spring.util.BeanRefUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleService sysRoleServiceImpl;

    @Autowired
    SysRoleUserService sysRoleUserServiceImpl;

    @Autowired
    ActivitiIdentityService activitiIdentityServiceImpl;


    @Override
    public Mapper<SysUser> getMapper() {
        return sysUserMapper;
    }


    @Override
    public SysUser selectByPrimaryKey(Object id) {
        //遍历添加角色
        SysUser user = getMapper().selectByPrimaryKey(id);
        if (user == null)
            return null;
        SysRole role = new SysRole();
        role.setUserId(user.getId());
        user.setRlist(sysRoleServiceImpl.selectListByPage2(role));
        return user;
    }

    @Override
    public int insert(SysUser record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        BeanRefUtil.setFieldValue(record, map);

        //添加角色关系
        if (record.getRlist() != null) {
            for (SysRole role :record.getRlist()) {
                SysRoleUser roleUser =new SysRoleUser();
                roleUser.setRoleId(role.getId());
                roleUser.setUserId(record.getId());
                sysRoleUserServiceImpl.insert(roleUser);
            }
        }

        //同步activiti用户组
        activitiIdentityServiceImpl.saveUser(record);

        return getMapper().insertSelective(record);
    }
    @Override
    public int updateByPrimaryKeySelective(SysUser record) throws Exception {
        Map map= new HashMap<String,String>();
        SysUser user =(SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("updateDate",BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy",user==null?"":user.getId());
        BeanRefUtil.setFieldValue(record,map);
        //添加角色关系
        if (record.getRlist() != null) {
            //删除用户角色关系
            SysRoleUser roleUser1 =new SysRoleUser();
            roleUser1.setUserId(record.getId());
            sysRoleUserServiceImpl.delete(roleUser1);
            for (SysRole role :record.getRlist()) {
                SysRoleUser roleUser =new SysRoleUser();
                roleUser.setRoleId(role.getId());
                roleUser.setUserId(record.getId());
                sysRoleUserServiceImpl.insert(roleUser);
            }
        }
        activitiIdentityServiceImpl.saveUser(record);
        return getMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByIds(String ids) {
        int i = 0;
        String[] idlist = ids.split(",");
        for (String id : idlist) {
            //删除用户角色关系
            SysRoleUser roleUser1 =new SysRoleUser();
            roleUser1.setUserId(id);
            sysRoleUserServiceImpl.delete(roleUser1);
            i += getMapper().deleteByPrimaryKey(id);
            activitiIdentityServiceImpl.deleteUser(id);
        }
        return i;
    }

    @Override
    public SysUser selectOneByCon(SysUser sys) {
        return getMapper().selectOne(sys);
    }

    @Override
    public boolean updateOpenidNull(String openid) {
        return sysUserMapper.updateOpenidNull(openid)>0;
    }

}
