package com.javakc.pms.instruct.client;

import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.instruct.entity.Instruct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-mes",fallback = MesInstructDegradeClient.class)
@Component
public interface MesInstructClient {
    //一定与mes服务中的Controller的方法保持一致
    @PostMapping("/mes/instruct/SaveReleaseInstruct")
    public APICODE SaveReleaseInstruct(@RequestBody Instruct instruct);
}
