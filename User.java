// Abstract User Class
public abstract class User
{
    protected String username;
    protected String userID;
    protected String name;
    protected String password;
    protected String role;  // Vendor, Customer, DeliveryRunner, Administrator
    // email attribute for users needed or not?

    public User(String username, String userID, String name, String password, String role)
    {
        this.username = username;
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public abstract void login();

    public abstract void logout();

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

}