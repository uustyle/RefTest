
public class MultiThread extends Thread {

	private String name;

	private EhcacheTest2 ehcacheTest;

	   public MultiThread(String name, EhcacheTest2 ehcacheTest) {
	     this.name = name;
	     this.ehcacheTest = ehcacheTest;
	   }

	   public void run() {
	    for (int i = 3; i >= 0 ; i--) {
	      try {
	        sleep(1000);
	      } catch (InterruptedException e) {}
	      System.out.println(name + " : " + i);
	      ehcacheTest.getEhcacheDtoList(this.name, 3, 11);
	    }
	  }

	  public static void main(String[] args) {

		  EhcacheTest2 ehcacheTest = new EhcacheTest2();

		  MultiThread t1 = new MultiThread("thread1", ehcacheTest);
		  MultiThread t2 = new MultiThread("thread2", ehcacheTest);
		  t1.start();
		  t2.start();
	  }
}
