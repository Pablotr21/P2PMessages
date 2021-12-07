package aplicacion;

import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a
 * distributed object of class CallbackServerImpl, 
 * which implements the remote interface 
 * CallbackServerInterface.  It also accepts callback
 * from the server.
 * 
 * 
 * 
 * @author M. L. Liu
 */

public class CallbackClient {
    
    private static gui.FachadaGUI fgui;

  public static void main(String args[]) {
      fgui = new gui.FachadaGUI();
    try {
      int RMIPort;         
      String hostName;
      InputStreamReader is = 
        new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      System.out.println(
        "Enter the RMIRegistry host namer:");
      hostName = br.readLine();
      System.out.println(
        "Enter the RMIregistry port number:");
      String portNum = br.readLine();
      RMIPort = Integer.parseInt(portNum); 
      String registryURL = 
        "rmi://localhost:" + portNum + "/callback";  
      // find the remote object and cast it to an 
      //   interface object
      CallbackServerInterface h =
        (CallbackServerInterface)Naming.lookup(registryURL);
      //System.out.println("Lookup completed " );
      //System.out.println("Server said " + h.sayHello());
      CallbackClientInterface callbackObj = 
        new CallbackClientImpl();
      // register for callback
      h.registerForCallback(callbackObj);
      
    } // end try 
    catch (Exception e) {
      System.out.println(
        "Exception in CallbackClient: " + e);
    } // end catch
  } //end main
}//end class
