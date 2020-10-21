package com.javakc.pms.instruct.client;

import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.instruct.entity.Instruct;
import org.springframework.stereotype.Component;

@Component
public class MesInstructDegradeClient implements MesInstructClient{
    @Override
    public APICODE SaveReleaseInstruct(Instruct instruct) {
        return APICODE.ERROR().message("MES服务调用失败--下达失败");
    }
}
