package domain.enums;

public enum UserState {
	BLACKLISTED {
		public String toString() {
			return "BlackListed";
		}
	},
	VOLUNTEER {
		public String toString() {
			return "Volunteer";
		}
	},
	MEMBER {
		public String toString() {
			return "Member";
		}
	},
	DELETED {
		public String toString() {
			return "Deleted";
		}
	}
}
