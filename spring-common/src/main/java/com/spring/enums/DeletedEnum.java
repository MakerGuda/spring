package com.spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeletedEnum {

    DELETED(1, "已删除"), NOT_DELETED(0, "未删除");

    private final Integer code;

    private final String desc;

}
