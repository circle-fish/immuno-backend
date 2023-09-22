package com.kingmed.immuno.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.LabOrder;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.mapper.LabOrderMapper;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.model.dataModel.LabUser;
import com.kingmed.immuno.model.dataModel.dto.LabOrderTaskDO;
import com.kingmed.immuno.service.LabOrderService;
import com.kingmed.immuno.service.factory.LabOrderFactory;
import com.kingmed.immuno.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单批次服务实现类
 *
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabOrderServiceImpl implements LabOrderService {
    @Autowired
    private LabOrderMapper labOrderMapper;
    @Autowired
    private LabOrderFactory labOrderFactory;
    @Autowired
    private LabTaskMapper labTaskMapper;
    @Autowired
    private MapperHelpper mapperHelpper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabOrder queryById(Integer id) {
        return labOrderMapper.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param labOrder 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Page<LabOrder> paginQuery(LabOrder labOrder, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<LabOrder> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(labOrder.getWet())) {
            queryWrapper.eq(LabOrder::getWet, labOrder.getWet());
        }
        if (StrUtil.isNotBlank(labOrder.getTemperature())) {
            queryWrapper.eq(LabOrder::getTemperature, labOrder.getTemperature());
        }
        if (StrUtil.isNotBlank(labOrder.getBizOrgCode())) {
            queryWrapper.eq(LabOrder::getBizOrgCode, labOrder.getBizOrgCode());
        }
        if (StrUtil.isNotBlank(labOrder.getCreatedBy())) {
            queryWrapper.eq(LabOrder::getCreatedBy, labOrder.getCreatedBy());
        }
        if (StrUtil.isNotBlank(labOrder.getUpdatedBy())) {
            queryWrapper.eq(LabOrder::getUpdatedBy, labOrder.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<LabOrder> pagin = new Page<>(current, size, true);
        IPage<LabOrder> selectResult = labOrderMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    public LabOrder insert(LabOrder labOrder) {
        labOrderMapper.insert(labOrder);
        return labOrder;
    }

    /**
     * 更新数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    public LabOrder update(LabOrder labOrder) {
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<LabOrder> chainWrapper = new LambdaUpdateChainWrapper<LabOrder>(labOrderMapper);
        if (StrUtil.isNotBlank(labOrder.getWet())) {
            chainWrapper.eq(LabOrder::getWet, labOrder.getWet());
        }
        if (StrUtil.isNotBlank(labOrder.getTemperature())) {
            chainWrapper.eq(LabOrder::getTemperature, labOrder.getTemperature());
        }
        if (StrUtil.isNotBlank(labOrder.getBizOrgCode())) {
            chainWrapper.eq(LabOrder::getBizOrgCode, labOrder.getBizOrgCode());
        }
        if (StrUtil.isNotBlank(labOrder.getCreatedBy())) {
            chainWrapper.eq(LabOrder::getCreatedBy, labOrder.getCreatedBy());
        }
        if (StrUtil.isNotBlank(labOrder.getUpdatedBy())) {
            chainWrapper.eq(LabOrder::getUpdatedBy, labOrder.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(LabOrder::getId, labOrder.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(labOrder.getId());
        } else {
            return labOrder;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id) {
        int total = labOrderMapper.deleteById(id);
        return total > 0;
    }

    /**
     * 获取到今天的lab_order,如果没有则创建一个
     *
     * @param labUser
     * @return 今日任务批次
     */
    @Override
    public LabOrder checkTodayLabOrder(LabUser labUser) {
        Date startTime = DateTimeUtil.getStNow();
        Date endTime = DateTimeUtil.getEdNow();
        List<LabOrder> labOrders = labOrderMapper.selectValidOrdersByTime(startTime, endTime);
        if (labOrders.isEmpty()) {
            LabOrder labOrder = labOrderFactory.createLabOrder(labUser);
            labOrderMapper.insert(labOrder);
            return labOrder;
        } else {
            return labOrders.get(0);
        }
    }

    /**
     * 获取属于当前lab_order的所有的lab_task, 并把所有尚未处理的LabTask全部绑定到当前的lab_order
     *并返回被绑定后的LabTasks
     * @param labOrder
     * @param labUser
     * @return 被绑定的LabTasks
     */
    @Override
    public List<LabTask> bindLabTaskToLabOrder(LabOrder labOrder, LabUser labUser) {

        QueryWrapper<LabTask> labTaskQueryWrapper = new QueryWrapper<>();
        labTaskQueryWrapper.eq("biz_org_code", labUser.getBizOrgCode())
                .eq("status", EnumManager.LabTaskStatus.inited.toString());

        List<LabTask> labTasks = labTaskMapper.selectList(labTaskQueryWrapper);
        for(LabTask labTask : labTasks){

            UpdateWrapper<LabTask> taskUpdateWrapper = new UpdateWrapper<>();
            taskUpdateWrapper.set("lab_order_id",labOrder.getId());
            taskUpdateWrapper.set("status", EnumManager.LabTaskStatus.binded.getValue());
            taskUpdateWrapper.set("updated_time",DateTimeUtil.getNow());
            taskUpdateWrapper.set("updated_by",labUser.getOperatorName());
            taskUpdateWrapper.set("version",labTask.getVersion()+1);

            labTaskMapper.update(labTask,taskUpdateWrapper);
        }
        /**
         * 返回当前子公司同一批次下的被修改后LabTask列表retLabTasks
         */
        QueryWrapper<LabTask> retLabTaskQueryWrapper = new QueryWrapper<>();
        retLabTaskQueryWrapper.eq("lab_order_id", labOrder.getId())
                .eq("biz_org_code", labUser.getBizOrgCode());
        List<LabTask> retLabTasks = labTaskMapper.selectList(retLabTaskQueryWrapper);

        return retLabTasks;
    }

    /**
     * 获取到今天的lab_order,如果没有则创建一个，并且根据创建情况对其进行LabTask进行绑定
     *
     * @param labUser
     * @return
     */
    @Transactional
    public LabOrderTaskDO getTodayLabOrder(LabUser labUser) {
        Date startTime = DateTimeUtil.getStNow();
        Date endTime = DateTimeUtil.getEdNow();
        QueryWrapper<LabOrder> labOrderQueryWrapper = new QueryWrapper<>();
        labOrderQueryWrapper.eq("biz_org_code", labUser.getBizOrgCode())
                .between("created_time",startTime,endTime)
                .orderByDesc("created_time")
                .last("for update");
        /**
         * 需要对符合条件的行数据进行加锁，防止其他的用户操作
         * 如何加锁能满足安全和负载均衡???
         * 此处加for update
         */

        List<LabOrder> labOrders = labOrderMapper.selectList(labOrderQueryWrapper);
        LabOrder labOrder = labOrders.get(0);

        /**
         * 如果没有今天的lab_order,则创建一个
         */
        boolean isInserted = false;
        if(labOrder == null){
            labOrder = labOrderFactory.createLabOrder(labUser);
            labOrderMapper.insert(labOrder);
            isInserted = true;
        }
        /**
         *  根据创建的情况,绑定lab_task
         */
        List<LabTask> labTasks = new ArrayList<>();
        if(isInserted){
            QueryWrapper<LabTask> labTaskQueryWrapper = new QueryWrapper<>();
            labTaskQueryWrapper.eq("biz_org_code",labOrder.getBizOrgCode())
                    .eq("status", EnumManager.LabTaskStatus.inited.toString())
                    .last("for update");
            labTasks = LabTaskBindingUpdate(labUser, labOrder, labTaskQueryWrapper);

        }else{

            QueryWrapper<LabTask> labTaskQueryWrapper = new QueryWrapper<>();
            labTaskQueryWrapper.eq("lab_order_id",labOrder.getId())
                    .eq("biz_org_code",labOrder.getBizOrgCode())
                    .eq("status", EnumManager.LabTaskStatus.inited.toString());

            labTasks = LabTaskBindingUpdate(labUser, labOrder, labTaskQueryWrapper);
        }
        if(labTasks.isEmpty() || labOrder == null) {
            throw new RuntimeException("出现异常！！任务个数"+labTasks.size()+"/n任务批次："+labOrder.toString());
        }
        return new LabOrderTaskDO(labTasks,labOrder);

    }

    /**
     * @param labOrder 
     * @return
     */
    @Override
    public LabOrder upsert(LabOrder labOrder) {
        int res = mapperHelpper.upsert(labOrder,labOrderMapper);
        if(res > 0)
        {
            return labOrder;
        }else{
            throw new ServiceException("LabOrder 的upsert失败！ id: "+ labOrder.getId());
        }
    }


    /**
     * 根据参数和查询条件构造器对要绑定的LabTask进行信息的更新
     * @param labUser
     * @param labOrder
     * @param labTaskQueryWrapper
     * @return
     */
    private List<LabTask> LabTaskBindingUpdate(LabUser labUser, LabOrder labOrder, QueryWrapper<LabTask> labTaskQueryWrapper) {
        List<LabTask> labTasks = labTaskMapper.selectList(labTaskQueryWrapper);

        for(LabTask labTask : labTasks){
            labTask.setLabOrderId(labOrder.getId());
            labTask.setStatus(EnumManager.LabTaskStatus.binded.toString());
            labTask.setUpdatedBy(labUser.getOperatorName());
            labTaskMapper.updateById(labTask);
        }
        return labTasks;
    }


}
