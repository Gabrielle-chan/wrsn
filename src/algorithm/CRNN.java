package algorithm;
import java.util.ArrayList;
import java.util.Iterator;

import WRSN.*;
public class CRNN {
	public ArrayList a=new ArrayList();; //save the anchor
	public ArrayList b; //save the nodes
	public float r;
	private void find_anchor(ArrayList s)  
	{	
	/*	ArrayList b=new ArrayList();
		
		for(int i0=0;i0<s.size();i0++)
		{
			b.add(s.get(i0));
		}*/
	
		//try to find the anchor
		//if only one,must have no neighbor
			if(s.size()==1)
			{
			System.out.println("only 1 left");
			a.add(b.get(0));
			s.remove(0);
			}
			
			else 
			{
			//from b0
				
			int t=((Sensor)s.get(0)).nghb_num;
			int t_id=((Sensor)s.get(0)).get_id();
			int an_x=0;
			int an_y=0;
			int p=0;//save the position of anchor in arraylist s;
			//find the anchor
			for(int j=1;j<s.size();j++)
				{
					if (t<((Sensor)s.get(j)).nghb_num)
					{
					t=((Sensor)s.get(j)).nghb_num;
					t_id=((Sensor)s.get(j)).get_id();
					an_x=((Sensor)s.get(j)).position_x;
					an_y=((Sensor)s.get(j)).position_y;
					 p=j;
					
					}
					
					//else { System.out.println("this round the anchor is "+t_id);}
				}
			System.out.println("this round the anchor is "+t_id);
			System.out.println("an_xx="+an_x);
			System.out.println("an_yy="+an_y);
			a.add(s.get(p));
			//delete anchor and its neighbor
			Iterator it=s.iterator();
			while(it.hasNext())
			{	Sensor st=(Sensor)it.next();
				//System.out.println("suck my dic "+st.get_id());
				
				//System.out.println(st.get_id());
				int xx=st.position_x;//
				int yy=st.position_y;//System.out.println("yy="+yy);
				if(st.get_id()==t_id)
				{
					it.remove();
					// System.out.println("now try the "+i1);
					 System.out.println("this round delete anchor "+st.get_id());
					
				}
				else 
				{ 
					if((Math.pow((xx-an_x), 2)+Math.pow((yy-an_y), 2))<Math.pow(r, 2))
					{ 			//System.out.println(Math.pow((xx-an.position_x), 2));
								//System.out.println(Math.pow((yy-an.position_y), 2));
								//System.out.println(Math.pow(r, 2));
							it.remove();
							 System.out.println("this round delete "+st.get_id());
					}
				}
				
			}

			}
			//to see which sensors are left
			//if END
			if(s.size()==0)
			{
				System.out.println("CRNN is end.");
				System.out.print("Anchors are ");
				for(int aai=0;aai<a.size();aai++)
					{
						System.out.print(((Sensor)a.get(aai)).get_id()+" ");
					}
			}
			//if not end print the left list
			else
			{
				System.out.print("s:");
				for(int ai=0;ai<s.size();ai++)
					{	
						if(ai==s.size()-1) 
						{System.out.println(( (Sensor)s.get(ai)).get_id());}
						else 
						{System.out.print(( (Sensor)s.get(ai)).get_id()+" ");}
				
					}
			}
		
			
			
	}

		
		
	//calcultate the neighbors of all sensors;
	public void get_nghbnum() 
	{
		
		for(int i=0;i<b.size();i++)
		{	((Sensor)b.get(i)).nghb_num=0;   //recalculate 
			int ix=((Sensor)b.get(i)).position_x;
			int iy=((Sensor)b.get(i)).position_y;
			for(int j=0;j<b.size();j++)
			{   if(j!=i)
				{
				int jx=((Sensor)b.get(j)).position_x;
				int jy=((Sensor)b.get(j)).position_y;
				
				if((Math.pow((ix-jx), 2)+Math.pow((iy-jy), 2))<Math.pow(r, 2))
					((Sensor)b.get(i)).nghb_num++;
				}
			}
		System.out.println("sensor "+((Sensor)b.get(i)).get_id()+"'s nghb_num is "+((Sensor)b.get(i)).nghb_num);
		}
	};
	public CRNN(Sensor [] s,float x)
	{	b=new ArrayList(s.length);
		r=x;
		for(int i=0;i<s.length;i++)
		{
			b.add(s[i]);
		}
		while(b.size()>0)
		{
			this.get_nghbnum();
			this.find_anchor(b);
			
		}
		
		
	};

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Net net=new Net(15);
		CRNN crnn=new CRNN(net.n,20);
	
	
	}

}
