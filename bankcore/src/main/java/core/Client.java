package core;

public class Client  {
    private Integer id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;

    public Client() {
    }
    public Client(Client c) {
        this.id = c.getId();
        this.firstName = c.getFirstName();
        this.lastName = c.getLastName();
        this.pesel = c.getPesel();
        this.email = c.getEmail();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel + ", email=" + email + '}';
    }
    
}
