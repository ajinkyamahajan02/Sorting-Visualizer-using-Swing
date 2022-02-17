package Ajinkya;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {

	private final int MAX_SPEED = 1000;
	private final int MIN_SPEED = 1;
	private final int MAX_SIZE = 50;
	private final int MIN_SIZE = 1;
	private final int DEFAULT_SPEED = 20;
	private final int DEFAULT_SIZE = 40;



	private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Merge"};

	private int sizeModifier;

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel buttonWrapper;
	private JPanel stringprinter;
	private JPanel[] squarePanels;
	private JButton start;
	private JComboBox<String> selection;
	private JSlider speed;
	private JSlider size;
	private JLabel speedVal;
	private JLabel sizeVal;
	private JLabel initarr;
	private GridBagConstraints c;


	public VisualizerFrame(){
		super("SDP Project");

		start = new JButton("Start");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		wrapper = new JPanel();
		stringprinter = new JPanel();
		selection = new JComboBox<String>();
		speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
		size = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);

		speedVal = new JLabel("Speed: 20 ms");
		sizeVal = new JLabel("Size: 40 values");
		initarr = new JLabel("", SwingConstants.CENTER);
		c = new GridBagConstraints();

		for(String s : Sorts)
			selection.addItem(s);

		arrayWrapper.setLayout(new GridBagLayout());
		wrapper.setLayout(new BorderLayout());

		c.insets = new Insets(0,1,0,1);
		c.anchor = GridBagConstraints.SOUTH;

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingVisualizer.startSort((String) selection.getSelectedItem());

			}
		});


		speed.setMinorTickSpacing(100);
		speed.setMajorTickSpacing(250);
		speed.setPaintTicks(true);

		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
				validate();
				SortingVisualizer.sleep = speed.getValue();
			}
		});

		size.setMinorTickSpacing(10);
		size.setMajorTickSpacing(25);
		size.setPaintTicks(true);

		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
				validate();
				SortingVisualizer.sortDataCount = size.getValue();
			}
		});


		buttonWrapper.add(speedVal);
		buttonWrapper.add(speed);
		buttonWrapper.add(sizeVal);
		buttonWrapper.add(size);
		buttonWrapper.add(start);
		buttonWrapper.add(selection);

		wrapper.add(buttonWrapper, BorderLayout.SOUTH);
		stringprinter.add(initarr);
		wrapper.add(stringprinter, BorderLayout.NORTH);
		wrapper.add(arrayWrapper);

		add(wrapper);

		setExtendedState(JFrame.MAXIMIZED_BOTH );

		addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e)
			{
				sizeModifier = (int) ((getHeight()*0.7)/(squarePanels.length));
			}

			@Override
			public void componentMoved(ComponentEvent e) 
			{
				// Do nothing
			}

			@Override
			public void componentShown(ComponentEvent e) 
			{
				// Do nothing
			}

			@Override
			public void componentHidden(ComponentEvent e) 
			{
				// Do nothing
			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void preDrawArray(Integer[] squares){

		squarePanels = new JPanel[SortingVisualizer.sortDataCount];
		arrayWrapper.removeAll();
		sizeModifier =  (int) (((getHeight()-200)*0.8)/(squarePanels.length));

		for(int i = 0; i<SortingVisualizer.sortDataCount; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].add(new JLabel(""+squares[i])).setForeground(Color.WHITE);
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
			squarePanels[i].setBackground(Color.blue);
			arrayWrapper.add(squarePanels[i], c);
		}

		repaint();
		validate();
	}

	public void reDrawArray(Integer[] x){
		reDrawArray(x, -1);
	}

	public void reDrawArray(Integer[] x, int y){
		reDrawArray(x, y, -1);
	}

	public void reDrawArray(Integer[] x, int y, int z){
		reDrawArray(x, y, z, -1);
	}

	// reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
	public void reDrawArray(Integer[] squares, int working, int comparing, int reading)
	{
		arrayWrapper.removeAll();
		for(int i = 0; i<squarePanels.length; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
			if (i == working){
				squarePanels[i].setBackground(Color.green);
			}else if(i == comparing){
				squarePanels[i].setBackground(Color.red);
			}else if(i == reading){
				squarePanels[i].setBackground(Color.yellow);
			}else{
				squarePanels[i].setBackground(Color.blue);
			}
			squarePanels[i].add(new JLabel(""+squares[i])).setForeground(Color.WHITE);
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();

	}

	public void getarrSize(Integer[] names)
	{

			String tempstr = "" + names[0] + " ";
			for (int i = 1; i < names.length; i++) {

				tempstr = tempstr + names[i] + " ";
			}

			initarr.setText("Initial Array: " + tempstr);
			initarr.setFont(new Font("Arial", Font.PLAIN, 16));
			validate();

	}

	public void getsrtSize(Integer[] srtnames)
	{
			String tempstr2 = "" + srtnames[0] + " ";
			for (int i = 1; i < srtnames.length; i++) {

				tempstr2 = tempstr2 + srtnames[i] + " ";
			}
			String str = initarr.getText();
			initarr.setText("<html>" + str + "<br/> Sorted Array: " + tempstr2 + "</html>");
			initarr.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 16));
			validate();
	}


}
