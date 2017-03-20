package com.servelet.vj;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "My 1st Servelet", urlPatterns = { "/ServeletTrial1" })
public class ServeletTrial1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.print("1st Servlet");
		reportType("doGet_1", response);
		response.setContentType("text/html");
		PrintWriter pr = response.getWriter();
		pr.println("<html><body>");
		pr.println("<h2>imported form servelet</h2>");
		pr.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		reportType("doPost", response);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reportType("doPut", response);
		doPost(request, response);
	
	}
	
	public void reportType(String requestType, HttpServletResponse response) throws IOException, ServletException{
	  response.setContentType("text/html");
	
	  PrintWriter out = response.getWriter();
	
	  out.println("<html>");
	  out.println("<head>");
	  out.println("<title>doGetdoPostdoPutServlet" + "</title>");
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<h1>Your Request</h1>");
	  out.println("Your request type: " + requestType);
	  out.println("</body>");
	  out.println("</html>");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
		// get the file to delete
		File file = null;
		try {
			file = new File (searchFile(request));
		} catch (Exception e) {
			e.printStackTrace();
		}


		if (!file.exists()){
		response.sendError(HttpServletResponse.SC_NOT_FOUND); 
		return;
		}else{
		//delete the file if it is exist.
		success=file.delete(); 
		}

		if (success){
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}
	
	private String searchFile(HttpServletRequest req) throws Exception{
		// get the file to view
		String fileName = req.getPathInfo();

		// strip off the leading slash
		fileName = fileName.substring(1);

		return fileName;
	}
	
	public void destroy() {
		System.out.println("destroyed");
	}

}
