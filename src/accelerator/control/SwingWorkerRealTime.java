/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accelerator.control;

/**
 *
 * @author STS
 */

import java.util.LinkedList;
import java.util.List;
 
import javax.swing.SwingWorker;
 
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
 
/**
 * Creates a real-time chart using SwingWorker
 */
public class SwingWorkerRealTime {
 
  MySwingWorker mySwingWorker;
  SwingWrapper<XYChart> sw;
  XYChart chart;
 
  public static void main(String[] args) throws Exception {
 
    SwingWorkerRealTime swingWorkerRealTime = new SwingWorkerRealTime();
    swingWorkerRealTime.go();
  }
 
  private void go() {
 
    // Create Chart
    chart = QuickChart.getChart("Frequency Update Real-time Demo", "Time", "Value", "randomWalk", new double[] { 0 }, new double[] { 0 });
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setXAxisTicksVisible(false);
 
    // Show it
    sw = new SwingWrapper<XYChart>(chart);
    sw.displayChart();
 
    mySwingWorker = new MySwingWorker();
    mySwingWorker.execute();

  }
 
  private class MySwingWorker extends SwingWorker<Boolean, double[]> {
 
    LinkedList<Double> fifo = new LinkedList<Double>();
        LinkedList<Double> fifo2 = new LinkedList<Double>();
 
    public MySwingWorker() {
 
      fifo.add(0.0);
      fifo2.add(0.0);
    }
 
    @Override
    protected Boolean doInBackground() throws Exception {
 
      while (!isCancelled()) {
 
        fifo.add(fifo.get(fifo.size() - 1) + Math.random() - .5);
        fifo2.add(fifo2.get(fifo2.size() -1)+ Math.random()-.5);
        if (fifo.size() > 500) {
          fifo.removeFirst();
          fifo2.removeFirst();
        }
 
        double[] xarray = new double[fifo.size()];
        double[] yarray= new double [fifo2.size()];
        for (int i = 0; i < fifo.size(); i++) {
          xarray[i] = fifo.get(i);
          yarray[i] = fifo2.get(i);
        }
        publish(xarray);
        publish(yarray);

 
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          // eat it. caught when interrupt is called
          System.out.println("MySwingWorker shut down.");
        }
 
      }
 
      return true;
    }
 
    @Override
    protected void process(List<double[]> chunks) {
 
      System.out.println("number of chunks: " + chunks.size());
 
      double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

      chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
      sw.repaintChart();
 
      long start = System.currentTimeMillis();
      long duration = System.currentTimeMillis() - start;
      try {
        Thread.sleep(40 - duration); // 40 ms ==> 25fps
        // Thread.sleep(400 - duration); // 40 ms ==> 2.5fps
      } catch (InterruptedException e) {
      }
 
    }
  }
}