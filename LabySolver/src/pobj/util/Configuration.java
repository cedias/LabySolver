package pobj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Configuration  {


	Map<String,String> params = new HashMap<String,String>();
	private static Configuration instance = new Configuration();
	
	private Configuration() {}
	
	public static Configuration getInstance() {
		return instance;
	}
	
	
	public Map<String, String> getParams() {
		return params;
	}

	public String getParameterValue(String param)
	{
		return params.get(param);
	}
	
	public void setParameterValue(String param, String value)
	{
		params.put(param, value);
	}
	
	/**
	 * Import du ConfigurationLaby sauvé par xml
	 * 
	 * @param fileName
	 *            : nom du fichier 
	 * @throws IOException problème de lecture du fichier ou de son contenu
	 */
public static Configuration importConfigurationData(File fileName) throws IOException , ParserConfigurationException, SAXException{
		
   		SAXParserFactory factory = SAXParserFactory.newInstance();
   		SAXParser parser = factory.newSAXParser();
   		Configuration conf = Configuration.getInstance();
   		ConfigHandler parseHandler = new ConfigHandler();
   		parser.parse(fileName, parseHandler);
   		return conf;
   		
   	}
/**
 * Export d'une ConfigurationLaby par xml
 * @param le nom du fichier à charger (en convention {@link File})
 * @throws IOException En cas de problème d'écriture dans ce fichier.
 */
   	
   	public static void exportConfigurationData(FileOutputStream fos) throws IOException{
		Writer out = new OutputStreamWriter(fos);
		out.append("<Configuration>\n");
		Configuration config = Configuration.getInstance();
		for(Map.Entry<String,String> entry : config.getParams().entrySet()){
			out.append("<parameter name='"+entry.getKey()+"' value='"+entry.getValue()+"'/>\n");
		}
		out.append("</Configuration>\n");
		out.close();
		fos.close();
}

}
