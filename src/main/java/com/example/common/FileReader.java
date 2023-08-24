package com.example.common;

import com.example.entity.LabTestItem;
import com.example.entity.LabTestItemRel;
import com.example.mapper.LabTestItemMapper;
import com.example.mapper.LabTestItemRelMapper;
import com.example.model.dataModel.FileReadResult;
import com.example.model.response.BaseResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReader {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private LabTestItemMapper labTestItemMapper;

    @Autowired
    private LabTestItemRelMapper labTestItemRelMapper;


    public FileReadResult readXlsx(String fileName) throws IOException {
//        Resource resource_ = new ClassPathResource(fileName);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:" + fileName);
        if (resources.length == 0) {
            throw new IOException("File not found: " + fileName);
        }

        List<LabTestItem> labTestItems = new ArrayList<>();
        List<LabTestItemRel> labTestItemRels = new ArrayList<>();

        try {
            for (Resource resource : resources) {
                if (resource.getFilename().endsWith("xlsx")) {
                    labTestItems.addAll(labTestItemMapper.findAll());
                    labTestItemRels.addAll(labTestItemRelMapper.findAll());// 执行SQL查询
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileReadResult(labTestItems,labTestItemRels);
    }
}
