import java.io.IOException;

/**
 * This code was written to demonstrate that exceptions thrown in the constructor of an
 * {@link AutoCloseable} can interrupt the safety that a try-with-resources block would suggest, so
 * extra care should be taken in those constructors.
 */
public class Driver {
	
	public static void main(String[] args) {
		
		try (EvilResource resource = new EvilResource()) {
			resource.doThings();
		} catch (IOException e) {
			System.out.println("Catch: " + e.getMessage());
		}
		
		if (!EvilResource.closeWasCalled) {
			System.out.println("Not closed :(");
		} else {
			System.out.println("Closed :)");
		}
	}
	
	/**
	 * An "Evil" implementation of {@link AutoCloseable} that will intentionally throw exceptions for
	 * demonstration purposes.
	 */
	public static class EvilResource implements AutoCloseable {
		
		public static boolean closeWasCalled = false;
		
		public EvilResource() throws IOException {
			System.out.println("I am constructing...");
			
			// consider commenting out this line and running the program with or without it
			throw new IOException("Uh oh! (constructor)");
		}
		
		public void doThings() throws IOException {
			System.out.println("I am reading the resources...");
			
			// exceptions here are "OK" in the sense that the try-with-resources will complete normally
			throw new IOException("Uh oh! (doThings())");
		}
		
		@Override
		public void close() throws IOException {
			EvilResource.closeWasCalled = true;
		}
	}
}
