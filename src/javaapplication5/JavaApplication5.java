/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.net.ConnectException;
import java.net.InetAddress;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

/**
 * jamod-1.2-SNAPSHOT.jar
 * @author Manuel Junior
 */
public class JavaApplication5 {

    /**
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       try {
            String addr = "127.0.0.1";
            ModbusTCPTransaction trans;
            ReadMultipleRegistersResponse res;
            int data;
            
           InetAddress m_SlaveAddress = InetAddress.getByName(addr);
           TCPMasterConnection m_Connection;
           m_Connection = new TCPMasterConnection(m_SlaveAddress);
           m_Connection.setPort(502);
	   m_Connection.connect();
	   ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(100-1, 5);
	   req.setUnitID(1);
           trans = new ModbusTCPTransaction(m_Connection);
           trans.setRequest(req);
           trans.execute();
           res = (ReadMultipleRegistersResponse) trans.getResponse();
	   
           System.out.println("Exemplo de ler registro1: "+res.getRegisterValue(0));
           System.out.println("Exemplo de ler registro2: "+res.getRegisterValue(1));
           System.out.println("Exemplo de ler registro3: "+res.getRegisterValue(2));
           System.out.println("Exemplo de ler registro4: "+res.getRegisterValue(3));
           System.out.println("Exemplo de ler registro5: "+res.getRegisterValue(4));
           
           m_Connection.close(); 
                      
    } catch (ModbusException | ConnectException  e) {
       System.out.println("houve erro! \n"+e);
    }
        
    }

    
}
