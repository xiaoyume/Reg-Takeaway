package com.xiaoyume.regtakeaway.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyume.regtakeaway.entity.Category;
import com.xiaoyume.regtakeaway.mapper.CategoryMapper;
import com.xiaoyume.regtakeaway.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
