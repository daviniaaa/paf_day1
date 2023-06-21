package paf_day1_workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paf_day1_workshop.model.Customer;
import paf_day1_workshop.service.CustomerService;

@RestController
public class CustomerController {
    @Autowired
    CustomerService custSvc;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return custSvc.getAllCustomers();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomersWithLimitAndOffset(@RequestParam("limit") int limit, 
        @RequestParam("offset") int offset) {
        return custSvc.getAllCustomersWithLimitAndOffset(limit, offset);
    }

    @GetMapping("/{cust-id}")
    public Customer getCustomerById(@PathVariable int id) {
        return custSvc.getCustomerById(id);
    }
}
