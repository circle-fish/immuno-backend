package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.entity.Device;
import org.apache.ibatis.annotations.*;
import com.example.entity.Device;
import org.springframework.stereotype.Repository;

/**
 * ;(device)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
@Repository
public interface DeviceMapper  extends BaseMapper<Device>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Device> selectByPage(IPage<Device> page , @Param(Constants.WRAPPER) Wrapper<Device> wrapper);

    /**
     * 根据id查询设备信息
     * @param id 设备id
     * @return 设备信息
     */
    @Select("SELECT * FROM device WHERE id = #{id}")
    Device selectById(@Param("id") Long id);

    /**
     * 根据id删除设备信息
     * @param id 设备id
     * @return 删除结果
     */
    @Delete("DELETE FROM device WHERE id = #{id}")
    Integer deleteById(@Param("id") Long id);

    /**
     * 更新设备信息
     * @param device 设备对象
     * @return 更新结果
     */
    @Update("UPDATE device SET\n" +
            "       biz_org_code = #{bizOrgCode},\n" +
            "       status = #{status},\n" +
            "       device_type = #{deviceType},\n" +
            "       device_code = #{deviceCode},\n" +
            "       device_name = #{deviceName},\n" +
            "       lab_test_item_ids = #{labTestItemIds},\n" +
            "       capacity = #{capacity}\n" +
            "   WHERE id = #{id}")
    Integer updateDevice(Device device);

    /**
     * 插入设备信息
     * @param device 设备对象
     * @return 插入结果
     */
    @Insert("INSERT INTO device(id, biz_org_code, status, device_type, device_code, device_name,\n" +
            "                             lab_test_item_ids, capacity)\n" +
            "        VALUES(#{id}, #{bizOrgCode}, #{status}, #{deviceType}, #{deviceCode}, #{deviceName},\n" +
            "               #{labTestItemIds}, #{capacity})")
    Integer insertDevice(Device device);
}