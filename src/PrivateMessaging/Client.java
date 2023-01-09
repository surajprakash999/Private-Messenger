//here we use all java basic concept of opps or java swing and awt and also socket programmig
//socket programming ---> Socket programming shows how to use socket APIs to
//establish communication links between remote and local processes
package PrivateMessaging;

import static PrivateMessaging.Server.f;
import static PrivateMessaging.Server.formatLabel;
import static PrivateMessaging.Server.vertical;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;// java extended package
import javax.swing.border.*;

public class Client implements ActionListener {// frame from swing package 
    
    JTextField text;//global declaration
    
    static JPanel a1;// global declaration of pannel to write message in it
    
    static Box vertical = Box.createVerticalBox();
    
    static  JFrame f = new JFrame();
    
    static DataOutputStream dout;
    
    Client(){// after making class object there will be a constructor call 
        
        f.setLayout(null);
        
       JPanel p1 = new JPanel();
        p1.setBackground(new Color(19,194,184));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);//use image class if image is small
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
    // use image class to scale image to good looks    
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);//if not show image as it was added in frame not in pannel p1
        
        //back button click action ...mouse event
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
           // setVisible(false);
           System.exit(0);
            
        }
    });
        
        //profile pic import
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
    // use image class to scale image to good looks    
        Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,5,60,60);
        p1.add(profile);
        
        //2 more images
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        
        JLabel video = new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        
        JLabel phone = new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        
        //one more image
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(270,20,310,25);
        p1.add(morevert);
        
        //write in frame ..ie main use of jlabel in frame to write 
        JLabel name = new JLabel("Fresher");
        name.setBounds(120,15,150,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        p1.add(name);
        
        // active now status
        JLabel status = new JLabel("Online");
        status.setBounds(122,38,150,18);
        status.setForeground(Color.red);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        p1.add(status);
        

        //down message box pannel
         a1 = new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);

        //writing message 
         text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.HANGING_BASELINE,15));
        f.add(text);
        

        //adding button beside text input field
        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,74,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);
                

        //frame
        f.setSize(450,700); //frame size
        
        f.setLocation(700,10);
        f.setUndecorated(true);// to remove upper heading border or faltu heading bar
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
       try{ 
        String out = text.getText();
        //System.out.println(out);
        
        //JLabel output = new JLabel(out);// tis will be written inside the function we created below
        
        JPanel p2 = formatLabel(out); // direct calling so removed new JPanel(); here 
        //p2.add(output);
        
        a1.setLayout(new BorderLayout());// to place image in top botton left right and center 
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);// adding p2 instead of out directly and string and string is not compatible
        vertical.add(right);//vericle alligning
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical,BorderLayout.PAGE_START);// frame needed to be reloaded to show message so require frame obj to reload ie JFrame
        
        
        dout.writeUTF(out);//to send message server also
        
        
        text.setText("");// to make text empty when ever event is performed by user
        
                
        
        f.repaint();
        f.invalidate();
        f.validate();// reassigning after wards whenever we send messages
   
       }catch(Exception e){
           e.printStackTrace();
       }
    }
     
    // now to assign all messages in a box  so assigning function to it
    
    public static JPanel formatLabel(String out){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width:100px\">"+ out + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,20));
        output.setBackground(new Color(137,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        //for time below the message we can use calender class
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time .setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        
        return panel;
    }
    
    public static void main (String[] args){
        new Client();//announnoumous object
        
        try {
            Socket s = new Socket("127.0.0.1",6001);
            
            DataInputStream din = new DataInputStream(s.getInputStream());
                
                dout = new DataOutputStream(s.getOutputStream());
            while(true){
                
                    a1.setLayout(new BorderLayout());
                    String msg = din .readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    
                    vertical.add(left);
                    
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    
                    f.validate();
                }
                
                
                
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
    }
}
