package dk.tec.jaj;

public class OurThread implements Runnable {

	@Override
	public void run() 
	{
		for(int c = 0; c < 5; c++)
		{
			System.out.println(Thread.currentThread().getName() + ": " + c);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
