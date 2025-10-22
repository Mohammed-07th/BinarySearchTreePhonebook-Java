import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class linkedlistADT<T> {
	private Node<T> head;
	private Node<T> current;

	public linkedlistADT() {

		head = current = null;
	}

	public boolean SearchForConflict(String DateAndTime) {
		Node<T> tmp = head;
		String dateFormatEvent = "MM/dd/yyyy HH:mm";// to make the date in the right format
		SimpleDateFormat eventFormat = new SimpleDateFormat(dateFormatEvent);

		while (tmp != null) {
			Event event = (Event) tmp.data;

			// Check if event is null
			if (event != null) {
				Date eventDateAndTime = event.getEventDateAndTime();

				// Check if eventDateAndTime is null
				if (eventDateAndTime != null) {
					String eventDateAndTimeString = eventFormat.format(eventDateAndTime);

					if (eventDateAndTimeString.equalsIgnoreCase(DateAndTime)) {
						System.out.println("there already a Event or Appoinment at the same time");
						System.out.println();
						return true;
					}
				}
			}
			tmp = tmp.next;
		}
		return false;
	}

	public boolean searchEventOrAppointmentByTitleOrContactNameForAppointment(String TitleOrContactName) {
		// Searches for an event or appointment by Title or Contact Name for
		// appointments.
		if (empty()) {
			return false;
		}
		boolean foundEvent = false;
		Node<T> tmp = head;
		while (tmp != null) {
			Event event = (Event) tmp.data;
			if (event != null) {
				if ((TitleOrContactName.equalsIgnoreCase(event.getEventTitle())
						|| TitleOrContactName.equalsIgnoreCase(event.getContactAppointmentOrEvent()))) {
					foundEvent = true;
					PrintEvent(tmp.data);
				}
			}
			tmp = tmp.next;
		}
		return foundEvent;
	}

	// Searches for an event by Contact Name
	public boolean searchEventByContactName(String contactName) {
		if (empty()) {
			return false;
		}
		Node<T> tmp = head;
		boolean foundEvent = false;
		while (tmp != null) {
			Event event = (Event) tmp.data;
			String[] contactArray = event.getContactAppointmentOrEvent().split(",");
			// Check if the contact name exists in the event's contact list
			for (String contact : contactArray) {
				if (contact.trim().equalsIgnoreCase(contactName)) {
					// Display event details
					PrintEvent(tmp.data);
					foundEvent = true;
					break;
				}
			}
			// Move to the next event in the list
			tmp = tmp.next;
		}
		if (foundEvent) {// we make the foundEvent varible for if we have many event that have the same
			// Contact Name
			return true;
		}
		return false;
	}

	public void AddEventOrAppointment(T event) {
		Node<T> newEvent = new Node<T>(event);

		if (empty()) {
			// check if the list is empty; if yes, add the new Event at the start
			head = current = newEvent;
			System.out.println();
			System.out.println("Event scheduled successfully!");
			System.out.println();
			return;
		}

		if (((Event) event).compareTo((Event) head.data) <= 0) {
			// check if the Event should be before the head event
			newEvent.next = head;
			head = newEvent;
			System.out.println();
			System.out.println("Event scheduled successfully!");
			System.out.println();
			return;
		}

		Node<T> previous = null;
		Node<T> current = head;

		// Traverse the list to find the correct position for the New Event based on
		// title and using compareTo
		while (current != null && ((Event) event).compareTo((Event) current.data) > 0) {
			previous = current;
			current = current.next;
		}

		// Insert the new Event at the correct position in the list
		newEvent.next = current;
		previous.next = newEvent;
		System.out.println();
		System.out.println("Event scheduled successfully!");
		System.out.println();
	}

	public void DeleteAppointment(String ContactName) {// if we delete a contact the event for it should be deleted
														// that why we may get the contact name or number
		if (empty()) {// check if it empty if yes then there nothing to delete
			return;
		}
		Node<T> tmp = head;
		findFirst();// move the current to the head because the deletion is by remove() method
		while (tmp != null) {
			Event event = (Event) tmp.data;
			if (event.getContactAppointmentOrEvent().equalsIgnoreCase(ContactName)) {
				remove();//remove the current node
				return;
			}
			tmp = tmp.next;
			if (tmp != null) {
				findNext();//move the current
			}
		}

	}

	public void DeleteEvent(String contactName) {
		if (empty()) {// check if it empty if yes then there nothing to delete
			return;
		}
		Node<T> tmp = head;
		findFirst();// move the current to the head because the deletion is by remove() method
		while (tmp != null) {
			Event event = (Event) tmp.data;
			String[] contactArray = event.getContactAppointmentOrEvent().split(",");

			// Create a StringBuilder to build the modified contact list for the event
			StringBuilder modifiedContactList = new StringBuilder();

			// Iterate over the contacts and append them to the result, excluding the
			// contact to delete
			for (String contact : contactArray) {
				if (!contact.trim().equalsIgnoreCase(contactName)) {
					modifiedContactList.append(contact).append(",");
				}
			}

			// Remove the trailing comma if there are contacts in the result
			if (modifiedContactList.length() > 0) {
				modifiedContactList.deleteCharAt(modifiedContactList.length() - 1);
			}

			// Set the modified contact list for the event
			event.setContactAppointmentOrEvent(modifiedContactList.toString());

			// If the event no longer has contacts, delete it from the list
			if (modifiedContactList.length() == 0) {
				remove();
				return;
			} else {
				// Move to the next event in the list
				findNext();
				tmp = tmp.next;
			}
		}

	}

	public void PrintEvent(T event) {// print event details
		String dateFormatEvent = "MM/dd/yyyy HH:mm";
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatEvent);
		Event PrintEvent = (Event) event;
		System.out.println("Event title: " + PrintEvent.getEventTitle());
		System.out.println("Contact name: " + PrintEvent.getContactAppointmentOrEvent());
		System.out.println(
				"Event date and time (MM/DD/YYYY HH:MM): " + dateFormat.format(PrintEvent.getEventDateAndTime()));
		System.out.println("Event location: " + PrintEvent.getEventLocation());
	}

	public void PrintEventAlphabetically() {// print all event alphabeticallly
		if (empty()) {
			System.out.println("the Event and Appointment list is empty");
			System.out.println();
		}
		String dateFormatEvent = "MM/dd/yyyy HH:mm";
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatEvent);
		Node<T> tmp = head;
		findFirst();
		while (tmp != null) {
			System.out.println("Event title: " + ((Event) retrieve()).getEventTitle());
			System.out.println("Contact name: " + ((Event) retrieve()).getContactAppointmentOrEvent());
			System.out.println("Event date and time (MM/DD/YYYY HH:MM): "
					+ dateFormat.format(((Event) retrieve()).getEventDateAndTime()));
			System.out.println("Event location: " + ((Event) retrieve()).getEventLocation());
			System.out.println();
			findNext();
			tmp = tmp.next;
		}
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getCurrent() {
		return current;
	}

	public void setCurrent(Node<T> current) {
		this.current = current;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public T retrieve() {

		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public void insert(T val) {
		Node<T> tmp;
		if (empty()) {
			head = current = new Node<T>(val);
		} else {
			tmp = current.next;
			current.next = new Node<T>(val);
			current = current.next;
			current.next = tmp;
		}
	}

	public void remove() {
		if (empty()) {
			return;
		}

		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;

			while (tmp.next != null && tmp.next != current)
				tmp = tmp.next;

			if (tmp.next != null)
				tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;

	}

	public boolean full() {

		return false;
	}

	public boolean last() {

		return current.next == null;
	}

	public boolean empty() {

		return head == null;
	}

}
