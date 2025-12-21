package com.angelina.shortlink.admin.service.impl;

import com.angelina.shortlink.admin.common.convention.exception.ClientException;
import com.angelina.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.angelina.shortlink.admin.dao.entity.UserDO;
import com.angelina.shortlink.admin.dao.mapper.UserMapper;
import com.angelina.shortlink.admin.dto.resp.UserRespDTO;
import com.angelina.shortlink.admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);

        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }

        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;

    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }
}
