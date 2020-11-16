package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsKongziVolunteer;
import spring.mapper.WsKongziVolunteerMapper;
import spring.mappers.Mapper;
import spring.service.WsKongziVolunteerService;

/**
 * @author shen
 * @date 2020/9/8 11:00
 */
@Service
@Transactional
public class WsKongziVolunteerServiceImpl extends BaseServiceImpl<WsKongziVolunteer> implements WsKongziVolunteerService {

    @Autowired
    WsKongziVolunteerMapper wsKongziVolunteerMapper;
    @Override
    public Mapper<WsKongziVolunteer> getMapper() {
        return wsKongziVolunteerMapper;
    }
}
