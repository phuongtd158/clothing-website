package controller;

import dao.ProductDAO;
import dao.ProductSizeDAO;
import dao.SizeDAO;
import entity.Product;
import entity.ProductSize;
import entity.ProductSizePK;
import entity.Size;
import utils.EncryptUtil;
import utils.ValidateUtil;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
        ProductDAO productDAO = new ProductDAO();
        SizeDAO sizeDAO = new SizeDAO();

        int[] id = {1, 2, 4};


        for (Integer i : id) {
            ProductSize productSize = new ProductSize();
            ProductSizePK pk = new ProductSizePK();
            Size size = sizeDAO.findById(i);
            Product product = productDAO.findById(58);

            pk.setSizeId(i);
            pk.setProductId(product.getId());
            productSize.setProductId(product.getId());
            productSize.setSizeId(i);
            productSize.setProductByProductId(product);
            productSize.setSizeBySizeId(size);
            productSizeDAO.create(productSize);
        }

//
    }
}
