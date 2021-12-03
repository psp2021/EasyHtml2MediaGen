# EasyHtml2MediaGen
EasyHtml2MediaGen
This project will let you create:
------------------------
1. PDF file using html
2. PDF file using any web url
3. Image using html
4. Image using any web url



How to use the jar & code:
---------------------------------------------------------------------------------------------------------------------------------
Step 1: 
--------------------------
Download both the jar from https://github.com/psp2021/EasyHtml2MediaGen/tree/main/jar to your local machine. i.e
      1.1 EasyHtml2MediaGen.jar
      1.2 org.apache.commons.io.jar

Step 2: 
--------------------------
Use any editor for java , like eclipse,STS ,etc and create a simple java project

Step 3: 
--------------------------
Include the dowloaded jar in step 1, as libraries in the java build path.

Step 4: 
--------------------------
Create any java class in the project and write a simple main method

Step 5: 
--------------------------
Now copy below code as per the scenario and try to execute the main class (Note : Kindly change the path of the files as per your system)

      Java code to execute and use the function within jar
-------------------------------------------------------------------------------------------------
1. SAMPLE JAVA CODE for HTML to PDF
-----------------------------------------------------------------------------------------------------------------
EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen();
easyHtml2PdfGen.html2pdfWithStream(new StringBuilder("<b>Hello World!</b>"),"D:\\files\\output1.pdf");

2. SAMPLE JAVA CODE for WEB-URL to PDF
-----------------------------------------------------------------------------------------------------------------
EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen();
easyHtml2PdfGen.urlToPdf("https://en.wikipedia.org/wiki/Springer_Nature","D:\\files\\output2.pdf");

3. SAMPLE JAVA CODE for HTML-FILE 2 PDF
-----------------------------------------------------------------------------------------------------------------
EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen();
easyHtml2PdfGen.htmlFile2PdfWithStreams("D:\\files\\demo.html","D:\\files\\output3.pdf");//demo.html should contain some html text to render


4. SAMPLE JAVA CODE for HTML to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.html2ImageWithStream(new StringBuilder("<b>Hello World!</b>"), "D:\\files\\output1.jpg");


5. SAMPLE JAVA CODE for WEB-URL to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.urlToImage("https://en.wikipedia.org/wiki/Springer_Nature", "D:\\files\\output2.jpg");


6. SAMPLE JAVA CODE for HTML-FILE to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.htmlFile2ImageWithStreams("D:\\files\\demo.html", "D:\\files\\output3.jpg");//demo.html should contain some html text to render



Source Code using which the jar is generated:
------------------------------------------------
The entire java source code can be found at https://github.com/psp2021/EasyHtml2MediaGen
