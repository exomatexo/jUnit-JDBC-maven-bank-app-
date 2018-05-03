import core.Account;
import core.Bank;
import core.BankImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankImplTest {
    private BankImpl bank= null;
    private Integer id1,id2;
    @Test
    public void createaccounttest(){
        assert id1!=null;
    }
    @Test
    public void createaccounttest1(){
        assert id1.equals(id2);
    }
    @Test(expected= Bank.AccountIdException.class)
    public void deposittest(){
        Integer id = 1000;
        bank.deposit(id, 1000);
    }
    @Test
    public void accountTest(){
        Account a = new Account("1","2");
        assert a.getAdress().equals("2");
        assert a.getName().equals("1");
    }
    @Test(expected= Bank.AccountIdException.class)
    public void getBalanceTest(){
        bank.getBalance(1000);
    }
    @Test(expected= Bank.AccountIdException.class)
    public void withdrawTest(){
        bank.withdraw(1000,200);
    }
    @Test(expected= Bank.InsufficientFundsException.class)
    public void withdrawTest2(){
        bank.deposit(id1,2000);
        bank.withdraw(id1,2500);
    }
    @Test(expected= Bank.AccountIdException.class)
    public void transferTest(){
        bank.transfer(id1,1000,200);
    }
    @Test(expected= Bank.AccountIdException.class)
    public void transferTest2(){
        bank.transfer(100,id2,200);
    }
    @Test(expected= Bank.AccountIdException.class)
    public void transferTest3(){
        bank.transfer(1000,1000,200);
    }
    @Test(expected= Bank.InsufficientFundsException.class)
    public void transferTest4(){
        bank.deposit(id1,2000);
        bank.transfer(id1,id2,2500);
    }
    @Before
    public void setUp(){
        bank = new BankImpl();
        id1 =bank.createAccount("","");
        id2=bank.createAccount("","");
    }
    @After
    public  void reset(){
        bank=null;
        id1 =null;
        id2=null;
    }
}