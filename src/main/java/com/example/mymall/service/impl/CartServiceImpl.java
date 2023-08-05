package com.example.mymall.service.impl;

import com.example.mymall.dao.ProductMapper;
import com.example.mymall.enums.ProductStatus;
import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.form.CartAddForm;
import com.example.mymall.form.CartUpdateForm;
import com.example.mymall.pojo.Cart;
import com.example.mymall.pojo.Product;
import com.example.mymall.service.ICartService;
import com.example.mymall.vo.CartProductVo;
import com.example.mymall.vo.CartVo;
import com.example.mymall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : CartServiceImpl.java
 * @createTime : 2023/7/3 14:49
 */
@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm) {
        Integer quantity = 1;
        //商品存在
        Product product = productMapper.selectByPrimaryKey(cartAddForm.getProductId());
        if (product == null) {
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        //在售
        if (!product.getStatus().equals(ProductStatus.ON_SALE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        //库存
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        //写入到redis
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Cart cart = opsForHash.get(key, String.valueOf(product.getId()));
        if(cart == null){
            //没有该商品，新增
            cart = new Cart(product.getId(), quantity, cartAddForm.getSelected());
        }else{
            //已经有了，数量+1
            cart.setQuantity(cart.getQuantity() + quantity);
        }
        opsForHash.put(String.format(CART_REDIS_KEY_TEMPLATE, uid), String.valueOf(product.getId()), cart);
        log.info("cart:{}", cart);

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, Cart> entries = opsForHash.entries(key);

        // cartVo字段
        boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();

        // 把cart里的Id存入一个set里
        Set<Integer> productIdSet = entries.keySet().stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toSet());


        // 根据productIdSet查询数据库
        List<Product> productList = new ArrayList<>();
        if(!productIdSet.isEmpty()){
            productList = productMapper.selectByProductIdSet(productIdSet);
        }

        // 把product和cart合并
        for(Product product: productList){
            Cart cart = entries.get(String.valueOf(product.getId()));
            CartProductVo cartProductVo = new CartProductVo(product.getId(), cart.getQuantity(), product.getName(),
                    product.getSubtitle(), product.getMainImage(), product.getPrice(), product.getStatus(),
                    product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())), product.getStock(),
                    cart.getProductSelected());

            cartProductVoList.add(cartProductVo);
            if(!cart.getProductSelected()){
                selectAll = false;
            }
            if(cart.getProductSelected()){
                cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
            }
            cartTotalQuantity += cart.getQuantity();
        }

//        for (Map.Entry<String, Cart> entry : entries.entrySet()) {
//            Integer productId = Integer.valueOf(entry.getKey());
//            Cart cart = entry.getValue();
//
//
//            Product product = productMapper.selectByPrimaryKey(productId);
//            if(product != null){
//                CartProductVo cartProductVo = new CartProductVo(product.getId(), cart.getQuantity(), product.getName(),
//                        product.getSubtitle(), product.getMainImage(), product.getPrice(), product.getStatus(),
//                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())), product.getStock(),
//                        cart.getProductSelected());
//
//                cartProductVoList.add(cartProductVo);
//                if(!cart.getProductSelected()){
//                    selectAll = false;
//                }
//                if(cart.getProductSelected()){
//                cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
//                }
//            }
//            cartTotalQuantity += cart.getQuantity();
//        }

        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setAllChecked(selectAll);
        cartVo.setCartTotalPrice(cartTotalPrice);

        return ResponseVo.success(cartVo);

    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Cart cart = opsForHash.get(key, String.valueOf(productId));
        if(cart == null){
            //没有该商品，报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        if(cartUpdateForm.getQuantity() != null && cartUpdateForm.getQuantity() >= 0){
            cart.setQuantity(cartUpdateForm.getQuantity());
        }
        if(cartUpdateForm.getSelected() != null){
            cart.setProductSelected(cartUpdateForm.getSelected());
        }
        opsForHash.put(key, String.valueOf(productId), cart);

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Cart cart = opsForHash.get(key, String.valueOf(productId));
        if(cart == null){
            //没有该商品，报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        opsForHash.delete(key, String.valueOf(productId));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> cartList = opsForHash.values(key);
        for (Cart cart : cartList) {
            cart.setProductSelected(true);
            opsForHash.put(String.format(CART_REDIS_KEY_TEMPLATE, uid), String.valueOf(cart.getProductId()), cart);
        }

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> cartList = opsForHash.values(key);
        for (Cart cart : cartList) {
            cart.setProductSelected(false);
            opsForHash.put(String.format(CART_REDIS_KEY_TEMPLATE, uid), String.valueOf(cart.getProductId()), cart);
        }

        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> cartList = opsForHash.values(key);
        Integer sum = cartList.stream().
                map(Cart::getQuantity).
                reduce(0, Integer::sum);

        return ResponseVo.success(sum);
    }

    @Override
    public List<Cart> listForCart(Integer uid){
        HashOperations<String, String, Cart> opsForHash = redisTemplate.opsForHash();
        String key = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        return opsForHash.values(key);
    }
}
