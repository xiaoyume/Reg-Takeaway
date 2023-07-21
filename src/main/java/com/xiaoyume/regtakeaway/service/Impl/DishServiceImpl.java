package com.xiaoyume.regtakeaway.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyume.regtakeaway.entity.Dish;
import com.xiaoyume.regtakeaway.mapper.DishMapper;
import com.xiaoyume.regtakeaway.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
