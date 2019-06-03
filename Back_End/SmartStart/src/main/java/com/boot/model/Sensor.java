package com.boot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "smartstart")
public class Sensor {
	  	@Id
	    private String _id;

	  	private String name;
	  	private String value;
	    private String ISODATA;
	    private long idInsurance;

	    public Sensor() {}

	    public Sensor(String name, String value, String ISODATA, long idInsurance) {
	        this.name = name;
	        this.value = value;
	        this.ISODATA = ISODATA;
	        this.idInsurance = idInsurance;
	    }

	    @Override
	    public String toString() {
	        return String.format(
	                "sensor[_id=%s, name='%s', value='%s', ISODATA='%s']",
	                _id, name, value, ISODATA);
	    }

		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void setISODATA(String ISODATA) {
			this.ISODATA = ISODATA;
		}

		public long getIdInsurance() {
			return idInsurance;
		}

		public void setIdInsurance(long idInsurance) {
			this.idInsurance = idInsurance;
		}
		
	    
	    

	}