package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class DeleteCheckedServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(requestParams);
        if (!requestParams.equals("")) {
            String[] pairs = requestParams.split("&");

            for (String pair : pairs) {
                int ID = contactConverter.getIDForDelete(pair);
                contactService.delContact(ID);
            }
        }
        resp.sendRedirect("/phonebook");
    }
}
