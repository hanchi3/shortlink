package com.angelina.shortlink.project.dto.req;

import com.angelina.shortlink.project.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;
}
