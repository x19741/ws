package spring.mapper;

import spring.entity.SysActivitiTask;
import spring.mappers.Mapper;

import java.util.List;

public interface SysActivitiTaskMapper extends Mapper<SysActivitiTask> {
    List<SysActivitiTask> getBacklog(SysActivitiTask item);

    List<SysActivitiTask> getUnderway(SysActivitiTask item);

    List<SysActivitiTask> getFinished(SysActivitiTask item);

    List<SysActivitiTask> getCarbonCopy(SysActivitiTask item);

    List<SysActivitiTask> getInitiate(SysActivitiTask item);

    List<SysActivitiTask> getThisProcess(SysActivitiTask item);
}