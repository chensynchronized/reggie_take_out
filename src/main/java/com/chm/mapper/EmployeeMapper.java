package com.chm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chm.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    //继承了BaseMapper接口，接口里面常见的增删改查方法都已经继承过来了

}
