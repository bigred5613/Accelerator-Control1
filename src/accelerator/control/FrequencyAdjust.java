/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accelerator.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author Burgett
 */
public class FrequencyAdjust {
    public static void main(){
        
            String aboutToWrite = "";
        
                    if(AllData.isTesting = true){
                System.out.println("moving frequency up by" + AllData.AccOneStep);}
            AllData.AccOneFreq=AllData.AccOneFreq+AllData.AccOneStep;

            String setAccOneFreq = Double.toHexString(AllData.freqConv(AllData.AccOneFreq));
                        if(AllData.isTesting = true){
                System.out.println(AllData.AccOneFreq + " new hex value is: " + setAccOneFreq);}
                     aboutToWrite=(">S1F"+setAccOneFreq);
                     System.out.println(aboutToWrite);
           long setpoint = (long) AllData.freqConv(AllData.AccOneFreq);
           String hex = Long.toHexString(setpoint);
           System.out.println("hex" + hex);
           String hex2 = hex.toUpperCase();
                      System.out.println("hex" + hex2);
        try {
            AllData.serialPort.writeString(">S1F" + hex2 + "\r\n");
        } catch (SerialPortException ex) {
            System.out.println("Well, Frequency Adjust Java just failed");
            Logger.getLogger(FrequencyAdjust.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
