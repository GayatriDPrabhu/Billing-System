/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author AAA
 */
public class dbconnect {
    
    private Connection con;
    private Statement st;
    private Connection con1;
    private Statement st1;
    private ResultSet rs;
    
    public dbconnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/item","root","");
            st=con.createStatement();
            con1=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cust","root","");
            st1=con1.createStatement();
            }
        catch(Exception ex){
            System.out.println(ex);
        }
            
    }
        
    public int getCount(String db){
        int c=0;
        try
        {
            String sq="Select count(Iid) from "+db+";";
            rs=st.executeQuery(sq);
            while(rs.next())
            {
                c=Integer.parseInt(rs.getString("count(Iid)"));
            }
        }
        catch(SQLException s)
        {
            System.out.println("error");
        }
        catch(Exception e)
        {
            
        }
        return c;
    }
    
    public ResultSet getResult(String query) {
     // System.out.println(query);
        try {
            st = con.createStatement();
            ResultSet res = st.executeQuery(query);
            return res;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Vector<Vector<String>> getdata(String q,int c) {
     Vector<Vector<String>> s=new Vector<Vector<String>>();
     int i=0;
        try {
            rs=st.executeQuery(q);
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                s.add(new Vector<String>());
                for(int j=0;j<c;j++)
                  s.get(i).add(rs.getString(j+1));
                i++;
                
            }  
        } 
        catch (SQLException ex) {
        }
        return s;
    }
    
    public int getid(String s)
    {
        int x=0;
    try {
            rs=st1.executeQuery("select * from cust.details where Name='"+s+"'");
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=Integer.parseInt(rs.getString("Id"));
            }  
        } 
        catch (SQLException ex) {
        }  
        return x;
    }
    
    public String gettn(String s)
    {
        String x="";
    try {
            rs=st1.executeQuery("select * from cust.details where Name='"+s+"'");
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=rs.getString("TN");
            }  
        } 
        catch (SQLException ex) {
        }  
        return x;
    }
    
    public String getad(String s)
    {
        String x="";
    try {
            rs=st1.executeQuery("select * from cust.details where Name='"+s+"'");
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=rs.getString("Addr");
            }  
        } 
        catch (SQLException ex) {
        }  
        return x;
    }
    
    public int getMrp(int n)
    {
        int x=0;
    try {
            rs=st.executeQuery("select * from item.idetails where Iid="+n);
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=Integer.parseInt(rs.getString("Mrp"));
            }  
        } 
        catch (SQLException ex) {
        }  
        return x; 
    }
    
     public int getOP(int n)
    {
        int x=0;
    try {
            rs=st.executeQuery("select * from item.idetails where Iid="+n);
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=Integer.parseInt(rs.getString("OurPrice"));
            }  
        } 
        catch (SQLException ex) {
        }  
        return x; 
    }
    
    public String getn(int n)
    {
        String x=null;
    try {
            rs=st.executeQuery("select * from item.idetails where Iid="+n);
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                x=rs.getString("Iname");
            }  
        } 
        catch (SQLException ex) {
        }  
        return x; 
    } 
     
    public Vector<Vector<String>> getdata1(int c) {
     Vector<Vector<String>> s=new Vector<Vector<String>>();
     int i=0;
        try {
            rs=st1.executeQuery("Select * from cust.details");
        } 
        catch (SQLException ex) {
        }
        try {
            while(rs.next())
            {
                s.add(new Vector<String>());
                for(int j=0;j<c;j++)
                  s.get(i).add(rs.getString(j+1));
                i++;
                
            }  
        } 
        catch (SQLException ex) {
        }
        return s;
    }
    
    public int getId()
    {
        int x=0;
        try {
            rs=st.executeQuery("select * from cust.details");
        } 
        catch (SQLException ex) {
        }
         try {
            while(rs.next())
            {
                x=Integer.parseInt(rs.getString("Id"));
            }  
        } 
        catch (SQLException ex) {
        }
        x++;
        return x;
    }
    
    
    public void setCust(int x,String name,String tn,String addr)
    {
      try{
        st1.executeUpdate("insert into cust.details values("+x+",'"+name+"','"+tn+"','"+addr+"')");
        }
        catch(SQLException e){
        }  
    }
    
  public static void main(String args[]){  
      dbconnect d=new dbconnect();
         int y=d.getCount("item.idetails");
      System.out.println(y);
      Vector<Vector<String>> s=d.getdata("Select * from item.idetails", 4);
        System.out.println(s.get(0).get(1));
        dbconnect v=new dbconnect();
        System.out.println(v.getn(16));
      
        
        }
            
  
}
