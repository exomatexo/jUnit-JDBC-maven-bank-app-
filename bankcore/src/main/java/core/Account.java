package core;

public class Account {
    private Integer id;
    private String name;
    private String adress;
    private long  amount=0;

    public Account(String name, String address) {
        this.name=name;
        this.adress=address;
    }
    public Account(Account account){
        this.id = account.id;
        this.adress = account.adress;
        this.name = account.name;
    }
    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "["+id+","+name+","+adress+"]";
    }
}