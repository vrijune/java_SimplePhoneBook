package simplephonebook;

import java.util.*;

/**
 * Complete this class for adding names and phone numbers to a collection.
 *
 * @author wyu861.
 */
public class SimplePhoneBook {
    private Map<String, Integer> phoneBook; // contact numbers

    public SimplePhoneBook() {
        this.phoneBook = new TreeMap<String, Integer>();
    }


    // 2a. Adds multiple contacts to the phone book collection
    public void addMultipleContacts(Map<String, Integer> multipleContacts) {
        for (Map.Entry<String, Integer> contact : multipleContacts.entrySet()) {
            phoneBook.put(contact.getKey(), contact.getValue());
        }
    }

//        Map<String, Integer> map = new HashMap<>();
//        map.put("test", (Integer) new Object());
//        map.put("test1", (Integer) new Object());
//
//        Set<Map.Entry<String, Integer>> set = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> it = set.iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Integer> entry = it.next();
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println(key + "=" + value);
//
//        }
//        return (List) multipleContacts;
//    }


    //     2b. Adds a single contact to the phone book collection
    public void addNewContact(String name, int number) {


        Integer num = phoneBook.get(name);
        if (num != null) {
            System.out.printf(" Current number for %s is %d.%n", name, number);
        }
        phoneBook.put(name, number);
        System.out.printf("Number for %s is %d.%n", name, number);
    }
//        String newName = null;
//        Integer newNumber = 0;
//
//        map.put(newName, newNumber);
//        Set<String> set = map.keySet();
//        for (String key : set) {
//            if (map.containsKey(name)) {
//                System.out.println("Current number for\n" + name + "is" + number);
//            } else System.out.println("Number for" + newName + "is" + newNumber);
//        }


    // 2c. Prints the number for a given name
    public void printNumberFor(String name) {

        Integer num = phoneBook.get(name);
        if (num != null) {
            System.out.printf("Current number for %s is %d.%n", name, num);
        } else {
            System.out.println("The person " + name + " does not exist in the phone book.");
        }

    }
//        Map<String, Integer> map = new HashMap<>();
//        for (String key : map.keySet()) {
//            if (map.containsKey(name)) {
//                Integer value = map.get(key);
//                System.out.println("Current number for" + name + "is" + value);
//            } else {
//                System.out.println("The person" + name + " does not exist in the phone book");
//            }
//
//        }
//
//    }


    // 2d. Prints the total number of entries of the phone book collection
    public void printTotalNumberOfEntries() {
        System.out.println("There are " + phoneBook.size() + " entries in the phone book.");
    }


    //        Scanner sc = new Scanner(System.in);
//        int totalNumber = 0;
//        for (int i = 0; i < 10; i++) {
//            totalNumber += sc.nextInt();
//        }
//        System.out.println("There are" + totalNumber + "entries in the phone book");
//        sc.close();


    // 2e. Prints the name for a given phone number
    public void printNameByPhoneNumber(int phoneNumber) {
        for (Map.Entry<String, Integer> contact : phoneBook.entrySet()) {
            if (contact.getValue() == phoneNumber) {
                System.out.println("The person with the number " + phoneNumber + " is: " + contact.getKey() + ".");
                return;
            }
        }
        System.out.println("Cannot find person in the phone book with the number " + phoneNumber + ".");
    }
}





//        String key = "";
//        TreeMap<String, Integer> map = new TreeMap<>();
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            if (phoneNumber==(entry.getValue())) {
//                key = entry.getKey();
//            }
//
//        }
//
//        return key;

