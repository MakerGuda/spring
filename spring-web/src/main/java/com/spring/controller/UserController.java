package com.spring.controller;

import com.spring.api.CommonPage;
import com.spring.api.CommonResult;
import com.spring.service.UserService;
import com.spring.dto.req.UserCreateReq;
import com.spring.dto.req.UserListReq;
import com.spring.dto.res.UserDetailRes;
import com.spring.dto.res.UserListRes;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("create")
    public CommonResult<Long> create(@RequestBody @Validated UserCreateReq req) {
        Long userId = userService.create(req);
        return CommonResult.success(userId);
    }

    @GetMapping("detail")
    public CommonResult<UserDetailRes> detail(@RequestParam("id") Long id) {
        UserDetailRes res = userService.getDetailById(id);
        return CommonResult.success(res);
    }

    @PostMapping("list")
    public CommonResult<CommonPage<UserListRes>> getPageList(@RequestBody @Validated UserListReq req) {
        CommonPage<UserListRes> res = userService.getPageList(req);
        return CommonResult.success(res);
    }

}
