package aplicacion;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class CallbackServer  {
    
    private static baseDatos.FachadaBaseDatos fbd;
    
    public static void main(String args[]) {
        CallbackServer sv;
        sv = new CallbackServer();
        InputStreamReader is = 
        new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try{

            System.out.println(
                "Enter the RMIregistry port number:");
            portNum = (br.readLine()).trim();
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            CallbackServerImpl exportedObj = 
                new CallbackServerImpl(fbd);
            registryURL = 
                "rmi://localhost:" + portNum + "/callback";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Callback Server ready.");
        }// end try
        catch (Exception re) {
            System.out.println(
                "Exception in HelloServer.main: " + re);
        } // end catch
    } // end main   
    
    public CallbackServer() {
        fbd = new baseDatos.FachadaBaseDatos();
    }
    
    
    
    //This method starts a RMI registry on the local host, if
    //it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum)
        throws RemoteException{
        try {

            Registry registry = 
                LocateRegistry.getRegistry(RMIPortNum);
            registry.list( );  
            // This call will throw an exception
            // if the registry does not already exist
        }
        catch (RemoteException e) { 
          // No valid registry at that port.
            Registry registry = 
                LocateRegistry.createRegistry(RMIPortNum);
        }
    } // end startRegistry

} // end class
