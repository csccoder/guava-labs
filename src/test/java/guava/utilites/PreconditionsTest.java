package guava.utilites;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.List;

/**
 * {@link com.google.common.base.Preconditions} Test
 *
 * @author chensicong
 * @date 2020-09-26 22:42
 **/
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void preconditionOnCheckNotNull() {
        List list = ImmutableList.of();
        Preconditions.checkNotNull(list);
    }

    @Test
    public void preconditionOnCheckNotNullWithErrorMessage() {
        try {
            List list = null;
            Preconditions.checkNotNull(list, "list must be null");
        } catch (Exception ex) {
            System.out.println("here");
            MatcherAssert.assertThat(ex.getClass(), IsEqual.equalTo(NullPointerException.class));
            MatcherAssert.assertThat(ex.getMessage(), IsEqual.equalTo("list must be null"));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void preconditionOnCheckArguments() {
        try {
            List list = ImmutableList.of("hello", "world");
            Preconditions.checkArgument(list.size() > 5, "list current size is %s , > 5", list.size());
        } catch (Exception ex) {
            MatcherAssert.assertThat(ex.getMessage(), IsEqual.equalTo("list current size is 2 , > 5"));
            throw ex;
        }
    }

    @Test
    public void preconditionOnCheckState() {
        try {
            int status = 1;
            Preconditions.checkState(status != 0, "status error");
        } catch (Exception ex) {
            System.out.printf("here");
            MatcherAssert.assertThat(ex.getClass(), IsEqual.equalTo(IllegalStateException.class));
            MatcherAssert.assertThat(ex.getMessage(), IsEqual.equalTo("status error"));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void preconditionOnCheckElementIndex() {
        try {
            List<String> list = ImmutableList.of("hello", "world");
            Preconditions.checkElementIndex(5, list.size());
        } catch (Exception ex) {
            MatcherAssert.assertThat(ex.getClass(), IsEqual.equalTo(IndexOutOfBoundsException.class));
            MatcherAssert.assertThat(ex.getMessage(), IsEqual.equalTo("index (5) must be less than size (2)"));
            throw ex;
        }
    }

    @Test(expected = AssertionError.class)
    public void jdk8Assert() {
        try {
            int i = 2;
            assert i > 3 : "啦啦啦";
        } catch (Error error) {
            MatcherAssert.assertThat(error.getClass(), IsEqual.equalTo(IndexOutOfBoundsException.class));
            MatcherAssert.assertThat(error.getMessage(), IsEqual.equalTo("啦啦啦"));
            throw error;
        }
    }
}
