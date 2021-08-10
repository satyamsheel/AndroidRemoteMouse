package DataUtils;

public  class QueueItem implements Comparable<QueueItem>{
	public Object getmObject() {
		return mObject;
	}

	public void setmObject(Object mObject) {
		this.mObject = mObject;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setType(int type) {
		mType = type;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	Object mObject;
	long timestamp;
	int mType;
	public QueueItem(Object obj, long ts, int type) {
		mObject = obj;
		timestamp = ts;
		mType = type;
	}

	public QueueItem() {
	}

	@Override
	public int compareTo(QueueItem item) {
		if(timestamp < item.timestamp)
			return -1;
		else if(timestamp > item.timestamp) 
			return 1;
		return 0;
	}

	public String toString() {
		return mObject.toString() + ";" + timestamp;
	}

    public Object getObject() {
		return mObject;
    }
}