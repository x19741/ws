package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysSerialNumber;
import spring.exception.CustomException;
import spring.mapper.SysSerialNumberMapper;
import spring.mappers.Mapper;
import spring.service.SysSerialNumberService;
import spring.util.IdWorker;
import spring.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author shen
 * @date 2020/9/21 16:05
 */
@Service
@Transactional
public class SysSerialNumberServiceImpl extends BaseServiceImpl<SysSerialNumber> implements SysSerialNumberService {

    @Autowired
    SysSerialNumberMapper sysSerialNumberMapper;

    @Override
    public Mapper<SysSerialNumber> getMapper() {
        return sysSerialNumberMapper;
    }

    @Override
    public synchronized String getSerialNumber(String type) throws Exception {
        //获取获取今天最新的编号
        List<SysSerialNumber> slist= sysSerialNumberMapper.getMaxNumByDayAndType(type);
        String time=new SimpleDateFormat("yyyyMMdd").format(new Date());
        if(slist!=null&&slist.size()>0){
            Integer i =Integer.parseInt(slist.get(0).getNum());
            i++;
            String num =StringUtils.addLeftRepairForNum(i.toString(),3,"0");
            SysSerialNumber sysSerialNumber=new SysSerialNumber();
            sysSerialNumber.setId(IdWorker.getIdWorkerNext().toString());
            sysSerialNumber.setType(type);
            sysSerialNumber.setNum(num);
            sysSerialNumber.setSerialNumber(time+num);
            insert(sysSerialNumber);
            return sysSerialNumber.getSerialNumber();
        }else {
            String num =StringUtils.addLeftRepairForNum("1",3,"0");
            SysSerialNumber sysSerialNumber=new SysSerialNumber();
            sysSerialNumber.setId(IdWorker.getIdWorkerNext().toString());
            sysSerialNumber.setType(type);
            sysSerialNumber.setNum(num);
            sysSerialNumber.setSerialNumber(time+num);
            insert(sysSerialNumber);
            return sysSerialNumber.getSerialNumber();
        }
    }
}
