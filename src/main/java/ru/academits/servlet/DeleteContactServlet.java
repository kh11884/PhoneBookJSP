package ru.academits.servlet;
import ru.academits.PhoneBook;
import ru.academits.coverter.RequestConverter;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class DeleteContactServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private RequestConverter requestConverter = PhoneBook.requestConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        if (!requestParams.equals("")) {
            int[] toDeleteIDs = requestConverter.getIDs(requestParams);
            for (int id : toDeleteIDs) {
                contactService.delContact(id);
            }
        }
        resp.sendRedirect("/phonebook");
    }
}
