package com.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.api.CommonPage;
import com.spring.entity.User;
import com.spring.dto.req.UserCreateReq;
import com.spring.dto.req.UserListReq;
import com.spring.dto.res.UserDetailRes;
import com.spring.dto.res.UserListRes;

public interface UserService extends IService<User> {

    /**
     * 新增用户
     */
    Long create(UserCreateReq req);

    /**
     * 详情
     */
    UserDetailRes getDetailById(Long id);

    /**
     * 分页列表
     */
    CommonPage<UserListRes> getPageList(UserListReq req);

}
