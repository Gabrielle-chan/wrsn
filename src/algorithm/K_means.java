package algorithm;
import java.util.ArrayList;
import java.util.Iterator;

import WRSN.*;
public class K_means {

	public int k;
	 int centers[];
	 ArrayList a;   //save k clusters
	public Sensor [] convengence(Sensor[] x)
		{   
			
			int count=0;
		    Sensor [] centroid=new Sensor[k];//save centroids
		    centers=new int[k];
		    Sensor [] c=x;      
		    for(int i=0;i<k;i++)
		    //chose k of the sensors as the initial centroid
			{   centroid[i]=(Sensor) x[i].clone();
				//System.out.println("sssssssss"+x[i].position_x+" : "+x[i].position_y);
			
				
			}
		    for(int m=0;m>=0;m++) //start iterate
		    	
		    {  // System.out.println("test "+x[1].position_x+":"+x[1].position_y);
		    	count=count+1;  //save iterate times
		    	a=new ArrayList();
		    	for(int z=0;z<k;z++)
		    	{
		    	ArrayList b=new ArrayList(); //as a cluster save each node
				a.add(b);
				}
			//all the sensors compare with k heads
			for(int i=0;i<c.length;i++)    
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
				
					//first 
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
				}//end one node compare with k heads,save its head_id				
				((ArrayList)a.get(c[i].cluster_id)).add(i);
			//	System.out.print("sensor "+i+" belongs to cluster ");
			//	System.out.println(((ArrayList)a.get(c[i].cluster_id)).get(0));				
			}//end all nodes
			
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
				int tb=0;
				tb=(int) (Math.pow(ave_x[mm]-centroid[mm].position_x,2)+Math.pow(ave_y[mm]-centroid[mm].position_y,2));
				System.out.println(tb);
				if(tb>1)
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
				//select one node which is closet to the centroid 
				System.out.println("centers are: ");
				for(int i=0;i<k;i++)
				{
					Iterator it=((ArrayList)a.get(i)).iterator();
					int cx=centroid[i].position_x;
					int cy=centroid[i].position_y;
					int id0=(int) ((ArrayList)a.get(i)).get(0);
					int jx0=c[id0].position_x;
					int jy0=c[id0].position_y;
					Eu_Metric em=new Eu_Metric(); 
					float t=em.get_2D(cx,cy,jx0,jy0);
					centers[i]=id0;
					for(int j=0;j<((ArrayList)a.get(i)).size();j++)
					{	int id=(int) ((ArrayList)a.get(i)).get(j);
						int jxx=c[id].position_x;
						int jyy=c[id].position_y;
						float t2=em.get_2D(cx, cy, jxx, jyy);
						if(t2<t)
						{
							centers[i]=id;
						}					
					}
					
					//find if exist node too far away from centers
					for(int j=0;j<((ArrayList)a.get(i)).size();j++)
					{int idd=(int) ((ArrayList)a.get(i)).get(j);
					int jxx=c[idd].position_x;
					int jyy=c[idd].position_y;
					int cx2=x[centers[i]].position_x;
					int cy2=x[centers[i]].position_y;
					
					float t3=em.get_2D(cx2, cy2, jxx, jyy);
						if(t3>30)
							System.out.println("exist node "+idd+" >30 to centers"+i);
					}
				
				System.out.println(x[centers[i]].position_x+":"+x[centers[i]].position_y);
				
				}
					
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
		Net net=new Net(20);
		K_means kk=new K_means();
		kk.k=3;
		kk.convengence(net.n);
		ArrayList aa=(ArrayList)kk.a.get(0);
		for(int iiii=0;iiii<aa.size();iiii++)
		{
			System.out.println(aa.get(iiii));
		}
		System.out.println("aa end ");
		//ScatterPlot.data("鑺傜偣鍒嗗竷鍥�",net.n );
		//ScatterPlot.data("绨囧ご鍒嗗竷", kk.convengence(net.n));

		

	}
}
