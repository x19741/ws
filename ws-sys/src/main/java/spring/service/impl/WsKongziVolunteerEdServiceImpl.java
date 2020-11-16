package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziVolunteerEd;
import spring.mapper.WsKongziVolunteerEdMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziVolunteerEdService;

/**
 * @author shen
 * @date 2020/9/8 11:43
 */
@Service
@Transactional
public class WsKongziVolunteerEdServiceImpl extends BaseServiceImpl<WsKongziVolunteerEd> implements WsKongziVolunteerEdService {

    @Autowired
    WsKongziVolunteerEdMapper wsKongziVolunteerEdMapper;

    @Override
    public Mapper<WsKongziVolunteerEd> getMapper() {
        return wsKongziVolunteerEdMapper;
    }
}
