package com.kingmed.immuno.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.service.HeliosReagentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(helios_reagent)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-9-22
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/heliosReagent")
public class HeliosReagentController{
    @Autowired
    private HeliosReagentService heliosReagentService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("getById/{id}")
    public ResponseEntity<HeliosReagent> queryById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(heliosReagentService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param heliosReagent 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<HeliosReagent>> paginQuery(HeliosReagent heliosReagent, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<HeliosReagent> pageResult = heliosReagentService.paginQuery(heliosReagent, current,size);
        //3. 分页结果组装
        List<HeliosReagent> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<HeliosReagent> retPage = new PageImpl<HeliosReagent>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<HeliosReagent> add(HeliosReagent heliosReagent){
        return ResponseEntity.ok(heliosReagentService.insert(heliosReagent));
    }
     /**
      * 更新或插入数据
      *
      * @param heliosReagent 实例对象
      * @return 实例对象
      */
     @ApiOperation("增加或更新数据")
     @PostMapping("/upsertHeliosReagent")
     public ResponseEntity<HeliosReagent> upsert(@RequestBody HeliosReagent heliosReagent){
         return ResponseEntity.ok(heliosReagentService.upsert(heliosReagent));
     }
    
    /** 
     * 更新数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<HeliosReagent> edit(HeliosReagent heliosReagent){
        return ResponseEntity.ok(heliosReagentService.update(heliosReagent));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id){
        return ResponseEntity.ok(heliosReagentService.deleteById(id));
    }
}