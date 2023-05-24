
package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info implements ActionListener {

    JFrame info_frame;
    JTextArea description_area;
    JScrollPane scroll_pane;
    JButton button_home ;
    Info() {
        description_area = new JTextArea("Application Name: AIO Steganography Tool\nVersion: 1.0\n" +
                "Developer : Nadra Ibrahim\nContact: nadra.ibrahim@knoldus.com\n" +
                "Date : 23-MAY-2023\n" +
                " AIO Steganography Tool : Comprehensive set of features for hiding and extracting" +
                "data from various types of files, \n" +
                "including images, videos and audio files. \n\n\n " +
                "WORK FLOW :\n" +
                "Button 1 : Encode\n" +
                "1. Select cover media : The digital media in which the information will be embedded :" +
                "• Image\n(.png, .jpeg, .jpeg)" +
                "• Video\n(.flv)" +
                "• Audio\n(.aac, .wav)" +
                "1. Select input media : The secret digital media  which will be embedded :\n" +
                "• Image (Only for image cover media) \n" +
                "• Text\n" +
                "NOTE : The dimensions of secret should be lesser than the cover image, \n" +
                "i.e. canvasArea < (2 * secretArea + Steganographer.METADATA_PIXELS)\n" +
                "Button 2 : Decode\n" +
                "1. Text from Image : The Stego-image is selected by the user and on pressing decode button,\n" +
                " the secret text will be decoded from the image and will be displayed.\n" +
                "2. Image from Image : The Stego-image is selected by the user and on pressing decode button,\n" +
                " the secret mage will be decoded from the image. The secret image will be \n" +
                "saved in the current working directory as “Imageoutput.png”. \n" +
                "The user can open the image from the file browser.\n" +
                "3. Text from Audio : The Stego-audio file of format (.wav) is selected by the user \n" +
                "and on pressing decode button, the secret text will be decoded  and displayed.\n" +
                "4. Text from Video : The Stego-video file of format (.flv) is selected by the user \n" +
                "and on pressing decode button, the secret text will be decoded  and displayed.\n\n\n" +
                "ALGORITHM : \n" +
                "1. Text from Image : LSB substitution\n" +
                "2. Image from Image : DES + LSB\n" +
                "3. Text from Audio : Randomized LSB substitution.\n" +
                "4. Text from Video : RC4 + Frame-level steganography. \n"
        );
        description_area.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        description_area.setForeground(Color.BLACK);
        description_area.setBackground(Color.white);
        description_area.setOpaque(true);
        description_area.setFocusable(false);
        description_area.setEditable(false);
        scroll_pane = new JScrollPane(description_area);

        info_frame = new JFrame("Information");
        Image icon = Toolkit.getDefaultToolkit().getImage("/home/knoldus/majorProject/digital_steganography/icon.png");
        info_frame.setIconImage(icon);

        button_home = new JButton("HOME");
        button_home.setFocusable(false);
        button_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleHome();
            }
        });

        info_frame.setLayout(null);
        info_frame.setSize(800, 600);
        info_frame.setResizable(false);
        info_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info_frame.setLocationRelativeTo(null);
        info_frame.setVisible(true);
    }

    private void setInfoElements() {
        description_area.setBounds(0, 0, info_frame.getWidth(), info_frame.getHeight());
        button_home.setBounds(450,500,200,50);
    }

    private void addInfoElements() {
        info_frame.add(button_home);
        info_frame.add(description_area);
    }

    void initialiseInfo() {
        setInfoElements();
        addInfoElements();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    void handleHome(){
        info_frame.dispose();
        Home obj = new Home();
        obj.initialiseHome();
    }
}
