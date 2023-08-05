package com.example.mymall.service.impl;

import com.example.mymall.dao.ProductMapper;
import com.example.mymall.enums.ProductStatus;
import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.pojo.Product;
import com.example.mymall.service.ICategoryService;
import com.example.mymall.service.IProductService;
import com.example.mymall.vo.ProductDetailVo;
import com.example.mymall.vo.ProductVo;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
 * @ClassName : ProductServiceImpl.java
 * @createTime : 2023/7/1 9:53
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<PageInfo<ProductVo>> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if(categoryId != null){
            categoryService.findSubCategoryId(categoryId, categoryIdSet);
            categoryIdSet.add(categoryId);

        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = products.stream().map(item -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(item, productVo);
            return productVo;
        }).toList();

        PageInfo<ProductVo> pageInfo = new PageInfo<>(productVoList);

        log.info("productVoList={}", productVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {

        if(productId == null){
            return ResponseVo.error(ResponseEnum.PARAM_ERROR);
        }

        Product product = productMapper.selectByPrimaryKey(productId);

        if(product.getStatus().equals(ProductStatus.OFF_SALE.getCode()) ||
                product.getStatus().equals(ProductStatus.DELETE.getCode())){
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        //敏感数据处理
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
        return ResponseVo.success(productDetailVo);
    }
}
