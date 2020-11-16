package spring.service;

import spring.entity.SysSerialNumber;

/**
 * @author shen
 * @date 2020/9/21 16:04
 */
public interface SysSerialNumberService extends BaseService<SysSerialNumber> {
    String getSerialNumber(String type) throws Exception;
}
