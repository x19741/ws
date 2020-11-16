package spring.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.IdentityServiceImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysRole;
import spring.entity.SysRoleUser;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.ActivitiIdentityService;

@Service
@Transactional
public class ActivitiIdentityServiceImpl implements ActivitiIdentityService {

    @Autowired
    IdentityService identityServiceImpl;



    @Override
    public UserEntity saveUser(SysUser record) {
        try {
            UserEntity userEntity = new UserEntity(record.getId());
            userEntity.setFirstName(record.getAccountname());

            User user = identityServiceImpl.createUserQuery().userId(record.getId()).singleResult();
            if (user != null) {
                if(!record.getAccountname().equals(user.getFirstName()))
                    identityServiceImpl.deleteUser(user.getId());
                else{
                    return userEntity;
                }
            }
            identityServiceImpl.saveUser(userEntity);
            //添加角色关系
            if (record.getRlist() != null) {
                for (SysRole role : record.getRlist()) {
                    identityServiceImpl.createMembership(record.getId(), role.getId());
                }
            }
            return userEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("用户流程信息是出现了意外的错误!");
        }

    }

    @Override
    public GroupEntity saveGroup(SysRole record) {
        try {
            Group group = identityServiceImpl.createGroupQuery().groupId(record.getId()).singleResult();
            if (group != null) {
                identityServiceImpl.deleteGroup(group.getId());
            }
            GroupEntity groupEntity = new GroupEntity(record.getId());
            groupEntity.setName(record.getRoleName());
            identityServiceImpl.saveGroup(groupEntity);
            return groupEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("用户流程信息是出现了意外的错误!");
        }

    }

    @Override
    public void deleteUser(String userId) {
        try {
            identityServiceImpl.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("用户流程信息是出现了意外的错误!");
        }
    }

    @Override
    public void deleteGroup(String groupId) {
        try {
            identityServiceImpl.deleteGroup(groupId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("用户流程信息是出现了意外的错误!");
        }
    }


}
