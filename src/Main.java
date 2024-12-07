import java.util.*;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
        System.out.println("Book removed: " + title);
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public int countBooks() {
        return books.size();
    }
}

class BankAccount {
    private String accountNumber;
    private String owner;
    private double balance;
    private boolean isBlocked;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.isBlocked = false;
    }

    public void deposit(double amount) {
        if (!isBlocked && amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Account is blocked or invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (!isBlocked && balance >= amount && amount > 0) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or account is blocked.");
        }
    }

    public void blockAccount() {
        isBlocked = true;
        System.out.println("Account " + accountNumber + " is now blocked.");
    }

    public void unblockAccount() {
        isBlocked = false;
        System.out.println("Account " + accountNumber + " is now unblocked.");
    }

    @Override
    public String toString() {
        return "BankAccount{accountNumber='" + accountNumber + "', owner='" + owner + "', balance=" + balance + ", isBlocked=" + isBlocked + "}";
    }
}

class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accountNumber, String owner, double balance) {
        if (!accounts.containsKey(accountNumber)) {
            BankAccount newAccount = new BankAccount(accountNumber, owner, balance);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created: " + newAccount);
        } else {
            System.out.println("Account already exists.");
        }
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {

        fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred " + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
        } else {
                System.out.println("Invalid account(s) for transfer.");
        }
                }
                }

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

class Company {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }



    public double getTotalSalary() {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

    public double getAverageSalary() {
        if (employees.isEmpty()) return 0;
        return getTotalSalary() / employees.size();
    }
}


class MainApp {
    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("Библиотека");
        System.out.println("-----------------------------------------------------");

        Library library = new Library();
        library.addBook(new Book("1984", "George Orwell", 1949));
        library.addBook(new Book("Animal Farm", "George Orwell", 1945));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));

        System.out.println("Books by George Orwell:");
        for (Book book : library.searchByAuthor("George Orwell")) {
            System.out.println(book);
        }

        library.removeBook("1984");
        System.out.println("Total books in library: " + library.countBooks());

        Book foundBook = library.searchByTitle("Animal Farm");
        if (foundBook != null) {
            System.out.println("Found book: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }

        System.out.println("\n-----------------------------------------------------");
        System.out.println("Банк");
        System.out.println("-----------------------------------------------------");

        Bank bank = new Bank();
        bank.createAccount("12345", "John Doe", 1000.0);
        bank.createAccount("67890", "Jane Smith", 2000.0);

        BankAccount johnsAccount = bank.findAccount("12345");
        BankAccount janesAccount = bank.findAccount("67890");

        if (johnsAccount != null) johnsAccount.deposit(500);
        if (janesAccount != null) janesAccount.withdraw(300);

        bank.transfer("12345", "67890", 200);

        if (johnsAccount != null) johnsAccount.blockAccount();
        if (johnsAccount != null) johnsAccount.withdraw(100);

        // Работа с компанией
        System.out.println("\n-----------------------------------------------------");
        System.out.println("Компания");
        System.out.println("-----------------------------------------------------");

        Company company = new Company();
        company.addEmployee(new Employee("Alice", "Engineering", 1200));
        company.addEmployee(new Employee("Bob", "Marketing", 1000));
        company.addEmployee(new Employee("Charlie", "Engineering", 1500));

        System.out.println("Total salary: " + company.getTotalSalary());
        System.out.println("Average salary: " + company.getAverageSalary());
        System.out.println("Total salary after removal: " + company.getTotalSalary());
        System.out.println("Average salary after removal: " + company.getAverageSalary());
    }
}
// Я не зміг зробити в різних классах навіть у чата гпт питав дивився форум нічого не допомагає тому ось так ....
//git init
//git add .
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/ProBeUSs/domaska12121212.git
//git push -u origin main