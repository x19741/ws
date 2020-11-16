package spring.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import spring.entity.SysAction;
import spring.entity.SysActionRole;
import spring.entity.SysRole;

import spring.entity.SysUser;
import spring.mapper.SysActionMapper;
import spring.mapper.SysActionRoleMapper;
import spring.mapper.SysRoleMapper;
import spring.mapper.SysRoleUserMapper;
import spring.mappers.Mapper;
import spring.service.ActivitiIdentityService;
import spring.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.util.BeanRefUtil;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysRoleServiceImpl extends  BaseServiceImpl<SysRole>  implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    SysActionRoleMapper sysActionRoleMapper;

    @Autowired
    SysActionMapper sysActionMapper;

    @Autowired
    ActivitiIdentityService activitiIdentityServiceImpl;

    @Override
    public Mapper<SysRole> getMapper() {
        return sysRoleMapper;
    }

    @Override
    public SysRole selectByPrimaryKey(Object id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("createBy", user == null ? "" : user.getId());
        map.put("createDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        BeanRefUtil.setFieldValue(record, map);
        activitiIdentityServiceImpl.saveGroup(record);
        return getMapper().insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) throws Exception {
        Map map = new HashMap<String, String>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        map.put("updateDate", BeanRefUtil.fmtDate(new Date()));
        map.put("updateBy", user == null ? "" : user.getId());
        BeanRefUtil.setFieldValue(record, map);
        activitiIdentityServiceImpl.saveGroup(record);
        return getMapper().updateByPrimaryKeySelective(record);
    }

    public int deleteByIds(String ids) {
        int i = 0;
        String[] idlist = ids.split(",");
        for (String id : idlist) {
            activitiIdentityServiceImpl.deleteGroup(id);
            i += getMapper().deleteByPrimaryKey(id);
        }
        return i;
    }






    /*public List selectByUsername(String username){
        return  sysRoleMapper.selectByUsername(username);
    }

    @Override
    public Integer deleteByUserId(String id) {
        return sysRoleMapper.deleteByUserId(id);
    }

    @Override
    public boolean updateUserRole(SysUser user) throws Exception {



       throw new Exception("关联用户角色失败!");
    }

    @Override
    public String selectByMap(Map<String, String> map) {
        String roleCode=map.get("roleCode"), statu=map.get("statu"),pageNumber=map.get("pageNumber"),pageSize=map.get("pageSize");

        // 分页插件: 查询第1页，每页10行
        Page<SysRole> page = PageHelper.startPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        //List<SysRole> rlist= (List<SysRole>) sysRoleMapper.selectByRoleStatu("%"+roleCode+"%",Integer.valueOf(statu));

        //Integer count =sysUserMapper.selectCountByRole(roleCode);
        //return JsonUtil.sucess("成功",rlist, (int) page.getTotal());
        return  null;
    }
    @Override
    public SysRole selectByCode(String code) {
        //SysRole role= sysRoleMapper.selectByCode(code);
        return null;//role;
    }

    @Override
    public int updateRole(SysRole role) throws Exception {
        int i=  getMapper().updateByPrimaryKeySelective(role);
        if(i>0){
            //删除权限角色表
            //sysActionRoleMapper.deleteByRoleid(role.getId());
            //添加权限角色表
            if(role.getSysActions()!=null){
                for (SysAction action:  role.getSysActions()) {
                    SysActionRole ar=new SysActionRole();
                    ar.setRoleId(role.getId());
                    ar.setActionId(action.getActionid());
                    //sysActionRoleMapper.insert(ar);
                    for (SysAction action1 : action.getSysActions()) {
                        SysActionRole ar1=new SysActionRole();
                        ar1.setRoleId(role.getId());
                        ar1.setActionId(action1.getActionid());
                        //sysActionRoleMapper.insert(ar1);
                    }
                }
            }

            return  1;
        }
        throw new Exception("更新角色失败.");
    }*/


}
