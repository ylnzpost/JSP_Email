package com.web.email;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EmailServlet() {
        super();
    }
    
    private String emailSubject = "This is a Email Subject";
    private String emailContent = "This is a Email sent from Servlet";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String outHTML_01 = 
				"<html>" + 
				"<head>" +
				"</head>" + 
				"<title>" +
				"Email Message Sender" +
				"</title>" +
				"<body>";
		
		response.setContentType("text/html");
		PrintWriter outWriter = response.getWriter();
		outWriter.println(outHTML_01);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EmailBean emailer = new EmailBean();
		emailer.setSmtpHost(EmailBean.DEFAULT_HOST_SERVER);
		emailer.setFrom(EmailBean.DEFAULT_FROM);
		emailer.setTo(EmailBean.DEFAULT_TO);
		emailer.setSubject(this.emailSubject);
		emailer.setContent(this.emailContent);
		
		// Start to send email
		try
		{
			if (emailer.sendEmail())
			{
				outWriter.println("Email has been sent to " + "[" + emailer.getTo() + "]" + " successfully");
			}
		}
		catch (Exception ex)
		{
			outWriter.println(ex.getMessage());
			throw new ServletException(ex);
		}
		
		String outHTML_02 = 
				"</body>" +
				"</html>";
		outWriter.println(outHTML_02);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
