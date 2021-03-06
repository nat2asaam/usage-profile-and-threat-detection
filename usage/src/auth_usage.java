import java.util.*;
class auth_usage implements model{
	boolean system_learning;
	usage_modeller math_modeller;
	double usageVal;// a system's usage value at a point in time
	double x1;//network upload speed
	double x2; //network download speed;
	double x3;//size of request data
	double x4;// size of response data
	double y;// time taken for  authentication
	double c1; //successful auth
	double c2;//failed auth
	double c3; // expected auth
	double c4; // min auth
	double c5;// max auth
	double x_set[];// for computing usage value
	auth_usage(){
		math_modeller=new usage_modeller(4);// for independent variables of size 4 (0-3)
		x1=0;
		x2=0;
		x3=0;
		x4=0;
		y=0;
		x_set=new double[4];
		system_learning=false;
	}
	public void capture_sample(){
		Random rand=new Random();
		x1=Math.round(rand.nextDouble()*2);// 0 to 2 kbps
		x2=Math.round(rand.nextDouble()*2);// 0 to 2 kbps
		x3=Math.round(rand.nextDouble()*2);// 0 to 100 kb
		x4=Math.round(rand.nextDouble()*2);// 0 to 100 kb
		y=Math.round(rand.nextDouble()*60);//0 to 60 seconds(1 mins)
		System.out.println("x1: "+x1+" x2: "+x2+" x3: "+x3+"x4: "+x4);
	}
	public void set_x(double x_set[]){
		this.x_set=x_set;
	}
	public void set_sample(){
		double x_set[]=new double[4];
		x_set[0]=x1;
		x_set[1]=x2;
		x_set[2]=x3;
		x_set[3]=x4;
		math_modeller.sample_x_set(x_set);
		math_modeller.sample_y(y);
		math_modeller.queue_sample();
	}
	public double computeval(){
		//usageVal=a value  determined using x_set from the relationship determined.
		return usageVal;
	}
	public double findchange(){
		return 0;
	}
	public void learnsys(int t){
		if(system_learning==false){
			system_learning=true;
			System.out.println("learning auth usage ...");
		}
		for(int i=0;i<t;i++){
			capture_sample();
			set_sample();
		}
	}
	public Object findrelationship(){
		return null;
	}
	public void monitor(int t){
	}
	public void showalarm(String info){
	}
	public void haltprocess(){
	}
	public void predictvals(){
	}
	public static void main(String args[]){
		auth_usage usg=new auth_usage();
		for (int i=0;i<10;i++){
			usg.capture_sample();
			usg.set_sample();
		}
		usg.print_sample_stats(usg.math_modeller);
	}
	public void print_sample_stats(usage_modeller math_modeller){
		math_modeller.end_modeller=true;
		usage_stats us=math_modeller.get_usage_stats();
		
		System.out.println("Average authentication time: "+us.average_y);
		x_set set=us.average_x_set;
		System.out.println("Average x1,x2,x3,x4: "+set.x_set[0]+","+set.x_set[1]+","+set.x_set[2]+","+set.x_set[3]);
		
	}
	
}