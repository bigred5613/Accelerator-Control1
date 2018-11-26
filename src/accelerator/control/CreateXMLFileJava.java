package accelerator.control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Administrator
 */
public class CreateXMLFileJava {
public static final String xmlFilePath = "/home/Administrator/Accelerator/acceleratordatatest1.xml";

	public static void main(String argv[]) {

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("Accelerator 1");
			document.appendChild(root);

			// employee element
			Element acc1 = document.createElement("Pulses");

			root.appendChild(acc1);

			// set an attribute to staff element
			Attr attr = document.createAttribute("id");
			attr.setValue("10");
			acc1.setAttributeNode(attr);

			//you can also use staff.setAttribute("id", "1") for this

			// firstname element
			Element firstName = document.createElement("firstname");
			firstName.appendChild(document.createTextNode("James"));
			acc1.appendChild(firstName);

			// lastname element
			Element lastname = document.createElement("lastname");
			lastname.appendChild(document.createTextNode("Harley"));
			acc1.appendChild(lastname);

			// email element
			Element email = document.createElement("email");
			email.appendChild(document.createTextNode("james@example.org"));
			acc1.appendChild(email);

			// department elements
			Element department = document.createElement("department");
			department.appendChild(document.createTextNode("Human Resources"));
			acc1.appendChild(department);

			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging 

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}

    

