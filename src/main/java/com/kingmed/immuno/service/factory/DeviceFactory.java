package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class DeviceFactory {
    public Device createDevice(String bizOrgCode,int counter){
        Device device = new Device();
        String count = Integer.toString(counter);
        device.setBizOrgCode(bizOrgCode);
        device.setStatus(1);
        device.setDeviceType(EnumManager.DeviceType.helios.getValue());
        device.setDeviceCode("@DeviceCode"+count);
        device.setDeviceName("@Device"+count);
        //???暂定两个质控pc与nc 以及12个玻片
        device.setLabTestItemIds("");
        device.setCapacity(12);
        return device;
    }
}
