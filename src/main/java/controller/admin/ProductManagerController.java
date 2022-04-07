package controller.admin;

import dao.*;
import entity.*;

import javax.servlet.ServletException;
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
public class ProductManagerController extends HttpServlet {

    ProductDAO productDAO;
    ColorDAO colorDAO;
    SizeDAO sizeDAO;
    CategoryDAO categoryDAO;
    int idcl;
    int ids;

    public ProductManagerController() {
        this.productDAO = new ProductDAO();
        this.colorDAO = new ColorDAO();
        this.sizeDAO = new SizeDAO();
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
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

        List<Product> listProduct = productDAO.findALl();

        request.setAttribute("listPro", listProduct);
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

            String[] colorId = request.getParameterValues("product-color[]");
            String[] sizeId = request.getParameterValues("product-size[]");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            int quantity = Integer.parseInt(request.getParameter("product-quantity"));
            float price = Integer.parseInt(request.getParameter("product-price"));
            String name = request.getParameter("product-name");
            String note = request.getParameter("product-note");

            ProductColorDAO dao = new ProductColorDAO();
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            Product product = new Product();
            ProductSize productSize = new ProductSize();
            ProductColor productColor = new ProductColor();

//            for (String id : sizeId) {
//                ids = Integer.parseInt(id);
//                productSize.setSizeId(ids);
//            }


            Categories category = categoryDAO.findById(categoryId);
            product.setProductName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setNotes(note);
            product.setCategoriesByCategoryId(category);

            productDAO.create(product);

            int productId = product.getId();
            Product pid = productDAO.findById(productId);
            for (String id : sizeId) {

                idcl = Integer.parseInt(id);
                Size size = sizeDAO.findById(idcl);
                productSize.setSizeBySizeId(size);
                productSize.setProductByProductId(pid);
                productSizeDAO.create(productSize);

            }
//            for (String id : colorId) {
//                idcl = Integer.parseInt(id);
//                Color color = colorDAO.findById(idcl);
//                productColor.setColorByColorId(color);
//                productColor.setColorId(color.getId());
//                productColor.setProductByProductId(pid);
//                productColor.setProductId(product.getId());
//
//                dao.create(productColor);
//
//            }


//            productColor.setProductId(product.getId());
//            productColor.setColorId(colorId);
//            productSize.setProductId(product.getId());
//            productSize.setSizeId(sizeId);
//            BeanUtils.populate(product, request.getParameterMap());
            response.sendRedirect("/Assignment_Java4/admin/product/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/product/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("viewAdmin", "/views/admin/product/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }
}
