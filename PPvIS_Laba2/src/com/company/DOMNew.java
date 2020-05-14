package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMNew {
    private List <Info> tableInfo = new ArrayList<Info>();
    String pathFile;
    private static List<Info> infoes;
    public void setInfoes(List<Info> infs, String path){
        infoes = infs;
        pathFile = path;
    }
    public static List<Info> getInfoes(){
        return infoes;
    }
    private Node createBookElement(Document doc, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    private Node createBook(Document document, String pet_name, String date_of_birth, String last_date, String doctor, String diagnosis){
        Element note = document.createElement("note");
        note.appendChild(createBookElement(document, "pet_name", pet_name));
        note.appendChild(createBookElement(document, "date_of_birth", date_of_birth));
        note.appendChild(createBookElement(document, "last_date", last_date));
        note.appendChild(createBookElement(document, "doctor", doctor));
        note.appendChild(createBookElement(document, "diagnosis", diagnosis));

        return note;
    }

    public void setBooks(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElementNS("", "table");
            document.appendChild(root);
            for(Info info : infoes){
                root.appendChild(createBook(document, info.getName_pet(), info.getDate_of_birth(), info.getLast_date(), info.getDoctor(), info.getDiagnosis()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try{
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                DOMSource source = new DOMSource(document);
                File file = new File(pathFile);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(source,streamResult);
            }catch (TransformerException e){
                e.printStackTrace();
            }
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }
    }

}
