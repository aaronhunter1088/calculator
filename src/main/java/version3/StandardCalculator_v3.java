package version3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class StandardCalculator_v3 extends Calculator_v3 {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER;
	
	// All standard calculators have:
    // The same menu bar
    JMenuBar bar = new JMenuBar();
	final protected AddButtonHandler addButtonHandler = new AddButtonHandler();
	final protected JButton buttonAdd = new JButton("+");
	final protected SubtractButtonHandler subtractButtonHandler = new SubtractButtonHandler();
	final protected JButton buttonSubtract = new JButton("-");
	final protected MultiplyButtonHandler multiplyButtonHandler = new MultiplyButtonHandler();
    final protected JButton buttonMultiply = new JButton("*");
    final protected DivideButtonHandler divideButtonHandler = new DivideButtonHandler();
    final protected JButton buttonDivide = new JButton("/");
    final protected EqualsButtonHandler equalsButtonHandler = new EqualsButtonHandler();
    final protected JButton buttonEquals = new JButton("=");
    
    final String negate = "\u00B1";
    final protected NegateButtonHandler negButtonHandler = new NegateButtonHandler();
    final protected JButton buttonNegate = new JButton(negate);
    // moving up protected boolean addBool = false, subBool = false, mulBool = false, divBool = false, memAddBool = false, memSubBool = false;
    private Calculator_v3 calculator;
    public Calculator_v3 getCalculatorImage1() { return calculator; }
    private void setCalculator(Calculator_v3 calculator) { this.calculator = calculator; }
    
    // 4 types of Standard Calculators: create getters and setters
    protected JPanel currentPanel;
//    protected JPanel panelBasic = new JPanelBasic_v3(this);
//    protected JPanel panelProgrammer;
//    protected JPanel panelScientific;
//    protected JPanel panelDate;
	public JPanel getCurrentJPanel() { return currentPanel; }
	
	/*
	 * TODO: Change method to be a list of tasks that should
	 * occur when we switch panels.
	 * 
	 * method: performTasksWhenChangingJPanels
	 * 
	 * tasks: set currentPanel
	 * 		  set title of frame
	 *        set the mode of the calculator
	 *        perform setup tasks based on mode
	 */
	public void performTasksWhenChangingJPanels(JPanel currentPanel, String title) {
		setCurrentJPanel(currentPanel);
		//setupButtonsAndSuch();
		setupStandardCalculator_v3();

		setMode(determineMode());
		setupRestOfCalculator();

		super.setTitle(title);
		confirm("Switched modes to " + super.getTitle());
	}

	// TODO: When switch mode to programmer, pressing button1 puts onto screen: 11
    // TOOD: Fix!
	public void setupRestOfCalculator() {
	    if (mode == Mode.BASIC) {
	        JPanelBasic_v3.performBasicSetup(calculator);
        } else if (mode == Mode.PROGRAMMER) {
	        JPanelProgrammer_v3.performBasicSetup(calculator);
        }
    }

	public Mode determineMode() {
	    if (currentPanel instanceof JPanelBasic_v3) { this.mode = Mode.BASIC; }
	    else if (currentPanel instanceof  JPanelProgrammer_v3) { this.mode = Mode.PROGRAMMER; }
	    else { this.mode = Mode.BASIC; }
	    return mode;
    }
	
	public void setCurrentJPanel(JPanel currentPanel) {
	    if (this.currentPanel == null) {}
	    else remove(this.currentPanel);
	    this.currentPanel = currentPanel;
	    add(this.currentPanel);

        SwingUtilities.updateComponentTreeUI(calculator);
        calculator.setMinimumSize(getCurrentJPanel().getSize());
        calculator.pack();
	}

    static {
        LOGGER = LogManager.getLogger(StandardCalculator_v3.class);
    }
	public StandardCalculator_v3() throws HeadlessException {
		// TODO Auto-generated constructor stub
	
	}

	public StandardCalculator_v3(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

    public StandardCalculator_v3(String title, GraphicsConfiguration gc) {
        super(title, gc);
        // TODO Auto-generated constructor stub
    }

	// TODO: Implement first
	public StandardCalculator_v3(String title) throws HeadlessException {
		super(Calculator_v3.BASIC); // default title is Basic
		setCalculator(this);
		setCurrentJPanel(new JPanelBasic_v3(this));

        //setCurrentJPanel(panelProgrammer);
		setupStandardCalculator_v3();
		setupMenuBar();
        setMode(determineMode());
        setupRestOfCalculator();
		setImageIcons();
		setMinimumSize(getCurrentPanel().getSize());
		
	}

    public void setupMenuBar() {
        LOGGER.info("Inside setupMenuBar()");
        // Menu Bar and Options
        bar = new JMenuBar(); // create menu bar
        setJMenuBar(bar); // add menu bar to application

        JMenu viewMenu = new JMenu("View"); // create view menu
        viewMenu.setFont(this.font );


        JMenuItem basic = new JMenuItem("Basic");
        basic.setFont(this.font);
        viewMenu.add(basic);
        basic.addActionListener(action -> {
            mode = Mode.BASIC;
            performTasksWhenChangingJPanels(new JPanelBasic_v3(this), Calculator_v3.BASIC);
        });

        JMenuItem programmer = new JMenuItem("Programmer");
        programmer.setFont(this.font);
        viewMenu.add(programmer);
        programmer.addActionListener(action -> {
            mode = Mode.PROGRAMMER;
            performTasksWhenChangingJPanels(new JPanelProgrammer_v3(this), Calculator_v3.PROGRAMMER);
        });
        this.bar.add(viewMenu); // add viewMenu to menu bar
        // Edit Menu and Actions
        JMenu editMenu = new JMenu("Edit"); // create edit menu
        editMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        bar.add(editMenu); // add editMenu to menu bar

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
        copyItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create first item in editMenu
        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_V, java.awt.Event.CTRL_MASK));
        pasteItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create second item in editMenu
        JMenu historyMenu = new JMenu("History");
        historyMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        /// create third time in editMenu

        // add JMenuItems to editMenu
        editMenu.add(copyItem);
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values[3] = textArea.getText(); // to copy
                textarea = textArea.getText();
                confirm();
            }
        });

        editMenu.add(pasteItem);
        pasteItem.addActionListener(
                new ActionListener() {
                    // paste from copyItem
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (values[3].equals(""))
                            LOGGER.info("Temp[3] is null");
                        else
                            LOGGER.info("temp[3]: " + values[3]);
                        textArea.setText(values[3]); // to paste
                        values[valuesPosition] = textArea.getText();
                        textarea = textArea.getText();
                        confirm();
                    }
                }
        );
        editMenu.addSeparator();
        editMenu.add(historyMenu);

        JMenuItem copyHistoryItem = new JMenuItem("Copy History");
        copyHistoryItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create first item in historyMenu
        JMenuItem editItem = new JMenuItem("Edit");
        editItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create second item in historyMenu
        JMenuItem cancelEditItem = new JMenuItem("Cancel Edit");
        cancelEditItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create third item in historyMenu
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create fourth item in historyMenu

        // add JMenuItems to historyMenu
        historyMenu.add(copyHistoryItem);
        historyMenu.add(editItem);
        historyMenu.add(cancelEditItem);
        historyMenu.add(clearItem);

        // Help  Menu and Actions
        JMenu helpMenu = new JMenu("Help"); // create help menu
        helpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        bar.add(helpMenu); // add helpMenu to menu bar

        JMenuItem viewHelpItem = new JMenuItem("View Help");
        viewHelpItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );

        JMenuItem aboutCalculatorItem = new JMenuItem("About Calculator");
        aboutCalculatorItem.setFont(new Font("Segoe UI", Font.PLAIN, 12) );
        // create second item in helpMenu

        // add JMenuItems to helpMenu
        helpMenu.add(viewHelpItem);
        // NEEDS UPDATING
        // Needs a scroll bar
        // Text with hyperlinks
        // Info about how to use
        viewHelpItem.addActionListener(new ActionListener() {
        // display message dialog box when user selects Help....
        @Override
        public void actionPerformed(ActionEvent event) {
            String COPYRIGHT = "\u00a9";
            iconLabel = new JLabel();
            //iconLabel.setIcon(calculator);
            //iconLabel.setText(" ");
            JPanel iconPanel = new JPanel(new GridBagLayout() );

            iconPanel.add(iconLabel);
            textLabel = new JLabel("<html>Apple MacBook Air "
                   + "Version 3.0.1<br>"
                   + COPYRIGHT + " 2018 Microsoft Corporation. All rights reserved.<br><br>"
                   + "Mac OS mojave and its user interface are protected by trademark and all other<br>"
                   + "pending or existing intellectual property right in the United States and other<br>"
                   + "countries/regions."
                   + "<br><br><br>"
                   + "This product is licensed under the License Terms to:<br>"
                   + "Michael Ball</html>", macLogo, SwingConstants.LEFT);
            textLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            textLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.white);
            mainPanel.add(iconLabel);
            mainPanel.add(textLabel);
            JOptionPane.showMessageDialog(StandardCalculator_v3.this,
                   mainPanel, "Help", JOptionPane.PLAIN_MESSAGE);
        }
    });
        helpMenu.addSeparator();
        helpMenu.add(aboutCalculatorItem);
        aboutCalculatorItem.addActionListener(
            new ActionListener() // anonymous inner class
            {
                // display message dialog box when user selects About....
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    String COPYRIGHT = "\u00a9";
                    //iconLabel = new JLabel(calculator2);
                    //iconLabel.setIcon(calculator2);
                    //iconLabel.setVisible(true);
                    JPanel iconPanel = new JPanel(new GridBagLayout() );
                    iconPanel.add(iconLabel);
                    textLabel = new JLabel("<html>Apple MacBook Air"
                            + "Version 3.0.1 (Build 1)<br>"
                            + COPYRIGHT + " 2018 Microsoft Corporation. All rights reserved.<br><br>"
                            + "Mac OS mojave and its user interface are<br>"
                            + "protected by trademark and all other pending or existing intellectual property<br>"
                            + "right in the United States and other countries/regions."
                            + "<br><br><br>"
                            + "This product is licensed under the License Terms to:<br>"
                            + "Michael Ball</html>", macLogo, SwingConstants.LEFT);
                    textLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                    textLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

                    JPanel mainPanel = new JPanel();
                    mainPanel.add(iconLabel);
                    mainPanel.add(textLabel);
                    JOptionPane.showMessageDialog(StandardCalculator_v3.this,
                            mainPanel, "About Calculator", JOptionPane.PLAIN_MESSAGE);
                }
            }
        );
        LOGGER.info("Finished. Leaving setupMenuBar()");
    } // end public setMenuBar
	
	public void setupStandardCalculator_v3() {
		buttonAdd.setFont(font);
        buttonAdd.setPreferredSize(new Dimension(35, 35) );
        buttonAdd.setBorder(new LineBorder(Color.BLACK));
        buttonAdd.setEnabled(true);
        buttonAdd.addActionListener(addButtonHandler);
        
        buttonSubtract.setFont(font);
        buttonSubtract.setPreferredSize(new Dimension(35, 35) );
        buttonSubtract.setBorder(new LineBorder(Color.BLACK));
        buttonSubtract.setEnabled(true);
        buttonSubtract.addActionListener(subtractButtonHandler);

        buttonMultiply.setFont(font);
        buttonMultiply.setPreferredSize(new Dimension(35, 35) );
        buttonMultiply.setBorder(new LineBorder(Color.BLACK));
        buttonMultiply.setEnabled(true);
        buttonMultiply.addActionListener(multiplyButtonHandler);
        
        buttonDivide.setFont(font);
        buttonDivide.setPreferredSize(new Dimension(35, 35) );
        buttonDivide.setBorder(new LineBorder(Color.BLACK));
        buttonDivide.setEnabled(true);
        buttonDivide.addActionListener(divideButtonHandler);
        
        buttonEquals.setFont(font);
        buttonEquals.setPreferredSize(new Dimension(35, 70) );
        buttonEquals.setBorder(new LineBorder(Color.BLACK));
        buttonEquals.setEnabled(true);
        buttonEquals.addActionListener(equalsButtonHandler);
        
        buttonNegate.setFont(font);
        buttonNegate.setPreferredSize(new Dimension(35, 35) );
        buttonNegate.setBorder(new LineBorder(Color.BLACK));
        buttonNegate.setEnabled(true);
        //buttonNegate.addActionListener(negButtonHandler);
        
        add(getCurrentPanel());
	}
	
	public void add() {
    	LOGGER.info("value[0]: '" + values[0] + "'");
    	LOGGER.info("value[1]: '" + values[1] + "'");
        double result = Double.parseDouble(values[0]) + Double.parseDouble(values[1]); // create result forced double
        LOGGER.info(values[0] + " + " + values[1] + " = " + result);
        values[0] = Double.toString(result);
        LOGGER.info("addBool: "+addBool);
        LOGGER.info("subBool: "+subBool);
        LOGGER.info("mulBool: "+mulBool);
        LOGGER.info("divBool: "+divBool);
        if (result % 1 == 0) {
        	LOGGER.info("We have a whole number");
        	textarea = Double.toString(result);
            values[0] = textarea.substring(0, textarea.length()-2); // textarea changed to whole number, or int
            textArea.setText("\n" + values[0]);
            if (Integer.parseInt(values[0]) < 0 ) {
                textarea = textArea.getText(); // temp[valuesPosition]
                LOGGER.info("textarea: '" + textarea + "'");
                textarea = textarea.substring(1, textarea.length());
                LOGGER.info("textarea: '" + textarea + "'");
                textArea.setText("\n "+textarea.replaceAll("-", "") + "-"); // update textArea
            }
            dotButtonPressed = false;
            buttonDot.setEnabled(true);
        } else { // if double == double, keep decimal and number afterwards
        	LOGGER.info("We have a decimal");
            if (Double.parseDouble(values[0]) < 0.0 ) {
                values[0] = formatNumber(values[0]);
                LOGGER.info("textarea: '" + textarea + "'");
                textarea = values[0];
                LOGGER.info("textarea: '" + textarea + "'");
                textarea = textarea.substring(1, textarea.length());
                LOGGER.info("textarea: '" + textarea + "'");
                textArea.setText("\n" + textarea + "-"); // update textArea
                LOGGER.info("temp["+valuesPosition+"] '" + values[valuesPosition] + "'");
            } else {
                textArea.setText("\n" + formatNumber(values[0]));
                values[0] = formatNumber(values[0]);
            }
        }
    }
    
    public void subtract() {
        LOGGER.info("value[0]: '" + values[0] + "'");
        LOGGER.info("value[1]: '" + values[1] + "'");
        double result = Double.parseDouble(values[0]) - Double.parseDouble(values[1]); // create result forced double
        LOGGER.info(values[0] + " - " + values[1] + " = " + result);
        values[0] = Double.toString(result); // store result
        LOGGER.info("addBool: " + addBool);
        LOGGER.info("subBool: " + subBool);
        LOGGER.info("mulBool: " + mulBool);
        LOGGER.info("divBool: " + divBool);
        if (result % 1 == 0) {
            //textArea.setText(temp[0]);
            textarea = Double.toString(result);
            textarea = textarea.substring(0, textarea.length()-2); // textarea changed to whole number, or int
            values[0] = textarea; // update storing
            textArea.setText("\n" + values[0]);
            if (Integer.parseInt(values[0]) < 0 ) {
                //textarea = textArea.getText(); 
                //System.out.printf("\n%s", textarea);
                textarea = textarea.substring(1, textarea.length());
                LOGGER.info("textarea: " + textarea);
                textArea.setText("\n" + textarea.replaceAll(" ", "") + "-".replaceAll(" ", "")); // update textArea
                LOGGER.info("temp[0]: " + values[0]);
            }
        } else {// if double == double, keep decimal and number afterwards
            if (Double.parseDouble(values[0]) < 0.0 ) {
                values[0] = formatNumber(values[0]);
                textarea = values[0];
                LOGGER.info("textarea: " + textarea);
                textarea = textarea.substring(1, textarea.length());
                LOGGER.info("textarea: " + textarea);
                textArea.setText("\n" + textarea + "-"); // update textArea
                LOGGER.info("temp["+valuesPosition+"]: " + values[valuesPosition]);
            } else {
                textArea.setText("\n" + formatNumber(values[0]));
            }
        }
    }
    
    public void multiply() {
        LOGGER.info("value[0]: '" + values[0] + "'");
        LOGGER.info("value[1]: '" + values[1] + "'");
        double result = Double.parseDouble(values[0]) * Double.parseDouble(values[1]); // create result forced double
        LOGGER.info(values[0] + " * " + values[1] + " = " + result);
        values[0] = Double.toString(result); // store result
        LOGGER.info("addBool: " + addBool);
        LOGGER.info("subBool: " + subBool);
        LOGGER.info("mulBool: " + mulBool);
        LOGGER.info("divBool: " + divBool);
        if (result % 1 == 0) {
            textArea.setText(values[0]);
            textarea = textArea.getText().replaceAll("\\n", "");
            textarea = textarea.substring(0, textarea.length()-2); // textarea changed to whole number, or int
            values[0] = textarea; // update storing
            textArea.setText("\n" + values[0]);
            if (Integer.parseInt(values[0]) < 0 ) {
                textarea = textArea.getText().replaceAll("\\n", ""); // temp[2]
                LOGGER.info("textarea: " + textarea);
                textarea = textarea.substring(1, textarea.length());
                LOGGER.info("textarea: " + textarea);
                textArea.setText("\n" + textarea + "-"); // update textArea
                LOGGER.info("temp["+valuesPosition+"]: " + values[valuesPosition]);
            }
        } else {// if double == double, keep decimal and number afterwards
            textArea.setText("\n" + formatNumber(values[0]));
        }
    }
    
    public void divide() {
        LOGGER.info("value[0]: '" + values[0] + "'");
        LOGGER.info("value[1]: '" + values[1] + "'");
        if (!values[1].equals("0")) { 
            // if the second number is not zero, divide as usual
            double result = Double.parseDouble(values[0]) / Double.parseDouble(values[1]); // create result forced double
            LOGGER.info(values[0] + " / " + values[1] + " = " + result);
            values[0] = Double.toString(result); // store result
            LOGGER.info("addBool: " + addBool);
            LOGGER.info("subBool: " + subBool);
            LOGGER.info("mulBool: " + mulBool);
            LOGGER.info("divBool: " + divBool);
            if (Double.parseDouble(values[0]) % 1 == 0) {
                // if int == double, cut off decimal and zero
                textArea.setText("\n" + values[0]);
                textarea = textArea.getText().replaceAll("\\n", "");
                textarea = textarea.substring(0, textarea.length()-2); // textarea changed to whole number, or int
                values[0] = textarea; // update storing
                textArea.setText("\n" + values[0]);
                if (Integer.parseInt(values[0]) < 0 ) {
                    textarea = textArea.getText().replaceAll("\\n", ""); // temp[2]
                    LOGGER.info("textarea: " + textarea);
                    textarea = textarea.substring(1, textarea.length());
                    LOGGER.info("textarea: " + textarea);
                    textArea.setText("\n" + textarea + "-"); // update textArea
                    LOGGER.info("temp["+valuesPosition+"]: " + values[valuesPosition]);
                }
            } else {
                // if double == double, keep decimal and number afterwards
                textArea.setText("\n" + formatNumber(values[0]));
            }
        } else if (values[1].equals("0")) {
            String result = "0";
            LOGGER.warn("Attempting to divide by zero. Cannot divide by 0!");
            textArea.setText("Cannot divide by 0");
            values[0] = result;
            firstNumBool = true;
        }
    }

    // used to keep 2 decimals on the number at all times
    public String formatNumber(String num) {
        DecimalFormat df = new DecimalFormat();
        if (!numberIsNegative) {
            if (num.length() == 2) df = new DecimalFormat("0.00");
            if (num.length() == 3) df = new DecimalFormat("0.000");
        } else {
            if (num.length() == 3) df = new DecimalFormat("0.0");
            if (num.length() == 4) df = new DecimalFormat("0.00");
            if (num.length() == 5) df = new DecimalFormat("0.000");
        }
        double number = Double.parseDouble(num);
        number = Double.valueOf(df.format(number));
        String numberAsStr = Double.toString(number);
        num = df.format(number);
        LOGGER.info("Formatted: " + num);
        if (numberAsStr.charAt(numberAsStr.length()-3) == '.' && numberAsStr.substring(numberAsStr.length()-3).equals(".00") ) {
            numberAsStr = numberAsStr.substring(0, numberAsStr.length()-3);
            LOGGER.info("Formatted again: " + num);
        }
        return numberAsStr;
    }

    class AddButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	LOGGER.info("AddButtonHandler started");
        	LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (addBool == false && subBool == false && mulBool == false && divBool == false && !textArea.getText().equals("") && !calculator.textAreaContainsBadText()) {
                //textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                textarea = textArea.getText().replaceAll("\\n", "");
                textArea.setText("\n" + " " + e.getActionCommand() + " " + textarea);
                LOGGER.info("textArea: " + textArea.getText().replaceAll("\\n", "")); // print out textArea has proper value confirmation; recall text area's orientation
                LOGGER.info("values["+valuesPosition+"] is "+values[valuesPosition]+ " after addButton pushed"); // confirming proper textarea before moving on
                addBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
                if (memorySwitchBool == true) {
                	memoryValues[memoryPosition] += " + ";
                }
            } else if (addBool == true && !values[1].equals("")) {
                add(); //addition();
                addBool = resetOperator(addBool); // sets addBool to false
                addBool = true;
            } else if (subBool == true && !values[1].equals("")) {
                subtract();
                subBool = resetOperator(subBool);
                addBool = true;
            } else if (mulBool == true && !values[1].equals("")) {
                multiply();
                mulBool = resetOperator(mulBool);
                addBool = true;
            } else if (divBool == true && !values[1].equals("")) {
                divide();
                divBool = resetOperator(divBool);
                addBool = true;
            } else if (calculator.textAreaContainsBadText()) {
                textArea.setText(e.getActionCommand() + " " +  values[0]); // "userInput +" // temp[valuesPosition]
                addBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true || subBool == true || mulBool == true || divBool == true) {
            	LOGGER.info("already chose an operator. choose another number.");
            } 
            
            textarea = textArea.getText();
            buttonDot.setEnabled(true);
            dotButtonPressed = false;
            dotButtonPressed = false;
            confirm();
        }
    }

	class SubtractButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LOGGER.info("SubtractButtonHandler class started");
            LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (addBool == false && subBool == false && mulBool == false && divBool == false && !textArea.getText().equals("") && !textArea.getText().equals("Invalid textarea") && !textArea.getText().equals("Cannot divide by 0")) {
            	textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                textarea = textArea.getText().replaceAll("\\n", "");
                textArea.setText("\n" + " " + e.getActionCommand() + " " + textarea);
                LOGGER.info("textArea: " + textArea.getText()); // print out textArea has proper value confirmation; recall text area's orientation
                LOGGER.info("temp["+valuesPosition+"] is "+values[valuesPosition]+ " after addButton pushed"); // confirming proper textarea before moving on
                subBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true && !values[1].equals("")) {
                add();
                addBool = resetOperator(addBool);
                subBool = true;
            } else if (subBool == true && !values[1].equals("")) {
                subtract();
                subBool = resetOperator(subBool);
                subBool = true;
            } else if (mulBool == true && !values[1].equals("")) {
                multiply();
                mulBool = resetOperator(mulBool);
                subBool = true;
            } else if (divBool == true && !values[1].equals("")) {
                divide();
                divBool = resetOperator(divBool);
                subBool = true;
            } else if (textArea.getText().equals("Invalid textarea") || textArea.getText().equals("Cannot divide by 0")) {
                textArea.setText(e.getActionCommand() + " " +  values[0]); // "userInput +" // temp[valuesPosition]
                subBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true || subBool == true || mulBool == true || divBool == true) {
            	LOGGER.info("already chose an operator. next number is negative...");
            	negatePressed = true;
        	}
            textarea = textArea.getText();
            buttonDot.setEnabled(true);
            dotButtonPressed = false;
            dotButtonPressed = false;
            confirm();
        }
    }

	class MultiplyButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	LOGGER.info("MultiplyButtonHandler class started");
        	LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (addBool == false && subBool == false && mulBool == false && divBool == false && !textArea.getText().equals("") && !textArea.getText().equals("Invalid textarea") && !textArea.getText().equals("Cannot divide by 0")) {
            	textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                textarea = textArea.getText().replaceAll("\\n", "");
                textArea.setText("\n" + " " + e.getActionCommand() + " " + textarea);
                LOGGER.info("textArea: \\n" + textArea.getText().replaceAll("\n","")); // print out textArea has proper value confirmation; recall text area's orientation
                LOGGER.info("values["+valuesPosition+"] is "+values[valuesPosition]+ " after buttonMultiply was pushed"); // confirming proper textarea before moving on
                mulBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true && !values[1].equals("")) {
                add(); // addition();
                addBool = resetOperator(addBool);
                mulBool = true;
            } else if (subBool == true && !values[1].equals("")) {
                subtract();
                subBool = resetOperator(subBool);
                mulBool = true;
            } else if (mulBool == true && !values[1].equals("")) {
                multiply();
                mulBool = resetOperator(mulBool);
                mulBool = true;
            } else if (divBool == true && !values[1].equals("")) {
                divide();
                divBool = resetOperator(divBool);
                mulBool = true;
            } else if (textArea.getText().equals("Invalid textarea") || textArea.getText().equals("Cannot divide by 0")) {
                textArea.setText(e.getActionCommand() + " " +  values[0]); // "userInput +" // temp[valuesPosition]
                mulBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true || subBool == true || mulBool == true || divBool == true) {
            	LOGGER.info("already chose an operator. choose another number.");
            	
        	}
            textarea = textArea.getText();
            buttonDot.setEnabled(true);
            dotButtonPressed = false;
            dotButtonPressed = false;
            confirm();
        }
    }

	class DivideButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	LOGGER.info("DivideButtonHandler class started");
        	LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (addBool == false && subBool == false && mulBool == false && divBool == false && !textArea.getText().equals("") && !textArea.getText().equals("Invalid textarea") && !textArea.getText().equals("Cannot divide by 0")) {
            	textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                textarea = textArea.getText().replaceAll("\\n", "");
                textArea.setText("\n" + " " + e.getActionCommand() + " " + textarea);
                LOGGER.info("textArea: " + textArea.getText()); // print out textArea has proper value confirmation; recall text area's orientation
                LOGGER.info("temp["+valuesPosition+"] is "+values[valuesPosition]+ " after addButton pushed"); // confirming proper textarea before moving on
                divBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true && !values[1].equals("")) {
                add(); // addition();
                addBool = resetOperator(addBool); // sets addBool to false
                divBool = true;
            } else if (subBool == true && !values[1].equals("")) {
                subtract();
                subBool = resetOperator(subBool);
                divBool = true;
            } else if (mulBool == true && !values[1].equals("")) {
                multiply();
                mulBool = resetOperator(mulBool);
                divBool = true;
            } else if (divBool == true && !values[1].equals("") & !values[1].equals("0")) {
                divide();
                divBool = resetOperator(divBool);
                divBool = true;
            } else if (textArea.getText().equals("Invalid textarea") || textArea.getText().equals("Cannot divide by 0")) {
                textArea.setText(e.getActionCommand() + " " +  values[0]); // "userInput +" // temp[valuesPosition]
                divBool = true; // sets logic for arithmetic
                firstNumBool = false; // sets logic to perform operations when collecting second number
                dotButtonPressed = false;
                valuesPosition++; // increase valuesPosition for storing textarea
            } else if (addBool == true || subBool == true || mulBool == true || divBool == true) {
            	LOGGER.info("already chose an operator. choose another number.");
            }
            textarea = textArea.getText();
            buttonDot.setEnabled(true);
            dotButtonPressed = false;
            dotButtonPressed = false;
            confirm();
        }
    }
	
	class EqualsButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	LOGGER.info("EqualsButtonHandler class started");
        	LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (addBool == true) {
                add(); // addition();
                addBool = resetOperator(addBool);
                valuesPosition = 0;
            } 
            else if (subBool == true){
                subtract();
                subBool = resetOperator(subBool);
                valuesPosition = 0;
            } 
            else if (mulBool == true){
                multiply();
                mulBool = resetOperator(mulBool);
                valuesPosition = 0;
            } 
            else if (divBool == true){
                divide();
                divBool = resetOperator(divBool);
                valuesPosition = 0;
            } else if (values[0].equals("") && values[1].equals("")) {
                // if temp[0] and temp[1] do not have a number
                valuesPosition = 0;
            } else if (textArea.getText().equals("Invalid textarea") || textArea.getText().equals("Cannot divide by 0")) {
                textArea.setText(e.getActionCommand() + " " +  values[valuesPosition]); // "userInput +" // temp[valuesPosition]
                valuesPosition = 1;
                firstNumBool = true;
            } 
            values[1] = ""; // this is not done in addition, subtraction, multiplication, or division
            textarea = textArea.getText();
            firstNumBool = true;
            dotButtonPressed = false;
            confirm();
        }
    }

    /**
     * Handles the logic when user clicks the Negate button
     */
    class NegateButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	LOGGER.info("NegateButtonHandler started");
        	LOGGER.info("button: " + e.getActionCommand()); // print out button confirmation
            if (!textarea.equals("")) {
                textarea = textarea.replaceAll("\n","");
                if (isNegativeNumber(textarea)) {
                    textarea = convertToPositive(textarea);
                    LOGGER.debug("textarea: " + textarea);
                    getTextArea().setText("\n"+textarea);
                } else {
                    getTextArea().setText("\n"+textarea+"-");
                    textarea = convertToNegative(textarea);
                    LOGGER.debug("textarea: " + textarea);
                }
            }
            values[valuesPosition] = textarea;
            confirm();    
        }
    }

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

    
}




