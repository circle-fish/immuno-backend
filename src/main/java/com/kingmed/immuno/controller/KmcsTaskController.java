package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.model.request.TaskQueryRequest;
import com.kingmed.immuno.service.KmcsTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("getKmcsTaskById/{taskid}")
    public ResponseEntity<KmcsTask> queryById(String taskId){
        return ResponseEntity.ok(kmcsTaskService.queryById(taskId));
    }
    @PostMapping("/getKmcsTask")
     public ResponseEntity<KmcsTask> getKmcsTask(@RequestBody TaskQueryRequest req)
    {
        System.out.println(req);
        return ResponseEntity.ok(kmcsTaskService.queryById(req.getTaskId()));
    }
    @ApiOperation("查询所有KmcsTasks")
    @PostMapping("/selectAllKmcsTasks")
    public ResponseEntity<List<KmcsTask>> selectAllKmcsTasks() {
        return ResponseEntity.ok(kmcsTaskService.selectAllKmcsTasks());
    }

}