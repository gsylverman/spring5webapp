package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData  implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author eric = new Author("Eric", "Land");
        Book someBook = new Book("Programming Book", "12133131");

        Publisher publisher = new Publisher();
        publisher.setName("publisher X");
        publisher.setCity("NY");
        publisher.setState("NY");

        eric.getBooks().add(someBook);
        someBook.getAuthors().add(eric);
        someBook.setPublisher(publisher);

        publisherRepository.save(publisher);

        publisher.getBooks().add(someBook);

        authorRepository.save(eric);
        bookRepository.save(someBook);

        Author john = new Author("John", "Evans");
        Book bookXs = new Book("XS", "12123");
        publisher.getBooks().add(bookXs);

        john.getBooks().add(bookXs);
        bookXs.getAuthors().add(john);

        authorRepository.save(john);
        bookRepository.save(bookXs);


        System.out.println("from guru.springframework.spring5webapp.bootstrap.............");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }
}
