package com.hcsc.claims.uiis.configuration;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Sample {

	public static void main(String[] args) throws SOAPException, IOException {
		SOAPMessage mesage = getSaopMessage();
		mesage.writeTo(System.out);
		SOAPHeader header = mesage.getSOAPHeader();
		 for (Iterator<SOAPElement> iterator = header.getChildElements(); iterator.hasNext(); ) {
			    SOAPElement child = iterator.next();
			    for(Iterator<SOAPElement> passwordElement = child.getChildElements(); passwordElement.hasNext();){
			    	SOAPElement soap = passwordElement.next();
			    	if(soap.hasAttribute("Type")){
			    		soap.addTextNode("");
			    		soap.addTextNode("dummy");
			    	}
			    	
			    }
			}

		 mesage.writeTo(System.out);
		
		
		
		
	}
	
	
	private static SOAPMessage getSaopMessage() {
		SOAPMessage soapMsg = null;
		try {
			MessageFactory factory = MessageFactory.newInstance();
			 soapMsg = factory.createMessage();
			SOAPPart part = soapMsg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPHeader header = envelope.getHeader();
			SOAPBody body = envelope.getBody();
			SOAPBodyElement element = body
					.addBodyElement(envelope.createName("JAVA", "training", "https://jitendrazaa.com/blog"));
			element.addChildElement("WS").addTextNode("Training on Web service");

			SOAPBodyElement element1 = body
					.addBodyElement(envelope.createName("JAVA", "training", "https://jitendrazaa.com/blog"));
			element1.addChildElement("Spring").addTextNode("Training on Spring 3.0");
			
			SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		    SOAPElement username = security.addChildElement("Username", "wsse");
		    username.addTextNode("dwe");

		    SOAPElement password = security.addChildElement("Password", "wsse");
		    password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
		    password.addTextNode("dae"); 

		    header.addChildElement(security);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return soapMsg;
		
		
	
		
	}

}