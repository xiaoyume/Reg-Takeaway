package com.xiaoyume.regtakeaway.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyume.regtakeaway.entity.Employee;
import com.xiaoyume.regtakeaway.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
}
