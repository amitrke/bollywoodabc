package com.babc.server.web.admin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class CategoryFrmPrcsSvlt extends HttpServlet {

	 private static final Logger log =
	      Logger.getLogger(FileUpload.class.getName());

	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	      throws ServletException, IOException {
	    try {
	      ServletFileUpload upload = new ServletFileUpload();
	      res.setContentType("text/plain");

	      FileItemIterator iterator = upload.getItemIterator(req);
	      while (iterator.hasNext()) {
	        FileItemStream item = iterator.next();
	        InputStream stream = item.openStream();

	        if (item.isFormField()) {
	          log.info("Got a form field: " + item.getFieldName());
	        } else {
	          log.info("Got an uploaded file: " + item.getFieldName() +
	                      ", name = " + item.getName());

	          // You now have the filename (item.getName() and the
	          // contents (which you can read from stream).  Here we just
	          // print them back out to the servlet output stream, but you
	          // will probably want to do something more interesting (for
	          // example, wrap them in a Blob and commit them to the
	          // datastore).
	          int len;
	          byte[] buffer = new byte[8192];
	          while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
	            res.getOutputStream().write(buffer, 0, len);
	          }
	        }
	      }
	    } catch (Exception ex) {
	      throw new ServletException(ex);
	    }
	  }

}
