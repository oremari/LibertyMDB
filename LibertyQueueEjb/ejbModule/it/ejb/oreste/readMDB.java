package it.ejb.oreste;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: readMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class readMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public readMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	 javax.jms.TextMessage txtMsg = (javax.jms.TextMessage)message;
    	    try 
    	    {
    	        System.out.println("INSIDE MESSAGE DRIVEN BEAN:  MESSAGE -> " + txtMsg.getText());
    	    } 
    	    catch (JMSException e) 
    	    {
    	        e.printStackTrace(System.out);
    	    }
    	}
    }


