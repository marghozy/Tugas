import java.util.ArrayList;

public class Network {
    private ArrayList<User> users;
    private ArrayList<Post> posts;

    public Network() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public void addUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);
    }

    public void addPost(String content, User author) {
        Post newPost = new Post(content, author);
        posts.add(newPost);
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void displayPosts() {
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
