package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziActualBudget;
import spring.mapper.WsKongziActualBudgetMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziActualBudgetService;

/**
 * @author shen
 * @date 2020/8/21 11:15
 */
@Service
@Transactional
public class WsKongziActualBudgetServiceImpl extends BaseServiceImpl<WsKongziActualBudget> implements WsKongziActualBudgetService {

    @Autowired
    WsKongziActualBudgetMapper wsKongziActualBudgetMapper;

    @Override
    public Mapper<WsKongziActualBudget> getMapper() {
        return wsKongziActualBudgetMapper;
    }
}
