import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBook {
	static BST<Contact> ContactBST = new BST<Contact>();
	static linkedlistADT<Event> Eventlist = new linkedlistADT<Event>();
	static Scanner input = new Scanner(System.in);
	static String dateFormatContact = "MM/dd/yyyy";// the date format we want for contact
	static String dateFormatEvent = "MM/dd/yyyy HH:mm";// the date format we want for event
	static SimpleDateFormat contactFormat = new SimpleDateFormat(dateFormatContact);
	static SimpleDateFormat eventFormat = new SimpleDateFormat(dateFormatEvent);
	static String OnlyNumbers = "^[0-9]+$";// to make sure the number for the contact should be only numbers no

	public void addContact() {
		Date contactBirthday;
		System.out.print("Enter the contact's name:");
		String ConsumeLeftovers = input.nextLine();// we use consume leftovers because there a chance that nextLine not
													// be working
		String contactName = input.nextLine();
		System.out.print("Enter the contact's phone number:");
		String contactNumber = input.next();
		if (!(contactNumber.matches(OnlyNumbers))) {// here we check if the user enter numbers for contact number if not
			// we return the user to the first interface
			System.out.println("invalid input please try again only with numbers");
			System.out.println("-----------------------");
			return;
		}
		System.out.print("Enter the contact's email address:");
		String contactEmail = input.next();
		System.out.print("Enter the contact's address:");
		String ConsumeLeftovers2 = input.nextLine();// we use consume leftovers because there a chance that nextLine not
													// be working
		String contactAddress = input.nextLine();
		System.out.print("Enter the contact's birthday:");
		String contactBirthdayBeta = input.next();// we first take the user input for the birthday as String and check
													// if it have the correct format we are using if not return him to
													// the interface menu
		try {
			contactBirthday = contactFormat.parse(contactBirthdayBeta);

		} catch (ParseException e) {
			System.err.println("invalid date try again (MM/DD/YYYY)");
			System.out.println("-----------------------");
			return;
		}
		System.out.print("Enter any notes for the contact:");
		String ConsumeLeftovers3 = input.nextLine();// we use consume leftovers because there a chance that nextLine not
													// be working
		String contactNotes = input.nextLine();
		Contact NewContact = new Contact(contactName, contactNumber, contactEmail, contactAddress, contactBirthday,
				contactNotes);
		if (!ContactBST.insert(NewContact)) {
			System.out.println("Contact is already added!");
			System.out.println();
		} else {
			System.out.println("Contact added successfully!");
			System.out.println();
		}
		return;

	}

	public void AddEvent() {
		Date eventDateAndTime;
		System.out.print("Enter Event title:");
		String ConsumeLeftovers = input.nextLine();// we use consume leftovers because there a chance that nextLine not
		// be working
		String eventTitle = input.nextLine();
		System.out.print("Enter contacts name separated by a comma:");
		String eventContacts = input.nextLine();
		String[] contactArray = eventContacts.split(",");
		for (int i = 0; i < contactArray.length; i++) {
			if (!ContactBST.findKey(contactArray[i])) {// here we check before making the event that there is a contact
														// with the name the user enterd
				System.out.println("there no contact with name " + contactArray[i]);
				return;
			}

		}
		System.out.print("Enter Event date and time (MM/dd/yyyy HH:mm): ");
		String eventDateBeta = input.nextLine();// we first take the user input for the Event Date and Time as String
												// and checkif it have the correct format we are using if not return him
												// tothe interface menu
		try {
			eventDateAndTime = eventFormat.parse(eventDateBeta);
		} catch (ParseException e) {
			System.err.println("invalid date try again (MM/dd/yyyy HH:mm)");
			System.out.println("-----------------------");
			return;
		}
		String eventDateAndTimeString = eventFormat.format(eventDateAndTime);
		if (Eventlist.SearchForConflict(eventDateAndTimeString)) {// here we check if we have conflict with the event //
																	// time and date
			return;
		}
		System.out.print("Enter Appointment location:");
		String eventLocation = input.nextLine();
		Event NewEvent = new Event(eventTitle, eventLocation, eventDateAndTime, eventContacts);
		Eventlist.AddEventOrAppointment(NewEvent);
		return;
	}

	public void AddAppointment() {
		Date eventDateAndTime;
		System.out.print("Enter Appointment title:");
		String ConsumeLeftovers = input.nextLine();// we use consume leftovers because there a chance that nextLine not
		// be working
		String eventTitle = input.nextLine();
		System.out.print("Enter Contact name:");
		String eventContact = input.nextLine();
		if (!ContactBST.findKey(eventContact)) {// here we check before making the event that there is a contact with //
												// the name the user enterd
			System.out.println("there no contact with name " + eventContact);
			return;
		}
		System.out.print("Enter Appointment date and time (MM/dd/yyyy HH:mm): ");
		String eventDateBeta = input.nextLine();// we first take the user input for the Event Date and Time as String
												// and check
		// if it have the correct format we are using if not return him to
		// the interface menu
		try {
			eventDateAndTime = eventFormat.parse(eventDateBeta);
		} catch (ParseException e) {
			System.err.println("invalid date try again (MM/dd/yyyy HH:mm)");
			System.out.println("-----------------------");
			return;
		}
		String eventDateAndTimeString = eventFormat.format(eventDateAndTime);
		if (Eventlist.SearchForConflict(eventDateAndTimeString)) {// here we check if we have conflict with the event
																	// time and date
			return;
		}
		System.out.print("Enter Appointment location:");
		String eventLocation = input.nextLine();
		Event NewEvent = new Event(eventTitle, eventLocation, eventDateAndTime, eventContact);
		Eventlist.AddEventOrAppointment(NewEvent);
		return;
	}

	public void Start() {
		System.out.println("Welcome to the BST Phonebook!");
		int choice;
		do {
			try {
				System.out.println("Please choose an option:");
				System.out.println("1. Add a contact");
				System.out.println("2. Search for a contact");
				System.out.println("3. Delete a contact");
				System.out.println("4. Schedule an event/appointment");
				System.out.println("5. Print event details");
				System.out.println("6. Print contacts by first name");
				System.out.println("7. Print all events alphabetically");
				System.out.println("8. Exit");
				System.out.println();
				System.out.print("Enter your choice: ");
				choice = input.nextInt();
				System.out.println();
				System.out.println();
				switch (choice) {
				case 1:
					addContact();
					break;
				case 2:
					int searchContact = 0;
					System.out.println("Enter search criteria:");
					System.out.println("1. Name");
					System.out.println("2. Phone Number");
					System.out.println("3. Email Address");
					System.out.println("4. Address ");
					System.out.println("5. Birthday ");
					System.out.println();
					System.out.print("Enter your choice:");
					searchContact = input.nextInt();
					System.out.println();
					switch (searchContact) {
					case 1:
						System.out.print("Enter the contact's name:");
						String ConsumeLeftovers = input.nextLine();
						String contactName = input.nextLine();
						System.out.println();
						if (ContactBST.findKey(contactName))
							ContactBST.PrintContactDetails();
						else
							System.out.println("There is not contact with name: " + contactName);
						break;

					case 2:
						System.out.print("Enter the contact's Number:");
						String ConsumeLeftovers2 = input.nextLine();
						String ContactNumber = input.nextLine();
						System.out.println();
						ContactBST.FindContactNumber(ContactNumber);
						break;
					case 3:
						System.out.print("Enter the contact's Email:");
						String ConsumeLeftovers22 = input.nextLine();
						String ContactEmail = input.nextLine();
						System.out.println();
						ContactBST.SearchByEmail(ContactEmail);
						break;
					case 4:
						System.out.print("Enter the contact's Address:");
						String ConsumeLeftovers222 = input.nextLine();
						String ContactAddress = input.nextLine();
						System.out.println();
						ContactBST.SearchByAddress(ContactAddress);
						break;
					case 5:
						System.out.print("Enter the contact's Birthday:");
						String ConsumeLeftovers2222 = input.nextLine();
						String ContactBirthday = input.nextLine();
						System.out.println();
						ContactBST.SearchByBirthday(ContactBirthday);
						break;
					}
					break;
				case 3:
					System.out.print("Enter the contact's name:");
					String ConsumeLeftoversDelete = input.nextLine();
					String DeleteContact = input.nextLine();
					System.out.println();
					boolean found = ContactBST.removeKey(DeleteContact);
					if (found) {
						System.out.println("Contact Deleted successfully!");
					}
					if (found) {
						Eventlist.DeleteAppointment(DeleteContact);
						Eventlist.DeleteEvent(DeleteContact);
					}
					break;
				case 4:
					int AddEventOrappointment = 0;
					System.out.println("Enter type:");
					System.out.println("1. event");
					System.out.println("2. appointment");
					System.out.println();
					System.out.print("Enter your choice: ");
					AddEventOrappointment = input.nextInt();
					switch (AddEventOrappointment) {
					case 1:
						AddEvent();
						break;
					case 2:
						AddAppointment();
						break;
					}
					break;
				case 5:
					int searchEvent = 0;
					System.out.println("Enter search criteria:");
					System.out.println("1. contact name");
					System.out.println("2. Event tittle");
					System.out.println();
					System.out.print("Enter your choice:");
					searchEvent = input.nextInt();
					switch (searchEvent) {
					case 1:
						System.out.print("Enter the contact name:");
						String ConsumeLeftoversSearchEvent = input.nextLine();
						String ContactName = input.nextLine();
						if (Eventlist.searchEventOrAppointmentByTitleOrContactNameForAppointment(ContactName)
								|| Eventlist.searchEventByContactName(ContactName))
							;
						else {
							System.out.println("There is no Event Or Appointment with " + ContactName);
							System.out.println();
						}
						break;
					case 2:
						System.out.print("Enter the Event Or Appointment title:");
						String ConsumeLeftovers2 = input.nextLine();
						String EventTilte = input.nextLine();
						if (!Eventlist.searchEventOrAppointmentByTitleOrContactNameForAppointment(EventTilte))
							System.out.println("There is no Event Or Appointment called " + EventTilte);
						System.out.println();
						break;
					}

					break;

				case 6:
					if (!ContactBST.empty()) {
						System.out.print("Enter the first name:");
						String ConsumeLeftoversFisrtname = input.nextLine();
						String contactFirstName = input.nextLine();
						ContactBST.PrintContactFirstName(contactFirstName);
					} else
						System.out.println("the tree is empty!!");
					System.out.println();
					break;
				case 7:
					Eventlist.PrintEventAlphabetically();
					break;
				case 8:
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("invalid input try again");
					System.out.println("-----------------------");
					break;

				}

			} catch (InputMismatchException e) {
				// here we Handle the exceptions that may be a mismatch (invalid input)
				System.out.println("invalid input try again");
				System.out.println("-----------------------");
				// Clear the input from the scanner because that may cause some problem
				input.nextLine();
				choice = -1; // Set choice to an invalid value to ensure the loop continues
			}
		} while (choice != 8);

	}
}
