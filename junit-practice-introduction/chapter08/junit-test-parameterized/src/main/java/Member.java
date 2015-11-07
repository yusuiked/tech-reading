public class Member {
    public static boolean canEntry(int age, Gender gender) {
        if (gender == Gender.MALE) return false;
        if (25 < age)  return false;
        return true;
    }

    public enum Gender {
        MALE,
        FEMALE,
    }
}
