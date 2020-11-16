package spring.listener.assignee.impl;

import spring.listener.assignee.Assignee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAssignee implements Assignee {

    @Override
    public List<Map<String, Object>> notify(Map<String, Object> map) {
        List<Map<String,Object>> list =new ArrayList<>();
        Map<String,Object > assigneemap =new HashMap<>();
        assigneemap.put("assigneeName","admin");
        assigneemap.put("assigneeValue","test1");
        list.add(assigneemap);
        assigneemap =new HashMap<>();
        assigneemap.put("assigneeName","student");
        assigneemap.put("assigneeValue","test2");
        list.add(assigneemap);
        return list;
    }

    @Override
    public boolean notifyCheak(String roleId, String deptId, String unitId, List<String> users, String userId) {
        return true;
    }

}
