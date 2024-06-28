package domain.enums;

public enum UserType {
	MEMBER {
		public String toString() {
			return "Member";
		}
	},
	VOLUNTEER {
		public String toString() {
			return "Volunteer";
		}
	}
}
