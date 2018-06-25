package it.planetek.eosai.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 
* @author M. Cosmai
*
*/
public class DownloadZipCreator  implements Callable 
{
	private static final Logger log = LoggerFactory.getLogger(DownloadZipCreator.class);

	private Properties wqProps;
	
	/**
	* Compressione prodotti EOSAI
	* 
	* @param EOSAI_Product		Path completo del file prodotto da compattare 
	* @return output 			URL http di download del file
	* @throws IOException 
	* 
	*/
	public String zip(String EOSAI_Product) throws IOException
	{
		byte[] buffer = new byte[1024];
    	String MD_ZipFileName="";
    	String ZipFolder = 	wqProps.getProperty("download.zipfolder");
    	String WebFolder = wqProps.getProperty("download.webURL");
    	String tematic = "Thematic";
    	
    	String EOSAI_Product_fileName = Paths.get(EOSAI_Product).getFileName().toString();
    	String EOSAI_Product_without_ext = EOSAI_Product_fileName.substring(0, EOSAI_Product_fileName.lastIndexOf('.'));
    	
    	String EOSAI_Legenda =  "/legenda/Legend_";
    	if(EOSAI_Product_without_ext.contains("DOX")){
    		EOSAI_Legenda += "DOX.png";
    	}else if (EOSAI_Product_without_ext.contains("CUR")) {
    		EOSAI_Legenda += "CUR.png";
		}else if (EOSAI_Product_without_ext.contains("SAL")) {
			EOSAI_Legenda += "SAL.png";
		}else if (EOSAI_Product_without_ext.contains("SWH")) {
			EOSAI_Legenda += "SWH.png";
		}else if (EOSAI_Product_without_ext.contains("TEM")) {
			EOSAI_Legenda += "TEM.png";
		}
  	
    	MD_ZipFileName = ZipFolder + EOSAI_Product_without_ext + ".zip"; 
    	
    	try {
    		log.info("Product file: " + EOSAI_Product);
    		log.info("Legenda file: " + EOSAI_Legenda);
    		log.info("Output zip file: " + MD_ZipFileName);
    		
    		FileOutputStream fos = new FileOutputStream(MD_ZipFileName);
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry(EOSAI_Product_fileName);
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream(EOSAI_Product);
    		
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}
    		in.close();
    		zos.closeEntry();
     		//Compressione file legenda (se tematico)
   		
    		if (EOSAI_Product_without_ext.contains(tematic)){
        		ZipEntry ze1= new ZipEntry("Legenda.png");
	    		
	    		zos.putNextEntry(ze1);
	    		InputStream in1 = getClass().getResourceAsStream(EOSAI_Legenda);
	    		
	    		int len1;
	    		while ((len1 = in1.read(buffer)) > 0) {
	    			zos.write(buffer, 0, len1);
	    		}
	    		in1.close();
    		}
    		zos.closeEntry();
    		zos.close();
    		
    	} catch(IOException ex) {
    	   ex.printStackTrace();
    	   throw ex;
    	}
    	
    	return WebFolder + EOSAI_Product_without_ext + ".zip";
	}


	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception
	{
		wqProps = eventContext.getMuleContext().getRegistry().get("wqProps");

		String EOSAI_Product = eventContext.getMessage().getProperty("wqModisOutLocalFullPath", PropertyScope.INVOCATION);
		
		return zip(EOSAI_Product);
	}
	
	
}