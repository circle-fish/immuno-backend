package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.LabOrder;
import com.kingmed.immuno.model.dataModel.LabUser;
import org.springframework.stereotype.Component;

@Component
public class LabOrderFactory {
    public LabOrder createLabOrder(LabUser labUser){
        LabOrder labOrder = new LabOrder();
        labOrder.setBizOrgCode(labUser.getBizOrgCode());
        return labOrder;
    }
}
