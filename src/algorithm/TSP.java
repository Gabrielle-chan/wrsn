package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import WRSN.*;

public class TSP {
	public ArrayList path;
	public Sensor start=new Sensor(0);
	Sensor [] s;

public TSP(Sensor []x,ArrayList a) {
	path=new ArrayList();
	this.start.position_x=25;
	this.start.position_y=25;
	ArrayList u_v=(ArrayList) a.clone();
	s=x.clone();
	Eu_Metric em=new Eu_Metric();
	float dis=0;
	while(!u_v.isEmpty())
	{	
		Iterator it=u_v.iterator();
		int now_x;
		int now_y;
		if(path.isEmpty())
		{
			now_x=start.position_x;
			now_y=start.position_y;
		}
		else {
			int now=(int) path.get(path.size()-1);
			 now_x=s[now].position_x;
			 now_y=s[now].position_y;
		}
			
			
		
		int min =((Sensor)(u_v.get(0))).get_id();
	
		
		dis=em.get_2D(now_x, now_y, s[min].position_x, s[min].position_y);
		
		while(it.hasNext())
		{	 int next=((Sensor)(it.next())).get_id();
			 int next_x=s[next].position_x;
			 int next_y=s[next].position_y;
			 if(dis>em.get_2D(now_x, now_y, next_x, next_y))
			 {
				 dis=em.get_2D(now_x, now_y, next_x, next_y);
				 min=next;
			 }
			
			 
		}
		path.add(min);
		for(int j=0;j<u_v.size();j++)
		{
		if(((Sensor)u_v.get(j)).get_id()==min)	
		u_v.remove(j);
		}
		
	}
	
	show_path();
	
	
	
	
	
}

public void show_path() {
	Iterator it =path.iterator();
	System.out.println("path is from start ->");
	while(it.hasNext())
	{
		int n=(int) it.next();
		System.out.print(n+" -> ");
		
		
	}
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Net net=new Net(50);
		CRNN crnn=new CRNN(net.n,10);
		TSP tsp=new TSP(net.n,crnn.a);
		
	}

}
