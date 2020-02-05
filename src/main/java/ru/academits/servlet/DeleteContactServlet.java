package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;
import ru.academits.service.ContactValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;


public class DeleteContactServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        if (!requestParams.equals("")) {
            System.out.println("параметры: "     + requestParams);
            String[] pairs = requestParams.split("&");
            System.out.println("пары: "     + pairs[pairs.length - 1]);
            if (pairs[pairs.length - 1].equals("delSelected=")) {
                int[] iDs = contactConverter.getCheckedIDs(pairs);
                System.out.println("массив ID: " + Arrays.toString(iDs));
                for (int id: iDs
                ) {
                    contactService.delContact(id);
                }

            } else {
                int ID = contactConverter.getIDForDelete(pairs[0]);
                contactService.delContact(ID);
            }
        }
        resp.sendRedirect("/phonebook");
    }
}
