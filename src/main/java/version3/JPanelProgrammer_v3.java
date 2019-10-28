package version3;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class JPanelProgrammer_v3 extends JPanel {

    protected final static Logger LOGGER;
    static {
        LOGGER = LogManager.getLogger(JPanelProgrammer_v3.class);
    }

    private static final long serialVersionUID = 1L;

    private GridBagLayout programmerLayout; // layout of the calculator
    private GridBagLayout otherButtonLayout = new GridBagLayout();
    private JPanel allOtherButtonsPanel = new JPanel(otherButtonLayout);
    private GridBagConstraints constraints; // layout's constraints

    private final ButtonGroup btnGroupOne = new ButtonGroup();
    private final ButtonGroup btnGroupTwo = new ButtonGroup();
    private final JPanel buttonGroup1ButtonPanel = new JPanel(); // contains the first button group
    private final JPanel buttonGroup2ButtonPanel = new JPanel(); // contains the second button group

    protected JTextArea textArea1 = new JTextArea(1,5); // rows, columns
    protected JTextArea textArea2 = new JTextArea(1,5); // rows, columns
    protected JTextArea textArea3 = new JTextArea(1,5); // rows, columns

    final private JButton button = new JButton(" ");
    final private JButton buttonMod = new JButton("Mod");
    final private JButton buttonLPar = new JButton("(");
    final private JButton buttonRPar = new JButton(")");
    final private JButton buttonRol = new JButton ("RoL");
    final private JButton buttonRor = new JButton ("RoR");
    final private JButton buttonOr = new JButton ("OR");
    final private JButton buttonXor = new JButton ("XOR");
    final private JButton buttonLSh = new JButton ("Lsh");
    final private JButton buttonRSh = new JButton ("Rsh");
    final private JButton buttonNot = new JButton ("NOT");
    final private JButton buttonAnd = new JButton ("AND");
    // A - F
    final private JButton buttonA = new JButton("A");
    final private JButton buttonB = new JButton("B");
    final private JButton buttonC = new JButton("C");
    final private JButton buttonD = new JButton("D");
    final private JButton buttonE = new JButton("E");
    final private JButton buttonF = new JButton("F");
    final private JRadioButton buttonHex = new JRadioButton("Hex");
    final private JRadioButton buttonDec = new JRadioButton("Dec");
    final private JRadioButton buttonOct = new JRadioButton("Oct");
    final private JRadioButton buttonBin = new JRadioButton("Bin");
    final private JRadioButton buttonQWord = new JRadioButton("Qword");
    final private JRadioButton buttonDWord = new JRadioButton("Dword");
    final private JRadioButton buttonWord = new JRadioButton("Word");
    final private JRadioButton buttonByte = new JRadioButton("Byte");
    final private JButton buttonFraction = new JButton("1/x");
    final private JButton buttonPercent = new JButton("%");
    final private JButton buttonSqrt = new JButton("\u221A");
    protected StringBuffer conversion = new StringBuffer();
    protected StandardCalculator_v3 calculator;

    public JPanelProgrammer_v3(StandardCalculator_v3 calculator) {
        setMinimumSize(new Dimension(600,400));
        programmerLayout = new GridBagLayout();
        setLayout(programmerLayout); // set frame layout
        constraints = new GridBagConstraints(); // instantiate constraints
        try {
            setupPanel_v3(calculator);
        } catch (Calculator_v3Error ce) {
            LOGGER.error(ce.getMessage());
        }
        setCalculator(calculator);
        addComponentsToPanel_v3(calculator);
        //performCalculatorTypeSwitchOperations(); // this should be common to all faces
    }
    public JPanelProgrammer_v3() {}

    /************* Start of methods here ******************/

    public void setupPanel_v3(StandardCalculator_v3 calculator) throws Calculator_v3Error {
        LOGGER.info("Starting setupProgrammerPanel_v3");
        constraints.insets = new Insets(5,5,5,5); //THIS LINE ADDS PADDING; LOOK UP TO LEARN MORE

        try {
            setButtons2To9(false);
            calculator.buttonNegate.setEnabled(false);
        } catch (NullPointerException e) {}
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonE.setEnabled(false);
        buttonF.setEnabled(false);
        buttonSqrt.setEnabled(false);
        buttonPercent.setEnabled(false);
        buttonFraction.setEnabled(false);

        buttonByte.setSelected(true);
        buttonBin.setSelected(true);

        buttonSqrt.setEnabled(false);
        buttonSqrt.setFont(this.calculator.font);
        buttonSqrt.setPreferredSize(new Dimension(35, 35) );
        buttonSqrt.setBorder(new LineBorder(Color.BLACK));

        buttonPercent.setEnabled(false);
        buttonPercent.setFont(this.calculator.font);
        buttonPercent.setPreferredSize(new Dimension(35, 35) );
        buttonPercent.setBorder(new LineBorder(Color.BLACK));

        buttonFraction.setEnabled(false);
        buttonFraction.setFont(this.calculator.font);
        buttonFraction.setPreferredSize(new Dimension(35, 35));
        buttonFraction.setBorder(new LineBorder(Color.BLACK));
        // add buttons to buttonGroupOne
        btnGroupOne.add(buttonHex);
        btnGroupOne.add(buttonDec);
        btnGroupOne.add(buttonOct);
        btnGroupOne.add(buttonBin);
        // add buttons to ButtonGroupTwo
        btnGroupTwo.add(buttonQWord);
        btnGroupTwo.add(buttonDWord);
        btnGroupTwo.add(buttonWord);
        btnGroupTwo.add(buttonByte);

        button.setPreferredSize(new Dimension(35,35));
        button.setBorder(new LineBorder(Color.BLACK));

        buttonMod.setFont(this.calculator.font);
        buttonMod.setPreferredSize(new Dimension(35,35));
        buttonMod.setBorder(new LineBorder(Color.BLACK));

        buttonLPar.setFont(this.calculator.font);
        buttonLPar.setPreferredSize(new Dimension(35,35));
        buttonLPar.setBorder(new LineBorder(Color.BLACK));

        buttonRPar.setFont(this.calculator.font);
        buttonRPar.setPreferredSize(new Dimension(35,35));
        buttonRPar.setBorder(new LineBorder(Color.BLACK));

        buttonRol.setFont(this.calculator.font);
        buttonRol.setPreferredSize(new Dimension(35,35));
        buttonRol.setBorder(new LineBorder(Color.BLACK));

        buttonRor.setFont(this.calculator.font);
        buttonRor.setPreferredSize(new Dimension(35,35));
        buttonRor.setBorder(new LineBorder(Color.BLACK));

        buttonOr.setFont(this.calculator.font);
        buttonOr.setPreferredSize(new Dimension(35,35));
        buttonOr.setBorder(new LineBorder(Color.BLACK));
        buttonOr.addActionListener(action -> {
            try {
                performButtonOrActions(action);
            } catch (Calculator_v3Error calculator_v3Error) {
                LOGGER.error(calculator_v3Error.getMessage());
            }
        });

        buttonXor.setFont(this.calculator.font);
        buttonXor.setPreferredSize(new Dimension(35,35));
        buttonXor.setBorder(new LineBorder(Color.BLACK));

        buttonLSh.setFont(this.calculator.font);
        buttonLSh.setPreferredSize(new Dimension(35,35));
        buttonLSh.setBorder(new LineBorder(Color.BLACK));

        buttonRSh.setFont(this.calculator.font);
        buttonRSh.setPreferredSize(new Dimension(35,35));
        buttonRSh.setBorder(new LineBorder(Color.BLACK));

        buttonNot.setFont(this.calculator.font);
        buttonNot.setPreferredSize(new Dimension(35,35));
        buttonNot.setBorder(new LineBorder(Color.BLACK));
        buttonNot.addActionListener(action -> {
            performButtonNotActions();
        });

        buttonAnd.setFont(this.calculator.font);
        buttonAnd.setPreferredSize(new Dimension(35,35));
        buttonAnd.setBorder(new LineBorder(Color.BLACK));
        // A - F
        buttonA.setFont(this.calculator.font);
        buttonA.setPreferredSize(new Dimension(35, 35) );
        buttonA.setBorder(new LineBorder(Color.BLACK));

        buttonB.setFont(this.calculator.font);
        buttonB.setPreferredSize(new Dimension(35, 35) );
        buttonB.setBorder(new LineBorder(Color.BLACK));

        buttonC.setFont(this.calculator.font);
        buttonC.setPreferredSize(new Dimension(35, 35));
        buttonC.setBorder(new LineBorder(Color.BLACK));

        buttonD.setFont(this.calculator.font);
        buttonD.setPreferredSize(new Dimension(35, 35));
        buttonD.setBorder(new LineBorder(Color.BLACK));

        buttonE.setFont(this.calculator.font);
        buttonE.setPreferredSize(new Dimension(35, 35));
        buttonE.setBorder(new LineBorder(Color.BLACK));

        buttonF.setFont(this.calculator.font);
        buttonF.setPreferredSize(new Dimension(35, 35));
        buttonF.setBorder(new LineBorder(Color.BLACK));

        // memory
        calculator.buttonMemoryClear.setFont(this.calculator.font);
        calculator.buttonMemoryClear.setPreferredSize(new Dimension(35, 35) );
        calculator.buttonMemoryClear.setBorder(new LineBorder(Color.BLACK));
        calculator.buttonMemoryClear.setBorder(new LineBorder(Color.BLACK));

        calculator.buttonMemoryRecall.setFont(this.calculator.font);
        calculator.buttonMemoryRecall.setPreferredSize(new Dimension(35, 35) );
        calculator.buttonMemoryRecall.setBorder(new LineBorder(Color.BLACK));
        calculator.buttonMemoryRecall.setSize(new Dimension(5, 5) );

        calculator.buttonMemoryStore.setFont(this.calculator.font);
        calculator.buttonMemoryStore.setPreferredSize(new Dimension(35, 35) );
        calculator.buttonMemoryStore.setBorder(new LineBorder(Color.BLACK));

        calculator.buttonMemoryAddition.setFont(this.calculator.font);
        calculator.buttonMemoryAddition.setPreferredSize(new Dimension(35, 35) );
        calculator.buttonMemoryAddition.setBorder(new LineBorder(Color.BLACK));

        calculator.buttonMemorySubtraction.setFont(this.calculator.font);
        calculator.buttonMemorySubtraction.setPreferredSize(new Dimension(35, 35) );
        calculator.buttonMemorySubtraction.setBorder(new LineBorder(Color.BLACK));

        LOGGER.info("End setupProgrammerPanel_v3() ");
    }
    public void addComponentsToPanel_v3(StandardCalculator_v3 calculator) {
        LOGGER.info("Starting addComponentsToProgrammerPanel_v3");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(9,9,0,5);
        calculator.getTextArea().setBorder(new LineBorder(Color.BLACK));
        addComponent(calculator.getTextArea(), 0, 0, 8, 2);
        constraints.insets = new Insets(5,5,5,5);

        buttonGroup1ButtonPanel.setLayout(new GridLayout(4,1));
        // add buttons to panel
        buttonGroup1ButtonPanel.add(buttonHex);
        buttonGroup1ButtonPanel.add(buttonDec);
        buttonGroup1ButtonPanel.add(buttonOct);
        buttonGroup1ButtonPanel.add(buttonBin);
        buttonBin.addActionListener(action -> {});
        buttonOct.addActionListener(action -> {});
        buttonDec.addActionListener(action -> {});
        buttonHex.addActionListener(action -> {});
        Border border = buttonGroup1ButtonPanel.getBorder();
        Border margin = new TitledBorder("Base");
        buttonGroup1ButtonPanel.setBorder(new CompoundBorder(border, margin));
        addComponent(buttonGroup1ButtonPanel, 4, 0, 1, 4);

        buttonGroup2ButtonPanel.setLayout(new GridLayout(4,1)); //rows and columns
        // add buttons to panel
        buttonGroup2ButtonPanel.add(buttonQWord);
        buttonGroup2ButtonPanel.add(buttonDWord);
        buttonGroup2ButtonPanel.add(buttonWord);
        buttonGroup2ButtonPanel.add(buttonByte);

        buttonQWord.addActionListener(action -> {});
        buttonDWord.addActionListener(action -> {});
        buttonWord.addActionListener(action -> {});
        buttonByte.addActionListener(action -> {});
        Border border2 = buttonGroup2ButtonPanel.getBorder();
        Border margin2 = new TitledBorder("Byte");
        buttonGroup2ButtonPanel.setBorder(new CompoundBorder(border2, margin2));
        // add panel to Calculator
        addComponent(buttonGroup2ButtonPanel, 8, 0, 1, 4);

        constraints.insets = new Insets(5,0,1,5); //THIS LINE ADDS PADDING; LOOK UP TO LEARN MORE
        setComponent(button, 0, 0, 1, 1, otherButtonLayout);
        setComponent(buttonMod, 0, 1, 1, 1, otherButtonLayout);
        setComponent(buttonA, 0, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMemoryClear, 0, 3, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMemoryRecall, 0, 4, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMemoryStore, 0, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMemoryAddition, 0, 6, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMemorySubtraction, 0, 7, 1, 1, otherButtonLayout);
        allOtherButtonsPanel.add(button);
        allOtherButtonsPanel.add(buttonMod);
        allOtherButtonsPanel.add(buttonA);
        allOtherButtonsPanel.add(calculator.buttonMemoryClear);
        allOtherButtonsPanel.add(calculator.buttonMemoryRecall);
        allOtherButtonsPanel.add(calculator.buttonMemoryStore);
        allOtherButtonsPanel.add(calculator.buttonMemoryAddition);
        allOtherButtonsPanel.add(calculator.buttonMemorySubtraction);

        setComponent(buttonLPar, 1, 0, 1, 1, otherButtonLayout);
        setComponent(buttonRPar, 1, 1, 1, 1, otherButtonLayout);
        setComponent(buttonB, 1, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonDelete, 1, 3, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonClearEntry, 1, 4, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonClear, 1, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonNegate, 1, 6, 1, 1, otherButtonLayout);
        setComponent(buttonSqrt, 1, 7, 1, 1, otherButtonLayout);
        allOtherButtonsPanel.add(buttonLPar);
        allOtherButtonsPanel.add(buttonRPar);
        allOtherButtonsPanel.add(buttonB);
        allOtherButtonsPanel.add(calculator.buttonDelete);
        allOtherButtonsPanel.add(calculator.buttonClearEntry);
        allOtherButtonsPanel.add(calculator.buttonClear);
        allOtherButtonsPanel.add(calculator.buttonNegate);
        allOtherButtonsPanel.add(buttonSqrt);

        setComponent(buttonRol, 2, 0, 1, 1, otherButtonLayout);
        setComponent(buttonRor, 2, 1, 1, 1, otherButtonLayout);
        setComponent(buttonC, 2, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.button7, 2, 3, 1, 1, otherButtonLayout);
        setComponent(calculator.button8, 2, 4, 1, 1, otherButtonLayout);
        setComponent(calculator.button9, 2, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonDivide, 2, 6, 1, 1, otherButtonLayout);
        setComponent(buttonPercent, 2, 7, 1, 1, otherButtonLayout);
        allOtherButtonsPanel.add(buttonRol);
        allOtherButtonsPanel.add(buttonRor);
        allOtherButtonsPanel.add(buttonC);
        allOtherButtonsPanel.add(calculator.button7);
        allOtherButtonsPanel.add(calculator.button8);
        allOtherButtonsPanel.add(calculator.button9);
        allOtherButtonsPanel.add(calculator.buttonDivide);
        allOtherButtonsPanel.add(buttonPercent);

        setComponent(buttonOr, 3, 0, 1, 1, otherButtonLayout);
        setComponent(buttonXor, 3, 1, 1, 1, otherButtonLayout);
        setComponent(buttonD, 3, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.button4, 3, 3, 1, 1, otherButtonLayout);
        setComponent(calculator.button5, 3, 4, 1, 1, otherButtonLayout);
        setComponent(calculator.button6, 3, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonMultiply, 3, 6, 1, 1, otherButtonLayout);
        setComponent(buttonFraction, 3, 7, 1, 1, otherButtonLayout);
        allOtherButtonsPanel.add(buttonOr);
        allOtherButtonsPanel.add(buttonXor);
        allOtherButtonsPanel.add(buttonD);
        allOtherButtonsPanel.add(calculator.button4);
        allOtherButtonsPanel.add(calculator.button5);
        allOtherButtonsPanel.add(calculator.button6);
        allOtherButtonsPanel.add(calculator.buttonMultiply);
        allOtherButtonsPanel.add(buttonFraction);

        setComponent(buttonLSh, 4, 0, 1, 1, otherButtonLayout);
        setComponent(buttonRSh, 4, 1, 1, 1, otherButtonLayout);
        setComponent(buttonE, 4, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.button1, 4, 3, 1, 1, otherButtonLayout);
        setComponent(calculator.button2, 4, 4, 1, 1, otherButtonLayout);
        setComponent(calculator.button3, 4, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonSubtract, 4, 6, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonEquals, 4, 7, 1, 2, otherButtonLayout);
        allOtherButtonsPanel.add(buttonLSh);
        allOtherButtonsPanel.add(buttonRSh);
        allOtherButtonsPanel.add(buttonE);
        allOtherButtonsPanel.add(calculator.button1);
        allOtherButtonsPanel.add(calculator.button2);
        allOtherButtonsPanel.add(calculator.button3);
        allOtherButtonsPanel.add(calculator.buttonSubtract);
        allOtherButtonsPanel.add(calculator.buttonEquals);

        setComponent(buttonNot, 5, 0, 1, 1, otherButtonLayout);
        setComponent(buttonAnd, 5, 1, 1, 1, otherButtonLayout);
        setComponent(buttonF, 5, 2, 1, 1, otherButtonLayout);
        setComponent(calculator.button0, 5, 3, 2, 1, otherButtonLayout);
        setComponent(calculator.buttonDot, 5, 5, 1, 1, otherButtonLayout);
        setComponent(calculator.buttonAdd, 5, 6, 1, 2, otherButtonLayout);

        allOtherButtonsPanel.add(buttonNot);
        allOtherButtonsPanel.add(buttonAnd);
        allOtherButtonsPanel.add(buttonF);
        allOtherButtonsPanel.add(calculator.button0);
        allOtherButtonsPanel.add(calculator.buttonDot);
        allOtherButtonsPanel.add(calculator.buttonAdd);
        // add allOtherButtonsPanel to gui
        constraints.insets = new Insets(0, 0, 5, 0);
        addComponent(allOtherButtonsPanel, 5, 1, 6, 8, programmerLayout);
        LOGGER.info("Finished addComponentsToProgrammerPanel_v3");
    }
    public void setButtonGroup2Mode() {
        calculator.isButtonBinSet = false;
        calculator.isButtonOctSet = false;
        calculator.isButtonDecSet = false;
        calculator.isButtonHexSet = false;
        if (buttonBin.isSelected())
            calculator.isButtonBinSet = true;
        else if (buttonOct.isSelected())
            calculator.isButtonOctSet = true;
        else if (buttonDec.isSelected())
            calculator.isButtonDecSet = true;
        else if (buttonHex.isSelected())
            calculator.isButtonHexSet = true;
    }

    // method to set constraints on
    public void addComponent(Component c, int row, int column, int gwidth, int gheight) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = gwidth;
        constraints.gridheight = gheight;
        programmerLayout.setConstraints(c, constraints); // set constraints
        add(c); // add component
    }
    public void addComponent(Component c, int row, int column, int gwidth, int gheight, GridBagLayout layout) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = gwidth;
        constraints.gridheight = gheight;
        layout.setConstraints(c, constraints); // set constraints
        add(c); // add component
    }
    public void setComponent(Component c, int row, int column, int gwidth, int gheight, GridBagLayout layout) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = gwidth;
        constraints.gridheight = gheight;
        layout.setConstraints(c, constraints); // set constraints
        add(c); // add component
    }

    public class BinaryButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonBin.isSelected()) {
                calculator.button2.setEnabled(false);
                calculator.button3.setEnabled(false);
                calculator.button4.setEnabled(false);
                calculator.button5.setEnabled(false);
                calculator.button6.setEnabled(false);
                calculator.button7.setEnabled(false);
                calculator.button8.setEnabled(false);
                calculator.button9.setEnabled(false);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                buttonE.setEnabled(false);
                buttonF.setEnabled(false);
                // lot more to disable
            }

            setButtonGroup2Mode();
            // only convert number if textArea has text
            if (!calculator.getTextArea().getText().equals(""))
                convertToBinary();
        }
    }
    public class ButtonOctHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonOct.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(false);
                calculator.button9.setEnabled(false);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                buttonE.setEnabled(false);
                buttonF.setEnabled(false);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToOctal();
        }
    }
    public class ButtonDecHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonDec.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(true);
                calculator.button9.setEnabled(true);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                buttonE.setEnabled(false);
                buttonF.setEnabled(false);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToDecimal();
        }
    }
    public class ButtonHexidecimalHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonHex.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(true);
                calculator.button9.setEnabled(true);
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                buttonE.setEnabled(true);
                buttonF.setEnabled(true);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToHexadecimal();
        }
    }
    public class ButtonByteHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 8) {
                if (calculator.getTextArea().getText().equals(""))
                    calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(8,16));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class ButtonWordHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 16) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,16));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class ButtonDWordHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 32) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,32));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class ButtonQWordHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 64) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,64));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class OctalButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonOct.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(false);
                calculator.button9.setEnabled(false);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                buttonE.setEnabled(false);
                buttonF.setEnabled(false);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToOctal();
        }
    }
    public class DecimalButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonDec.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(true);
                calculator.button9.setEnabled(true);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                buttonE.setEnabled(false);
                buttonF.setEnabled(false);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToDecimal();
        }
    }
    public class HexidecimalButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonHex.isSelected()){
                calculator.button2.setEnabled(true);
                calculator.button3.setEnabled(true);
                calculator.button4.setEnabled(true);
                calculator.button5.setEnabled(true);
                calculator.button6.setEnabled(true);
                calculator.button7.setEnabled(true);
                calculator.button8.setEnabled(true);
                calculator.button9.setEnabled(true);
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                buttonE.setEnabled(true);
                buttonF.setEnabled(true);
            }
            if (!calculator.getTextArea().getText().equals(""))
                convertToHexadecimal();
        }
    }
    public class ByteButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 8) {
                if (calculator.getTextArea().getText().equals(""))
                    calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(8,16));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class WordButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 16) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,16));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class DWordButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 32) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,32));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }
    public class QWordButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculator.getTextArea().getText().length() > 64) {
                calculator.textarea = new StringBuffer().append(calculator.getTextArea().getText());
                calculator.textarea = new StringBuffer().append(calculator.textarea.substring(0,64));
                calculator.getTextArea().setText(calculator.textarea.toString());
            }
        }
    }

    public void performButtonNotActions() {
        LOGGER.info("performing not operation...");

        // convert the textarea
        calculator.textarea = new StringBuffer(calculator.getTextArea().getText().replaceAll("\n", ""));
        LOGGER.debug("before not operation: " + calculator.textarea.toString());
        StringBuffer newBuffer = new StringBuffer();
        for (int i = 0; i < calculator.textarea.length(); i++) {
            String s = Character.toString(calculator.textarea.charAt(i));
            if (s.equals("0")) { newBuffer.append("1"); LOGGER.debug("appending a 1"); }
            else               { newBuffer.append("0"); LOGGER.debug("appending a 0"); }
        }
        LOGGER.debug("after not operation: " + newBuffer);
        calculator.textarea = new StringBuffer(newBuffer);
        calculator.getTextArea().setText("\n"+calculator.textarea.toString());
        LOGGER.info("not operation completed.");
    }
    public void performButtonOrActions(ActionEvent action) throws Calculator_v3Error {
        LOGGER.info("performOrLogic starts here");
        LOGGER.info("button: " + action.getActionCommand());
        if (StringUtils.isEmpty(calculator.values[0]) && !StringUtils.isEmpty(calculator.values[1]))
        {
            String msg = "calculator.values[1] is set and not calculator.values[0]. This is not allowed.";
            throw new Calculator_v3Error(msg);
        }
        else if (!StringUtils.isEmpty(calculator.values[0]) && StringUtils.isEmpty(calculator.values[1]))
        {
            // hence do nothing. just show textarea.
            calculator.orButtonBool = true;
            calculator.firstNumBool = false;
            calculator.valuesPosition++;
            calculator.confirm("Show values[0]");
        }
        else if (StringUtils.isEmpty(calculator.values[0]) && StringUtils.isEmpty(calculator.values[1]))
        {
            calculator.orButtonBool = false;
            calculator.firstNumBool = true;
            calculator.confirm("Do nothing");
        }
        else if (!StringUtils.isEmpty(calculator.values[0]) && !StringUtils.isEmpty(calculator.values[1]))
        {
//            calculator.convertAllValuesToDecimal();
            calculator.orButtonBool = true;
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<calculator.values[0].length(); i++) {
                String letter = "0";
                if (String.valueOf(calculator.values[0].charAt(i)).equals("0") &&
                        String.valueOf(calculator.values[1].charAt(i)).equals("0") )
                { // if the characters at both values at the same position are the same and equal 0
                    letter = "0";
                    sb.append(letter);
                }
                else
                {
                    letter = "1";
                    sb.append(letter);
                }
                LOGGER.info(String.valueOf(calculator.values[0].charAt(i))+" + "+String.valueOf(calculator.values[1].charAt(i))+
                        " = "+ letter);
            }
            calculator.values[0] = String.valueOf(sb);
            calculator.getTextArea().setText(calculator.addNewLineCharacters(1)+calculator.values[0]);
            calculator.orButtonBool = false;
            calculator.valuesPosition = 0;
//            calculator.convertAllValuesToBinary();
        }
    }
    public void convertFromTypeToType(String type1, String type2) {
        if (type1.equals("Binary") && type2.equals("Decimal")) {
            // converting both numbers in values if applicable
            if (!calculator.values[0].equals("")) {
                try {
                    double result = 0.0;
                    double num1 = 0.0;
                    double num2 = 0.0;
                    for(int i=0, k=calculator.values[0].length()-1; i<calculator.values[0].length(); i++, k--) {
                        String c = Character.toString(calculator.values[0].charAt(i));
                        num1 = Double.valueOf(c);
                        num2 = Math.pow(2,k);
                        result = (num1 * num2) + result;
                    }
                    calculator.values[0] = calculator.clearZeroesAtEnd(Double.toString(result)).toString();
                } catch (NumberFormatException nfe) {

                }
            }
            if (!calculator.values[1].equals("")) {
                try {
                    double result = 0.0;
                    double num1 = 0.0;
                    double num2 = 0.0;
                    for(int i=0, k=calculator.values[1].length()-1; i<calculator.values[1].length(); i++, k--) {
                        String c = Character.toString(calculator.values[1].charAt(i));
                        num1 = Double.valueOf(c);
                        num2 = Math.pow(2,k);
                        result = (num1 * num2) + result;
                    }
                    calculator.values[1] = calculator.clearZeroesAtEnd(Double.toString(result)).toString();
                } catch (NumberFormatException nfe) {

                }
            }
        }
    }
    public String convertToBinary(String valueToConvert) {
        conversion = new StringBuffer();
        int number = 0;
        try {
            number = Integer.parseInt(valueToConvert);
            LOGGER.debug("number: " + number);
            int i = 0;
            while(i < Integer.parseInt(valueToConvert)) {
                if (number % 2 == 0) {
                    conversion.append("0");
                } else {
                    conversion.append("1");
                }
                if (number % 2 == 0 && number / 2 == 0) {
                    // 0r0
                    for(int k = i; k<calculator.getBytes(); k++) {
                        conversion.append("0");
                    }
                    break;
                } else if (number / 2 == 0 && number % 2 == 1) {
                    // 0r1
                    for(int k = i+1; k<calculator.getBytes(); k++) {
                        conversion.append("0");
                    } break;
                }
                i++;
                number /= 2;
            }
        } catch (NumberFormatException nfe) { LOGGER.error(nfe.getMessage()); }
        conversion = conversion.reverse(); // reversing so it will display properly
        String strToReturn = conversion.toString();
        LOGGER.debug("convertToBinary("+valueToConvert+") = "+ strToReturn);
        conversion = new StringBuffer();
        return strToReturn;
    }
    public void convertToBinary() {
        LOGGER.info("convertToBinary started");
        LOGGER.info("textarea: " + calculator.textarea);
        int i;
        int bytes = calculator.getBytes();
        if (calculator.isButtonOctSet)
        {
            // logic for Octal to Binary
        }
        else if (calculator.isButtonDecSet)
        {
            // logic for Decimal to Binary
        }
        else if (calculator.isButtonBinSet)
        {
            if (calculator.getTextArea().getText().equals("")) { return; }
            calculator.textarea = new StringBuffer().append(calculator.getTextAreaWithoutNewLineCharacters().strip());

            String operator = String.valueOf(calculator.textarea.charAt(0));
            boolean operatorIncluded = false;
            switch(operator) {
                case "+" : operatorIncluded = true; LOGGER.debug("operator: " + operator); calculator.addBool = true; break;
                case "-" : operatorIncluded = true; LOGGER.debug("operator: " + operator); calculator.subBool = true; break;
                case "*" : operatorIncluded = true; LOGGER.debug("operator: " + operator); calculator.mulBool = true; break;
                case "/" : operatorIncluded = true; LOGGER.debug("operator: " + operator); calculator.divBool = true; break;
                default : LOGGER.error("unknown string means there is another else if case: " + operator);
            }
            if (operatorIncluded) calculator.textarea = new StringBuffer().append(String.valueOf(calculator.textarea).substring(2,calculator.textarea.length()));

            int number;
            try {
                number = Integer.parseInt(calculator.textarea.toString());
                i = 0;
                while(i < calculator.getBytes()) {
                    if (number % 2 == 0) {
                        conversion.append("0");
                    }
                    else {
                        conversion.append("1");
                    }
                    if (number % 2 == 0 && number / 2 == 0) {
                        // 0r0
                        for(int k = i; k<calculator.getBytes(); k++) {
                            conversion.append("0");
                        }
                        break;
                    } else if (number / 2 == 0 && number % 2 == 1) {
                        // 0r1
                        for(int k = i+1; k<calculator.getBytes(); k++) {
                            conversion.append("0");
                        } break;
                    }
                    i++;
                    number /= 2;
                }
            } catch (NumberFormatException nfe) {
                if (StringUtils.isBlank(calculator.textarea)) {
                    number = 0;
                    calculator.textarea = new StringBuffer();
                    conversion = new StringBuffer("00000000");
                }
            }
            conversion = conversion.reverse(); // reversing so it will display properly
            LOGGER.info("converted number is: " + conversion);
            LOGGER.info("textarea: " + calculator.textarea);

            if (operatorIncluded)
            {
                calculator.values[calculator.valuesPosition-1] = String.valueOf(conversion);
                calculator.getTextArea().setText(calculator.addNewLineCharacters(1)+operator+" "+conversion);
                calculator.updateTextareaFromTextArea();
            }
            else
            {
                calculator.values[calculator.valuesPosition] = String.valueOf(conversion);
                calculator.getTextArea().setText(calculator.addNewLineCharacters(1)+conversion);
                calculator.updateTextareaFromTextArea();
            }

        }
        else if (calculator.isButtonHexSet == true) {
            // logic for Hexadecimal to Binary
        }
        LOGGER.info("convertToBinary finished");
    }
    public void convertToOctal() {
        if (calculator.isButtonBinSet == true) {
            // logic for Binary to Octal
        } else if (calculator.isButtonDecSet == true) {
            // logic for Decimal to Octal
        } else if (calculator.isButtonHexSet == true) {
            // logic for Hexadecimal to Octal
        }
    }
    public void convertToDecimal() {
        if (calculator.isButtonBinSet == true) {
            // logic for Binary to Decimal
        } else if (calculator.isButtonOctSet == true) {
            // logic for Octal to Decimal
        } else if (calculator.isButtonHexSet == true) {
            // logic for Hexadecimal to Decimal
        }
    }
    public void convertToHexadecimal() {
        if (calculator.isButtonBinSet == true) {
            // logic for Binary to Hexadecimal
        } else if (calculator.isButtonOctSet == true) {
            // logic for Octal to Hexadecimal
        } else if (calculator.isButtonDecSet == true) {
            // logic for Decimal to Hexadecimal
        }
    }
    public void convertTextArea() {
        calculator.textarea = new StringBuffer().append(calculator.getTextAreaWithoutNewLineCharacters().strip());
        if (calculator.getCalcType() == CalcType_v3.BASIC) {
            // decimal to binary
            LOGGER.info("Going from decimal to binary...");
            if (calculator.isDecimal(calculator.textarea.toString())) {
                // add appropriate logic
            }
            else
            {
                convertToBinary();
            }
        }
        else {
            LOGGER.info("Current CalcType is: " + calculator.getCalcType());
        }
    }
    /**
     * This method handles the logic when we switch from any type of calculator
     * to the Programmer type
     *
     * TODO: Implement this method
     */
    public void performCalculatorTypeSwitchOperations() {
        LOGGER.info("Starting to performCalculatorTypeSwitchOperations");
        // possible conversion of the value in the textarea from
        // whatever mode it was in before to binary
        convertTextArea();
        // set CalcType now
        calculator.setCalcType(CalcType_v3.PROGRAMMER);
        // setting up all the buttons
        setButtons2To9(false);
        LOGGER.info("Finished performCalculatorTypeSwitchOperations\n");
        calculator.confirm("");
    }
    public void setButtons2To9(boolean isEnabled) {
        calculator.button2.setEnabled(isEnabled);
        calculator.button3.setEnabled(isEnabled);
        calculator.button4.setEnabled(isEnabled);
        calculator.button5.setEnabled(isEnabled);
        calculator.button6.setEnabled(isEnabled);
        calculator.button7.setEnabled(isEnabled);
        calculator.button8.setEnabled(isEnabled);
        calculator.button9.setEnabled(isEnabled);
    }


    /************* All Getters and Setters ******************/

    public StringBuffer getConversion() { return conversion; }
    public Calculator_v3 getCalculator() { return calculator; }
    private void setCalculator(StandardCalculator_v3 calculator) { this.calculator = calculator; }
}
