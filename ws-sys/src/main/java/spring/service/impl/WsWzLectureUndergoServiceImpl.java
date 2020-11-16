package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzLectureUndergo;
import spring.mapper.WsWzLectureUndergoMapper;
import spring.mappers.Mapper;
import spring.service.WsWzLectureUndergoService;

/**
 * @author shen
 * @date 2020/9/7 15:27
 */
@Service
@Transactional
public class WsWzLectureUndergoServiceImpl extends BaseServiceImpl<WsWzLectureUndergo> implements WsWzLectureUndergoService {

    @Autowired
    WsWzLectureUndergoMapper wsWzLectureUndergoMapper;

    @Override
    public Mapper<WsWzLectureUndergo> getMapper() {
        return wsWzLectureUndergoMapper;
    }
}
