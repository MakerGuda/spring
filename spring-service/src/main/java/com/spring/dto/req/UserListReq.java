package com.spring.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListReq {

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 当前页
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageNum;

    /**
     * 页大小
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageSize;

}
