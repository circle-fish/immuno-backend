package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(device)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/device")
public class DeviceController{
    @Autowired
    private DeviceService deviceService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Device> queryById(Integer id){
        return ResponseEntity.ok(deviceService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param device 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<Device>> paginQuery(Device device, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Device> pageResult = deviceService.paginQuery(device, current,size);
        //3. 分页结果组装
        List<Device> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Device> retPage = new PageImpl<Device>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Device> add(Device device){
        return ResponseEntity.ok(deviceService.insert(device));
    }
     
    /**
      * 更新或插入数据 
      *
      * @param  device 实例对象
      * @return 实例对象
      */
     @ApiOperation("增加或更新数据")
     @PostMapping("/upsertDevice")
     public ResponseEntity<Device> upsert(@RequestBody Device device){
         return ResponseEntity.ok(deviceService.upsert(device));
     }
    /** 
     * 更新数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Device> edit(Device device){
        return ResponseEntity.ok(deviceService.update(device));
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
        return ResponseEntity.ok(deviceService.deleteById(id));
    }
}