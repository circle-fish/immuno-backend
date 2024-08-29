package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.HeliosImage;
import com.kingmed.immuno.service.HeliosImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(helios_image)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/heliosImage")
public class HeliosImageController{
    @Autowired
    private HeliosImageService heliosImageService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("getById/{id}")
    public ResponseEntity<HeliosImage> queryById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(heliosImageService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param heliosImage 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<HeliosImage>> paginQuery(HeliosImage heliosImage, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<HeliosImage> pageResult = heliosImageService.paginQuery(heliosImage, current,size);
        //3. 分页结果组装
        List<HeliosImage> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<HeliosImage> retPage = new PageImpl<HeliosImage>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<HeliosImage> add(HeliosImage heliosImage){
        return ResponseEntity.ok(heliosImageService.insert(heliosImage));
    }
    
    /** 
     * 更新数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<HeliosImage> edit(HeliosImage heliosImage){
        return ResponseEntity.ok(heliosImageService.update(heliosImage));
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
        return ResponseEntity.ok(heliosImageService.deleteById(id));
    }
}