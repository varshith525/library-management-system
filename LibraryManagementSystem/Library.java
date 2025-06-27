import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added: " + book.getTitle());
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("✅ User registered: " + user.getName());
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null && !book.isIssued()) {
            book.setIssued(true);
            user.borrowBook(book);
            System.out.println("✅ Book issued to " + user.getName());
        } else {
            System.out.println("❌ Issue failed. Book may already be issued or not found.");
        }
    }

    public void returnBook(int bookId, int userId) {
        User user = findUser(userId);
        if (user != null) {
            Book bookToReturn = null;
            for (Book b : user.getBorrowedBooks()) {
                if (b.getId() == bookId) {
                    bookToReturn = b;
                    break;
                }
            }
            if (bookToReturn != null) {
                bookToReturn.setIssued(false);
                user.returnBook(bookToReturn);
                System.out.println("✅ Book returned.");
            } else {
                System.out.println("❌ User has not borrowed this book.");
            }
        } else {
            System.out.println("❌ User not found.");
        }
    }

    public void showAvailableBooks() {
        for (Book b : books) {
            if (!b.isIssued()) {
                System.out.println(b);
            }
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }
}
