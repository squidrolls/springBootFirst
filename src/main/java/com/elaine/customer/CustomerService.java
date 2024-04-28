package com.elaine.customer;

import com.elaine.exception.DuplicateResourcesException;
import com.elaine.exception.NoDataChangeException;
import com.elaine.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomer();
    }

    public Customer getCustomer(Integer id){
    return customerDao.selectCustomerById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("customer with id [%s] not found".formatted(id)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        //check if email exists
        //add
        String email = customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email)){
            throw new DuplicateResourcesException("email already taken");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void updateCustomer(Integer id, CustomerUpdateRequest customerUpdateRequest){
        //check if the id exist
        Customer customer = customerDao.selectCustomerById(id)
                .orElseThrow(()-> new ResourceNotFoundException("customer with id [%s] not found".formatted(id)));

        //check if the updated has any changes
        //update
        boolean isChanged = false;

        if(customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            isChanged = true;
        }
        //if the new email is taken, throw exception
        if(customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())){
            if(customerDao.existsPersonWithEmail(customerUpdateRequest.email())){
                throw new DuplicateResourcesException("email already taken");
            }
            customer.setEmail(customerUpdateRequest.email());
            isChanged = true;
        }
        if(customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            isChanged = true;
        }
        if(!isChanged){
            throw new NoDataChangeException("No data change found");
        }

        customerDao.updateCustomer(customer);

    }

    public void deleteCustomer(Integer id){
        if(!customerDao.existsPersonWithId(id)){
            throw new ResourceNotFoundException("customer with id [%s] not found".formatted(id));
        }
        customerDao.deleteCustomer(id);
    }
}
