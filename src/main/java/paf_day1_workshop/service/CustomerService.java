package paf_day1_workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf_day1_workshop.model.Customer;
import paf_day1_workshop.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository custRepo;

    public List<Customer> getAllCustomers() {
        return custRepo.getAllCustomers();
    }

    public List<Customer> getAllCustomersWithLimitAndOffset(int limit, int offset) {
        return custRepo.getAllCustomersWithLimit(limit, offset);
    }

    public Customer getCustomerById(int id) {
        return custRepo.getCustomerById(id);
    }
}
