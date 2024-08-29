package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.LabTestItem;
import com.kingmed.immuno.service.LabTestItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(lab_test_item)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/labTestItem")
public class LabTestItemController{
    @Autowired
    private LabTestItemService labTestItemService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("getById/{id}")
    public ResponseEntity<LabTestItem> queryById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(labTestItemService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param labTestItem 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<LabTestItem>> paginQuery(LabTestItem labTestItem, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<LabTestItem> pageResult = labTestItemService.paginQuery(labTestItem, current,size);
        //3. 分页结果组装
        List<LabTestItem> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<LabTestItem> retPage = new PageImpl<LabTestItem>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<LabTestItem> add(LabTestItem labTestItem){
        return ResponseEntity.ok(labTestItemService.insert(labTestItem));
    }
    
    /** 
     * 更新数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<LabTestItem> edit(LabTestItem labTestItem){
        return ResponseEntity.ok(labTestItemService.update(labTestItem));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id){
        return ResponseEntity.ok(labTestItemService.deleteById(id));
    }
}