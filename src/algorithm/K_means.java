package algorithm;
import java.util.ArrayList;

import WRSN.*;
public class K_means {

		public int k;
		
		public Sensor [] convengence(Sensor[] x)
		{   ArrayList a=new ArrayList();
			int count=0;
			
		 	    
		    
		//	int a[][]=new int[k][99];               //a[i][0] is centroids
		    Sensor [] centroid=new Sensor[k];
		    Sensor [] c=x;      
		    
			for(int i=0;i<k;i++)//chose k of the sensors as the initial centroid
			{   
				centroid[i]=x[i];
				ArrayList b=new ArrayList();
				//b.add(i);
				a.add(b);
				//System.out.println(((ArrayList)a.get(i)).get(0));		
			}
		
	
		for(int m=0;m>=0;m++) //start iterate
		{   count=count+1;
			
			for(int i=0;i<c.length;i++)    //all the sensors
			{  
			int ix=c[i].position_x;
			int iy=c[i].position_y;
		
			double t=0;
				for(int j=0;j<k;j++)         //compare with k centroids
				{   
					int cx=centroid[j].position_x;
					
					int cy=centroid[j].position_y;
				//	System.out.println(cy);
					double distance= Math.pow(ix-cx, 2)+Math.pow(iy-cy,2);
				//	System.out.println("distance is "+distance);
					if(j==0) 
					{
						t=distance;
						c[i].cluster_id=j;
					
						
						// System.out.println(a[j]);
				 
					}
					else
					{
						if(distance<t) 
						{
						t=distance;
						c[i].cluster_id=j;
						
						 
						 					//	System.out.println(a[j]);
						} 
						
					}
					//((ArrayList)a.get(c[i].cluster_id)).add(i);
				//	System.out.print("sensor "+i+" belongs to cluster ");
				//	System.out.println(((ArrayList)a.get(c[i].cluster_id)).get(0));
				}
				((ArrayList)a.get(c[i].cluster_id)).add(i);
				System.out.print("sensor "+i+" belongs to cluster ");
				System.out.println(((ArrayList)a.get(c[i].cluster_id)).get(0));

				
			}
  
			//calculate the means
			int flag=0;
			int ave_x[]=new int[k];
			int ave_y[]=new int[k];
			for(int mm=0;mm<k;mm++)
			{	int total_x=0;
				int total_y=0;
				for(int i2=0;i2<((ArrayList)a.get(mm)).size();i2++)
				{
					
					total_x +=c[(int)((ArrayList)a.get(mm)).get(i2)].position_x;
					total_y +=c[(int)((ArrayList)a.get(mm)).get(i2)].position_y;
					
				}
					//avg灏辨槸骞冲潎鏁�
				ave_x[mm]=total_x/((ArrayList)a.get(mm)).size();
				
				ave_y[mm]=total_y/((ArrayList)a.get(mm)).size();
				int b=0;
				b=(int) (Math.pow(ave_x[mm]-centroid[mm].position_x,2)+Math.pow(ave_y[mm]-centroid[mm].position_y,2));
				System.out.println(b);
				if(b>1)
				{ 
					flag=1;
				}
			}
			if(flag==0)
				
			{	for(int i4=0;i4<k;i4++)
				{
				System.out.println("centroids are: ");
				System.out.println(centroid[i4].position_x+":"+centroid[i4].position_y);
				}
				
				System.out.println("success");
				System.out.println("count :"+count);
				break;
				
			}
			else
			{
				for(int i3=0;i3<k;i3++)
					{
						centroid[i3].position_x=ave_x[i3];
						centroid[i3].position_y=ave_y[i3];
					}
			}
					
				
				
			
			
			}
			
				
		
			
			
			
			
//iterate end 
		//	if()
		//		break;
		//} 

		
		return centroid;
			
			
			
		};
		
		


	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Net net=new Net(100);
		K_means kk=new K_means();
		kk.k=8;
		
		ScatterPlot.data("鑺傜偣鍒嗗竷鍥�",net.n );
		ScatterPlot.data("绨囧ご鍒嗗竷", kk.convengence(net.n));

		

	}
}
