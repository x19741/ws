package spring.service;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import spring.entity.SysRole;
import spring.entity.SysUser;

public interface ActivitiIdentityService  {

    UserEntity saveUser(SysUser record);

    GroupEntity saveGroup(SysRole record);

    void deleteUser(String userId);

    void deleteGroup(String groupId);
}
