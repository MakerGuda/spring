package com.spring.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonPage<T> {

    /**
     * 当前页码
     */
    private Long pageNum;

    /**
     * 每页数量
     */
    private Long pageSize;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * 分页列表转换
     */
    public static <T> CommonPage<T> getPage(Page<?> page, List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        result.setTotalPage(page.getPages());
        result.setPageNum(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setList(list);
        return result;
    }


}
