package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.impl.SimpleCustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService = new SimpleCustomerServiceImpl();

    @GetMapping("/customer/list")
    public ModelAndView showCustomerList() {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customer/{id}")
    public ModelAndView showCustomerDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/customer/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id,
                                       @RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       @RequestParam("address") String address) {
        Customer customer = customerService.findOne(id);
        if (customer == null) {
            return new ModelAndView("error-404");
        }else {
            customer.setName(name);
            customer.setAddress(address);
            customer.setEmail(email);
            customerService.save(customer);
            ModelAndView modelAndView =  new ModelAndView("customers/info");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
    }
}
