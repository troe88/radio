import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainForm {
	public boolean OnOff = false;
	private JFrame frame;
	static JSlider slider = new JSlider();
	JButton btn_scan;
	JButton btn_reset;
	JButton btn_onOff;
	Play play;
	Scan scan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainForm window = new mainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainForm() {
		initialize();
	}
	
	private synchronized void startReset() {
		scan.setReset();
		scan.start();
	}
	
	private synchronized void startScan() {
		scan.setScan();
		scan.start();
	}
	
	private synchronized void finish() {
		play.finish();
		scan.finish();
	}

	private synchronized void startPlay() {
		play.start();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Radio is OFF");
		frame.setBounds(2000, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn_onOff = new JButton("ON");
		btn_onOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnOff = !OnOff;
				if (OnOff == false) {
					System.out.println("ON");
					btn_onOff.setText("ON");
					frame.setTitle("Radio is OFF");
					btn_scan.setEnabled(false);
					btn_reset.setEnabled(false);
					finish();
				} else {
					System.out.println("OFF");
					btn_onOff.setText("OFF");
					frame.setTitle("Radio is ON");
					btn_scan.setEnabled(true);
					btn_reset.setEnabled(true);
					play = new Play();
					startPlay();
					slider.setValue(slider.getMaximum());
				}
			}


		});
		frame.getContentPane().add(btn_onOff, BorderLayout.WEST);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinimum(88);
		slider.setMaximum(108);
		slider.setEnabled(false);
		slider.setValue(slider.getMaximum());
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				play.changeRadioStation(slider.getValue());
			}
		});
		frame.getContentPane().add(slider, BorderLayout.NORTH);
		btn_scan = new JButton("SCAN");
		btn_scan.setEnabled(false);
		btn_scan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("SCAN");
				scan = new Scan();
				startScan();

			}

			
		});
		frame.getContentPane().add(btn_scan, BorderLayout.CENTER);
		btn_reset = new JButton("RESET");
		btn_reset.setEnabled(false);
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("RESET");
				scan = new Scan();
				startReset();
			}

			
		});
		frame.getContentPane().add(btn_reset, BorderLayout.EAST);
		
	}
}