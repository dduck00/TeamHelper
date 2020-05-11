package com.teamhelper.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    GroupApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GroupApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();

        customerRepository.save(new Customer("ALICE", "SMITH"));
        customerRepository.save(new Customer("Lee", "DUCK"));

        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println(customerRepository.findByFirstName("Alice"));

    }
}
