package spring.listener.assignee;

import org.activiti.engine.delegate.DelegateTask;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 获取处理人
 */
public interface Assignee extends Serializable {

    List<Map<String,Object>> notify(Map<String,Object> map);

    boolean notifyCheak(String roleId, String deptId, String unitId, List<String> users,String userId);

}
