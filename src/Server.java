import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.*;

/**
 * 
 * @author A Visser, 17224047
 * 		   T Butler, 17403812
 *
 */
public class Server
{	
    public static HashSet<User> users = new HashSet<User>();
    public static Map<String,Socket> Maptest = new HashMap<String,Socket>();
    public static ArrayList<String> usernames = new  ArrayList<String>();
    public static ArrayList<String> resultsPath = new  ArrayList<String>();
    public static ArrayList<String> results = new  ArrayList<String>();
    public static Map<String, Socket> resultsSocket = new  HashMap<String, Socket>();
	public static int prevLen = 0;
	public static int curLen = 0;
	public static boolean writeBusy = false;
	public static int counter = 0;
	
	public static boolean busyOn3001 = false;
	public static boolean busyOn3002 = false;
	
    
	public static JFrame _serverFrame = new JFrame();
	public static JTextArea _serverLog = new JTextArea();
	public static JScrollPane _serverScroll = new JScrollPane();
	
	/**
	 * Main method that is run when the server is started.
	 * 
	 * @param args : empty
	 */
	public static void main(String[] args)
	{
		int portNumber = 3000;
	    boolean listening = true;
	    
	    
	    _serverFrame.getContentPane().setLayout(null);
	    _serverFrame.setSize(350, 400);
	    _serverFrame.setResizable(false);
	    _serverFrame.setTitle("ServerLogs");
	    _serverFrame.addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent evt) 
					{
						ServerThread.ServerClosed();
					}
				});
	    
	    _serverLog.setColumns(20);
	    _serverLog.setLineWrap(true);
	    _serverLog.setRows(5);
	    _serverLog.setEditable(false);
		
	    _serverScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    _serverScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    _serverScroll.setViewportView(_serverLog);
	    _serverFrame.getContentPane().add(_serverScroll);
		_serverScroll.setBounds(10, 10 , 330, 330);
	  
		_serverFrame.setVisible(true);
	    
		try
	    {
	    	ServerSocket serverSocket = new ServerSocket(portNumber);
	    	_serverLog.append("Server up!\n");
            while (listening)
            {
	            new ServerThread(serverSocket.accept()).start();
	        }
            serverSocket.close();
	    }
	    catch (IOException IOerror)
	    {
            System.err.println("Could not start server on port: " + portNumber);
            System.exit(-1);
        }

	}

}