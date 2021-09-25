package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData  implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author eric = new Author("Eric", "Land");
        Book someBook = new Book("Programming Book", "12133131");

        eric.getBooks().add(someBook);
        someBook.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(someBook);

        Author john = new Author("John", "Evans");
        Book bookXs = new Book("XS", "12123");

        john.getBooks().add(bookXs);
        bookXs.getAuthors().add(john);

        authorRepository.save(john);
        bookRepository.save(bookXs);


        System.out.println("from guru.springframework.spring5webapp.bootstrap.............");
//        System.out.println("Number of books: " + bookRepository.count());
    }
}
