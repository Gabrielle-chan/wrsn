package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import WRSN.*;


public class GPSR {
public float Efs=(float) 0.0002;
public float Eelec=(float) 0.0001;
public Sensor []s;
public ArrayList r_list;//the routing list
//int head;//save the head id

//public int root_id;
public GPSR(Sensor []x,int head,ArrayList a,ArrayList e)
{	r_list=new ArrayList();
	//create the routing list
	/*System.out.println("a size is "+a.size());
	for(int iiii=0;iiii<a.size();iiii++)
	{
		System.out.println(a.get(iiii));
	}
	System.out.println("a end ");
	System.out.println("head is "+head);
	*/
	for(int i=0;i<a.size();i++)
	{	
		r_list.add(new ArrayList());
	}
	//clone the sensor
	s=x.clone();
	
	
	//start iterate
	for(int ii=0;ii>=0;ii++)
		{	
			int flag=0;
			int count=0;
			Iterator it = r_list.iterator();
			
					
			while(it.hasNext())
				{	
					ArrayList a_now=(ArrayList)it.next(); //save node i's routing
					//judge if node i is the head
					if((int)a.get(count)==head)
						{
						count++;
						continue;
						}
					//judge if node i has got to the head
					int size=a_now.size();
					if(size!=0)
						{	
						//if got to the head
							if((int)(a_now.get(size-1))==head)
								{
								count++;
								continue;
								}
						}
				//	count++;
					flag=1;
		
			
		
		
		ArrayList id=new ArrayList(e.size());//save neighbors'id of node i
		ArrayList ws=new ArrayList(e.size());;//save neighbors'weigh of node i
		int id_clt=0;   //save the neighbor closest to head
		int id_now=(int)a.get(count);   //save the id waited for adress
		if(size!=0)
		{
			id_now=(int)(a_now.get(size-1));
			
		}
		//System.out.println("id now is"+id_now+"of "+a.get(count));
		//find neighbors 
		for(int i=0;i<e.size();i++)
		{	 
			
			
			
			if(id_now==((Edge)e.get(i)).v[0]||id_now==((Edge)e.get(i)).v[1])
				{
					if(id_now==((Edge)e.get(i)).v[0])
						id.add(((Edge)e.get(i)).v[1]);
					else {id.add(((Edge)e.get(i)).v[0]);}
					ws.add(((Edge)e.get(i)).w);
				}
		}
		//System.out.println("node "+id_now+" neighbors are ");
		for(int zz=0;zz<id.size();zz++)
		{
			System.out.print(id.get(zz)+" ");
		}
		System.out.println();
		//find the neighbor closest to the aim
		id_clt=id_now;
		double dis=0;
		Eu_Metric em=new Eu_Metric();
		double w_clt=em.get_2D(s[id_clt].position_x, s[id_clt].position_y, s[head].position_x, s[head].position_y);
		
		for(int j=0;j<id.size();j++)
			{
				if(em.get_2D(s[(int)id.get(j)].position_x, s[(int)id.get(j)].position_y, s[head].position_x, s[head].position_y)<w_clt)
					{
						w_clt=em.get_2D(s[(int)id.get(j)].position_x, s[(int)id.get(j)].position_y, s[head].position_x, s[head].position_y);
						if(w_clt==0) {System.out.println("node "+a.get(count)+" can get to head");}
						id_clt=(int)id.get(j);
						dis=w_clt;
						System.out.println("distance to "+id_clt+" is "+dis);
					}
			}
		
		
		System.out.println(a.get(count)+": the node closest to head "+head+" is "+id_clt);
		//add it to the node's routing 
		a_now.add(id_clt);
		count++;
		//System.out.println("count is "+count);
	}
	
	if(flag==0)
		{
		System.out.println("head is  "+head);
		System.out.println("r list has "+r_list.size());
		System.out.println("iterator times "+ii);
		
		
		System.out.println("GPSR end.The routing list :");
		for(int i=0;i<r_list.size();i++)
		{
		 
		 Iterator ittt=((ArrayList)(r_list.get(i))).iterator();
		 System.out.print(a.get(i));
		 int t_1=(int) a.get(i);
		 int t_2=0;
			while(ittt.hasNext())
			{	int xx=(int) ittt.next();
				s[t_1].s_t.add(xx);
				t_2=xx;
				s[t_2].s_r.add(t_1);
				System.out.print("->"+xx);
				t_1=xx;
			
			}
			System.out.println(" ");
		}
	
		
		break;
		}
	}
	
}

public void set_power(ArrayList a)
{
	//System.out.print("fuck it?");
	for(int i=0;i<a.size();i++)
	{	
		float power_r= (float) (Eelec*s[(int) a.get(i)].s_r.size()+Eelec);
		float power_t=(float) (Eelec*s[(int) a.get(i)].s_t.size());
		System.out.print("node "+a.get(i)+" sr: ");
		
		Iterator it2=s[(int) a.get(i)].s_r.iterator();
		
		
		while(it2.hasNext())
		{
			
			System.out.print(it2.next()+" ");
		}
		
		System.out.print(" st: ");
		Iterator it3=s[(int) a.get(i)].s_t.iterator();
		Eu_Metric emm=new Eu_Metric();
		while(it3.hasNext())	
		{	int it33=(int)it3.next();
			float d=emm.get_2D(s[(int) a.get(i)].position_x, s[(int) a.get(i)].position_y, s[it33].position_x,s[it33].position_y);
			//System.out.println("distance is "+d);
			power_t=(float) (power_t+Efs*d*d);
			System.out.print(it33+" ");
		}
		System.out.println();
		//set node i power
		s[(int) a.get(i)].set_power(power_r, power_t);
		
	}
		
}
public static void main(String[] args) {
		// TODO Auto-generated method stub
Net net=new Net(50);
K_means kk=new K_means();
kk.k=3;
kk.convengence(net.n);
GPSR gpsr=new GPSR(net.n,kk.centers[0],(ArrayList)(kk.a.get(0)),net.e);
gpsr.set_power((ArrayList)(kk.a.get(0)));
}

}
