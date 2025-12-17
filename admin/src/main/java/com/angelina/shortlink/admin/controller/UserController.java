package com.angelina.shortlink.admin.controller;

import com.angelina.shortlink.admin.common.convention.result.Result;
import com.angelina.shortlink.admin.common.convention.result.Results;
import com.angelina.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.angelina.shortlink.admin.dto.resp.UserRespDTO;
import com.angelina.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        UserRespDTO result = userService.getUserByUsername(username);
        if(result == null){
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage("用户查询为空");
        }else {
            return Results.success(result);
        }
    }
}
