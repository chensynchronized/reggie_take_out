package com.chm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.entity.Employee;
import com.chm.mapper.EmployeeMapper;
import com.chm.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
