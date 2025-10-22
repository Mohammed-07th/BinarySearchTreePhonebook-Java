import java.text.SimpleDateFormat;

public class BST<T> {
	BSTNode<T> root, current;

	public enum Relative {
		Root, Parent, LeftChild, RightChild
	};

	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public boolean findKey(String tKey) {
		current = findKeyRecursive(root, tKey); // Start the search from the root of the tree
		return current != null;// Return true if the key is found, false otherwise
	}

	private BSTNode<T> findKeyRecursive(BSTNode<T> node, String tKey) {
		if (node == null || node.key.equalsIgnoreCase(tKey)) {// Base case: If the current node is null or the key is
																// found at the current node, return the node
			return node;
		}
		// Compare the search key with the key of the current node
		int comparison = tKey.compareToIgnoreCase(node.key);
		if (comparison < 0) { // If the search key is less than the current node's key, continue the search in
								// the left subtree
			return findKeyRecursive(node.left, tKey);
		} else { // If the search key is greater than the current node's key, continue the search
					// in the right subtree
			return findKeyRecursive(node.right, tKey);
		}

	}

	public boolean insert(T val) {
		if (findKey(((Contact) val).getName()) || findContactByNumber(((Contact) val).getNumber())) {
			return false; // The contact already exists
		}

		root = insertRecursive(root, val); // Start the recursive insertion

		return true;
	}

	private BSTNode<T> insertRecursive(BSTNode<T> node, T val) {
		if (node == null) {
			return new BSTNode<T>(val); // Insert the new node at the correct position
		}

		// Compare based on the name of the contact
		if (((Contact) val).getName().compareTo(((Contact) node.data).getName()) < 0) {
			node.left = insertRecursive(node.left, val); // Go left
		} else {
			node.right = insertRecursive(node.right, val); // Go right
		}

		return node;
	}

	public boolean removeKey(String key) {
		if (empty()) { // Check if the tree is empty
			System.out.println("The tree is empty!");
			System.out.println();
			return false;
		}

		BooleanWrapper removed = new BooleanWrapper(false);// Create a BooleanWrapper to keep track of whether the key
															// is removed
		root = removeKeyRecursive(key, root, removed);// Call the recursive method to remove the key
		return removed.getValue();// Return the result of whether the key is removed
	}

	private BSTNode<T> removeKeyRecursive(String key, BSTNode<T> p, BooleanWrapper flag) {
		BSTNode<T> q, child = null;
		if (p == null) // Base case: If the current node is null, return null
			return null;

		// Compare the key with the key of the current node using p.data.getName()
		if (key.equalsIgnoreCase(((Contact) p.data).getName())) {
			flag.set(true); // Set the flag to true since the key is found
			if (p.left != null && p.right != null) { // // Case: Node with two children
				q = findMin(p.right);
				p.key = q.key; // Update the key
				p.data = q.data; // Update the data
				p.right = removeKeyRecursive(((Contact) q.data).getName(), p.right, flag);
			} else {
				if (p.right == null) // Case: Node with one child or no child
					child = p.left;
				else if (p.left == null)
					child = p.right;
				return child;
			}
		} else if (key.compareTo(((Contact) p.data).getName()) < 0)
			p.left = removeKeyRecursive(key, p.left, flag); // go left
		else
			p.right = removeKeyRecursive(key, p.right, flag); // go right

		return p;
	}

	private BSTNode<T> findMin(BSTNode<T> node) {
		while (node.left != null) { // Traverse to the leftmost node to find the minimum key
			node = node.left;
		}
		return node; // Return the node with the minimum key
	}

	public boolean findContactByNumber(String contactNumber) {
		return inOrderTraversalfindContactByNumber(root, contactNumber);
	}

	private boolean inOrderTraversalfindContactByNumber(BSTNode<T> node, String contactNumber) {
		if (node != null) {
			// Traverse the left subtree
			if (inOrderTraversalfindContactByNumber(node.left, contactNumber)) {
				return true; // Contact found in the left subtree, terminate the traversal
			}
			if (((Contact) node.data).getNumber().equalsIgnoreCase(contactNumber)) {
				// Found the contact with the specified contact number
				return true; // Terminate the traversal
			}
			// Traverse the right subtree
			return inOrderTraversalfindContactByNumber(node.right, contactNumber);
		}

		return false; // Contact not found
	}

	public void SearchByEmail(String Email) {
		inOrderTraversalSearchByEmail(root, Email);
	}

	private void inOrderTraversalSearchByEmail(BSTNode<T> node, String Email) {
		if (node != null) {
			// Traverse the left subtree
			inOrderTraversalSearchByEmail(node.left, Email);

			if (((Contact) node.data).getEmail().equalsIgnoreCase(Email)) {
				String dateFormatContact = "MM/dd/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
				System.out.println("Name: " + ((Contact) node.data).getName());
				System.out.println("Phone Number: " + ((Contact) node.data).getNumber());
				System.out.println("Email Address: " + ((Contact) node.data).getEmail());
				System.out.println("Address: " + ((Contact) node.data).getAddress());
				System.out.println("Birthday: " + dateFormat.format(((Contact) node.data).getBirtday()));
				System.out.println("Notes: " + ((Contact) node.data).getNotes());
			}

			// Traverse the right subtree
			inOrderTraversalSearchByEmail(node.right, Email);
		}
	}

	public void SearchByAddress(String Address) {
		inOrderTraversalSearchByAddress(root, Address);
	}

	private void inOrderTraversalSearchByAddress(BSTNode<T> node, String Address) {
		if (node != null) {
			// Traverse the left subtree
			inOrderTraversalSearchByAddress(node.left, Address);

			if (((Contact) node.data).getAddress().equalsIgnoreCase(Address)) {
				String dateFormatContact = "MM/dd/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
				System.out.println("Name: " + ((Contact) node.data).getName());
				System.out.println("Phone Number: " + ((Contact) node.data).getNumber());
				System.out.println("Email Address: " + ((Contact) node.data).getEmail());
				System.out.println("Address: " + ((Contact) node.data).getAddress());
				System.out.println("Birthday: " + dateFormat.format(((Contact) node.data).getBirtday()));
				System.out.println("Notes: " + ((Contact) node.data).getNotes());
			}

			// Traverse the right subtree
			inOrderTraversalSearchByAddress(node.right, Address);
		}
	}

	public void SearchByBirthday(String Birthday) {
		inOrderTraversalSearchByBirthday(root, Birthday);
	}

	private void inOrderTraversalSearchByBirthday(BSTNode<T> node, String Birthday) {
		if (node != null) {
			// Traverse the left subtree
			inOrderTraversalSearchByBirthday(node.left, Birthday);

			String dateFormatContact = "MM/dd/yyyy";// to make sure the date have the correct format
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
			String date = dateFormat.format(((Contact) node.data).getBirtday());
			if (Birthday.equalsIgnoreCase(date)) {
				System.out.println("Name: " + ((Contact) node.data).getName());
				System.out.println("Phone Number: " + ((Contact) node.data).getNumber());
				System.out.println("Email Address: " + ((Contact) node.data).getEmail());
				System.out.println("Address: " + ((Contact) node.data).getAddress());
				System.out.println("Birthday: " + dateFormat.format(((Contact) node.data).getBirtday()));
				System.out.println("Notes: " + ((Contact) node.data).getNotes());
			}

			// Traverse the right subtree
			inOrderTraversalSearchByBirthday(node.right, Birthday);
		}
	}

	public void PrintContactFirstName(String Firstname) {
		inOrderTraversalPrintByFirstName(root, Firstname);
	}

	public void inOrderTraversalPrintByFirstName(BSTNode<T> node, String firstName) {
		if (node != null) {
			// Traverse the left subtree
			inOrderTraversalPrintByFirstName(node.left, firstName);

			String[] firstname = ((Contact) node.data).getName().split(" ");
			if (firstName.equalsIgnoreCase(firstname[0])) {
				String dateFormatContact = "MM/dd/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
				System.out.println("Name: " + ((Contact) node.data).getName());
				System.out.println("Phone Number: " + ((Contact) node.data).getNumber());
				System.out.println("Email Address: " + ((Contact) node.data).getEmail());
				System.out.println("Address: " + ((Contact) node.data).getAddress());
				System.out.println("Birthday: " + dateFormat.format(((Contact) node.data).getBirtday()));
				System.out.println("Notes: " + ((Contact) node.data).getNotes());
			}

			// Traverse the right subtree
			inOrderTraversalPrintByFirstName(node.right, firstName);
		}
	}

	public void FindContactNumber(String Number) {
		inOrderTraversalFindNumber(root, Number);
	}

	public void inOrderTraversalFindNumber(BSTNode<T> node, String Number) {
		if (node != null) {
			// Traverse the left subtree
			inOrderTraversalFindNumber(node.left, Number);
			if (Number.equalsIgnoreCase(((Contact) node.data).getNumber())) {
				String dateFormatContact = "MM/dd/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
				System.out.println("Name: " + ((Contact) node.data).getName());
				System.out.println("Phone Number: " + ((Contact) node.data).getNumber());
				System.out.println("Email Address: " + ((Contact) node.data).getEmail());
				System.out.println("Address: " + ((Contact) node.data).getAddress());
				System.out.println("Birthday: " + dateFormat.format(((Contact) node.data).getBirtday()));
				System.out.println("Notes: " + ((Contact) node.data).getNotes());
			}

			// Traverse the right subtree
			inOrderTraversalFindNumber(node.right, Number);
		}
	}

	public boolean find(Relative rel) {
		switch (rel) {
		case Root: // Easy case
			current = root;
			return true;
		case Parent:
			if (current == root)
				return false;
			current = findparent(current, root);
			return true;
		case LeftChild:
			if (current.left == null)
				return false;
			current = current.left;
			return true;
		case RightChild:
			if (current.right == null)
				return false;
			current = current.right;
			return true;
		default:
			return false;
		}
	}

	public void PrintContactDetails() {// print the contact details
		String dateFormatContact = "MM/dd/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatContact);
		System.out.println("Name: " + ((Contact) current.data).getName());
		System.out.println("Phone Number: " + ((Contact) current.data).getNumber());
		System.out.println("Email Address: " + ((Contact) current.data).getEmail());
		System.out.println("Address: " + ((Contact) current.data).getAddress());
		System.out.println("Birthday: " + dateFormat.format(((Contact) current.data).getBirtday()));
		System.out.println("Notes: " + ((Contact) current.data).getNotes());
	}

	private BSTNode<T> findparent(BSTNode<T> p, BSTNode<T> t) {
		if (t == null)
			return null; // empty tree

		if (t.right == null && t.left == null)
			return null;
		else if (t.right == p || t.left == p)
			return t; // parent is t
		else {
			BSTNode<T> q = findparent(p, t.left);
			if (q != null)
				return q;
			else
				return findparent(p, t.right);
		}
	}

}
