import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//author Lihan Xu

public class hotel_booking {
    public static void main(String[] args) {


        JFrame frame =new JFrame("xjtlu international hotel booking system");

        frame.setSize(530,1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel =new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);



        //for user information
        JLabel userLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone number:");
        panel.add(userLabel);
        panel.add(phoneLabel);
        userLabel.setBounds(100,25,80,25);
        phoneLabel.setBounds(100,55,120,25);

        JTextField user = new JTextField(20);
        user.setBounds(250,25,165,25);
        panel.add(user);
        JTextField phone = new JTextField(20);
        phone.setBounds(250,55,165,25);
        panel.add(phone);



        //for check in and out
        JLabel check_in =new JLabel("check in:");
        JLabel check_out =new JLabel("check out:");
        panel.add(check_in);
        panel.add(check_out);
        check_in.setBounds(100,85,80,25);
        check_out.setBounds(100,115,80,25);

        JComboBox in_year=new JComboBox();
        JComboBox in_month=new JComboBox();
        JComboBox in_day=new JComboBox();
        in_year.setBounds(250,85,80,25);
        in_month.setBounds(335,85,40,25);
        in_day.setBounds(380,85,40,25);
        in_year.addItem("year");
        in_year.addItem("2021");
        in_year.addItem("2022");
        in_month.addItem("month");
        in_month.addItem("1");
        in_month.addItem("2");
        in_month.addItem("3");
        in_month.addItem("4");
        in_month.addItem("5");
        in_month.addItem("6");
        in_month.addItem("7");
        in_month.addItem("8");
        in_month.addItem("9");
        in_month.addItem("10");
        in_month.addItem("11");
        in_month.addItem("12");
        in_day.addItem("date");
        in_day.addItem("1");
        in_day.addItem("2");
        in_day.addItem("3");
        in_day.addItem("4");
        in_day.addItem("5");
        in_day.addItem("6");
        in_day.addItem("7");
        in_day.addItem("8");
        in_day.addItem("9");
        in_day.addItem("10");
        in_day.addItem("11");
        in_day.addItem("12");
        in_day.addItem("13");
        in_day.addItem("14");
        in_day.addItem("15");
        in_day.addItem("16");
        in_day.addItem("17");
        in_day.addItem("18");
        in_day.addItem("19");
        in_day.addItem("20");
        in_day.addItem("21");
        in_day.addItem("22");
        in_day.addItem("23");
        in_day.addItem("24");
        in_day.addItem("25");
        in_day.addItem("26");
        in_day.addItem("27");
        in_day.addItem("28");
        in_day.addItem("29");
        in_day.addItem("30");
        in_day.addItem("31");
        panel.add(in_year);
        panel.add(in_month);
        panel.add(in_day);

        JComboBox out_year=new JComboBox();
        JComboBox out_month=new JComboBox();
        JComboBox out_day=new JComboBox();
        out_year.setBounds(250,115,80,25);
        out_month.setBounds(335,115,40,25);
        out_day.setBounds(380,115,40,25);
        out_year.addItem("year");
        out_year.addItem("2021");
        out_year.addItem("2022");
        out_month.addItem("month");
        out_month.addItem("1");
        out_month.addItem("2");
        out_month.addItem("3");
        out_month.addItem("4");
        out_month.addItem("5");
        out_month.addItem("6");
        out_month.addItem("7");
        out_month.addItem("8");
        out_month.addItem("9");
        out_month.addItem("10");
        out_month.addItem("11");
        out_month.addItem("12");
        out_day.addItem("date");
        out_day.addItem("1");
        out_day.addItem("2");
        out_day.addItem("3");
        out_day.addItem("4");
        out_day.addItem("5");
        out_day.addItem("6");
        out_day.addItem("7");
        out_day.addItem("8");
        out_day.addItem("9");
        out_day.addItem("10");
        out_day.addItem("11");
        out_day.addItem("12");
        out_day.addItem("13");
        out_day.addItem("14");
        out_day.addItem("15");
        out_day.addItem("16");
        out_day.addItem("17");
        out_day.addItem("18");
        out_day.addItem("19");
        out_day.addItem("20");
        out_day.addItem("21");
        out_day.addItem("22");
        out_day.addItem("23");
        out_day.addItem("24");
        out_day.addItem("25");
        out_day.addItem("26");
        out_day.addItem("27");
        out_day.addItem("28");
        out_day.addItem("29");
        out_day.addItem("30");
        out_day.addItem("31");
        panel.add(out_year);
        panel.add(out_month);
        panel.add(out_day);




        //for selecting rooms
        JLabel room_types=new JLabel("select room(s)：");
        panel.add(room_types);
        room_types.setBounds(40,230,120,25);
        JCheckBox chkbox1=new JCheckBox("economic room");
        JCheckBox chkbox2=new JCheckBox("standard room");
        JCheckBox chkbox3=new JCheckBox("luxury room");
        chkbox1.setBounds(145,230,120,25);
        chkbox2.setBounds(145,260,120,25);
        chkbox3.setBounds(145,290,120,25);
        panel.add(chkbox1);
        panel.add(chkbox2);
        panel.add(chkbox3);



        JLabel room_number=new JLabel("room quantity：");
        panel.add(room_number);
        room_number.setBounds(305,230,120,25);

        JComboBox economic=new JComboBox();
        JComboBox standard=new JComboBox();
        JComboBox luxury=new JComboBox();
        economic.setBounds(410,230,40,25);
        standard.setBounds(410,260,40,25);
        luxury.setBounds(410,290,40,25);
        economic.addItem("");
        economic.addItem("1");
        economic.addItem("2");
        economic.addItem("3");
        economic.addItem("4");
        economic.addItem("5");
        standard.addItem("");
        standard.addItem("1");
        standard.addItem("2");
        standard.addItem("3");
        out_day.addItem("4");
        standard.addItem("5");
        luxury.addItem("");
        luxury.addItem("1");
        luxury.addItem("2");
        luxury.addItem("3");
        luxury.addItem("4");
        luxury.addItem("5");
        panel.add(economic);
        panel.add(standard);
        panel.add(luxury);




        //add space for specific requirements
        JLabel specific_requirements =new JLabel("specific requirements:");
        specific_requirements.setBounds(40,320,165,25);
        panel.add(specific_requirements);
        JTextField requirements =new JTextField(20);
        requirements.setBounds(200,320,250,150);
        panel.add(requirements);



        //add reset and confirm button
        JButton clearButton = new JButton("reset");
        JButton confirmButton =new JButton("confirm");
        confirmButton.setBounds(250,500,80,25);
        clearButton.setBounds(150, 500, 80, 25);
        panel.add(clearButton);
        panel.add(confirmButton);

        JLabel clear  =new JLabel();
        clear.setBounds(250,550,300,25);
        panel.add(clear);
        JLabel confirm  =new JLabel();
        confirm.setBounds(250,550,300,25);
        panel.add(confirm);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clear.setText("info cleared,your booking was reseted");
                confirm.setText("");
                user.setText("");
                phone.setText("");
                requirements.setText("");
                in_year.setSelectedItem("year");
                in_month.setSelectedItem("month");
                in_day.setSelectedItem("date");
                out_year.setSelectedItem("year");
                out_month.setSelectedItem("month");
                out_day.setSelectedItem("date");
                chkbox1.setSelected(false);
                chkbox2.setSelected(false);
                chkbox3.setSelected(false);
                economic.setSelectedItem("");
                standard.setSelectedItem("");
                luxury.setSelectedItem("");
            }
            }
        );


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                confirm.setText("booking success,proceeding to payment page……");
                clear.setText("");
            }
        });



        //add map pics

        JLabel address =new JLabel("mapped location：");
        address.setBounds(40,580,300,25);
        panel.add(address);
        JLabel location =new JLabel(new ImageIcon("src/map.png"));
        panel.add(location);
        location.setBounds(5,600,525,180);
    }
}
