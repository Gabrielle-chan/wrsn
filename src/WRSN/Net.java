package WRSN;

public class Net {
	public int Sensor_num;
	public Sensor[] n;
	public int [] v;	
	public Edge [] e;
	
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
	  
	}



	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Net n=new Net(10);
       
       
		
      
	}

}
