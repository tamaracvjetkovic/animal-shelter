package domain.enums;

public enum RequestType {
	ADOPTION {
		public String toString() {
			return "ADOPTION";
		}
	},
	TEMPORARY_CARE {
		public String toString() {
			return "TEMPORARY CARE";
		}
	},
	VOLUNTEERING {
		public String toString() {
			return "VOLUNTEERING";
		}
	},
	ANIMAL_REGISTRATION {
		public String toString() {
			return "ANIMAL REGISTRATION";
		}
	},
	POST_EDITING {
		public String toString() {
			return "POST EDITING";
		}
	}
}
