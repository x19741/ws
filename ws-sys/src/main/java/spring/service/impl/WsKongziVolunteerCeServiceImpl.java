package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziVolunteerCe;
import spring.mapper.WsKongziVolunteerCeMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziVolunteerCeService;

/**
 * @author shen
 * @date 2020/9/8 11:11
 */
@Service
@Transactional
public class WsKongziVolunteerCeServiceImpl extends BaseServiceImpl<WsKongziVolunteerCe> implements WsKongziVolunteerCeService {

    @Autowired
    WsKongziVolunteerCeMapper wsKongziVolunteerCeMapper;

    @Override
    public Mapper<WsKongziVolunteerCe> getMapper() {
        return wsKongziVolunteerCeMapper;
    }
}
