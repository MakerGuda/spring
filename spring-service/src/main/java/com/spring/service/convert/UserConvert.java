package com.spring.service.convert;

import cn.hutool.crypto.digest.DigestUtil;
import com.spring.entity.User;
import dto.req.UserCreateReq;
import dto.res.UserDetailRes;
import dto.res.UserListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {DigestUtil.class})
public interface UserConvert {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createTime", expression = "java(System.currentTimeMillis())")
    @Mapping(target = "updateTime", expression = "java(System.currentTimeMillis())")
    @Mapping(target = "password", expression = "java(DigestUtil.md5Hex(req.getPassword()))")
    User convert(UserCreateReq req);

    UserDetailRes convert(User user);

    List<UserListRes> convertList(List<User> list);

    UserListRes convertList(User user);

}
