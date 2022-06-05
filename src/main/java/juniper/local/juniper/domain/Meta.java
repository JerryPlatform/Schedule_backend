package juniper.local.juniper.domain;

import java.util.Arrays;

public interface Meta {
    String getDescription();

    default String[] constants() {
        return (String[]) Arrays.stream(this.getClass().getEnumConstants()).map(Meta::getDescription).toArray((x$0) -> {
            return new String[x$0];
        });
    }
}
