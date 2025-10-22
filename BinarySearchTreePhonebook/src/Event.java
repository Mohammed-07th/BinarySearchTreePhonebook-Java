import java.util.Date;

public class Event<T extends Comparable<Event>> {
	private String EventTitle;
	private String EventLocation;
	private Date EventDateAndTime;
	private String ContactAppointmentOrEvent;

	public Event(String eventTitle, String eventLocation, Date eventDateAndTime, String contactAppointmentOrEvent) {
		super();
		EventTitle = eventTitle;
		EventLocation = eventLocation;
		EventDateAndTime = eventDateAndTime;
		ContactAppointmentOrEvent = contactAppointmentOrEvent;

	}

	public String getEventTitle() {
		return EventTitle;
	}

	public void setEventTitle(String eventTitle) {
		EventTitle = eventTitle;
	}

	public String getEventLocation() {
		return EventLocation;
	}

	public void setEventLocation(String eventLocation) {
		EventLocation = eventLocation;
	}

	public Date getEventDateAndTime() {
		return EventDateAndTime;
	}

	public void setEventDateAndTime(Date eventDateAndTime) {
		EventDateAndTime = eventDateAndTime;
	}

	public String getContactAppointmentOrEvent() {
		return ContactAppointmentOrEvent;
	}

	public void setContactAppointmentOrEvent(String contactAppointmentOrEvent) {
		ContactAppointmentOrEvent = contactAppointmentOrEvent;
	}

	public int compareTo(Event data) {
		return this.getEventTitle().compareToIgnoreCase((data).getEventTitle());
	}

}
