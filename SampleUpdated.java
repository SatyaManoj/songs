package com.hcsc.claims.uiis.dao.impl;

import java.io.IOException;
import java.util.Base64;
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

import org.w3c.dom.NodeList;

public class SampleUpdated {
	public static void main(String[] args) throws SOAPException, IOException {
		SOAPMessage mesage = getSaopMessage();
		mesage.writeTo(System.out);
		SOAPHeader header = mesage.getSOAPHeader();
		 for (Iterator<SOAPElement> iterator = header.getChildElements(); iterator.hasNext(); ) {
			    SOAPElement child = iterator.next();
			    	for(Iterator<SOAPElement> passwordElement = child.getChildElements(); passwordElement.hasNext();){
				    	SOAPElement soap = passwordElement.next();
				    	for(Iterator<SOAPElement> element = soap.getChildElements(); element.hasNext();){
				    		SOAPElement password = element.next();
				    		if(password.hasAttribute("Type")){
				    			password.setTextContent("satyaaaa");
					    	}
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
			  SOAPElement security =
	                    header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");



	            SOAPElement usernameToken =
	                    security.addChildElement("UsernameToken", "wsse");
	            usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

	            SOAPElement username =
	                    usernameToken.addChildElement("Username", "wsse");
	            username.addTextNode("test");

	            SOAPElement password =
	                    usernameToken.addChildElement("Password", "wsse");
	            password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
	           password.addTextNode("test321");

	          

		    header.addChildElement(security);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return soapMsg;
		
		
	
		
	}
	
}
