package algorithm_labcourse;

import org.rosuda.REngine.*;
import org.rosuda.REngine.Rserve.*;

public class TestR {
    public static void main(String[] args) throws RserveException {
        try {
            RConnection c = new RConnection();// make a new local connection on default port (6311)
            try {
				double d[] = c.eval("rnorm(10)").asDoubles();
				for(int i=0; i<d.length;i++){
					System.out.println(d[i]);
				}
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            org.rosuda.REngine.REXP x0 = c.eval("R.version.string");
            try {
				System.out.println(x0.asString());
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
} catch (REngineException e) {
            //manipulation
        }       

    }
}
