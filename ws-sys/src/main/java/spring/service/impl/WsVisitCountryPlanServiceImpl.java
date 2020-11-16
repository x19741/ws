package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryPlan;
import spring.mapper.WsVisitCountryPlanMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryPlanService;

/**
 * @author shen
 * @date 2020/9/22 14:46
 */
@Service
@Transactional
public class WsVisitCountryPlanServiceImpl extends BaseServiceImpl<WsVisitCountryPlan> implements WsVisitCountryPlanService {

    @Autowired
    WsVisitCountryPlanMapper wsVisitCountryPlanMapper;

    @Override
    public Mapper<WsVisitCountryPlan> getMapper() {
        return wsVisitCountryPlanMapper;
    }
}
