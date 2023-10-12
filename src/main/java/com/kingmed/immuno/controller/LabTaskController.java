package com.kingmed.immuno.controller;

import cn.hutool.core.lang.Tuple;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.LabUser;
import com.kingmed.immuno.service.LabTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(lab_task)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/labTask")
public class LabTaskController{
    @Autowired
    private LabTaskService labTaskService;

    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("getLabTaskById/{id}")
    public ResponseEntity<LabTask> queryById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(labTaskService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param labTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<LabTask>> paginsQuery(LabTask labTask, PageRequest pageRequest){

        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*
        *把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧
        */

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<LabTask> pageResult = labTaskService.paginQuery(labTask, current,size);
        //3. 分页结果组装
        List<LabTask> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();

        PageImpl<LabTask> retPage = new PageImpl<LabTask>(dataList,pageRequest,total);

        return ResponseEntity.ok(retPage);

    }
    
    /** 
     * 新增数据
     *
     * @RequestBody 的使用 以及url路径设置 ？？？
     * 还有是否需要定义与前端交互的参照model/request自定义类型更改
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("/addLabTask")
    public ResponseEntity<LabTask> add(@RequestBody LabTask labTask){
        return ResponseEntity.ok(labTaskService.insert(labTask));
    }
     @ApiOperation("增加或更新数据")
     @PostMapping("/upsertLabTask")
     public ResponseEntity<LabTask> upsert(@RequestBody LabTask labTask){
         return ResponseEntity.ok(labTaskService.upsert(labTask));
     }
    /** 
     * 更新数据
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<LabTask> edit(LabTask labTask){
        return ResponseEntity.ok(labTaskService.update(labTask));
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
        return ResponseEntity.ok(labTaskService.deleteById(id));
    }

    public ResponseEntity<Tuple> initTasksForInterface(){return ResponseEntity.ok(labTaskService.initTasksForInterFace());}

     public ResponseEntity<Tuple> layAsideLabTasks(List<LabTask> labTasks){
        return ResponseEntity.ok(labTaskService.layAsideLabTask(labTasks));
     }


     public ResponseEntity<Tuple> bringIntoLabTasks(List<LabTask> labTasks, LabUser labUser){
        return ResponseEntity.ok((labTaskService.bringIntoLabTask(labTasks,labUser)));
     }


}