package WRSN;

import java.util.ArrayList;
import java.util.Iterator;

import algorithm.CRNN;
import algorithm.Eu_Metric;
import algorithm.TSP;

public class Charge extends Thread{
public ArrayList path;
public Sensor [] s;
public volatile boolean isRunning = true;
public float time=0;  //save the time of charge

	public void run() {
	while(isRunning)
	{	
	Mc mc=new Mc(10,100);//we think mc has enough power
	mc.set_path(path);
	Eu_Metric em=new Eu_Metric();
	while(!path.isEmpty())
	{	
		Iterator it=path.iterator();
		while(it.hasNext())
		{	
			
			//save the mc next positin
			int next=(int) it.next();
			//save the distance between now and next
			float dis=em.get_2D(s[next].position_x, s[next].position_y, mc.position_x, mc.position_y);
			//time 
			time=time+dis;
			mc.set_postion(s[next].position_x, s[next].position_y);
			try {
				System.out.println("Mc is moving,"+" plz waiting "+dis);
				this.sleep((long) (dis*100));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Mc is at position "+mc.position_x+" : "+mc.position_y);
			
			for(int i=0;i<s.length;i++)
			{	float d=em.get_2D(s[i].position_x, s[i].position_y, mc.position_x, mc.position_y);
				if(d<mc.r)
				{											
					float ps=(float) (36/Math.pow(d+30, 2));
					s[i].set_charge(ps);					
				}
			}
			Up up=new Up();
			up.s=s;
			up.run();
			it.remove();
		}
		float back=em.get_2D(25, 25, mc.position_x, mc.position_y);
		System.out.println("charge ends and MC is moving to base "+back);
		
		//time 
		time=time+back;
		
		System.out.println("charge ends and MC is moving to base");
		try {
			this.sleep((long) back);
			System.out.println("total time is "+time);
			isRunning = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
	}
//up thread run:charge the nodes around the mc
class Up extends Thread{
		public Sensor []s;

		public void run()
		{		float t_max=0;
			 	
				for(int i=0;i<s.length;i++)
				{	
					if(s[i].ps>0&&s[i].energy<2) 
					{	float t=(2-s[i].energy)/s[i].ps;
						if(t>t_max) {t_max=t;}
					}
				}
				System.out.println("t max is "+t_max);
				time=time+t_max;
				for(int j=0;j<s.length;j++)
				{	
					if(s[j].ps>0&&s[j].energy<2) 
					{	
						s[j].energy=s[j].energy+t_max*s[j].ps;
						if(s[j].energy>2)s[j].energy=2;
						System.out.println("node "+j+" "+s[j].energy);}
				}
						
				
					
				
				
				try {
					System.out.println("Mc is charging plz wait "+t_max);
					Thread.sleep((long) t_max*100);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			
		}
}


public Charge(ArrayList a,Sensor x[]) {
	this.path=(ArrayList) a.clone();
	s=x.clone();
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Net net=new Net(50);
			CRNN crnn=new CRNN(net.n,10);
			TSP tsp=new TSP(net.n,crnn.a);
			Charge ch=new Charge(tsp.path,net.n);
			ch.run();
	}

}
