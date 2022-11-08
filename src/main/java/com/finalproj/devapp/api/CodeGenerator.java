package com.finalproj.devapp.api;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.finalproj.devapp.api.utils.YamlUtils;

import java.util.HashMap;

public class CodeGenerator {
    private static final String PREFIX = "";
    private static final String MODULE_NAME = "";
    private static final String DATABASE_URL = YamlUtils.getStringByYaml("Mysql.url");
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_USERNAME = YamlUtils.getStringByYaml("Mysql.mysql_user");
    private static final String DATABASE_PASSWORD = YamlUtils.getStringByYaml("Mysql.mysql_password");
    private static final String BASE_PACKAGE = "com.finalproj.devapp.api";
    private static final DataSourceConfig.Builder DATA_CONFIG_BUILDER=
            new DataSourceConfig.Builder(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD)
                    .dbQuery(new MySqlQuery())
                    .schema("public")
                    .typeConvert(new MySqlTypeConvert())
                    .keyWordsHandler(new MySqlKeyWordsHandler());

    public static void main(String[] args) {
        final String parentPath = System.getProperty("user.dir")+"/src/main";
        final String projectPath = parentPath+"/java";
        final String packagePath = "/com/finalproj/devapp/api/";
        final HashMap<OutputFile, String> pathInfoMap = new HashMap<>();
        pathInfoMap.put(OutputFile.entity,projectPath+packagePath+"/model");
        pathInfoMap.put(OutputFile.xml,parentPath+"/resources/mapper");
        pathInfoMap.put(OutputFile.controller,projectPath+packagePath+"/api/");

        FastAutoGenerator.create(DATA_CONFIG_BUILDER)
                .globalConfig(builder -> {
                    builder.enableSwagger()
                            .disableOpenDir()
                            .outputDir(projectPath);
                })
                .packageConfig(builder -> {
                    builder.parent(BASE_PACKAGE)
                            .entity("domain.model")
                            .controller("api")
                            .xml(".domain.model")
                            .pathInfo(pathInfoMap);
                })
                .strategyConfig(builder -> {
                    builder.addExclude()
                            .mapperBuilder().enableBaseColumnList().enableBaseResultMap()
                            .serviceBuilder().formatServiceFileName("%sService")
                            .controllerBuilder().enableRestStyle()
                            .entityBuilder().enableTableFieldAnnotation().enableChainModel().columnNaming(NamingStrategy.underline_to_camel).naming(NamingStrategy.underline_to_camel)
                            .enableLombok();
                }).execute();


    }
}
