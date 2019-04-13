package algorithm;

public class Eu_Metric {
public float get_2D(int x1,int y1,int x2,int y2) 
{
	float x=0;
	x=(float)Math.sqrt((double) (Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
	
	
	return x;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Eu_Metric e=new Eu_Metric();
    System.out.println(e.get_2D(10, 10, 12, 33));
	
	}

}
