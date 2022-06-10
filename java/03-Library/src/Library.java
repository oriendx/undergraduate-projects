import java.util.*;
import java.util.function.Predicate;

class Book implements Comparable<Book> {
    public String ISBN;
    public String title;
    public String author;
    public String catg;
    public int copiesTotal;
    public int copiesAvail;

    public Book(String isbn, String tit, String aut,
            String cat, int ct, int ca) {
        ISBN = isbn;
        title = tit;
        author = aut;
        catg = cat;
        copiesTotal = ct;
        copiesAvail = ca;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;

        Book b = (Book) o;

        if ((!"".equals(b.ISBN) && ISBN.equals(b.ISBN)) ||
                (title.equals(b.title) && author.equals(b.author))) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Book b) {
        return copiesTotal - b.copiesTotal;
    }

    @Override
    public String toString() {
        return String.format("%10s %20s %15s %10s %6d %6d", 
                ISBN, title, author, catg, copiesTotal, copiesAvail);
    }
}

public class assignment2_Library {
    private final List<Book> bookList;
    public assignment2_Library() {
        bookList = new ArrayList<>();
    }

    /*
     * @param b to add book
     */
    public boolean addBook(Book b) {
        if (!bookList.contains(b)) {
            bookList.add(b);
            return true;
        } else {
            return false; // duplicate book exist
        }
    }

    /**
     * @param b to delete book
     * @return int 0: delete success, N: books lent out, -1: no found
     */
    public int deleteBook(Book b) {
        for (Book book : bookList) {
            if ((!"".equals(b.ISBN) && book.ISBN.equals(b.ISBN)) ||
                (book.title.equals(b.title) && book.author.equals(b.author))) {
                // find the book to delete
                dispOneBook(book);

                if (book.copiesTotal == book.copiesAvail) {
                    bookList.remove(book);
                    return 0;
                } else {
                    return book.copiesTotal-book.copiesAvail; // book lent out cannot be deleted
                }
            }
        }

        return -1; // delete failed
    }

    /**
     * @param k key to search
     * @return 
     */
    public List<Book> searchBook(String k) {
        List<Book> result = new ArrayList<>();
        bookList.stream().filter((Book book) -> book.ISBN.contains(k)
                || book.title.contains(k)
                || book.author.contains(k)
                || book.catg.contains(k)).forEachOrdered((book) -> {
                    result.add(book);
        });

        Collections.sort(result);

        return result;
    }

    /**
     * @param isbn
     * @return 
     */
    public Book getToUpdBook(String isbn) {
        for (Book book : bookList) {
            if (!"".equals(isbn) && book.ISBN.equals(isbn)) {
                return book;
            }
        }

        return null; // update failed
    }

    public void dispOneBook(Book b) {
        System.out.println("Found book:");
        String header = String.format("%10s %20s %15s %10s    TOTAL_COPIES AVAILABLE_COPIES", 
                "ISBN", "TITLE", "AUTHOR", "CATEGORY");
        System.out.println(header);
        System.out.println(b);
    }

    public void sortBooks(String t) {
        if (t.equals("C")) {
            Collections.sort(bookList, (Book b1, Book b2) -> b1.catg.compareTo(b2.catg));
        } else if (t.equals("A")) {
            Collections.sort(bookList, (Book b1, Book b2) -> b1.author.compareTo(b2.author));
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void displayBooks(List<Book> list) {
        String header = String.format("%10s %20s %15s %10s    TOTAL_COPIES AVAILABLE_COPIES", 
                "ISBN", "TITLE", "AUTHOR", "CATEGORY");
        System.out.println(header);
        list.forEach((book) -> {
            System.out.println(book);
        });
    }

    public void prompt() {
        System.out.println("-------Welcome to The Library---------");
        System.out.println("Add - to add a new book");
        System.out.println("Update - to update book info");
        System.out.println("Search - to enquire about book info");
        System.out.println("Delete - to delete a book");
        System.out.println("Display - to display book(s) info");
        System.out.println("Quit - to exit from the current level of interactions");
    }

    public static void main(String[] args) {
        assignment2_Library booklib;
        booklib = new assignment2_Library();

        try (Scanner in = new Scanner(System.in)) {
            String isbn;
            String title;
            String author;
            String category;
            int total;
            int lent;
            
            OUTER:
            while (true) {
                booklib.prompt();
                System.out.println("--");
                System.out.print("Enter your command here(Enter 'Quit' at any time to exit from current level):");
                String opt = in.nextLine();
                switch (opt) {
                    case "Quit":
                        break OUTER;
                    case "Add":
                        {
                            System.out.println("Enter a new book ISBN: ");
                            isbn = in.nextLine();
                            System.out.println("ISBN: " + isbn + " Entered.");
                            System.out.println("Enter the title: ");
                            title = in.nextLine();
                            System.out.println("Title: " + title + " Entered.");
                            System.out.println("Enter the author: ");
                            author = in.nextLine();
                            System.out.println("Author: " + author + " Entered.");
                            System.out.println("Enter category: ");
                            category = in.nextLine();
                            System.out.println("Category: " + category + " Entered.");
                            System.out.println("Enter total copy number: ");
                            total = Integer.parseInt(in.nextLine());
                            System.out.println("Ready to add book: " + isbn+";"
                                    + title + ";" + author + ";" + category+";"
                                    + total+";" + total);
                            System.out.println("Enter Y to add the new book. Anything else to quit: ");
                            String o = in.nextLine();
                            if (o.equals("Y")) {
                                booklib.addBook(new Book(isbn, title, author, category, total, total));
                                System.out.println("New book added successfully");
                            }       break;
                        }
                    case "Update":
                        {
                            System.out.println("Enter ISBN: ");
                            isbn = in.nextLine();
                            Book b = booklib.getToUpdBook(isbn);
                            if (b != null) {
                                booklib.dispOneBook(b);
                                System.out.println("Enter type of information you want to update, 'T' for the title, 'A' for author, 'C' for category, 'TC' for total copy number, 'AC' for available number :");
                                String o = in.nextLine();
                                switch (o) {
                                    case "TC":
                                        {
                                            System.out.println("Enter your new total copy number: ");
                                            int ntc = Integer.parseInt(in.nextLine());
                                            b.copiesTotal = ntc;
                                            break;
                                        }
                                    case "T":
                                        System.out.println("Enter your new title: ");
                                        title = in.nextLine();
                                        b.title = title;
                                        break;
                                    case "A":
                                        System.out.println("Enter your new author: ");
                                        author = in.nextLine();
                                        b.author = author;
                                        break;
                                    case "C":
                                        System.out.println("Enter your new category: ");
                                        category = in.nextLine();
                                        b.catg = category;
                                        break;
                                    case "AC":
                                        {
                                            System.out.println("Enter your new lent number: ");
                                            int ntc = Integer.parseInt(in.nextLine());
                                            b.copiesAvail = b.copiesTotal - ntc;
                                            break;
                                        }
                                    default:
                                        break;
                                }
                            }       break;
                        }
                    case "Search":
                        while (true) {
                            String key;
                            System.out.println("Enter your keyword: ");
                            key = in.nextLine();
                            List<Book> ret = booklib.searchBook(key);
                            System.out.println("Found book(s): ");
                            booklib.displayBooks(ret);
                            System.out.println("Enter Y to search other books, anything else to quit: ");
                            String o = in.nextLine();
                            if (!o.equals("Y")) {
                                break;
                            }
                        }   break;
                    case "Delete":
                        {
                            System.out.println("Enter the book's ISBN or title+author: ");
                            String line = in.nextLine();
                            Book b;
                            int pos = line.indexOf("+");
                            if (pos > 0) {
                                b = new Book("", line.substring(0, pos),
                                        line.substring(pos+1), "", 0, 0);
                            } else {
                                b = new Book(line, "", "", "", 0, 0);
                            }       int left = 0;
                            if ((left = booklib.deleteBook(b)) > 0) {
                                System.out.println("Sorry this book cannot be deleted. There are " + left + " copies have been lent out.");
                            }       break;
                        }
                    case "Display":
                        {
                            System.out.println("Enter 'C' for displaying group by category, or 'A' for displaying group by author: ");
                            String o = in.nextLine();
                            booklib.sortBooks(o);
                            booklib.displayBooks(booklib.getBookList());
                            break;
                        }
                    default:
                        break;
                }
            }
        }
    }
}
