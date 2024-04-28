package com.elaine;

import com.elaine.customer.Customer;
import com.elaine.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
//@ComponentScan(basePackages = "com.elaine")
//@EnableAutoConfiguration
//@Configuration

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Main.class, args);
//        printBeans(applicationContext);
            /*
            org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
            customerController
            customerDataAccessService
            customerService
             */
    }
    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            Customer alex = new Customer("Alex", "alex@gmail.com", 21);
            Customer jamila = new Customer("jamila", "jamila@gmail.com", 25);

            List<Customer> customers = List.of(alex, jamila);
            customerRepository.saveAll(customers);
        };
    }

    @Bean("foo")
    public Foo getFoo(){
        //connection details
        //configuration classes
        return new Foo("bar");
    }
    record Foo(String name){};
    private static void printBeans(ConfigurableApplicationContext ctx){
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
