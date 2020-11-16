package spring.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysActivitiTask;
import spring.mapper.SysActivitiTaskMapper;
import spring.mappers.Mapper;
import spring.service.SysActivitiTaskService;
import spring.util.ReType;

import java.util.List;

@Service
@Transactional
public class SysActivitiTaskServiceImpl extends BaseServiceImpl<SysActivitiTask> implements SysActivitiTaskService {

    @Autowired
    SysActivitiTaskMapper sysActivitiTaskMapper;

    @Override
    public Mapper<SysActivitiTask> getMapper() {
        return sysActivitiTaskMapper;
    }

    @Override
    public ReType getBacklog(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getBacklog(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType getUnderway(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getUnderway(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType getFinished(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getFinished(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType getCarbonCopy(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getCarbonCopy(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType getInitiate(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getInitiate(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public ReType getThisProcess(SysActivitiTask item, Integer pageNumber, Integer pageSize) {
        List<SysActivitiTask> tList = null;
        Page<SysActivitiTask> tPage = PageHelper.startPage(pageNumber, pageSize);
        try {
            item.setDisposeUserId(getUser().getId());
            tList = sysActivitiTaskMapper.getThisProcess(item);
        } catch (Exception e) {
            //log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }


}
