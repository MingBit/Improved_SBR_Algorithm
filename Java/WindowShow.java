package algorithm_labcourse;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;


public class WindowShow {
	private static Text count;
	private static Text len;
	private static Text max;
	private static Table table;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		showWindow();

	}
		
	public static void showWindow(){

		final HashMap<Integer, Integer> Output= new HashMap<Integer,Integer>();
		Display display = Display.getDefault();
		Shell shlSignedSortingBy = new Shell();
		shlSignedSortingBy.setImage(SWTResourceManager.getImage("E:\\20150906191627.jpg"));
		shlSignedSortingBy.setSize(550, 429);
		shlSignedSortingBy.setText("Signed Sorting By Reversals Application");
		
		count = new Text(shlSignedSortingBy, SWT.SINGLE|SWT.BORDER);
		count.setBounds(23, 107, 94, 23);
//		count.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		
		
		Label lblNewLabel = new Label(shlSignedSortingBy, SWT.NONE);
		lblNewLabel.setBounds(23, 74, 97, 17);
		lblNewLabel.setText("No. of instances:");
		
		Label lblLength = new Label(shlSignedSortingBy, SWT.NONE);
		lblLength.setText("Length:");
		lblLength.setBounds(146, 74, 94, 17);
		
		len = new Text(shlSignedSortingBy, SWT.SINGLE|SWT.BORDER);
		len.setBounds(146, 107, 94, 23);
		
		
		Label lblMax = new Label(shlSignedSortingBy, SWT.NONE);
		lblMax.setText("Max:");
		lblMax.setBounds(276, 74, 94, 17);
		
		max = new Text(shlSignedSortingBy, SWT.SINGLE|SWT.BORDER);
		max.setBounds(276, 107, 94, 23);
		
		
		Label lblOutput = new Label(shlSignedSortingBy, SWT.NONE);
		lblOutput.setBounds(23, 136, 61, 17);
		lblOutput.setText("Output:");
		
		
		final Button btnBS = new Button(shlSignedSortingBy, SWT.RADIO);
		btnBS.setBounds(23, 33, 97, 17);
		btnBS.setText("Basic Sorting");
		
		
		final Button btnHP = new Button(shlSignedSortingBy, SWT.RADIO);
		btnHP.setBounds(143, 33, 211, 17);
		btnHP.setText("Hannenhalli-Pevzner Method");
		
		Button btnGo = new Button(shlSignedSortingBy, SWT.NONE);

		btnGo.addSelectionListener(new SelectionAdapter() {
			
		
		
		
		public void widgetSelected(SelectionEvent e) {
			final int Count= Integer.parseInt(count.getText());
			final int Len = Integer.parseInt(len.getText());
			final int Max = Integer.parseInt(max.getText());
			HashMap<Integer, int[]> input= RandomInitial.initialSet(Count,Len,Max);
			if(btnBS.getSelection()){
				
				for(int i=0;i<input.size();i++){
					TableItem item = new TableItem(table, SWT.NONE);
					Output.put(i, basicSorting.repeatBasicCall((int[])input.get(i)).get(0));
					item.setText(0, String.valueOf(i));
					item.setText(1, String.valueOf(Output.get(i)));
					item.setText(2,"_");
					if(basicSorting.repeatBasicCall((int[])input.get(i)).get(1)==1){
						item.setText(3, "NO");
					}else
						item.setText(3,"YES");
				}
			}
			
			else if (btnHP.getSelection()){
					
					for(int i=0;i<input.size();i++){
						TableItem item = new TableItem(table, SWT.NONE);
						Output.put(i, SbrCallFunction.repeatCall((int[])input.get(i)));
						item.setText(0, String.valueOf(i));
						item.setText(1,"_" );
						item.setText(2,String.valueOf(Output.get(i)));
						item.setText(3,"YES");
				}
				}

			for (int i = 0; i < 4; i++) {
			      table.getColumn(i).pack();
			    }
			
		}
		});
			

		btnGo.setBounds(400, 105, 80, 27);
		btnGo.setText("Go");
		
		Button btnSaveResult = new Button(shlSignedSortingBy, SWT.NONE);
		btnSaveResult.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File file = new File("myResult.csv");
				
			}
		});
		btnSaveResult.setText("Save Result");
		btnSaveResult.setBounds(322, 360, 80, 27);
		
		Button btnExit = new Button(shlSignedSortingBy, SWT.NONE);							
		btnExit.setText("Exit");
		btnExit.setBounds(415, 360, 80, 27);
		
		table = new Table(shlSignedSortingBy, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(23, 182, 472, 164);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button Analysis = new Button(shlSignedSortingBy, SWT.NONE);
		Analysis.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 try {
					RConnection c = new RConnection();
					c.assign("x", Output.keySet().toString());
					c.assign("y",Output.values().toString());
					c.eval("a=lm(y~x)");
					c.eval("plot(a)");
				} catch (RserveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
			}
		});
		Analysis.setText("Analysis");
		Analysis.setBounds(400, 136, 80, 27);
		 String[] titles = { "No. of instance", "BS_distance","HP_distance","Sorted or not"};
			    for (int i = 0; i < titles.length; i++) {
			      TableColumn column = new TableColumn(table, SWT.NONE);
			      column.setText(titles[i]);
			    }
	
		
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		
		

		shlSignedSortingBy.open();
		shlSignedSortingBy.layout();
		while (!shlSignedSortingBy.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
			
		
	}
	}		

