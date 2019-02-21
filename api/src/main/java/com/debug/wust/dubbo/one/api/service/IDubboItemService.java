package com.debug.wust.dubbo.one.api.service;

import com.debug.wust.dubbo.one.api.response.BaseResponse;

public interface IDubboItemService {

    BaseResponse listItems();

    BaseResponse listPageItems(Integer pageNo,Integer PageSize);

    BaseResponse listPageItemsParams(Integer pageNo,Integer PageSize,String search);
}
