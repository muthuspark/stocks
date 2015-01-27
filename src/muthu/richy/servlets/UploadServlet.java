package muthu.richy.servlets;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import muthu.richy.utility.CSVReader;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 100 * 1024;
	   private int maxMemSize = 10 * 1024;
	   private File file ;

	   public void init( ){
	      // Get the file location where it would be stored.
	      filePath = 
	             getServletContext().getInitParameter("file-upload"); 
	   }
	   public void doPost(HttpServletRequest request, 
	               HttpServletResponse response)
	              throws ServletException, java.io.IOException {
	      // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("application/json");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("Error Uploading");
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("D:\\temp\\"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            String fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            fi.write( file ) ;
	        //    System.out.println(file.getAbsolutePath());
	          //  out.println("Uploaded Filename: " + fileName + "<br>");
	         }
	      }
	      CSVReader csv = new CSVReader();
	      String responsejson = csv.applyIntelligence(file);
	      out.println(responsejson);
	   }catch(Exception ex) {
	       System.out.println(ex);
	   }
	   }
	   public void doGet(HttpServletRequest request, 
	                       HttpServletResponse response)
	        throws ServletException, java.io.IOException {
	        
	        throw new ServletException("GET method used with " +
	                getClass( ).getName( )+": POST method required.");
	   } 

}
