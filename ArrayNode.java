package problem6_37;

public class ArrayNode<T> {
	protected T info;
	protected int next;
	protected static final int NUL = -1;
	
	public ArrayNode(T info) {
		this.info = info;
		next = NUL;
	}
	public ArrayNode() {
		this.info = null;
		next = NUL;
	}
	
	public void setInfo(T info) {this.info = info;}
	public T getInfo() {return info;}
	public void setIndex(int index) {this.next = index;}
	public int getIndex() {return next;}

}
