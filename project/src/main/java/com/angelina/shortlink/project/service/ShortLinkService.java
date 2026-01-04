package com.angelina.shortlink.project.service;

import com.angelina.shortlink.project.dao.entity.ShortLinkDO;
import com.angelina.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.angelina.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

}
