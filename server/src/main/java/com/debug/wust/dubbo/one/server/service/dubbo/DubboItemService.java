package com.debug.wust.dubbo.one.server.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.debug.wust.dubbo.one.api.enums.StatusCode;
import com.debug.wust.dubbo.one.api.response.BaseResponse;
import com.debug.wust.dubbo.one.api.service.IDubboItemService;
import com.debug.wust.dubbo.one.model.entity.ItemInfo;
import com.debug.wust.dubbo.one.model.mapper.ItemInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Service(protocol = {"dubbo","rest"},validation = "true",version = "1.0",timeout = 3000)
@Path("moocOne")
public class DubboItemService implements IDubboItemService {

    //记录日志
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DubboItemService.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    /**
     * 列表查询服务,实际的业务逻辑
     *
     * @return
     */
    @Path("item/list")
    public BaseResponse listItems() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<ItemInfo> itemInfos = itemInfoMapper.selectAll();
            log.info("查询到的商品列表数据:{}", itemInfos);
            response.setData(itemInfos);
        } catch (Exception e) {
            log.error("列表查询服务发生异常", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    /**
     * 列表查询,分页查询
     * @return
     */
    @Path("item/page/list")
    public BaseResponse listPageItems(@QueryParam("pageNo") Integer pageNo,
                                      @QueryParam("PageSize") Integer PageSize) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //pageNo:第几页;PageSize:页数
            PageHelper.startPage(pageNo, PageSize);
            PageInfo<ItemInfo> info = new PageInfo<>(itemInfoMapper.selectAll());
            response.setData(info);
        } catch (Exception e) {
            log.error("列表查询分页查询服务发生异常", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }


    /**
     * 列表查询,模糊查询
     * @return
     */
    @Path("item/page/list/params")
    public BaseResponse listPageItemsParams(@QueryParam("pageNo") Integer pageNo,
                                      @QueryParam("PageSize") Integer PageSize,
                                      @QueryParam("search") String search) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //pageNo:第几页;PageSize:页数
            PageHelper.startPage(pageNo, PageSize);
            //模糊查询
            PageInfo<ItemInfo> info = new PageInfo<>(itemInfoMapper.selectAllByParams(search));
            response.setData(info);
        } catch (Exception e) {
            log.error("列表查询分页查询服务发生异常", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}