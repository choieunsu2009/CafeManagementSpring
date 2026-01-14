package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.CustomerDTO;
import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    // 고객 등록 페이지
    @GetMapping("/customer/add")
    public String customerAddView(){
        return "customer/customerAdd";
    }
    // 고객 등록
    @PostMapping("/customer/addApi")
    public String customerAdd(CustomerDTO dto, RedirectAttributes rttr){
        Customer customer = dto.toEntity();
        if(customerRepo.existsByPhone(customer.getPhone())){
            rttr.addFlashAttribute("msg", "이미 등록된 전화번호입니다");
            log.warn("이미 등록된 전화번호");
            return "redirect:/customer/add";
        }
        Customer saved = customerRepo.save(customer);
        return "redirect:/customer/info/" + saved.getUserId();
    }


    // 고객 정보 페이지
    @GetMapping("/customer/info/{id}")
    public String customerInfo(@PathVariable Long id, Model model){
        Customer customerEntity = customerRepo.findCustomerByUserId(id);
        model.addAttribute("customer", customerEntity);
        if (customerEntity == null){
            return "customer/customerNULL";
        }
        return "customer/customerInfo";
    }

    @PostMapping("/customer/edit")
    public String customerEdit(CustomerDTO dto){
        Customer customerEntity = dto.toEntity();
        Customer target = customerRepo.findCustomerByUserId(customerEntity.getUserId());

        log.info(customerEntity.toString());

        if(target != null){
            customerRepo.save(customerEntity);
            log.info("save successfully");
        }

        return "redirect:/customer/info/" + target.getUserId().toString();
    }

    @GetMapping("/customer/delete/{id}")
    public String customerDel(@PathVariable Long id){
        Customer target = customerRepo.findCustomerByUserId(id);
        if(target != null){
            customerRepo.delete(target);
        }
        return "redirect:/customer/add";
    }
}
