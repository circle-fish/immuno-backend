package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.LabTestItemRel;
import com.kingmed.immuno.service.LabTestItemRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(lab_test_item_rel)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/labTestItemRel")
public class LabTestItemRelController{
    @Autowired
    private LabTestItemRelService labTestItemRelService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<LabTestItemRel> queryById(Integer id){
        return ResponseEntity.ok(labTestItemRelService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param labTestItemRel 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<LabTestItemRel>> paginQuery(LabTestItemRel labTestItemRel, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<LabTestItemRel> pageResult = labTestItemRelService.paginQuery(labTestItemRel, current,size);
        //3. 分页结果组装
        List<LabTestItemRel> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<LabTestItemRel> retPage = new PageImpl<LabTestItemRel>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param labTestItemRel 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<LabTestItemRel> add(LabTestItemRel labTestItemRel){
        return ResponseEntity.ok(labTestItemRelService.insert(labTestItemRel));
    }
    
    /** 
     * 更新数据
     *
     * @param labTestItemRel 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<LabTestItemRel> edit(LabTestItemRel labTestItemRel){
        return ResponseEntity.ok(labTestItemRelService.update(labTestItemRel));
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
        return ResponseEntity.ok(labTestItemRelService.deleteById(id));
    }
}