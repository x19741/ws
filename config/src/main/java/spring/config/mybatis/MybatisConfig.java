package spring.config.mybatis;


;


//@Configuration
public class MybatisConfig {

    /*@Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //添加XML目录  注意: resource目录下必须要有mapper文件夹  否则报错,此处也是坑
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("mapper/*.xml"));
        return bean.getObject();
    }

    //注意此类的加载必须要在MybatisConfig之后在加载
    @Configuration
    @AutoConfigureAfter(MybatisConfig.class)
    public static class MyBatisMapperScannerConfig {

        @Bean
        public MapperScannerConfigurer mapperScannerConfig() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
            //扫描mapper位置
            mapperScannerConfigurer.setBasePackage("spring.mapper");

            //配置通用mappers
            Properties properties = new Properties();
            //注册自定义mapper
            properties.setProperty("mappers", "spring.mappers.BaseMapper");
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");
            mapperScannerConfigurer.setProperties(properties);

            return mapperScannerConfigurer;
        }

    }

    *//**
     * 配置mybatis的分页插件pageHelper
     * @return
     *//*
    //@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //指定为MySQL数据库
        properties.setProperty("helperDialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }*/

}

