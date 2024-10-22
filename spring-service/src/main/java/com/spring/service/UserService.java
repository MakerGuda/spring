package com.spring.service;

import com.spring.api.CommonPage;
import dto.req.UserCreateReq;
import dto.req.UserListReq;
import dto.res.UserDetailRes;
import dto.res.UserListRes;

public interface UserService {

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
