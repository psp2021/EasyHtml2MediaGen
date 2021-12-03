# EasyHtml2MediaGen
- no <html>
EasyHtml2MediaGen
This project will let you create:

PDF file using html
PDF file using any web url
Image using html
Image using any web url




Java code to execute and use the function within jar
-------------------------------------------------------------------------------------------------
1. SAMPLE JAVA CODE for HTML to PDF
-----------------------------------------------------------------------------------------------------------------
#EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen()
#easyHtml2PdfGen.html2pdfWithStream(new StringBuilder("<h1>Hello World!</h1>"),"D:\\files\\output1.pdf");

2. SAMPLE JAVA CODE for WEB-URL to PDF
-----------------------------------------------------------------------------------------------------------------
EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen()
easyHtml2PdfGen.urlToPdf("https://en.wikipedia.org/wiki/Springer_Nature","D:\\files\\output2.pdf");

3. SAMPLE JAVA CODE for HTML-FILE 2 PDF
-----------------------------------------------------------------------------------------------------------------
EasyHtml2PdfGen easyHtml2PdfGen = 		EasyHtml2PdfGen.buildEasyHtmlGen()
easyHtml2PdfGen.htmlFile2PdfWithStreams("D:\\files\\demo.html","D:\\files\\output3.pdf");


4. SAMPLE JAVA CODE for HTML to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.html2ImageWithStream(new StringBuilder("<h1>Hello World!</h1>"), "D:\\files\\output1.jpg");


5. SAMPLE JAVA CODE for WEB-URL to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.urlToImage("https://en.wikipedia.org/wiki/Springer_Nature", "D:\\files\\output2.jpg");


6. SAMPLE JAVA CODE for HTML-FILE to Image
-----------------------------------------------------------------------------------------------------------------
EasyHtml2ImageGen easyHtml2ImageGen = EasyHtml2ImageGen.buildEasyHtmlGen();
easyHtml2ImageGen.htmlFile2ImageWithStreams("D:\\files\\demo.html", "D:\\files\\output3.jpg");


