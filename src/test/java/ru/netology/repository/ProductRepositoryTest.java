package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Windows 11", 500, "Bill");
    Product second = new Smartphone(2, "samsung", 20000, "Korea");
    Product third = new Smartphone(3, "samsung", 15000, "Korea");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFoundId() {
        assertThrows(NotFoundException.class, () -> {
                    repository.removeById(5);
        });

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third};

        assertArrayEquals(expected, actual);
    }
}
