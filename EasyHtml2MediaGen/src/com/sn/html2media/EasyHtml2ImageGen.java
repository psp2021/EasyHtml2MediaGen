package com.sn.html2media;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
/**
 * 
 * @author Paresh Sharma
 *
 */
public class EasyHtml2ImageGen {
	
	private static final String CMDTOOL= "d2todG1sdG9pbWFnZS5leGU=";
	
	private static final String FILE_SEP = "\\";
	
	private static final String TEMP_PREFIX = "eh2ig";
	
	private static String toolPath;
	
	private static String cmdName ;
	
	
	
	public static EasyHtml2ImageGen buildEasyHtmlGen() throws Exception {
		if(cmdName == null || cmdName.isEmpty()) {
		cmdName = new String(Base64.getDecoder().decode(CMDTOOL));
		}
		if(toolPath == null || toolPath.isEmpty()) {
			initCmd();
		}
		EasyHtml2ImageGen res = new EasyHtml2ImageGen();
		return res;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new RuntimeException("Clone cannot be done");
	}
	
	private EasyHtml2ImageGen() throws Exception {
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public static void initCmd() throws Exception {
		InputStream stream = null;
		FileOutputStream outStream = null;
		try {
		if(toolPath==null) {
			String tempPath = Files.createTempDirectory(TEMP_PREFIX).toString();
			System.out.println(tempPath);
		stream= EasyHtml2PdfGen.class.getResourceAsStream(cmdName);
		if(stream == null) {
            throw new Exception("Cannot get resource \"" + cmdName + "\" from Jar file.");
        }
        outStream = new FileOutputStream(tempPath+FILE_SEP+cmdName);

        byte[] buffer = new byte[4096];
        int readBytes;
        while ((readBytes = stream.read(buffer)) > 0) {
        	outStream.write(buffer, 0, readBytes);
        }toolPath = tempPath+FILE_SEP+cmdName;
        }
		}finally {
			if(stream!=null) {
				stream.close();
			}
			if(outStream!=null) {
				outStream.close();
			}
		}
		
	}
	
	/**
	 * 
	 * @param url
	 * @param destinationPath
	 * @throws Exception
	 */
	public  void urlToImage(final String url,final String destinationPath) throws Exception {
        Process prhtml;
        String command = toolPath+" "+url+" "+destinationPath;
        prhtml = Runtime.getRuntime().exec(command);
        IOUtils.copy(prhtml.getErrorStream(), System.err);

        prhtml.waitFor();
    }
	/**
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @throws Exception
	 */
	public  void htmlFile2ImageWithStreams(final String sourcePath,final String destinationPath) throws Exception {
        Process wkhtml;

        File destinationFile = new File(destinationPath);
        File sourceFile = new File(sourcePath);

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destinationFile);

        String command = toolPath+" - -";
        
        wkhtml = Runtime.getRuntime().exec(command);

        Thread errThread = new Thread(() -> {
            try {
                IOUtils.copy(wkhtml.getErrorStream(), System.err);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread htmlReadThread = new Thread(() -> {
            try {
                IOUtils.copy(fis, wkhtml.getOutputStream());
                wkhtml.getOutputStream().flush();
                wkhtml.getOutputStream().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread pdfWriteThread = new Thread(() -> {
            try {
                IOUtils.copy(wkhtml.getInputStream(), fos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        errThread.start();
        pdfWriteThread.start();
        htmlReadThread.start();

        
        wkhtml.waitFor(); // Allow process to run
    }
	
	/**
	 * 
	 * @param sourceHtml
	 * @param destinationPath
	 * @throws Exception
	 */
	public  void html2ImageWithStream(final StringBuilder sourceHtml,final String destinationPath) throws Exception {
        Process prhtml;

        File destinationFile = new File(destinationPath);

        InputStream fis = new ByteArrayInputStream(sourceHtml.toString().getBytes());
        FileOutputStream fos = new FileOutputStream(destinationFile);

        String command = toolPath+" - -";
        
        prhtml = Runtime.getRuntime().exec(command);

        Thread errThread = new Thread(() -> {
            try {
                IOUtils.copy(prhtml.getErrorStream(), System.err);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread htmlReadThread = new Thread(() -> {
            try {
                IOUtils.copy(fis, prhtml.getOutputStream());
                prhtml.getOutputStream().flush();
                prhtml.getOutputStream().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread pdfWriteThread = new Thread(() -> {
            try {
                IOUtils.copy(prhtml.getInputStream(), fos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        errThread.start();
        pdfWriteThread.start();
        htmlReadThread.start();

        
        prhtml.waitFor(); // Allow process to run
    }
}
