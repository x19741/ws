package spring.service.impl;

import spring.entity.SysDic;
import spring.entity.SysDicType;
import spring.exception.CustomException;
import spring.mapper.SysDicMapper;
import spring.mapper.SysDicTypeMapper;
import spring.mappers.Mapper;
import spring.service.SysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.util.IdWorker;

import java.util.List;
@Service
@Transactional
public class SysDicServiceImpl extends  BaseServiceImpl<SysDic>  implements SysDicService {


    @Autowired
    SysDicMapper sysDicMapper;




    @Autowired
    SysDicTypeMapper sysDicTypeMapper;

    @Override
    public Mapper<SysDic> getMapper() {
        return sysDicMapper;
    }




    @Override
    public List<SysDic> select(String dictype) throws Exception {
        SysDic dic =new SysDic();
        dic.setDicType(dictype);
        return  getMapper().select(dic);
    }

    public List<SysDicType> selectType() {
        return  sysDicTypeMapper.selectAll();

    }

    @Override
    public SysDic selectOneById(int id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int delete(int id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int update(SysDic sys) {
        return getMapper().updateByPrimaryKeySelective(sys);
    }

    @Override
    public int add(SysDic sys) {
        //这里要判断是否重复
        SysDic sys1= new SysDic();
        sys1.setDicType(sys.getDicType());
        sys1.setDicValue(sys.getDicValue());
        if(getMapper().select(sys1).size()>0){
            throw  new CustomException("数据重复");
        }
        sys1.setDicValue(null);
        sys1.setDicName(sys.getDicName());
        if(getMapper().select(sys1).size()>0){
            throw  new CustomException("数据重复");
        }
        sys.setDicId(IdWorker.getIdWorkerNext().toString());
        return getMapper().insertSelective(sys);
    }

    @Override
    public List<SysDic> selectSysDicByCon(SysDic sys) {
        return getMapper().selectListByPage(sys);
    }


}
