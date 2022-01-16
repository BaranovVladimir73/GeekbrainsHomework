package ru.gb.servlet;

import ru.gb.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductHttpServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    public ProductServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", this.getProductList());
        this.getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList();
        productList.add(new Product(1, "bread", 40.99D));
        productList.add(new Product(2, "fish", 240.99D));
        productList.add(new Product(3, "Vodka", 340.99D));
        productList.add(new Product(4, "yogurt with pineapple", 55.99D));
        productList.add(new Product(5, "Kefir", 47.99D));
        productList.add(new Product(6, "Indian Tea", 140.99D));
        productList.add(new Product(7, "Sunflower oil", 120.99D));
        productList.add(new Product(8, "Salt", 10.99D));
        productList.add(new Product(9, "Sugar", 100.99D));
        productList.add(new Product(10, "Kwas", 99.99D));
        return productList;
    }
}
