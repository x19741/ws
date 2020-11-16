package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.WsWzLectureFund;
import spring.mapper.WsWzLectureFundMapper;
import spring.mappers.Mapper;
import spring.service.WsWzLectureFundService;

/**
 * @author shen
 * @date 2020/9/7 15:23
 */
@Service
@Transactional
public class WsWzLectureFundServiceImpl extends BaseServiceImpl<WsWzLectureFund> implements WsWzLectureFundService {

    @Autowired
    WsWzLectureFundMapper wsWzLectureFundMapper;

    @Override
    public Mapper<WsWzLectureFund> getMapper() {
        return wsWzLectureFundMapper;
    }
}
