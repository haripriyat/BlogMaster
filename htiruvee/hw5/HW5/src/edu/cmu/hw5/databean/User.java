package edu.cmu.hw5.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class User {
	private String userName;
    private String password;
    private String firstName;
    private String lastName;

    
    public String getUserName()        { return userName; }
    public String getPassword()        { return password; }
    public String getFirstName()       { return firstName;}
    public String getLastName()        { return lastName; }
    

    
    public void setUserName(String s)  { userName = s;    }
    public void setPassword(String s)  { password = s;    }
    public void setFirstName(String s) { firstName = s;   }
    public void setLastName(String s)  { lastName = s;    }
}