package org.example.gui;

import org.example.Main;
import org.example.TypesOfBooks;
import org.example.books.AudiobookFormat;
import org.example.books.Book;
import org.example.books.BookFactory;
import org.example.books.EbookFormat;
import org.example.user.Admin;
import org.example.user.Customer;
import org.example.user.Role;
import org.example.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFrame extends JFrame implements ActionListener {

    JComboBox comboBox;
    JButton button;
    JButton returnButton;
    JButton returnButton2;
    JButton addNewBook;
    JButton viewAllBookButtonAdmin;


    Admin currentUserAdmin;
    Customer currentUserCustomer;

    JButton paperbackButton;
    JButton ebookButton;
    JButton audiobookButton;

    JTextField barcodeField;
    JTextField titleField;
    JTextField languageField;
    JTextField genreField;
    JTextField quantityField;
    JTextField retailPriceField;
    JTextField noOfPagesField;
    JComboBox conditionBox;
    JComboBox dateCombobox;
    JButton addPaperbackButton;
    JButton addEbookButton;
    JComboBox formatBox;

    JTextField listeningLengthField;

    JButton addAudioBookButton;
    JButton viewAllBookButtonCustomer;
    JButton shoppingBasketButton;
    JButton searchFilterButton;

    JTextField barcodeFilterField;
    JButton barcodeFilterButton;
    JTextField listeningLengthFilterField;
    JButton listeningLengthFilterButton;
    JComboBox booksOptionComboBox;

    JButton addToBasketButton;
    JButton resetMenu;
    JButton checkoutButton;
    JButton clearBasketButton;
    JPanel paperbackBookPanel;

    JButton logoutButton;

    List<String> globalUsersStringList;
    List<String> globaluserNameStringList;

    public LoginFrame(List<String> usersStringList, List<String> userNameStringList) {

        mainFrame(usersStringList,userNameStringList);

    }

    public void mainFrame(List<String> usersStringList, List<String> userNameStringList) {

        this.setTitle("Book Shop");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        globalUsersStringList = usersStringList;
        globaluserNameStringList = userNameStringList;

        JLabel logInText = new JLabel();
        logInText.setText("Choose which user you would like to log in as");
        logInText.setVerticalAlignment(JLabel.CENTER);
        logInText.setHorizontalAlignment(JLabel.CENTER);

        button = new JButton();
        button.setText("Log In");
        button.addActionListener(this);



        comboBox = new JComboBox(userNameStringList.toArray());
        comboBox.setSize(100, 50);


        JPanel panel = new JPanel();

        panel.add(logInText);
        panel.add(comboBox);
        panel.add(button);
        panel.setBackground(Color.blue);

        this.add(panel);
        this.pack();
        this.setSize(1500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void adminFrame(Admin currentUser) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        viewAllBookButtonAdmin = new JButton();
        viewAllBookButtonAdmin.setText("View all books");
        viewAllBookButtonAdmin.setBounds(200, 200, 300, 100);
        viewAllBookButtonAdmin.addActionListener(this);

        addNewBook = new JButton();
        addNewBook.setText("Add a Book");
        addNewBook.setBounds(500, 200, 300, 100);
        addNewBook.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);



        this.add(viewAllBookButtonAdmin);
        this.add(addNewBook);
        this.add(logoutButton);



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button ) {

            int index = comboBox.getSelectedIndex();
            String selectedUser = globalUsersStringList.get(index);
            String[] userInfoArray = selectedUser.replace(" ", "").split(",");






            if (userInfoArray[7].equals("admin")) {

                this.getContentPane().removeAll();
                this.getContentPane().revalidate();
                this.getContentPane().repaint();

                currentUserAdmin = new Admin(Integer.parseInt(userInfoArray[0]), userInfoArray[1], userInfoArray[2]
                        , Integer.parseInt(userInfoArray[3]), userInfoArray[4], userInfoArray[5], Role.ADMIN);

                adminFrame(currentUserAdmin);

                // main.adminAccess(userInfoArray);

            } else if (userInfoArray[7].equals("customer")) {
                this.getContentPane().removeAll();
                this.getContentPane().revalidate();
                this.getContentPane().repaint();

                List<Book> emptyList = new ArrayList<>();

                currentUserCustomer = new Customer(Integer.parseInt(userInfoArray[0]), userInfoArray[1], userInfoArray[2],
                        Integer.parseInt(userInfoArray[3]), userInfoArray[4], userInfoArray[5], Double.parseDouble(userInfoArray[6]),
                        Role.CUSTOMER, emptyList);

                customerFrame(currentUserCustomer);
            }

        }

        if(e.getSource() == logoutButton) {

            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();


            for(int i=0; i< globalUsersStringList.size(); i++) {
                try {



                    if((currentUserCustomer.toString().split(",")[0].equals(globalUsersStringList.get(i).replace(" ", "").replace(",", ", ").split(",")[0]))) {
                        globalUsersStringList.add(i, currentUserCustomer.toString());
                        globalUsersStringList.remove(i+1);
                    }

                }catch(Exception e1) {

                }

                try {
                    if((currentUserAdmin.toString().split(",")[0].equals(globalUsersStringList.get(i).replace(" ", "").replace(",", ", ").split(",")[0]))) {
                        globalUsersStringList.remove(i);
                        globalUsersStringList.add(i, currentUserAdmin.toString());
                    }


                }catch(Exception e1) {

                }
            }

            currentUserCustomer = null;
            currentUserAdmin = null;
            mainFrame(globalUsersStringList,globaluserNameStringList);
        }

        if (e.getSource() == returnButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            adminFrame(currentUserAdmin);
        }

        if (e.getSource() == returnButton2) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            customerFrame(currentUserCustomer);
        }

        if (e.getSource() == viewAllBookButtonAdmin) {

            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            viewAllBookTableAdmin(currentUserAdmin);
        }

        if (e.getSource() == viewAllBookButtonCustomer) {

            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            viewAllBookCustomer(currentUserCustomer);
        }

        if (e.getSource() == addNewBook) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addNewBook();
        }

        if (e.getSource() == paperbackButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addingPaperbackBook(currentUserAdmin);
        }

        if (e.getSource() == ebookButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addingEBook(currentUserAdmin);
        }

        if (e.getSource() == audiobookButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addingAudioBook(currentUserAdmin);
        }
        try {

            if (e.getSource() == addPaperbackButton) {


                String barcode  =barcodeField.getText();
                String inputText = languageField.getText();
                Pattern eightDigit = Pattern.compile("^\\d{8}$"); // Regex pattern for 8 digits
                Matcher matcher = eightDigit.matcher(barcode);

                if(!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Barcode must only be 8 digits long.");
                }
                else if(!(languageField.getText().equalsIgnoreCase("English") || languageField.getText().equalsIgnoreCase("French"))) {
                    JOptionPane.showMessageDialog(null, "language can only be English or French");

                }
                else if(!(genreField.getText().equalsIgnoreCase("Politics") || genreField.getText().equalsIgnoreCase("Business") || genreField.getText().equalsIgnoreCase("Computer Science") || genreField.getText().equalsIgnoreCase("Biography"))) {
                    JOptionPane.showMessageDialog(null, "Genre can only be Politics, Business, Computer Science, or Biography");
                }
                else {
                    BookFactory bookFactory = new BookFactory();
                    List<Book> booksOptionList = bookFactory.typeOfBookListObj();
                    int index =0 ;

                    for(Book book : booksOptionList) {

                        if(Integer.parseInt(barcode) == book.getBarcode()) {
                            JOptionPane.showMessageDialog(null, "This book is already in the system");
                            break;
                        }

                        index++;
                        if(index == booksOptionList.size()) {
                            currentUserAdmin.addPaperback(Integer.parseInt(barcodeField.getText()), TypesOfBooks.PAPERBACK, titleField.getText(),
                                    languageField.getText(), genreField.getText(), dateCombobox.getSelectedItem().toString(),
                                    Integer.parseInt(quantityField.getText()), Double.parseDouble(retailPriceField.getText()),
                                    Integer.parseInt(noOfPagesField.getText()), conditionBox.getSelectedItem().toString());
                        }
                    }

                }
            }
        } catch (Exception ex) {
            List<String> wrongInputType = new ArrayList<>();

            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
        }

        try{
            if (e.getSource() == addEbookButton) {
                String barcode  =barcodeField.getText();
                String inputText = languageField.getText();
                Pattern eightDigit = Pattern.compile("^\\d{8}$"); // Regex pattern for 8 digits
                Matcher matcher = eightDigit.matcher(barcode);

                if(!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Barcode must only be 8 digits long.");
                }
                else if(!(languageField.getText().equalsIgnoreCase("English") || languageField.getText().equalsIgnoreCase("French"))) {
                    JOptionPane.showMessageDialog(null, "language can only be English or French");

                }
                else if(!(genreField.getText().equalsIgnoreCase("Politics") || genreField.getText().equalsIgnoreCase("Business") || genreField.getText().equalsIgnoreCase("Computer Science") || genreField.getText().equalsIgnoreCase("Biography"))) {
                    JOptionPane.showMessageDialog(null, "Genre can only be Politics, Business, Computer Science, or Biography");
                }
                else {
                    BookFactory bookFactory = new BookFactory();
                    List<Book> booksOptionList = bookFactory.typeOfBookListObj();
                    int index =0 ;

                    for(Book book : booksOptionList) {

                        if(Integer.parseInt(barcode) == book.getBarcode()) {
                            JOptionPane.showMessageDialog(null, "This book is already in the system");
                            break;
                        }

                        index++;
                        if(index == booksOptionList.size()) {
                            currentUserAdmin.addEbook(Integer.parseInt(barcodeField.getText()), TypesOfBooks.EBOOK, titleField.getText(),
                                    languageField.getText(), genreField.getText(), dateCombobox.getSelectedItem().toString(),
                                    Integer.parseInt(quantityField.getText()), Double.parseDouble(retailPriceField.getText()),
                                    Integer.parseInt(noOfPagesField.getText()), (EbookFormat) formatBox.getSelectedItem());
                        }
                    }}}} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer value.");
        }

        try{


            if (e.getSource() == addAudioBookButton) {

                String barcode  =barcodeField.getText();
                String inputText = languageField.getText();
                Pattern eightDigit = Pattern.compile("^\\d{8}$"); // Regex pattern for 8 digits
                Matcher matcher = eightDigit.matcher(barcode);

                if(!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Barcode must only be 8 digits long.");
                }
                else if(!(languageField.getText().equalsIgnoreCase("English") || languageField.getText().equalsIgnoreCase("French"))) {
                    JOptionPane.showMessageDialog(null, "language can only be English or French");

                }
                else if(!(genreField.getText().equalsIgnoreCase("Politics") || genreField.getText().equalsIgnoreCase("Business") || genreField.getText().equalsIgnoreCase("Computer Science") || genreField.getText().equalsIgnoreCase("Biography"))) {
                    JOptionPane.showMessageDialog(null, "Genre can only be Politics, Business, Computer Science, or Biography");
                }
                else {
                    BookFactory bookFactory = new BookFactory();
                    List<Book> booksOptionList = bookFactory.typeOfBookListObj();
                    int index =0 ;

                    for(Book book : booksOptionList) {

                        if(Integer.parseInt(barcode) == book.getBarcode()) {
                            JOptionPane.showMessageDialog(null, "This book is already in the system");
                            break;
                        }

                        index++;
                        if(index == booksOptionList.size()) {
                            currentUserAdmin.addAudioBook(Integer.parseInt(barcodeField.getText()), TypesOfBooks.AUDIOBOOK, titleField.getText(),
                                    languageField.getText(), genreField.getText(), dateCombobox.getSelectedItem().toString(),
                                    Integer.parseInt(quantityField.getText()), Double.parseDouble(retailPriceField.getText()),
                                    Double.parseDouble(listeningLengthField.getText()), (AudiobookFormat) formatBox.getSelectedItem());
                        }}}}} catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer value.");
        }

        if (e.getSource() == searchFilterButton || e.getSource() == resetMenu) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            BookFactory bookFactory = new BookFactory();
            List<Book> booksOptionList = bookFactory.typeOfBookListObj();

            addBookToBasket(currentUserCustomer, booksOptionList);
        }

        if (e.getSource() == listeningLengthFilterButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addBookToBasket(currentUserCustomer, currentUserCustomer.listeningLengthFilter(Double.parseDouble(listeningLengthFilterField.getText())));
        }

        if (e.getSource() == barcodeFilterButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            addBookToBasket(currentUserCustomer, currentUserCustomer.barcodeSearch(Integer.parseInt(barcodeFilterField.getText())));
        }

        if (e.getSource() == addToBasketButton) {

            int selectedItem = Integer.parseInt(booksOptionComboBox.getSelectedItem().toString().split(",")[0]);

            if (currentUserCustomer.addItem(selectedItem) != null){
                JOptionPane.showMessageDialog(null, currentUserCustomer.addItem(selectedItem));
            }

            else {
//                currentUserCustomer.addItem(selectedItem);
            }

        }


        if (e.getSource() == shoppingBasketButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            viewShoppingBasket(currentUserCustomer);
        }

        if (e.getSource() == clearBasketButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            currentUserCustomer.clearBasket();
            viewShoppingBasket(currentUserCustomer);
        }

        if (e.getSource() == checkoutButton) {
            this.getContentPane().removeAll();
            this.getContentPane().revalidate();
            this.getContentPane().repaint();

            reciptFrame(currentUserCustomer);
        }

    }

    private void reciptFrame(Customer currentUserCustomer) {
        String finalMessage = currentUserCustomer.checkout();

        JLabel finalLabel = new JLabel(finalMessage);

        returnButton2 = new JButton("return");

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        this.add(finalLabel);

        returnButton2.addActionListener(this);
        this.add(returnButton2);
        this.add(logoutButton);


    }

    private void viewShoppingBasket(Customer currentUserCustomer) {
        DefaultListModel<Book> bookModel = new DefaultListModel<Book>();

        for (Book book : currentUserCustomer.getShoppingBasket()) {
            bookModel.addElement(book);
        }

        JList<Book> bookJList = new JList<>(bookModel);
        JScrollPane scrollPane = new JScrollPane(bookJList);

        checkoutButton = new JButton("Checkout");
        clearBasketButton = new JButton("Clear Basket");

        clearBasketButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        returnButton2 = new JButton("return");
        returnButton2.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        this.add(scrollPane);
        this.add(checkoutButton);
        this.add(clearBasketButton);
        this.add(returnButton2);
        this.add(logoutButton);

    }

    private void addBookToBasket(Customer currentUserCustomer, List<Book> booksOptionList) {
        JLabel barcodeLabel = new JLabel("Search by barcode:");
        barcodeFilterField = new JTextField(20);
        barcodeFilterButton = new JButton("Search");
        barcodeFilterButton.addActionListener(this);

        JLabel listeningLengthLabel = new JLabel("Filter by listening length:");
        listeningLengthFilterField = new JTextField(20);
        listeningLengthFilterButton = new JButton("Filter");
        listeningLengthFilterButton.addActionListener(this);

        resetMenu = new JButton("reset menu");
        resetMenu.addActionListener(this);

        booksOptionComboBox = new JComboBox(booksOptionList.toArray());
        addToBasketButton = new JButton("Add to Basket");
        addToBasketButton.addActionListener(this);

        returnButton2 = new JButton("return");
        returnButton2.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);



        this.add(barcodeLabel);
        this.add(barcodeFilterField);
        this.add(barcodeFilterButton);

        this.add(listeningLengthLabel);
        this.add(listeningLengthFilterField);
        this.add(listeningLengthFilterButton);


        this.add(booksOptionComboBox);
        this.add(addToBasketButton);

        this.add(resetMenu);

        this.add(returnButton2);

        this.add(logoutButton);
    }

    private void viewAllBookCustomer(Customer currentUserCustomer) {
        List<Book> sortedList = currentUserCustomer.viewAllBooks();

        String[] columnNames = {"Barcode", "Type", "Title", "Language", "Genre", "Release date", "Quantity in stock",
                "Retail price", "Number of pages", "Condition", "Listening Length", "Format"};

        String[][] sortedList2DArray = new String[sortedList.size()][12];

        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = 0; j <= 11; j++) {


                if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.EBOOK.name()) && (j == 9 || j == 10)) {
                    sortedList2DArray[i][9] = null;
                    sortedList2DArray[i][10] = null;
                    sortedList2DArray[i][11] = sortedList.get(i).toString().split(",")[j];
                    j = 11;

                } else if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.AUDIOBOOK.name()) && (j == 8 || j == 9)) {
                    sortedList2DArray[i][8] = null;
                    sortedList2DArray[i][9] = null;
                    sortedList2DArray[i][10] = sortedList.get(i).toString().split(",")[j];
                    sortedList2DArray[i][11] = sortedList.get(i).toString().split(",")[j + 1];
                    j = 11;

                } else if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.PAPERBACK.name()) && (j == 10 || j == 11)) {
                    sortedList2DArray[i][10] = null;
                    sortedList2DArray[i][11] = null;

                } else {
                    sortedList2DArray[i][j] = sortedList.get(i).toString().split(",")[j];
                }
            }
        }


        JTable table = new JTable(sortedList2DArray, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));

        table.getColumnModel().getColumn(2).setPreferredWidth(200);


        returnButton2 = new JButton("Return");
        returnButton2.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);



        this.add(scrollPane);
        this.add(returnButton2);
        this.add(logoutButton);

    }

    private void customerFrame(Customer currentUser) {
        this.setLayout(new FlowLayout());
        viewAllBookButtonCustomer = new JButton("View all Book");
        viewAllBookButtonCustomer.addActionListener(this);

        shoppingBasketButton = new JButton("Shopping Basket");
        shoppingBasketButton.addActionListener(this);

        searchFilterButton = new JButton("Add book to basket");
        searchFilterButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        this.add(viewAllBookButtonCustomer);
        this.add(shoppingBasketButton);
        this.add(searchFilterButton);
        this.add(logoutButton);
    }

    private void addingPaperbackBook(Admin currentUser) {
        paperbackBookPanel = new JPanel();
        paperbackBookPanel.setLayout(new GridLayout(11, 2));

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(600, 500));

        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(600, 500));

        paperbackBookPanel.setPreferredSize(new Dimension(500, 400));

        barcodeField = new JTextField(20);
        JLabel barcodeLabel = new JLabel("Barcode:");

        titleField = new JTextField(20);
        JLabel titleLabel = new JLabel("Title:");

        languageField = new JTextField(20);
        JLabel languageLabel = new JLabel("Language:");

        genreField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");

        JLabel dateLabel = new JLabel("Release Date :");


        quantityField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");

        retailPriceField = new JTextField(20);
        JLabel retailPriceLabel = new JLabel("Retail Price:");

        noOfPagesField = new JTextField(20);
        JLabel noOfPagesLabel = new JLabel("No of Pages:");

        String[] conditionBoxOptions = {"new", "used"};
        conditionBox = new JComboBox(conditionBoxOptions);
        JLabel conditionLabel = new JLabel("Condition");



        paperbackBookPanel.add(barcodeLabel);
        paperbackBookPanel.add(barcodeField);


        paperbackBookPanel.add(titleLabel);
        paperbackBookPanel.add(titleField);


        paperbackBookPanel.add(languageLabel);
        paperbackBookPanel.add(languageField);


        paperbackBookPanel.add(genreLabel);
        paperbackBookPanel.add(genreField);



        LocalDate todaysDate = LocalDate.now();
        String[] dates = new String[300001];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 300000; i >= 0; i--) {
            dates[i] = todaysDate.minusDays(i).format(format);
        }
        dateCombobox = new JComboBox(dates);
        paperbackBookPanel.add(dateLabel);
        paperbackBookPanel.add(dateCombobox);


        paperbackBookPanel.add(quantityLabel);
        paperbackBookPanel.add(quantityField);


        paperbackBookPanel.add(retailPriceLabel);
        paperbackBookPanel.add(retailPriceField);


        paperbackBookPanel.add(noOfPagesLabel);
        paperbackBookPanel.add(noOfPagesField);


        paperbackBookPanel.add(conditionLabel);
        paperbackBookPanel.add(conditionBox);


        addPaperbackButton = new JButton("Add Book");
        addPaperbackButton.addActionListener(this);
        addPaperbackButton.setPreferredSize(new Dimension(300, 50));

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);





        this.setLayout(new BorderLayout());

        this.add(emptyPanel1, BorderLayout.EAST);
        this.add(emptyPanel2, BorderLayout.WEST);
        this.add(paperbackBookPanel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));

        returnButton = new JButton("return");
        returnButton.addActionListener(this);

        panel2.add(addPaperbackButton);
        panel2.add(returnButton);
        panel2.add(logoutButton);

        this.add(panel2, BorderLayout.SOUTH);



    }

    private void addingAudioBook(Admin currentUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(600, 500));

        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(600, 500));

        panel.setPreferredSize(new Dimension(300, 400));

        barcodeField = new JTextField(20);
        JLabel barcodeLabel = new JLabel("Barcode:");

        titleField = new JTextField(20);
        JLabel titleLabel = new JLabel("Title:");

        languageField = new JTextField(20);
        JLabel languageLabel = new JLabel("Language:");

        genreField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");

        JLabel dateLabel = new JLabel("Release Date :");


        quantityField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");

        retailPriceField = new JTextField(20);
        JLabel retailPriceLabel = new JLabel("Retail Price:");

        listeningLengthField = new JTextField(20);
        JLabel listeningLengthLabel = new JLabel("Listening Length:");

        AudiobookFormat[] formatBoxOptions = AudiobookFormat.values();
        formatBox = new JComboBox(formatBoxOptions);
        JLabel formatLabel = new JLabel("Format");

        panel.add(barcodeLabel);
        panel.add(barcodeField);

        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(languageLabel);
        panel.add(languageField);

        panel.add(genreLabel);
        panel.add(genreField);


        LocalDate todaysDate = LocalDate.now();
        String[] dates = new String[300001];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 300000; i >= 0; i--) {
            dates[i] = todaysDate.minusDays(i).format(format);
        }
        dateCombobox = new JComboBox(dates);
        panel.add(dateLabel);
        panel.add(dateCombobox);

        panel.add(quantityLabel);
        panel.add(quantityField);

        panel.add(retailPriceLabel);
        panel.add(retailPriceField);

        panel.add(listeningLengthLabel);
        panel.add(listeningLengthField);

        panel.add(formatLabel);
        panel.add(formatBox);

        addAudioBookButton = new JButton("Add Book");
        addAudioBookButton.addActionListener(this);
        addAudioBookButton.setPreferredSize(new Dimension(300, 50));


        this.setLayout(new BorderLayout());

        this.add(emptyPanel1, BorderLayout.EAST);
        this.add(emptyPanel2, BorderLayout.WEST);
        this.add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));

        returnButton = new JButton("return");
        returnButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        panel2.add(addAudioBookButton);
        panel2.add(returnButton);
        panel2.add(logoutButton);

        this.add(panel2, BorderLayout.SOUTH);


    }

    private void addingEBook(Admin currentUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(600, 500));

        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(600, 500));

        panel.setPreferredSize(new Dimension(300, 400));

        barcodeField = new JTextField(20);
        JLabel barcodeLabel = new JLabel("Barcode:");

        titleField = new JTextField(20);
        JLabel titleLabel = new JLabel("Title:");

        languageField = new JTextField(20);
        JLabel languageLabel = new JLabel("Language:");

        genreField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");

        JLabel dateLabel = new JLabel("Release Date :");


        quantityField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");

        retailPriceField = new JTextField(20);
        JLabel retailPriceLabel = new JLabel("Retail Price:");

        noOfPagesField = new JTextField(20);
        JLabel noOfPagesLabel = new JLabel("No of Pages:");

        EbookFormat[] formatBoxOptions = EbookFormat.values();
        formatBox = new JComboBox(formatBoxOptions);
        JLabel formatLabel = new JLabel("Format");

        panel.add(barcodeLabel);
        panel.add(barcodeField);

        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(languageLabel);
        panel.add(languageField);

        panel.add(genreLabel);
        panel.add(genreField);


        LocalDate todaysDate = LocalDate.now();
        String[] dates = new String[300001];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 300000; i >= 0; i--) {
            dates[i] = todaysDate.minusDays(i).format(format);
        }
        dateCombobox = new JComboBox(dates);
        panel.add(dateLabel);
        panel.add(dateCombobox);

        panel.add(quantityLabel);
        panel.add(quantityField);

        panel.add(retailPriceLabel);
        panel.add(retailPriceField);

        panel.add(noOfPagesLabel);
        panel.add(noOfPagesField);

        panel.add(formatLabel);
        panel.add(formatBox);

        addEbookButton = new JButton("Add Book");
        addEbookButton.addActionListener(this);
        addEbookButton.setPreferredSize(new Dimension(300, 50));


        this.setLayout(new BorderLayout());

        this.add(emptyPanel1, BorderLayout.EAST);
        this.add(emptyPanel2, BorderLayout.WEST);
        this.add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));

        returnButton = new JButton("return");
        returnButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        panel2.add(addEbookButton);
        panel2.add(returnButton);
        panel2.add(logoutButton);

        this.add(panel2, BorderLayout.SOUTH);


    }



    public void addNewBook() {
        JLabel label = new JLabel("Which type of book would you like to add");

        paperbackButton = new JButton("Paperback");
        ebookButton = new JButton("Ebook");
        audiobookButton = new JButton("Audiobook");

        paperbackButton.addActionListener(this);
        ebookButton.addActionListener(this);
        audiobookButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);


        this.add(paperbackButton);
        this.add(ebookButton);
        this.add(audiobookButton);
        this.add(logoutButton);
    }

    public void viewAllBookTableAdmin(Admin currentUser) {
        List<Book> sortedList = currentUser.viewAllBooks();

        String[] columnNames = {"Barcode", "Type", "Title", "Language", "Genre", "Release date", "quantity in stock",
                "retail price", "number of pages", "condition", "Listening Length", "Format"};

        String[][] sortedList2DArray = new String[sortedList.size()][12];
        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = 0; j <= 11; j++) {


                if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.EBOOK.name()) && (j == 9 || j == 10)) {
                    sortedList2DArray[i][9] = null;
                    sortedList2DArray[i][10] = null;
                    sortedList2DArray[i][11] = sortedList.get(i).toString().split(",")[j];
                    j = 11;

                } else if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.AUDIOBOOK.name()) && (j == 8 || j == 9)) {
                    sortedList2DArray[i][8] = null;
                    sortedList2DArray[i][9] = null;
                    sortedList2DArray[i][10] = sortedList.get(i).toString().split(",")[j];
                    sortedList2DArray[i][11] = sortedList.get(i).toString().split(",")[j + 1];
                    j = 11;

                } else if (sortedList.get(i).toString().split(",")[1].equals(TypesOfBooks.PAPERBACK.name()) && (j == 10 || j == 11)) {
                    sortedList2DArray[i][10] = null;
                    sortedList2DArray[i][11] = null;

                } else {
                    sortedList2DArray[i][j] = sortedList.get(i).toString().split(",")[j];
                }
            }
        }


        JTable table = new JTable(sortedList2DArray, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));

        table.getColumnModel().getColumn(2).setPreferredWidth(200);


        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 200, 300, 100);
        logoutButton.addActionListener(this);



        this.add(scrollPane);
        this.add(returnButton);
        this.add(logoutButton);

    }


}
