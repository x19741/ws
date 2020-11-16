package spring.service;

import spring.entity.SysActivitiTask;
import spring.util.ReType;

public interface SysActivitiTaskService extends BaseService<SysActivitiTask> {

    ReType getBacklog(SysActivitiTask item, Integer pageNumber, Integer pageSize);

    ReType getUnderway(SysActivitiTask item, Integer pageNumber, Integer pageSize);

    ReType getFinished(SysActivitiTask item, Integer pageNumber, Integer pageSize);

    ReType getCarbonCopy(SysActivitiTask item, Integer pageNumber, Integer pageSize);

    ReType getInitiate(SysActivitiTask item, Integer pageNumber, Integer pageSize);

    ReType getThisProcess(SysActivitiTask item, Integer pageNumber, Integer pageSize);
}
