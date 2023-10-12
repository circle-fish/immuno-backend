package com.kingmed.immuno;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabOrder;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.*;
import com.kingmed.immuno.model.dataModel.*;
import com.kingmed.immuno.model.dataModel.dto.LabOrderTaskDO;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.response.BaseResponse;
import com.kingmed.immuno.service.LabOrderService;
import com.kingmed.immuno.service.factory.DeviceFactory;
import com.kingmed.immuno.service.factory.HeliosReagentFactory;
import com.kingmed.immuno.service.factory.KmcsUserFactory;
import com.kingmed.immuno.service.factory.LabOrderFactory;
import com.kingmed.immuno.service.impl.*;
import com.kingmed.immuno.util.ExcelGenerator;
import com.kingmed.immuno.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CreateDataTest {
    @Autowired
    private LabOrderFactory labOrderFactory;
    @Autowired
    private DeviceFactory deviceFactory;
    @Autowired
    private HeliosReagentFactory heliosReagentFactory;
    @Autowired
    private KmcsUserFactory kmcsUserFactory;
    @Autowired
    TaskConversionServiceImpl taskConversionService;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    @Autowired
    private MapperHelpper mapperHelpper;


    @Autowired
    private HeliosReagentMapper heliosReagentMapper;
    @Autowired
    private LabTaskMapper labTaskMapper;
    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;
    @Autowired
    private LabOrderMapper labOrderMapper;

    @Autowired
    private KmcsServiceImpl kmcsService;
    @Autowired
    private KmcsUserServiceImpl kmcsUserService;
    @Autowired
    private LabTaskServiceImpl labTaskService;
    @Autowired
    private LabOrderService labOrderService;
    @Autowired
    private LabTaskAllocationServiceImpl labTaskAllocationService;

    @Autowired
    private RedisUtil redisUtil;

    public String read(String addr) throws IOException {
        FileReader fileReader = new FileReader(addr);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }

        bufferedReader.close();
        fileReader.close();

        return stringBuilder.toString();
    }

    @Test
    public void testCreateData() throws IOException {
        LabUser  user = new LabUser("-testing@bizOrgCode1","admin");
        //needed data
        HeliosReagent heliosReagent = heliosReagentFactory.createHeliosReagent("-testing@bizOrgCode1",1);
//        LabOrder labOrder = labOrderService.checkTodayLabOrder(user);
        Device device = deviceFactory.createDevice("-testing@bizOrgCode1", 1);
        //plug in
        mapperHelpper.upsert(heliosReagent,heliosReagentMapper);
//        mapperHelpper.upsert(labOrder,labOrderMapper);
        mapperHelpper.upsert(device,deviceMapper);

        System.out.printf("测试数据创建成功:" +
                        "\n %s\n %s\n",
                heliosReagent, device);

    }
    /**
     * 把KmcsTasks转换为LabTasks
     * @throws IOException
     * @throws NoSuchFieldException
     */
    @Test
    public void testConvertKmTaskToLabTask() throws IOException, NoSuchFieldException {
        List<String> bizOrgCodes = kmcsTaskMapper.selectALlByBizOrgCode();
        //默认先设为String 之后改为泛型--输列名获取参数值

        System.out.println(bizOrgCodes);
        for (String bizOrgCode : bizOrgCodes) {
            BaseResponse<ConversionResult> response = taskConversionService.convertKmTaskToLabTask(bizOrgCode);
            if (response.getData() == null) {
                continue;
            }
            System.out.println(response.getCode());
            System.out.println(response.getMessage());
            List<LabTask> labTasks = response.getData().getLabTasks();
            System.out.println(labTasks.size());
            for (int i = 0; i < labTasks.size(); i++)
                System.out.println(JSON.toJSONString(labTasks.get(i)));
        }
        System.out.println("convertion completed .................................................");
    }


    /**
     * 按照批次处理LabTask——给LabTask分配对应的设备Device
     * 返回虚拟设备列表方便下一步 excel打印测试
     */
    @Test
    public void testTasksAllocation(){
        LabUser user = new LabUser("-testing@bizOrgCode1","admin");
        LabOrderTaskDO labOrderTaskDO = labOrderService.getTodayLabOrder(user);
        List<LabTask> labTasks = labOrderTaskDO.getLabTasks();
        
        QueryWrapper<Device> deviceQueryWrapper = new QueryWrapper<>();
        deviceQueryWrapper.eq("biz_org_code","-testing@bizOrgCode1");
        QueryWrapper<HeliosReagent> heliosReagentQueryWrapper = new QueryWrapper<>();
        heliosReagentQueryWrapper.eq("biz_org_code","-testing@bizOrgCode1");

        List<Device> devices = deviceMapper.selectList(deviceQueryWrapper);
        HeliosReagent heliosReagent = heliosReagentMapper.selectOne(heliosReagentQueryWrapper);

        labTaskAllocationService.clearVirtualMachineList();
        TaskAllocationResult result = labTaskAllocationService.heliosTaskAllocation(labTasks,
                devices,
                heliosReagent,
                "admin"
        );

        System.out.println(String.format("任务分配结果:\n %s \n %s \n %s",
                result.getSampleTasks(),result.getQcTasksForInsert(),result.getQcTasksForDelete()));
        /**
         * 打印分配结果玻片孔位的数据
         */
        Set<VirtualMachine> virtualMachines =labTaskAllocationService.getVirtualMachineList();
        System.out.println("设备数量:"+virtualMachines.size());
        for(VirtualMachine virtualMachine: virtualMachines){
            System.out.println("设备名称："+virtualMachine.getDeviceName()
            +"\n含有玻片数："+virtualMachine.getVirtualSlides().size());
            for(VirtualSlide virtualSlide : virtualMachine.getVirtualSlides()){
                virtualSlide.print();
            }
        }
        /**
         * 打印分配后任务的数据更新结果
         */
        ModificationResult DBResult = labTaskAllocationService.DBModification(result);

        for (Device device : DBResult.getDevices()) {
            System.out.println(device);
        }
        for(LabTask labTask : DBResult.getLabTasks()){
            System.out.println(labTask);
        }
        redisUtil.setCacheList("virtualMachines",DBResult.getRetVirtualMachines());
        for(VirtualMachine virtualMachine : DBResult.getRetVirtualMachines()){
            System.out.println(virtualMachine.getLabTestItemIds());
            System.out.println(virtualMachine.getLabTestItemIdList());
        }

    }

    /**
     * 测试分配流程并将结果打印出来
     * @throws IOException
     */
    @Test
    public void testExcelGenerator() throws IOException {
        List<VirtualMachine> virtualMachines = redisUtil.getCacheList("virtualMachines");
        //先打印第一个设备
        System.out.println(virtualMachines.size());
        LabUser user = new LabUser("-testing@bizOrgCode1","admin");
        LabOrder labOrder = labOrderService.checkTodayLabOrder(user);
        if(virtualMachines.size()>0){
            for(VirtualMachine virtualMachine : virtualMachines)
            {
                ExcelHeaderInfo excelHeaderInfo = new ExcelHeaderInfo(
                        1,"厂家1","检测方法1",
                        labOrder.getWet(),
                        labOrder.getTemperature()
                );
                ExcelGenerator excelGenerator = new ExcelGenerator(virtualMachine);
                excelGenerator.writeHeader(excelHeaderInfo);
                excelGenerator.write();
                excelGenerator.writeToFile("output.xlsx");
                //暂时只访问一个
                break;
            }
        }
    }

}
