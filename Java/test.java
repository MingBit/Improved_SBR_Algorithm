package algorithm_labcourse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class test {

	 Display d;

	  Shell s;

	  test() {
	    d = new Display();
	    s = new Shell(d);
	    s.setSize(250, 250);
	    s.setText("A Text Field Example");
	    final Text text1 = new Text(s, SWT.SINGLE | SWT.BORDER);
	    text1.setBounds(100, 50, 100, 20);
	    final Text text2 = new Text(s, SWT.SINGLE | SWT.BORDER);
	    text2.setBounds(100, 75, 100, 20);
	    text1.addVerifyListener(new VerifyListener() {
	        public void verifyText(VerifyEvent e) {
	          if (e.text.startsWith("1")) {
	            e.doit = false;
	          } else {
	            e.text = e.text.toUpperCase();
	          }
	        }
	      });
	    text2.addListener(SWT.Verify, new Listener() {
	        public void handleEvent(Event e) {
	          String string = e.text;
	          char[] chars = new char[string.length()];
	          string.getChars(0, chars.length, chars, 0);
	          for (int i = 0; i < chars.length; i++) {
	            if (!('0' <= chars[i] && chars[i] <= '9')) {
	              e.doit = false;
	              return;
	            }
	          }
	        }
	      });
	    s.open();
	    while (!s.isDisposed()) {
	      if (!d.readAndDispatch())
	        d.sleep();
	    }
	    d.dispose();
	  }

	  public static void main(String[] arg) {
	    new test();
	  }

	}
