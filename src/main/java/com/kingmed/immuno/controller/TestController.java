package com.kingmed.immuno.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.DeviceMapper;
import com.kingmed.immuno.mapper.HeliosReagentMapper;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.model.dataModel.TaskAllocationResult;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.request.ExportRequest;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.impl.LabTaskAllocationServiceImpl;
import com.kingmed.immuno.util.ExcelGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @Autowired
    private HeliosReagentMapper heliosReagentMapper;
    @Autowired
    private LabTaskMapper labTaskMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private LabTaskAllocationServiceImpl labTaskAllocationService;

    /**
     * 分配后传到前端的任务HeliosLabTaskWithPostion为参数
     *
     * 打印清单接口
     * @param exportRequest
     * @param response
     * @throws IOException
     */

    @ApiOperation("导出某设备的分配结果excel")
    @GetMapping(value = "/export")
    public void exportTemplateToExcel(@RequestBody ExportRequest exportRequest, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=template" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        List<HeliosLabTaskWithPostion> virtualLabTasks = exportRequest.getHeliosLabTaskWithPostions();
//        String deviceName = virtualLabTasks.get(0).getDeviceName();
//        int reagentID = virtualLabTasks.get(0).getReagentId();
//        String operatorName = virtualLabTasks.get(0).getUpdatedBy();
//        /**
//         * 构造测试任务分配需要的参数
//         *目前一次只导出一个设备
//         */
//
//        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("device_name",deviceName);
//        List<Device> devices = deviceMapper.selectList(queryWrapper);
//
//        //???能否通过批号唯一确定试剂，不可则在LabTask中增加关于ReagentID
////        ???HeliosLabTaskWithPostion中有但是LabTask中无，创建其中的ReagentID是否有值未知
//        HeliosReagent heliosReagent = heliosReagentMapper.selectById(reagentID);
        List<LabTask> labTasks = labTaskMapper.selectList(new QueryWrapper<LabTask>().
                eq("biz_org_code","-testing@bizOrgCode1"));
        List<Device> devices = deviceMapper.selectList(new QueryWrapper<Device>().
                eq("biz_org_code","-testing@bizOrgCode1"));
        HeliosReagent heliosReagent = new HeliosReagent();


        /**
         * heliosReagent待补充
         * 分配后virtualMachineList 完成分配等操作
         */
        TaskAllocationResult result = labTaskAllocationService.heliosTaskAllocation(labTasks,
                devices,heliosReagent,"admin");

        Set<VirtualMachine> virtualMachines = labTaskAllocationService.getVirtualMachineList();

        /**
         * 今日任务导出，所有任务分配情况一键导出
         * 暂时不考虑选择部分任务导出
         * 导出所有virtualMachine
         */

        for(VirtualMachine virtualMachine  : virtualMachines){
            //设备可用且是当天的orderID
            if(virtualMachine.getStatus() == 1 && !virtualMachines.isEmpty() ){
                ExcelGenerator excelGenerator = new ExcelGenerator(virtualMachine);
                excelGenerator.generateExcelFile(exportRequest.getExcelHeaderInfo(),response);
            }
        }


    }
}
