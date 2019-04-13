package WRSN;

import java.util.ArrayList;

import algorithm.GPSR;
import algorithm.K_means;

class Down extends Thread{
public Sensor []s;

public void run()
{	int count=0;
	while(true) 
	{	for(int i=0;i<s.length;i++)
	{
		System.out.println("power is "+s[i].p);
		 s[i].energy=s[i].energy-s[i].p;
		 System.out.println("down"+s[i].energy);
	}
			
		
		try {
			Thread.sleep(1000);
			count=count+1;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==100)break;
	}
}

public Down(Sensor[] x) {
	s=x;
}
}

class Up extends Thread{
public Sensor s;

public void run()
{
	while(true) 
	{
		
				s.energy=(float) (s.energy+0.2);
				System.out.println("up"+s.energy);
			
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s.energy<0)break;
	}
}


public Up(Sensor x) {
	s=x;
}
	
}



public class WRSN {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Net net=new Net(20);
		K_means kk=new K_means();
		kk.k=3;
		kk.convengence(net.n);
		for(int i=0;i<kk.k;i++) {
		GPSR gpsr=new GPSR(net.n,kk.centers[i],(ArrayList)(kk.a.get(i)),net.e);
		gpsr.set_power((ArrayList)(kk.a.get(i)));
		}
		Down d=new Down(net.n);
		d.start();
		

	}

}
