import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialGenerics {
  void testConfidentialMap() {
    Map<String, @Confidential String> map = new HashMap<>();
    @Confidential String secret = "password";
    map.put("key", secret);

    @Confidential String retrieved = map.get("key");

    // :: error: [assignment]
    @NonConfidential String leaked = map.get("key");
  }

  void testNonConfidentialMap() {
    Map<String, String> map = new HashMap<>();
    @Confidential String secret = "password";

    // :: error: [argument]
    map.put("key", secret);

    @NonConfidential String safe = map.get("key");
  }

  void testConfidentialSet() {
    Set<@Confidential String> set = new HashSet<>();
    @Confidential String secret = "token";
    set.add(secret);
  }

  void testNonConfidentialSet() {
    Set<String> set = new HashSet<>();
    @Confidential String secret = "token";

    // :: error: [argument]
    set.add(secret);
  }

  void testConfidentialList() {
    List<@Confidential String> list = new ArrayList<>();
    @Confidential String secret = "ssn";
    list.add(secret);

    @Confidential String retrieved = list.get(0);

    // :: error: [assignment]
    @NonConfidential String leaked = list.get(0);
  }

  void testConfidentialKeys() {
    Map<@Confidential String, String> map = new HashMap<>();
    @Confidential String confKey = "secret-key";
    map.put(confKey, "value");
  }
}
