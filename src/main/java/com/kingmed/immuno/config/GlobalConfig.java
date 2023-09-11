package com.kingmed.immuno.config;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.exception.ServiceException;
import lombok.Data;

/*
*全局配置类
* 根据环境变量设置相关接口调用Url
 */
@Data
public class GlobalConfig {
    private String heliosAiUrl = "";
    private String kmcsApiUrl = "";
    private String kmcsPlatformUrl = "";

    public GlobalConfig(EnumManager.Environment env) {
        if(env == env.FT){
            this.setHeliosAiUrl("http://10.20.3.12:8001/api/ai_inference/v1/get_ana_inference");
            this.setKmcsApiUrl("http://kmcs-ft.kingmed.com.cn: 8070/km-lb-server");
            this.setKmcsPlatformUrl("http://kmcs-ft.kingmed.com.cn:8070/km-platform-web");
        }
        else {
            throw  new ServiceException("未知的环境");
        }
    }

}
