package gui;

import javax.swing.*;

import textInAudioStego.AudioSteganography;
import textInAudioStego.NotEnoughSpaceException;
import textInAudioStego.SecretMessageException;
import imageInImageStego.ImageHider;
import textInImageStego.TextSteganography;
import textInVideoStego.FLVCrypto;
import textInVideoStego.IO;
import textInVideoStego.RC4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.*;

public class Encode implements ActionListener {
    JFrame encode_frame;
    JLabel encode_label;
    JLabel icon_label;
    JLabel select_cover_label;
    JLabel select_input_label;
    JLabel background_label;
    JLabel file_loc,file_loc2;

    JRadioButton encode_radio_image,encode_radio_image2, encode_radio_text, encode_radio_audio, encode_radio_video;

    JButton open_cover_media, open_input_media;

    JButton encode_button,button_home ;

    JTextArea message_area ;

    public static String file_dir , temp_file_name;

    ButtonGroup encode_btn_group = new ButtonGroup();

    Encode() {
        encode_frame = new JFrame("Encode Frame");
        encode_frame.getContentPane().setBackground(new java.awt.Color(239, 222, 205));

        ImageIcon backgroundImage = new ImageIcon("/home/knoldus/majorProject/digital_steganography/backgroundImage.jpeg");
        background_label = new JLabel(backgroundImage);

        Image icon = Toolkit.getDefaultToolkit().getImage("/home/knoldus/majorProject/digital_steganography/icon.png");
        encode_frame.setIconImage(icon);

        encode_label = new JLabel("ENCODE");
        encode_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        encode_label.setForeground(Color.BLACK);
        encode_label.setFocusable(false);

        select_cover_label = new JLabel("Select cover media : ");
        select_cover_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        select_cover_label.setForeground(Color.BLACK);
        select_cover_label.setFocusable(false);

        select_input_label = new JLabel("Select input media : ");
        select_input_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        select_input_label.setForeground(Color.BLACK);
        select_input_label.setFocusable(false);

        encode_radio_image = new JRadioButton("Image");
        encode_radio_video = new JRadioButton("Video");
        encode_radio_audio = new JRadioButton("Audio");

        encode_radio_image.setFocusable(false);
        encode_radio_video.setFocusable(false);
        encode_radio_audio.setFocusable(false);

        file_loc = new JLabel();
        file_loc2 = new JLabel();

        encode_radio_image2 = new JRadioButton("Image");
        encode_radio_text = new JRadioButton("Text");

        encode_radio_image2.setFocusable(false);
        encode_radio_text.setFocusable(false);

        encode_radio_text.setEnabled(false);
        encode_radio_image2.setEnabled(false);

        open_cover_media = new JButton("Open");
        open_cover_media.setFocusable(false);

        open_cover_media.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(open_media(1)){
                    show_source_option();
                }
            }
        });

        open_input_media = new JButton("Open");
        open_input_media.setFocusable(false);

        open_input_media.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_media(2);
            }
        });

        button_home = new JButton("HOME");
        button_home.setFocusable(false);
        button_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleHome();
            }
        });

        message_area = new JTextArea();
        message_area.setBackground(Color.white);

        encode_button = new JButton("ENCODE");
        encode_button.setFocusable(false);
        encode_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEncodeButton();
            }
        });

        encode_frame.setLayout(null);
        encode_frame.setSize(800, 600);
        encode_frame.setResizable(false);
        encode_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        encode_frame.setLocationRelativeTo(null);
        encode_frame.setVisible(true);
    }

    private void setEncodeElements() {
        encode_label.setBounds(125,100,600,35);
        background_label.setBounds(0, 0, encode_frame.getWidth(), encode_frame.getHeight());

        //encode_label.setBounds(175, 5, 300, 35);

        select_cover_label.setBounds(125, 150, 200, 35);
        encode_radio_image.setBounds(125, 200, 80, 20);
        encode_radio_video.setBounds(125, 250, 80, 20);
        encode_radio_audio.setBounds(125, 300, 80, 20);
        open_cover_media.setBounds(125, 350, 80, 20);
        file_loc.setBounds(125, 400, 500,20);
        button_home.setBounds(125,450,200,50);

        encode_radio_audio.addActionListener(this);
        encode_radio_video.addActionListener(this);
        encode_radio_image.addActionListener(this);

        select_input_label.setBounds(425, 150,  200, 35);
        encode_radio_image2.setBounds(425, 200, 80,20);
        encode_radio_text.setBounds(425, 250, 80,20);
        open_input_media.setBounds(425,300,80,20);
        file_loc2.setBounds(425,350,500,20);
        encode_button.setBounds(425,450,200,50);
        message_area.setBounds(425,300,300,100);

    }

    private void addEncodeElements() {
        encode_frame.add(encode_label);
        encode_frame.add(select_cover_label);

        encode_btn_group.add(encode_radio_image);
        encode_btn_group.add(encode_radio_audio);
        encode_btn_group.add(encode_radio_video);

        encode_frame.add(encode_radio_audio);
        encode_frame.add(encode_radio_image);
        encode_frame.add(encode_radio_video);

        encode_frame.add(open_cover_media);

        encode_frame.add(file_loc);

        encode_frame.add(select_input_label);

        encode_frame.add(encode_radio_image2);

        encode_frame.add(encode_radio_text);

        //encode_frame.add(open_input_media);

        encode_frame.add(file_loc2);

        //encode_frame.add(message_area);

        encode_frame.add(encode_button);

        encode_frame.add(button_home);
        encode_frame.getContentPane().add(background_label);
    }

    void initialiseEncode() {
        setEncodeElements();
        addEncodeElements();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encode_radio_audio || e.getSource() == encode_radio_video) {
            //open_cover_media.setBounds(125, 350, 80, 20);
            select_input_label.setText("Enter secret Message :");
            message_area.setBounds(425,200,300,100);
            encode_frame.remove(encode_radio_image2);
            encode_frame.remove(encode_radio_text);
            encode_frame.remove(open_input_media);


            encode_radio_image2.setSelected(false);
            encode_radio_text.setSelected(false);
            encode_frame.add(message_area);
            message_area.setText("");
            file_loc.setText("");
            file_loc2.setText("");
            encode_frame.repaint();
        }


        else if(e.getSource() == encode_radio_image){
            open_cover_media.setBounds(125, 350, 80, 20);
            select_input_label.setText("Select input media :");

            message_area.setText("");
            encode_frame.remove(message_area);

            encode_frame.add(encode_radio_image2);
            encode_frame.add(encode_radio_text);

            encode_frame.repaint();

        }

        else if(e.getSource() == encode_radio_image2)
        {
            encode_frame.remove(message_area);
            encode_frame.add(open_input_media);
        }
        else if(e.getSource() == encode_radio_text){
            select_input_label.setText("Enter secret Message :");
            message_area.setBounds(425,200,300,100);
            encode_frame.remove(open_input_media);
            encode_frame.remove(encode_radio_text);
            encode_frame.remove(encode_radio_image2);
            encode_frame.remove(file_loc2);
            encode_frame.add(message_area);
            message_area.setText("");
        }
        encode_frame.revalidate();
        encode_frame.repaint();

    }

    private boolean open_media(int a) {

        boolean flag = true ;
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(workingDirectory);
        int r = jfc.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            file_dir = jfc.getSelectedFile().getAbsolutePath();
        } else {
            file_dir = "Please select a file";

            flag = false ;
        }
        if(a==1)
            file_loc.setText(file_dir);
        if(a==2)
            file_loc2.setText(file_dir);

        return flag;

    }

    private void show_source_option(){

        if(encode_radio_image.isSelected()){
            ButtonGroup bg = new ButtonGroup();
            bg.add(encode_radio_image2);
            bg.add(encode_radio_text);

            encode_radio_image2.setEnabled(true);
            encode_radio_text.setEnabled(true);

            encode_radio_image2.addActionListener(this);
            encode_radio_text.addActionListener(this);

        }
    }

    void handleEncodeButton(){
        if(encode_radio_image.isSelected() && encode_radio_image2.isSelected()){
            ImageHider obj = new ImageHider();
            obj.hide(file_loc.getText(), file_loc2.getText(), "image_image_out.png");
            JOptionPane.showMessageDialog(encode_frame, imageInImageStego.Messages.IMAGE_HIDDEN_SUCCESSFULLY.getmessage());
            handleHome();
        }
        else if(encode_radio_image.isSelected() && encode_radio_text.isSelected()){
            TextSteganography.encode(file_dir,message_area.getText(),getSecretKey());
            JOptionPane.showMessageDialog(encode_frame,"Image Encoded Successfully");
            handleHome();
        }
        else if(encode_radio_video.isSelected()){
            try {
                Path inpfile = Paths.get(file_loc.getText());
                RC4 rc4 =  new RC4(getSecretKey().getBytes());
                FLVCrypto flv= new FLVCrypto();
                byte[] inputFileBytes = IO.readFileBytes(inpfile);
                byte[] encryptedMessage = rc4.encrypt(message_area.getText().getBytes());
                byte[] outputFileBytes = flv.embed(inputFileBytes,encryptedMessage) ;
                Path outfile = Paths.get("./video_text_out.flv");
                IO.writeFileBytes(outfile, outputFileBytes);
                JOptionPane.showMessageDialog(encode_frame,"Video Encoded successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(encode_radio_audio.isSelected()){
            File inp_file = new File(file_loc.getText());
            File out_file = new File("audio_text_out.wav");
            try {
                AudioSteganography.encodeMessage(inp_file, out_file, message_area.getText());
                JOptionPane.showMessageDialog(encode_frame,"Audio Encoded Successfully");
            } catch (NotEnoughSpaceException e) {
                System.out.println(e);
            } catch (SecretMessageException e) {
               System.out.println(e);
            }
        }
    }

    String getSecretKey(){
        return JOptionPane.showInputDialog(encode_frame, "Enter your Secret Key");
    }

    void handleHome(){
        encode_frame.dispose();
        Home obj = new Home();
        obj.initialiseHome();
    }
}
