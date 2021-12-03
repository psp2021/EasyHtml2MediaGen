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
 * @author Paresh Sharma
 * @param args
 * @throws Exception
 */
public class EasyHtml2PdfGen {
	
	private static final String CMDTOOL= "d2todG1sdG9wZGYuZXhl";
	
	private static final String FILE_SEP = "\\";
	
	private static final String TEMP_PREFIX = "eh2pg";
	
	private static String toolPath;
	
	private static String cmdName ;
	
	public static EasyHtml2PdfGen buildEasyHtmlGen() throws Exception {
		if(cmdName == null || cmdName.isEmpty()) {
		cmdName = new String(Base64.getDecoder().decode(CMDTOOL));
		}
		if(toolPath == null || toolPath.isEmpty()) {
			initCmd();
		}
		EasyHtml2PdfGen res = new EasyHtml2PdfGen();
		return res;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new RuntimeException("Clone cannot be done");
	}
	
	private EasyHtml2PdfGen() throws Exception {
		
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
	public  void urlToPdf(final String url,final String destinationPath) throws Exception {
        Process html;
        String command = toolPath+" "+url+" "+destinationPath;
        html = Runtime.getRuntime().exec(command);
        IOUtils.copy(html.getErrorStream(), System.err);

        html.waitFor();
    }
	/**
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @throws Exception
	 */
	public  void htmlFile2PdfWithStreams(final String sourcePath,final String destinationPath) throws Exception {
        Process html;

        File destinationFile = new File(destinationPath);
        File sourceFile = new File(sourcePath);

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destinationFile);

        String command = toolPath+" - -";
        
        html = Runtime.getRuntime().exec(command);

        Thread errThread = new Thread(() -> {
            try {
                IOUtils.copy(html.getErrorStream(), System.err);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread htmlReadThread = new Thread(() -> {
            try {
                IOUtils.copy(fis, html.getOutputStream());
                html.getOutputStream().flush();
                html.getOutputStream().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread pdfWriteThread = new Thread(() -> {
            try {
                IOUtils.copy(html.getInputStream(), fos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        errThread.start();
        pdfWriteThread.start();
        htmlReadThread.start();

        
        html.waitFor(); // Allow process to run
    }
	
	/**
	 * 
	 * @param sourceHtml
	 * @param destinationPath
	 * @throws Exception
	 */
	public  void html2pdfWithStream(final StringBuilder sourceHtml,final String destinationPath) throws Exception {
        Process html;

        File destinationFile = new File(destinationPath);

        InputStream fis = new ByteArrayInputStream(sourceHtml.toString().getBytes());
        FileOutputStream fos = new FileOutputStream(destinationFile);

        String command = toolPath+" - -";
        
        html = Runtime.getRuntime().exec(command);

        Thread errThread = new Thread(() -> {
            try {
                IOUtils.copy(html.getErrorStream(), System.err);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread htmlReadThrd = new Thread(() -> {
            try {
                IOUtils.copy(fis, html.getOutputStream());
                html.getOutputStream().flush();
                html.getOutputStream().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread pdfWriteThrd = new Thread(() -> {
            try {
                IOUtils.copy(html.getInputStream(), fos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        errThread.start();
        pdfWriteThrd.start();
        htmlReadThrd.start();

        
        html.waitFor(); // Allow process to run
    }
}

