package com.xiaoyume.regtakeaway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoyume.regtakeaway.common.R;
import com.xiaoyume.regtakeaway.entity.Employee;
import com.xiaoyume.regtakeaway.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){
        String username = employee.getUsername();
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<Employee>();
        wrapper.eq(Employee::getUsername, username);
        Employee one = employeeService.getOne(wrapper);
        if(one == null){
            return R.error("登录失败");
        }
        if(!one.getPassword().equals(password)){
            return R.error("用户名与密码不匹配");
        }
        if(one.getStatus() == 0){
            return R.error("用户禁用");
        }
        request.getSession().setAttribute("employee", one.getId());

        /*获取用户名密码
        * 根据用户名查询，没有返回失败
        * 比对密码，密码不同失败
        * 密码相同，把id存在session中
        * */
        return R.success(one);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("成功退出");
    }

    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page pageInfo = new Page<>();
//        log.info("pagesize:{}",page);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
//        queryWrapper.
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest servletRequest, @RequestBody Employee employee){
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((Long) servletRequest.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee byId = employeeService.getById(id);
        if(byId != null){
            return R.success(byId);
        }
        return R.error("未查询到员工信息");
    }
}
