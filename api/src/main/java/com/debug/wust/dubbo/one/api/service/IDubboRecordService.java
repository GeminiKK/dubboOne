package com.debug.wust.dubbo.one.api.service;

import com.debug.wust.dubbo.one.api.request.PushOrderDto;
import com.debug.wust.dubbo.one.api.response.BaseResponse;

public interface IDubboRecordService {

    public BaseResponse pushOrder(PushOrderDto dto);

}
