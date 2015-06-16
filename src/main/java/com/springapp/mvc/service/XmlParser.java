package com.springapp.mvc.service;

import org.w3c.dom.Document;

/**
 * Created by Hasitha on 6/16/2015.
 */
public interface XmlParser {
    public String xmlParserProfileChange(String id_type,
                                         String id_number,
                                         String title,
                                         String first_name,
                                         String last_name,
                                         String gender,
                                         String dob,
                                         String profession,
                                         String vip_status,
                                         String primary,
                                         String address_line1,
                                         String address_line2,
                                         String address_line3,
                                         String account_numbers);

    public String xmlParserCreateHierarchy(String parent_brn,
                                           String child_brn);
    public String xmlParserUpdateNumberHierarchy(String brn,
                                                 String type,
                                                 String number_list);
    public String xmlParserChangeLoyalty(String nic,
                                         String previous_loyalty,
                                         String new_loyalty,
                                         String expiry_date,
                                         int points,
                                         String tag_method,
                                         String tagged_person,
                                         String date_time);
    public String xmlParserPrimaryNumberUpdate(String type,
                                               String previous_primary_number,
                                               String new_primary_number,
                                               String nic);
    public String convertDocumentToString(Document doc);
    public Document convertStringToDocument(String xmlStr);
    public void parseXML(String xmlString);
}
