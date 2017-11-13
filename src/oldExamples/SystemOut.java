package oldExamples;
import java.io.PrintStream;
import java.io.OutputStream;


public class SystemOut {
	private static PrintStream originalOut;
	private static PrintStream originalErr;
	
	public static void disableOutput(){
		originalOut = System.out;
		originalErr = System.err;
        System.setOut(new PrintStream(new OutputStream() {
                    public void write(int b) {
                        //DO NOTHING
                    }
                }));
        //System.out.println("Off");
        System.setErr(new PrintStream(new OutputStream() {
	            public void write(int b) {
	                //DO NOTHING
	            }
	        }));
	}
	
	public static void enableOutput(){
		System.setOut(originalOut);
		System.setErr(originalErr);
		//System.out.println("On");
	}
}
