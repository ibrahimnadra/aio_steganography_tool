package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home implements ActionListener {
    JFrame home_frame ;
    JButton home_encode_btn , home_decode_btn, home_info_btn;
    JLabel AIO_label , background_label;

    Home(){
        home_frame = new JFrame("Security Tool");

        AIO_label = new JLabel("AIO Steganography Tool");
        AIO_label.setFont(new Font("Roboto",Font.BOLD, 30));
        AIO_label.setForeground(Color.BLACK);
        AIO_label.setFocusable(false);

        home_encode_btn  = new JButton("ENCODE");
        home_decode_btn = new JButton("DECODE");
        home_info_btn = new JButton("INFO");

        home_encode_btn.setForeground(Color.BLACK);
        home_decode_btn.setForeground(Color.BLACK);
        home_info_btn.setForeground(Color.BLACK);

        home_encode_btn.setFont(new Font("Roboto",Font.BOLD,15));
        home_decode_btn.setFont(new Font("Roboto",Font.BOLD,15));
        home_info_btn.setFont(new Font("Roboto",Font.BOLD,15));

        home_encode_btn.setFocusable(false);
        home_decode_btn.setFocusable(false);
        home_info_btn.setFocusable(false);

        ImageIcon backgroundImage = new ImageIcon("/home/knoldus/majorProject/digital_steganography/backgroundImage.jpeg");
        background_label = new JLabel(backgroundImage);

        Image icon = Toolkit.getDefaultToolkit().getImage("/home/knoldus/majorProject/digital_steganography/icon.png");
        home_frame.setIconImage(icon);

        home_frame.setSize(800, 600);
        home_frame.setLayout(new BorderLayout());

        home_frame.setResizable(false);
        home_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        home_frame.setLocationRelativeTo(null);
        home_frame.setVisible(true);
    }

    private void setHomeElements(){
            AIO_label.setBounds(125,100,600,35);
            background_label.setBounds(0, 0, home_frame.getWidth(), home_frame.getHeight());

            home_encode_btn.setBounds(125,200,200,50);
            home_decode_btn.setBounds(125,300,200,50);
            home_info_btn.setBounds(125,400,200,50);

            home_encode_btn.addActionListener(this);
            home_decode_btn.addActionListener(this);
            home_info_btn.addActionListener(this);
    }

    private void addHomeElements(){
        home_frame.add(AIO_label);
        home_frame.add(home_encode_btn);
        home_frame.add(home_decode_btn);
        home_frame.add(home_info_btn);
        home_frame.getContentPane().add(background_label);
    }

    void initialiseHome(){
        setHomeElements();
        addHomeElements();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == home_encode_btn){
                home_frame.dispose();
                Encode encode = new Encode();
                encode.initialiseEncode();
            }
            else if(e.getSource() == home_decode_btn){
                home_frame.dispose();
                Decode decode = new Decode();
                decode.initialiseDecode();
            }
            else if(e.getSource() == home_info_btn){
                home_frame.dispose();
                Info info = new Info();
                info.initialiseInfo();
            }

    }
}
