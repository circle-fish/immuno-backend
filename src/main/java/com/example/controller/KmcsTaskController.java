package com.example.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.KmcsTask;
import com.example.service.KmcsTaskService;

 /**
 * ;(kmcs_task)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/kmcsTask")
public class KmcsTaskController{
    @Autowired
    private KmcsTaskService kmcsTaskService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param taskId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{taskid}")
    public ResponseEntity<KmcsTask> queryById(String taskId){
        return ResponseEntity.ok(kmcsTaskService.queryById(taskId));
    }
    
    /** 
     * 分页查询
     *
     * @param kmcsTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<KmcsTask>> paginQuery(KmcsTask kmcsTask, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<KmcsTask> pageResult = kmcsTaskService.paginQuery(kmcsTask, current,size);
        //3. 分页结果组装
        List<KmcsTask> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<KmcsTask> retPage = new PageImpl<KmcsTask>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<KmcsTask> add(KmcsTask kmcsTask){
        return ResponseEntity.ok(kmcsTaskService.insert(kmcsTask));
    }
    
    /** 
     * 更新数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<KmcsTask> edit(KmcsTask kmcsTask){
        return ResponseEntity.ok(kmcsTaskService.update(kmcsTask));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String taskId){
        return ResponseEntity.ok(kmcsTaskService.deleteById(taskId));
    }
}