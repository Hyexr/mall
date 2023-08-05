package com.example.mymall.service;

import com.example.mymall.vo.CategoryVo;
import com.example.mymall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service
 * @ClassName : ICategoryService.java
 * @createTime : 2023/6/23 16:40
 */
public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
