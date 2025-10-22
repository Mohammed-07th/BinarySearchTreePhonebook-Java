import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Contact<T extends Comparable<Contact>> {
	private String name;
	private String number;
	private String email;
	private String address;
	private Date birtday;
	private String notes;

	public Contact(String name, String number, String email, String address, Date birtday, String notes) {
		super();
		this.name = name;
		this.number = number;
		this.email = email;
		this.address = address;
		this.birtday = birtday;
		this.notes = notes;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirtday() {
		return birtday;
	}

	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int compareTo(Contact contact) {
		return this.getName().compareToIgnoreCase((contact).getName());
	}

}