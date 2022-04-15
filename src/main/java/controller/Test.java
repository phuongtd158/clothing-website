package controller;

import dao.*;
import entity.*;
import utils.EncryptUtil;
import utils.ValidateUtil;

import javax.management.relation.Role;
import java.sql.Timestamp;
import java.util.*;

public class Test {
    public static void main(String[] args) {

//        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
//        ProductDAO productDAO = new ProductDAO();
//        SizeDAO sizeDAO = new SizeDAO();
        UserDAO userDAO = new UserDAO();
//        CategoryDAO categoryDAO = new CategoryDAO();
        OrderDAO orderDAO = new OrderDAO();
        Orders o = orderDAO.findById(2);
        //orderDAO.update(o, 1);
//        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//
//        Users users = userDAO.findById(2);
//
//        Orders order = new Orders();
//        OrderDetails orderDetail = new OrderDetails();
//        Date date = new Date();
//        order.setEmail(users.getEmail());
//        order.setAddress(users.getAddress());
//        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
//        order.setStatus(0);
//        order.setFullname(users.getFullName());
//        order.setPhoneNumber(users.getPhoneNumber());
//        order.setUsersByUserId(users);
//        orderDAO.create(order);




    }
}
