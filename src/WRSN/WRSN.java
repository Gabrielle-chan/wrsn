package WRSN;

import java.util.ArrayList;

import algorithm.CRNN;
import algorithm.GPSR;
import algorithm.K_means;
import algorithm.TSP;

class Down extends Thread{
public Sensor []s;
public volatile boolean isRunning = true;
public void run()
{	int count=0;
	while(isRunning) 
	{	for(int i=0;i<s.length;i++)
	{
		//System.out.println("power is "+s[i].p);
		 s[i].energy=s[i].energy-s[i].p;
		 if(s[i].energy<0) {count=1;System.out.println("node "+i+"is dead");}
		 System.out.println("node"+s[i].energy);
	}
			
		
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==1)
			{
			System.out.println("GG");
			
			break;}
	}
}

public Down(Sensor[] x) {
	s=x;
}
}




	




public class WRSN {
	public volatile boolean isRunning = true;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Net net=new Net(50);
		K_means kk=new K_means();
		kk.k=5;
		kk.convengence(net.n);
		for(int i=0;i<kk.k;i++) {
		GPSR gpsr=new GPSR(net.n,kk.centers[i],(ArrayList)(kk.a.get(i)),net.e);
		gpsr.set_power((ArrayList)(kk.a.get(i)));
		}
		CRNN crnn=new CRNN(net.n,10);
		TSP tsp=new TSP(net.n,crnn.a);
		Charge ch=new Charge(tsp.path,net.n);
		Down down=new Down(net.n);
		down.start();
		ch.start();
		
		

	}

}
