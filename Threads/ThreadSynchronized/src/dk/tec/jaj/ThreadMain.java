package dk.tec.jaj;

public class ThreadMain 
{
	static int count = 0;
	
	static synchronized void increment()
	{
		count++;
	}
	
	static synchronized void decrement()
	{
		count--;
	}
	

	public static void main(String[] args) 
	{
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i = 0; i < 1000; i++)
				{
					increment();			
				}
			}
		});
		
				
        Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 1000; i++)
				{
					decrement();		
				}
			}
		});
        
        t2.start();
		t1.start();
		
		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println("Count value: " + count);
	}
}










