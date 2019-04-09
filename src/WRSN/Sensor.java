package WRSN;
import java.lang.Math;
public class Sensor {
//position
public	int position_x;
public  int position_y;
//cluster id
public  int cluster_id;  
    float rate;
    float energy;
//neighbor   
public int  nghb_num=0;
private int onehop [];
//id of sensor
private int id=0;

public int get_id()
{
	return id;
}
public void set_id(int x)
{
	id=x;
}
  public Sensor()
    {
	   position_x=1+(int)(Math.random()*50);
	   position_y=1+(int)(Math.random()*50);
       System.out.println(position_x+":"+position_y);
	  // System.out.println(position_y); 
	 
	};
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     // Sensor s1=new Sensor();
      
      
	}

}
