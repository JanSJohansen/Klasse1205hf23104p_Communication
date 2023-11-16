package dk.tec.jaj;

public class ThreadMain {

	public static void main(String[] args) 
	{
		System.out.println(Thread.currentThread().getName() + ": Started");
		
		MyThread mt = new MyThread("MtyThread");
		//mt.setName("MyThread");
		mt.start();
		
		Thread t1 = new Thread(new OurThread(),"OurNiceThread");
		t1.start();
		
		
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				for(int i = 0; i < 5; i++)
				{
					System.out.println(Thread.currentThread().getName() + ": " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}						
			}
		}, "Anonymous nice Thread");
		
		//t2.setName("Anonymous Thread");
		t2.start();
		
		System.out.println(Thread.currentThread().getName() + ": Ended\n");
	}

}
