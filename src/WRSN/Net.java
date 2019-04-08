package WRSN;

public class Net {
	public int Sensor_num;
	public Sensor[] n;
	
	
public Net(int x) 
	{
	  Sensor_num=x;
	  n=new Sensor[Sensor_num];   
	  for(int i=0;i<Sensor_num;i++)
	  {n[i]=new Sensor();}
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Net n=new Net(10);
       
       
		
      
	}

}
