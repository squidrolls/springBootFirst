package com.elaine.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomer();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
    boolean existsPersonWithEmail(String email);

    boolean existsPersonWithId(Integer id);


}
