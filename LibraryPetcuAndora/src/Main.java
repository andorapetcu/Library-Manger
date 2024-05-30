import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    private static JPanel authorPanel = new JPanel(new GridLayout(6, 2));
    private static JPanel bookPanel = new JPanel(new GridLayout(6, 2));
    private static JPanel genrePanel = new JPanel(new GridLayout(6, 2));
    private static JPanel loanPanel = new JPanel(new GridLayout(6, 2));
    private static JPanel readerPanel = new JPanel(new GridLayout(6, 2));

    public static void main(String[] args) {
        JFrame f = new JFrame("Library Management System");
        f.setLayout(new BorderLayout());
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);


        f.setVisible(true);

        authorPanel = new JPanel(new GridLayout(6, 2));
        bookPanel = new JPanel(new GridLayout(6, 2));
        genrePanel = new JPanel(new GridLayout(6, 2));
        loanPanel = new JPanel(new GridLayout(6, 2));
        readerPanel = new JPanel(new GridLayout(6, 2));

        f.add(authorPanel, BorderLayout.CENTER);
        f.add(bookPanel, BorderLayout.CENTER);
        f.add(genrePanel, BorderLayout.CENTER);
        f.add(loanPanel, BorderLayout.CENTER);
        f.add(readerPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuGenres = new JMenu("Genres");
        JMenuItem menuAddGenre = new JMenuItem("Add Genre");
        JMenuItem menuGenreList = new JMenuItem("Genre List");
        menuGenres.add(menuAddGenre);
        menuGenres.add(menuGenreList);

        JMenu menuAuthors = new JMenu("Authors");
        JMenuItem menuAddAuthor = new JMenuItem("Add Author");
        JMenuItem menuAuthorList = new JMenuItem("Author List");
        menuAuthors.add(menuAddAuthor);
        menuAuthors.add(menuAuthorList);

        JMenu menuBooks = new JMenu("Books");
        JMenuItem menuAddBook = new JMenuItem("Add Book");
        JMenuItem menuBookList = new JMenuItem("Book List");
        menuBooks.add(menuAddBook);
        menuBooks.add(menuBookList);

        JMenu menuReaders = new JMenu("Readers");
        JMenuItem menuAddReader = new JMenuItem("Add Reader");
        JMenuItem menuReaderList = new JMenuItem("Reader List");
        menuReaders.add(menuAddReader);
        menuReaders.add(menuReaderList);

        JMenu menuLoans = new JMenu("Loans");
        JMenuItem menuAddLoan = new JMenuItem("Add Loan");
        JMenuItem menuLoanList = new JMenuItem("Loan List");
        menuLoans.add(menuAddLoan);
        menuLoans.add(menuLoanList);

        menuBar.add(menuGenres);
        menuBar.add(menuAuthors);
        menuBar.add(menuBooks);
        menuBar.add(menuReaders);
        menuBar.add(menuLoans);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(menuBar, BorderLayout.NORTH);
        f.add(panel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        f.add(tablePanel, BorderLayout.CENTER);

//GENRE____________________________________________________________________________________


        menuGenreList.addActionListener(e -> {
            List<Genre> genres = DBUtils.GetAllActiveGenres();
            if (genres != null) {
                JTable table = createGenreTable(genres);
                JScrollPane scrollPane = new JScrollPane(table);
                tablePanel.removeAll();
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(deleteEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int genreIdToDelete = (int) table.getValueAt(selectedRow, 0);
                        int confirmDialog = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this genre?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirmDialog == JOptionPane.YES_OPTION) {
                            boolean success = DBUtils.deleteGenre(genreIdToDelete);
                            if (success) {
                                //daca confirmi, se updateaza tabelul
                                List<Genre> updatedGenres = DBUtils.GetAllActiveGenres();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Genre genre : updatedGenres) {
                                    Object[] rowData = {genre.getGenreId(), genre.getName(), genre.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                                JOptionPane.showMessageDialog(f, "Genre deleted successfully!");
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to delete genre from the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a genre to delete");
                    }
                });

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(editEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int genreId = (int) table.getValueAt(selectedRow, 0);
                        String name = (String) table.getValueAt(selectedRow, 1);

                        String updatedName = JOptionPane.showInputDialog(f, "Edit Genre Name:", name);
                        if (updatedName != null && !updatedName.trim().isEmpty()) {
                            try {
                                DBUtils.updateGenre(genreId, updatedName);
                                //se updateaza tabelul
                                List<Genre> updatedGenres = DBUtils.GetAllActiveGenres();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Genre genre : updatedGenres) {
                                    Object[] rowData = {genre.getGenreId(), genre.getName(), genre.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                                JOptionPane.showMessageDialog(f, "Genre updated successfully!");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(f, "Failed to update genre in the database");
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Please provide a valid genre name");
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a genre to edit");
                    }
                });


                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                buttonPanel.add(deleteButton);
                buttonPanel.add(editButton);

                tablePanel.add(buttonPanel, BorderLayout.SOUTH);

                tablePanel.revalidate();
                tablePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(f, "Failed to retrieve genres from the database");
            }
        });
//AUTHOR______________________________________________________________________________________________

        menuAuthorList.addActionListener(e -> {
            List<Author> authors = DBUtils.GetAllActiveAuthors();
            if (authors != null) {
                JTable table = createAuthorTable(authors);
                JScrollPane scrollPane = new JScrollPane(table);
                tablePanel.removeAll();
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(deleteEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int authorIdToDelete = (int) table.getValueAt(selectedRow, 0);
                        int confirmDialog = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this author?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirmDialog == JOptionPane.YES_OPTION) {
                            boolean success = DBUtils.deleteAuthor(authorIdToDelete);
                            if (success) {
                                List<Author> updatedAuthors = DBUtils.GetAllActiveAuthors();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Author author : updatedAuthors) {
                                    Object[] rowData = {author.getAuthorId(), author.getFirstName(), author.getLastName(), author.getNationality(), author.getGenreId(), author.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                                JOptionPane.showMessageDialog(f, "Author deleted successfully!");
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to delete author from the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select an author to delete");
                    }
                });

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(editEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int authorId = (int) table.getValueAt(selectedRow, 0);
                        String firstName = (String) table.getValueAt(selectedRow, 1);
                        String lastName = (String) table.getValueAt(selectedRow, 2);
                        String nationality = (String) table.getValueAt(selectedRow, 3);
                        int genreId = (int) table.getValueAt(selectedRow, 4);

                        JTextField txtFirstName = new JTextField(firstName);
                        JTextField txtLastName = new JTextField(lastName);
                        JTextField txtNationality = new JTextField(nationality);

                        JPanel editPanel = new JPanel(new GridLayout(0, 2));
                        editPanel.add(new JLabel("First Name:"));
                        editPanel.add(txtFirstName);
                        editPanel.add(new JLabel("Last Name:"));
                        editPanel.add(txtLastName);
                        editPanel.add(new JLabel("Nationality:"));
                        editPanel.add(txtNationality);

                        int result = JOptionPane.showConfirmDialog(f, editPanel, "Edit Author",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            String updatedFirstName = txtFirstName.getText();
                            String updatedLastName = txtLastName.getText();
                            String updatedNationality = txtNationality.getText();

                            boolean success = DBUtils.updateAuthor(authorId, updatedFirstName, updatedLastName, updatedNationality, genreId);
                            if (success) {
                                List<Author> updatedAuthors = DBUtils.GetAllActiveAuthors();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Author author : updatedAuthors) {
                                    Object[] rowData = {author.getAuthorId(), author.getFirstName(), author.getLastName(), author.getNationality(), author.getGenreId(), author.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                                JOptionPane.showMessageDialog(f, "Author updated successfully!");
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to update author in the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select an author to edit");
                    }
                });

                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                buttonPanel.add(deleteButton);
                buttonPanel.add(editButton);

                tablePanel.add(buttonPanel, BorderLayout.SOUTH);

                tablePanel.revalidate();
                tablePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(f, "Failed to retrieve authors from the database");
            }
        });

//LOAN____________________________________________________________________________________

        menuLoanList.addActionListener(e -> {
            List<Loan> loans = DBUtils.GetAllActiveLoans();
            if (loans != null) {
                JTable table = createLoanTable(loans);
                JScrollPane scrollPane = new JScrollPane(table);
                tablePanel.removeAll();
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(deleteEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int loanIdToDelete = (int) table.getValueAt(selectedRow, 0);
                        int confirmDialog = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this loan?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirmDialog == JOptionPane.YES_OPTION) {
                            boolean success = DBUtils.deleteLoan(loanIdToDelete);
                            if (success) {
                                List<Loan> updatedLoans = DBUtils.GetAllActiveLoans();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Loan loan : updatedLoans) {
                                    Object[] rowData = {loan.getLoanId(), loan.getBookId(), loan.getReaderId(), loan.getLoanDate(), loan.getReturnDate(), loan.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to delete loan from the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a loan to delete");
                    }
                });

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(editEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int loanId = (int) table.getValueAt(selectedRow, 0);
                        int bookId = (int) table.getValueAt(selectedRow, 1);
                        int readerId = (int) table.getValueAt(selectedRow, 2);
                        java.sql.Date loanDate = (java.sql.Date) table.getValueAt(selectedRow, 3);
                        java.sql.Date returnDate = (java.sql.Date) table.getValueAt(selectedRow, 4);

                        JTextField txtBookId = new JTextField(String.valueOf(bookId));
                        JTextField txtReaderId = new JTextField(String.valueOf(readerId));
                        JTextField txtLoanDate = new JTextField(String.valueOf(loanDate));
                        JTextField txtReturnDate = new JTextField(String.valueOf(returnDate));

                        JPanel editPanel = new JPanel(new GridLayout(0, 2));
                        editPanel.add(new JLabel("Book ID:"));
                        editPanel.add(txtBookId);
                        editPanel.add(new JLabel("Reader ID:"));
                        editPanel.add(txtReaderId);
                        editPanel.add(new JLabel("Loan Date:"));
                        editPanel.add(txtLoanDate);
                        editPanel.add(new JLabel("Return Date:"));
                        editPanel.add(txtReturnDate);

                        int result = JOptionPane.showConfirmDialog(f, editPanel, "Edit Loan",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            int updatedBookId = Integer.parseInt(txtBookId.getText());
                            int updatedReaderId = Integer.parseInt(txtReaderId.getText());
                            java.sql.Date updatedLoanDate = java.sql.Date.valueOf(txtLoanDate.getText());
                            java.sql.Date updatedReturnDate = java.sql.Date.valueOf(txtReturnDate.getText());

                            boolean success = DBUtils.updateLoan(loanId, updatedBookId, updatedReaderId, updatedLoanDate, updatedReturnDate);
                            if (success) {
                                List<Loan> updatedLoans = DBUtils.GetAllActiveLoans();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Loan loan : updatedLoans) {
                                    Object[] rowData = {loan.getLoanId(), loan.getBookId(), loan.getReaderId(), loan.getLoanDate(), loan.getReturnDate(), loan.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to update loan in the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a loan to edit");
                    }
                });

                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                buttonPanel.add(deleteButton);
                buttonPanel.add(editButton);

                tablePanel.add(buttonPanel, BorderLayout.SOUTH);

                tablePanel.revalidate();
                tablePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(f, "Failed to retrieve loans from the database");
            }
        });

//READER______________________________________________________________________________________________________

        menuReaderList.addActionListener(e -> {
            List<Reader> readers = DBUtils.GetAllActiveReaders();
            if (readers != null) {
                JTable table = createReaderTable(readers);
                JScrollPane scrollPane = new JScrollPane(table);
                tablePanel.removeAll();
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(deleteEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int readerIdToDelete = (int) table.getValueAt(selectedRow, 0);
                        int confirmDialog = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this reader?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirmDialog == JOptionPane.YES_OPTION) {
                            boolean success = DBUtils.deleteReader(readerIdToDelete);
                            if (success) {
                                List<Reader> updatedReaders = DBUtils.GetAllActiveReaders();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Reader reader : updatedReaders) {
                                    Object[] rowData = {reader.getReaderId(), reader.getFirstName(), reader.getLastName(), reader.getCNP(), reader.getPhone(), reader.getEmail(), reader.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to delete reader from the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a reader to delete");
                    }
                });

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(editEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int readerId = (int) table.getValueAt(selectedRow, 0);
                        String firstName = (String) table.getValueAt(selectedRow, 1);
                        String lastName = (String) table.getValueAt(selectedRow, 2);
                        String cnp = (String) table.getValueAt(selectedRow, 3);
                        String phone = (String) table.getValueAt(selectedRow, 4);
                        String email = (String) table.getValueAt(selectedRow, 5);

                        JTextField txtFirstName = new JTextField(firstName);
                        JTextField txtLastName = new JTextField(lastName);
                        JTextField txtCNP = new JTextField(cnp);
                        JTextField txtPhone = new JTextField(phone);
                        JTextField txtEmail = new JTextField(email);

                        JPanel editPanel = new JPanel(new GridLayout(0, 2));
                        editPanel.add(new JLabel("First Name:"));
                        editPanel.add(txtFirstName);
                        editPanel.add(new JLabel("Last Name:"));
                        editPanel.add(txtLastName);
                        editPanel.add(new JLabel("CNP:"));
                        editPanel.add(txtCNP);
                        editPanel.add(new JLabel("Phone:"));
                        editPanel.add(txtPhone);
                        editPanel.add(new JLabel("Email:"));
                        editPanel.add(txtEmail);

                        int result = JOptionPane.showConfirmDialog(f, editPanel, "Edit Reader",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            String updatedFirstName = txtFirstName.getText();
                            String updatedLastName = txtLastName.getText();
                            String updatedCNP = txtCNP.getText();
                            String updatedPhone = txtPhone.getText();
                            String updatedEmail = txtEmail.getText();
                            boolean success = DBUtils.updateReader(readerId, updatedFirstName, updatedLastName, updatedCNP, updatedPhone, updatedEmail);
                            if (success) {
                                List<Reader> updatedReaders = DBUtils.GetAllActiveReaders();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Reader reader : updatedReaders) {
                                    Object[] rowData = {reader.getReaderId(), reader.getFirstName(), reader.getLastName(), reader.getCNP(), reader.getPhone(), reader.getEmail(), reader.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to update reader in the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a reader to edit");
                    }
                });

                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                buttonPanel.add(deleteButton);
                buttonPanel.add(editButton);

                tablePanel.add(buttonPanel, BorderLayout.SOUTH);

                tablePanel.revalidate();
                tablePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(f, "Failed to retrieve readers from the database");
            }
        });



//BOOK______________________________________________________________________________________________________________________

        menuBookList.addActionListener(e -> {
            List<Book> books = DBUtils.GetAllActiveBooks();
            if (books != null) {
                JTable table = createBookTable(books);
                JScrollPane scrollPane = new JScrollPane(table);
                tablePanel.removeAll();
                tablePanel.add(scrollPane, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(deleteEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int bookIdToDelete = (int) table.getValueAt(selectedRow, 0);
                        int confirmDialog = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this book?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirmDialog == JOptionPane.YES_OPTION) {
                            boolean success = DBUtils.deleteBook(bookIdToDelete);
                            if (success) {
                                List<Book> updatedBooks = DBUtils.GetAllActiveBooks();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Book book : updatedBooks) {
                                    Object[] rowData = {book.getBookId(), book.getTitle(), book.getAuthorId(), book.getPublisher(), book.getGenreId(), book.getPublicationYear(), book.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to delete book from the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a book to delete");
                    }
                });

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(editEvent -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int bookId = (int) table.getValueAt(selectedRow, 0);
                        String title = (String) table.getValueAt(selectedRow, 1);
                        int authorId = (int) table.getValueAt(selectedRow, 2);
                        String publisher = (String) table.getValueAt(selectedRow, 3);
                        int genreId = (int) table.getValueAt(selectedRow, 4);
                        int publicationYear = (int) table.getValueAt(selectedRow, 5);

                        JTextField txtTitle = new JTextField(title);
                        JTextField txtAuthorId = new JTextField(String.valueOf(authorId));
                        JTextField txtPublisher = new JTextField(publisher);
                        JTextField txtGenreId = new JTextField(String.valueOf(genreId));
                        JTextField txtPublicationYear = new JTextField(String.valueOf(publicationYear));

                        JPanel editPanel = new JPanel(new GridLayout(0, 2));
                        editPanel.add(new JLabel("Title:"));
                        editPanel.add(txtTitle);
                        editPanel.add(new JLabel("Author ID:"));
                        editPanel.add(txtAuthorId);
                        editPanel.add(new JLabel("Publisher:"));
                        editPanel.add(txtPublisher);
                        editPanel.add(new JLabel("Genre ID:"));
                        editPanel.add(txtGenreId);
                        editPanel.add(new JLabel("Publication Year:"));
                        editPanel.add(txtPublicationYear);

                        int result = JOptionPane.showConfirmDialog(f, editPanel, "Edit Book",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            String updatedTitle = txtTitle.getText();
                            int updatedAuthorId = Integer.parseInt(txtAuthorId.getText());
                            String updatedPublisher = txtPublisher.getText();
                            int updatedGenreId = Integer.parseInt(txtGenreId.getText());
                            int updatedPublicationYear = Integer.parseInt(txtPublicationYear.getText());

                            boolean success = DBUtils.updateBook(bookId, updatedTitle, updatedAuthorId, updatedPublisher, updatedGenreId, updatedPublicationYear);
                            if (success) {
                                List<Book> updatedBooks = DBUtils.GetAllActiveBooks();
                                DefaultTableModel updatedModel = (DefaultTableModel) table.getModel();
                                updatedModel.setRowCount(0);
                                for (Book book : updatedBooks) {
                                    Object[] rowData = {book.getBookId(), book.getTitle(), book.getAuthorId(), book.getPublisher(), book.getGenreId(), book.getPublicationYear(), book.isActive()};
                                    updatedModel.addRow(rowData);
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Failed to update book in the database");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a book to edit");
                    }
                });

                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                buttonPanel.add(deleteButton);
                buttonPanel.add(editButton);

                tablePanel.add(buttonPanel, BorderLayout.SOUTH);

                tablePanel.revalidate();
                tablePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(f, "Failed to retrieve books from the database");
            }
        });



//ADD_____________________________________________________________________________________________________________________________


        menuAddAuthor.addActionListener(e -> {
            AuthorAttributes(-1);
        });

        menuAddBook.addActionListener(e -> {
            BookAttributes(-1);
        });

        menuAddGenre.addActionListener(e -> {
            GenreAttributes(-1);
        });

        menuAddLoan.addActionListener(e -> {
            LoanAttributes(-1);
        });

        menuAddReader.addActionListener(e -> {
            ReaderAttributes(-1);
        });


        f.setVisible(true);

        f.setLayout(new BorderLayout());

        authorPanel.setPreferredSize(new Dimension(f.getWidth(), 200));
        f.add(authorPanel, BorderLayout.NORTH);
        f.setJMenuBar(menuBar);

        f.setVisible(true);

        f.setLayout(new BorderLayout());

        bookPanel.setPreferredSize(new Dimension(f.getWidth(), 200));
        f.add(bookPanel, BorderLayout.NORTH);
        f.setJMenuBar(menuBar);

        f.setVisible(true);

        f.setLayout(new BorderLayout());

        genrePanel.setPreferredSize(new Dimension(f.getWidth(), 200));
        f.add(genrePanel, BorderLayout.NORTH);
        f.setJMenuBar(menuBar);

        f.setVisible(true);

        f.setLayout(new BorderLayout());

        loanPanel.setPreferredSize(new Dimension(f.getWidth(), 200));
        f.add(loanPanel, BorderLayout.NORTH);
        f.setJMenuBar(menuBar);

        f.setVisible(true);

        f.setLayout(new BorderLayout());

        readerPanel.setPreferredSize(new Dimension(f.getWidth(), 200));
        f.add(readerPanel, BorderLayout.NORTH);
        f.setJMenuBar(menuBar);
    }



//CREATE TABLES_____________________________________________________________________________________________________________________________________________
    private static JTable createGenreTable(List<Genre> genres) {
        String[] columnNames = {"Genre ID", "Name", "Active"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Genre genre : genres) {
            Object[] rowData = {genre.getGenreId(), genre.getName(), genre.isActive()};
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        columnModel.getColumn(2).setMinWidth(0);
        columnModel.getColumn(2).setMaxWidth(0);
        columnModel.getColumn(2).setWidth(0);

        return table;
    }

    private static JTable createAuthorTable(List<Author> authors) {
        String[] columnNames = {"Author ID", "First Name", "Last Name", "Nationality", "Genre ID", "Active"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Author author : authors) {
            Object[] rowData = {
                    author.getAuthorId(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getNationality(),
                    author.getGenreId(),
                    author.isActive()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);
        columnModel.getColumn(5).setWidth(0);

        return table;
    }

    private static JTable createLoanTable(List<Loan> loans) {
        String[] columnNames = {"Loan ID", "Book ID", "Reader ID", "Loan Date", "Return Date", "Active"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Loan loan : loans) {
            Object[] rowData = {
                    loan.getLoanId(),
                    loan.getBookId(),
                    loan.getReaderId(),
                    loan.getLoanDate(),
                    loan.getReturnDate(),
                    loan.isActive()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        columnModel.getColumn(5).setMinWidth(0);
        columnModel.getColumn(5).setMaxWidth(0);
        columnModel.getColumn(5).setWidth(0);

        return table;
    }

    private static JTable createReaderTable(List<Reader> readers) {
        String[] columnNames = {"Reader ID", "First Name", "Last Name", "CNP", "Phone", "Email", "Active"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Reader reader : readers) {
            Object[] rowData = {
                    reader.getReaderId(),
                    reader.getFirstName(),
                    reader.getLastName(),
                    reader.getCNP(),
                    reader.getPhone(),
                    reader.getEmail(),
                    reader.isActive()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        columnModel.getColumn(6).setMinWidth(0);
        columnModel.getColumn(6).setMaxWidth(0);
        columnModel.getColumn(6).setWidth(0);

        return table;
    }


    private static JTable createBookTable(List<Book> books) {
        String[] columnNames = {"Book ID", "Title", "Author ID", "Publisher", "Genre ID", "Publication Year", "Active"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Book book : books) {
            Object[] rowData = {
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthorId(),
                    book.getPublisher(),
                    book.getGenreId(),
                    book.getPublicationYear(),
                    book.isActive()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        columnModel.getColumn(6).setMinWidth(0);
        columnModel.getColumn(6).setMaxWidth(0);
        columnModel.getColumn(6).setWidth(0);

        return table;
    }



//ADD DIALOG____________________________________________________________________________________________________________________________________________________________________
    private static void AuthorAttributes(int authorId) {
        try {
            authorPanel.removeAll();

            JLabel lblFirstName = new JLabel("First Name:");
            JTextField txtFirstName = new JTextField();
            JLabel lblLastName = new JLabel("Last Name:");
            JTextField txtLastName = new JTextField();
            JLabel lblNationality = new JLabel("Nationality:");
            JTextField txtNationality = new JTextField();
            JLabel lblGenreId = new JLabel("Genre ID:");
            JTextField txtGenreId = new JTextField();

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> {
                try {
                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String nationality = txtNationality.getText();
                    int genreId = Integer.parseInt(txtGenreId.getText());
                    DBUtils.addAuthor(firstName, lastName, nationality, genreId);
                    authorPanel.removeAll();
                    authorPanel.revalidate();
                    authorPanel.repaint();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid format: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            });

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> {
                authorPanel.removeAll();
                authorPanel.revalidate();
                authorPanel.repaint();
            });

            authorPanel.add(lblFirstName);
            authorPanel.add(txtFirstName);
            authorPanel.add(lblLastName);
            authorPanel.add(txtLastName);
            authorPanel.add(lblNationality);
            authorPanel.add(txtNationality);
            authorPanel.add(lblGenreId);
            authorPanel.add(txtGenreId);
            authorPanel.add(btnSave);
            authorPanel.add(btnCancel);

            authorPanel.revalidate();
            authorPanel.repaint();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    authorPanel.revalidate();
                    authorPanel.repaint();
                }
            });
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }

    private static void BookAttributes(int bookId) {
        try {
            bookPanel.removeAll();

            JLabel lblTitle = new JLabel("Title:");
            JTextField txtTitle = new JTextField();
            JLabel lblAuthorId = new JLabel("Author ID:");
            JTextField txtAuthorId = new JTextField();
            JLabel lblPublisher = new JLabel("Publisher:");
            JTextField txtPublisher = new JTextField();
            JLabel lblGenreId = new JLabel("Genre ID:");
            JTextField txtGenreId = new JTextField();
            JLabel lblPublicationYear = new JLabel("Publication Year:");
            JTextField txtPublicationYear = new JTextField();

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> {
                try {
                    String title = txtTitle.getText();
                    int authorId = Integer.parseInt(txtAuthorId.getText());
                    String publisher = txtPublisher.getText();
                    int genreId = Integer.parseInt(txtGenreId.getText());
                    int publicationYear = Integer.parseInt(txtPublicationYear.getText());

                    DBUtils.addBook(title, authorId, publisher, genreId, publicationYear);

                    bookPanel.removeAll();
                    bookPanel.revalidate();
                    bookPanel.repaint();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid format: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            });

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> {
                bookPanel.removeAll();
                bookPanel.revalidate();
                bookPanel.repaint();
            });

            bookPanel.add(lblTitle);
            bookPanel.add(txtTitle);
            bookPanel.add(lblAuthorId);
            bookPanel.add(txtAuthorId);
            bookPanel.add(lblPublisher);
            bookPanel.add(txtPublisher);
            bookPanel.add(lblGenreId);
            bookPanel.add(txtGenreId);
            bookPanel.add(lblPublicationYear);
            bookPanel.add(txtPublicationYear);
            bookPanel.add(btnSave);
            bookPanel.add(btnCancel);

            bookPanel.revalidate();
            bookPanel.repaint();
            SwingUtilities.invokeLater(() -> {
                bookPanel.revalidate();
                bookPanel.repaint();
            });
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }


    private static void GenreAttributes(int genreId) {
        try {
            genrePanel.removeAll();

            JLabel lblGenreName = new JLabel("Genre Name:");
            JTextField txtGenreName = new JTextField();

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> {
                try {
                    String genreName = txtGenreName.getText();
                    DBUtils.addGenre(genreName);
                    genrePanel.removeAll();
                    genrePanel.revalidate();
                    genrePanel.repaint();
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            });

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> {
                genrePanel.removeAll();
                genrePanel.revalidate();
                genrePanel.repaint();
            });

            genrePanel.add(lblGenreName);
            genrePanel.add(txtGenreName);
            genrePanel.add(btnSave);
            genrePanel.add(btnCancel);

            genrePanel.revalidate();
            genrePanel.repaint();
            SwingUtilities.invokeLater(() -> {
                genrePanel.revalidate();
                genrePanel.repaint();
            });
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }


    private static void LoanAttributes(int loanId) {
        try {
            loanPanel.removeAll();

            JLabel lblBookId = new JLabel("Book ID:");
            JTextField txtBookId = new JTextField();
            JLabel lblReaderId = new JLabel("Reader ID:");
            JTextField txtReaderId = new JTextField();
            JLabel lblLoanDate = new JLabel("Loan Date:");
            JTextField txtLoanDate = new JTextField();
            JLabel lblReturnDate = new JLabel("Return Date:");
            JTextField txtReturnDate = new JTextField();

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> {
                try {
                    int bookId = Integer.parseInt(txtBookId.getText());
                    int readerId = Integer.parseInt(txtReaderId.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date javaLoanDate = dateFormat.parse(txtLoanDate.getText());
                    java.util.Date javaReturnDate = dateFormat.parse(txtReturnDate.getText());

                    java.sql.Date loanDate = new java.sql.Date(javaLoanDate.getTime());
                    java.sql.Date returnDate = new java.sql.Date(javaReturnDate.getTime());

                    DBUtils.addLoan(bookId, readerId, loanDate, returnDate);

                    loanPanel.removeAll();
                    loanPanel.revalidate();
                    loanPanel.repaint();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid format: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            });

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> {
                loanPanel.removeAll();
                loanPanel.revalidate();
                loanPanel.repaint();
            });

            loanPanel.add(lblBookId);
            loanPanel.add(txtBookId);
            loanPanel.add(lblReaderId);
            loanPanel.add(txtReaderId);
            loanPanel.add(lblLoanDate);
            loanPanel.add(txtLoanDate);
            loanPanel.add(lblReturnDate);
            loanPanel.add(txtReturnDate);
            loanPanel.add(btnSave);
            loanPanel.add(btnCancel);

            loanPanel.revalidate();
            loanPanel.repaint();
            SwingUtilities.invokeLater(() -> {
                loanPanel.revalidate();
                loanPanel.repaint();
            });
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }


    private static void ReaderAttributes(int readerId) {
        try {
            readerPanel.removeAll();

            JLabel lblFirstName = new JLabel("First Name:");
            JTextField txtFirstName = new JTextField();
            JLabel lblLastName = new JLabel("Last Name:");
            JTextField txtLastName = new JTextField();
            JLabel lblCNP = new JLabel("CNP:");
            JTextField txtCNP = new JTextField();
            JLabel lblPhone = new JLabel("Phone:");
            JTextField txtPhone = new JTextField();
            JLabel lblEmail = new JLabel("Email:");
            JTextField txtEmail = new JTextField();

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> {
                try {
                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String cnp = txtCNP.getText();
                    String phone = txtPhone.getText();
                    String email = txtEmail.getText();

                    DBUtils.addReader(firstName, lastName, cnp, phone, email);

                    readerPanel.removeAll();
                    readerPanel.revalidate();
                    readerPanel.repaint();
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            });

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> {
                readerPanel.removeAll();
                readerPanel.revalidate();
                readerPanel.repaint();
            });

            readerPanel.add(lblFirstName);
            readerPanel.add(txtFirstName);
            readerPanel.add(lblLastName);
            readerPanel.add(txtLastName);
            readerPanel.add(lblCNP);
            readerPanel.add(txtCNP);
            readerPanel.add(lblPhone);
            readerPanel.add(txtPhone);
            readerPanel.add(lblEmail);
            readerPanel.add(txtEmail);
            readerPanel.add(btnSave);
            readerPanel.add(btnCancel);

            readerPanel.revalidate();
            readerPanel.repaint();
            SwingUtilities.invokeLater(() -> {
                readerPanel.revalidate();
                readerPanel.repaint();
            });
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
}