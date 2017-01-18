import java.io.*;
import java.util.*;
public class tryml1 {
	public static void main(String[] args) throws IOException {

	    String line;
	    int x=0;
	    int y=0;
	    String[][] data = new String [2001][21];
	    try (BufferedReader input = new BufferedReader(new FileReader("C:/Users/Archana Sinha/Desktop/test_set.csv"))) {
	    while ((line = input.readLine())!=null && x<2001) {
	        while(y<21)
	        {
	             data[x][y] = line.split(",")[y];
	             y++;
	        }
	        y=0;
	        x++;
	    }
	        // System.out.println(data[2000][20]);
	    }
	    int pos=0; int neg=0;
	    buildtree tree=new buildtree();
	    Node node=new Node();
	    double gain=node.findmaxgain(data);
	    System.out.println(gain);
	    
	}

}
import java.lang.Math;
public class Node {
	Node left, right;
	String id;
	int pos,neg;
	double entropy,gain;
	public Node()
	{
		left=null;
		right=null;
		id=null;
	}
	public Node(String idn)
	{
		left=null;
		right=null;
		id=idn;
	}
	public void setleft(Node n)
	{
		left=n;
	}
	public void setright(Node n)
	{
		right=n;
	}
	public Node getleft()
	{
		return left;
	}
	public Node getright()
	{
		return right;
	}
	public void setid(String d)
	{
		id=d;
	}
	public String getid()
	{
		return id;
	}
	public double findentropy(String att,int pos,int neg)
	{
		int sum=pos+neg;
		double p=-(pos/sum)*logfun(pos,sum);
		double q=-(neg/sum)*logfun(neg,sum);
		entropy=p+q;
		return entropy;
	}
	static double logfun(int x, int y)
	{
		double c=Math.log(x)/Math.log(2)-Math.log(y)/Math.log(2);
		return c;		
	}
	public double findgain(String[][] arr,String att)
	{
		int x=0,y,pos=0,neg=0,noOfatt=0;
		while(!arr[0][x].equals(att))
		{
			x++;
		}
		int size=arr.length;
		//System.out.println(size);
		for(int i=1;i<size;i++)
		{
			if(arr[i][20].equals("1"))
				pos++;
			else
				neg++;
		}
		System.out.println(pos);
		double ent_root=findentropy(att,pos,neg);
		int netsum=pos+neg;
		pos=0;neg=0;
		for(int i=1;i<size;i++)
		{
			if((arr[i][x].equals("1"))&&(arr[i][20].equals("1")))
					pos++;
			else neg++;
		}
		double ent_child1=findentropy(att,pos,neg);
		int sum1=pos+neg;
		pos=0;neg=0;
		for(int i=1;i<size;i++)
		{
			if((arr[i][x].equals("0"))&&(arr[i][20].equals("1")))
					pos++;
			else neg++;
		}
		double ent_child0=findentropy(att,pos,neg);
		int sum0=pos+neg;
		gain=ent_root-((sum1/netsum)*ent_child1 + (sum0/netsum)*ent_child0);
		return gain;
		
	}
	public double findmaxgain(String[][] arr)
	{
		double maxgain=0; double gain=0; double gain1;
		for(int i=0;i<20;i++)
		{	
			if(i==0)
			{
				gain=findgain(arr,arr[0][i]);
				System.out.println("**");
			}
			else
			{
				gain1=findgain(arr,arr[0][i]);
				if(gain>gain1)
					gain=gain1;
			}
		}
		return maxgain;
	}
}
