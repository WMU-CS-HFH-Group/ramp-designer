package oldExamples;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;

import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddData extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroupAge = new ButtonGroup();
	private JTextField textFieldMajor;
	private final ButtonGroup buttonGroupCredit = new ButtonGroup();
	private final ButtonGroup buttonGroupYear = new ButtonGroup();
	private final ButtonGroup buttonGroupAvgCaf = new ButtonGroup();
	private final ButtonGroup buttonGroupWater = new ButtonGroup();
	private final ButtonGroup buttonGroupStart = new ButtonGroup();
	private final ButtonGroup buttonGroupEnd = new ButtonGroup();
	private final ButtonGroup buttonGroupWork = new ButtonGroup();
	private final ButtonGroup buttonGroupSleep = new ButtonGroup();
	private final ButtonGroup buttonGroupFinal = new ButtonGroup();
	private final ButtonGroup buttonGroupPerfered = new ButtonGroup();

	// Devlin Start
	private String age = "NULL";
	private String credit = "NULL";
	private String year = "NULL";
	private String caffeine = "NULL";
	private String caffeine2 = "NULL";
	private String water = "NULL";
	private String classStart = "NULL";
	private String lateWork = "NULL";
	private String hours = "NULL";
	private String sleep = "NULL";
	private String business = "NULL";
	private Survey survey;
	private JTextField updatedQuestion;

	// Devlin Stop
	public AddData(Survey a) {
		survey = a;
		launch();
	}

	/**
	 * Launch the application.
	 */
	public void launch() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Take Survey");
		setResizable(false);
		setBounds(100, 100, 763, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToggleButton toggleButton = new JToggleButton("18-20");
		buttonGroupAge.add(toggleButton);
		toggleButton.setAction(new age1());
		toggleButton.setBounds(20, 83, 151, 23);
		contentPane.add(toggleButton);

		JToggleButton toggleButton_1 = new JToggleButton("21-25");
		buttonGroupAge.add(toggleButton_1);
		toggleButton_1.setAction(new age2());
		toggleButton_1.setBounds(185, 83, 165, 23);
		contentPane.add(toggleButton_1);

		JToggleButton toggleButton_2 = new JToggleButton("26-30");
		buttonGroupAge.add(toggleButton_2);
		toggleButton_2.setAction(new age3());
		toggleButton_2.setBounds(20, 117, 151, 23);
		contentPane.add(toggleButton_2);

		JToggleButton toggleButton_3 = new JToggleButton("31+");
		buttonGroupAge.add(toggleButton_3);
		toggleButton_3.setAction(new age4());
		toggleButton_3.setBounds(185, 117, 165, 23);
		contentPane.add(toggleButton_3);

		textFieldMajor = new JTextField();
		textFieldMajor.setBounds(20, 199, 330, 20);
		textFieldMajor.setAction(new tFM());
		contentPane.add(textFieldMajor);
		textFieldMajor.setColumns(10);

		JToggleButton toggleButton_4 = new JToggleButton("0-6");
		buttonGroupCredit.add(toggleButton_4);
		toggleButton_4.setAction(new credit1());
		toggleButton_4.setBounds(20, 285, 94, 23);
		contentPane.add(toggleButton_4);

		JToggleButton toggleButton_5 = new JToggleButton("7-11");
		buttonGroupCredit.add(toggleButton_5);
		toggleButton_5.setAction(new credit2());
		toggleButton_5.setBounds(124, 285, 104, 23);
		contentPane.add(toggleButton_5);

		JToggleButton toggleButton_6 = new JToggleButton("12-15");
		buttonGroupCredit.add(toggleButton_6);
		toggleButton_6.setAction(new credit3());
		toggleButton_6.setBounds(238, 285, 112, 23);
		contentPane.add(toggleButton_6);

		JToggleButton toggleButton_7 = new JToggleButton("16-18");
		buttonGroupCredit.add(toggleButton_7);
		toggleButton_7.setAction(new credit4());
		toggleButton_7.setBounds(67, 319, 104, 23);
		contentPane.add(toggleButton_7);

		JToggleButton toggleButton_8 = new JToggleButton("19+");
		buttonGroupCredit.add(toggleButton_8);
		toggleButton_8.setAction(new credit5());
		toggleButton_8.setBounds(185, 319, 94, 23);
		contentPane.add(toggleButton_8);

		JToggleButton tglbtnFreshman = new JToggleButton("Freshman");
		buttonGroupYear.add(tglbtnFreshman);
		tglbtnFreshman.setAction(new year1());
		tglbtnFreshman.setBounds(20, 389, 104, 23);
		contentPane.add(tglbtnFreshman);

		JToggleButton tglbtnSophmore = new JToggleButton("Sophmore");
		buttonGroupYear.add(tglbtnSophmore);
		tglbtnSophmore.setAction(new year2());
		tglbtnSophmore.setBounds(134, 389, 105, 23);
		contentPane.add(tglbtnSophmore);

		JToggleButton tglbtnJunior = new JToggleButton("Junior");
		buttonGroupYear.add(tglbtnJunior);
		tglbtnJunior.setAction(new year3());
		tglbtnJunior.setBounds(246, 389, 104, 23);
		contentPane.add(tglbtnJunior);

		JToggleButton tglbtnSenior = new JToggleButton("Senior");
		buttonGroupYear.add(tglbtnSenior);
		tglbtnSenior.setAction(new year4());
		tglbtnSenior.setBounds(20, 423, 151, 23);
		contentPane.add(tglbtnSenior);

		JToggleButton tglbtnGraduate = new JToggleButton("Graduate");
		buttonGroupYear.add(tglbtnGraduate);
		tglbtnGraduate.setAction(new year5());
		tglbtnGraduate.setBounds(210, 423, 140, 23);
		contentPane.add(tglbtnGraduate);

		JToggleButton toggleButton_9 = new JToggleButton("0");
		buttonGroupAvgCaf.add(toggleButton_9);
		toggleButton_9.setAction(new caffeine1());
		toggleButton_9.setBounds(10, 494, 85, 23);
		contentPane.add(toggleButton_9);

		JToggleButton toggleButton_10 = new JToggleButton("1");
		buttonGroupAvgCaf.add(toggleButton_10);
		toggleButton_10.setAction(new caffeine2());
		toggleButton_10.setBounds(105, 494, 82, 23);
		contentPane.add(toggleButton_10);

		JToggleButton toggleButton_11 = new JToggleButton("2");
		buttonGroupAvgCaf.add(toggleButton_11);
		toggleButton_11.setAction(new caffeine3());
		toggleButton_11.setBounds(197, 494, 82, 23);
		contentPane.add(toggleButton_11);

		JToggleButton toggleButton_12 = new JToggleButton("3");
		buttonGroupAvgCaf.add(toggleButton_12);
		toggleButton_12.setAction(new caffeine4());
		toggleButton_12.setBounds(285, 494, 82, 23);
		contentPane.add(toggleButton_12);

		JToggleButton toggleButton_13 = new JToggleButton("4");
		buttonGroupAvgCaf.add(toggleButton_13);
		toggleButton_13.setAction(new caffeine5());
		toggleButton_13.setBounds(66, 528, 77, 23);
		contentPane.add(toggleButton_13);

		JToggleButton toggleButton_14 = new JToggleButton("5");
		buttonGroupAvgCaf.add(toggleButton_14);
		toggleButton_14.setAction(new caffeine6());
		toggleButton_14.setBounds(153, 528, 82, 23);
		contentPane.add(toggleButton_14);

		JToggleButton toggleButton_15 = new JToggleButton("6+");
		buttonGroupAvgCaf.add(toggleButton_15);
		toggleButton_15.setAction(new caffeine7());
		toggleButton_15.setBounds(245, 528, 82, 23);
		contentPane.add(toggleButton_15);

		JToggleButton tglbtnNone = new JToggleButton("None");
		buttonGroupPerfered.add(tglbtnNone);
		tglbtnNone.setAction(new caffeine2_1());
		tglbtnNone.setBounds(10, 599, 94, 23);
		contentPane.add(tglbtnNone);

		JToggleButton tglbtnCoffee = new JToggleButton("Coffee");
		buttonGroupPerfered.add(tglbtnCoffee);
		tglbtnCoffee.setAction(new caffeine2_2());
		tglbtnCoffee.setBounds(134, 599, 94, 23);
		contentPane.add(tglbtnCoffee);

		JToggleButton tglbtnTea = new JToggleButton("Tea");
		buttonGroupPerfered.add(tglbtnTea);
		tglbtnTea.setAction(new caffeine2_3());
		tglbtnTea.setBounds(256, 599, 94, 23);
		contentPane.add(tglbtnTea);

		JToggleButton tglbtnSoda = new JToggleButton("Soda");
		buttonGroupPerfered.add(tglbtnSoda);
		tglbtnSoda.setAction(new caffeine2_4());
		tglbtnSoda.setBounds(10, 633, 94, 23);
		contentPane.add(tglbtnSoda);

		JToggleButton tglbtnEnergyDrink = new JToggleButton("Energy Drink");
		buttonGroupPerfered.add(tglbtnEnergyDrink);
		tglbtnEnergyDrink.setAction(new caffeine2_5());
		tglbtnEnergyDrink.setBounds(124, 633, 118, 23);
		contentPane.add(tglbtnEnergyDrink);

		JToggleButton tglbtnOther = new JToggleButton("Other");
		buttonGroupPerfered.add(tglbtnOther);
		tglbtnOther.setAction(new caffeine2_6());
		tglbtnOther.setBounds(257, 633, 93, 23);
		contentPane.add(tglbtnOther);

		JLabel lblHowManyCaffinated = new JLabel(
				"How many caffinated beverages do you drink in a day?");
		lblHowManyCaffinated.setOpaque(true);
		lblHowManyCaffinated.setBackground(Color.ORANGE);
		lblHowManyCaffinated.setVerticalAlignment(SwingConstants.TOP);
		lblHowManyCaffinated.setBounds(10, 473, 357, 92);
		contentPane.add(lblHowManyCaffinated);

		JLabel lblHowManyCredits = new JLabel(
				"How many credits are you taking?");
		lblHowManyCredits.setOpaque(true);
		lblHowManyCredits.setBackground(Color.ORANGE);
		lblHowManyCredits.setVerticalAlignment(SwingConstants.TOP);
		lblHowManyCredits.setBounds(10, 266, 357, 92);
		contentPane.add(lblHowManyCredits);

		JLabel lblWhatIsYour = new JLabel("What is your age Group?");
		lblWhatIsYour.setOpaque(true);
		lblWhatIsYour.setBackground(Color.ORANGE);
		lblWhatIsYour.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsYour.setBounds(10, 60, 357, 92);
		contentPane.add(lblWhatIsYour);

		JLabel lblWhatIsYour_2 = new JLabel(
				"What is your preferred Caffinated Drink?");
		lblWhatIsYour_2.setOpaque(true);
		lblWhatIsYour_2.setBackground(Color.ORANGE);
		lblWhatIsYour_2.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsYour_2.setBounds(10, 576, 357, 86);
		contentPane.add(lblWhatIsYour_2);

		JLabel lblLable = new JLabel("Health Questionnaire");
		lblLable.setOpaque(true);
		lblLable.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblLable.setBounds(10, 11, 238, 38);
		contentPane.add(lblLable);

		JLabel lblWhatIsYour_1 = new JLabel("What is your Current Major?");
		lblWhatIsYour_1.setOpaque(true);
		lblWhatIsYour_1.setBackground(Color.ORANGE);
		lblWhatIsYour_1.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsYour_1.setBounds(10, 163, 357, 92);
		contentPane.add(lblWhatIsYour_1);

		JLabel lblWhatYearStudent = new JLabel("What year student are you?");
		lblWhatYearStudent.setOpaque(true);
		lblWhatYearStudent.setBackground(Color.ORANGE);
		lblWhatYearStudent.setVerticalAlignment(SwingConstants.TOP);
		lblWhatYearStudent.setBounds(10, 370, 357, 92);
		contentPane.add(lblWhatYearStudent);

		JToggleButton toggleButton_16 = new JToggleButton("0");
		buttonGroupWater.add(toggleButton_16);
		toggleButton_16.setAction(new water1());
		toggleButton_16.setBounds(377, 83, 57, 23);
		contentPane.add(toggleButton_16);

		JToggleButton toggleButton_17 = new JToggleButton("1");
		buttonGroupWater.add(toggleButton_17);
		toggleButton_17.setAction(new water2());
		toggleButton_17.setBounds(444, 83, 57, 23);
		contentPane.add(toggleButton_17);

		JToggleButton toggleButton_18 = new JToggleButton("2");
		buttonGroupWater.add(toggleButton_18);
		toggleButton_18.setAction(new water3());
		toggleButton_18.setBounds(511, 83, 57, 23);
		contentPane.add(toggleButton_18);

		JToggleButton toggleButton_19 = new JToggleButton("3");
		buttonGroupWater.add(toggleButton_19);
		toggleButton_19.setAction(new water4());
		toggleButton_19.setBounds(578, 83, 57, 23);
		contentPane.add(toggleButton_19);

		JToggleButton toggleButton_20 = new JToggleButton("4");
		buttonGroupWater.add(toggleButton_20);
		toggleButton_20.setAction(new water5());
		toggleButton_20.setBounds(645, 83, 57, 23);
		contentPane.add(toggleButton_20);

		JToggleButton toggleButton_21 = new JToggleButton("5");
		buttonGroupWater.add(toggleButton_21);
		toggleButton_21.setAction(new water6());
		toggleButton_21.setBounds(414, 117, 57, 23);
		contentPane.add(toggleButton_21);

		JToggleButton toggleButton_22 = new JToggleButton("6");
		buttonGroupWater.add(toggleButton_22);
		toggleButton_22.setAction(new water7());
		toggleButton_22.setBounds(481, 117, 57, 23);
		contentPane.add(toggleButton_22);

		JToggleButton toggleButton_23 = new JToggleButton("7");
		buttonGroupWater.add(toggleButton_23);
		toggleButton_23.setAction(new water8());
		toggleButton_23.setBounds(548, 117, 57, 23);
		contentPane.add(toggleButton_23);

		JToggleButton toggleButton_24 = new JToggleButton("8+");
		buttonGroupWater.add(toggleButton_24);
		toggleButton_24.setAction(new water9());
		toggleButton_24.setBounds(615, 117, 57, 23);
		contentPane.add(toggleButton_24);

		JLabel lblHowMuchWater = new JLabel(
				"How much water do you drink in an average day? (8 oz)");
		lblHowMuchWater.setOpaque(true);
		lblHowMuchWater.setBackground(Color.ORANGE);
		lblHowMuchWater.setVerticalAlignment(SwingConstants.TOP);
		lblHowMuchWater.setBounds(377, 60, 357, 92);
		contentPane.add(lblHowMuchWater);

		JToggleButton tglbtnAm = new JToggleButton("8:00 AM");
		buttonGroupStart.add(tglbtnAm);
		tglbtnAm.setAction(new classStart1());
		tglbtnAm.setBounds(377, 184, 82, 23);
		contentPane.add(tglbtnAm);

		JToggleButton tglbtnAm_1 = new JToggleButton("8:30 AM");
		buttonGroupStart.add(tglbtnAm_1);
		tglbtnAm_1.setAction(new classStart2());
		tglbtnAm_1.setBounds(469, 184, 82, 23);
		contentPane.add(tglbtnAm_1);

		JToggleButton tglbtnAm_2 = new JToggleButton("9:00 AM");
		buttonGroupStart.add(tglbtnAm_2);
		tglbtnAm_2.setAction(new classStart3());
		tglbtnAm_2.setBounds(561, 184, 82, 23);
		contentPane.add(tglbtnAm_2);

		JToggleButton tglbtnAm_3 = new JToggleButton("9:30 AM");
		buttonGroupStart.add(tglbtnAm_3);
		tglbtnAm_3.setAction(new classStart4());
		tglbtnAm_3.setBounds(652, 184, 82, 23);
		contentPane.add(tglbtnAm_3);

		JToggleButton tglbtnAm_4 = new JToggleButton("10:00 AM");
		buttonGroupStart.add(tglbtnAm_4);
		tglbtnAm_4.setAction(new classStart5());
		tglbtnAm_4.setBounds(407, 218, 94, 23);
		contentPane.add(tglbtnAm_4);

		JToggleButton tglbtnAm_5 = new JToggleButton("10:30 AM");
		buttonGroupStart.add(tglbtnAm_5);
		tglbtnAm_5.setAction(new classStart6());
		tglbtnAm_5.setBounds(511, 218, 94, 23);
		contentPane.add(tglbtnAm_5);

		JToggleButton tglbtnAm_6 = new JToggleButton("11:00+ AM");
		buttonGroupStart.add(tglbtnAm_6);
		tglbtnAm_6.setAction(new classStart7());
		tglbtnAm_6.setBounds(613, 218, 104, 23);
		contentPane.add(tglbtnAm_6);

		JLabel lblWhatTimeDoes = new JLabel(
				"What time does your earliest class start?");
		lblWhatTimeDoes.setOpaque(true);
		lblWhatTimeDoes.setBackground(Color.ORANGE);
		lblWhatTimeDoes.setVerticalAlignment(SwingConstants.TOP);
		lblWhatTimeDoes.setBounds(377, 163, 357, 92);
		contentPane.add(lblWhatTimeDoes);

		JToggleButton tglbtnPm = new JToggleButton("<5:00 PM");
		buttonGroupEnd.add(tglbtnPm);
		tglbtnPm.setAction(new lateWork1());
		tglbtnPm.setBounds(377, 285, 104, 23);
		contentPane.add(tglbtnPm);

		JToggleButton tglbtnPm_1 = new JToggleButton("5:00 PM");
		buttonGroupEnd.add(tglbtnPm_1);
		tglbtnPm_1.setAction(new lateWork2());
		tglbtnPm_1.setBounds(494, 285, 104, 23);
		contentPane.add(tglbtnPm_1);

		JToggleButton tglbtnPm_2 = new JToggleButton("6:00 PM");
		buttonGroupEnd.add(tglbtnPm_2);
		tglbtnPm_2.setAction(new lateWork3());
		tglbtnPm_2.setBounds(613, 285, 104, 23);
		contentPane.add(tglbtnPm_2);

		JToggleButton tglbtnPm_3 = new JToggleButton("7:00 PM");
		buttonGroupEnd.add(tglbtnPm_3);
		tglbtnPm_3.setAction(new lateWork4());
		tglbtnPm_3.setBounds(377, 319, 104, 23);
		contentPane.add(tglbtnPm_3);

		JToggleButton tglbtnPm_4 = new JToggleButton("8:00 PM");
		buttonGroupEnd.add(tglbtnPm_4);
		tglbtnPm_4.setAction(new lateWork5());
		tglbtnPm_4.setBounds(494, 319, 104, 23);
		contentPane.add(tglbtnPm_4);

		JToggleButton tglbtnPm_5 = new JToggleButton("9:00+ PM");
		buttonGroupEnd.add(tglbtnPm_5);
		tglbtnPm_5.setAction(new lateWork6());
		tglbtnPm_5.setBounds(615, 319, 104, 23);
		contentPane.add(tglbtnPm_5);

		JLabel lblWhatIsThe = new JLabel(
				"What is the latest that your work/class day ends?");
		lblWhatIsThe.setOpaque(true);
		lblWhatIsThe.setBackground(Color.ORANGE);
		lblWhatIsThe.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsThe.setBounds(377, 266, 357, 92);
		contentPane.add(lblWhatIsThe);

		JToggleButton toggleButton_25 = new JToggleButton("0-5");
		buttonGroupWork.add(toggleButton_25);
		toggleButton_25.setAction(new hours1());
		toggleButton_25.setBounds(377, 389, 104, 23);
		contentPane.add(toggleButton_25);

		JToggleButton toggleButton_26 = new JToggleButton("6-15");
		buttonGroupWork.add(toggleButton_26);
		toggleButton_26.setAction(new hours2());
		toggleButton_26.setBounds(494, 389, 104, 23);
		contentPane.add(toggleButton_26);

		JToggleButton toggleButton_27 = new JToggleButton("16-25");
		buttonGroupWork.add(toggleButton_27);
		toggleButton_27.setAction(new hours3());
		toggleButton_27.setBounds(613, 389, 104, 23);
		contentPane.add(toggleButton_27);

		JToggleButton toggleButton_28 = new JToggleButton("26-35");
		buttonGroupWork.add(toggleButton_28);
		toggleButton_28.setAction(new hours4());
		toggleButton_28.setBounds(434, 423, 104, 23);
		contentPane.add(toggleButton_28);

		JToggleButton toggleButton_29 = new JToggleButton("36+");
		buttonGroupWork.add(toggleButton_29);
		toggleButton_29.setAction(new hours5());
		toggleButton_29.setBounds(548, 423, 104, 23);
		contentPane.add(toggleButton_29);

		JLabel lblHowManyHours = new JLabel(
				"How many hours do you work per week?");
		lblHowManyHours.setOpaque(true);
		lblHowManyHours.setBackground(Color.ORANGE);
		lblHowManyHours.setVerticalAlignment(SwingConstants.TOP);
		lblHowManyHours.setBounds(377, 370, 357, 92);
		contentPane.add(lblHowManyHours);

		JToggleButton tglbtnHours = new JToggleButton("<4 Hours");
		buttonGroupSleep.add(tglbtnHours);
		tglbtnHours.setAction(new sleep1());
		tglbtnHours.setBounds(377, 494, 104, 23);
		contentPane.add(tglbtnHours);

		JToggleButton tglbtnHours_1 = new JToggleButton("4-5 Hours");
		buttonGroupSleep.add(tglbtnHours_1);
		tglbtnHours_1.setAction(new sleep2());
		tglbtnHours_1.setBounds(494, 494, 104, 23);
		contentPane.add(tglbtnHours_1);

		JToggleButton tglbtnHours_2 = new JToggleButton("5-6 Hours");
		buttonGroupSleep.add(tglbtnHours_2);
		tglbtnHours_2.setAction(new sleep3());
		tglbtnHours_2.setBounds(615, 494, 104, 23);
		contentPane.add(tglbtnHours_2);

		JToggleButton tglbtnHours_3 = new JToggleButton("6-7 Hours");
		buttonGroupSleep.add(tglbtnHours_3);
		tglbtnHours_3.setAction(new sleep4());
		tglbtnHours_3.setBounds(377, 528, 104, 23);
		contentPane.add(tglbtnHours_3);

		JToggleButton tglbtnHours_4 = new JToggleButton("7-8 Hours");
		buttonGroupSleep.add(tglbtnHours_4);
		tglbtnHours_4.setAction(new sleep5());
		tglbtnHours_4.setBounds(494, 528, 104, 23);
		contentPane.add(tglbtnHours_4);

		JToggleButton tglbtnHours_5 = new JToggleButton("8+ Hours");
		buttonGroupSleep.add(tglbtnHours_5);
		tglbtnHours_5.setAction(new sleep6());
		tglbtnHours_5.setBounds(613, 528, 104, 23);
		contentPane.add(tglbtnHours_5);

		JLabel lblHowManyHours_1 = new JLabel(
				"How many hours of sleep do you get each night?");
		lblHowManyHours_1.setOpaque(true);
		lblHowManyHours_1.setVerticalAlignment(SwingConstants.TOP);
		lblHowManyHours_1.setBackground(Color.ORANGE);
		lblHowManyHours_1.setBounds(377, 473, 360, 92);
		contentPane.add(lblHowManyHours_1);

		JToggleButton tglbtnYes = new JToggleButton("Yes");
		buttonGroupFinal.add(tglbtnYes);
		tglbtnYes.setAction(new business1());
		tglbtnYes.setBounds(380, 609, 104, 23);
		contentPane.add(tglbtnYes);

		JToggleButton tglbtnNo = new JToggleButton("No");
		buttonGroupFinal.add(tglbtnNo);
		tglbtnNo.setAction(new business2());
		tglbtnNo.setBounds(494, 609, 104, 23);
		contentPane.add(tglbtnNo);

		JToggleButton tglbtnWhoHasnt = new JToggleButton("Who Hasn't?!");
		buttonGroupFinal.add(tglbtnWhoHasnt);
		tglbtnWhoHasnt.setAction(new business3());
		tglbtnWhoHasnt.setBounds(613, 609, 121, 23);
		contentPane.add(tglbtnWhoHasnt);

		JLabel lblHaveYouConsidered = new JLabel(
				"Have you considered switching to a business major?");
		lblHaveYouConsidered.setOpaque(true);
		lblHaveYouConsidered.setVerticalAlignment(SwingConstants.TOP);
		lblHaveYouConsidered.setBackground(Color.ORANGE);
		lblHaveYouConsidered.setBounds(377, 576, 360, 86);
		contentPane.add(lblHaveYouConsidered);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(Color.YELLOW);
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSubmit.setAction(new submit());
		btnSubmit.setBounds(258, 11, 280, 38);
		contentPane.add(btnSubmit);
		
		updatedQuestion = new JTextField();
		updatedQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		updatedQuestion.setOpaque(false);
		updatedQuestion.setEditable(false);
		updatedQuestion.setAutoscrolls(false);
		updatedQuestion.setBounds(549, 11, 185, 38);
		contentPane.add(updatedQuestion);
		updatedQuestion.setColumns(10);
	}

	/*
	 * Submits the form data
	 */
	public void submit() throws SQLException {
		if (textFieldMajor.getText().length() > 40) { // Limits the field to 40
			// characters.
			textFieldMajor.setText(textFieldMajor.getText().substring(0, 40));
		}
		if (textFieldMajor.getText().equals("")) {
			textFieldMajor.setText("NULL");
		}

		int dataLength = 0;

		if (!age.equals("NULL")) {
			dataLength++;
		}
		if (!textFieldMajor.getText().equals("NULL")) {
			dataLength++;
		}
		if (!credit.equals("NULL")) {
			dataLength++;
		}
		if (!year.equals("NULL")) {
			dataLength++;
		}
		if (!caffeine.equals("NULL")) {
			dataLength++;
		}
		if (!caffeine2.equals("NULL")) {
			dataLength++;
		}
		if (!water.equals("NULL")) {
			dataLength++;
		}
		if (!classStart.equals("NULL")) {
			dataLength++;
		}
		if (!lateWork.equals("NULL")) {
			dataLength++;
		}
		if (!hours.equals("NULL")) {
			dataLength++;
		}
		if (!sleep.equals("NULL")) {
			dataLength++;
		}
		if (!business.equals("NULL")) {
			dataLength++;
		}

		String[][] data = new String[2][dataLength];
		int box = 0;

		if (!age.equals("NULL")) {
			data[0][box] = "ageGroup";
			data[1][box] = age;
			box++;
		}
		if (!textFieldMajor.getText().equals("NULL")) {
			data[0][box] = "major";
			data[1][box] = textFieldMajor.getText();
			box++;
			dataLength++;
		}
		if (!credit.equals("NULL")) {
			data[0][box] = "credits";
			data[1][box] = credit;
			box++;
		}
		if (!year.equals("NULL")) {
			data[0][box] = "studentlevel";
			data[1][box] = year;
			box++;
		}
		if (!caffeine.equals("NULL")) {
			data[0][box] = "numCaffBev";
			data[1][box] = caffeine;
			box++;
		}
		if (!caffeine2.equals("NULL")) {
			data[0][box] = "prefCaff";
			data[1][box] = caffeine2;
			box++;
		}
		if (!water.equals("NULL")) {
			data[0][box] = "numWater";
			data[1][box] = water;
			box++;
		}
		if (!classStart.equals("NULL")) {
			data[0][box] = "startTime";
			data[1][box] = classStart;
			box++;
		}
		if (!lateWork.equals("NULL")) {
			data[0][box] = "endTime";
			data[1][box] = lateWork;
			box++;
		}
		if (!hours.equals("NULL")) {
			data[0][box] = "hourWork";
			data[1][box] = hours;
			box++;
		}
		if (!sleep.equals("NULL")) {
			data[0][box] = "hourSleep";
			data[1][box] = sleep;
			box++;
		}
		if (!business.equals("NULL")) {
			data[0][box] = "business";
			data[1][box] = business;
			box++;
		}

		// String[][] data = {
		// { "ageGroup", "major", "credits", "studentlevel", "numCaffBev",
		// "prefCaff", "numWater", "startTime", "endTime",
		// "hourWork", "hourSleep", "business" },
		// { age, textFieldMajor.getText(), credit, year, caffeine,
		// caffeine2, water, classStart, lateWork, hours, sleep,
		// business } };

		buttonGroupAge.clearSelection();
		age = "NULL";
		buttonGroupCredit.clearSelection();
		credit = "NULL";
		buttonGroupYear.clearSelection();
		year = "NULL";
		buttonGroupAvgCaf.clearSelection();
		caffeine = "NULL";
		buttonGroupPerfered.clearSelection();
		caffeine2 = "NULL";
		buttonGroupWater.clearSelection();
		water = "NULL";
		buttonGroupStart.clearSelection();
		classStart = "NULL";
		buttonGroupEnd.clearSelection();
		lateWork = "NULL";
		buttonGroupWork.clearSelection();
		hours = "NULL";
		buttonGroupSleep.clearSelection();
		sleep = "NULL";
		buttonGroupFinal.clearSelection();
		business = "NULL";

		textFieldMajor.setText("");

		if(survey.submit(data)){
			updatedQuestion.setText("Submited");
		}else{
			updatedQuestion.setText("Failed");
		}
	}

	// Devlin Start
	/**
	 * Age Groups
	 */
	private class age1 extends AbstractAction {
		// Code pulled from Main.java.
		// Seems to override button text.
		public age1() {
			putValue(NAME, "18-20");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			age = "18-20";
		}
	}

	private class age2 extends AbstractAction {
		public age2() {
			putValue(NAME, "21-25");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			age = "21-25";
		}
	}

	private class age3 extends AbstractAction {
		public age3() {
			putValue(NAME, "26-30");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			age = "26-30";
		}
	}

	private class age4 extends AbstractAction {
		public age4() {
			putValue(NAME, "31+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			age = "31+";
		}
	}

	/**
	 * Text Field: Major
	 */
	private class tFM extends AbstractAction {
		public tFM() {
			putValue(NAME, "Major");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			if (textFieldMajor.getText().length() > 40) { // Limits the field to
				// 40 characters.
				textFieldMajor.setText(textFieldMajor.getText()
						.substring(0, 40));
			}
		}
	}

	/**
	 * Credit hours
	 */
	private class credit1 extends AbstractAction {
		public credit1() {
			putValue(NAME, "0-6");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			credit = "0-6";
		}
	}

	private class credit2 extends AbstractAction {
		public credit2() {
			putValue(NAME, "7-11");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			credit = "7-11";
		}
	}

	private class credit3 extends AbstractAction {
		public credit3() {
			putValue(NAME, "12-15");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			credit = "12-15";
		}
	}

	private class credit4 extends AbstractAction {
		public credit4() {
			putValue(NAME, "16-18");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			credit = "16-18";
		}
	}

	private class credit5 extends AbstractAction {
		public credit5() {
			putValue(NAME, "19+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			credit = "19+";
		}
	}

	/**
	 * Year
	 */
	private class year1 extends AbstractAction {
		public year1() {
			putValue(NAME, "Freshman");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			year = "Freshman";
		}
	}

	private class year2 extends AbstractAction {
		public year2() {
			putValue(NAME, "Sophmore");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			year = "Sophmore";
		}
	}

	private class year3 extends AbstractAction {
		public year3() {
			putValue(NAME, "Junior");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			year = "Junior";
		}
	}

	private class year4 extends AbstractAction {
		public year4() {
			putValue(NAME, "Senior");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			year = "Senior";
		}
	}

	private class year5 extends AbstractAction {
		public year5() {
			putValue(NAME, "Graduate");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			year = "Graduate";
		}
	}

	/**
	 * Caffeine
	 */
	private class caffeine1 extends AbstractAction {
		public caffeine1() {
			putValue(NAME, "0");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "0";
		}
	}

	private class caffeine2 extends AbstractAction {
		public caffeine2() {
			putValue(NAME, "1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "1";
		}
	}

	private class caffeine3 extends AbstractAction {
		public caffeine3() {
			putValue(NAME, "2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "2";
		}
	}

	private class caffeine4 extends AbstractAction {
		public caffeine4() {
			putValue(NAME, "3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "3";
		}
	}

	private class caffeine5 extends AbstractAction {
		public caffeine5() {
			putValue(NAME, "4");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "4";
		}
	}

	private class caffeine6 extends AbstractAction {
		public caffeine6() {
			putValue(NAME, "5");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "5";
		}
	}

	private class caffeine7 extends AbstractAction {
		public caffeine7() {
			putValue(NAME, "6+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine = "6+";
		}
	}

	/**
	 * Caffeine 2
	 */
	private class caffeine2_1 extends AbstractAction {
		public caffeine2_1() {
			putValue(NAME, "None");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "None";
		}
	}

	private class caffeine2_2 extends AbstractAction {
		public caffeine2_2() {
			putValue(NAME, "Coffee");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "Coffee";
		}
	}

	private class caffeine2_3 extends AbstractAction {
		public caffeine2_3() {
			putValue(NAME, "Tea");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "Tea";
		}
	}

	private class caffeine2_4 extends AbstractAction {
		public caffeine2_4() {
			putValue(NAME, "Soda"); // It's pop dammit!
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "Soda";
		}
	}

	private class caffeine2_5 extends AbstractAction {
		public caffeine2_5() {
			putValue(NAME, "Energy Drink");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "Energy Drink";
		}
	}

	private class caffeine2_6 extends AbstractAction {
		public caffeine2_6() {
			putValue(NAME, "Other");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			caffeine2 = "Other";
		}
	}

	/**
	 * Water
	 */
	private class water1 extends AbstractAction {
		public water1() {
			putValue(NAME, "0");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "0";
		}
	}

	private class water2 extends AbstractAction {
		public water2() {
			putValue(NAME, "1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "1";
		}
	}

	private class water3 extends AbstractAction {
		public water3() {
			putValue(NAME, "2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "2";
		}
	}

	private class water4 extends AbstractAction {
		public water4() {
			putValue(NAME, "3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "3";
		}
	}

	private class water5 extends AbstractAction {
		public water5() {
			putValue(NAME, "4");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "4";
		}
	}

	private class water6 extends AbstractAction {
		public water6() {
			putValue(NAME, "5");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "5";
		}
	}

	private class water7 extends AbstractAction {
		public water7() {
			putValue(NAME, "6");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "6";
		}
	}

	private class water8 extends AbstractAction {
		public water8() {
			putValue(NAME, "7");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "7";
		}
	}

	private class water9 extends AbstractAction {
		public water9() {
			putValue(NAME, "8+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			water = "8+";
		}
	}

	/**
	 * Class Start
	 */
	private class classStart1 extends AbstractAction {
		public classStart1() {
			putValue(NAME, "8:00 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "8:00 AM";
		}
	}

	private class classStart2 extends AbstractAction {
		public classStart2() {
			putValue(NAME, "8:30 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "8:30 AM";
		}
	}

	private class classStart3 extends AbstractAction {
		public classStart3() {
			putValue(NAME, "9:00 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "9:00 AM";
		}
	}

	private class classStart4 extends AbstractAction {
		public classStart4() {
			putValue(NAME, "9:30 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "9:30 AM";
		}
	}

	private class classStart5 extends AbstractAction {
		public classStart5() {
			putValue(NAME, "10:00 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "10:00 AM";
		}
	}

	private class classStart6 extends AbstractAction {
		public classStart6() {
			putValue(NAME, "10:30 AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "10:30 AM";
		}
	}

	private class classStart7 extends AbstractAction {
		public classStart7() {
			putValue(NAME, "11:00+ AM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			classStart = "11:00+ AM";
		}
	}

	/**
	 * Late Work
	 */
	private class lateWork1 extends AbstractAction {
		public lateWork1() {
			putValue(NAME, "<5:00 PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "<5:00 PM";
		}
	}

	private class lateWork2 extends AbstractAction {
		public lateWork2() {
			putValue(NAME, "5:00 PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "5:00 PM";
		}
	}

	private class lateWork3 extends AbstractAction {
		public lateWork3() {
			putValue(NAME, "6:00 PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "6:00 PM";
		}
	}

	private class lateWork4 extends AbstractAction {
		public lateWork4() {
			putValue(NAME, "7:00 PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "7:00 PM";
		}
	}

	private class lateWork5 extends AbstractAction {
		public lateWork5() {
			putValue(NAME, "8:00 PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "8:00 PM";
		}
	}

	private class lateWork6 extends AbstractAction {
		public lateWork6() {
			putValue(NAME, "9:00+ PM");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lateWork = "9:00+ PM";
		}
	}

	/**
	 * Hours
	 */
	private class hours1 extends AbstractAction {
		public hours1() {
			putValue(NAME, "0-5");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			hours = "0-5";
		}
	}

	private class hours2 extends AbstractAction {
		public hours2() {
			putValue(NAME, "6-15");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			hours = "6-15";
		}
	}

	private class hours3 extends AbstractAction {
		public hours3() {
			putValue(NAME, "16-25");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			hours = "16-25";
		}
	}

	private class hours4 extends AbstractAction {
		public hours4() {
			putValue(NAME, "26-35");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			hours = "26-35";
		}
	}

	private class hours5 extends AbstractAction {
		public hours5() {
			putValue(NAME, "36+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			hours = "36+";
		}
	}

	/**
	 * Sleep
	 */
	private class sleep1 extends AbstractAction {
		public sleep1() {
			putValue(NAME, "<4 Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "<4";
		}
	}

	private class sleep2 extends AbstractAction {
		public sleep2() {
			putValue(NAME, "4-5 Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "4-5";
		}
	}

	private class sleep3 extends AbstractAction {
		public sleep3() {
			putValue(NAME, "5-6 Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "5-6";
		}
	}

	private class sleep4 extends AbstractAction {
		public sleep4() {
			putValue(NAME, "6-7 Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "6-7";
		}
	}

	private class sleep5 extends AbstractAction {
		public sleep5() {
			putValue(NAME, "7-8 Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "7-8";
		}
	}

	private class sleep6 extends AbstractAction {
		public sleep6() {
			putValue(NAME, "8+ Hours");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			sleep = "8+";
		}
	}

	/**
	 * Business
	 */
	private class business1 extends AbstractAction {
		public business1() {
			putValue(NAME, "Yes");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			business = "Yes";
		}
	}

	private class business2 extends AbstractAction {
		public business2() {
			putValue(NAME, "No");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			business = "No";
		}
	}

	private class business3 extends AbstractAction {
		public business3() {
			putValue(NAME, "Who Hasn't?!");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			business = "Who Hasn\\'t?!";
		}
	}

	/**
	 * Submit
	 */
	private class submit extends AbstractAction {
		public submit() {
			putValue(NAME, "Submit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				submit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	// Devlin End
}