package ru.academits.coverter;

import com.sun.xml.internal.bind.v2.model.core.ID;
import ru.academits.model.Contact;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactConverter {

    public Contact convertFormStringParam(String contactParams) throws UnsupportedEncodingException {
        Map<String, String> map = splitQuery(contactParams);
        Contact contact = new Contact();

        contact.setLastName(map.get("lastName"));
        contact.setFirstName(map.get("firstName"));
        contact.setPhone(map.get("phone"));
        return contact;
    }

    public static Map<String, String> splitQuery(String params) throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = params.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return queryPairs;
    }

    public int getIDForDelete(String requestString) throws UnsupportedEncodingException {
        Map<String, String> mapRequest = splitQuery(requestString);
        return Integer.parseInt(mapRequest.get("ID"));
    }

    public int[] getCheckedIDs(String[] pairs) {
        List<Integer> IDs = new ArrayList<>();
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String stringID = pair.substring(idx+1);
            if(!stringID.equals("")) {
                IDs.add(Integer.parseInt(stringID));
            }
        }
        return IDs.stream().mapToInt(i -> i).toArray();
    }
}
