package edu.univdhaka.iit.appclub.calllog;



import java.io.Serializable;
import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

public class CallHistory implements Comparator<CallHistory>, Comparable<CallHistory>, Parcelable, Serializable{
	
	public CallHistory() {
		super();
	}
	private String name;
	private String number;
	private String duration;
	private int d;
	 public CallHistory(String number, int d) {
		super();
		this.number = number;
		this.d = d;
	}
	boolean selected = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CallHistory(String name, String number, String duration) {
		super();
		this.name = name;
		this.number = number;
		this.duration = duration;
	}
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CallHistory(String number, String duration) {
		super();
		this.number = number;
		this.duration = duration;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@Override
	public int compareTo(CallHistory dur) {
		return 0;
		// TODO Auto-generated method stub
		
	}
	@Override
	public int compare(CallHistory d1, CallHistory d2) {
		// TODO Auto-generated method stub
		return d1.duration.compareTo(d2.duration);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallHistory other = (CallHistory) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	

}
