/*
 * Peter Danshov 5.12.12
 * Java Calculator with history field
 * and ability to input via keyboard.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JavaCalc extends JFrame
{
    /***/
	private static final long serialVersionUID = 1L;
	
	private JButton jbtNum1;
    private JButton jbtNum2;
    private JButton jbtNum3;
    private JButton jbtNum4;
    private JButton jbtNum5;
    private JButton jbtNum6;
    private JButton jbtNum7;
    private JButton jbtNum8;
    private JButton jbtNum9;
    private JButton jbtNum0;
    private JButton jbtAdd;
    private JButton jbtSubtract;
    private JButton jbtMultiply;
    private JButton jbtDivide;
    private JButton jbtModulus;
    private JButton jbtPercent;
    private static JButton jbtSolve;
    private JButton jbtClear;
    private JButton jbtCC;
    private float SolveTEMP = 0;
    private float NumOne;
    private float NumTwo;
    private String[] numbers = {"0","0"};
    private JTextField jtfResult;
    private JTextArea history;
//    private JScrollPane scroll;

	Boolean ans = false;
	
	String display = "";
	String hist_disp = "";
	
	char oper[] = {'+','-','*','/','M','%'};

	public JavaCalc()
	{
	    JPanel p1 = new JPanel();
	    p1.setLayout(new GridLayout(4, 3));
	    p1.add(jbtNum1 = new JButton("1"));
	    p1.add(jbtNum2 = new JButton("2"));
	    p1.add(jbtNum3 = new JButton("3"));
	    p1.add(jbtNum4 = new JButton("4"));
	    p1.add(jbtNum5 = new JButton("5"));
	    p1.add(jbtNum6 = new JButton("6"));
	    p1.add(jbtNum7 = new JButton("7"));
	    p1.add(jbtNum8 = new JButton("8"));
	    p1.add(jbtNum9 = new JButton("9"));
	    p1.add(jbtNum0 = new JButton("0"));
	    p1.add(jbtClear = new JButton("C"));
	    p1.add(jbtCC = new JButton("CC"));
	
	    JPanel p2 = new JPanel();
	    //p2.setLayout(new FlowLayout());
	    p2.setLayout(new BorderLayout());
//	    JTextArea history = new JTextArea(hist_disp, 5, 20);
//	    JScrollPane scroll = new JScrollPane(history);
//	    p2.add(scroll, BorderLayout.NORTH);
//	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//	    scroll.setPreferredSize(new Dimension(100, 100));
	    p2.add(history = new JTextArea(hist_disp,5,20), BorderLayout.NORTH);
	    history.setEditable(false);
	    //history.setLineWrap(true);
	    //history.setWrapStyleWord(true);
	    p2.add(jtfResult = new JTextField(20), BorderLayout.SOUTH);
	    jtfResult.setHorizontalAlignment(JTextField.CENTER);
	    jtfResult.setEditable(true);
	
	    JPanel p3 = new JPanel();
	    p3.setLayout(new GridLayout(4, 1));
	    p3.add(jbtAdd = new JButton("+"));
	    p3.add(jbtSubtract = new JButton("-"));
	    p3.add(jbtMultiply = new JButton("*"));
	    p3.add(jbtDivide = new JButton("/"));
	    p3.add(jbtModulus = new JButton("M"));
	    p3.add(jbtPercent = new JButton("%"));
	    p3.add(jbtSolve = new JButton("="));
	
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(p2, BorderLayout.NORTH);
	    p.add(p1, BorderLayout.WEST);
	    p.add(p3, BorderLayout.EAST);
	
	    add(p);
	
	    jbtNum1.addActionListener(new NumList());
	    jbtNum2.addActionListener(new NumList());
	    jbtNum3.addActionListener(new NumList());
	    jbtNum4.addActionListener(new NumList());
	    jbtNum5.addActionListener(new NumList());
	    jbtNum6.addActionListener(new NumList());
	    jbtNum7.addActionListener(new NumList());
	    jbtNum8.addActionListener(new NumList());
	    jbtNum9.addActionListener(new NumList());
	    jbtNum0.addActionListener(new NumList());

	    jbtAdd.addActionListener(new OpList());
	    jbtSubtract.addActionListener(new OpList());
	    jbtMultiply.addActionListener(new OpList());
	    jbtDivide.addActionListener(new OpList());
	    jbtModulus.addActionListener(new OpList());
	    jbtPercent.addActionListener(new OpList());
	    jbtSolve.addActionListener(new ListenToSolve());
	    jbtClear.addActionListener(new ListenToClear());
	    jbtCC.addActionListener(new ListenToClear());
	
	}
//	JavaCaluclator()
	class ListenToClear implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	if (e.getSource() == jbtClear) {
		        jtfResult.setText("");
		        SolveTEMP = 0 ;
	    	}
	    	else if (e.getSource() == jbtCC) {
	    		history.setText("");
		        jtfResult.setText("");
		        SolveTEMP = 0 ;
	    	}
	    }
	}
	class NumList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ans = false;
			int digit = (Integer.valueOf(e.getActionCommand())).intValue();
			for (int number=0; number<10; number++) {
				if (digit == number) {
					display = jtfResult.getText();
					jtfResult.setText(display + number);
				}
			}
		}
	}
	class OpList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int op=0; op<oper.length; op++) {
				if (e.getActionCommand().charAt(0) == oper[op]) {
					if (ans == true) {
						ans = false;
						jtfResult.setText(SolveTEMP+" "+oper[op]+" ");
					}
					else {
						display = jtfResult.getText();
						jtfResult.setText(display+" "+oper[op]+" ");
					}
				}
			}
		}
	}
	class ListenToSolve implements ActionListener {
	    public void actionPerformed(ActionEvent e)
	    {
	    	if (jtfResult.getText().indexOf("+")>0) {
	    		numbers = jtfResult.getText().split("\\+");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = NumOne + NumTwo;
	    	}
	    	else if (jtfResult.getText().indexOf("-")>0) {
	    		String[] numbers = jtfResult.getText().split("\\-");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = NumOne - NumTwo;
	    	}
	    	else if (jtfResult.getText().indexOf("*")>0) {
	    		String[] numbers = jtfResult.getText().split("\\*");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = NumOne * NumTwo;
	    	}
	    	else if (jtfResult.getText().indexOf("/")>0) {
	    		String[] numbers = jtfResult.getText().split("/");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = NumOne / NumTwo;
	    	}
	    	else if (jtfResult.getText().indexOf("M")>0) {
	    		String[] numbers = jtfResult.getText().split("M");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = NumOne % NumTwo;
	    	}
	    	else if (jtfResult.getText().indexOf("%")>0) {
	    		String[] numbers = jtfResult.getText().split("\\%");
	    		NumOne = Float.parseFloat(numbers[0]);
	    		NumTwo = Float.parseFloat(numbers[1]);
	    		SolveTEMP = (NumOne / NumTwo) * 100;
	    	}
	    	display = jtfResult.getText();
//	    	hist_disp = history.getText();
//	        history.setText(hist_disp+display+" = "+Float.toString(SolveTEMP)+'\n');
	        history.append(display+" = "+Float.toString(SolveTEMP)+'\n');
	    	jtfResult.selectAll();
//	        clrBool = true ;
	        ans = true;
//	        history.setCaretPosition(history.getDocument().getLength());
	    }
	}
	public static void main(String[] args) {
	    JavaCalc calc = new JavaCalc();
	    calc.pack();
	    calc.setLocationRelativeTo(null);
	    calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    calc.getRootPane().setDefaultButton(jbtSolve);
	    calc.setTitle("Java Basic Calculator");
	    calc.setVisible(true);
	}
}
//JavaCalculator
