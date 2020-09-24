package guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;

public class JoinerTest {

    private final List<String> stringList = Arrays.asList("Hello", "Guava", "Java", "Kafka");

    private final List<String> stringListWithNullValue = Arrays.asList("Hello", "Guava", null, "Kafka");

    private final String targetFileName = "D:\\guava_test.txt";

    private final Map<String, String> map = ImmutableMap.of("Hello", "Guava", "Java", "Kafka");

    private final Map<String, String> mapWithNullValue = ImmutableMap.of("Hello", "Guava", "Java", null);


    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        MatcherAssert.assertThat(result, equalTo("Hello#Guava#Java#Kafka"));
    }

    @Test
    public void testJoinOnJoinWithNullValue() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        MatcherAssert.assertThat(result, equalTo("Hello#Guava#Java"));
    }

    @Test
    public void testJoinOnJoinWithNullValueUseForNull() {
        String result = Joiner.on("#").useForNull("default").join(stringListWithNullValue);
        MatcherAssert.assertThat(result, equalTo("Hello#Guava#Java#default"));
    }

    @Test
    public void testJoinOnAppendToStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder builder = Joiner.on("#").appendTo(stringBuilder, stringList);

        MatcherAssert.assertThat(builder.toString(), equalTo("Hello#Guava#Java#Kafka"));
        MatcherAssert.assertThat(stringBuilder, sameInstance(builder));
    }

    @Test
    public void testJoinOnAppendToFileWriter() {
        try (FileWriter fileWriter = new FileWriter(targetFileName)) {
            Joiner.on("#").appendTo(fileWriter, stringList);
            MatcherAssert.assertThat(Files.isFile().test(new File(targetFileName)), is(true));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testJoinOnJoinSkipNullWithStream() {
        String result = stringListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(Collectors.joining("#"));
        MatcherAssert.assertThat(result, equalTo("Hello#Guava#Java"));
    }

    @Test
    public void testJoinOnJoinUseForNullWithStream() {
        String result = stringListWithNullValue.stream().map(item -> item != null && !item.isEmpty() ? item : "default").collect(Collectors.joining("#"));
        MatcherAssert.assertThat(result, equalTo("Hello#Guava#Java#default"));
    }

    @Test
    public void testJoinOnJoinForMap() {
        String result = Joiner.on("#").withKeyValueSeparator("=").join(map);
        MatcherAssert.assertThat(result, equalTo("Hello=Guava#Java=Kafka"));
    }
}
