package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsVisitCountryReportTra;
import spring.mapper.WsVisitCountryReportTraMapper;
import spring.mappers.Mapper;
import spring.service.WsVisitCountryReportTraService;

/**
 * @author shen
 * @date 2020/9/22 15:21
 */
@Service
@Transactional
public class WsVisitCountryReportTraServiceImpl extends BaseServiceImpl<WsVisitCountryReportTra> implements WsVisitCountryReportTraService {

    @Autowired
    WsVisitCountryReportTraMapper wsVisitCountryReportTraMapper;

    @Override
    public Mapper<WsVisitCountryReportTra> getMapper() {
        return wsVisitCountryReportTraMapper;
    }
}
