package it.oreste;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import javax.jms.TextMessage;
//Import the classes to use JNDI.
import javax.naming.*;
import java.util.*;


/**
 * Servlet implementation class putMessage
 */
@WebServlet("/putMessage")
public class putMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  @Resource(lookup="jms/libertyQCF")
	  ConnectionFactory cf1;
	  
	  @Resource(lookup="jms/libertyQue")
	  Destination dst;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public putMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String title = "Putting message in a queue";
		    out.println(ServletUtilities.headWithTitle(title) +
		                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
		                "<TABLE BORDER=0 ALIGN=CENTER>\n" +
		                "<TR BGCOLOR=\"#FFAD00\">\n" +
		                "<TH>Message Input Name<TH>Message Value written in queue<TH>Status<TH>");
		    Enumeration paramNames = request.getParameterNames();
		    while(paramNames.hasMoreElements()) {
		      String paramName = (String)paramNames.nextElement();
		      out.println("<TR><TD>" + paramName + "\n<TD>");
		      String[] paramValues = request.getParameterValues(paramName);
		      if (paramValues.length == 1)
		      {
		        String paramValue = paramValues[0];
		        if (paramValue.length() == 0)
		          out.print("<I>No Value</I>");
		        else
		        { out.print(paramValue);

		          try
		          {
		            
		        	  /*
		        	   * 
		        	   * ConnectionFactory cf = (ConnectionFactory)context.lookup
                    ("java:comp/env/jms/MyConnectionFactoryRef");
            Destination dest = (Destination)context.lookup
                    ("java:comp/env/jms/MyDestinationRef");
                    
		        	   * 
		        	   */
		
		        				Connection con = cf1.createConnection();
		        				con.start();
		        				System.out.println("Connessione creata");
		        				Session session = con.createSession(false,
		        						javax.jms.Session.AUTO_ACKNOWLEDGE);

		        				MessageProducer producer = session.createProducer(dst);
		        				System.out.println("Producer creato");
		        				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		        				TextMessage msg = session.createTextMessage();
		        				msg.setText(paramValue);
		        				System.out.println("Prima di spedire");
		        				producer.send(msg);
		        			 out.println("<TD> Message sent successfully\n<TD>");
		        				//out.println("Message sent successfully");
		        	  
		         
		        	  
		             
		          }
		          catch(Exception e)
		          {
		             
		             System.out.println(e);
		            
		          }
		          
	         
		        }
		        out.println("</UL>");
		      }
		    }
		    out.println("</TABLE>\n</BODY></HTML>");
		  }
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
