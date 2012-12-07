package pobj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Configuration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String,String> params = new HashMap<String,String>();
	private static Configuration instance = new Configuration();
	
	private Configuration() {}
	
	public static Configuration getInstance() {
		return instance;
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
	 * Import du ConfigurationLaby sauvé par la sérialisation.
	 * 
	 * @param fileName
	 *            : nom du fichier sauvé par la sérialisation
	 * @throws IOException problème de lecture du fichier ou de son contenu
	 */
	public static void chargerConfiguration(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			instance =  (Configuration) ois.readObject();
			ois.close();
		} catch (ClassCastException e) {
			ois.close();
			throw new IOException(
					"Le fichier ne contient pas une ConfigurationLaby.", e);
		} catch (ClassNotFoundException e) {
			ois.close();
			throw new IOException(
					"JVM does not know the type ConfigurationLaby.", e);
		}
		
	}

	/**
	 * Export d'une ConfigurationLaby par la sérialisation
	 * @param le nom du fichier à charger (en convention {@link File})
	 * @throws IOException En cas de problème d'écriture dans ce fichier.
	 */
	public static void sauverConfiguration(FileOutputStream fos)
			throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(instance);
		fos.close();
	}

}
