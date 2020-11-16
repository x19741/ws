package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzLectureEndFund;
import spring.mapper.WsWzLectureEndFundMapper;
import spring.mappers.Mapper;
import spring.service.WsWzLectureEndFundService;

/**
 * @author shen
 * @date 2020/9/7 15:15
 */
@Service
@Transactional
public class WsWzLectureEndFundServiceImpl extends BaseServiceImpl<WsWzLectureEndFund> implements WsWzLectureEndFundService {

    @Autowired
    WsWzLectureEndFundMapper wsWzLectureEndFundMapper;
    @Override
    public Mapper<WsWzLectureEndFund> getMapper() {
        return wsWzLectureEndFundMapper;
    }
}
