package com.xiaoyume.regtakeaway.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyume.regtakeaway.entity.Employee;
import com.xiaoyume.regtakeaway.mapper.EmployeeMapper;
import com.xiaoyume.regtakeaway.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
