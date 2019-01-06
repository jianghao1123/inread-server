package com.in.read.framework.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class InreadGenerator {

    private String packageName;
    private String packageDir;
    private String[] tablePrefix = {"inread_"};
    private String[] includeTables;
    private String[] superEntityColumns = {"id", "create_time", "update_time", "status", "delete"};

    public void run() {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig config = new GlobalConfig();
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setControllerName("%sController");
        mpg.setGlobalConfig(config);
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://cdb-7np9yfc1.gz.tencentcdb.com:10021/inread_test?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true")
                .setUsername("root")
                .setPassword("inread2019")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.in.read.framework.base.BaseEntity")
                .setSuperServiceClass("com.in.read.framework.base.BaseService")
                .setSuperServiceImplClass("com.in.read.framework.base.BaseServiceImpl")
                .setTablePrefix(tablePrefix)
                .setSuperEntityColumns(superEntityColumns)
                .setInclude(includeTables);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("Generator")
                .setOutputDir(getOutputDir())
                .setFileOverride(false);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                setMap(new HashMap<>());
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return getOutputDir() + "/resource/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
//            }
//        });
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/service/" + tableInfo.getServiceName() + ".java";
            }
        });
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/service/impl/" + tableInfo.getServiceImplName() + ".java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 包配置
        PackageConfig pc = new PackageConfig();
        // 控制台扫描
        pc.setParent(packageName);
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        mpg.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .execute();
    }

    /**
     * 生成到项目中
     *
     * @return outputDir
     */
    public String getOutputDir() {
        return System.getProperty("user.dir") + packageDir;
    }


}
