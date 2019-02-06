package com.revature.models;

import java.sql.Date;
import java.time.LocalDateTime;

public class Requests {
	private String requestType, requestDesc; 
	private int requestID, empID, reqStatus, mgrID;
	private double requestAmount;
	private LocalDateTime requestDate;
	public Requests() {
		super();
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestDesc() {
		return requestDesc;
	}
	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getMgrID() {
		return mgrID;
	}
	public void setMgrID(int mgrID) {
		this.mgrID = mgrID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public int getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(int reqStatus) {
		this.reqStatus = reqStatus;
	}
	public double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public LocalDateTime getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDateTime date) {
		this.requestDate = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empID;
		result = prime * result + mgrID;
		result = prime * result + reqStatus;
		long temp;
		temp = Double.doubleToLongBits(requestAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((requestDesc == null) ? 0 : requestDesc.hashCode());
		result = prime * result + requestID;
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
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
		Requests other = (Requests) obj;
		if (empID != other.empID)
			return false;
		if (mgrID != other.mgrID)
			return false;
		if (reqStatus != other.reqStatus)
			return false;
		if (Double.doubleToLongBits(requestAmount) != Double.doubleToLongBits(other.requestAmount))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestDesc == null) {
			if (other.requestDesc != null)
				return false;
		} else if (!requestDesc.equals(other.requestDesc))
			return false;
		if (requestID != other.requestID)
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Requests [requestType=" + requestType + ", requestDesc=" + requestDesc + ", requestID=" + requestID
				+ ", mgrID=" + mgrID + ", empID=" + empID + ", reqStatus=" + reqStatus + ", requestAmount="
				+ requestAmount + ", requestDate=" + requestDate + "]";
	}
}
