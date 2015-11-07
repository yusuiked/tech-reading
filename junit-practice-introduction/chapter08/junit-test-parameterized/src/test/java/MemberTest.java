import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class MemberTest {
    @DataPoints
    public static int[] AGES = {15, 20, 25, 30};
    @DataPoints
    public static Member.Gender[] GENDERS = Member.Gender.values();

    @Theory
    public void canEntryは25歳以下の女性の場合にtrueを返す(int age, Member.Gender gender) throws Exception {
        assumeTrue(age <= 25 && gender == Member.Gender.FEMALE);
        assertThat(Member.canEntry(age, gender), is(true));
    }
    @Theory
    public void canEntryは25歳以下の女性でない場合にfalseを返す(int age, Member.Gender gender) throws Exception {
        assumeTrue(25 < age || gender != Member.Gender.FEMALE);
        assertThat(Member.canEntry(age, gender), is(false));
    }
}
