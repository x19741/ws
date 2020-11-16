package spring.listener.nextnode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface NextNode extends Serializable {

    List<Map<String, Object>>  notify(String taskId,String ModelId,String businessKey);

    boolean notifyCheak(String taskId,String ModelId,String nextNode);
}
