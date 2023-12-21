package hu.webler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Iterable<Book> {

    String author;
    String title;
    String publisher;
    int year;
    String id;
    Map<String, List<Book>> booksByCategory;

    private Map<String, List<Book>> booksByPublisher;

    private Map<String, List<Book>> getBooksByPublisher() {
        return booksByCategory.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Book::getPublisher));
    }

    @Override
    public Iterator<Book> iterator() {
        return new Iterator<Book>() {

            private final Iterator<Map.Entry<String, List<Book>>> iter = booksByCategory.entrySet().iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Book next() {
                var entry = iter.next();
                List<Book> booksInCategory = entry.getValue();
                // Assuming getActual is a method that returns a book from the list
                return getActual(booksInCategory);
            }
        };
    }

    // You need to provide the implementation for this method
    private Book getActual(List<Book> booksInCategory) {
        // Implement logic to return an actual book from the list
        return booksInCategory.get(0); // For example, returning the first book
    }

    @Override
    public void forEach(Consumer<? super Book> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Book> spliterator() {
        return Iterable.super.spliterator();
    }
}
