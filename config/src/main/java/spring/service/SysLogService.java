package spring.service;

import spring.entity.SysLog;

public interface SysLogService extends BaseService<SysLog> {

    public int insertLog(SysLog sysLog);


}
