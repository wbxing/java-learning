package com.bs.servlet;

import com.bs.bean.Book;
import com.bs.service.IBookService;
import com.bs.service.impl.BookServiceImpl;
import com.bs.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    IBookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 保存图书
        bookService.addBook(book);
        // 请求转发到图书列表界面 /manager/book?action=list
        // req.getRequestDispatcher("/manager/book?action=list").forward(req, resp);
        // 请求转发会存在 bug, 浏览器会记录最后一次请求的全部信息，用户按下功能键 F5, 就会发起浏览器记录的最后一次请求
        // 应采用请求重定向
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=list");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=list");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=list");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过 BookService 查询全部图书
        List<Book> books = bookService.queryAllBooks();
        // 把图书信息保存到 request 域中
        req.setAttribute("books", books);
        // 请求转发到 /pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

}
