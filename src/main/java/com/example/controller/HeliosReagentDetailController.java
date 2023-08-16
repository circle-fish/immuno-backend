package com.example.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.HeliosReagentDetail;
import com.example.service.HeliosReagentDetailService;

 /**
 * ;(helios_reagent_detail)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/heliosReagentDetail")
public class HeliosReagentDetailController{
    @Autowired
    private HeliosReagentDetailService heliosReagentDetailService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<HeliosReagentDetail> queryById(Long id){
        return ResponseEntity.ok(heliosReagentDetailService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param heliosReagentDetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<HeliosReagentDetail>> paginQuery(HeliosReagentDetail heliosReagentDetail, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<HeliosReagentDetail> pageResult = heliosReagentDetailService.paginQuery(heliosReagentDetail, current,size);
        //3. 分页结果组装
        List<HeliosReagentDetail> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<HeliosReagentDetail> retPage = new PageImpl<HeliosReagentDetail>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<HeliosReagentDetail> add(HeliosReagentDetail heliosReagentDetail){
        return ResponseEntity.ok(heliosReagentDetailService.insert(heliosReagentDetail));
    }
    
    /** 
     * 更新数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<HeliosReagentDetail> edit(HeliosReagentDetail heliosReagentDetail){
        return ResponseEntity.ok(heliosReagentDetailService.update(heliosReagentDetail));
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
        return ResponseEntity.ok(heliosReagentDetailService.deleteById(id));
    }
}