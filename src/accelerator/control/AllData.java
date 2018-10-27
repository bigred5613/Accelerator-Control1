/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accelerator.control;

import jssc.SerialPort;

/**
 *
 * @author STS
 */
public class AllData {
    public static  int AccOneStep;
    public static int AccTwoStep;
    public static double AccOneFreq;
    public static double AccTwoFreq;
    public static double AccOnePhase;
    public static double AccTwoPhase;
    public static double AccOneAmp;
    public static double AccTwoAmp;
    public static int AccOneBStep;
    public static int AccTwoBStep;
    public static double AccOneBFreq;
    public static double AccTwoBFreq;
    public static double AccOneBPhase;
    public static double AccTwoBPhase;
    public static double AccOneBAmp;
    public static double AccTwoBAmp;
    public static final double maxAmp = 4095;
    public static final double maxPhase= 65536;
    public static final double maxFrequency = 2861200000L;
    public static final double minAmp = 0;
    public static final double minPhase = 0;
    public static final double minFrequency = 2851200000L;
    public static double divisor;
    static boolean isTesting = true;
    public static SerialPort serialPort;

    public static double freqConv(double inputFreq){
        divisor=.814907252789;
        double output;
        if(isTesting=true){
            System.out.println("divisor is " + divisor);
           
        }
        output=inputFreq/divisor;
        return output;
    }
}
