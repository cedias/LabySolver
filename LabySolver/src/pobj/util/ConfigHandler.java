package pobj.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

 
public class ConfigHandler extends DefaultHandler{
	
    public void startElement(String uri, String localName,
        String qName, Attributes attributes)
    throws SAXException {
    		if("parameter".equals(qName)){
    			String name = attributes.getValue("name");
    			String value = attributes.getValue("value");
    			Configuration.getInstance().setParameterValue(name, value);
    		}
    }
   
}