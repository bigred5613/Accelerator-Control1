/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accelerator.control;

import java.io.IOException;
import jssc.SerialPortList;
import java.io.*; // IOException
import java.util.*; // Scanner
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

/**
 *
 * @author STS
 */


public class SerialCommunicator{

    public static SerialPort serialPort;
     
     public static void serialOpen(String portName){

        serialPort = new SerialPort(portName);
        try  {
            // opening port
            serialPort.openPort();
            
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
    
}        catch (SerialPortException ex) {
             Logger.getLogger(SerialCommunicator.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public static void SerialCheck(){
          String[] portNames = SerialPortList.getPortNames();
        
        if (portNames.length == 0) {
            System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
            System.out.println("Press Enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }
        
        // выбор порта
        System.out.println("Available com-ports:");
        for (int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
 /*       
        System.out.println("Type port name, which you want to use, and press Enter...");
        Scanner in = new Scanner(System.in);
        String portName = in.next();
        //This is a carryover from the old days.  leave it here for a while.
 */    }
     public static void StartUp(){
         AllData.AccOneStep = 10;
         AllData.AccTwoStep = 10;
         AllData.AccOneFreq = 2856475000L;
         AllData.AccOneBFreq= AllData.AccOneFreq;
         AllData.AccTwoFreq = 2857104000L;
         AllData.AccTwoBFreq =AllData.AccTwoFreq;
         AllData.AccOneAmp = 4095;
         
     }
         private static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    // получение ответа от порта
                    String receivedData = serialPort.readString(event.getEventValue());
                    System.out.println("Received response from port: " + receivedData);
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}



    