package domain.enums;

public enum AnimalState {
	ADOPTED{
		public String toString() {
			return "Adopted";
		}
	},NOTADOPTED{
		public String toString() {
			return "Not adopted";
		}
	},INFOSTERCARE{
		public String toString() {
			return "In foster care";
		}
	},UNDERTREATMENT{
		public String toString() {
			return "Under treatment";
		}
	}
}
