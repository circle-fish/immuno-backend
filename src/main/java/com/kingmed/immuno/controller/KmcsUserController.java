package com.kingmed.immuno.controller;

import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.model.request.UserQueryRequest;
import com.kingmed.immuno.service.KmcsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
 * ;(kmcs_user)表控制层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/kmcsUser")
public class KmcsUserController{
    @Autowired
    private KmcsUserService kmcsUserService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<KmcsUser> queryById(Integer id){
        return ResponseEntity.ok(kmcsUserService.queryById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param kmcsUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<KmcsUser>> paginQuery(KmcsUser kmcsUser, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<KmcsUser> pageResult = kmcsUserService.paginQuery(kmcsUser, current,size);
        //3. 分页结果组装
        List<KmcsUser> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<KmcsUser> retPage = new PageImpl<KmcsUser>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<KmcsUser> add(KmcsUser kmcsUser){
        return ResponseEntity.ok(kmcsUserService.insert(kmcsUser));
    }
    
    /** 
     * 更新数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<KmcsUser> edit(KmcsUser kmcsUser){
        return ResponseEntity.ok(kmcsUserService.update(kmcsUser));
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
        return ResponseEntity.ok(kmcsUserService.deleteById(id));
    }

    @ApiOperation("用户登录验证")
    @PostMapping("/loginUser")
    public ResponseEntity<KmcsUser> LoginQuery(UserQueryRequest userQueryRequest) {
        String username = userQueryRequest.getUsername();
        String password = userQueryRequest.getPassword();
        String bizOrgCode = userQueryRequest.getBizOrgCode();
        return ResponseEntity.ok(kmcsUserService.LoginQuery(username,password,bizOrgCode));
    }
}