package controller;

import dao.*;
import entity.*;
import utils.EncryptUtil;
import utils.ValidateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

//        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
//        ProductDAO productDAO = new ProductDAO();
//        SizeDAO sizeDAO = new SizeDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
//
//        int[] id = {1, 2, 4};
//
//        for (Integer i : id) {
//            ProductSize productSize = new ProductSize();
//            Size size = sizeDAO.findById(i);
//            Product product = productDAO.findById(62);
//
//            productSize.setProductByProductId(product);
//            productSize.setSizeBySizeId(size);
//            productSizeDAO.create(productSize);
//        }
//        if (userDAO.checkRole(3)) {
//            System.out.println(1);
//        } else {
//            System.out.println(0);
//        }

        Categories c = categoryDAO.findById(14);

        c.setName("Testttttt");
        c.setCreatedAt(c.getCreatedAt());

        categoryDAO.update(c);

    }
}
