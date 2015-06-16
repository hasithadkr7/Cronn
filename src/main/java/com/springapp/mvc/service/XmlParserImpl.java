package com.springapp.mvc.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Hasitha on 6/16/2015.
 */
public class XmlParserImpl implements XmlParser{
    //  Customer profile changes in LS
    @Override
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
                                         String account_numbers){
        String xmlString = ""+
                "<notification>" +
                "<event_type>customer_details_change</event_type>" +
                "<id_type>"+id_type+"</id_type>" +
                "<id_number>"+id_number+"</id_number>" +
                "<title>"+title+"</title>" +
                "<first_name>"+first_name+"</first_name>" +
                "<last_name>"+last_name+"</last_name>" +
                "<gender>"+gender+"</gender>" +
                "<dob>"+dob+"</dob>" +
                "<profession>"+profession+"</profession>" +
                "<vip_status>"+vip_status+"</vip_status>" +
                "<primary_number>"+primary+"</primary_number>" +
                "<address>" +
                    "<line1>"+address_line1+"</line1>" +
                    "<line2>"+address_line2+"</line2>" +
                    "<line3>"+address_line3+"</line3>" +
                "</address>" +
                "<account_numbers>"+account_numbers+"</account_numbers>" +
                "</notification>";
        return xmlString;
    }

    //  Creating a hierarchy
    @Override
    public String xmlParserCreateHierarchy(String parent_brn,
                                           String child_brn){
        String xmlString = ""+
                "<notification>"+
                "<event_type>create_hierarchy</event_type>" +
                "<parent_brn>"+parent_brn+"</parent_brn>" +
                "<child_brn>"+child_brn+"</child_brn>" +
                "</notification>";
        return xmlString;
    }

    //  Add/Remove new numbers to a hierarchy
    @Override
    public String xmlParserUpdateNumberHierarchy(String brn,
                                                 String type,
                                                 String number_list){
        String xmlString = ""+
                "<notification>"+
                "<event_type>update_number_hierarchy</event_type>" +
                "<brn>"+brn+"</brn>" +
                "<type>"+type+"</type>" +
                "<number_list>"+number_list+"</number_list>" +
                "</notification>";
        return xmlString;
    }

    //  Change in loyalty category.
    @Override
    public String xmlParserChangeLoyalty(String nic,
                                         String previous_loyalty,
                                         String new_loyalty,
                                         String expiry_date,
                                         int points,
                                         String tag_method,
                                         String tagged_person,
                                         String date_time){
        String xmlString = ""+
                "<notification>"+
                "<event_type>change_loyalty</event_type>" +
                "<nic>"+nic+"</nic>" +
                "<previous_loyalty>"+previous_loyalty+"</previous_loyalty>" +
                "<new_loyalty>"+new_loyalty+"</new_loyalty>" +
                "<expiry_date>"+expiry_date+"</expiry_date>" +
                "<points>"+points+"</points>" +
                "<tag_method>"+tag_method+"</tag_method>" +
                "<tagged_person>"+tagged_person+"</tagged_person>" +
                "<date_time>"+date_time+"</date_time>" +
                "</notification>";
        return xmlString;
    }

    //  Primary number adding/changing.
    @Override
    public String xmlParserPrimaryNumberUpdate(String type,
                                               String previous_primary_number,
                                               String new_primary_number,
                                               String nic){
        String xmlString = ""+
                "<notification>"+
                "<event_type>primary_number_change</event_type>" +
                "<type>"+type+"</type>" +
                "<previous_primary_number>"+previous_primary_number+"</previous_primary_number>" +
                "<new_primary_number>"+new_primary_number+"</new_primary_number>" +
                "<nic>"+nic+"</nic>" +
                "</notification>";
        return xmlString;
    }

    @Override
    public String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try{
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void parseXML(String xmlString){
        Document document = convertStringToDocument(xmlString);
        document.getDocumentElement().normalize();
        System.out.println("Root element :" + document.getDocumentElement().getNodeName());
        NodeList nList = document.getElementsByTagName("notification");
        for(int temp = 0; temp < nList.getLength();temp++){
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("event_type : " + eElement.getElementsByTagName("event_type").item(0).getTextContent());
                System.out.println("id_type : " + eElement.getElementsByTagName("id_type").item(0).getTextContent());
                System.out.println("id_number : " + eElement.getElementsByTagName("id_number").item(0).getTextContent());
                for(int j=0;j<eElement.getElementsByTagName("address").getLength();j++){
                    System.out.println("address : " + eElement.getElementsByTagName("address").item(j).getTextContent());
                }
            }
        }
    }
}


//        "<account>" +
//        "<number>778523691</number>" +
//        "<lob>MOBILE</lob>" +
//        "<type>POST_PAID</type>" +
//        "<status>A</status>" +
//        "<created>10/12/2010-10:02:52</created>" +
//        "</account>" +
//        "<account>" +
//        "<number>776543212</number>" +
//        "<lob>DTV</lob>" +
//        "<type>POST_PAID</type>" +
//        "<status>ACTIVE</status>" +
//        "<created>10/12/2010-10:02:52</created>" +
//        "</account>" +