package com.example.mymall.service.impl;

import com.example.mymall.common.MallConst;
import com.example.mymall.dao.CategoryMapper;
import com.example.mymall.pojo.Category;
import com.example.mymall.service.ICategoryService;
import com.example.mymall.vo.CategoryVo;
import com.example.mymall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : CategoryServiceImpl.java
 * @createTime : 2023/6/23 16:41
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::category2CategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());
        //查询子目录
        findSubCategory(categories, categoryVoList);

        return ResponseVo.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(categories, id, resultSet);
    }

    private void findSubCategoryId(List<Category> categories, Integer id, Set<Integer> resultSet) {
        for(Category category: categories){
            if(category.getParentId().equals(id)){
                resultSet.add(category.getId());
                findSubCategoryId(categories, category.getId(), resultSet);
            }
        }
    }

    private void findSubCategory(List<Category> categories, List<CategoryVo> categoryVoList) {
        for(CategoryVo categoryVo: categoryVoList){
            List<CategoryVo> subCategoriesList = new ArrayList<>();
            for(Category category: categories){
//                如果查到子目录
                if(categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoriesList.add(subCategoryVo);
                }
            }
            subCategoriesList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
            categoryVo.setSubCategories(subCategoriesList);
            //递归查询子目录
            findSubCategory(categories, subCategoriesList);
        }
    }

    private CategoryVo category2CategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
