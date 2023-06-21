package paf_day1_workshop.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf_day1_workshop.model.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSql = "select * from customer";
    private final String findByIdSql = "select * from customer where id = ?";
    private final String findAllLimitOffsetSql = "select * from customer limit ? offest ?";

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSql);
        // SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from customer");

        while(rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            list.add(customer);
        }

        return Collections.unmodifiableList(list);
    }

    public List<Customer> getAllCustomersWithLimit (int limit, int offset) {
        List<Customer> list = new ArrayList<>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllLimitOffsetSql);

        while(rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            list.add(customer);
        }

        return Collections.unmodifiableList(list);
    }

    public Customer getCustomerById(int id) {
        Customer customer = new Customer();

        customer = jdbcTemplate.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(
            Customer.class), id);

        return customer;
    }
}
