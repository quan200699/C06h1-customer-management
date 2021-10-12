package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ModelAndView showCustomerList() {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    // /customer => cho list
    // /customer/1 => sẽ bị báo lỗi là trùng mapping
    @GetMapping("/{id}")
    public ModelAndView showCustomerDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id,
                                       @ModelAttribute(name = "customer") Customer customer) {
        Customer oldCustomer = customerService.findOne(id);
        if (oldCustomer == null) {
            return new ModelAndView("error-404");
        } else {
            oldCustomer.setName(customer.getName());
            oldCustomer.setAddress(customer.getAddress());
            oldCustomer.setEmail(customer.getEmail());
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("customers/info");
            modelAndView.addObject("customer", oldCustomer);
            return modelAndView;
        }
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute(name = "customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
}
