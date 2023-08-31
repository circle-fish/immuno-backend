package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.HeliosAiTask;
import com.kingmed.immuno.service.HeliosAiTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(helios_ai_task)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/heliosAiTask")
public class HeliosAiTaskController{
    @Autowired
    private HeliosAiTaskService heliosAiTaskService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<HeliosAiTask> queryById(Integer id){
        return ResponseEntity.ok(heliosAiTaskService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param heliosAiTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<HeliosAiTask>> paginQuery(HeliosAiTask heliosAiTask, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<HeliosAiTask> pageResult = heliosAiTaskService.paginQuery(heliosAiTask, current,size);
        //3. 分页结果组装
        List<HeliosAiTask> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<HeliosAiTask> retPage = new PageImpl<HeliosAiTask>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<HeliosAiTask> add(HeliosAiTask heliosAiTask){
        return ResponseEntity.ok(heliosAiTaskService.insert(heliosAiTask));
    }
    
    /** 
     * 更新数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<HeliosAiTask> edit(HeliosAiTask heliosAiTask){
        return ResponseEntity.ok(heliosAiTaskService.update(heliosAiTask));
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
        return ResponseEntity.ok(heliosAiTaskService.deleteById(id));
    }
}