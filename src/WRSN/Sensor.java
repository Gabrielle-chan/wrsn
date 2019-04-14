package WRSN;
import java.lang.Math;
import java.util.ArrayList;
public class Sensor implements Cloneable {
//position
public	int position_x;
public  int position_y;
//cluster id
public  int cluster_id;  
//initial energy is 2
float energy=2;
//neighbor   
public int  nghb_num=0;
public ArrayList  s_r=new ArrayList(0);    //save the receive sensors
public ArrayList  s_t=new ArrayList(0);	// //save the transport sensors


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
//power
public float p=0,pr=0,pt=0,ps=0;
public void set_power(float p1,float p2) {
this.pr=p1;
this.pt=p2;
this.p=pr+pt;
}

public void set_charge(float pss)
{   ps=pss;
	
}

public Object clone() {  
    Sensor ss = null;  
    try{  
        ss = (Sensor)super.clone();  
    }catch(CloneNotSupportedException e) {  
        e.printStackTrace();  
    }  
    return ss ;  
}



  public Sensor()
    {
	   position_x=1+(int)(Math.random()*50);
	   position_y=1+(int)(Math.random()*50);
       System.out.println(position_x+":"+position_y);
	  // System.out.println(position_y); 
	 
	};
	  public Sensor(int x)
	    {  if(x==0)
	    {
		   position_x=1+(int)(Math.random()*50);
		   position_y=1+(int)(Math.random()*50);
	      // System.out.println(position_x+":"+position_y);
		  // System.out.println(position_y); 
	    }
		};

  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     // Sensor s1=new Sensor();
      
      
	}

}
