package phonebook;

abstract public class DAO {
    public static String EDIT_RESULT="Edit success";
    public static String DELETE_RESULT="Delete success";
    abstract public void addAnEntry(String name, String number);
    abstract public String searchPhoneNumber(String name);
    abstract public String editData(String name,String number);
    abstract public String deleteData(String name);
}
