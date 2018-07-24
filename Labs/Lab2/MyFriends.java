public class MyFriends {
  public static void main(String args[]) {

    SocialNetwork contacts = new SocialNetwork();

    contacts.add("Snoopy","Dog","snoopy@uwo.ca");
    contacts.add("Felix","Cat","felix@uwo.ca");
    contacts.add("Mickey","Mouse","mickey@uwo.ca");

    System.out.println(contacts.toString());
    System.out.println("I have " + contacts.getNumFriends() + " friends in my list.");
    
    if (contacts.remove("Snoopy", "Dog")) {
    	System.out.println("");
        System.out.println("Snoopy Dog was removed successfully");
    }
    
    else {
    	System.out.println("Error");
    }
    
    if (contacts.remove("Hasan", "Ahmed")) {
    	System.out.println("");
        System.out.println("Hasan Ahmed was removed successfully");
    }
    else {
    	System.out.println("Hasan Ahmed was not successful removed as user not in contacts");
    }
    System.out.println("");
    System.out.println(contacts.toString());
  }
}
