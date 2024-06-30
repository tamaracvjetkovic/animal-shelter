package domain.enums;

public enum MessageOwner {
    MEMBER {
        public String toString() {
            return "Member";
        }
    },
    ANIMALSHELTER {
        public String toString() {
            return "Animal shelter";
        }
    }
}
