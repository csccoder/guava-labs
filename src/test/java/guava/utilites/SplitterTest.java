package guava.utilites;

import com.google.common.base.Splitter;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link com.google.common.base.Splitter} test cases
 *
 * @author chensicong
 * @date 2020-09-25 23:39
 **/
public class SplitterTest {

    @Test
    public void testSplitOnSplit() {
        List<String> result = Splitter.on("|").splitToList("Hello|World");
        assertThat(result, IsNull.notNullValue());
        assertThat(result.get(0), IsEqual.equalTo("Hello"));
        assertThat(result.get(1), IsEqual.equalTo("World"));
    }

    @Test
    public void testSplitOnSplitOmitEmptyString() {
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("Hello|World|||");
        assertThat(result.size(), IsEqual.equalTo(2));
        assertThat(result.get(0), IsEqual.equalTo("Hello"));
        assertThat(result.get(1), IsEqual.equalTo("World"));
    }

    @Test
    public void testSplitOnSplitTrim() {
        List<String> result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("Hello | World|   | |");
        assertThat(result.size(), IsEqual.equalTo(2));
        assertThat(result.get(0), IsEqual.equalTo("Hello"));
        assertThat(result.get(1), IsEqual.equalTo("World"));
    }

    @Test
    public void testSplitOnSplitLimit() {
        List<String> result = Splitter.on("|").limit(3).splitToList("java|js|css|rocketmq|dubbo");
        assertThat(result.size(), IsEqual.equalTo(3));
        assertThat(result.get(0), IsEqual.equalTo("java"));
        assertThat(result.get(1), IsEqual.equalTo("js"));
        assertThat(result.get(2), IsEqual.equalTo("css|rocketmq|dubbo"));
    }

    @Test
    public void testSplitFixedLength() {
        List<String> result = Splitter.fixedLength(2).splitToList("abcde");
        assertThat(result.size(), IsEqual.equalTo(3));
        assertThat(result.get(0), IsEqual.equalTo("ab"));
        assertThat(result.get(1), IsEqual.equalTo("cd"));
        assertThat(result.get(2), IsEqual.equalTo("e"));
    }

    @Test
    public void testSplitOnSplitMap() {
        Map<String, String> map = Splitter.on('&').omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("hello= world& java=kafka&&");
        assertThat(map.size(), IsEqual.equalTo(2));
        assertThat(map.containsKey("hello"), IsEqual.equalTo(true));
        assertThat(map.containsKey("java"), IsEqual.equalTo(true));
        System.out.println(map);
    }

    @Test
    public void testSplitOnPattern() {
        List<String> result = Splitter.onPattern("\\|").splitToList("java|js|css|rocketmq|dubbo");
        assertThat(result.size(), IsEqual.equalTo(5));
    }

    @Test
    public void testSplitPattern() {
        List<String> result = Splitter.on(Pattern.compile(("\\|"))).splitToList("java|js|css|rocketmq|dubbo");
        assertThat(result.size(), IsEqual.equalTo(5));
    }
}
