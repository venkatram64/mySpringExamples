package com.venkat.myh2.repository;

import com.venkat.myh2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String GET_ALL_EMPLOYEES = "select * from employees";

    private static final String INSERT_EMPLOYEES = "insert into employees(first_name, last_name, address) values (?,?,?)";

    private static final String UPDATE_EMPLOYEES = "update employees set first_name = ?, last_name = ?, address = ? where id = ?";

    private static final String DELETE_EMPLOYEE_BY_ID = "delete employees where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Employee> rowMapper = (ResultSet rs, int rowNum) -> {
        Employee emp = new Employee(rs.getInt(1),
                rs.getString(2), rs.getString(3),
                rs.getString(4), rs.getString(5));
        return emp;
    };

    public List<Employee> findAll(){
        return jdbcTemplate.query(GET_ALL_EMPLOYEES, rowMapper);
    }

    public boolean save(Employee employee) {
        int result = jdbcTemplate.update(INSERT_EMPLOYEES, employee.getFirstName(), employee.getLastName(), employee.getAddress());
        return result > 0 ? true : false;
    }

    public boolean update(Employee employee) {
        int result = jdbcTemplate.update(UPDATE_EMPLOYEES, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getId());
        return result > 0 ? true : false;
    }

    public boolean deleteById(int id) {
        int result = jdbcTemplate.update(DELETE_EMPLOYEE_BY_ID, id);
        return result > 0 ? true : false;
    }
}
