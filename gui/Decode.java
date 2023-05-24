package gui;

import javax.swing.*;

import textInAudioStego.AudioSteganography;
import imageInImageStego.ImageHider;
import textInImageStego.TextSteganography;
import textInVideoStego.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.*;

public class Decode {
    JLabel background_label;

    JFrame decode_frame;
    JRadioButton Radio_Text_image, Radio_image_image, Radio_text_audio, Radio_text_video;
    JLabel decodeLabel;
    ButtonGroup decode_button_group;
    JButton button_decode, button_select_file,button_home;
    public String file_dir;

    Decode() {
        decode_frame = new JFrame("Decode Frame");

        ImageIcon backgroundImage = new ImageIcon("/home/knoldus/majorProject/digital_steganography/backgroundImage.jpeg");
        background_label = new JLabel(backgroundImage);

        Image icon = Toolkit.getDefaultToolkit().getImage("/home/knoldus/majorProject/digital_steganography/icon.png");
        decode_frame.setIconImage(icon);

        Radio_Text_image = new JRadioButton("Text from Image");
        Radio_image_image = new JRadioButton("Image from Image");
        Radio_text_audio = new JRadioButton("Text from Audio");
        Radio_text_video = new JRadioButton("Text from Video");

        Radio_image_image.setFocusable(false);
        Radio_Text_image.setFocusable(false);
        Radio_text_video.setFocusable(false);
        Radio_text_audio.setFocusable(false);


        decode_button_group = new ButtonGroup();
        button_decode = new JButton("DECODE");
        button_decode.setFocusable(false);
        button_decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDecodeButton();
            }
        });

        button_select_file = new JButton("Select file");
        button_select_file.setFocusable(false);
        button_select_file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleFileChooser();
            }
        });

        button_home = new JButton("HOME");
        button_home.setFocusable(false);
        button_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleHome();
            }
        });

        decodeLabel = new JLabel("DECODE");
        decodeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        decodeLabel.setForeground(Color.BLACK);

        decode_frame.setLayout(null);
        decode_frame.setSize(800, 600);
        decode_frame.setResizable(false);
        decode_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        decode_frame.setLocationRelativeTo(null);
        decode_frame.setVisible(true);
    }

    void setDecodeElements() {
        background_label.setBounds(0, 0, decode_frame.getWidth(), decode_frame.getHeight());
        decodeLabel.setBounds(125,100,600,35);
        Radio_Text_image.setBounds(125, 200, 160, 20);
        Radio_image_image.setBounds(125, 250, 160, 20);
        Radio_text_audio.setBounds(125, 300, 160, 20);
        Radio_text_video.setBounds(125, 350, 160, 20);

        button_select_file.setBounds(125, 400, 150 , 30);
        button_home.setBounds(125,450,200,50);
        button_decode.setBounds(425,450,200,50);
    }

    void addDecodeElements() {
        decode_frame.add(decodeLabel);

        decode_button_group.add(Radio_Text_image);
        decode_button_group.add(Radio_image_image);
        decode_button_group.add(Radio_text_audio);
        decode_button_group.add(Radio_text_video);

        decode_frame.add(Radio_Text_image);
        decode_frame.add(Radio_image_image);
        decode_frame.add(Radio_text_audio);
        decode_frame.add(Radio_text_video);

        decode_frame.add(button_decode);
        decode_frame.add(button_select_file);
        decode_frame.add(button_home);
        decode_frame.getContentPane().add(background_label);
    }
    void initialiseDecode() {
        setDecodeElements();
        addDecodeElements();
    }

    void handleFileChooser() {
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(workingDirectory);
        int r = jfc.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            file_dir = jfc.getSelectedFile().getAbsolutePath();
        } else {
            file_dir = "please select a file";
        }
    }

    void handleDecodeButton() {
        if (Radio_Text_image.isSelected()) {
            String sec_mesg = TextSteganography.decode(file_dir, getSecretKey());
            if (sec_mesg.length() > 0) {
                JOptionPane.showMessageDialog(decode_frame, sec_mesg);
            }
        } else if (Radio_image_image.isSelected()) {
            ImageHider objimg = new ImageHider();
            objimg.reveal(file_dir, "Imageoutput.png");
            JOptionPane.showMessageDialog(decode_frame, imageInImageStego.Messages.IMAGE_EXTRACTED_SUCCESSFULLY.getmessage());
        } else if (Radio_text_audio.isSelected()) {
            File audioFile = new File(file_dir);
            try {
                JOptionPane.showMessageDialog(decode_frame, AudioSteganography.decodeMessage(audioFile));
            } catch (Exception e) {
                System.out.println("Exception in audioStegnography");
            }
        } else if (Radio_text_video.isSelected()) {
            try {
                Path inp = Paths.get(file_dir);
                RC4 rc4 = new RC4(getSecretKey().getBytes());
                FLVCrypto flv = new FLVCrypto();
                byte[] inpufileBytes = IO.readFileBytes(inp);
                byte[] encryptedByte = flv.extract(inpufileBytes);
                String msg = new String(rc4.decrypt(encryptedByte));
                JOptionPane.showMessageDialog(decode_frame, msg);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    String getSecretKey() {
        return JOptionPane.showInputDialog(decode_frame, "Enter your Secret Key");
    }

    void handleHome(){
        decode_frame.dispose();
        Home obj = new Home();
        obj.initialiseHome();
    }

}
