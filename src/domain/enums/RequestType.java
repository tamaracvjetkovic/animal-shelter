package domain.enums;

public enum RequestType {
	ADOPTION {
		public String toString() {
			return "Adoption";
		}
	},
	TEMPORARY_CARE {
		public String toString() {
			return "Temporary Care";
		}
	},
	VOLUNTEERING {
		public String toString() {
			return "Volunteering";
		}
	},
	ANIMAL_REGISTRATION {
		public String toString() {
			return "Animal Registration";
		}
	},
	POST_EDITING {
		public String toString() {
			return "Post Editing";
		}
	}
}
