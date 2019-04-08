
package WRSN;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.RefineryUtilities;
 
public class ScatterPlot {
	 public static void data(String title,Sensor []a)
	    {
	        DefaultXYDataset   xydataset = new DefaultXYDataset ();
	        DefaultXYDataset   xydataset2 = new DefaultXYDataset ();
	        
	        double[][] data=new double[2][a.length];
	       // double[][] data2=new double[2][b.length];
		    for(int i=0;i<a.length;i++)
		    {
		     data[0][i]=a[i].position_x;
		     data[1][i]=a[i].position_y;
		    }
	       xydataset.addSeries("", data);
	/*       for(int i=0;i<b.length;i++)
		    {
		     data2[0][i]=b[i].position_x;
		     data2[1][i]=b[i].position_y;
		    }
	      */
	      // xydataset.addSeries("", data2);
	       
	/*       XYTextAnnotation text1 = new XYTextAnnotation("1sss",2, 2); 
	       XYTextAnnotation text2 = new XYTextAnnotation("2aaa", 4, 4);  
	       XYTextAnnotation text3 = new XYTextAnnotation("3bbb", 7, 5);*/
	       
	       final JFreeChart chart =ChartFactory.createScatterPlot("","","",xydataset,PlotOrientation.VERTICAL,false,false,false);
	    //   final JFreeChart chart2 =ChartFactory.createScatterPlot("","","",xydataset2,PlotOrientation.VERTICAL,false,false,false);
	       XYPlot xyplot = (XYPlot) chart.getPlot();
	       //chart=ChartFactory.createScatterPlot("","","",xydataset,PlotOrientation.VERTICAL,false,false,false);
	    //   XYPlot xyplot2 = (XYPlot) chart2.getPlot();
	       
	       xyplot.getRenderer().setSeriesPaint(0, Color.blue) ;
	 /*      xyplot.addAnnotation(text1);
	       xyplot.addAnnotation(text2);
	       xyplot.addAnnotation(text3);*/
	       
	       ChartFrame frame = new ChartFrame(title,chart);
	        frame.pack();
	        RefineryUtilities.centerFrameOnScreen(frame);
	        frame.setVisible(true);
	    }
	 
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Net net=new Net(100);
		
	
		ScatterPlot.data("节点分布图",net.n );
	}
 
}

