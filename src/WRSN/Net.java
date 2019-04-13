package WRSN;

import java.util.ArrayList;
import java.util.Iterator;

import algorithm.Eu_Metric;

public class Net {
	public int Sensor_num;
	public Sensor[] n;
	public int [] v;	
	public ArrayList e;//save the edges
	public float rs=30; //rs is the max distance of sensor communication 
	
public Net(int x) 
	{
	  Sensor_num=x;
	  n=new Sensor[Sensor_num];   
	  v=new int [Sensor_num];
	  for(int i=0;i<Sensor_num;i++)
	  {
		  n[i]=new Sensor();
		  n[i].set_id(i);
		  v[i]=i;                    //record the vertices of net
	  }
	  this.get_edge();
	  
	  
	}
private void get_edge() {
	Eu_Metric eu=new Eu_Metric();
	this.e=new ArrayList();
	for(int i=0;i<n.length;i++)
	{
		int ix=n[i].position_x;
		int iy=n[i].position_y;
		for(int j=i+1;j<n.length;j++)
		{
			int jx=n[j].position_x;
			int jy=n[j].position_y;
			//judge if exist edge between i,j
			if(eu.get_2D(ix, iy, jx, jy)<rs)
			{	
				e.add(new Edge(i,j,eu.get_2D(ix, iy, jx, jy)));
			}
		}
		
	}
}



	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Net n=new Net(50);
     Iterator it=n.e.iterator();
     System.out.println(n.e.size());
     int count=0;
     while(it.hasNext())
     {
    	// count++;
    	 //System.out.println(count);
    	 Edge t=(Edge)it.next();
    	 System.out.println(t.v[0]+" "+t.v[1]);
     }
       
       
		
      
	}

}
