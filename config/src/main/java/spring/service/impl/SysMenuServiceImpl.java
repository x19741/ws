package spring.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysMenu;
import spring.entity.SysMenuRole;
import spring.entity.SysUser;
import spring.mapper.SysMenuMapper;
import spring.mappers.Mapper;
import spring.service.SysMenuRoleService;
import spring.service.SysMenuService;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysMenuRoleService sysMenuRoleServiceImpl;

    @Override
    public Mapper<SysMenu> getMapper() {
        return sysMenuMapper;
    }

    @Override
    public ReType show(SysMenu t, int page, int limit) {
        List<SysMenu> tList = null;
        Page<SysMenu> tPage = PageHelper.startPage(page, limit);
        try {
            SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
            t.setParentId("1");
            t.setCreateBy(user.getId());
            tList = recursionShow(getMapper().selectListByPage(t),user.getId());
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }


    @Override
    public ReType show2(SysMenu t, int page, int limit) {
        List<SysMenu> tList = null;
        Page<SysMenu> tPage = PageHelper.startPage(page, limit);
        try {
            t.setParentId("1");
            tList = recursionShow2(getMapper().selectListByPage2(t),null);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    /**
     * 递归获取子集
     * @param list
     * @param userid 根据userid查询权限功能
     * @return
     */
    public List<SysMenu> recursionShow2(List<SysMenu> list,String userid) {
        if (list == null)
            return null;
        List<SysMenu> mList = new ArrayList<SysMenu>();
        for (SysMenu item : list) {
            SysMenu pro1 = new SysMenu();
            pro1.setParentId(item.getId());
            pro1.setCreateBy(userid);
            item.setMlist(recursionShow2(getMapper().selectListByPage2(pro1),userid));
            mList.add(item);
        }
        return mList;
    }

    /**
     * 递归获取子集
     * @param list
     * @param userid 根据userid查询权限功能
     * @return
     */
    public List<SysMenu> recursionShow(List<SysMenu> list,String userid) {
        if (list == null)
            return null;
        List<SysMenu> mList = new ArrayList<SysMenu>();
        for (SysMenu item : list) {
            SysMenu pro1 = new SysMenu();
            pro1.setParentId(item.getId());
            pro1.setCreateBy(userid);
            item.setMlist(recursionShow(getMapper().selectListByPage(pro1),userid));
            mList.add(item);
        }
        return mList;
    }


    @Override
    public String getRoleAcionTree(String id, Integer pageNumber, Integer pageSize) {
        SysMenuRole c=new SysMenuRole();
        Page<SysMenuRole> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            SysMenuRole sysMenuRole=new SysMenuRole();
            sysMenuRole.setParentId("1");
            sysMenuRole.setRoleId(id);
            List<SysMenuRole> clist = recursionRoleAcionTree(sysMenuRoleServiceImpl.selectListByPage(sysMenuRole));
            c.setRoleId(id);
            c.setMrlist(clist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtil.sucess("成功",c);
    }

    /**
     * 递归获取子集
     * @param list
     * @return
     */
    public List<SysMenuRole> recursionRoleAcionTree(List<SysMenuRole> list) {
        if (list == null)
            return null;
        List<SysMenuRole> mList = new ArrayList<SysMenuRole>();
        for (SysMenuRole item : list) {
            SysMenuRole i = new SysMenuRole();
            i.setRoleId(item.getRoleId());
            i.setParentId(item.getMenuId());
            item.setMrlist(recursionRoleAcionTree(sysMenuRoleServiceImpl.selectListByPage(i)));
            mList.add(item);
        }
        return mList;
    }

    @Override
    public void updateRoleAcionTree(List<SysMenuRole> mrlist, String roleId) throws Exception {
        if(mrlist==null)
            return ;
        if(roleId!=null){
            SysMenuRole a=new SysMenuRole();
            a.setRoleId(roleId);
            sysMenuRoleServiceImpl.delete(a);
            roleId=null;
        }
        recursionInsertRoleAcionTree(mrlist);

    }

    /**
     * 递归添加子集
     * @param mrlist
     * @return
     */
    public void recursionInsertRoleAcionTree(List<SysMenuRole> mrlist) throws Exception {
        if(mrlist==null)
            return ;
        for (SysMenuRole item : mrlist) {
            if (item.getMrlist() != null) {
                for (SysMenuRole action : item.getMrlist()) {
                    if (action.getMenuId()!=null&&action.getRoleId() != null && action.getRoleId() != null && "0".equals(action.getIsAction())) {
                        action.setId(IdWorker.getIdWorkerNext().toString());
                        sysMenuRoleServiceImpl.insert(action);
                    }
                    recursionInsertRoleAcionTree(action.getMrlist());
                }
            }

            if (item.getMenuId()!=null&&item.getRoleId() != null && item.getRoleId() != null && "0".equals(item.getIsAction())) {
                if(item.getId()==null)
                    item.setId(IdWorker.getIdWorkerNext().toString());
                sysMenuRoleServiceImpl.insert(item);
            }

        }

    }
}
