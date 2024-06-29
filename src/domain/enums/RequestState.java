package domain.enums;

public enum RequestState {
	PENDING {
		public String toString() {
			return "Pending";
		}
	},
	APPROVED {
		public String toString() {
			return "Approved";
		}
	},
	REJECTED {
		public String toString() {
			return "Rejected";
		}
	}
}
