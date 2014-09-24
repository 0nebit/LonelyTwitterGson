package ca.ualberta.cs.lonelytwitter;

public class Summary {

	private int num;
	private float lat;
	private int len;
	
	public Summary(int number, float latency, int length)
	{
		this.num = number;
		this.lat = latency;
		this.len = length;
	}
	
	public int get_num() {
		return this.num;
	}
	
	public float get_lat() {
		return this.lat;
	}
	
	public int get_len() {
		return this.len;
	}
}
