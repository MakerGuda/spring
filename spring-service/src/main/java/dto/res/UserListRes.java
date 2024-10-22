package dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRes {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 1:男 2:女 0:未知
     */
    private Integer sex;

    /**
     * 状态 1:启用 0:禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

}
