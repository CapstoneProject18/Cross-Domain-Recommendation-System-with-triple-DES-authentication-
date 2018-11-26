import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;


public class denseVector {

	public  static void main(String[] args)
	{
		List<Vector> list = new ArrayList();
		Double count = 0.0;
		for(int i=0;i<4;i++)
			
		{			
			double[] values = new double[5];
			for(int j = 0; j<5; j++)
			{
				values[j]= count;
				//v.set(j, count);
				count++;
			}
			
			Vector v = new DenseVector(values);
			
			list.add(v);
			
		}
		
		for (int i = 0 ; i <4;i++)
{
	Vector v = list.get(i);
	System.out.println("V"+ i + ":");
	for (int j = 0 ; j<5; j++)
	{
		System.out.println(v.get(j));
	}
}

	}
	
	
}
