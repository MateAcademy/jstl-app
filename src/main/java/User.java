import java.util.Objects;

public class User {
    private String name;
    private String login;

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "У нас есть пользователь:{" +
                "имя='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login);
    }
}

