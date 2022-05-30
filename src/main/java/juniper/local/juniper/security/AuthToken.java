package juniper.local.juniper.security;

public interface AuthToken <T> {
    String AUTHORITIES_KEY = "role";
    boolean validate();
    T getData();
}
