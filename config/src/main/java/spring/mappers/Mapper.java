package spring.mappers;

import tk.mybatis.mapper.common.*;

import java.util.List;

public interface Mapper<T> extends BaseMapper<T>, MySqlMapper<T>, IdsMapper<T>, ConditionMapper<T>, ExampleMapper<T> {

    List<T> selectListByPage(T record);

    List<T> selectListByPage2(T record);

    List<T> selectComfromTable(String...string);

    List<T>  selectListByPageForm(T record);


}
