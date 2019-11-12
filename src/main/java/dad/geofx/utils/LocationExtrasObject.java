package dad.geofx.utils;

public class LocationExtrasObject {

	public class Language {
		
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public Language[] languages;
	
	private String country_flag;
	
	public String getCountry_flag() {
		return country_flag;
	}

	public void setCountry_flag(String country_flag) {
		this.country_flag = country_flag;
	}

	public Language[] getLanguages() {
		return languages;
	}

	public void setLanguages(Language[] languages) {
		this.languages = languages;
	}

	public String calling_code;


	public String getCalling_code() {
		return calling_code;
	}

	public void setCalling_code(String calling_code) {
		this.calling_code = calling_code;
	}
}
