import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (!isContactDuplicate(contact)) {
            contacts.add(contact);
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Contact with the same name already exists.");
        }
    }

    private boolean isContactDuplicate(Contact contact) {
        for (Contact existingContact : contacts) {
            if (existingContact.getName().equalsIgnoreCase(contact.getName())) {
                return true;
            }
        }
        return false;
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        System.out.println("Contact removed successfully.");
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Save contact data to a file using JSON format
    public void saveContactsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
            System.out.println("Contacts saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    // Load contact data from a file using JSON format
    public void loadContactsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            contacts.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
            System.out.println("Contacts loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        boolean running = true;

        while (running) {
            System.out.println("\nADDRESS BOOK SYSTEM");
            System.out.println("1. Add new contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Display all contacts");
            System.out.println("4. Remove a contact");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Load contacts from file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();

                    if (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
                        System.out.println("Error: Name, phone number, and email address cannot be empty.");
                    } else {
                        Contact newContact = new Contact(name, phoneNumber, emailAddress);
                        addressBook.addContact(newContact);
                    }
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    Contact searchedContact = addressBook.searchContact(searchName);
                    if (searchedContact != null) {
                        System.out.println("Contact found: " + searchedContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.println("All Contacts:");
                    addressBook.displayAllContacts();
                    break;
                case 4:
                    System.out.print("Enter name of the contact to remove: ");
                    String removeName = scanner.nextLine();
                    Contact contactToRemove = addressBook.searchContact(removeName);
                    if (contactToRemove != null) {
                        addressBook.removeContact(contactToRemove);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter filename to save contacts: ");
                    String saveFilename = scanner.nextLine();
                    addressBook.saveContactsToFile(saveFilename);
                    break;
                case 6:
                    System.out.print("Enter filename to load contacts from: ");
                    String loadFilename = scanner.nextLine();
                    addressBook.loadContactsFromFile(loadFilename);
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the Address Book System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
