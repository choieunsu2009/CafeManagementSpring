package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.entity.Menu;
import com.example.cafemanagementsystem.entity.Orders;
import com.example.cafemanagementsystem.entity.Point;
import com.example.cafemanagementsystem.repo.CustomerRepo;
import com.example.cafemanagementsystem.repo.MenuRepo;
import com.example.cafemanagementsystem.repo.OrderRepo;
import com.example.cafemanagementsystem.repo.PointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Slf4j
@Controller
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private PointRepo pointRepo;

    @GetMapping("/")
    public String orderAll(Model model) {
        Iterable<Orders> ordersEntities = orderRepo.findAll();
        Iterable<Menu> menus = menuRepo.findAll();

        // Orders를 OrderDTO로 변환 (상태별 플래그 포함)
        java.util.List<com.example.cafemanagementsystem.dto.OrderDTO> orderDTOs = new java.util.ArrayList<>();
        for (Orders order : ordersEntities) {
            orderDTOs.add(new com.example.cafemanagementsystem.dto.OrderDTO(order));
        }

        model.addAttribute("order", orderDTOs);
        model.addAttribute("menus", menus);
        return "order/orderList";
    }

    @GetMapping("/orders/{orderId}/next")
    public String orderStatusNext(@PathVariable Long orderId) {
        Orders target = orderRepo.findById(orderId).orElse(null);
        if (target != null) {
            target.setStatusCode(target.getStatusCode() + 1);
            orderRepo.save(target);
        }

        return "redirect:/";
    }

    @GetMapping("/orders/now")
    public String orderNotDone(Model model) {
        Iterable<Orders> ordersEntities = orderRepo.findOrderByStatusCodeIsNot(2L);
        Iterable<Menu> menus = menuRepo.findAll();

        // Orders를 OrderDTO로 변환 (상태별 플래그 포함)
        java.util.List<com.example.cafemanagementsystem.dto.OrderDTO> orderDTOs = new java.util.ArrayList<>();
        for (Orders order : ordersEntities) {
            orderDTOs.add(new com.example.cafemanagementsystem.dto.OrderDTO(order));
        }

        model.addAttribute("order", orderDTOs);
        model.addAttribute("menus", menus);
        return "order/orderList";
    }

    @GetMapping("/orders/done")
    public String orderDone(Model model) {
        Iterable<Orders> ordersEntities = orderRepo.findOrderByStatusCodeIs(2L);
        Iterable<Menu> menus = menuRepo.findAll();

        // Orders를 OrderDTO로 변환 (상태별 플래그 포함)
        java.util.List<com.example.cafemanagementsystem.dto.OrderDTO> orderDTOs = new java.util.ArrayList<>();
        for (Orders order : ordersEntities) {
            orderDTOs.add(new com.example.cafemanagementsystem.dto.OrderDTO(order));
        }

        model.addAttribute("order", orderDTOs);
        model.addAttribute("menus", menus);
        return "order/orderList";
    }

    @PostMapping("/orders")
    public String addOrder(
            @RequestParam("menuId") Long menuId,
            @RequestParam("phone") String phone,
            @RequestParam("points") Long points,
            @RequestParam("point-action") String pointAction,
            RedirectAttributes rttr,
            Model model) {

        // TODO: 실제 구현 시 Customer와 Menu 엔티티를 조회하여 Orders 생성
        // 예시:
        Menu menu = menuRepo.findById(menuId).orElse(null);
        Customer customer = customerRepo.findCustomerByPhone(phone);
        Point point = pointRepo.findLatestByUserId(customer.getUserId()) != null ? pointRepo.findLatestByUserId(customer.getUserId()) : new Point(null, false, null, 0L, customer);
//        Orders order = new Orders(null, 0L, customer, menu);
//        orderRepo.save(order);

        // 포인트 처리 로직
        if ("use".equals(pointAction)) {
            // 포인트 사용 로직
            if (menu.getPrice() > point.getTotal_point()){
                log.warn("포인트 부족");
            }
            else {
                Long leftPoint = point.getTotal_point() - menu.getPrice();
                Orders order = new Orders(null, 0L, customer, menu);
                Point point1 = new Point(null, true, menu.getPrice(), leftPoint, customer);
                orderRepo.save(order);
                pointRepo.save(point1);
            }
        } else if ("earn".equals(pointAction)) {
            // 포인트 적립 로직
            Long popo = Objects.requireNonNull(menu).getPrice()/10;
            Orders order = new Orders(null, 0L, customer, menu);
            Point point1 = new Point(null, false, popo, point.getTotal_point()+popo, customer);
            orderRepo.save(order);
            pointRepo.save(point1);
        }

        return "redirect:/";
    }

    @GetMapping("/orders/del/{orderId}")
    public String orderDel(@PathVariable Long orderId, RedirectAttributes rttr){
        Orders target = orderRepo.findById(orderId).orElse(null);
        if (target != null) {
            orderRepo.delete(target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }
        return "redirect:/";
    }
}
