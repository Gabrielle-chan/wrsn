package WRSN;

import java.util.ArrayList;

public class Mc {
public float r;
public float e;
public int position_x=25;
public int position_y=25;

ArrayList path;// save the path 
public void set_path(ArrayList p)
{
	this.path=(ArrayList) p.clone();
}

public Mc(float rr,float ee) {
	this.r=rr;
	this.e=ee;
	
}
public void set_postion(int xx,int yy)
{
	this.position_x=xx;
	this.position_y=yy;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
