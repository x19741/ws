package spring.service;

import spring.entity.SysMenu;
import spring.entity.SysMenuRole;

import java.util.List;

public interface SysMenuService  extends  BaseService<SysMenu>{
    String getRoleAcionTree(String id, Integer pageNumber, Integer pageSize);

    void updateRoleAcionTree(List<SysMenuRole> mrlist, String roleId) throws Exception;
}
