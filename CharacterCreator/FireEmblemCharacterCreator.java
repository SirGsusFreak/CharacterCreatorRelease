package CharacterCreator;
/**	
 *  FireEmblemCharacterCreator created by TheFlyingMinotaur
 *  Updated by Baconmaster120
 *  Additional art resources provided by Iscaneus
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JLabel;
import java.awt.image.WritableRaster;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import CharacterCreator.ImagePanel;


public class FireEmblemCharacterCreator extends JFrame implements ChangeListener, ItemListener, ActionListener {
	
	private static final long serialVersionUID = -7435578396712824308L;
	BufferedImage portrait;
	BufferedImage token;
	BufferedImage hair;
	BufferedImage hairb;
	BufferedImage face;
	BufferedImage accessory;
	BufferedImage armor;
	BufferedImage blankPortrait;
	BufferedImage blankToken;
	BufferedImage importedToken;
	
	ImagePanel portraitPanel;
	ImagePanel tokenPanel;
	
	//This could probably be done WAY more efficiently
	JLabel hairRect = new JLabel();
	JLabel hairRectB = new JLabel();
	JLabel hairRectD = new JLabel();
	JLabel eyeRect = new JLabel();
	JLabel eyeRectB = new JLabel();
	JLabel eyeRectD = new JLabel();
	JLabel skinRect = new JLabel();
	JLabel skinRectB = new JLabel();
	JLabel skinRectD = new JLabel();
	JLabel metalRect = new JLabel();
	JLabel metalRectB = new JLabel();
	JLabel metalRectD = new JLabel();
	JLabel trimRect = new JLabel();
	JLabel trimRectB = new JLabel();
	JLabel trimRectD = new JLabel();
	JLabel clothRect = new JLabel();
	JLabel clothRectB = new JLabel();
	JLabel clothRectD = new JLabel();
	JLabel leatherRect = new JLabel();
	JLabel leatherRectB = new JLabel();
	JLabel leatherRectD = new JLabel();
	JLabel accessoryRect = new JLabel();
	JLabel accessoryRectB = new JLabel();
	JLabel accessoryRectD = new JLabel();
	
	JTextField exportFileName = new JTextField();
	
	
	ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	ArrayList<JComboBox<String>> boxes = new ArrayList<JComboBox<String>>();
	
	Color skinColor = new Color(248,208,152,255);
	Color hairColor = new Color(224,216,64,255);
	Color eyeColor = new Color(64,50,25,255);
	Color metalColor= new Color(100,100,100,255);
	Color trimColor= new Color(247,173,82,255);
	Color clothColor= new Color(82,82,115,255);
	Color leatherColor= new Color(148,100,66,255);
	Color accessoryColor = new Color(0,0,0,255);
	Color outlineColor = new Color(0,0,0,255);
	Color blankColor = new Color(0,0,0,0);
	Color startCCColor = new Color(0,0,0,0);
	Color newCCColor = new Color(0,0,0,0);
	
	int hairXOffsetVal=0;
	int hairYOffsetVal=0;
	int faceXOffsetVal=0;
	int faceYOffsetVal=0;
	int armorXOffsetVal=0;
	int armorYOffsetVal=0;
	int accessoryXOffsetVal=0;
	int accessoryYOffsetVal=0;
	
	
	
	//SystemColorChooserPanel newChooser = new SystemColorChooserPanel();
	//chooser.addChoosePanel(newChooser);
	
	//Button strings
	private static final String BTNEXPORT = "Export";
	private static final String BTNHAIRCOLOR = "Hair Color";
	private static final String BTNEYECOLOR = "Eye/Beard Color";
	private static final String BTNSKINCOLOR = "Skin Color";
	private static final String BTNMETALCOLOR = "Metal Color";
	private static final String BTNTRIMCOLOR = "Trim Color";
	private static final String BTNCLOTHCOLOR = "Cloth Color";
	private static final String BTNLEATHERCOLOR = "Leather Color";
	private static final String BTNACCESSORYCOLOR = "Accessory Color";
	private static final String BTNRANDOMHAIR = "Random Hair";
	private static final String BTNRANDOMFACE = "Random Face";
	private static final String BTNRANDOMARMOR = "Random Armor";
	private static final String BTNRANDOMTOKEN = "Random Token";
	private static final String BTNRANDOMACCESSORY = "Random Accessory";
	private static final String BTNRANDOMPORTRAIT = "Random Portrait";
	
	File folder;
	File[] listOfFiles;
	ArrayList<String> hairs = new ArrayList<String>();
	ArrayList<String> faces = new ArrayList<String>();
	ArrayList<String> armors = new ArrayList<String>();
	ArrayList<String> accessories = new ArrayList<String>();
	ArrayList<String> tokens = new ArrayList<String>();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireEmblemCharacterCreator frame = new FireEmblemCharacterCreator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedEncodingException 
	 */
	public FireEmblemCharacterCreator() throws UnsupportedEncodingException {
		

		
		String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String path = URLDecoder.decode(rawPath, "UTF-8");
		path = path.substring(0, path.lastIndexOf("/") + 1);
		path = path.replaceAll("%20", " ");
		
		folder = new File(path + "resources");
		listOfFiles  = folder.listFiles();
		
		accessory = null;
		try {
		    accessory = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
		}
		
		hair = null;
		try {
		    hair = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
		}
		
		hairb = null;
		try {
		    hairb = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
		}
		
		face = null;
		try {
		    face = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
		}
		
		armor = null;
		try {
		    armor = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
		}
		portrait = null;
		try {
		    portrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
		} catch (IOException ex) {
		}
		//System.out.println((portrait == null));
		
		token = null;
		try {
		    token = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
		}
		
		blankPortrait = null;
		try {
		    blankPortrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
		} catch (IOException ex) {
		}
		//System.out.println((portrait == null));
		
		blankToken = null;
		try {
		    blankToken = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
		}
		
		importedToken = null;
		try {
		    importedToken = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
		}
		
		setFont(new Font("Calibri", Font.BOLD, 12));
		setTitle("Fire Emblem Character Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 550); //was xx932x
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		
		//----------COLOR CHOOSER BUTTONS-----------------------------
		JButton hairCCBtn = new JButton(BTNHAIRCOLOR);
		hairCCBtn.setBounds(380,30,130,50);
		hairCCBtn.addActionListener(this);
		contentPane.add(hairCCBtn);
		
		hairRectB.setBounds(380, 80, 40, 20);
		hairRectB.setOpaque(true);
		hairRectB.setBackground(hairColor.brighter());
		contentPane.add(hairRectB);
		hairRect.setBounds(420, 80, 50, 20);
		hairRect.setOpaque(true);
		hairRect.setBackground(hairColor);
		contentPane.add(hairRect);
		hairRectD.setBounds(470, 80, 40, 20);
		hairRectD.setOpaque(true);
		hairRectD.setBackground(hairColor.darker());
		contentPane.add(hairRectD);
		
		JButton eyeCCBtn = new JButton(BTNEYECOLOR);
		eyeCCBtn.setBounds(510,30,130,50);
		eyeCCBtn.addActionListener(this);
		contentPane.add(eyeCCBtn);
		//JLabel eyeRect = new JLabel();
		eyeRectB.setBounds(510, 80, 40, 20);
		eyeRectB.setOpaque(true);
		eyeRectB.setBackground(eyeColor.brighter());
		contentPane.add(eyeRectB);
		eyeRect.setBounds(550, 80, 50, 20);
		eyeRect.setOpaque(true);
		eyeRect.setBackground(eyeColor);
		contentPane.add(eyeRect);
		eyeRectD.setBounds(600, 80, 40, 20);
		eyeRectD.setOpaque(true);
		eyeRectD.setBackground(eyeColor.darker());
		contentPane.add(eyeRectD);
		
		JButton skinCCBtn = new JButton(BTNSKINCOLOR);
		skinCCBtn.setBounds(640,30,130,50);
		skinCCBtn.addActionListener(this);
		contentPane.add(skinCCBtn);
		//JLabel skinRect = new JLabel();
		skinRectB.setBounds(640, 80, 40, 20);
		skinRectB.setOpaque(true);
		skinRectB.setBackground(skinColor.brighter());
		contentPane.add(skinRectB);
		skinRect.setBounds(680, 80, 50, 20);
		skinRect.setOpaque(true);
		skinRect.setBackground(skinColor);
		contentPane.add(skinRect);
		skinRectD.setBounds(730, 80, 40, 20);
		skinRectD.setOpaque(true);
		skinRectD.setBackground(skinColor.darker());
		contentPane.add(skinRectD);
		
		JButton metalCCBtn = new JButton(BTNMETALCOLOR);
		metalCCBtn.setBounds(380,100,130,50);
		metalCCBtn.addActionListener(this);
		contentPane.add(metalCCBtn);
		//JLabel metalRect = new JLabel();
		metalRectB.setBounds(380, 150, 40, 20);
		metalRectB.setOpaque(true);
		metalRectB.setBackground(metalColor.brighter());
		contentPane.add(metalRectB);
		metalRect.setBounds(420, 150, 50, 20);
		metalRect.setOpaque(true);
		metalRect.setBackground(metalColor);
		contentPane.add(metalRect);
		metalRectD.setBounds(470, 150, 40, 20);
		metalRectD.setOpaque(true);
		metalRectD.setBackground(metalColor.darker());
		contentPane.add(metalRectD);
		
		JButton trimCCBtn = new JButton(BTNTRIMCOLOR);
		trimCCBtn.setBounds(510,100,130,50);
		trimCCBtn.addActionListener(this);
		contentPane.add(trimCCBtn);
		//JLabel trimRect = new JLabel();
		trimRectB.setBounds(510, 150, 40, 20);
		trimRectB.setOpaque(true);
		trimRectB.setBackground(trimColor.brighter());
		contentPane.add(trimRectB);
		trimRect.setBounds(550, 150, 50, 20);
		trimRect.setOpaque(true);
		trimRect.setBackground(trimColor);
		contentPane.add(trimRect);
		trimRectD.setBounds(600, 150, 40, 20);
		trimRectD.setOpaque(true);
		trimRectD.setBackground(trimColor.darker());
		contentPane.add(trimRectD);
		
		JButton clothCCBtn = new JButton(BTNCLOTHCOLOR);
		clothCCBtn.setBounds(640,100,130,50);
		clothCCBtn.addActionListener(this);
		contentPane.add(clothCCBtn);
		//JLabel clothRect = new JLabel();
		clothRectB.setBounds(640, 150, 40, 20);
		clothRectB.setOpaque(true);
		clothRectB.setBackground(clothColor.brighter());
		contentPane.add(clothRectB);
		clothRect.setBounds(680, 150, 50, 20);
		clothRect.setOpaque(true);
		clothRect.setBackground(clothColor);
		contentPane.add(clothRect);
		clothRectD.setBounds(730, 150, 40, 20);
		clothRectD.setOpaque(true);
		clothRectD.setBackground(clothColor.darker());
		contentPane.add(clothRectD);
		
		JButton leatherCCBtn = new JButton(BTNLEATHERCOLOR);
		leatherCCBtn.setBounds(770,100,130,50);
		leatherCCBtn.addActionListener(this);
		contentPane.add(leatherCCBtn);
		//JLabel leatherRect = new JLabel();
		leatherRectB.setBounds(770, 150, 40, 20);
		leatherRectB.setOpaque(true);
		leatherRectB.setBackground(leatherColor.brighter());
		contentPane.add(leatherRectB);
		leatherRect.setBounds(810, 150, 50, 20);
		leatherRect.setOpaque(true);
		leatherRect.setBackground(leatherColor);
		contentPane.add(leatherRect);
		leatherRectD.setBounds(860, 150, 40, 20);
		leatherRectD.setOpaque(true);
		leatherRectD.setBackground(leatherColor.darker());
		contentPane.add(leatherRectD);
		
		JButton accessoryCCBtn = new JButton(BTNACCESSORYCOLOR);
		accessoryCCBtn.setBounds(770,30,130,50);
		accessoryCCBtn.addActionListener(this);
		contentPane.add(accessoryCCBtn);
		//JLabel leatherRect = new JLabel();
		accessoryRectB.setBounds(770, 80, 40, 20);
		accessoryRectB.setOpaque(true);
		accessoryRectB.setBackground(accessoryColor.brighter());
		contentPane.add(accessoryRectB);
		accessoryRect.setBounds(810, 80, 50, 20);
		accessoryRect.setOpaque(true);
		accessoryRect.setBackground(accessoryColor);
		contentPane.add(accessoryRect);
		accessoryRectD.setBounds(860, 80, 40, 20);
		accessoryRectD.setOpaque(true);
		accessoryRectD.setBackground(accessoryColor.darker());
		contentPane.add(accessoryRectD);
		
		//JColorChooser hairColorChooser = new JColorChooser();
		//hairColorChooser.setSize(500,500);
		//hairColorChooser.setVisible(true);
		//hairColorChooser.setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		//PORTRAIT BOX
		portraitPanel = new ImagePanel(path + "resources/BlankPortrait.png");
		portraitPanel.setBounds(22, 10, 192, 192);
		contentPane.add(portraitPanel);
		
		//TOKEN BOX
		tokenPanel = new ImagePanel(path + "resources/BlankTok.png");
		tokenPanel.setBounds(224, 0, 128, 128);
		contentPane.add(tokenPanel);
		
		
		hairs.add("Empty.png");
		faces.add("Empty.png");
		armors.add("Empty.png");
		accessories.add("Empty.png");
		tokens.add("Emptytok.png");
		
		System.out.println(listOfFiles.length);
		for (int i = 0; i< listOfFiles.length; i++){
			String filename = listOfFiles[i].getName();
			if (filename.contains("Hair.png")){
				hairs.add(filename);
			}
			else if(filename.contains("Face.png")){
				faces.add(filename);
			}
			else if(filename.contains("Armor.png")){
				armors.add(filename);
			}
			else if(filename.contains("Acc.png")) {
				accessories.add(filename);
			}
			else if(filename.contains("Token.png")){
				tokens.add(filename);
			}
		}
		
		//ComboBox labels
		JLabel lblHair = new JLabel("Hair");
		lblHair.setFont(new Font("Calibri", Font.BOLD, 13));
		lblHair.setBounds(40, 202, 131, 21);
		contentPane.add(lblHair);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setFont(new Font("Calibri", Font.BOLD, 13));
		lblFace.setBounds(270, 202, 131, 21);
		contentPane.add(lblFace);
		
		JLabel lblArmor = new JLabel("Armor");
		lblArmor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblArmor.setBounds(500, 202, 131, 21);
		contentPane.add(lblArmor);
		
		JLabel lblAccessory = new JLabel("Accessory");
		lblAccessory.setFont(new Font("Calibri", Font.BOLD, 13));
		lblAccessory.setBounds(730, 202, 131, 21);
		contentPane.add(lblAccessory);
		
		//DROPBOXES
		CustomComboBoxRenderer renderer = new CustomComboBoxRenderer();

		JComboBox<String> comboBox_hairs = new JComboBox<String>(hairs.toArray(new String[hairs.size()]));
		comboBox_hairs.setBounds(40, 220, 220, 100);
		comboBox_hairs.setMaximumRowCount(9);
		comboBox_hairs.setRenderer(renderer);
		contentPane.add(comboBox_hairs);

		JButton btnRandomHair = new JButton(BTNRANDOMHAIR);
		btnRandomHair.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomHair.setBounds(40, 330, 202, 25);
		contentPane.add(btnRandomHair);
		
		JComboBox<String> comboBox_faces = new JComboBox<String>(faces.toArray(new String[faces.size()]));
		comboBox_faces.setBounds(270, 220, 220, 100);
		comboBox_faces.setMaximumRowCount(9);
		comboBox_faces.setRenderer(renderer);
		contentPane.add(comboBox_faces);

		JButton btnRandomFace = new JButton(BTNRANDOMFACE);
		btnRandomFace.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomFace.setBounds(270, 330, 202, 25);
		contentPane.add(btnRandomFace);
		
		JComboBox<String> comboBox_armors = new JComboBox<String>(armors.toArray(new String[armors.size()]));
		comboBox_armors.setBounds(500, 220, 220, 100);
		comboBox_armors.setMaximumRowCount(9);
		comboBox_armors.setRenderer(renderer);
		contentPane.add(comboBox_armors);

		JButton btnRandomArmor = new JButton(BTNRANDOMARMOR);
		btnRandomArmor.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomArmor.setBounds(500, 330, 202, 25);
		contentPane.add(btnRandomArmor);
		
		JComboBox<String> comboBox_accessories = new JComboBox<String>(accessories.toArray(new String[accessories.size()]));
		comboBox_accessories.setBounds(730, 220, 220, 100);
		comboBox_accessories.setMaximumRowCount(9);
		comboBox_accessories.setRenderer(renderer);
		contentPane.add(comboBox_accessories);

		JButton btnRandomAccessory = new JButton(BTNRANDOMACCESSORY);
		btnRandomAccessory.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomAccessory.setBounds(730, 330, 202, 25);
		contentPane.add(btnRandomAccessory);
		
		JComboBox<String> comboBox_tokens = new JComboBox<String>(tokens.toArray(new String[tokens.size()]));
		comboBox_tokens.setBounds(240, 142, 131, 20);
		comboBox_tokens.setMaximumRowCount(12);
		contentPane.add(comboBox_tokens);

		JButton btnRandomToken = new JButton(BTNRANDOMTOKEN);
		btnRandomToken.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomToken.setBounds(236, 170, 140, 25);
		contentPane.add(btnRandomToken);
		
		JLabel lblToken = new JLabel("Token");
		lblToken.setFont(new Font("Calibri", Font.BOLD, 13));
		lblToken.setBounds(234, 125, 46, 21);
		contentPane.add(lblToken);
		
		//---------XY OFFSET SLIDERS--------------------
		JLabel lblYOffset = new JLabel("Y Offset");
		lblYOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblYOffset.setBounds(40, 360, 46, 21);
		contentPane.add(lblYOffset);

		JSlider hairYOffset = new JSlider();
		hairYOffset.setValue(0);
		hairYOffset.setPaintTicks(true);
		hairYOffset.setPaintLabels(true);
		hairYOffset.setMajorTickSpacing(10);
		hairYOffset.setMaximum(20);
		hairYOffset.setMinimum(-20);
		hairYOffset.setBounds(30, 380, 220, 38);
		contentPane.add(hairYOffset);
		
		JLabel lblXOffset = new JLabel("X Offset");
		lblXOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblXOffset.setBounds(40, 430, 46, 21);
		contentPane.add(lblXOffset);
		
		JSlider hairXOffset = new JSlider();
		hairXOffset.setValue(0);
		hairXOffset.setPaintTicks(true);
		hairXOffset.setPaintLabels(true);
		hairXOffset.setMinimum(-20);
		hairXOffset.setMaximum(20);
		hairXOffset.setMajorTickSpacing(10);
		hairXOffset.setBounds(30, 450, 220, 38);
		contentPane.add(hairXOffset);

		JLabel label_2 = new JLabel("Y Offset");
		label_2.setFont(new Font("Calibri", Font.BOLD, 13));
		label_2.setBounds(270, 360, 46, 21);
		contentPane.add(label_2);
		
		JSlider faceYOffset = new JSlider();
		faceYOffset.setValue(0);
		faceYOffset.setPaintTicks(true);
		faceYOffset.setPaintLabels(true);
		faceYOffset.setMinimum(-20);
		faceYOffset.setMaximum(20);
		faceYOffset.setMajorTickSpacing(10);
		faceYOffset.setBounds(260, 380, 220, 38);
		contentPane.add(faceYOffset);
		
		JLabel label_5 = new JLabel("X Offset");
		label_5.setFont(new Font("Calibri", Font.BOLD, 13));
		label_5.setBounds(270, 430, 46, 21);
		contentPane.add(label_5);
		
		JSlider faceXOffset = new JSlider();
		faceXOffset.setValue(0);
		faceXOffset.setPaintTicks(true);
		faceXOffset.setPaintLabels(true);
		faceXOffset.setMinimum(-20);
		faceXOffset.setMaximum(20);
		faceXOffset.setMajorTickSpacing(10);
		faceXOffset.setBounds(260, 450, 220, 38);
		contentPane.add(faceXOffset);
		
		JLabel label_8 = new JLabel("Y Offset");
		label_8.setFont(new Font("Calibri", Font.BOLD, 13));
		label_8.setBounds(500, 360, 46, 21);
		contentPane.add(label_8);
		
		JSlider armorYOffset = new JSlider();
		armorYOffset.setValue(0);
		armorYOffset.setPaintTicks(true);
		armorYOffset.setPaintLabels(true);
		armorYOffset.setMinimum(-20);
		armorYOffset.setMaximum(20);
		armorYOffset.setMajorTickSpacing(10);
		armorYOffset.setBounds(490, 380, 220, 38);
		contentPane.add(armorYOffset);
		
		JLabel label_11 = new JLabel("X Offset");
		label_11.setFont(new Font("Calibri", Font.BOLD, 13));
		label_11.setBounds(500, 430, 46, 21);
		contentPane.add(label_11);
		
		JSlider armorXOffset = new JSlider();
		armorXOffset.setValue(0);
		armorXOffset.setPaintTicks(true);
		armorXOffset.setPaintLabels(true);
		armorXOffset.setMinimum(-20);
		armorXOffset.setMaximum(20);
		armorXOffset.setMajorTickSpacing(10);
		armorXOffset.setBounds(490, 450, 220, 38);
		contentPane.add(armorXOffset);
		
		//Accessories
		JLabel lblAccessoryYOffset = new JLabel("Y Offset");
		lblAccessoryYOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblAccessoryYOffset.setBounds(730, 360, 46, 21);
		contentPane.add(lblAccessoryYOffset);
		
		JSlider accessoryYOffset = new JSlider();
		accessoryYOffset.setValue(0);
		accessoryYOffset.setPaintTicks(true);
		accessoryYOffset.setPaintLabels(true);
		accessoryYOffset.setMinimum(-20);
		accessoryYOffset.setMaximum(20);
		accessoryYOffset.setMajorTickSpacing(10);
		accessoryYOffset.setBounds(720, 380, 220, 38);
		contentPane.add(accessoryYOffset);
		
		JLabel lblAccessoryXOffset = new JLabel("X Offset");
		lblAccessoryXOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblAccessoryXOffset.setBounds(730, 430, 46, 21);
		contentPane.add(lblAccessoryXOffset);
		
		JSlider accessoryXOffset = new JSlider();
		accessoryXOffset.setValue(0);
		accessoryXOffset.setPaintTicks(true);
		accessoryXOffset.setPaintLabels(true);
		accessoryXOffset.setMinimum(-20);
		accessoryXOffset.setMaximum(20);
		accessoryXOffset.setMajorTickSpacing(10);
		accessoryXOffset.setBounds(720, 450, 220, 38);
		contentPane.add(accessoryXOffset);

		JButton btnRandomPortrait = new JButton(BTNRANDOMPORTRAIT);
		btnRandomPortrait.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomPortrait.setBounds(905,150,130,40);
		contentPane.add(btnRandomPortrait);

		JButton btnExport = new JButton(BTNEXPORT);
		btnExport.setFont(new Font("Calibri", Font.BOLD, 13));
		btnExport.setBounds(905,100,130,40);
		contentPane.add(btnExport);
		
		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setFont(new Font("Calibri", Font.BOLD, 13));
		lblFileName.setBounds(905, 40,113,21);
		contentPane.add(lblFileName);
		//JTextField exportFileName = new JTextField();
		exportFileName.setBounds(905,60,130,21);
		contentPane.add(exportFileName);
		
		//--------LISTENERS--------------------
		hairYOffset.addChangeListener(this);
		hairXOffset.addChangeListener(this);
		faceYOffset.addChangeListener(this);
		faceXOffset.addChangeListener(this);
		armorYOffset.addChangeListener(this);
		armorXOffset.addChangeListener(this);
		accessoryYOffset.addChangeListener(this);
		accessoryXOffset.addChangeListener(this);

		sliders.add(hairYOffset);
		sliders.add(hairXOffset);
		sliders.add(faceYOffset);
		sliders.add(faceXOffset);
		sliders.add(armorYOffset);
		sliders.add(armorXOffset);
		sliders.add(accessoryYOffset);
		sliders.add(accessoryXOffset);

		
		comboBox_hairs.addItemListener(this);
		comboBox_faces.addItemListener(this);
		comboBox_armors.addItemListener(this);
		comboBox_accessories.addItemListener(this);
		comboBox_tokens.addItemListener(this);
		
		boxes.add(comboBox_hairs);
		boxes.add(comboBox_faces);
		boxes.add(comboBox_armors);
		boxes.add(comboBox_tokens);
		boxes.add(comboBox_accessories);

		btnRandomHair.addActionListener(this);
		btnRandomFace.addActionListener(this);
		btnRandomArmor.addActionListener(this);
		btnRandomToken.addActionListener(this);
		btnRandomAccessory.addActionListener(this);
		btnRandomPortrait.addActionListener(this);
		btnExport.addActionListener(this);
		
	}
	
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	
	void drawImages(){
		portrait = deepCopy(blankPortrait);
		token = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Color pixel = null;
		Color newPixel = null;
		//Draw Hair_back
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-hairYOffsetVal <0 || i- hairYOffsetVal>95) continue;
				if (j+hairXOffsetVal<0 || j+ hairXOffsetVal>95)continue;
				pixel = new Color(hairb.getRGB(i-hairYOffsetVal, j+hairXOffsetVal),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				//newPixel = pixel;
				//System.out.println(newPixel.getRed());
				
				//Image size x4
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		//Draw Armor
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-armorYOffsetVal <0 || i- armorYOffsetVal>95) continue;
				if (j+armorXOffsetVal<0 || j+ armorXOffsetVal>95)continue;
				pixel = new Color(armor.getRGB(i-armorYOffsetVal, j+armorXOffsetVal),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				//newPixel = pixel;
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		//Draw face
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-faceYOffsetVal <0 || i- faceYOffsetVal>95) continue;
				if (j+faceXOffsetVal<0 || j+ faceXOffsetVal>95)continue;
				pixel = new Color(face.getRGB(i-faceYOffsetVal, j+faceXOffsetVal),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = facePixelParser(pixel);
				//newPixel = pixel;
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		//Draw hair_front
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-hairYOffsetVal <0 || i- hairYOffsetVal>95) continue;
				if (j+hairXOffsetVal<0 || j+ hairXOffsetVal>95)continue;
				pixel = new Color(hair.getRGB(i-hairYOffsetVal, j+hairXOffsetVal),true);
				newPixel = pixelParser(pixel);
				if(pixel.getAlpha()==0){
					continue;
				}
				//newPixel = pixel;
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		//Draw accessory
				for(int i = 0; i<96; i++){
					for(int j = 0; j<96; j++){
						if (i-accessoryYOffsetVal <0 || i- accessoryYOffsetVal>95) continue;
						if (j+accessoryXOffsetVal<0 || j+ accessoryXOffsetVal>95)continue;
						pixel = new Color(accessory.getRGB(i-accessoryYOffsetVal, j+accessoryXOffsetVal),true);
						newPixel = facePixelParser(pixel);
						if(pixel.getAlpha()==0){
							continue;
						}
						//newPixel = pixel;
						portrait.setRGB(i*2, j*2, newPixel.getRGB());
						portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
						portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
						portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
					}
				}
		//Draw Token
		for(int i = 0; i<64; i++){
			for(int j = 0; j<64; j++){
				pixel = new Color(importedToken.getRGB(i, j),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				//System.out.println(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue());
				//newPixel = pixel;
				token.setRGB(i*2, j*2, newPixel.getRGB());
				token.setRGB(i*2+1, j*2, newPixel.getRGB());
				token.setRGB(i*2, j*2+1, newPixel.getRGB());
				token.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		
	}
	
	//RECOLORS EVERYTHING
		//The included images all have red values corresponding to what they are.
		//This is why eye and hair color match, because they share the same range of values (1-3)
		//E.g. face color is 51, the lighter parts are 42, and darker parts are 60+, corresponding to case 4-8.
			//Given that the max value is 25 (255), if the image eyes were changed to have red values above 210,
			//they could be separated from the hair
			//Alternatively, if the pixel could be confirmed as part of the hair or face, a simple if statement would fix it
	
	Color pixelParser(Color pixel){
		Color newPixel = null;
		//double check this line 
		if(pixel.getAlpha() == 0){
			newPixel = blankColor;
			return newPixel;
		}
		int redIndex = pixel.getRed()/10; 
				//System.out.println(redIndex);
		switch(redIndex){
		case 0: newPixel = outlineColor;
				break;
		case 1: newPixel = hairColor.brighter();
				break;
		case 2: newPixel = hairColor;
				break;
		case 3: newPixel = hairColor.darker();
				break;
		case 4: newPixel = skinColor.brighter();
				break;
		case 5: newPixel = skinColor;
				break;
		case 6: newPixel = skinColor.darker();
				break;
		case 7: newPixel = skinColor.darker().darker();
				break;
		case 8: newPixel = skinColor.darker().darker().darker();
				break;
		case 9: newPixel = metalColor.brighter();
				break;
		case 10: newPixel = metalColor;
				break;
		case 11: newPixel = metalColor.darker();
				break;
		case 12: newPixel = trimColor.brighter();
				break;
		case 13: newPixel = trimColor;
				break;
		case 14: newPixel = trimColor.darker();
				break;
		case 15: newPixel = clothColor.brighter();
				break;
		case 16: newPixel = clothColor;
				break;
		case 17: newPixel = clothColor.darker();
				break;
		case 18: newPixel = leatherColor.brighter();
				break;
		case 19: newPixel = leatherColor;
				break;
		case 20: newPixel = leatherColor.darker();
				break;
		default: newPixel = Color.WHITE;
		}
		return newPixel;
	}
	
	Color facePixelParser(Color pixel){ //Currently used for face and accessory
		Color newPixel = null;
		//double check this line 
		if(pixel.getAlpha() == 0){
			newPixel = blankColor;
			return newPixel;
		}
		int redIndex = pixel.getRed()/10; 
				//System.out.println(redIndex);
		switch(redIndex){
		case 0: newPixel = outlineColor;
				break;
		case 1: newPixel = eyeColor.brighter();
				break;
		case 2: newPixel = eyeColor;
				break;
		case 3: newPixel = eyeColor.darker();
				break;
		case 4: newPixel = skinColor.brighter();
				break;
		case 5: newPixel = skinColor;
				break;
		case 6: newPixel = skinColor.darker();
				break;
		case 7: newPixel = skinColor.darker().darker();
				break;
		case 8: newPixel = skinColor.darker().darker().darker();
				break;
		case 9: newPixel = accessoryColor.brighter();
				break;
		case 10: newPixel = accessoryColor;
				break;
		case 11: newPixel = accessoryColor.darker();
				break;
				
				//12-20 shouldn't get used, but I'll keep them for now
		case 12: newPixel = trimColor.brighter();
				break;
		case 13: newPixel = trimColor;
				break;
		case 14: newPixel = trimColor.darker();
				break;
		case 15: newPixel = clothColor.brighter();
				break;
		case 16: newPixel = clothColor;
				break;
		case 17: newPixel = clothColor.darker();
				break;
		case 18: newPixel = leatherColor.brighter();
				break;
		case 19: newPixel = leatherColor;
				break;
		case 20: newPixel = leatherColor.darker();
				break;
		default: newPixel = Color.WHITE;
		}
		return newPixel;
	}
	
	//If slider value changes, update image
	public void stateChanged(ChangeEvent e){
		JSlider src = (JSlider) e.getSource();
		int index = sliders.indexOf(src);
		int val = src.getValue();
		//System.out.println(index);
		switch(index){
		case 0: //hair_X	------Should not be using id numbers. Too many potential problems when changed
			hairXOffsetVal = val;
			break;
		case 1: //hair_Y
			hairYOffsetVal = val;
			break;
		case 2:
			faceXOffsetVal = val;
			break;
		case 3:
			faceYOffsetVal = val;
			break;
		case 4:
			armorXOffsetVal = val;
			break;
		case 5:
			armorYOffsetVal = val;
			break;
		case 6:
			accessoryXOffsetVal = val;
			break;
		case 7:
			accessoryYOffsetVal = val;
			break;
		default:
			System.out.println("Switch statement overran");
		}
		//System.out.println(src.getName() + " Val: " + val);
		drawImages();
		portraitPanel.setImage(portrait);
		portraitPanel.repaint();
		tokenPanel.setImage(token);
		tokenPanel.repaint();
	}
	
	public void itemStateChanged(ItemEvent event){
		try {
			String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String path = URLDecoder.decode(rawPath, "UTF-8");
			path = path.substring(0, path.lastIndexOf("/") + 1);
			path = path.replaceAll("%20", " ");
			
			JComboBox<String> src = (JComboBox<String>)event.getSource();
			String fileName = (String)src.getSelectedItem();
			int menuNumber = boxes.indexOf(src);
			switch(menuNumber){
			case 0:
				try {
				    hair = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
				}
				try {
					String secondFileName = fileName.substring(0, fileName.length()-4);
				    hairb = ImageIO.read(new File(path + "resources/" + secondFileName + "b.png"));
				} catch (IOException ex) {
				}
				break;
			case 1:
				try {
				    face = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
				}
				break;
			case 2:
				try {
				    armor = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
				}
				break;
			case 3:
				try {
				    importedToken = ImageIO.read(new File(path + "resources/" + fileName));
				  //System.out.println(src.getName() + " FileName: " + fileName);
				} catch (IOException ex) {
				}
				break;
			case 4:
				try {
				    accessory = ImageIO.read(new File(path + "resources/" + fileName));
				  //System.out.println(src.getName() + " FileName: " + fileName);
				} catch (IOException ex) {
				}
				break;	
			default:
				System.out.println("File Swith OverRun");
			}
			//System.out.println(src.getName() + " FileName: " + fileName);
			drawImages();
			portraitPanel.setImage(portrait);
			portraitPanel.repaint();
			tokenPanel.setImage(token);
			tokenPanel.repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Create Default colors panel
	class MyChooserPanel extends AbstractColorChooserPanel {

		private static final long serialVersionUID = 8469886355393151520L;
		JPanel hairBtns = new JPanel();
		
		public void buildChooser() {
		    //setLayout(new GridLayout(50,50,0, 3));
			
		    setLayout(new FlowLayout());
//		    JPanel skinBtns = new JPanel();
//		    add(skinBtns);
//		    JPanel hairBtns = new JPanel();
//		    add(hairBtns);
		    
		    //THIS SHOULD PROBABLY BE AN ARRAY
		    //Default Skin Colors
		    makeSkinButton("btnSkinColor_6", new Color(248,224,216,255));
		    makeSkinButton("btnSkinColor_5", new Color(232,216,192,255));
		    makeSkinButton("btnSkinColor_7", new Color(248,208,152,255));
		    makeSkinButton("btnSkinColor_0", new Color(248,216,120,255));
		    makeSkinButton("btnSkinColor_1", new Color(248,224,120,255));
		    makeSkinButton("btnSkinColor_3", new Color(248,224,96,255));
		    makeSkinButton("btnSkinColor_2", new Color(240,184,104,255));
		    makeSkinButton("btnSkinColor_4", new Color(232,160,72,255));
		    makeSkinButton("btnSkinColor_9", new Color(140,101,82,255));
		    makeSkinButton("btnSkinColor_8", new Color(112,80,64,255));
		    
		    //Default Hair Colors
		    makeHairButton("btnHairColor_Lyn", new Color(48,104,104,255));
		    makeHairButton("btnHairColor_Hector", new Color(80,80,216,255));
		    makeHairButton("btnHairColor_Eliwood", new Color(208,40,32,255));
		    makeHairButton("btnHairColor_Ninian", new Color(120,224,224,255));
		    makeHairButton("btnHairColor_Nils", new Color(72,192,184,255));
		    makeHairButton("btnHairColor_blonde", new Color(224,216,64,255));
		    getColorSelectionModel().setSelectedColor(startCCColor);
		  }

		  public void updateChooser() {
		  }

		  public String getDisplayName() {
		    return "Defaults";
		  }

		  public Icon getSmallDisplayIcon() {
		    return null;
		  }
		  public Icon getLargeDisplayIcon() {
		    return null;
		  }
		  private void makeSkinButton(String name, Color color) {
		    JButton button = new JButton(name);
		    button.setPreferredSize(new Dimension(30,30));
		    button.setBackground(color);
		    button.setAction(setColorAction);
		    add(button);
		  }
		  private void makeHairButton(String name, Color color) {
			    JButton button = new JButton(name);
			    button.setPreferredSize(new Dimension(30,30));
			    button.setBackground(color);
			    button.setAction(setColorAction);
			    add(button);
			  }
		  
		  Action setColorAction = new AbstractAction() {
			private static final long serialVersionUID = 4121913800217436519L;

			public void actionPerformed(ActionEvent evt) {
		      JButton button = (JButton) evt.getSource();

		      getColorSelectionModel().setSelectedColor(button.getBackground());
		    }
		  };
		} //End MyChooserPanel
	
	class MyPreviewPanel extends JComponent {
		private static final long serialVersionUID = -6482402848842359509L;
		Color curColor;
		public MyPreviewPanel(JColorChooser choosr) {
			curColor = choosr.getColor();
			setPreferredSize(new Dimension(150,50));
		}
		//Create 3 squares with the lighter, base, and darker colors
		public void paint(Graphics g) {
			g.setColor(curColor.brighter());
			g.fillRect(0, 0,50,50);
			g.setColor(curColor);
			g.fillRect(50, 0,50,50);
			g.setColor(curColor.darker());
			g.fillRect(100, 0, 50, 50);
		}
	}
	
	public static AbstractColorChooserPanel findPanel (JColorChooser chooser, String name) {
		AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
		for (int i = 0; i < panels.length; i++) {
			String clsName = panels[i].getClass().getName();
			if (clsName.equals(name)) {
				return panels[i];
			}
		}
		return null;
	}
	
	public void actionPerformed(ActionEvent event){
		Component source = null;
		String srcAction = "";
		source = (JButton)(event.getSource());
		srcAction = ((JButton)source).getText();
		
		//WIP
		JColorChooser chooser = new JColorChooser();
		//int numPanels = chooser.getChooserPanels().length;
		//AbstractColorChooserPanel[] newPanels = new AbstractColorChooserPanel[3];
		//newPanels[0] = findPanel(chooser, "javax.swing.colorchooser.DefaultRGBChooserPanel");
		//newPanels[0] = findPanel(chooser, "javax.swing.colorchooser.DefaultHSBChooserPanel");
		//newPanels[0] = findPanel(chooser, "javax.swing.colorchooser.DefaultSwatchChooserPanel");
		
		chooser.addChooserPanel(new MyChooserPanel());
		final MyPreviewPanel pre = new MyPreviewPanel(chooser);
		chooser.setPreviewPanel(pre);
		
		ColorSelectionModel model = chooser.getSelectionModel();
		model.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				ColorSelectionModel model = (ColorSelectionModel) evt.getSource();
				pre.curColor = model.getSelectedColor();
			}
		});
		//Did nothing: chooser.getSelectionModel().setSelectedColor(newCCColor);
		
	    ActionListener okActionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          newCCColor = chooser.getColor();
	        }
	      };

	      // For cancel selection, change button background to red
	      ActionListener cancelActionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	newCCColor = startCCColor;
	        }
	      };
		
	      final JDialog dialog = JColorChooser.createDialog(this, "Select a Color", true,
			        chooser, okActionListener, cancelActionListener);
			dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		if(srcAction.equalsIgnoreCase(BTNEXPORT)) {
			try{	
	
				String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				String path = URLDecoder.decode(rawPath, "UTF-8");
				path = path.substring(0, path.lastIndexOf("/") + 1);
				path = path.replaceAll("%20", " ");
				File portraitOutputFile = new File(path + exportFileName.getText() + ".png");
				try{		
					ImageIO.write(portrait, "PNG", portraitOutputFile);
					}
				catch(IOException e){};
				
				File tokenOutputFile = new File(path + exportFileName.getText() + "token.png");
			
					ImageIO.write(token, "PNG", tokenOutputFile);
				}
			catch(IOException e){};
		}
		else {
			switch(srcAction) {
			case BTNHAIRCOLOR:
				startCCColor = hairColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				//if (newColor != null)
					hairColor = newCCColor;
				hairRectB.setBackground(hairColor.brighter());
				hairRect.setBackground(hairColor);
				hairRectD.setBackground(hairColor.darker());
				break;
			case BTNEYECOLOR :
				startCCColor = eyeColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
				eyeColor = newCCColor;
				eyeRectB.setBackground(eyeColor.brighter());
				eyeRect.setBackground(eyeColor);
				eyeRectD.setBackground(eyeColor.darker());
				break;
			case BTNSKINCOLOR:
				startCCColor = skinColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
				skinColor = newCCColor;
				skinRectB.setBackground(skinColor.brighter());
				skinRect.setBackground(skinColor);
				skinRectD.setBackground(skinColor.darker());
				break;
			case BTNMETALCOLOR:
				startCCColor = metalColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
					metalColor = newCCColor;
					metalRectB.setBackground(metalColor.brighter());
					metalRect.setBackground(metalColor);
					metalRectD.setBackground(metalColor.darker());
				break;
			case BTNTRIMCOLOR:
				startCCColor = trimColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
					trimColor = newCCColor;
					trimRectB.setBackground(trimColor.brighter());
					trimRect.setBackground(trimColor);
					trimRectD.setBackground(trimColor.darker());
				break;
			case BTNCLOTHCOLOR:
				startCCColor = clothColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
					clothColor = newCCColor;
					clothRectB.setBackground(clothColor.brighter());
					clothRect.setBackground(clothColor);
					clothRectD.setBackground(clothColor.darker());
				break;
			case BTNLEATHERCOLOR:
				startCCColor = leatherColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
					leatherColor = newCCColor;
					leatherRectB.setBackground(leatherColor.brighter());
					leatherRect.setBackground(leatherColor);
					leatherRectD.setBackground(leatherColor.darker());
				break;
			case BTNACCESSORYCOLOR:
				startCCColor = accessoryColor;
				chooser.setColor(startCCColor);
				dialog.setVisible(true);
				
				//if (newColor != null)
					accessoryColor = newCCColor;
					accessoryRectB.setBackground(accessoryColor.brighter());
					accessoryRect.setBackground(accessoryColor);
					accessoryRectD.setBackground(accessoryColor.darker());
				break;
			case BTNRANDOMHAIR:
				randomizeComboBox(boxes.get(0));
				break;
			case BTNRANDOMFACE:
				randomizeComboBox(boxes.get(1));
				break;
			case BTNRANDOMARMOR:
				randomizeComboBox(boxes.get(2));
				break;
			case BTNRANDOMTOKEN:
				randomizeComboBox(boxes.get(3));
				break;
			case BTNRANDOMACCESSORY:
				randomizeComboBox(boxes.get(4));
				break;
			case BTNRANDOMPORTRAIT:
				for (JComboBox box : boxes) {
					if (boxes.indexOf(box) != 3) {
						randomizeComboBox(box);
					}
				}
				break;
			}
			
			drawImages();
			portraitPanel.setImage(portrait);
			portraitPanel.repaint();
			tokenPanel.setImage(token);
			tokenPanel.repaint();
		}

	}

	private void randomizeComboBox(JComboBox box) {
		Random random = new Random();
		int index = random.nextInt(box.getItemCount());
		box.setSelectedIndex(index);
	}
	
}
