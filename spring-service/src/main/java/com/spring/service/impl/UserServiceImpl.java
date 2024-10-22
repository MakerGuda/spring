package com.spring.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.api.CommonPage;
import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.service.UserService;
import com.spring.service.convert.UserConvert;
import dto.req.UserCreateReq;
import dto.req.UserListReq;
import dto.res.UserDetailRes;
import dto.res.UserListRes;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConvert userConvert;

    /**
     * 新增用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(UserCreateReq req) {
        User user = userConvert.convert(req);
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 详情
     */
    @Override
    public UserDetailRes getDetailById(Long id) {
        User user = userMapper.selectById(id);
        Assert.notNull(user, "当前id无法找到匹配的用户信息");
        return userConvert.convert(user);
    }

    /**
     * 分页列表
     */
    @Override
    public CommonPage<UserListRes> getPageList(UserListReq req) {
        Page<User> page = new LambdaQueryChainWrapper<>(userMapper)
                .eq(StringUtils.isNotBlank(req.getName()), User::getName, req.getName())
                .eq(StringUtils.isNotBlank(req.getMobile()), User::getMobile, req.getMobile())
                .eq(Objects.nonNull(req.getSex()), User::getSex, req.getSex())
                .orderByDesc(User::getId).page(new Page<>(req.getPageNum(), req.getPageSize()));
        List<UserListRes> list = userConvert.convertList(page.getRecords());
        return CommonPage.getPage(page, list);
    }

}
