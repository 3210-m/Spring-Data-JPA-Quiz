package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.employee.entity.Company;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //以下所有的*都代表变量

    //1.查询名字是*的第一个employee
    Employee findFirstByName(String name);

    //2.找出Employee表中第一个姓名包含`*`字符并且薪资大于*的雇员个人信息
    Employee findFirstByNameLike(String name);
    //3.找出一个薪资最高且公司ID是*的雇员以及该雇员的姓名
    Employee findFirstByCompanyIdOrderBySalaryDesc(int companyId);
    //4.实现对Employee的分页查询，每页两个数据
    //default

    //5.查找**的所在的公司的公司名称
    @Query(value = "SELECT c.companyName FROM Employee e, company c WHERE e.name=?1 And e.companyId = c.id",nativeQuery = true)
     String findViewInfo(String name);
    //6.将*的名字改成*,输出这次修改影响的行数
    @Modifying
    @Query(value = "UPDATE employee e SET e.name=?1 WHERE e.name=?2",nativeQuery = true)
    int updateName( String name,String oldName);

    //7.删除姓名是*的employee
    int deleteEmployeeByName(String name);


}
