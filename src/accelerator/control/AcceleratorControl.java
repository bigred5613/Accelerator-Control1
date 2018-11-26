/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accelerator.control;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author STS
 */
public class AcceleratorControl {
public  double AccOneStep;
public  double AccTwoStep;
public  double AccOneFreq;
public  double AccTwoFreq;
public  double AccOnePhase;
public double AccTwoPhase;
public double AccOneAmp;
public double AccTwoAmp;
public  double AccOneBStep;
public  double AccTwoBStep;
public  double AccOneBFreq;
public  double AccTwoBFreq;
public  double AccOneBPhase;
public double AccTwoBPhase;
public double AccOneBAmp;
public double AccTwoBAmp;
public AllData ab;
//     private static SerialPort serialPort1;
//     private static SerialPort serialPort2;
     private static final String port1Name = "COM3";
     private static final String port2Name = "COM4";
     private static final String port1 = port1Name;
     private static final String port2 = port2Name;

     
     public static SerialPort serial1Port;

        /**
     * @param args the command line arguments
     */
/**
 * 
 * 
 * public static void main(String[] args) throws SerialPortException {
        String test = "test";
        SerialCommunicator.SerialCheck();
    //       SerialCommunicator.serialOpen(port1);
SerialPort serial1Port = new SerialPort(port1Name);
        try {
            // opening port
            serial1Port.openPort();
            
            serial1Port.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
            //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
            //                              SerialPort.FLOWCONTROL_RTSCTS_OUT);
            
            serial1Port.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            // writing string to port
            serial1Port.writeString(">Q1F");
            serial1Port.writeString("\r\n");
            
            System.out.println("String wrote to port, waiting for response..");
        }
        catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
        }
    System.out.println("Opened first port");
   
//        SerialCommunicator.serialOpen(port2);
                System.out.println("Opened second port");
        
//      NewClass.main(args);
      main_window.main(args);
      serial1Port.closePort();
              // TODO code application logic here
              
    }
    
    
    private static class PortReader implements SerialPortEventListener {



        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    // получение ответа от порта
                    String receivedData = serial1Port.readString(event.getEventValue());
                    System.out.println("Received response from port: " + receivedData);
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}
**/
     


    private static SerialPort serialPort;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] portNames = SerialPortList.getPortNames();
        SerialCommunicator.StartUp();
        if (AllData.DeadHead !=true){
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
    }
        
        // выбор порта
        System.out.println("Available com-ports:");
        for (int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        System.out.println("Type port name, which you want to use, and press Enter...");
        Scanner in = new Scanner(System.in);
        String portName = in.next();
        
        System.out.println("Type second accelerator port name, which you want to use, and press Enter...");
        Scanner in2 = new Scanner(System.in);
        String portName2 = in.next();
        
        // writing to port
        AllData.serialPort = new SerialPort(portName);
        AllData.serialPort2 = new SerialPort(portName2);
        
        
        try {
            // opening port
            AllData.serialPort.openPort();
            AllData.serialPort2.openPort();
            
            AllData.serialPort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
                        AllData.serialPort2.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
            //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
            //                              SerialPort.FLOWCONTROL_RTSCTS_OUT);
            
            AllData.serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            AllData.serialPort2.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);          
            // writing string to port
            AllData.serialPort.writeString(">Q1F");
            AllData.serialPort.writeString("\r\n");
            
            AllData.serialPort2.writeString(">Q1F");
            AllData.serialPort2.writeString("\r\n");

            
            System.out.println("Strings wrote to port, waiting for response..");
             AllData.serialPort.writeString(">Q1F");
            AllData.serialPort.writeString("\r\n");
            
            AllData.serialPort2.writeString(">Q1F");
            AllData.serialPort2.writeString("\r\n");
        }
        catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
        }
        
        // This is the main window startup command.  Add things before here if you need to.
        
            main_window.main(args, AllData.serialPort, AllData.serialPort2);

            
    }
    
    // receiving response from port
    private static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    // получение ответа от порта
                    String receivedData = AllData.serialPort.readString(event.getEventValue());
                    System.out.println("Received response from port: " + receivedData);
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}
