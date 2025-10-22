public class BSTNode<T> {
	public String key;
	public T data;
	public BSTNode<T> left, right;

	public BSTNode( T val) {
		key = ((Contact) val).getName();
		data = val;
		left = right = null;
	}

	public BSTNode( T val, BSTNode<T> l, BSTNode<T> r) {
		key = ((Contact) val).getName();
		data = val;
		left = l;
		right = r;
	}
}
