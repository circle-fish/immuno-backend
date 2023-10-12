package com.kingmed.immuno.model.dataModel.dto;

import com.kingmed.immuno.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 在设备分配的时候使用到的虚拟设备
 * 用于判断设备有无qc任务，管理上面的孔位Slide等
 */
@Getter
@Setter
@AllArgsConstructor
public class VirtualMachine implements Serializable {

    private Integer id ;
    /** 子公司代码 */

    private String bizOrgCode ;

    /** 设备状态： 0—不可用 1—不可用 */

    private Integer status ;
    /** 设备类型 */

    private Integer deviceType ;
    /** 设备编号 */

    private String deviceCode ;
    /** 设备名称 */

    private String deviceName ;
    /** 运行的实验项目的用String表示的Id */

    private String labTestItemIds ;
    /** 容量 */

    private Integer capacity;

    /** 运行的实验项目的用整数链表表示的Id*/
    private List<Integer> labTestItemIdList;

    /**
     * 上面都是从设备里获取的属性
     * 下面的属性是在分配时使用的
     */

    private List<VirtualSlide> virtualSlides;


    private boolean hasQc = false;

    public VirtualMachine(Device device) {
        // 这些字段是从device中直接复制过来的
        this.id = device.getId();
        this.bizOrgCode = device.getBizOrgCode();
        this.status = device.getStatus();
        this.deviceType = device.getDeviceType();
        this.deviceCode = device.getDeviceCode();
        this.deviceName = device.getDeviceName();
        this.labTestItemIds = device.getLabTestItemIds();
        this.capacity = device.getCapacity();

        // 这些字段是在分配的时候使用到的
        this.virtualSlides = new ArrayList<>();
        this.labTestItemIdList = convertToLabTestItemIdList();
        this.hasQc = false;
    }

    public boolean hasQc() {
        return this.hasQc;
    }

    /**
     * 将lab_test_item_ids转换成lab_test_item_id_list
     */
    public List<Integer> convertToLabTestItemIdList(){
        List<Integer> labTestItemIdList = new ArrayList<>();
        if(labTestItemIds.isEmpty() || labTestItemIds == "" ){
            return labTestItemIdList;
        }
        for(String labTestItemIdStr : labTestItemIds.split(",")){
            /**
             * 果数字字符串的格式不正确，会抛出NumberFormatException异常
             */
            labTestItemIdList.add(Integer.parseInt(labTestItemIdStr));
        }
        return labTestItemIdList;
    }
    /**
     * 给设备添加孔位，注意labTestItemId要去重
     */
    public void addSlide(VirtualSlide virtualSlide) {
        this.virtualSlides.add(virtualSlide);
        virtualSlide.bindMachine(this);
        Integer id = virtualSlide.getLabTestItemId();
        if(!this.labTestItemIdList.contains(id)) {
            if(labTestItemIdList.size()==0){
                setLabTestItemIds(Integer.toString(id));
            }else {
                setLabTestItemIds(this.labTestItemIds.concat("," + id));
            }
            this.labTestItemIdList.add(virtualSlide.getLabTestItemId());
        }
    }
}
