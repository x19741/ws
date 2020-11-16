package spring.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ProcessService {
    List<Map<String, Object>> getNextNode(String taskId, String modelId,String businessKey) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    Map<String,Object> completeTask(String taskId, String assignee, String nextNode, String comformInfo,String comformId, String businessKey
            , String modelId, String opinion, String serialNumber, String urgencyDegree) throws Exception;

    Map getprocessImageDate(String taskId,String modelId);

    InputStream getProcessImage(String taskId);
}
