package controller.admin;

import dao.*;
import entity.*;
import org.apache.commons.beanutils.BeanUtils;
import utils.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet({
        "/admin/product/list",
        "/admin/product/create",
        "/admin/product/store",
        "/admin/product/edit",
        "/admin/product/update",
        "/admin/product/delete"
})
@MultipartConfig
public class ProductManagerController extends HttpServlet {

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private ProductColorDAO productColorDAO;
    private ProductSizeDAO productSizeDAO;
    private ColorDAO colorDAO;
    private SizeDAO sizeDAO;

    public ProductManagerController() {
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
        this.productColorDAO = new ProductColorDAO();
        this.productSizeDAO = new ProductSizeDAO();
        this.colorDAO = new ColorDAO();
        this.sizeDAO = new SizeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("list")) {
            this.index(request, response);
        } else if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            System.out.println("edit");
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Product> listProduct = productDAO.findALl();
            int count = productDAO.countProduct();

            request.setAttribute("count", count);
            request.setAttribute("listPro", listProduct);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        request.setAttribute("viewAdmin", "/views/admin/product/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Color> listColor = colorDAO.findALl();
        List<Size> listSize = sizeDAO.findALl();
        List<Categories> listCategories = categoryDAO.findAll();

        request.setAttribute("listColor", listColor);
        request.setAttribute("listSize", listSize);
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("viewAdmin", "/views/admin/product/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String[] colorIdString = request.getParameterValues("product-color[]");
            String[] sizeIdString = request.getParameterValues("product-size[]");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            int quantity = Integer.parseInt(request.getParameter("product-quantity"));
            float price = Integer.parseInt(request.getParameter("product-price"));
            String name = request.getParameter("product-name");
            String note = request.getParameter("product-note");

            Product product = new Product();

            Categories category = categoryDAO.findById(categoryId);
            product.setProductName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setNotes(note);
            product.setCategoriesByCategoryId(category);
            product.setImage(UploadUtil.uploadImage("image-product", request));
            productDAO.create(product);

            for (String id : sizeIdString) {
                int sizeId = Integer.parseInt(id);
                ProductSize productSize = new ProductSize();
                Product pid = productDAO.findById(product.getId());
                Size size = sizeDAO.findById(sizeId);

                productSize.setSizeBySizeId(size);
                productSize.setProductByProductId(pid);

                productSizeDAO.create(productSize);
            }

            for (String id : colorIdString) {
                int colorId = Integer.parseInt(id);
                ProductColor productColor = new ProductColor();
                Product pid = productDAO.findById(product.getId());
                Color color = colorDAO.findById(colorId);

                productColor.setColorByColorId(color);
                productColor.setProductByProductId(pid);

                productColorDAO.create(productColor);
            }

            response.sendRedirect("/Assignment_Java4/admin/product/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/product/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            productDAO.delete(productId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/Assignment_Java4/admin/product/list");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            System.out.println("productId = " + productId);
            List<Color> listColor = colorDAO.findALl();
            List<Size> listSize = sizeDAO.findALl();
            List<Categories> listCategories = categoryDAO.findAll();
            Product product = productDAO.findById(productId);

            request.setAttribute("listCategories", listCategories);
            request.setAttribute("listColor", listColor);
            request.setAttribute("listSize", listSize);
            request.setAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("viewAdmin", "/views/admin/product/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            System.out.println("productIdUpdate = " + productId);
            int categoryId = Integer.parseInt(request.getParameter("category"));
            String[] colorIdString = request.getParameterValues("product-color[]");
            String[] sizeIdString = request.getParameterValues("product-size[]");
            int quantity = Integer.parseInt(request.getParameter("product-quantity"));
            float price = Integer.parseInt(request.getParameter("product-price"));
            String name = request.getParameter("product-name");
            String note = request.getParameter("product-note");

            Product newProduct = new Product();
            Categories category = categoryDAO.findById(categoryId);
            Product oldProduct = productDAO.findById(productId);


            oldProduct.setProductName(name);
            oldProduct.setCategoriesByCategoryId(category);
            oldProduct.setPrice(price);
            oldProduct.setQuantity(quantity);
            oldProduct.setNotes(note);
            oldProduct.setCreatedAt(oldProduct.getCreatedAt());
            productDAO.update(oldProduct);

            response.sendRedirect("/Assignment_Java4/admin/product/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/product/edit");
        }
    }
}
