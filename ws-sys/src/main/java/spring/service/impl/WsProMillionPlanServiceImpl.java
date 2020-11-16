package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsProMillionPlan;
import spring.mapper.WsProMillionPlanMapper;
import spring.mappers.Mapper;
import spring.service.WsProMillionPlanService;

/**
 * @author shen
 * @date 2020/8/25 10:29
 */
@Service
@Transactional
public class WsProMillionPlanServiceImpl extends BaseServiceImpl<WsProMillionPlan> implements WsProMillionPlanService {

    @Autowired
    WsProMillionPlanMapper wsProMillionPlanMapper;
    @Override
    public Mapper<WsProMillionPlan> getMapper() {
        return wsProMillionPlanMapper;
    }
}
