package com.angelina.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.angelina.shortlink.admin.common.convention.result.Result;
import com.angelina.shortlink.admin.common.convention.result.Results;
import com.angelina.shortlink.admin.dto.resp.UserActualRespDTO;
import com.angelina.shortlink.admin.dto.resp.UserRespDTO;
import com.angelina.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查询无脱敏用户信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
        // 因为 getUserByUsername 返回值是 UserRespDTO，这里是 UserActualRespDTO，使用需要用 BeanUtil 包一下
    }

    /**
     * 查询用户名是否存在
     */
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }
}
