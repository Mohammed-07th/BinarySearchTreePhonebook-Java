DESCRIPTION:
This project implements a Phonebook system using two main data structures:
1. BST Class (Phonebook BST) for managing contacts.
2. LinkedListADT Class for managing events and appointments.

BST CLASS METHODS (for Contact Management):
- findKey(String tKey): Search by key. O(log n).
- insert(T val): Insert a new contact. O(log n).
- removeKey(String key): Remove a contact by key. O(log n).
- Linear Searches (e.g., findContactByNumber, SearchByEmail, SearchByAddress, SearchByBirthday, PrintContactFirstName): All use in-order traversal and have a time complexity of O(N).

LINKEDLISTADT CLASS METHODS (for Event/Appointment Management):
- SearchForConflict(String DateAndTime): Checks for time conflicts. O(N).
- searchEventOrAppointmentByTitleOrContactNameForAppointment(String TitleOrContactName): Searches for events/appointments. O(N).
- searchEventByContactName(String contactName): Searches for an event. O(NM)[cite: 34, 35].
- AddEventOrAppointment(T event): Insert-sorts a new item. O(N)[cite: 36, 37].
- DeleteAppointment(String ContactName) / DeleteEvent(String contactName): Removal by contact name. O(NM).
- PrintEventAlphabetically(): Prints all events and appointments in alphabetical order. O(N).
- PrintEvent(T event): Prints event/appointment details. O(1).

PHONEBOOK CLASS METHODS (User Interface/Main Operations):
- addContact(): Takes user input, validates, and inserts into BST. O(log n).
- AddEvent(): Takes user input, validates, and inserts into LinkedList. O(N).
- AddAppointment(): Takes user input, validates, and inserts into LinkedList. O(N).
